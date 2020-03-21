import React, {Component} from 'React';
import {List, Card, Button, Modal,Row,Col} from 'antd';
import "../../css/App.css";
import { connect } from 'react-redux';
import SingleFormItem from "../../component/SingleFormItem";
import {getCarBrandListsAction, getRoPerfectAction,getOriginaRoCodesAction} from "../../store/actions/repairOrderActions";
import PerfectRODetails from  "../../component/PerfectRODetails";
import {adjusterAction, mechanicsAction, vendorsAction} from "../../store/actions/generalActions";
import {mechanicWorkAction} from "../../store/actions/mechanicInfoActions";
import { isNvl } from "../../utils/G";


const mapStateToProps = state => {
    return {
        data: state.ro.data,
        pagination: state.ro.pagination,
        loading: state.ro.loading,
        datape:state.ro.datape,//待完善工单列表
        datacb: state.ro.datacb,//车辆品牌
        dataor: state.ro.dataor,//原来的工单号
        adjuster:state.base.adjuster,//调漆员
        mechanics:state.base.mechanics,//服务技师
        mechanicWork:state.base.mechanicWork,//工单状态
        vendors: state.base.vendors
    }
}

const mapDispatchToProps = dispatch => {
    return {
        //待完善工单列表
        loadRoPerfect: (value)=>{
            if(isNvl(value)){
                value={S_REPAIR_STATE:"no"};
            }else{
                value.S_REPAIR_STATE="no";
            }
            dispatch(getRoPerfectAction(value));
        },
        //车辆品牌接口
        loadCarBrand: () => {
            dispatch(getCarBrandListsAction({}));
        },
        //原来的工单号
        loadORLCodes: ()=>{
            dispatch(getOriginaRoCodesAction({}));
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
        },
        //门店
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        }
    }
}

class NewTobePerfectRO extends Component{

    state = {
        modalVisible: false,
        details: {},
        // datacb:[],
    }

    componentDidMount(){
        this.props.loadRoPerfect();//待完善工单列表
        this.props.loadMechanics();//门店
        this.props.loadCarBrand();//车辆品牌
        this.props.loadSeverMechanic();//服务技师
        this.props.loadTqAdjusterAction();//调漆员
        this.props.loadmechanicWork();//工单状态
    }
    choooseoneclick=(values)=>{
        console.log('55555555555');
        console.log(values);
        this.setState({
            modalVisible: true,
            details: values,
        });
    }
    modalHandle = () => {
        Modal.destroyAll();
        this.setState({
            modalVisible: false,
        });
    }
    searchFromForm = (values) => {
        this.props.loadRoPerfect(values);
    }
    render(){
        const {datape,datacb,dataor,adjuster,mechanics,mechanicWork,vendors} = this.props;
        const {modalVisible,details} = this.state;
        return(
            <div>
                <SingleFormItem vendors={vendors||[]}  search={this.searchFromForm}  />
                <List
                    grid={{ gutter: 16, column: 5 }}
                    dataSource={datape}
                    renderItem={(item,index) => (
                        <List.Item>
                            <Card title={item.RO_NUMBER} onClick={() => this.choooseoneclick(item)} actions={[<a>待完善</a>]}>
                                <Row type="flex" justify="center">
                                    <Col>这是第 <strong style={{color:"#fc7944"}}>{index+1}</strong> 个需要待完善的单子</Col>
                                </Row>
                            </Card>
                        </List.Item>
                    )}
                    >
                </List>
                <Modal visible={modalVisible} width={970} className="modalstyle" title="工单详情"
                       onCancel={this.modalHandle} onOk={this.modalHandle} footer={null} destroyOnClose={true}>

                    <PerfectRODetails modalHandle={this.modalHandle} searchFromForm={this.searchFromForm} details={details} datacb={datacb||[]} dataor={dataor||[]} mechanics={mechanics||[]} adjuster={adjuster||[]} mechanicWork={mechanicWork||[]}/>
                </Modal>
            </div>
        )
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(NewTobePerfectRO);