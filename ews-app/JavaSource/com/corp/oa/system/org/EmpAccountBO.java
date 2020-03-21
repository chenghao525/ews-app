package com.corp.oa.system.org;

import com.corp.frame.core.exception.GeneralException;

public class EmpAccountBO {
	
	/**
	 * 获取员工最新的账户余额
	 * 
	 * @param orgAct
	 *            原始值
	 * @param actIn
	 *            充值
	 * @param actOut
	 *            销值
	 * @param actOther
	 *            其他金额
	 * @return
	 * @throws java.lang.Exception
	 */
	public String getEmpAccountValue(String orgAct, String actIn,
			String actOut, String actOther) throws java.lang.Exception {
		String re = "0";
		try {
			if (orgAct == null || "".equals(orgAct))
				orgAct = "0";
			if (actIn == null || "".equals(actIn))
				actIn = "0";
			if (actOut == null || "".equals(actOut))
				actOut = "0";
			if (actOther == null || "".equals(actOther))
				actOther = "0";

			float oa = Float.parseFloat(orgAct);
			float ai = Float.parseFloat(actIn);
			float ao = Float.parseFloat(actOut);
			float aot = Float.parseFloat(actOther);

			float r = oa + ai - ao + aot;

			re = String.valueOf(r);

		} catch (Exception e) {
			throw new GeneralException("解析出错。" + e.getMessage());
		}
		return re;
	}

	/**
	 * 获取指定业务员的账户交易清单
	 * 
	 * @param empsid
	 * @return
	 */
	public String getAccountList(String empsid) {
		String sql = "select t.* from t_account_list t where t.emp_sid='"
				+ empsid + "' order by t.sid desc";

		sql = "select * from (" + sql + ") where 1=1";
		return sql;
	}
}
