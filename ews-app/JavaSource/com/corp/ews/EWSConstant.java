package com.corp.ews;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

/**
 * wqj
 */
public class EWSConstant {

    //����״̬
    public static String REPEAT_NORMAL      = "910"; //Ĭ��״̬Ϊ����̬
    public static String REPEAT_COLOR       = "911"; //ɫ���
    public static String REPEAT_QUALITY     = "912"; //��������

    // ��Ա����
    public static String MANAGER            = "20"; // ������Ա

    //15������
    public static String LEFT_REAR_BUMPER       = "left_rear_bumper";   //����ո�
    public static String RIGHT_REAR_BUMPER      = "right_rear_bumper";  //�Һ��ո�
    public static String LEFT_FRONT_BUMPER      = "left_front_bumper";  //��ǰ���ո�
    public static String RIGHT_FRONT_BUMPER     = "right_front_bumper"; //��ǰ���ո�
    public static String LEFT_FRONT_FENDER      = "left_front_fender";  //��ǰҶ�Ӱ�
    public static String RIGHT_FRONT_FENDER     = "right_front_fender"; //��ǰҶ�Ӱ�
    public static String LEFT_REAR_FENDER       = "left_rear_fender";   //���Ҷ�Ӱ�
    public static String RIGHT_REAR_FENDER      = "right_rear_fender";  //�Һ�Ҷ�Ӱ�
    public static String LEFT_FRONT_DOOR        = "left_front_door";    //��ǰ��
    public static String RIGHT_FRONT_DOOR       = "right_front_door";   //��ǰ��
    public static String LEFT_REAR_DOOR         = "left_rear_door";     //�����
    public static String RIGHT_REAR_DOOR        = "right_rear_door";    //�Һ���
    public static String ROOF                   = "roof"; //����
    public static String TRUNK                  = "trunk"; //�����
    public static String HOOD                   = "hood"; //�����

    //��λ infotype=50
    public static String PAINT_MAKER            = "900"; //���Ἴʦ
    public static String PAINTER                = "901"; //���Ἴʦ
    public static String EM_MAN                 = "902"; //���缼ʦ
    public static String SH_MAN                 = "903"; //�ӽ�ʦ
    public static String BEAUTICIAN             = "904"; //���ݼ�ʦ


    // ��Ӧ�� func node ����
    public static String DELIVERY_CODE          = "4004020201"; //ά������
    public static String PURCHASE_CODE          = "40080602"; //�ɹ����
    public static String OTHER_IN               = "40080608"; //�������
    public static String OTHER_OUT              = "40080808"; //�������
    public static String TURN_HOUSE             = "40081002"; //ת��
    public static String STOCK_ON_HAND          = "40083002"; //�ִ���

    /**
     * �����Ӧ������
     * @param positionId
     * @return
     */
    public static String getSHFName(String positionId) {

        if (LEFT_REAR_BUMPER.equals(positionId)) {
            return "����ո�";
        } else if (RIGHT_REAR_BUMPER.equals(positionId)) {
            return "�Һ��ո�";
        } else if (LEFT_FRONT_BUMPER.equals(positionId)) {
            return "��ǰ���ո�";
        } else if (RIGHT_FRONT_BUMPER.equals(positionId)) {
            return "��ǰ���ո�";
        } else if (LEFT_FRONT_FENDER.equals(positionId)) {
            return "��ǰҶ�Ӱ�";
        } else if (RIGHT_FRONT_FENDER.equals(positionId)) {
            return "��ǰҶ�Ӱ�";
        } else if (LEFT_REAR_FENDER.equals(positionId)) {
            return "���Ҷ�Ӱ�";
        } else if (RIGHT_REAR_FENDER.equals(positionId)) {
            return "�Һ�Ҷ�Ӱ�";
        } else if (LEFT_FRONT_DOOR.equals(positionId)) {
            return "��ǰ��";
        } else if (RIGHT_FRONT_DOOR.equals(positionId)) {
            return "��ǰ��";
        } else if (LEFT_REAR_DOOR.equals(positionId)) {
            return "�����";
        } else if (RIGHT_REAR_DOOR.equals(positionId)) {
            return "�Һ���";
        } else if (ROOF.equals(positionId)) {
            return "����";
        } else if (TRUNK.equals(positionId)) {
            return "�����";
        } else if (HOOD.equals(positionId)) {
            return "�����";
        } else {
            return null;
        }
    }

    /**
     * ͳһ������Ϣ��ʽ
     * @param code
     * @param message
     * @param json
     * @return
     */
    public static String returnMessage(int code, String message, JSONObject json) {

        JSONObject result = baseMessage(code, message);
        if (json != null) {
            result.put("details", json);
        }
        return result.toString();
    }
    public static String returnMessage(int code, String message, JSONArray ja) {

        JSONObject result = baseMessage(code, message);
        if (ja != null) {
            result.put("details", ja);
        }
        return result.toString();
    }

    public static String returnMessage(int code, String message) {
        return baseMessage(code, message).toString();
    }

    private static JSONObject baseMessage(int code, String message) {
        JSONObject result = new JSONObject();
        result.put("code", code + "");
        result.put("message", message);

        return result;
    }
}
