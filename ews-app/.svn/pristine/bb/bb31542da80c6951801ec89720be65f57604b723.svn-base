import { U8C_DATA } from "../actions/U8CFailedActions";


const initialState = {
    datainfo: [],
    loading: true,
    pagination: {
        pageSize: 10
    }
};

const u8cInfo = (state=initialState,action) => {
    switch (action.type) {
        case U8C_DATA:
            const { payload } = action;
            const datainfo = payload.data || [];
            const total = payload.total || 0;
            return {
                ...state,
                loading: false,
                datainfo,
                total
            };

        default:
            return state;
    }
};

export default u8cInfo;