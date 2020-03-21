import React, {Component} from 'React';
import {Table, message, Modal,Row,Col} from 'antd';
import { Resizable } from 'react-resizable';//table可拖拽插件
import "../../css/App.css"
import { connect } from 'react-redux';
import ROSearchForm from "../../component/ROSearchForm";
import RepairDetails from "../../component/RepairDetails";
import { getROLists } from "../../store/actions/repairOrderActions";
import {mechanicsAction, vendorsAction,adjusterAction} from "../../store/actions/generalActions";
import {mechanicWorkAction} from "../../store/actions/mechanicInfoActions"

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
}

const mapStateToProps = state => {
    return {
        data: state.ro.data,
        pagination: state.ro.pagination,
        total:state.ro.total,
        loading: state.ro.loading,
        datacb: state.ro.datacb,//车辆品牌
        vendors: state.base.vendors,
        adjuster:state.base.adjuster,//调漆员
        mechanics:state.base.mechanics,//服务技师
        mechanicWork:state.base.mechanicWork,//工单状态
    }
}

const mapDispatchToProps = dispatch => {
    return {
        //完善工单列表
        load: (params = {}, panigation = {}, sorter = {}) => {
            dispatch(getROLists(params, panigation, sorter));
        },
        //门店
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        },
        //服务技师
        loadSeverMechanic: ()=>{
            dispatch(mechanicsAction({work:""}));
        },
        //调漆员
        loadTqAdjusterAction: () => {
            dispatch(adjusterAction({work:'900'}));
        },
        //工单状态
        loadmechanicWork: ()=>{
            dispatch(mechanicWorkAction({infoType:51}));
        }
    }
}


class newRepairOrderList extends Component{

    state = {
        columns: [
            {
                title: "序号",
                dataIndex: "0",
                key: "0",
                width: 60,
                align:"center",
                render:(text,record,index)=>{
                    return(
                        <Row type="flex" justify="center">{(this.state.currentIndex-1)*10+(index+1)}</Row>
                    )
                }
            },
            {
                title: "EWS工单号",
                dataIndex: "EWS_RO_CODE",
                key: "EWS_RO_CODE",
                width: 100,
                align:"center",
            },
            {
                title: "门店",
                dataIndex: "PROV_NAME_SUM",
                key: "PROV_NAME_SUM",
                width: 100,
                align:"center",
                onCell: () => {
                    return {
                        style: {
                            maxWidth: 100,
                            overflow: 'hidden',
                            whiteSpace: 'nowrap',
                            textOverflow:'ellipsis',
                            cursor:'pointer'
                        }
                    }
                },
                render: (text) => <span>{text}</span>
            },
            {
                title: "工单号",
                dataIndex: "RO_NUMBER",
                key: "RO_NUMBER",
                width: 100,
                align:"center",
            },
            {
                title: "车牌号",
                dataIndex: "VEHICLE_LICENSE",
                key: "VEHICLE_LICENSE",
                width: 100,
                align:"center",
            },
            {
                title: "色母消耗（g）",
                dataIndex: "WEIGHT_TOTAL",
                key: "WEIGHT_TOTAL",
                width: 100,
                align:"center",
            },
            {
                title: "色号",
                dataIndex: "COLOR_CODE",
                key: "COLOR_CODE",
                width: 100,
                align:"center",
            },
            {
                title: "车身颜色",
                dataIndex: "COLOR_NAME",
                key: "COLOR_NAME",
                width: 100,
                align:"center",
            },
            {
                title: "车辆品牌",
                dataIndex: "CAR_BRAND",
                key: "CAR_BRAND",
                width: 100,
                align:"center",
                render:(text,record,index)=>{
                    return(
                        <span type="flex" justify="center">{this.getSelectedDatacbOptions(text)}</span>
                    )
                }
            },
            {
                title: "钣面数",
                dataIndex: "SHEET_METAL_COUNT",
                key: "SHEET_METAL_COUNT",
                width: 70,
                align:"center",
            },
            {
                title: "调漆技师",
                dataIndex: "PAINT_MAKER",
                key: "PAINT_MAKER",
                width: 100,
                align:"center",
            },
            {
                title: "技师",
                dataIndex: "MECHANIC_NAME",
                key: "MECHANIC_NAME",
                width: 100,
                align:"center",
            },
            {
                title: "工单状态",
                dataIndex: "REWORK_TYPE_NAME",
                filters: [],
                key: "REWORK_TYPE_NAME",
                width: 100,
                align:"center",
            },
            {
                title: "调色时间",
                dataIndex: "MIXED_DATE",
                key: "MIXED_DATE",
                sort: true,
                width: 150,
                align:"center",
            },
        ],
        data: [],
        pagination: {
            pageSize: 10,
            total:this.total,
        },
        currentIndex:1,
        loading: false,
        modalVisible: false,
        details: {},
    };

    components = {
        header: {
            cell: ResizeableTitle,
        },
    };
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        this.props.load()//完善工单列表
        this.props.loadMechanics();//门店
        this.props.loadSeverMechanic();//服务技师
        this.props.loadTqAdjusterAction();//调漆员
        this.props.loadmechanicWork();//工单状态
    }
    //车品牌选择
    getSelectedDatacbOptions =(id)=> {
        for (var i=0;i<this.props.datacb.length;i++){
            if (id == this.props.datacb[i].id){
                const curtext = this.props.datacb[i].text;
                return curtext;
            }
        }
    }
    searchFromForm = (values) => {
        this.props.load(values);
    }

    handleTableChange = (pagination, filters, sorter) => {
        console.log("handle table change");
        this.props.load({}, pagination, sorter);
    }
    onSelectChange = selectedRowKeys => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
    };
    rowSelection = {
        onSelect: (sl, selected) => {
            console.log("single: is selected: ", selected, " data: ", sl);
        },
        onSelectAll: (selected, asl) => {
            console.log("all: is selected: ", selected, " data:", asl);
        },
        onChange:(selectedRowKeys)=>{
            console.log("selectedRowKeys: ", selectedRowKeys);
        },
        type: "radio", //单选 多选：checkbox
        fixed:true,
        columnWidth:50,
    }
    modalHandle = () => {
        this.setState({
            modalVisible: false,
            details: {}
        });
    }
    onRow = record => {
        return {
            onClick: event => {
                this.setState({
                    modalVisible: true,
                    details: record
                });
            }
        }
    }
    //列表列伸缩拉伸
    handleResize = index => (e, { size }) => {
        this.setState(({ columns }) => {
            const nextColumns = [...columns];
            nextColumns[index] = {
                ...nextColumns[index],
                width: size.width,
            };
            return { columns: nextColumns };
        });
    };
    render() {
        const { data, loading, vendors, total, adjuster, mechanics, mechanicWork,currentSelectMonth,selectedRowKeys} = this.props;
        const columns = this.state.columns.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResize(index),
            }),
        }));
        const { modalVisible, details, pagination} = this.state;
        return (
            <div>
                <ROSearchForm search={this.searchFromForm} vendors={vendors||[]} adjuster={adjuster||[]} mechanics={mechanics||[]} mechanicWork={mechanicWork||[]} currentSelectMonth={currentSelectMonth||[]} />
                <Table columns={columns}
                       components={this.components}
                       dataSource={data||[]}
                       loading={loading}
                       rowKey={record => record.SID}
                       pagination={{
                           pageSize:10,
                           total:total,
                           onChange:(page,pageSize)=>{
                               this.setState({
                                   currentIndex:page
                               })
                           }
                       }}
                       rowSelection={this.rowSelection}
                       onRow={this.onRow}
                       onChange = { this.handleTableChange }
                       bordered
                       size="small"
                       scroll={{ x: 1500 }}
                />

                <Modal visible={modalVisible} width={770} className="modalstyle" title="工单详情"
                       onCancel={this.modalHandle} onOk={this.modalHandle} destroyOnClose={true}>
                    <RepairDetails details={details}/>
                </Modal>
            </div>
        );
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(newRepairOrderList);