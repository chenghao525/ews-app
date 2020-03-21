package com.corp.ews.ro;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class GetOriginalRoCodes implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write(EWSConstant.returnMessage(1, "²ÎÊýÎª¿Õ"));
            return;
        }
        RepairOrdersUtils roUtl = new RepairOrdersUtils();
        String sql = roUtl.getROSql();
        sql += " and instr(vehicle_license, ?) > 0";

        JSONArray ja = new JSONArray();
        Iterator<DataObject> it = dm.find(sql, G.rqm(key).trim().toUpperCase()).iterator();
        DataObject dob = null;
        JSONObject json = null;

        while (it.hasNext()) {
            dob = it.next();
            json = new JSONObject();
            json.put("id", dob.getString("RO_NUMBER"));
            json.put("text", dob.getString("RO_NUMBER"));
            ja.put(json);
        }

        response.getWriter().write(ja.toString());
    }
}
