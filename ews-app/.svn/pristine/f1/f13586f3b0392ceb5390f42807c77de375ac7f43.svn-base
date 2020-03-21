import { ADD_DATA } from "../actions/mechanicInfoActions";


const initialState = {
    data: [],
    loading: true,
    pagination: {
        pageSize: 50
    }
};

const mechanicInfo = (state=initialState,action) => {
    switch (action.type) {
        case ADD_DATA:
            const { payload } = action;
            const data = payload.data || [];
            const total = payload.total || 0;
            return {
                ...state,
                loading: false,
                data,
                total
            };

        default:
            return state;
    }
};

export default mechanicInfo;