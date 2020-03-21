import { getWorkStatistic } from "../../services/workStatisticService";

export const HOME_DATA = "HOME_DATA";


export const workStatisticAction = (args) => {
    const rsp = getWorkStatistic(args);
    return {
        type: HOME_DATA,
        payload: rsp,
    }
};
