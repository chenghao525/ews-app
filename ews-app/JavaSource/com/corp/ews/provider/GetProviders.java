package com.corp.ews.provider;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class GetProviders implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {


//        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
//        if (!G.isNvl(key)) {
//            key = key.toUpperCase();
//        }

        ProviderUtil provUtl = new ProviderUtil();

        Iterator<DataObject> it = provUtl.ewsProviders(dm);

        DataObject dob = null;

        JSONArray ja = new JSONArray();
        JSONObject json = null;

        while (it.hasNext()) {
            dob = it.next();
            json = new JSONObject();
            json.put("id", dob.getString("SID"));
            json.put("text", dob.getString("PROV_NAME_SUM"));
            ja.put(json);
        }
        response.getWriter().write(ja.toString());
    }
}
