import React, {Component} from 'React';
import {Table, message, Modal} from 'antd';
import { Resizable } from 'react-resizable';
import "../../css/App.css"
import { connect } from 'react-redux';
import ROSearchForm from "../../component/ROSearchForm";
import RepairDetails from "../../component/RepairDetails";
import { getROLists } from "../../store/actions/repairOrderActions";
import { vendorsAction } from "../../store/actions/generalActions";

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
        loading: state.ro.loading,
        vendors: state.base.vendors
    }
}

const mapDispatchToProps = dispatch => {
    return {
        load: (params = {}, panigation = {}, sorter = {}) => {
            dispatch(getROLists(params, panigation, sorter));
        },
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        }
    }
}


class newRepairOrderList extends Component{

    state = {
        columns: [
            {
                title: "EWS工单号",
                dataIndex: "EWS_RO_CODE",
                key: "EWS_RO_CODE",
                width: 100,
            },
            {
                title: "门店",
                dataIndex: "PROV_SID",
                key: "PROV_SID",
                width: 100,
            },
            {
                title: "工单号",
                dataIndex: "RO_NUMBER",
                key: "RO_NUMBER",
                width: 100,
            },
            {
                title: "车牌号",
                dataIndex: "VEHICLE_LICENSE",
                key: "VEHICLE_LICENSE",
                width: 100,
            },
            {
                title: "色母消耗（g）",
                dataIndex: "WEIGHT_TOTAL",
                key: "WEIGHT_TOTAL",
                width: 100,
            },
            {
                title: "色号",
                dataIndex: "COLOR_CODE",
                key: "COLOR_CODE",
                width: 100,
            },
            {
                title: "车身颜色",
                dataIndex: "COLOR_NAME",
                key: "COLOR_NAME",
                width: 100,
            },
            {
                title: "车辆品牌",
                dataIndex: "CAR_BRAND",
                key: "CAR_BRAND",
                width: 100,
            },
            {
                title: "钣面数",
                dataIndex: "SHEET_METAL_COUNT",
                key: "SHEET_METAL_COUNT",
                width: 100,
            },
            {
                title: "调漆技师",
                dataIndex: "PAINT_MAKER",
                key: "PAINT_MAKER",
                width: 100,
            },
            {
                title: "技师",
                dataIndex: "MECHANIC_NAME",
                key: "MECHANIC_NAME",
                width: 100,
            },
            {
                title: "工单状态",
                dataIndex: "REWORK_TYPE",
                filters: [],
                key: "REWORK_TYPE",
                width: 100,
            },
            {
                title: "调色时间",
                dataIndex: "MIXED_DATE",
                key: "MIXED_DATE",
                sort: true,
                width: 100,
            },
        ],
        data: [],
        pagination: {
            pageSize: 50
        },
        loading: false,
        modalVisible: false,
        details: {}
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
        this.props.load();
        this.props.loadMechanics();
    }
    searchFromForm = (values) => {
        this.props.load(values);
    }

    handleTableChange = (pagination, filters, sorter) => {
        console.log("handle table change");
        this.props.load({}, pagination, sorter);
    }
    rowSelection = {
        onSelect: (sl, selected) => {
            console.log("single: is selected: ", selected, " data: ", sl);
        },
        onSelectAll: (selected, asl) => {
            console.log("all: is selected: ", selected, " data:", asl);
        },
        type: "radio" //单选 多选：checkbox
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
        const { data, loading, pagination, vendors } = this.props;

        const columns = this.state.columns.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResize(index),
            }),
        }));
        const { modalVisible, details } = this.state;
        return (
            <div>
                <ROSearchForm search={this.searchFromForm} vendors={vendors||[]}/>
                <Table columns={columns}
                       components={this.components}
                       rowKey = {record => record.EWS_RO_CODE }
                       dataSource={data||[]}
                       loading={loading}
                       pagination={pagination||{}}
                       rowSelection={this.rowSelection}
                       onRow={this.onRow}
                       onChange = { this.handleTableChange }
                       bordered
                />
                <Modal visible={modalVisible}
                       onCancel={this.modalHandle} onOk={this.modalHandle}>
                    <RepairDetails details={details}/>
                </Modal>
            </div>
        );
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(newRepairOrderList);