package com.corp.provider;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class ProviderListAction implements BusinessAction {
	@SuppressWarnings("unchecked")
	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {

		ProviderForm cform = (ProviderForm) form;

		int cur = cform.getCurrent();

		cform.getPagination().setCurrent(cur);

		ProviderBO bo = new ProviderBO();
		String sql = bo.getProvList();
		cform.getQueryView().setSelect(sql);
		cform.getQueryView().setExtension("order by uptdate desc");

		sql = cform.getQueryView().getSql();
		cform.setProvLst(dm.find(sql, null, false, null, cform.getPagination()));

		// 已改为Oracle的job执行
		// if (cur == 0) {// 修复映射关系
		// Thread thread = new Thread(new RepairProv());
		// thread.start();
		// }
	}

	class RepairProv implements Runnable {
		@Override
		public void run() {
			DataManager dm = null;
			try {
				dm = DataManager.getInstance();
				ProviderTools pt = new ProviderTools();
				pt.repairProvCarsMaping(dm);
			} catch (Exception e) {

			} finally {
				if (dm != null) {
					dm.close();
				}
			}
		}
	}

}
