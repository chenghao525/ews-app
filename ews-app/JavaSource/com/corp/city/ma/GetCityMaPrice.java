package com.corp.city.ma;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;
import com.corp.servlet.AjaxInterface;

public class GetCityMaPrice implements AjaxInterface {

	@SuppressWarnings("unchecked")
	public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm)
			throws Exception {

		String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);

		if (G.isNvl(key)) {
			response = G.setRespOptFailed(response, "²ÎÊý´íÎó");
			return;
		}

		CityMaBO bo = new CityMaBO();
		DataObject dob = null;
		JSONObject json = null;
		JSONArray ja = new JSONArray();

		String ma = key;
		String sql = bo.getCityMaPriceList(G.getSearchFromRequest(request));

		Iterator<DataObject> it = dm.find(sql, ma).iterator();

		Iterator<DataObject> it2 = null;
		String city = "", name = "";
		String price = "", price2 = "", pricea = "", pricea2b = "", priceb = "", priceb2b = "", pricec = "", pricec2b = "", priced = "", priced2b = "", priceai = "", priceai2b = "";

		while (it.hasNext()) {
			dob = it.next();
			json = null;
			city = "";
			name = "";
			
			json = dob.toJson();
			price = dob.getString("man_hour_price");
			price2 = dob.getString("man_hour_price_2b");
			pricea = dob.getString("a_class_price");
			pricea2b = dob.getString("a_class_price_2b");
			priceb = dob.getString("b_class_price");
			priceb2b = dob.getString("b_class_price_2b");
			pricec = dob.getString("c_class_price");
			pricec2b = dob.getString("c_class_price_2b");
			priced = dob.getString("d_class_price");
			priced2b = dob.getString("d_class_price_2b");
			priceai = dob.getString("ai_class_price");
			priceai2b = dob.getString("ai_class_price_2b");
			sql = bo.getCityMaPriceListByMaPrice();
			it2 = dm.find(
					sql,
					new String[] { price, price2, pricea, pricea2b, priceb, priceb2b, pricec,
							pricec2b, priced, priced2b, priceai, priceai2b, ma }).iterator();
			while (it2.hasNext()) {
				dob = it2.next();
				city += dob.getString("CITY_SID") + ",";
				name += dob.getString("CITY_NAME") + ",";
			}
			if (!"".equals(city)) {
				city = city.substring(0, city.length() - 1);
			}
			if (!"".equals(name)) {
				name = name.substring(0, name.length() - 1);
			}
			json.put("CITY_SID", city);
			json.put("CITY_NAME", name);
			ja.put(json);
		}

		response.getWriter().write(ja.toString());
	}
}
