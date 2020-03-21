package com.corp.ews.mechanic;

import com.corp.basic.Constant;
import com.corp.ews.EWSConstant;
import com.corp.ews.provider.ProviderUtil;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.ExcelPoi;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportMechanics implements AjaxInterface {

    protected final Log logger = LogFactory.getLog(getClass());

    JSONArray jaLog = new JSONArray();
    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {
        // 导入和新建是同一个权限
        if (!G.isOwnRight("13401001", request)) {
            response.getWriter().write("会话已失效或无权限");
            return;
        }
        jaLog = new JSONArray();

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("");
            return;
        }
        JSONObject data = new JSONObject(key);
        key = data.getString("path");

        ServletContext servletContext = request.getSession().getServletContext();

        String path = servletContext.getRealPath("") + key.replaceAll(Constant.getBaseUrl(), "");
        path = path.replaceAll("/", "\\\\\\\\");

        String fields = getFields();

        ExcelPoi excel = new ExcelPoi();

        excel.setFields(fields);

        // 读取数据
        ArrayList<DataObject> arrayList = excel.readExcelFile(path);
        if (arrayList != null) {
            logger.info("读取文件" + path + "成功，共" + arrayList.size() + "行");
        } else {
            addLog(0, "读取文件" + path + "失败");
            logger.error("读取文件" + path + "失败");
            response = G.setRespOptFailed(response, "读取文件" + path + "失败");
            return;
        }

        String user = G.getUserNameFromSession(request);

        MechanicUtils mhcUtl = new MechanicUtils();
        ProviderUtil pUtl = new ProviderUtil();

        Iterator<String> itKey = null;
        DataObject dob = null;
        DataObject pdob = null;

        String mechanicWorkId = null;
        String grateId = null;

        int successCount = 0;
        loop: for (int i = 0; i < arrayList.size(); i++) {
            dob = arrayList.get(i);
            itKey = dob.getKeyName().iterator();
            // 字段校验
            while (itKey.hasNext()) {
                key = itKey.next();
                dob.setString(key, G.nvl(dob.getString(key), "").trim());
                // 车牌号、支付号、收件人、收件人电话、区域、地址、应收金额、经办人、坐席工号等字段都不能为空
                if (("MECHANIC_NAME".equals(key) && G.isNvl(dob.getString(key)))
                        || ("MECHANIC_WORK".equals(key) && G.isNvl(dob.getString(key)))
                        || ("PROV_SID".equals(key) && G.isNvl(dob.getString(key)))
                        || ("TEL".equals(key) && G.isNvl(dob.getString(key)))
                        || ("GRADE".equals(key) && G.isNvl(dob.getString(key)))) {
                    addLog(i + 1, "字段" + key + "数据不能为空");
                    logger.error("第" + (i + 1) + "行字段" + key + "数据不能为空");
                    break loop;
                }
            }
            // 技师岗位
            mechanicWorkId = workIdOfName("50", dob.getString("MECHANIC_WORK"), dm);
            if (inputError(mechanicWorkId, "MECHANIC_WORK", i)) {
                continue ;
            }  else {
                dob.setString("MECHANIC_WORK", mechanicWorkId);
            }
            // 技师等级
            grateId = workIdOfName("52", dob.getString("GRADE"), dm);
            if (inputError(grateId, "GRADE", i)) {
                continue ;
            }  else {
                dob.setString("GRADE", grateId);
            }
            // 性别
            if (!G.isNvl(dob.getString("SEX"))) {
                if ("男".equals(dob.getString("SEX")) ||
                        "先生".equals(dob.getString("SEX"))) {
                    dob.setString("SEX", "13");
                } else {
                    dob.setString("SEX", "14");
                }
            } else {
                dob.setString("SEX", "13");
            }
            // 门店
            pdob = pUtl.getProviderByName(dob.getString("PROV_SID"), dm);
            if (pdob == null) {
                addLog(i + 1, "字段PROV_NAME填写的不符合要求");
                logger.error("第" + (i + 1) + "行字段PROV_NAME填写的不符合要求");
                continue ;
            } else {
                dob.setString("PROV_SID", pdob.getString("SID"));
            }

            // 用友id
            if (G.isNvl(dob.getString("U8_MECHAINC_CODE"))) {
                dob.setString("U8_MECHAINC_CODE", dob.getString("TEL"));
            }

            // 新增技师
            mhcUtl.addMechanic(user, dob, dm);
            successCount++;
        }
        if (successCount > 0) {
            addLog(0000, "共成功导入" + successCount + "条数据");
            logger.info("[技师导入]: 共成功导入" + successCount + "条数据");
        }

        response.getWriter().write(EWSConstant.returnMessage(0, "ok", jaLog));

    }

    private boolean inputError(String val, String key, int i) throws Exception {

        boolean error = false;
        if (null == val) {
            addLog(i + 1, "字段" + key + "填写的不符合要求");
            logger.error("第" + (i + 1) + "行字段" + key + "填写的不符合要求");
            error = true;
        } else if ("".equals(val)) {
            addLog(i + 1, "字段" + key + "填写错误");
            logger.error("第" + (i + 1) + "行字段" + key + "填写错误");
            error = true;
        }

        return error;
    }

    private String workIdOfName(String infoType,String workName, DataManager dm) throws Exception {

        String sql = "SELECT * FROM T_LIST_STATUS WHERE STATE='1' AND INFOTYPE=? AND INSTR(CONTENT, ?) > 0";

        DataObject dob = dm.findByPrimaryKey(sql, new String[] { infoType, workName });
        if (dob == null) {
            return null;
        }

        return dob.getString("SID");
    }


    private String getFields() {
        // 技师，技师岗位，所属门店，用友id，电话，工种，工号，性别
        return "MECHANIC_NAME,MECHANIC_WORK,PROV_SID,U8_MECHAINC_CODE,TEL,GRADE,WORK_NUMBER,SEX";
    }
    private void addLog(int idx, String log) throws Exception {
        JSONObject json = new JSONObject();
        if (0 == idx) {
            json.put("item", "0000");
        } else {
            json.put("item", "第" + (idx) + "行");
        }
        json.put("log", log);

        jaLog.put(json);
    }

}
