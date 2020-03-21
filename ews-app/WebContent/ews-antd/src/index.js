import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
import { Provider } from "react-redux";
import store from "./store";
import AppLayout from "./layout/Layout";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./pages/Login";


ReactDOM.render(
    <Router>
        <Provider store={store}>
            <Route exact path="/" component={Login}></Route>
            <Route path="/main" component={AppLayout}></Route>
        </Provider>
    </Router>,
    document.getElementById('root')
);
