import { getU8CFailedList } from "../../services/getU8CFailedServices";
import { getListCommonOptions } from "../../utils/G";

export const U8C_DATA = "U8C_DATA";

export const getU8CLists = (params, pagination, sorter) => {
    const options = getListCommonOptions(pagination, sorter);
    const response = getU8CFailedList(params, options);

    return {
        type: U8C_DATA,
        payload: response
    }
};
