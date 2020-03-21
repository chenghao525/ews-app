package com.corp.oa.share.empsel;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class DeptSelAction implements BusinessAction {
	public DeptSelAction() {
	}

	// 用来计数的变量
	private int idx = 0;

	private String dwOpen = "/images/dw_open.gif";

	private String dwClose = "/images/dw_close.gif";

	private String bmOpen = "/images/bm_open.gif";

	private String bmClose = "/images/bm_close.gif";

	private String ryImg = "/images/people.gif";

	private String unchkImg = "/images/uncheck.gif";

	private String chkImg = "/images/check.gif";

	private String empChkHead = "emp_chk_";

	public void doExecute(BusinessForm parm1, DataManager dm)
			throws java.lang.Exception {

		EmpSelForm sf = (EmpSelForm) parm1;
		// 获得用户内部SID
		// String userSID = sf.getUserView().getUserId();

		sf.setDwImg_open(dwOpen);
		sf.setDwImg_close(dwClose);
		sf.setBmImg_open(bmOpen);
		sf.setBmImg_close(bmClose);
		sf.setRyImg(ryImg);
		sf.setChkImg_chk(chkImg);
		sf.setChkImg_unchk(unchkImg);
		sf.setEmpChkHead(empChkHead);

		sf.setDivString(getDirString(dm));

	}

	// 取得父节点是sid的所有有目录列表的SQL
	private String getSelSql(String sid) {
		String sql = "";
		sql = "select  s.sid,s.DEPTID,s.DEPTNAME,s.INFO_CLASS from T_INFO_DEPT s ";
		sql += " where s.STATE='1' and s.INFO_CLASS=1 and s.PARENTDEPT=" + sid
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
			dob = (DataObject) iter.next();
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
		sql = "select sid,c.PARENTDEPT from T_INFO_DEPT C where state='1' and sid<>";
		sql += strSID + " Start With sid=" + strSID
				+ " Connect By Prior C.PARENTDEPT=C.SID order by sid ";

		Iterator<DataObject> iterTmp = dm.find(sql, null).iterator();
		DataObject dob = null;
		while (iterTmp.hasNext()) {
			// 在这个循环里面形成该节点前面的图片组成字符串
			dob = (DataObject) iterTmp.next();
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

			dob = (DataObject) iter.next();
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
			DataObject dob = (DataObject) it.next();

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
			strOut += "&nbsp;<font onclick=\"return turnit_leaf(this)\" ondblclick=\"leaf_dbl(this,Dir_"
					+ String.valueOf(idx)
					+ "_,Tag_"
					+ String.valueOf(idx)
					+ "_)\">" + sName + "</font><br>";

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
	private String getDirKind(String strid, String strname, int intNodeCls,
			String empSid, String stridx, DataManager dm) throws Exception {

		String strOut = "";
		String strGif = "", strImgCls = "", sTemp = "", sCheckImg = "", strMp = "";
		Iterator<DataObject> iter = getDirList(strid, dm).iterator();

		sTemp = strid;

		strImgCls = dwClose;

		// case 2:
		// strImgCls = bmClose;
		// break;
		/*
		 * case 3: strImgCls = ryImg; Iterator
		 * itEmp=dm.find("select f_getempname("
		 * +empSid+") as empname from dual",null).iterator();
		 * while(itEmp.hasNext()) { strid="emp_"+empSid+"_"; DataObject
		 * dob=(DataObject)itEmp.next(); strname=dob.getString("empname");
		 * //strMp=G.empty(dob.getString("MP"));
		 * //strMp="(手机："+strMp+")"; }
		 * sCheckImg="<img style='CURSOR: hand' height='16' id='";
		 * 
		 * sCheckImg+=empChkHead+ empSid +"' src='" + unchkImg; sCheckImg+=
		 * "' width='16' align='absmiddle' onclick=\"return turnit_leaf(this)\">"
		 * ; sCheckImg+="<INPUT TYPE='hidden' id='h_"+empChkHead+
		 * empSid+"' value='"; sCheckImg+=strname+"'>&nbsp;"; break;
		 */
		sCheckImg = "<img style='CURSOR: hand' height='16' id='";

		sCheckImg += empChkHead + strid + "' src='" + unchkImg;
		sCheckImg += "' width='16' align='absmiddle' onclick=\"return turnit_leaf(this)\">";
		sCheckImg += "<INPUT TYPE='hidden' id='h_" + empChkHead + strid
				+ "' value='";
		sCheckImg += strname + "'>&nbsp;";

		if (iter.hasNext()) { // 还有子目录，显示+的前导图片
			strOut += "<font onclick=\"turnit(Dir_" + stridx + "_,Tag_";
			strOut += stridx + "_)\" style='CURSOR: hand'>";
			strOut += "<img id='Tag_"
					+ stridx
					+ "_' src='/images/sub.gif' width='16' height='16' align='absmiddle'></font>";

			strOut += sCheckImg + "<img height='16' id='Img_" + stridx
					+ "_' src='" + strImgCls
					+ "' width='16' align='absmiddle'>";

			strOut += "&nbsp;<font onclick=\"return turnit_leaf(this)\" ondblclick=\"leaf_dbl(this,Dir_"
					+ stridx + "_,Tag_";
			strOut += stridx + "_)\">" + strname + strMp + "</font><br>";

		} else { // 没有子目录，直接显示弯角的前导图片
			if (isLastDir(sTemp, dm))
				strGif = "leaf_last.gif";
			else
				strGif = "leaf.gif";

			strOut += "<img id='Tag_" + stridx + "_' src='/images/" + strGif
					+ "' width='16' height='16' align='absmiddle'>";
			strOut += sCheckImg + "<img height='16' id='Img_" + stridx
					+ "_' src='" + strImgCls
					+ "' width='16' align='absmiddle'>";
			// 旗下没有子目录的时候，只加入单击事件，不存在双击事件

			strOut += "<font onclick=\"return turnit_leaf(this)\">" + strname
					+ strMp + "</font><br>";

		}
		return strOut;
	}

	// 获得父结点编号，内部使用
	@SuppressWarnings("unchecked")
	private String getFloderParentID(String sid, DataManager dm) {

		String result = "";

		String sql = "select PARENTDEPT from T_INFO_DEPT  where sid=" + sid;

		try {
			Collection<DataObject> list = dm.find(sql, null);
			Iterator<DataObject> iter = list.iterator();
			while (iter.hasNext()) {
				DataObject dob = (DataObject) iter.next();
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
