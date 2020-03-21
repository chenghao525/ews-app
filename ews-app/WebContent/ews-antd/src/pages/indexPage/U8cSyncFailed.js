import React, { Component } from "react";
import {Table, message, Modal, Button, Row, Col, Form } from "antd";
import { connect } from "react-redux";
import U8CSearchForm from "../../component/U8CSearchForm";
import { getU8CLists } from "../../store/actions/U8CFailedActions";
import { vendorsAction,yyStockAction } from "../../store/actions/generalActions";
import '../../css/MechanicEditTable.css';
import { Resizable } from 'react-resizable';
import { yyDecreaseStock } from "../../services/generalServices";
import { isNvl } from "../../utils/G";
import "../../css/App.css"

const ResizeableTitle = props => {
    const { onResize, width, ...restProps } = props;

    if (!width) {
        return <th {...restProps} />;
    }

    return (
        <Resizable
            width={width}
            height={0}
            onResize={onResize}
            draggableOpts={{ enableUserSelectHack: false }}
        >
            <th {...restProps} />
        </Resizable>
    );
};

const mapStateToProps = state => {
    return {
        datainfo: state.u8cInfo.datainfo,
        pagination: state.u8cInfo.pagination,
        loading: state.u8cInfo.loading,
        total: state.u8cInfo.total,
        vendors: state.base.vendors,
    }
};
const mapDispatchToProps = dispatch => {
    return {
        load: (params = {}, panigation = {}, sorter = {}) => {
            dispatch(getU8CLists(params, panigation, sorter));
        },
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        }

    }
};

class U8cSyncFailed extends Component{


    state = {
        data: [],
        pagination: {
            pageSize: 10,
            current: parseInt(window.location.hash.slice(1), 0) || 1,
        },
        loading: false,
        modalVisible: false,
        details: {},
        selectdata:[],
        modelOpen: false,
        searchvalue:{},
        selectedRowKeys:[],
        columns: [
            {
                title: 'Number',
                dataIndex: 'number',
                key: 'number',
                width: 100,
                render: (text, record, index) => (
                    <span>{10*((this.state.pagination.current?this.state.pagination.current:1)-1)+index+1}</span>
                ),
            },
            {
                title: "EWS工单号",
                dataIndex: "EWS_RO_CODE",
                //key: "EWS_RO_CODE",
                key:"1",
                width: 180
            },
            {
                title: "工单号",
                dataIndex: "RO_NUMBER",
                //key: "RO_NUMBER",
                key:"2",
                width: 180
            },
            {
                title: "车牌号",
                dataIndex: "VEHICLE_LICENSE",
                //key: "VEHICLE_LICENSE",
                key:"3",
                width: 150
            },
            {
                title: "色母消耗（g）",
                dataIndex: "WEIGHT_TOTAL",
                //key: "WEIGHT_TOTAL",
                width: 150
            },
            {
                title: "色号",
                dataIndex: "COLOR_CODE",
                //key: "COLOR_CODE",
                key:"4",
                width: 150
            },
            {
                title: "调色时间",
                dataIndex: "MIXED_DATE",
                //key: "MIXED_DATE",
                sort: true,
                key:"6",
                width: 200
            },
            {
                title: "工单状态",
                dataIndex: "REWORK_TYPE_NAME",
                filters: [],
                //key: "REWORK_TYPE",
                key:"5",

            },

        ]

    };
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        this.props.load();
        this.props.loadMechanics();
    }
    components = {
        header: {
            cell: ResizeableTitle,
        },
    };

    handleResize = index => (e, { size }) => {
        this.setState({modelOpen: false});
        this.setState(({ columns }) => {
            const nextColumns = [...columns];
            nextColumns[index] = {
                ...nextColumns[index],
                width: size.width,
            };
            return { columns: nextColumns };
        });
    };

    searchFromForm = (values) => {
        this.props.load(values);
        this.setState({searchvalue:values});
    };

    syncForm=()=>{
        if(isNvl(this.state.selectdata)){
            message.warning("请选择要同步的数据");
            return;
        }
        yyDecreaseStock({ro:[this.state.selectdata]}).then(res=>{
            if (res && res.code == '0') {
                //this.props.load(this.state.searchvalue);
                if(this.props.datainfo.length===1){
                    this.setState((prevState)=>(
                        {pagination:{current:prevState.pagination.current-1}}
                    ))}
                this.props.load(this.state.searchvalue,this.state.pagination,{});
                message.info("同步成功");
                this.setState({selectedRowKeys:null});
            } else {

            }
        }).catch(eer=>{
            console.log('44444',eer);
        })

    }



    handleTableChange = (pagination, filters, sorter, currentPageData) => {
        console.log("handle table change");
        this.props.load(this.state.searchvalue, pagination, {});
        this.setState({data:currentPageData});
    };
    // rowSelection = {
    //
    //     onSelect: (sl, selected) => {
    //         console.log("single: is selected: ", selected, " data: ", sl);
    //         this.setState({selectdata:sl});
    //     },
    //     onSelectAll: (selected, asl) => {
    //         console.log("all: is selected: ", selected, " data:", asl);
    //     },
    //     type: "radio", //单选 多选：checkbox
    //     selectedRowKeys:this.state.selectedRowKeys,
    //     onChange: this.onSelectedRowKeysChange,
    // };

    onSelectedRowKeysChange = (selectedRowKeys) => {
        this.setState({ selectedRowKeys:selectedRowKeys });
    }

    modalHandle = () => {
        this.setState({
            modalVisible: false,
            details: {}
        });
    };
    onSelect= (sl, selected) => {
        console.log("single: is selected: ", selected, " data: ", sl);
        this.setState({selectdata:sl});
    }

    reloadTable = () =>{
        this.setState({pagination:{current:1},searchvalue:{}},()=>{
            this.props.load({},this.state.pagination,{});
        })
    }

    selectRow = (record,index)  => {
        // console.log('22222',index);
        // return {
        //     onClick: (e) => {
        //        console.log('22222',index);
                record.key = index;
                console.log(record)
                const selectedRowKeys = [record.key];//单选
                //多选
                //const selectedRowKeys = [...this.state.selectedRowKeys];
                // if (selectedRowKeys.indexOf(record.key) >= 0) {
                //     selectedRowKeys.splice(selectedRowKeys.indexOf(record.key), 1);
                // } else {
                //     selectedRowKeys.push(record.key);
                // }
                this.setState({
                    modalVisible: true,
                    details: record,
                    selectedRowKeys:selectedRowKeys,
                    selectdata:record
                });

        //     }
        // }
    };
    render(){
        const { datainfo, total, loading, pagination, vendors} = this.props;
        const { modalVisible, details, selectdata, selectedRowKeys } = this.state;
        const columns = this.state.columns.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResize(index),
            }),
        }));
        const rowSelection = {
            onSelect:this.onSelect,
            selectedRowKeys:selectedRowKeys,
            onChange: this.onSelectedRowKeysChange,
            type:"radio"
        };
        return (
            <div>
                <U8CSearchForm search={this.searchFromForm} vendors={vendors||[]} selectdata={selectdata||[]} syncForm={this.syncForm}   reloadTable={this.reloadTable}  />
                <Table columns={columns}
                       rowKey = {(record) => record.SID }
                       dataSource={datainfo||[]}
                       loading={loading}
                       pagination={{
                           total: total,
                           current:　this.state.pagination.current,
                           onChange: (page, pageSize) => {
                               this.setState({
                                   pagination:{current: page},
                               },()=>{
                                   window.location.hash = `#${page}`;
                               })
                           }
                       }}
                       rowSelection={rowSelection}
                       onRow={(record) => ({
                           onClick: () => {
                               this.selectRow(record,record.SID);
                           },
                       })}
                       onChange = { this.handleTableChange }
                       components={this.components}
                       size="small"
                       bordered

                />
            </div>
        );
    }


}
U8cSyncFailed=Form.create()(U8cSyncFailed);

export default connect(mapStateToProps, mapDispatchToProps)(U8cSyncFailed);
