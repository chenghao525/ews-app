package com.corp.ews.provider;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

import java.util.Iterator;

public class ProviderUtil {

    public Iterator<DataObject> ewsProviders(DataManager dm) throws Exception {

        String sql = providerBaseSql();

        return dm.find(sql, null).iterator();
    }


    public String providerBaseSql() {
        return "select tip.*, tep.* from t_ews_provider_extension tep left join t_info_provider tip" +
                " on tip.sid=tep.prov_sid where tip.state='1'";
    }

    public DataObject getProviderByName(String provName, DataManager dm) throws Exception {

        String sql = "SELECT * FROM t_info_provider WHERE STATE='1'" +
                " AND INSTR(PROV_NAME, ?) > 0 OR INSTR(PROV_NAME_SUM, ?) > 0";

        return dm.findByPrimaryKey(sql, new String[] { provName, provName });
    }

}
