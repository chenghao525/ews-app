package com.corp.ews.mechanic;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;

import java.util.ArrayList;

/**
 *@author  wqj
 */
public class MechanicUtils {
    /**
     * 新增一个技师
     * @param user
     * @param dob
     * @param dm
     * @return
     * @throws Exception
     */
    public String addMechanic(String user, DataObject dob, DataManager dm) throws Exception {

        String ctime = G.getCurrentTime();

        dob.setModelName("T_EWS_INFO_MECHANICS");
        dob.setString("SID", G.getSequence("SEQ_T_EWS_INFO_MECHANICS"));
        dob.setString("UPT_TIME", ctime);
        dob.setString("CRT_USER", user);
        dob.setString("UPT_USER", user);

        dm.insert(dob);

        return "保存成功";
    }

    /**
     * 删除技师
     * @param user
     * @param dob
     * @param dm
     * @return
     * @throws Exception
     */
    public String deleteMechanic(String user, DataObject dob, DataManager dm) throws Exception {

        String ctime = G.getCurrentTime();

        dob.setModelName("T_EWS_INFO_MECHANICS");
        dob.setString("STATE", "0");
        dob.setString("UPT_TIME", ctime);
        dob.setString("UPT_USER", user);

        dm.update(dob);

        return "删除成功";
    }
    public String editMechanic(String user, DataObject dob, DataManager dm) throws Exception {
        if (G.isNvl(dob.getString("SID"))) {
            return "数据不全";
        }
        String ctime = G.getCurrentTime();

        dob.setModelName("T_EWS_INFO_MECHANICS");
        dob.setString("UPT_TIME", ctime);
        dob.setString("UPT_USER", user);

        dm.update(dob);

        return "保存成功";
    }

    public String getBaseSWSql() {
        return "select ts.*, tp.prov_name_sum, tp.prov_name from t_ews_info_mechanics ts" +
                " left join t_info_provider tp on tp.sid=ts.prov_sid where ts.state='1'";
    }

    /**
     * 技师搜索sql
     * @param sql
     * @param searchDob
     * @return
     */
    public Tuple getMechanicsSql(String provSid, String sql, DataObject searchDob) {

        String ret = "";
        String sprovSid    = searchDob.getString("S_PROV_SID");
        String workNumber = searchDob.getString("S_WORK_NUMBER");
        String mechanicWork = searchDob.getString("S_MECHANIC_WORK");
        String mechanicName = searchDob.getString("S_MECHANIC_NAME");

        ArrayList pl = new ArrayList<String>();
        if (!G.isNvl(provSid)) {
            ret += " and prov_sid=?";
            pl.add(provSid);
        }
        if (!G.isNvl(sprovSid)) {
            ret += " and prov_sid=?";
            pl.add(sprovSid);
        }
        if (!G.isNvl(workNumber)) {
            ret += " and instr(work_number, ?) > 0";
            pl.add(workNumber);
        }
        if (!G.isNvl(mechanicWork)) {
            ret += " and instr(?, mechanic_work) > 0";
            pl.add(G.rqm(mechanicWork));
        }
        if (!G.isNvl(mechanicName)) {
            ret += " and instr(mechanic_name, ?) > 0";
            pl.add(G.rqm(mechanicName));
        }

        return new Tuple(sql + ret, pl);
    }



}
