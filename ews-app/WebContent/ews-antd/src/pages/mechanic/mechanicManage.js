import React, {Component} from "react";
import {Table, message } from "antd";
import {getListCommonOptions} from "../../utils/G";
import {connect} from "react-redux";
// import ROSearchForm from "../../component/ROSearchForm";

class mechanicManage extends Component{

    columns = [
        {
            title: "����",
            // dataIndex: "ME_NAME",
            // key: "ME_NAME"
        },
        {
            title: "�ŵ�",
            dataIndex: "ME_SHOP",
            key: "ME_SHOP"
        },
        {
            title: "��ʦ��λ",
            dataIndex: "ME_POST",
            key: "ME_POST"
        },
        {
            title: "�绰",
            dataIndex: "ME_TEL",
            key: "ME_TEL"
        },
    ]
    state = {
        data:[],
        pagination: {},
        loading: false
    }
    // componentDidMount(){
    //     this.fetch();
    // }
    // handleTableChange = (pagination, filters, sorter) =>{
    //     this.fetch(pagination, sorter);
    // }

    render() {
        return(
            <div>
                {/*<ROSearchForm/>*/}
                <Table
                    columns={this.columns}
                       rowkey = {record => record.MECHANIC_NAME}
                       dataSource={this.state.data}
                       loading={this.state.loading}
                       pagination={this.state.pagination}
                       // onChange = {this.handleTableChange}
                />
            </div>
        );
    }
}

export default connect()(mechanicManage);