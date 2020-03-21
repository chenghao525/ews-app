import { MENU_LIST, MECHANICS, VENDORS, PAINT, DIC_LIST, ADJUSTER } from "../actions/generalActions";
import {MECHANIC_WORK,MECHANIC_SEX,MECHANIC_GRADE} from "../actions/mechanicInfoActions"
import { menuConfigs } from "../../configs/config";
import { isNvl } from "../../utils/G";

const defaulrState = {

    monthdata: {},
    pieSeries: [],
    cSeries: [],
    sumarry: {}
}


const base = (state = defaulrState, action) => {
    const { payload } = action;
    switch (action.type) {
        case MENU_LIST:
            let { list } = action;
            // 将原来jsp中的地址替换成路由
            for (let i = 0; i < list.length; i++) {
                let item = list[i];
                for (let j = 0; j < item.subs.length; j++) {
                    let it = item.subs[j];
                    const to = menuConfigs[it.to];
                    if (!isNvl(to)) {
                        it.to = to;
                    } else {
                        it.to = "";
                    }
                }
            }
            return {
                ...state,
                list
            }
        case MECHANICS://服务技师
            // const { mechanics } = payload;
            return {
                ...state,
                mechanics: payload
            };
        case ADJUSTER://调漆员
            return {
                ...state,
                adjuster: payload
            };
        case PAINT://油漆员
            return {
                ...state,
                paint: payload
            };

        case VENDORS:
            // const { vendors } = payload;
            return {
                ...state,
                vendors: payload
            }
        case MECHANIC_WORK://工单状态
            return {
                ...state,
                mechanicWork: payload
            }
        case MECHANIC_SEX:
            return{
                ...state,
                mechanicSex: payload
            }
        case MECHANIC_GRADE:
            return{
                ...state,
                mechanicGrade: payload
            }

        case DIC_LIST:
            return {
                ...state,
                diclist: payload
            }

        default:
            return state

    }
}
export default base;
