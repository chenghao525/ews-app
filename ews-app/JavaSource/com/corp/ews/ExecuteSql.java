package com.corp.ews;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class ExecuteSql implements AjaxInterface {
    protected final Log logger = LogFactory.getLog(getClass());
    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13808000", request)) {
            response.getWriter().write("没有权限或会话已失效");
            return;
        }

        String sql = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(sql)) {
            response.getWriter().write(EWSConstant.returnMessage(1, "sql 为空！！！"));
            return;
        }

        String tmp = sql.toUpperCase();
        if (tmp.indexOf("SELECT ") < 0 || tmp.indexOf("INSERT ") >= 0 ||
                tmp.indexOf("UPDATE ") >= 0 || tmp.indexOf("DELETE ") >=0 ||
                tmp.indexOf("ALTER ") >=0|| tmp.indexOf("DROP ") >=0||
                tmp.indexOf("CREATE ") >=0|| tmp.indexOf("TRUNCATE ") >=0) {
            response.getWriter().write(EWSConstant.returnMessage(3, "sql 错误！！！"));
            return;
        }

        logger.info("查询sql: " + sql);
        try {
            Iterator<DataObject> it = dm.find(sql, null).iterator();

            DataObject dob = null;
            JSONArray ja = new JSONArray();
            while (it.hasNext()) {
                dob = it.next();
                ja.put(dob.toJson());
            }
            response.getWriter().write(EWSConstant.returnMessage(0, "ok", ja));
        } catch (Exception e) {
            logger.error("错误", e);
            response.getWriter().write(EWSConstant.returnMessage(2, "错误: " + e.getMessage()));

        }



    }
}
