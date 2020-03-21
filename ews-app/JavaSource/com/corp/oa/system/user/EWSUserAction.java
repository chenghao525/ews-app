package com.corp.oa.system.user;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EWSUserAction implements AjaxInterface {


    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {


//        if (!G.isOwnRight("19902000,19902001,19902002", request)) {
//            response.getWriter().write("û��Ȩ�޻�Ự��ʧЧ");
//            return;
//        }

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("����Ϊ��");
            return;
        }

        JSONObject json = new JSONObject(key);
        if (json == null) {
            response.getWriter().write("����Ϊ��");
            return;
        }

        String optType = json.getString("TYPE");
        String sid = json.getString("SID");

        DataObject dob = null;

        UserBO ub = new UserBO();
        String sql = null;

        // �����˻���Ϣ
        if ("dtl".equals(optType)) {

            if (G.isNvl(sid)) {
                response.getWriter().write(EWSConstant.returnMessage(1, "�˺Ŷ�ӦSIDΪ��"));
                return;
            }
            sql = ub.getEmpInfoBySid();
            dob = dm.findByPrimaryKey(sql, sid);

            if (dob == null) {
                response.getWriter().write(EWSConstant.returnMessage(2,"���˺Ų����ڣ�"));
                return;
            }
            DataObject pDob = getProvSidByEmpSid(dob.getString("SID"), dm);
            if (pDob != null) {
                dob.setString("PROV_SID", pDob.getString("PROV_SID"));
            }

            response.getWriter().write(EWSConstant.returnMessage(0, "ok", dob.toJson()));
            return;
        }
        // ����
        if ("add".equals(optType)) {

            String empId = json.getString("EMPID");
            sql = ub.getEmpInfoByEmpId();
            dob = dm.findByPrimaryKey(sql, empId);
            if (dob != null) {
                response.getWriter().write(EWSConstant.returnMessage(1, "��Ա�ĵ�¼���ظ�����������д��"));
                return;
            }
            String userId = G.getUserIdFromSession(request);
//            String empType = G.getEmployeeType(request);
            ub.saveEmpInfo(json, userId, dm);

            response.getWriter().write(EWSConstant.returnMessage(0, "����ɹ�"));

           return;
        }
        // ����
        if ("update".equals(optType)) {
            String userId = G.getUserIdFromSession(request);
            ub.saveEmpInfo(json, userId, dm);
            response.getWriter().write(EWSConstant.returnMessage(0, "����ɹ�"));
            return;
        }
    }


    private DataObject getUserDetails(String sid, DataManager dm) throws Exception {

        String sql = "select * from t_info_employee where state='1' and sid=?";

        return dm.findByPrimaryKey(sql, sid);

    }

    private DataObject getProvSidByEmpSid(String empSid, DataManager dm) throws Exception {

        String sql = "SELECT * FROM T_EWS_PROVIDER_EXTENSION WHERE INSTR(EMP_SID, ?) > 0";
        sql = G.formatSql(sql, empSid);
        DataObject dob = dm.findByPrimaryKey(sql, null);

        return dob;
    }
}
