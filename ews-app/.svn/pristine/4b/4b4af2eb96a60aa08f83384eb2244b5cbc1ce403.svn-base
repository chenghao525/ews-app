import { getRepairOrders, getROComponents, getROSHFList } from "../../services/repairOrderServices";
import { getListCommonOptions } from "../../utils/G";
import {MECHANIC_WORK} from "./generalActions";
import {getMechanicWork,getRoPerfectList,GetCarBrandLists,GetOriginalRoCodes,ROPerfectAction} from "../../services/generalServices";

export const ADD_DATA = "ADD_DATA";

export const getROLists = (params, pagination, sorter) => {
    const options = getListCommonOptions(pagination, sorter);
    const response = getRepairOrders(params, options);

    return {
        type: ADD_DATA,
        payload: response
    }
}

//新增工单详情接口调用 色母详情
export const DEL_SMDATA = "DEL_SMDATA";

export const getDELSMLists = (params, pagination, sorter) => {
    const options = getListCommonOptions(pagination, sorter);
    options.ewsRoCode = params.ewsRoCode;
    const response = getROComponents(params, options);

    return {
        type: DEL_SMDATA,
        payload: response
    }
}

//新增工单详情接口调用 钣面详情
export const DEL_BMDATA = "DEL_BMDATA";

export const getDELBMLists = (params, pagination, sorter) => {
    const options = getListCommonOptions(pagination, sorter);
    options.ewsRoCode = params.ewsRoCode;
    const response = getROSHFList(params, options);

    return {
        type: DEL_BMDATA,
        payload: response
    }
}
//待完善列表
export const RO_PERFECT = "RO_PERFECT";

export const getRoPerfectAction = (args) => {
    const rsp = getRoPerfectList(args);
    return {
        type: RO_PERFECT,
        payload: rsp
    }
}
//待完善详情车品牌 GetCarBrandLists
export const CAR_BRAND = "CAR_BRAND";

export const getCarBrandListsAction = (args) => {
    const rsp = GetCarBrandLists(args);
    return {
        type: CAR_BRAND,
        payload: rsp
    }
}
//待完善详情原来的工单号 GetOriginalRoCodes

export const ORG_ROCODES = "ORG_ROCODES";

export const getOriginaRoCodesAction = (args)=>{
    const rsp = GetOriginalRoCodes(args);
    return {
        type: ORG_ROCODES,
        payload: rsp
    }
}

//待完善信息补充完整保存 ROPerfectAction

export const RO_PFT = "RO_PFT";

export const getROPerfectAction = (args)=>{
    const rsp = ROPerfectAction(args);
    return {
        type: RO_PFT,
        payload: rsp
    }
}







