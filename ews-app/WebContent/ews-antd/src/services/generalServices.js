import { get, post } from "../utils/request";
import { isNvl } from "../utils/G";


export const isOwnRight = async (rightId) => {
    return await get("IsOwnRight", {RightId: rightId})
}
//技师
export const getMechanics = async (args) => {
    return await get("GetMechanics", args);
};

export const getProviders = async (args) => {
    return await get("GetProviders", args);
}

export const login = async (args) => {

    return await get("LoginControl", args);
}
//工单状态
export const getMechanicWork = async (args) => {
    return await get("GetDicList4MU", args);
}

export const getMechanicDelete = async (args) => {
    return await get("MechanicAdjustment", args);
}

export const getMechanicAdjustment = async (args) => {
    return await get("MechanicAdjustment", args);
}

export const getMechanicSex = async (args) => {
    return await get("GetDicList4MU", args);
}

export const getMechanicGrade =async (args) => {
    return await get("GetDicList4MU",args)
}
//工单管理待完善
export const getRoPerfectList =async (args) => {
    return await get("GetROList",args);
}
//新增车辆品牌接口 GetCarBrandList
export const GetCarBrandLists =async (args) => {
    return await get("GetCarBrandList",args);
}
//新增待完善详情请求原来的工单号
export const GetOriginalRoCodes = async (args) =>{
    return await get("GetOriginalRoCodes", args);
}
//待完善信息补充完整保存 ROPerfectAction
export const ROPerfectAction = async (args) =>{
    return await get("ROPerfectAction", args);
}

/**
 * 获取t_list_status中的数据
 * @param infoType 必传类型
 * @param sid
 * @returns {Promise<*|Promise<unknown>>}
 */
export const getDicList4MU = async (infoType, sid) => {
    if (isNvl(infoType)) {
        return new Promise((resolve, reject) => {});
    }
    const params = {
        infoType: infoType,
    }
    if (!isNvl(sid)) {
        params.sid = sid;
    }
    return await get("GetDicList4MU", params);
}

export const  yyDecreaseStock= async (args) => {
    const data = {
        CODE: "yyDecreaseStock",
        PARAMS: args
    };
    return await post("http://tester.wedrive.com.cn:51518/ews-ws/AjaxService?method=CallWsWs", data);
}

export const ExecuteSql = async (args) => {
    return await get("ExecuteSql", args);
}

export const reportEfficiencyMonth = async (args) => {
    return await get("ReportEfficiencyMonth", args);
}

export const reportEfficiencyYear = async (args) => {
    return await get("ReportEfficiencyYear", args);
}


