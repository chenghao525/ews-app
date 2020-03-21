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
        // ������½���ͬһ��Ȩ��
        if (!G.isOwnRight("13401001", request)) {
            response.getWriter().write("�Ự��ʧЧ����Ȩ��");
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

        // ��ȡ����
        ArrayList<DataObject> arrayList = excel.readExcelFile(path);
        if (arrayList != null) {
            logger.info("��ȡ�ļ�" + path + "�ɹ�����" + arrayList.size() + "��");
        } else {
            addLog(0, "��ȡ�ļ�" + path + "ʧ��");
            logger.error("��ȡ�ļ�" + path + "ʧ��");
            response = G.setRespOptFailed(response, "��ȡ�ļ�" + path + "ʧ��");
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
            // �ֶ�У��
            while (itKey.hasNext()) {
                key = itKey.next();
                dob.setString(key, G.nvl(dob.getString(key), "").trim());
                // ���ƺš�֧���š��ռ��ˡ��ռ��˵绰�����򡢵�ַ��Ӧ�ս������ˡ���ϯ���ŵ��ֶζ�����Ϊ��
                if (("MECHANIC_NAME".equals(key) && G.isNvl(dob.getString(key)))
                        || ("MECHANIC_WORK".equals(key) && G.isNvl(dob.getString(key)))
                        || ("PROV_SID".equals(key) && G.isNvl(dob.getString(key)))
                        || ("TEL".equals(key) && G.isNvl(dob.getString(key)))
                        || ("GRADE".equals(key) && G.isNvl(dob.getString(key)))) {
                    addLog(i + 1, "�ֶ�" + key + "���ݲ���Ϊ��");
                    logger.error("��" + (i + 1) + "���ֶ�" + key + "���ݲ���Ϊ��");
                    break loop;
                }
            }
            // ��ʦ��λ
            mechanicWorkId = workIdOfName("50", dob.getString("MECHANIC_WORK"), dm);
            if (inputError(mechanicWorkId, "MECHANIC_WORK", i)) {
                continue ;
            }  else {
                dob.setString("MECHANIC_WORK", mechanicWorkId);
            }
            // ��ʦ�ȼ�
            grateId = workIdOfName("52", dob.getString("GRADE"), dm);
            if (inputError(grateId, "GRADE", i)) {
                continue ;
            }  else {
                dob.setString("GRADE", grateId);
            }
            // �Ա�
            if (!G.isNvl(dob.getString("SEX"))) {
                if ("��".equals(dob.getString("SEX")) ||
                        "����".equals(dob.getString("SEX"))) {
                    dob.setString("SEX", "13");
                } else {
                    dob.setString("SEX", "14");
                }
            } else {
                dob.setString("SEX", "13");
            }
            // �ŵ�
            pdob = pUtl.getProviderByName(dob.getString("PROV_SID"), dm);
            if (pdob == null) {
                addLog(i + 1, "�ֶ�PROV_NAME��д�Ĳ�����Ҫ��");
                logger.error("��" + (i + 1) + "���ֶ�PROV_NAME��д�Ĳ�����Ҫ��");
                continue ;
            } else {
                dob.setString("PROV_SID", pdob.getString("SID"));
            }

            // ����id
            if (G.isNvl(dob.getString("U8_MECHAINC_CODE"))) {
                dob.setString("U8_MECHAINC_CODE", dob.getString("TEL"));
            }

            // ������ʦ
            mhcUtl.addMechanic(user, dob, dm);
            successCount++;
        }
        if (successCount > 0) {
            addLog(0000, "���ɹ�����" + successCount + "������");
            logger.info("[��ʦ����]: ���ɹ�����" + successCount + "������");
        }

        response.getWriter().write(EWSConstant.returnMessage(0, "ok", jaLog));

    }

    private boolean inputError(String val, String key, int i) throws Exception {

        boolean error = false;
        if (null == val) {
            addLog(i + 1, "�ֶ�" + key + "��д�Ĳ�����Ҫ��");
            logger.error("��" + (i + 1) + "���ֶ�" + key + "��д�Ĳ�����Ҫ��");
            error = true;
        } else if ("".equals(val)) {
            addLog(i + 1, "�ֶ�" + key + "��д����");
            logger.error("��" + (i + 1) + "���ֶ�" + key + "��д����");
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
        // ��ʦ����ʦ��λ�������ŵ꣬����id���绰�����֣����ţ��Ա�
        return "MECHANIC_NAME,MECHANIC_WORK,PROV_SID,U8_MECHAINC_CODE,TEL,GRADE,WORK_NUMBER,SEX";
    }
    private void addLog(int idx, String log) throws Exception {
        JSONObject json = new JSONObject();
        if (0 == idx) {
            json.put("item", "0000");
        } else {
            json.put("item", "��" + (idx) + "��");
        }
        json.put("log", log);

        jaLog.put(json);
    }

}
