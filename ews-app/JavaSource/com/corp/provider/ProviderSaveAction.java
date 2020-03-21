package com.corp.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.basic.Constant;
import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.exception.GeneralException;
import com.corp.oa.util.G;
import com.corp.oa.util.ImageUtils;

public class ProviderSaveAction implements BusinessAction {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void doExecute(BusinessForm form, DataManager dm) throws Exception {
		ProviderForm cform = (ProviderForm) form;

		// 保留当前页码
		cform.getPagination().setCurrent(cform.getCurrent());

		ProviderTools tool = new ProviderTools();
		DataObject dob = cform.getT_INFO_PROVIDER();
		String sid = "";// , provSid = "";
		String empSid = cform.getUserView().getUserId();

		String carList = dob.getString("BRAND_ID_LIST");
		String carListEx = dob.getString("BRAND_ID_LIST_EX");
		String authBr = dob.getString("AUTH_BRAND");
		dob.setString("BRAND_ID_LIST", "");
		dob.setString("BRAND_ID_LIST_EX", "");
		dob.setString("AUTH_BRAND", "");
		dob.setString("UPTUSER", empSid);
		dob.setString("UPTDATE", G.getCurrentTime());

		// provSid = dob.getString("SID");

		// 此字段中保存的是原来的匹配车型的情况（yes/no）
		// String orgMatchAll = tool.getProvMatchCarsString(provSid, dm);
		String curMatchAll = dob.getString("MATCH_ALL");

		if (!"yes".equals(curMatchAll)) {// 不是服务于所有品牌
			dob.setString("MATCH_ALL", "no"); // 不是匹配所有车型
		}

		if (!"yes".equals(dob.getString("TB_BY_PDU"))) {// 不提供免费保养产品
			dob.setString("TB_BY_PDU", "no");
		}

		// 在此保存商户信息
		sid = tool.addOrEditCustomInfo(dob, dm);// 返回prov sid
		if (sid == null) {
			dm.setLocalConnRollback();
			throw new GeneralException("服务商编号[" + dob.getString("PROV_ID") + "]已存在，请重新输入");
		}

		try {

			// 保存商户的服务属性
			String attrList = cform.getAttrList();
			tool.saveProvAttrList(sid, attrList, dm);

			// 保存商户的维保项目列表
			String maList = cform.getMaList();
			tool.saveProvMaList(sid, maList, dm);

			// 删除原有的映射服务品牌
			tool.removeProvBrandMap(sid, dm);
			if ("yes".equals(curMatchAll)) {// 服务于所有品牌
				tool.insertProvAllCarsMap(sid, empSid, dm);
			} else {// 服务于部分品牌
				tool.insertProvBrandMap(sid, carList, empSid, dm);
			}

			// 排除的车型
			tool.insertProvBrandMapEx(sid, carListEx, empSid, dm);

			// 授权服务品牌
			tool.insertProvBrandAuthMap(sid, authBr, empSid, dm);

			// 必须commit，否则下面的匹配配件会出现问题
			// dm.setLocalConnCommit();

			// // 插入与配件的对应关系
			// CarTreeUtils ctu = new CarTreeUtils();
			// String name = dob.getString("PROV_NAME_SUM");
			// if (G.isNvl(name))
			// name = dob.getString("PROV_NAME");

			// if ("yes".equals(curMatchAll)) {// 现在服务于所有品牌
			// if (!"yes".equals(orgMatchAll)) {// 原来不是服务于所有品牌
			// ctu.buildProv2BomMap_AllCars(sid, name, empSid, dm);
			// }
			// } else {
			// ctu.buildProv2BomMap(sid, name, empSid, dm);
			// }

			String path = cform.getPhotoPath();
			String pathOld = cform.getPhotoPathOld();
			if (!G.isNvl(path) && !path.equalsIgnoreCase(pathOld)) {
				// 两个不相等，说明图片更改了

				// 去掉前面的http
				path = path.replaceAll(Constant.getBaseUrl(), "");

				// 将商户的主图片都生成一张小图，共app端使用 app使用的图片都放到bu项目中
				ImageUtils mypic = new ImageUtils();
				String root = Constant.getBuWebFilePath();
				mypic.compressPic(root + path, root + Constant.APP_PIC_SMALL_DIR + path, 160, 160,
						false);
			} else {
				path = G.nvl(pathOld, "");
			}

			G.setImagePath(dm, sid, "provider", path);

			// dm.setLocalConnCommit();

		} catch (Exception e) {
			dm.setLocalConnRollback();
			logger.error(null, e);
		}
	}
}
