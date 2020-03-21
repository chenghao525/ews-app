package com.corp.oa.share;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class empRight {
	public empRight() {
	}

	@SuppressWarnings("unchecked")
	public String getEmpSid(String rightId, DataManager dm) throws Exception {

		DataObject db = null;
		String empMp = "";
		if (rightId == null || "".equals(rightId))
			rightId = "0";
		String sql = "select SID,MP from T_INFO_EMPLOYEE where F_EMPHASRIGHT(sid,'"
				+ rightId + "')=1 order by sid";
		Collection<DataObject> coll = dm.find(sql, null);
		Iterator<DataObject> it = coll.iterator();
		while (it.hasNext()) {
			db = (DataObject) it.next();
			if (db != null) {
				empMp += db.getString("SID") + "/" + db.getString("MP") + ",";
			}
		}

		if (empMp.length() > 0)
			empMp = empMp.substring(0, empMp.length() - 1);

		return empMp;
	}

	public static void main(String[] arg) throws Exception {
		DataManager dm = DataManager.getInstance();
		empRight right = new empRight();
		System.out.println(right.getEmpSid("20103002", dm));
	}
}
