package com.corp.oa.system.org;

import java.util.Collection;
import java.util.Iterator;

import com.corp.frame.action.BusinessAction;
import com.corp.frame.action.BusinessForm;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;

public class ShowInfoAction implements BusinessAction {
	// ���������ı���
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

		// ��һ�ν���,�趨Ϊ��ǰ�ĵ�¼��Ա
		// ���/�޸�/ɾ��������,�ñ����Ѿ�����������
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

	// ȡ�ø��ڵ���sid��������Ŀ¼�б��SQL
	private String getSelSql(String sid) {
		String sql = "";
		sql = "select  s.sid,s.DEPTID,s.DEPTNAME,s.INFO_CLASS from T_INFO_DEPT s ";
		sql += " where s.STATE='1' and info_class!=3 and s.PARENTDEPT=" + sid
				+ " order by DEPTRANK";

		return sql;

	}

	// ���ص�ǰ�ڵ��Ƿ��Ǹ�Ŀ¼�µ����һ��Ŀ¼��
	@SuppressWarnings("unchecked")
	private boolean isLastDir(String strSID, DataManager dm) throws Exception {
		boolean bLast = false;

		Iterator<DataObject> iter = null;

		iter = dm.find(getSelSql(getFloderParentID(strSID, dm)), null)
				.iterator();

		DataObject dob = null;

		while (iter.hasNext()) {
			if (bLast) { // ���������Trueֵ���ֽ���ѭ����˵���϶��������һ����ֱ�ӷ��أ����Ч��
				bLast = false;
				break;
			}
			dob = iter.next();
			bLast = strSID.equals(dob.getString("sid"));
		}

		return bLast;
	}

	// ȡ��ÿ���ڵ���ʾʱǰ���ͼƬ����ַ���
	@SuppressWarnings("unchecked")
	private String getDirAheadImg(String strSID, DataManager dm)
			throws Exception {
		String strOut = "", sql = "", strGif = "";

		// ȡ�øýڵ�����ṹ����������ǰ�ڵ�
		sql = "select sid,c.PARENTDEPT from T_INFO_DEPT C where state='1' and sid<>? Start With sid=?"
				+ " Connect By Prior C.PARENTDEPT=C.SID order by sid ";

		Iterator<DataObject> iterTmp = dm.find(sql,
				new String[] { strSID, strSID }).iterator();
		DataObject dob = null;
		while (iterTmp.hasNext()) {
			// �����ѭ�������γɸýڵ�ǰ���ͼƬ����ַ���
			dob = iterTmp.next();
			if (isLastDir(dob.getString("sid"), dm)) {
				// �Ǹò�ε����һ��Ŀ¼����ʾ�հ�ͼƬ
				strGif = "line_none.gif";
			} else {
				// ���ǣ���ʾ���ε�����
				strGif = "line.gif";
			}

			strOut += "<img src='/images/" + strGif
					+ "' WIDTH='16' HEIGHT='16' align='absmiddle'>";
		}
		return strOut;
	}

	// ȡ�ñ��ΪstrDirSID�Ľڵ����Ŀ¼����ַ�����������ڵݹ����
	private String getSubDirList(String strDirSID, DataManager dm)
			throws Exception {

		String strSID = "", strName = "", strEmpID = "";
		String strTemp = "", strOut = "";
		int iCls;

		Iterator<DataObject> iter = null;
		DataObject dob = null;

		// ȡ�øýڵ����Ŀ¼�б�
		Collection<DataObject> col = getDirList(strDirSID, dm);
		// int k = 0; // ������

		iter = col.iterator();

		while (iter.hasNext()) { // ��ʼѭ��
			// k++;

			dob = iter.next();
			// ��Ŀ¼��sid������
			strSID = dob.getString("sid");
			strName = dob.getString("DEPTNAME");
			iCls = dob.getInt("INFO_CLASS");
			strEmpID = dob.getString("DEPTID");

			// �γɿ�ʼ���µ�<DIV>
			idx++;
			strOut += "<div id='Dir_" + String.valueOf(idx)
					+ "_' noWrap style='display:block'>";

			// ȡ��ǰ��ͼƬ��ʽ
			strOut += getDirAheadImg(strSID, dm);

			idx++;
			strTemp = String.valueOf(idx);

			// ȡ�øýڵ��ͼƬ��ʾ��ʽ
			strOut += getDirKind(strSID, strName, iCls, strEmpID, strTemp, dm);

			// �����µ�<DIV>�����������µ�������Ŀ¼������һ��<DIV>�����룡����
			strOut += "<div id='Dir_" + idx + "_' style='display:block'>";

			// ���еݹ���ã���ʾ���е���Ŀ¼
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

		// ѡ�����ڵ�
		sql = "select sid,deptid,deptname from T_INFO_DEPT where state='1'";
		sql += " and PARENTDEPT=0 and INFO_CLASS=1 order by DEPTRANK";
		Iterator<DataObject> it = dm.find(sql, null).iterator();

		while (it.hasNext()) {
			DataObject dob = it.next();

			idx++;

			// ��λ������
			sName = dob.getString("deptname");

			// ��λ���ڲ����
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

			// ��ȡ�Ӳ����ַ���
			strOut += getSubDirList(sID, dm);

			strOut += "</div></div>";
		}

		return strOut;
	}

	// ��øý���µ���Ŀ¼����
	@SuppressWarnings("unchecked")
	private Collection<DataObject> getDirList(String sid, DataManager dm)
			throws Exception {
		String sql = "";

		Collection<DataObject> list;
		sql = getSelSql(sid);

		list = dm.find(sql, null);

		return list;

	}

	// ���������ʾ��ͬ��ʽ��Ŀ¼
	@SuppressWarnings("unchecked")
	private String getDirKind(String strid, String strname, int intNodeCls,
			String empSid, String stridx, DataManager dm) throws Exception {

		String strOut = "";
		String strGif = "", strImgCls = "", sTemp = "";
		Iterator<DataObject> iter = getDirList(strid, dm).iterator();

		sTemp = strid;

		// ��ʾ��ͬ��ͼƬ
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

		if (iter.hasNext()) { // ������Ŀ¼����ʾ+��ǰ��ͼƬ
			strOut += "<font onclick=\"turnit(Dir_" + stridx + "_,Tag_";
			strOut += stridx + "_)\" style='CURSOR: hand'>";
			strOut += "<img id='Tag_"
					+ stridx
					+ "_' src='/images/sub.gif' width='16' height='16' align='absmiddle'>";
			// ���뵥����˫���¼�
			strOut += "</font><img height='16' id='Img_" + stridx + "_' src='"
					+ strImgCls + "' width='16' align='absmiddle'>";

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=\"return turnit_leaf('" + strid + "',Img_"
					+ stridx + "_,this)\" ondblclick=\"leaf_dbl('";
			strOut += strid + "',Img_" + stridx + "_,this,Dir_" + stridx
					+ "_,Tag_";
			strOut += stridx + "_)\">" + strname + "</A><br>";

		} else { // û����Ŀ¼��ֱ����ʾ��ǵ�ǰ��ͼƬ
			if (isLastDir(sTemp, dm))
				strGif = "leaf_last.gif";
			else
				strGif = "leaf.gif";

			strOut += "<img id='Tag_" + stridx + "_' src='/images/" + strGif
					+ "' width='16' height='16' align='absmiddle'>";
			strOut += "<img height='16' id='Img_" + stridx + "_' src='"
					+ strImgCls + "' width='16' align='absmiddle'>";
			// ����û����Ŀ¼��ʱ��ֻ���뵥���¼���������˫���¼�

			strOut += "<A HREF='#' id='" + strid
					+ "' onclick=\"return turnit_leaf('" + strid + "',Img_"
					+ stridx + "_,this)\">" + strname + "</A><br>";

		}
		return strOut;
	}

	// ��ø�����ţ��ڲ�ʹ��
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
