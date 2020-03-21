import { getMechanicInfo } from "../../services/mechanicInfoServices";
import { getListCommonOptions } from "../../utils/G";
import {getMechanicGrade, getMechanicSex, getMechanicWork} from "../../services/mechanicInfoServices";
//import {MECHANIC_GRADE, MECHANIC_SEX, MECHANIC_WORK} from "./generalActions";

export const ADD_DATA = "ADD_DATA";
export const MECHANIC_WORK = "MECHANIC_WORK";
export const MECHANIC_SEX = "MECHANIC_SEX";
export const MECHANIC_GRADE="MECHANIC_GRADE"

export const getmechanicInfoList = (params, pagination, sorter) => {
    const options = getListCommonOptions(pagination, sorter);
    const response = getMechanicInfo(params, options);

    return {
        type: ADD_DATA,
        payload: response
    }
};

export const mechanicWorkAction = (args) => {
    const rsp = getMechanicWork(args);
    return {
        type: MECHANIC_WORK,
        payload: rsp
    }
}

export const mechanicSexAction = (args) => {
    const rsp = getMechanicSex(args);
    return {
        type: MECHANIC_SEX,
        payload: rsp
    }
}

export const mechanicGradeAction = (args) => {
    const rsp = getMechanicGrade(args);
    return {
        type: MECHANIC_GRADE,
        payload: rsp
    }
}
