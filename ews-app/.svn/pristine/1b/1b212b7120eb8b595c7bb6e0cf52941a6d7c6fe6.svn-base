package com.corp.oa.share;

import java.util.Iterator;

import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class TreeRow {
	// 用来计数的变量
	private int idx = 0;

	private String dw = "";

	private String bm = "";

	private String ryImg = "";

	private DataManager dm = null;

	private String ckbDisp = "000";

	private String unchkImg = "";

	private String chkHead = "_chk_";

	// 登录人员内部编号
	private String empSid;

	public TreeRow(String empSid, String dw, String bm, String ryImg,
			String chkImg, String ckb) {
		this.empSid = empSid;
		this.dw = dw;
		this.bm = bm;
		this.ryImg = ryImg;
		this.ckbDisp = ckb;
		this.unchkImg = chkImg;
	}

	public String getTreeRow(DataObject rowDob, DataManager sdm)
			throws Exception {
		String strRow = "";
		dm = sdm;

		// 取得该节点的内部编号
		String rowSid = rowDob.getString("SID");

		strRow = this.getDirAheadImg(rowSid);

		idx++;
		strRow += this.getDirKind(rowDob);

		return strRow;
	}

	// 返回当前节点是否是父目录下的最后一个目录；
	@SuppressWarnings("unchecked")
	private boolean isLastDir(String strSID) throws Exception {
		boolean bLast = false;

		Iterator<DataObject> iter = null;

		iter = dm.find("{call EMPTREEPACKAGES.pro_ParentDeptList(?,?,?)}",
				new String[] { getFloderParentID(strSID), this.empSid })
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
	private String getDirAheadImg(String strSID) throws Exception {
		String strOut = "", strGif = "";

		Iterator<DataObject> iterTmp = dm.find(
				"{call EMPTREEPACKAGES.pro_NodeTreeDeptList(?,?,?)}",
				new String[] { strSID, this.empSid }).iterator();
		DataObject dob = null;
		while (iterTmp.hasNext()) {
			// 在这个循环里面形成该节点前面的图片组成字符串
			dob = (DataObject) iterTmp.next();
			if (isLastDir(dob.getString("sid"))) {
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

	// 更具情况显示不同样式的目录
	@SuppressWarnings("unchecked")
	private String getDirKind(DataObject dob) throws Exception {
		// String strid, String strname, int intNodeCls,
		// String empSid, String stridx, DataManager dm

		String strOut = "";
		String strGif = "", strImgCls = "", sSID = "";
		String strid = "", strname = "", sRowSID = "", sCheckImg = "", sCheckImgTemp = "";

		String nodeCls = dob.getString("INFO_CLASS");

		sRowSID = dob.getString("SID");

		strid = sRowSID;
		sSID = sRowSID;

		strname = dob.getString("DEPTNAME");

		sCheckImgTemp = "<img style='CURSOR: hand' height='16' id='";
		sCheckImgTemp += chkHead + sSID + "' src='" + unchkImg;
		sCheckImgTemp += "' width='16' align='absmiddle' onclick=turnit_chk(this)>";
		sCheckImgTemp += "<INPUT TYPE='hidden' id='h_" + chkHead + sSID
				+ "' value='";

		// 显示不同的图片
		if ("1".equals(nodeCls)) {// 部门
			strImgCls = dw;
			if ("1".equals(ckbDisp.substring(0, 1))) {
				sCheckImg = sCheckImgTemp + strname + "'>";
			}
		} else if ("2".equals(nodeCls)) {// 职务
			strImgCls = bm;
			if ("1".equals(ckbDisp.substring(1, 2))) {
				sCheckImg = sCheckImgTemp + strname + "'>";
			}
		} else {// 人员
			strImgCls = ryImg;

			// 由于数据库结构原因，当INFO_CLASS=3的时候表示的是人员信息，dob.getString("DEPTID")取得是人员的内部编号
			sSID = dob.getString("DEPTID");
			Iterator<DataObject> itEmp = dm.find(
					"select f_getempname(" + sSID + ") as empname from dual",
					null).iterator();
			while (itEmp.hasNext()) {
				strid = "emp_" + sSID + "_";
				strname = ((DataObject) itEmp.next()).getString("empname");
			}
			if ("1".equals(ckbDisp.substring(2, 3))) {
				sCheckImg = sCheckImgTemp + strname + "'>";
			}
		}

		Iterator<DataObject> iter = dm.find(
				"{call EMPTREEPACKAGES.pro_ParentDeptList(?,?,?)}",
				new String[] { sRowSID, this.empSid }).iterator();

		if (iter.hasNext()) { // 还有子目录，显示+的前导图片
			strOut += "<font onclick=turnit(Dir_" + idx + "_,Tag_";
			strOut += idx + "_,'" + strid + "') style='CURSOR: hand'>";
			strOut += "<img id='Tag_"
					+ idx
					+ "_' src='/images/plus.gif' width='16' height='16' align='absmiddle'>";
			// 加入单击事件
			strOut += "</font>" + sCheckImg + "<img height='16' id='Img_" + idx
					+ "_' src='" + strImgCls
					+ "' width='16' align='absmiddle'>";

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=return turnit_leaf('" + strid + "',Img_" + idx
					+ "_,this) style='FONT-SIZE: 9pt;'>" + strname + "</A><br>";

		} else { // 没有子目录，直接显示弯角的前导图片
			if (isLastDir(sRowSID))
				strGif = "leaf_last.gif";
			else
				strGif = "leaf.gif";

			strOut += "<img id='Tag_" + idx + "_' src='/images/" + strGif
					+ "' width='16' height='16' align='absmiddle'>";
			strOut += sCheckImg + "<img height='16' id='Img_" + idx
					+ "_' src='" + strImgCls
					+ "' width='16' align='absmiddle'>";
			// 旗下没有子目录的时候，只加入单击事件

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=return turnit_leaf('" + strid + "',Img_" + idx
					+ "_,this) style='FONT-SIZE: 9pt;'>" + strname + "</A><br>";

		}
		strOut += "<div id='Dir_" + String.valueOf(idx)
				+ "_' noWrap style='display:none'></div>";
		idx++;
		return strOut;
	}

	// 获得父结点编号，内部使用
	private String getFloderParentID(String sid) throws Exception {

		String result = "";
		String sql = "select PARENTDEPT from T_INFO_DEPT  where sid=" + sid;
		result = dm.findByPrimaryKey(sql, null).getString("PARENTDEPT");
		return result;

	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCkbDisp() {
		return ckbDisp;
	}

	public void setCkbDisp(String ckbDisp) {
		this.ckbDisp = ckbDisp;
	}

}
