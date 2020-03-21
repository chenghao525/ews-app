package com.corp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.oa.util.G;

public class AjaxService extends HttpServlet {

	private static final long serialVersionUID = 7596052507955435398L;

	public final Log logger = LogFactory.getLog(getClass());

	private static JSONObject jsMap = new JSONObject();
	private final static String PACKAGE_HEAD = "com.corp.";

	static {
		try {
			jsMap.put("CallWsWs", "basic.CallWsWs");
			jsMap.put("GetDicList", "oa.system.dictionary.GetDicList");
			jsMap.put("GetDicList4MU", "oa.system.dictionary.GetDicList4MU");
			jsMap.put("GetOrgTree", "oa.system.org.GetOrgTree");
			jsMap.put("CheckUserPass", "oa.system.user.CheckUserPass");
			jsMap.put("GetOrgUsers", "oa.system.org.GetOrgUsers");
			jsMap.put("EWSUserAction", "oa.system.user.EWSUserAction");
			jsMap.put("GetEmpAllList", "oa.system.user.GetEmpAllList");
			jsMap.put("ImSavePicture", "oa.share.image.ImSavePicture#保存图片");
			jsMap.put("ImGetPictures", "oa.share.image.ImGetPictures#查询图片");
			jsMap.put("ImDelPicture", "oa.share.image.ImDelPicture#删除图片");
			jsMap.put("ImSavePicturePrim", "oa.share.image.ImSavePicturePrim#保存主图片");
			jsMap.put("GetDicList4BC", "oa.system.dictionary.GetDicList4BC");
			jsMap.put("GetCommonList", "servlet.common.GetCommonList");
			jsMap.put("SaveCommonList", "servlet.common.SaveCommonList#调用通用保存接口");
			jsMap.put("RemoveCommonList", "servlet.common.RemoveCommonList#调用通用删除接口");
			jsMap.put("OrderListByCustomer4Mu", "custom.OrderListByCustomer4Mu");
			jsMap.put("GetRoleDicList", "oa.system.role.GetRoleDicList#查询系统角色列表");
			jsMap.put("GetRightIdList", "oa.system.right.GetRightIdList#查询角色的人员列表/人员的角色列表");
			jsMap.put("SetRightIdList", "oa.system.right.SetRightIdList#设置角色的人员列表/人员的角色列表");
			jsMap.put("SaveCityInfo", "city.SaveCityInfo#保存城市基本信息");
			jsMap.put("GetCityInfo", "city.GetCityInfo#查询城市基本信息");
			jsMap.put("GetROList", "ews.ro.GetROList");
			jsMap.put("ROPerfectAction", "ews.ro.ROPerfectAction#完善工单信息");
            jsMap.put("GetCarBrandList", "ews.ro.GetCarBrandList#获取车品牌");
            jsMap.put("GetMechanics", "ews.mechanic.GetMechanics#获取技工");
            jsMap.put("MechanicAdjustment", "ews.mechanic.MechanicAdjustment#技工新增或删除");
            jsMap.put("GetRODetails", "ews.ro.GetRODetails#工单详情");
            jsMap.put("ExportROList", "ews.ro.ExportROList#导出工单");
            jsMap.put("GetProviders", "ews.provider.GetProviders");
            jsMap.put("GetOriginalRoCodes", "ews.ro.GetOriginalRoCodes#获取原来的工单号");
            jsMap.put("LoginFuncNode", "sso.service.LoginFuncNode#U8C单点登录");
            jsMap.put("BungleTotalRpt", "ews.report.BungleTotalRpt");
            jsMap.put("AddRptRecord", "ews.report.AddRptRecord#添加月成本记录");
            jsMap.put("ImportMechanics", "ews.mechanic.ImportMechanics#导入技师");
			jsMap.put("ReportEfficiencyMonth", "ews.report.ReportEfficiencyMonth#人效报表月");
			jsMap.put("ReportEfficiencyYear", "ews.report.ReportEfficiencyYear#人效报表年");
			jsMap.put("ExecuteSql", "ews.ExecuteSql");
			jsMap.put("LoginControl", "newExtends.LoginControl#登录");
			jsMap.put("IsOwnRight", "newExtends.IsOwnRight");
		} catch (Exception e) {
		}

	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;application/json;charset=UTF-8");

		HttpSession session = request.getSession(true);
		if (session == null) {
			try {
				response = G.setRespOptFailed(response, "没有权限或者会议已过期，请重新登录");
			} catch (Exception ex) {
			}
			return;
		}

//		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "*");

		String methodName = request.getParameter("method");

		DataManager dm = null;

		String para = "";
		try {

			String[] cls = jsMap.getString(methodName).split("#");

			Class comCls = getClass().getClassLoader().loadClass(PACKAGE_HEAD + cls[0]);
			AjaxInterface aif = (AjaxInterface) comCls.newInstance();

			dm = DataManager.getInstance();

			if (cls.length > 1 && !G.isNvl(cls[1])) {// 保存日志
				para = G.nvl(request.getParameter("data"), "");
				if (para.length() > 2000) {
					para = para.substring(0, 2000);
				}
				G.insertLog(request, cls[1] + "#" + para, dm);
			}

			dm.setLocalConnAutoCommit(false);

			aif.doExecute(request, response, dm);

			try {
				if (dm != null)
					dm.setLocalConnCommit();
			} catch (Exception ex) {
				// logger.error(methodName + " ajax commit error:" +
				// ex.getMessage());
			}

			// Class[] argsClass = new Class[2];
			// argsClass[0] = HttpServletRequest.class;
			// argsClass[1] = HttpServletResponse.class;
			//
			// Class cls = this.getClass();
			// Method method = cls.getMethod(methodName, argsClass);
			//
			// Object[] args = new Object[2];
			// args[0] = request;
			// args[1] = response;

			// method.invoke(this, args);

		} catch (Exception e) {
			String err = "后台" + methodName + " ajax error:" + e.getMessage() + ",para:" + para;
			try {
				if (dm != null) {
					dm.setLocalConnRollback();
				}
				G.inserExceptionLog(err, dm);
			} catch (Exception ex) {
				logger.error(methodName + " ajax rollback error:" + ex.getMessage());
			}
			logger.error(err, e);
			JSONObject json = new JSONObject();
			json.put("code", -1);
			json.put("message", "运行时错误");
			json.put("stackTrace", "运行时错误");
			// response.reset();
			response.getWriter().write(json.toString());
		} finally {
			if (dm != null) {
				dm.close();
			}
		}
	}
}
