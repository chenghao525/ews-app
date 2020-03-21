package com.corp.provider;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;

public class ProviderDelAction implements BusinessAction {

	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {
		ProviderForm cform = (ProviderForm) form;
		// ±£Áôµ±Ç°Ò³Âë
		cform.getPagination().setCurrent(cform.getCurrent());

		String sid = cform.getSid();
		ProviderTools pt = new ProviderTools();
		pt.removeProvider(sid, dm);
	}

}
