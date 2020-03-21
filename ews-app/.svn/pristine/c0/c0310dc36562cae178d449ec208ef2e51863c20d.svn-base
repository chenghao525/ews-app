import React, { Component } from "react";
import { Form, Input, Table, Row, Col, Tabs} from "antd";
import { Resizable } from 'react-resizable';
import {connect} from "react-redux";
import {vendorsAction} from "../store/actions/generalActions";
import {getROLists, getDELSMLists, getDELBMLists, getCarBrandListsAction} from "../store/actions/repairOrderActions";


const mapStateToProps = state => {
    return {
        datasm: state.ro.datasm,//色母
        databm: state.ro.databm,//钣面
        datacb: state.ro.datacb,//车辆品牌
        pagination:0,
        loading: state.ro.loading,
        vendors: state.base.vendors
    }
}

const mapDispatchToProps = dispatch => {
    return {
        load: (params, panigation = {}, sorter = {}) => {
            dispatch(getDELSMLists(params, {}, {}));
            dispatch(getDELBMLists(params, {}, {}));
        },
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        },
        //车辆品牌接口
        loadCarBrand: () => {
            dispatch(getCarBrandListsAction({}));
        },
    }
}

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
class RepairDetails extends Component {
    //钣面详情列表

    state = {
        columnsbm : [
            {
                title: "",
                dataIndex: "1",
                key: "1",
                width: 20,
                render:(text,record,index)=>{
                    return(
                        <Row type="flex" justify="center">{(index+1)}</Row>
                    )
                }
            },
            {
                title: "钣面名称",
                dataIndex: "SHF_NAME",
                key: "SHF_NAME",
                width: 80,
            },
            {
                title: "面数",
                dataIndex: "SHF_NUMBER",
                key: "SHF_NUMBER",
                width: 80,
            },
            {
                title: "描述",
                dataIndex: "SHF_DESCRIPTION",
                key: "SHF_DESCRIPTION",
                width: 80,
            }
        ],
        columnssm : [
            {
                title: "",
                dataIndex: "1",
                key: "1",
                width: 20,
                render:(text,record,index)=>{
                    return(
                        <Row type="flex" justify="center">{(index+1)}</Row>
                    )
                }
            },
            {
                title: "色母编号",
                dataIndex: "COMPONENT_CODE",
                key: "COMPONENT_CODE",
                width: 80,
            },
            {
                title: "色母描述",
                dataIndex: "COMPONENT_DESCRIPTION",
                key: "COMPONENT_DESCRIPTION",
                width: 80,
            },
            {
                title: "称重目标(g)",
                dataIndex: "COMPONENT_WEIGHT_TARGET",
                key: "COMPONENT_WEIGHT_TARGET",
                width: 100,
            },
            {
                title: "实际称重(g)",
                dataIndex: "COMPONENT_WEIGHT_APPLIED",
                key: "COMPONENT_WEIGHT_APPLIED",
                width: 100,
            },
            {
                title: "密度",
                dataIndex: "COMPONENT_DENSITY",
                key: "COMPONENT_DENSITY",
                width: 100,
            }
        ],
    }
    components = {
        header: {
            cell: ResizeableTitle,
        },
    };
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        const { details } = this.props;
        this.props.load({ewsRoCode:details.EWS_RO_CODE});
        this.props.loadMechanics();
        this.props.loadCarBrand();
    }
    //列表列伸缩拉伸
    handleResize = index => (e, { size }) => {
        this.setState(({ columnssm }) => {
            const nextColumns = [...columnssm];
            nextColumns[index] = {
                ...nextColumns[index],
                width: size.width,
            };
            return { columnssm: nextColumns };
        });
    }
    handleResizebm = index => (e, { size }) => {
        this.setState(({ columnsbm }) => {
            const nextColumns = [...columnsbm];
            nextColumns[index] = {
                ...nextColumns[index],
                width: size.width,
            };
            return { columnsbm: nextColumns };
        });
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

   callback=(key)=> {
        console.log(key);
    }

    render() {
        const { datasm, databm,datacb, loading, vendors } = this.props;
        const { details } = this.props;
        const columnssm = this.state.columnssm.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResize(index),
            }),
        }));
        const columnsbm = this.state.columnsbm.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResizebm(index),
            }),
        }));
        return (
            <div>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">EWS工单号：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.EWS_RO_CODE}</Col>
                    <Col span={4} className="gutter-box">门店工单号：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{details.RO_NUMBER}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">门店：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.PROV_NAME_SUM}</Col>
                    <Col span={4} className="gutter-box">调色时间：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{details.MIXED_DATE}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">车牌号：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.VEHICLE_LICENSE}</Col>
                    <Col span={4} className="gutter-box">车辆品牌：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{this.getSelectedDatacbOptions(details.CAR_BRAND)}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">色号：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.COLOR_CODE}</Col>
                    <Col span={4} className="gutter-box">车身颜色：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{details.COLOR_NAME}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">技师：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.MECHANIC_NAME}</Col>
                    <Col span={4} className="gutter-box">调漆技师：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{details.PAINT_MAKER}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box">色母总消耗(g)：</Col>
                    <Col span={8} className="gutter-boxtwo">{details.WEIGHT_TOTAL}</Col>
                    <Col span={4} className="gutter-box">工单状态：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right">{details.REWORK_TYPE_NAME}</Col>
                </Row>
                <Row className="gutter-example">
                    <Col span={4} className="gutter-box boxborder-botom">原工单号：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-botom">{details.ORIGINAL_RO_NUMBER}</Col>
                    <Col span={4} className="gutter-box boxborder-botom">钣面数：</Col>
                    <Col span={8} className="gutter-boxtwo boxborder-right boxborder-botom">{details.SHEET_METAL_COUNT}</Col>
                </Row>
                <Row className="gutter-example" style={{backgroundColor:"#f7f7f7",padding:5}}>色母详情</Row>
                <Table bordered
                       columns={columnssm}
                       components={this.components}
                       dataSource={datasm||[]}
                       loading={loading}
                       pagination={ false }
                />
                <Row className="gutter-example" style={{backgroundColor:"#f7f7f7",padding:5}}>钣面详情</Row>
                <Table bordered
                       columns={columnsbm}
                       components={this.components}
                       dataSource={databm||[]}
                       pagination={ false }
                />
            </div>
        );
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(RepairDetails);
