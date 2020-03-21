package com.corp.servlet.common;


import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

import java.util.Iterator;

public class CommonUtils {

    private DataObject globalDob = null;

    public String listStatusByInfoType(String infoType, String sid, DataManager dm) throws Exception {
        if (globalDob != null) {
            String content = globalDob.getString(sid);
            if (!G.isNvl(content)) {
                return content;
            }
        }

        String sql = "select sid, content from t_list_status";
        String itype = null;
        if (infoType.indexOf(",") >= 0) {
            sql += " where infotype in ('" + infoType.replaceAll(",", "','") + "')";
        } else {
            sql += " where infotype=?";
            itype = infoType;
        }
        Iterator<DataObject> it = dm.find(sql, itype).iterator();

        DataObject dob = null;
        DataObject mapDob = new DataObject();
        while (it.hasNext()) {
            dob = it.next();
            mapDob.setString(dob.getString("SID"), dob.getString("CONTENT"));
        }
        globalDob = mapDob;

        return mapDob.getString(sid);
    }

}
