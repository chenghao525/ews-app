package com.corp.oa.system.org;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class ShowInfoAction implements BusinessAction {
	// 用来计数的变量
	private int idx = 0;

	private String dwOpen = "/images/dw_open.gif";

	private String dwClose = "/images/dw_close.gif";

	private String bmOpen = "/images/bm_open.gif";

	private String bmClose = "/images/bm_close.gif";

	private String ryImg = "/images/people.gif";

	public ShowInfoAction() {
	}

	@SuppressWarnings("unchecked")
	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		OrgForm of = (OrgForm) parm1;

		of.setDwImg_open(dwOpen);
		of.setDwImg_close(dwClose);
		of.setBmImg_open(bmOpen);
		of.setBmImg_close(bmClose);
		of.setRyImg(ryImg);

		of.setDivString(getDirString(dm));

		// 第一次进来,设定为当前的登录人员
		// 添加/修改/删除操作后,该变量已经设置了内容
		if (of.getTransInfo() == null) {
			String sql = "";
			sql = "select PARENTDEPT from T_INFO_DEPT where DEPTID=? and info_class=3";
			Iterator<DataObject> it = dm
					.find(sql, of.getUserView().getUserId()).iterator();
			if (it.hasNext()) {
				DataObject dob = it.next();
				of.setTransInfo(dob.getString("PARENTDEPT"));
			} else {
				of.setTransInfo("");
			}
		}

	}

	// 取得父节点是sid的所有有目录列表的SQL
	private String getSelSql(String sid) {
		String sql = "";
		sql = "select  s.sid,s.DEPTID,s.DEPTNAME,s.INFO_CLASS from T_INFO_DEPT s ";
		sql += " where s.STATE='1' and info_class!=3 and s.PARENTDEPT=" + sid
				+ " order by DEPTRANK";

		return sql;

	}

	// 返回当前节点是否是父目录下的最后一个目录；
	@SuppressWarnings("unchecked")
	private boolean isLastDir(String strSID, DataManager dm) throws Exception {
		boolean bLast = false;

		Iterator<DataObject> iter = null;

		iter = dm.find(getSelSql(getFloderParentID(strSID, dm)), null)
				.iterator();

		DataObject dob = null;

		while (iter.hasNext()) {
			if (bLast) { // 如果出现了True值，又进入循环，说明肯定不是最后一个，直接返回，提高效率
				bLast = false;
				break;
			}
			dob = iter.next();
			bLast = strSID.equals(dob.getString("sid"));
		}

		return bLast;
	}

	// 取得每个节点显示时前面的图片组成字符串
	@SuppressWarnings("unchecked")
	private String getDirAheadImg(String strSID, DataManager dm)
			throws Exception {
		String strOut = "", sql = "", strGif = "";

		// 取得该节点的树结构，不包括当前节点
		sql = "select sid,c.PARENTDEPT from T_INFO_DEPT C where state='1' and sid<>? Start With sid=?"
				+ " Connect By Prior C.PARENTDEPT=C.SID order by sid ";

		Iterator<DataObject> iterTmp = dm.find(sql,
				new String[] { strSID, strSID }).iterator();
		DataObject dob = null;
		while (iterTmp.hasNext()) {
			// 在这个循环里面形成该节点前面的图片组成字符串
			dob = iterTmp.next();
			if (isLastDir(dob.getString("sid"), dm)) {
				// 是该层次的最后一个目录，显示空白图片
				strGif = "line_none.gif";
			} else {
				// 不是，显示竖形的虚线
				strGif = "line.gif";
			}

			strOut += "<img src='/images/" + strGif
					+ "' WIDTH='16' HEIGHT='16' align='absmiddle'>";
		}
		return strOut;
	}

	// 取得编号为strDirSID的节点的子目录输出字符串，里面存在递归调用
	private String getSubDirList(String strDirSID, DataManager dm)
			throws Exception {

		String strSID = "", strName = "", strEmpID = "";
		String strTemp = "", strOut = "";
		int iCls;

		Iterator<DataObject> iter = null;
		DataObject dob = null;

		// 取得该节点的子目录列表
		Collection<DataObject> col = getDirList(strDirSID, dm);
		// int k = 0; // 计数器

		iter = col.iterator();

		while (iter.hasNext()) { // 开始循环
			// k++;

			dob = iter.next();
			// 子目录的sid和名称
			strSID = dob.getString("sid");
			strName = dob.getString("DEPTNAME");
			iCls = dob.getInt("INFO_CLASS");
			strEmpID = dob.getString("DEPTID");

			// 形成开始的新的<DIV>
			idx++;
			strOut += "<div id='Dir_" + String.valueOf(idx)
					+ "_' noWrap style='display:block'>";

			// 取得前导图片形式
			strOut += getDirAheadImg(strSID, dm);

			idx++;
			strTemp = String.valueOf(idx);

			// 取得该节点的图片显示形式
			strOut += getDirKind(strSID, strName, iCls, strEmpID, strTemp, dm);

			// 建立新的<DIV>，用来将旗下的所有子目录包括进一个<DIV>，必须！！！
			strOut += "<div id='Dir_" + idx + "_' style='display:block'>";

			// 进行递归调用，显示所有的子目录
			strOut += getSubDirList(strSID, dm);

			strOut += "</div>";

			strOut += "</div>";
		}

		return strOut;
	}

	@SuppressWarnings("unchecked")
	private String getDirString(DataManager dm) throws Exception {

		String strOut = "", sql = "";

		String sName, sID;

		// 选出根节点
		sql = "select sid,deptid,deptname from T_INFO_DEPT where state='1'";
		sql += " and PARENTDEPT=0 and INFO_CLASS=1 order by DEPTRANK";
		Iterator<DataObject> it = dm.find(sql, null).iterator();

		while (it.hasNext()) {
			DataObject dob = it.next();

			idx++;

			// 单位的名称
			sName = dob.getString("deptname");

			// 单位的内部编号
			sID = dob.getString("SID");

			strOut += "<div id='Dir_" + String.valueOf(idx)
					+ "_' noWrap style='display:block'>";

			idx++;
			strOut += "<font onclick=\"turnit(Dir_" + String.valueOf(idx)
					+ "_,Tag_" + String.valueOf(idx)
					+ "_)\" style='CURSOR: hand'>";
			strOut += "<img id='Tag_"
					+ String.valueOf(idx)
					+ "_' src='/images/sub.gif' width='16' height='16' align='absmiddle'>";
			strOut += "</font><img height='16' id='Img_" + String.valueOf(idx)
					+ "_' src='" + dwOpen + "' width='16' align='absmiddle'>";
			strOut += "&nbsp;<A HREF='#' id='" + sID
					+ "' onclick=\"return turnit_leaf('" + sID + "',Img_"
					+ String.valueOf(idx) + "_,this)\" ondblclick=\"leaf_dbl("
					+ sID + ",Img_" + String.valueOf(idx) + "_,this,Dir_"
					+ String.valueOf(idx) + "_,Tag_" + String.valueOf(idx)
					+ "_)\">" + sName + "</A><br>";

			strOut += "<div id='Dir_" + String.valueOf(idx)
					+ "_' style='display:block'>";

			// 获取子部门字符串
			strOut += getSubDirList(sID, dm);

			strOut += "</div></div>";
		}

		return strOut;
	}

	// 获得该结点下的子目录集合
	@SuppressWarnings("unchecked")
	private Collection<DataObject> getDirList(String sid, DataManager dm)
			throws Exception {
		String sql = "";

		Collection<DataObject> list;
		sql = getSelSql(sid);

		list = dm.find(sql, null);

		return list;

	}

	// 更具情况显示不同样式的目录
	@SuppressWarnings("unchecked")
	private String getDirKind(String strid, String strname, int intNodeCls,
			String empSid, String stridx, DataManager dm) throws Exception {

		String strOut = "";
		String strGif = "", strImgCls = "", sTemp = "";
		Iterator<DataObject> iter = getDirList(strid, dm).iterator();

		sTemp = strid;

		// 显示不同的图片
		switch (intNodeCls) {
		case 1:
			strImgCls = dwClose;
			break;
		case 2:
			strImgCls = bmClose;
			break;
		case 3:
			strImgCls = ryImg;
			Iterator<DataObject> itEmp = dm.find(
					"select f_getempname(?) as empname from dual", empSid)
					.iterator();
			while (itEmp.hasNext()) {
				strid = "emp_" + empSid + "_";
				strname = ((DataObject) itEmp.next()).getString("empname");
			}
			break;
		}

		if (iter.hasNext()) { // 还有子目录，显示+的前导图片
			strOut += "<font onclick=\"turnit(Dir_" + stridx + "_,Tag_";
			strOut += stridx + "_)\" style='CURSOR: hand'>";
			strOut += "<img id='Tag_"
					+ stridx
					+ "_' src='/images/sub.gif' width='16' height='16' align='absmiddle'>";
			// 加入单击和双击事件
			strOut += "</font><img height='16' id='Img_" + stridx + "_' src='"
					+ strImgCls + "' width='16' align='absmiddle'>";

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=\"return turnit_leaf('" + strid + "',Img_"
					+ stridx + "_,this)\" ondblclick=\"leaf_dbl('";
			strOut += strid + "',Img_" + stridx + "_,this,Dir_" + stridx
					+ "_,Tag_";
			strOut += stridx + "_)\">" + strname + "</A><br>";

		} else { // 没有子目录，直接显示弯角的前导图片
			if (isLastDir(sTemp, dm))
				strGif = "leaf_last.gif";
			else
				strGif = "leaf.gif";

			strOut += "<img id='Tag_" + stridx + "_' src='/images/" + strGif
					+ "' width='16' height='16' align='absmiddle'>";
			strOut += "<img height='16' id='Img_" + stridx + "_' src='"
					+ strImgCls + "' width='16' align='absmiddle'>";
			// 旗下没有子目录的时候，只加入单击事件，不存在双击事件

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=\"return turnit_leaf('" + strid + "',Img_"
					+ stridx + "_,this)\">" + strname + "</A><br>";

		}
		return strOut;
	}

	// 获得父结点编号，内部使用
	@SuppressWarnings("unchecked")
	private String getFloderParentID(String sid, DataManager dm) {

		String result = "";

		String sql = "select PARENTDEPT from T_INFO_DEPT  where sid=?";

		try {
			Collection<DataObject> list = dm.find(sql, sid);
			Iterator<DataObject> iter = list.iterator();
			while (iter.hasNext()) {
				DataObject dob = iter.next();
				result = dob.getString("PARENTDEPT");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

}
