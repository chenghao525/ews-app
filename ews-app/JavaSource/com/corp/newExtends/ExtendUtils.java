package com.corp.newExtends;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.frame.core.util.StringUtil;
import com.corp.oa.util.G;

import java.util.Collection;
import java.util.Iterator;

public class ExtendUtils {

    public boolean isTryTimesOut(String empId, String ip, DataManager dm) throws Exception {
        boolean limit = true;
        DataObject dob = null;
        String min = "10"; // 10分钟之内
        String sql = "select * from t_info_login_error t where ((sysdate -"
                + " to_date(t.logintime, 'YYYY-MM-DD HH24:MI:SS')) * 24 * 3600) <=(" + min
                + " * 60)" + " and t.empid=? and t.ip=?";

        dob = dm.findByPrimaryKey(sql, new String[] { empId, ip });
        if (dob == null) {// 还没有信息
            limit = false;
        } else {// 判断是否大于3次了
            int times = dob.getInt("ERR_TIMES");
            limit = !(times < 3);
        }
        return limit;
    }

    public void initTryTimes(String empId, String ip, DataManager dm) throws Exception {

        DataObject dob = new DataObject();
        dob.setString("EMPID", empId);
        dob.setString("IP", ip);
        dob.setString("LOGINTIME", G.getCurrentTime());
        dob.setString("ERR_TIMES", "0");
        dob.setModelName("T_INFO_LOGIN_ERROR");
        dm.save(dob);

    }

    public JSONArray getMenuList(String useId, String lang, DataManager dm) throws Exception {

        // 获得用户所有的权限
        String rightlist = getEmpRightList(dm, useId);
        String sql = "select T.sid,T.menuname,MENUNAME_EN,MENUNAME_JP,T.IMAGENAME  from t_ews_func_menu" +
                " T where state=1 and T.PARENTSID=0 order by OPTORDER";
        Iterator<DataObject> it = dm.find(sql, null).iterator();

        JSONArray ja = new JSONArray();
        JSONArray subJa = null;
        JSONObject json = null;
        DataObject dob = null;
        while (it.hasNext()) {
            dob = it.next();
            subJa = getNodeMenus(dob.getString("sid"), dm,
                    rightlist, lang);
            json = getMenuJson(dob);
            json.remove("to");
            json.remove("rightId");
            if (subJa.length() > 0) {
                json.put("subs", subJa);
            }
            ja.put(json);
        }
        return ja;
    }
    public static String getEmpRightList(DataManager dm, String empsid)
            throws Exception {
        int iempsid = Integer.parseInt(empsid);
        String sql = "select  F_EMPHASRIGHTLIST(" + iempsid
                + ")as rightlist from t_right_rightdic";
        DataObject dob = (DataObject) dm.findByPrimaryKey(sql, null);
        if (dob == null)
            return "";
        else
            return dob.getString("rightlist");

    }
    private JSONArray getNodeMenus(String sid, DataManager dm, String rightlist,
                                String lang) throws Exception {
        String sql = "select T.menuname,MENUNAME_EN,MENUNAME_JP,t.POSTURL,T.IMAGENAME ,T.RIGHTID" +
                " from t_ews_func_menu T where state=1 and T.PARENTSID=? order by OPTORDER";
        Collection<DataObject> col = dm.find(sql, sid);
        Iterator<DataObject> it = col.iterator();
        DataObject dol = null;
        String menuName = "menuname";
        if ("zh_cn".equals(lang)) {
            menuName = "menuname";
        } else if ("en".equals(lang)) {
            menuName = "menuname_en";
        } else if ("ja".equals(lang)) {
            menuName = "menuname_jp";
        }
        JSONArray ja = new JSONArray();
        JSONObject json = null;
        while (it.hasNext()) {
            dol = it.next();
            json = getMenuJson(dol);
            ja.put(json);
        }

        return ja;

    }
    private JSONObject getMenuJson(DataObject dob) {
        JSONObject json = new JSONObject();
        json.put("name", dob.getString("MENUNAME"));
        json.put("icon", dob.getString("IMAGENAME"));
        json.put("rightId", dob.getString("RIGHTID"));
        json.put("to", dob.getString("POSTURL"));
        return json;
    }
    public static boolean isRightMenu(String EmpRightList, String menuRightId)
            throws Exception {
        if (EmpRightList == null)

            return false;
        if (menuRightId == null)
            return true;
        return (EmpRightList.indexOf(menuRightId) >= 0);

    }
}
