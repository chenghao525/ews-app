package com.corp.provider;

import com.corp.basic.Constant;
import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.G;

/*
 * also for b_see
 */
public class ProviderEditAction implements BusinessAction {

	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {
		ProviderForm cform = (ProviderForm) form;
		// 保留当前页码
		cform.getPagination().setCurrent(cform.getCurrent());

		String sid = cform.getSid();
		ProviderBO bo = new ProviderBO();
		String sql = bo.getProvInfoBySid();
		DataObject dob = dm.findByPrimaryKey(sql, sid);

		// 得到服务商为哪些品牌的车型提供服务
		ProviderTools tool = new ProviderTools();
		String[] brand = tool.getProvBrandMap(dob.getString("SID"), dm);
		if (Constant.ALL_CAR_TYPE_MATCH.equals(brand[0])) {// 说明是服务于所有品牌
			dob.setString("MATCH_ALL", "yes");
			dob.setString("BRAND_ID_LIST_NAME", "所有车型");
		} else {
			dob.setString("MATCH_ALL", "no");
			dob.setString("BRAND_ID_LIST", brand[0]);
			dob.setString("BRAND_ID_LIST_NAME", brand[1]);
		}

		// 排除服务品牌
		brand = tool.getProvBrandMapEx(dob.getString("SID"), dm);
		dob.setString("BRAND_ID_LIST_EX", brand[0]);
		dob.setString("BRAND_ID_LIST_EX_NAME", brand[1]);

		// // 配置授权服务品牌的中文名称
		brand = tool.getProvBrandAuthMap(dob.getString("SID"), dm);
		dob.setString("AUTH_BRAND", brand[0]);
		dob.setString("AUTH_BRAND_NAME", brand[1]);

		// 商户标签内容的替换
		dob.setString("LBL_LIST",
				G.nvl(dob.getString("LBL_LIST"), "").replaceAll(Constant.STRING_BREAK_NEW, " "));

		// 商户的服务项目
		cform.setMaList(tool.getProvMaList(sid, dm));

		// 商户的服务属性
		cform.setAttrList(tool.getProvAttrList(sid, dm));

		
		cform.setT_INFO_PROVIDER(dob);

		// 获取图片
		String photo = G.getImagePath(dm, sid, "provider");
		if (G.isNvl(photo))
			photo = "/images/yesauto.jpg";
		cform.setPhotoPath(photo);
		cform.setPhotoPathOld(photo);

	}

}
