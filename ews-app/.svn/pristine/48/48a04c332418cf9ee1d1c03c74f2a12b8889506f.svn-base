package com.corp.ews.ro;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

import java.util.Iterator;

public class CarUtil {

    public String brandSql() {

        return "SELECT CAR_ID, NODE_TEXT, PY_HEAD FROM T_INFO_CAR_LIST_TREE_BU WHERE STATE='1' AND CAR_LEVEL=1";
    }

    public Iterator<DataObject> getAllCarBrand(DataManager dm) throws Exception {

        String sql = brandSql();

        return dm.find(sql, null).iterator();

    }
    public DataObject getCarBrandById(String carId, DataManager dm) throws Exception {
        if (G.isNvl(carId)) {
            return null;
        }
        String sql = brandSql() + " AND CAR_ID=?";

        return dm.findByPrimaryKey(sql, carId);
    }
}
