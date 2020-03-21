package com.corp.ews.ro;

import com.corp.ews.EWSConstant;
import com.corp.ews.processLog.PLogUtils;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wqj
 */
public class ROPerfectAction implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13101100", request)) {
            response.getWriter().write("�Ự��ʧЧ����Ȩ��");
            return;
        }

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("");
            return;
        }
        JSONObject json = new JSONObject(key);
        if (json == null) {
            response.getWriter().write(EWSConstant.returnMessage(2, "���ݸ�ʽ����"));
            return;
        }

        RepairOrdersUtils roUtl = new RepairOrdersUtils();
        DataObject dob = roUtl.getROByRONumber(json.getString("RO_NUMBER"), "no", dm);
        if (dob == null) {
            response.getWriter().write(EWSConstant.returnMessage(1, "�ù���������"));
            return;
        }
        JSONArray shfJa = json.getJSONArray("SHF");

        String user = G.getUserNameFromSession(request);
        //������
        json.put("REPAIR_STATE", "yes");
        // �����ƺ�תΪ��д
        json.put("VEHICLE_LICENSE", json.getString("VEHICLE_LICENSE").toUpperCase());

        //����
        String msg = roUtl.updateRO(user, new DataObject(json), dm);
        //������
        for (int i = 0; i < shfJa.length(); i++) {
            json = shfJa.getJSONObject(i);
            roUtl.addROSheetMetalSurface(dob.getString("EWS_RO_CODE"),
                    dob.getString("RO_NUMBER"), dob.getString("SID"), json.getString("SHF_ID"),
                    json.getString("SHF_NAME"), json.getString("SHF_DESCRIPTION"), json.getString("SHF_NUMBER"), dm);
        }
        //д��ˮ
        PLogUtils plUtl = new PLogUtils();
        plUtl.addAProcess(dob.getString("EWS_RO_CODE"),
                dob.getString("RO_NUMBER"), "yes", dob.getString("PROV_SID"),
                "���ƹ���", user, dm);

        response.getWriter().write(EWSConstant.returnMessage(0, msg));
    }
}
