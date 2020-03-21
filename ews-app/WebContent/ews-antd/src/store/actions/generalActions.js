import {getMechanics, getMechanicWork, getProviders,getMechanicSex,getMechanicGrade
    ,getDicList4MU, yyDecreaseStock, reportEfficiencyMonth} from "../../services/generalServices";

export const MENU_LIST  = "MENU_LIST";
export const MECHANICS  = "MECHANICS";
export const VENDORS    = "VENDORS";
export const DIC_LIST   = "DIC_LIST";
export const YYSTOCK    = "YYSTOCK";
export const ADJUSTER   = "ADJUSTER";
export const PAINT      = "PAINT";
export const MONTHDATA  = "MONTHDATA";



export const menuListAction = (list) => {
    return {
        type: MENU_LIST,
        list
    }
};
//服务技师,不传参数
export const mechanicsAction = (mechanics) => {
    const rsp = getMechanics(mechanics);
    return {
        type: MECHANICS,
        payload: rsp
    }
};

//调漆员,传{"work":"900"}
export const adjusterAction = (mechanics) => {
    const rsp = getMechanics(mechanics);
    return {
        type: ADJUSTER,
        payload: rsp
    }
};

//油漆员,传{"work":"901"}
export const paintAction = (mechanics) => {
    const rsp = getMechanics(mechanics);
    return {
        type: PAINT,
        payload: rsp
    }
};

export const vendorsAction = (args) => {
    const rsp = getProviders(args);
    return {
        type: VENDORS,
        payload: rsp
    }
};

export const dicAction = (mechanics) => {
    const rsp = getDicList4MU(mechanics);
    return {
        type: DIC_LIST,
        payload: rsp
    }
};

export const yyStockAction = (args) => {
    const rsp = yyDecreaseStock(args);
    return {
        type: YYSTOCK,
        payload: rsp
    }
};




