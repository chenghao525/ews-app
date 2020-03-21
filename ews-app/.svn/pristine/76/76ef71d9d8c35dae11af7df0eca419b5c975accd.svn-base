package com.corp.ews.ro;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * wqj
 */
public class GetCarBrandList implements AjaxInterface {


    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        CarUtil cUtl = new CarUtil();
        Iterator<DataObject> it = cUtl.getAllCarBrand(dm);

        DataObject dob = null;

        JSONArray ja = new JSONArray();
        JSONObject json = null;

        while (it.hasNext()) {
            dob = it.next();
            json = new JSONObject();
            json.put("id", dob.getString("CAR_ID"));
            json.put("text", dob.getString("NODE_TEXT"));

            ja.put(json);
        }

        response.getWriter().write(ja.toString());
    }


}
