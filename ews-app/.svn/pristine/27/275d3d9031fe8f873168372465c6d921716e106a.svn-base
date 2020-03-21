package com.corp.ews.report;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddRptRecord implements AjaxInterface {

    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13801001", request)) {
            response.getWriter().write("会话已失效或无权限");
            return;
        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

        JSONObject json = new JSONObject(key);
        if (json == null) {
            response.getWriter().write(EWSConstant.returnMessage(1, "数据为空"));
            return;
        }

        String msg = addRecord(new DataObject(json), dm);
        if (msg == null) {
            response.getWriter().write(EWSConstant.returnMessage(2, "数据错误"));
        } else {
            response.getWriter().write(EWSConstant.returnMessage(0, "保存成功"));
        }

    }

    private String addRecord(DataObject dob, DataManager dm) throws Exception {

        if (dob == null) {
            return null;
        }
        dob.setModelName("T_EWS_RPT_COST_MONTHLY");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(dob.getString("RPT_MONTH") + "-01");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dt);
        cal1.set(Calendar.DAY_OF_MONTH, cal1.getActualMaximum(Calendar.DAY_OF_MONTH));

        dob.setString("RPT_DATE", df.format(cal1.getTime()));

        dm.insert(dob);

        return "ok";
    }
}
