package com.corp.ews.ro;

import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;

import java.util.ArrayList;

public class ROSheetMetalSurfaceUtil {

    public String shfBaseSql() {

        return "select * from t_ews_ro_shf where 1=1";
    }
    public Tuple shfSql(String sql, String ewsRoCode, String roNumber,
                        String provSid, DataObject searchDob) {

        String ret = "";

        String shfId = searchDob.getString("S_SHF_ID");
        String roSid = searchDob.getString("S_RO_SID");

        ArrayList<String> pl = new ArrayList<String>();
        if (!G.isNvl(ewsRoCode)) {
            ret += " and ews_ro_code=?";
            pl.add(G.rqm(ewsRoCode));
        }
        if (!G.isNvl(roNumber)) {
            ret += " and ro_number=?";
            pl.add(G.rqm(roNumber));
        }
        if (!G.isNvl(provSid)) {
            ret += " and prov_sid=?";
            pl.add(G.rqm(provSid));
        }
        if (!G.isNvl(roSid)) {
            ret += " and ro_sid=?";
            pl.add(G.rqm(roSid));
        }
        if (!G.isNvl(shfId)) {
            ret += " and shf_id=?";
            pl.add(G.rqm(shfId));
        }

        sql = sql + ret;

        return new Tuple(sql, pl);
    }
}
