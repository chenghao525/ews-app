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
		// ������ǰҳ��
		cform.getPagination().setCurrent(cform.getCurrent());

		String sid = cform.getSid();
		ProviderBO bo = new ProviderBO();
		String sql = bo.getProvInfoBySid();
		DataObject dob = dm.findByPrimaryKey(sql, sid);

		// �õ�������Ϊ��ЩƷ�Ƶĳ����ṩ����
		ProviderTools tool = new ProviderTools();
		String[] brand = tool.getProvBrandMap(dob.getString("SID"), dm);
		if (Constant.ALL_CAR_TYPE_MATCH.equals(brand[0])) {// ˵���Ƿ���������Ʒ��
			dob.setString("MATCH_ALL", "yes");
			dob.setString("BRAND_ID_LIST_NAME", "���г���");
		} else {
			dob.setString("MATCH_ALL", "no");
			dob.setString("BRAND_ID_LIST", brand[0]);
			dob.setString("BRAND_ID_LIST_NAME", brand[1]);
		}

		// �ų�����Ʒ��
		brand = tool.getProvBrandMapEx(dob.getString("SID"), dm);
		dob.setString("BRAND_ID_LIST_EX", brand[0]);
		dob.setString("BRAND_ID_LIST_EX_NAME", brand[1]);

		// // ������Ȩ����Ʒ�Ƶ���������
		brand = tool.getProvBrandAuthMap(dob.getString("SID"), dm);
		dob.setString("AUTH_BRAND", brand[0]);
		dob.setString("AUTH_BRAND_NAME", brand[1]);

		// �̻���ǩ���ݵ��滻
		dob.setString("LBL_LIST",
				G.nvl(dob.getString("LBL_LIST"), "").replaceAll(Constant.STRING_BREAK_NEW, " "));

		// �̻��ķ�����Ŀ
		cform.setMaList(tool.getProvMaList(sid, dm));

		// �̻��ķ�������
		cform.setAttrList(tool.getProvAttrList(sid, dm));

		
		cform.setT_INFO_PROVIDER(dob);

		// ��ȡͼƬ
		String photo = G.getImagePath(dm, sid, "provider");
		if (G.isNvl(photo))
			photo = "/images/yesauto.jpg";
		cform.setPhotoPath(photo);
		cform.setPhotoPathOld(photo);

	}

}
