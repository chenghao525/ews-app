package com.corp.ews.mechanic;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * @author  wqj
 */
public class GetMechanics implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {


        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (!G.isNvl(key)) {
            key = key.toUpperCase();
        }

        JSONObject json = new JSONObject(key);
        String work = json.getString("WORK");

        MechanicUtils mUtl = new MechanicUtils();
        Tuple tuple = null;
        String sql = null;
        DataObject dob = new DataObject();

        String provSid = G.getEWSProvSidFromSession(request);
        //如果work不为空，那就查询对应的值
        if (!G.isNvl(work)) {
            dob.setString("S_MECHANIC_WORK", work);
            tuple = mUtl.getMechanicsSql(provSid, mUtl.getBaseSWSql(), dob);
            sql = tuple.getFirst();
        } else {
            //查找除了调漆技师以外的所有技师
            tuple = mUtl.getMechanicsSql(provSid, mUtl.getBaseSWSql(), dob);
            sql = tuple.getFirst();
            sql += " and mechanic_work<>'" + EWSConstant.PAINT_MAKER + "'";
        }

        Iterator<DataObject> it = dm.find(sql, tuple.getParameter()).iterator();

        JSONArray ja = new JSONArray();

        dob = null;
        json = null;

        while (it.hasNext()) {
            dob = it.next();
            json = new JSONObject();
            json.put("id", dob.getString("SID"));
            json.put("text", dob.getString("MECHANIC_NAME"));

            ja.put(json);
        }
        response.getWriter().write(ja.toString());

    }
}
