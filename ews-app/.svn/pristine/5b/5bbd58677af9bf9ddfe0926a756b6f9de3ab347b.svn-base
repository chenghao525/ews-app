import React, { Component } from "react";
import { Tabs } from "antd";
import { connect } from "react-redux";
import U8cSyncfailed from "./indexPage/U8cSyncfailed";
import WorkStatistic from "./home/WorkStatisticPage";

const { TabPane } = Tabs;

function callback(key) {
    console.log(key);
}

const mapStateToProps = state => {

    return {
        dataSource: state.home.list,
        loading: state.home.loading,
    }

};
const mapDispatchToProps = dispatch => {
    return {

    }

};

class Home extends Component {
    constructor(props) {
        super(props);

    }

    state = {
        data: [],
        loading: false,
        details: {}
    };


    render() {
        const { data, loading} = this.props;
        return (
            <div>
                <Tabs defaultActiveKey="1" onChange={callback}>
                    <TabPane tab="工作统计" key="1">
                        <WorkStatistic/>
                    </TabPane>
                    <TabPane tab="U8C同步" key="2">
                        <U8cSyncfailed/>
                    </TabPane>
                </Tabs>
            </div>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
