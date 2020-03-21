package com.corp.code;

import javax.servlet.jsp.PageContext;

import com.corp.frame.core.action.PrefixMaskClass;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class CodeMaskAction implements PrefixMaskClass {

	public String doExecute(String value, DataManager dm,
			PageContext pageContext) throws Exception {

		String re = value;

		if (re == null || "".equals(re))
			return re;

		int len = re.length();

		if (len < 4) {
		} else if (len >= 4 && len < 8) {
			re = G.setStringMask(re, 3);
		} else {
			re = G.setStringMask(re, 4);
		}

		return re;
	}

}
