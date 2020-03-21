package com.corp.ews.processLog;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;

import java.util.ArrayList;

public class PLogUtils {

    /**
     * 写入操作流水
     * @param ewsRoCode ews系统工单号
     * @param roNumber 门店工单号
     * @param optState 操作状态
     * @param provSid 商户id
     * @param memo 备注
     * @param optUser 操作人
     * @param dm
     * @throws Exception
     */
    public void addAProcess(String ewsRoCode,String roNumber, String optState, String provSid,
                            String memo, String optUser, DataManager dm) throws Exception {

        DataObject dob = new DataObject();
        dob.setModelName("T_EWS_RO_LOG");
        dob.setString("SID", G.getSequence("SEQ_T_EWS_RO_LOG"));
        dob.setString("EWS_RO_CODE", ewsRoCode);
        dob.setString("RO_NUMBER", roNumber);
        dob.setString("OPT_STATE", optState);
        dob.setString("PROV_SID", provSid);
        dob.setString("MEMO", memo);
        dob.setString("CRT_USER", optUser);

        dm.insert(dob);
    }

    public String pLogBaseSql() {
        String sql = "select * from t_ews_ro_log where state='1'";
        return sql;
    }

    public Tuple pLogSql(String sql, DataObject searchDob) {

        String ret = "";

        String crtTimeSt     = searchDob.getString("S_CRT_TIME_ST");
        String crtTimeEd     = searchDob.getString("S_CRT_TIME_ED");
        ArrayList<String> pl = new ArrayList<String>();
        if (!G.isNvl(crtTimeSt)) {
            ret += " and crt_time>=?";
            pl.add(G.rqm(crtTimeSt));
        }
        if (!G.isNvl(crtTimeEd)) {
            ret += " and crt_time<=?";
            pl.add(G.rqm(crtTimeEd));
        }



        return new Tuple(sql + ret, pl);
    }
}
