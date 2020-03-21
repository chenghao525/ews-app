import reducers from "./reducers";
import { createStore, applyMiddleware } from "redux";
import ReduxPromise from "redux-promise"

const finalCreateStore = applyMiddleware(ReduxPromise)(createStore);
const store = finalCreateStore(reducers);


export default store;
