import React, {Component} from 'react';
import {Table} from 'antd'


class WorkStaticTable extends Component{
    state={
        columns : [
            {
                title:'技师',
                dataIndex:'MECHANIC_NAME',
                key:'MECHANIC_NAME'
            },
            {
                title:'台次',
                dataIndex:'RO_COUNT',
                key:'RO_COUNT'
            },
            {
                title:'施工面',
                dataIndex:'SHF_COUNT',
                key:'SHF_COUNT'
            },
            {
                title:'均值（面/天）',
                dataIndex:'AVG_VAL',
                key:'AVG_VAL'
            }
        ]
    };

    constructor(props){
        super(props);
    }

    render(){
        const {tableData} = this.props;
        const myTableData = tableData.details?tableData.details.dtl:[];

        for(let i=0;i<myTableData.length;i++){
            myTableData[i].key=i;
        }

        return(
            <div>
                <Table
                    bordered
                    columns={this.state.columns}
                    dataSource={myTableData||[]}
                    rowKey={record=>record.key}
                />
            </div>
        )
    }
}

export default WorkStaticTable