import React, {Component} from 'React';
import {Tabs, Icon} from 'antd';
import NewTobePerfectRO from "./NewTobePerfectRO";
import NewRepairOrderList from "./newRepairOrderList";

class NewRepairOrderTab extends Component{
render() {
    const { TabPane } = Tabs;
    return (
        <div>
            <Tabs defaultActiveKey="1">
                <TabPane
                    tab={<span><Icon type="exception" />待完善</span>}
                    key="1">
                    <NewTobePerfectRO/>
                </TabPane>
                <TabPane
                    tab={<span><Icon type="hourglass" />已完善</span>
                    }key="2">
                    <NewRepairOrderList/>
                </TabPane>
            </Tabs>

        </div>
    );
}
}

export default NewRepairOrderTab;