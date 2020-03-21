import React, { Component } from "react";
import { Route, Switch } from "react-router-dom";
import Home from "../pages/Home";
import RepairOrdersList from "../pages/repairOrders/RepairOrdersList";
import u8cSyncFailed from "../pages/indexPage/U8cSyncFailed";
import EfficiencyRpt from "../pages/report/EfficiencyRpt";
import MechanicEditable from "../pages/mechanic/MechanicEditTable";
import NewRepairOrderTab from "../pages/newrepairorders/NewRepairOrderTab";
//import newRepairOrderList from "../pages/newrepairorders/newRepairOrderList"


class AppRouter extends Component {

    render() {
        return (
            <Switch>
                <Route path="/main" exact component={Home}/>

                <Route path="/main/u8c" component={u8cSyncFailed}/>
                <Route path="/main/rpt" component={EfficiencyRpt}/>
                <Route path="/main/mechanicEditable" component={MechanicEditable}/>
                <Route path="/main/newro" component={NewRepairOrderTab}/>
                {/*<Route path="/main/ro" component={RepairOrdersList}/>*/}
            </Switch>
        );
    }
}
export default AppRouter;
