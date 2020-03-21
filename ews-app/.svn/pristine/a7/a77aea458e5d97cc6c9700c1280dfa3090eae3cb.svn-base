package com.corp.ews;

import com.corp.frame.bean.JSONArray;
import com.corp.frame.bean.JSONObject;

/**
 * wqj
 */
public class EWSConstant {

    //返工状态
    public static String REPEAT_NORMAL      = "910"; //默认状态为正常态
    public static String REPEAT_COLOR       = "911"; //色差返工
    public static String REPEAT_QUALITY     = "912"; //质量返工

    // 人员级别
    public static String MANAGER            = "20"; // 管理人员

    //15个漆面
    public static String LEFT_REAR_BUMPER       = "left_rear_bumper";   //左后保险杠
    public static String RIGHT_REAR_BUMPER      = "right_rear_bumper";  //右后保险杠
    public static String LEFT_FRONT_BUMPER      = "left_front_bumper";  //左前保险杠
    public static String RIGHT_FRONT_BUMPER     = "right_front_bumper"; //右前保险杠
    public static String LEFT_FRONT_FENDER      = "left_front_fender";  //左前叶子板
    public static String RIGHT_FRONT_FENDER     = "right_front_fender"; //右前叶子板
    public static String LEFT_REAR_FENDER       = "left_rear_fender";   //左后叶子板
    public static String RIGHT_REAR_FENDER      = "right_rear_fender";  //右后叶子板
    public static String LEFT_FRONT_DOOR        = "left_front_door";    //左前门
    public static String RIGHT_FRONT_DOOR       = "right_front_door";   //右前门
    public static String LEFT_REAR_DOOR         = "left_rear_door";     //左后门
    public static String RIGHT_REAR_DOOR        = "right_rear_door";    //右后门
    public static String ROOF                   = "roof"; //车顶
    public static String TRUNK                  = "trunk"; //后备箱盖
    public static String HOOD                   = "hood"; //引擎盖

    //岗位 infotype=50
    public static String PAINT_MAKER            = "900"; //调漆技师
    public static String PAINTER                = "901"; //油漆技师
    public static String EM_MAN                 = "902"; //机电技师
    public static String SH_MAN                 = "903"; //钣金技师
    public static String BEAUTICIAN             = "904"; //美容技师


    // 供应链 func node 编码
    public static String DELIVERY_CODE          = "4004020201"; //维护订单
    public static String PURCHASE_CODE          = "40080602"; //采购入库
    public static String OTHER_IN               = "40080608"; //其他入库
    public static String OTHER_OUT              = "40080808"; //其他入库
    public static String TURN_HOUSE             = "40081002"; //转库
    public static String STOCK_ON_HAND          = "40083002"; //现存量

    /**
     * 漆面对应的名字
     * @param positionId
     * @return
     */
    public static String getSHFName(String positionId) {

        if (LEFT_REAR_BUMPER.equals(positionId)) {
            return "左后保险杠";
        } else if (RIGHT_REAR_BUMPER.equals(positionId)) {
            return "右后保险杠";
        } else if (LEFT_FRONT_BUMPER.equals(positionId)) {
            return "左前保险杠";
        } else if (RIGHT_FRONT_BUMPER.equals(positionId)) {
            return "右前保险杠";
        } else if (LEFT_FRONT_FENDER.equals(positionId)) {
            return "左前叶子板";
        } else if (RIGHT_FRONT_FENDER.equals(positionId)) {
            return "右前叶子板";
        } else if (LEFT_REAR_FENDER.equals(positionId)) {
            return "左后叶子板";
        } else if (RIGHT_REAR_FENDER.equals(positionId)) {
            return "右后叶子板";
        } else if (LEFT_FRONT_DOOR.equals(positionId)) {
            return "左前门";
        } else if (RIGHT_FRONT_DOOR.equals(positionId)) {
            return "右前门";
        } else if (LEFT_REAR_DOOR.equals(positionId)) {
            return "左后门";
        } else if (RIGHT_REAR_DOOR.equals(positionId)) {
            return "右后门";
        } else if (ROOF.equals(positionId)) {
            return "车顶";
        } else if (TRUNK.equals(positionId)) {
            return "后备箱盖";
        } else if (HOOD.equals(positionId)) {
            return "引擎盖";
        } else {
            return null;
        }
    }

    /**
     * 统一返回消息格式
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
