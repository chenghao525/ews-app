package com.corp.ews.ro;

import com.corp.ews.EWSConstant;
import com.corp.frame.bean.JSONObject;
import com.corp.frame.core.dao.DataManager;
import com.corp.frame.core.dao.DataObject;
import com.corp.oa.util.Excel;
import com.corp.oa.util.G;
import com.corp.oa.util.Tuple;
import com.corp.servlet.AjaxInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ExportROList implements AjaxInterface {

    protected final Log logger = LogFactory.getLog(getClass());
    RepairOrdersUtils roUtl = new RepairOrdersUtils();
    @Override
    public void doExecute(HttpServletRequest request, HttpServletResponse response, DataManager dm) throws Exception {

        if (!G.isOwnRight("13101202", request)) {
           response.getWriter().write("û��Ȩ�޻�˴λỰ��ʧЧ");
           return;
        }
        String key = request.getParameter(AJAX_PARAMETER_KEY_NAME);
        if (G.isNvl(key)) {
            response.getWriter().write("");
            return;
        }
        String fldList = request.getParameter("FLD_LIST");
        String[] fc = new String[] {"EWS������", "������", "���ƺ�", "ɫ��", "������ɫ", "ɫĸ����(g)", "����Ʒ��", "������", "����Ա", "��ʦ", "����״̬", "��ɫʱ��"};
        String[] fe = new String[] {"EWS_RO_CODE", "RO_NUMBER", "VEHICLE_LICENSE", "COLOR_CODE", "COLOR_NAME", "WEIGHT_TOTAL", "CAR_BRAND",
                "SHEET_METAL_COUNT", "PAINT_MAKER", "MECHANIC_NAME", "REWORK_TYPE_NAME", "MIXED_DATE"};

        String names = "", fields = "";
        fldList = G.commaBE(fldList);
        for (int i = 0; i < fe.length; i++) {
            if (fldList.indexOf(G.commaBE(fe[i])) >= 0) {// �������ֶ�
                names += fc[i] + ",";
                fields += fe[i] + ",";
            }
        }
        if (G.isNvl(names)) {
            logger.info("[RO����]��δ�ҵ��κ���Ҫ������ֵ");
            response.getWriter().write("�Ҳ���ƥ���ֶ�");
            return;
        }
        JSONObject json = new JSONObject(key);
        String provSid = G.getEWSProvSidFromSession(request);
        CarUtil cUtl = new CarUtil();

        Tuple tuple = roUtl.getROListSql(roUtl.getROSql(), provSid, new DataObject(json));
        String sql = "select tero.*, f_getmapcontent(tero.rework_type) as rework_type_name from (" + tuple.getFirst() + ") tero";
        Iterator<DataObject> it = dm.find(sql, tuple.getParameter()).iterator();

        Collection<DataObject> col = new ArrayList<DataObject>();
        DataObject dob = null;
        DataObject carDob = null;

        String brandId = null;
        String mixedDate = null;
        String[] mixedDtArray = null;

        // ��ӵĶ�̬�����ֶ�
        ArrayList<String> fieldList = new ArrayList<String>();
        ArrayList<String> nameList = new ArrayList<String>();

        while (it.hasNext()) {
            dob = it.next();
            brandId = dob.getString("CAR_BRAND");
            mixedDate = dob.getString("MIXED_DATE");
            // ���ҳ�����Ϣ
            carDob = cUtl.getCarBrandById(brandId, dm);
            if (carDob != null) {
                dob.setString("CAR_BRAND", carDob.getString("NODE_TEXT"));
            }
            // ����ɫʱ����зָ�
            if (!G.isNvl(mixedDate)) {
                mixedDtArray = mixedDate.split(" ");
                dob.setString("MIXED_DATE", mixedDtArray[0]);
            }
            // ��������
            sheetMetalSurfaceDeal(dob, fieldList, nameList, dm);

            col.add(dob);
        }
        // ���䵼���ĵ����ֶ�
        for (int i= 0; i < fieldList.size(); i++) {
            names  += nameList.get(i) + ",";
            fields += fieldList.get(i) + ",";
        }

        Excel excel = new Excel();

        excel.setTitle(names);
        excel.setField(fields);

        excel.setMaskField(null);
        excel.setMaskLength(0);

        excel.makeExcel(col);
        excel.pushFileToDownload(response);
    }

    // �����������
    private void sheetMetalSurfaceDeal(DataObject dob, ArrayList<String> fieldList, ArrayList<String> nameList,
                                       DataManager dm) throws Exception {

        Iterator<DataObject> shfIt = roUtl.roSheetMetalSurfaceByRoSid(dob.getString("SID"), dm);

        DataObject shfDob = null;
        String shfId = null;
        String shfName = null;
        String shfNumber = null;
        String shfDescription = null;

        String field = null;
        String name = null;
        String field1 = null;
        String name1 = null;

        int idx = 0;
        while (shfIt.hasNext()) {
            shfDob = shfIt.next();

            shfId           = shfDob.getString("SHF_ID");
            shfName         = shfDob.getString("SHF_NAME");
            shfNumber       = shfDob.getString("SHF_NUMBER");
            shfDescription  = shfDob.getString("SHF_DESCRIPTION");
            idx = getLocationByShfId(shfId);
            if (idx == 0) {
                logger.info("[����]:idδ�ҵ�");
                continue;
            }
            field = "SHF_NAME_" + idx;
            name  = "����" + idx;
            field1 = "SHF_COUNT_" + idx;
            name1  = "������" + idx;
            // ��̬��ϵ����ֶ�
            if (!fieldList.contains(field)) {

                fieldList.add(field);
                nameList.add(name);

                fieldList.add(field1);
                nameList.add(name1);
            }
            // �Զ��������
            if (idx > 15) {
                dob.setString(field, shfDescription);
            } else {
                dob.setString(field, shfName);
            }
            dob.setString(field1, shfNumber);
        }
    }
    // ��������Ӧλ��
    private int getLocationByShfId(String shfId) {

        if (EWSConstant.LEFT_FRONT_BUMPER.equals(shfId)) {
            return 1;
        } else if (EWSConstant.LEFT_FRONT_DOOR.equals(shfId)) {
            return 2;
        } else if (EWSConstant.LEFT_REAR_DOOR.equals(shfId)) {
            return 3;
        } else if (EWSConstant.LEFT_FRONT_FENDER.equals(shfId)) {
            return 4;
        } else if (EWSConstant.LEFT_REAR_BUMPER.equals(shfId)) {
            return 5;
        } else if (EWSConstant.LEFT_REAR_FENDER.equals(shfId)) {
            return 6;
        } else if (EWSConstant.RIGHT_FRONT_BUMPER.equals(shfId)) {
            return 7;
        } else if (EWSConstant.RIGHT_REAR_BUMPER.equals(shfId)) {
            return 8;
        } else if (EWSConstant.RIGHT_FRONT_DOOR.equals(shfId)) {
            return 9;
        } else if (EWSConstant.RIGHT_REAR_DOOR.equals(shfId)) {
            return 10;
        } else if (EWSConstant.RIGHT_FRONT_FENDER.equals(shfId)) {
            return 11;
        } else if (EWSConstant.RIGHT_REAR_FENDER.equals(shfId)) {
            return 12;
        } else if (EWSConstant.HOOD.equals(shfId)) {
            return 13;
        } else if (EWSConstant.TRUNK.equals(shfId)) {
            return 14;
        } else if (EWSConstant.ROOF.equals(shfId)) {
            return 15;
        } else if (shfId.indexOf("custom") >= 0) {
            String idxStr = shfId.substring("custom".length(), shfId.length());
            int idx = Integer.parseInt(idxStr);
            return 15 + idx;
        } else {
            return 0;
        }
    }
}
