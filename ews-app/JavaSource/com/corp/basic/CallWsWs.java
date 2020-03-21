package com.corp.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class CallWsWs implements AjaxInterface {

	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "��������");
			return;
		}
		JSONObject json = new JSONObject(key);

		String code = json.getString("CODE");
		JSONObject jPara = json.getJSONObject("PARAMS");
		if (!jPara.has("TOKEN")) {
			jPara.put("TOKEN", Constant.getCommonToken());
		}

		// �����true��ֱ�ӷ���ws�е�details��ֵ����
		boolean bDtl = (json.has("DETAILS") && "yes".equals(json.getString("DETAILS")));

		String empSid = G.getUserIdFromSession(request);
		String re = G.callWebService(code, jPara.toString(), empSid);
		if (bDtl) {// �����true��ֱ�ӷ���ws�е�details��ֵ����
			json = new JSONObject(re);
			re = "";
			if (json.has("details")) {
				re = json.get("details").toString();
			}
		}

		// System.out.println("callwsws=====" + re);

		response.getWriter().write(re);
	}
}
