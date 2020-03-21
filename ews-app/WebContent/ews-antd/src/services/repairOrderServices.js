import {get, getCommonList} from "../utils/request";

export const getRepairOrders = async (params, options) => {
    options.repair = "yes";
    return await getCommonList("GetRepairOrders", params, options);
}

//获取工单详情的接口 色母详情
export const getROComponents = async (params, options) => {

    return await getCommonList("GetROComponents", params, options);
}
//钣面详情
export const getROSHFList = async (params, options) => {

    return await getCommonList("GetROSHFList", params, options);
}