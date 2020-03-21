import React, { Component } from "react";
import  HomeSearchForm  from "../../component/workStatisticComponents/HomeSearchForm";
import DisplayCards from "../../component/workStatisticComponents/DisplayCards";
import {getPrevious12MonthsOfTheCurrent,isNvl} from "../../utils/G";
import {connect} from "react-redux";
import {workStatisticAction} from "../../store/actions/homeActions"
import WorkStatisticChart from "../../component/workStatisticComponents/HomeChart"
import WorkStaticTable from "../../component/workStatisticComponents/WorkStaticTable"
import {Row,Col,Button} from "antd"
import {vendorsAction} from "../../store/actions/generalActions";
import WorkStatisticModel from "../../component/workStatisticComponents/WorkStatisticModel";
import AddCostModel from "../../component/workStatisticComponents/AddCostModel";



const mapStateToProps = state => {
    return {
        data: state.home.data,
        loading: state.home.loading,
        vendors: state.base.vendors,
    }
}

const mapDispatchToProps = dispatch => {
    return {
        load: (args) => {
            dispatch(workStatisticAction(args));
        },
        loadVendors: () => {
            dispatch(vendorsAction({}));
        },
    }
}


class WorkStatisticPage extends Component{
    columns = [
        {
            title: "施工钣面（面）",
            dataIndex: "EWS_RO_CODE",
            key: "EWS_RO_CODE"
        },
        {
            title: "施工台次（台）",
            dataIndex: "RO_NUMBER",
            key: "RO_NUMBER"
        },
        {
            title: "色母",
            dataIndex: "COLOR_MASTER_COST",
            key: "COLOR_MASTER_COST"
        },
        {
            title: "清漆",
            dataIndex: "CLEAR_COAT_COST",
            key: "CLEAR_COAT_COST"
        },
        {
            title: "辅料",
            dataIndex: "SUNDRY_COST",
            key: "SUNDRY_COST"
        },
        {
            title: "耗材",
            dataIndex: "OTHER_COST",
            filters: [],
            key: "OTHER_COST"
        },
        {
            title: "总额",
            dataIndex: "TOTAL_COST",
            key: "TOTAL_COST",
            sort: true
        },
    ];

    state = {
        monthsArray: [],
        dataSource:{},
        date: "",
        modelOpen: false,
        currentSelectMonth:'',
        option : {
            xAxis: {
                type: 'category',
                data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: [120, 200, 150, 80, 70, 110, 130],
                type: 'bar'
            }]
        }
    };

    onRef = (ref) => {
        this.addCostModel = ref
    };

    handleAddClicked = (e) =>{
        this.addCostModel.showModel();
    };

    searchFromForm = (date) => {
        this.loadSearchData(date);
        this.setState({date:date});
    };

    constructor(props){
        super(props);
    }

    loadSearchData= (date) => {
        this.props.load(date);
    };

    openModal = () => {
        this.setState({modelOpen:true});
    };

    handleCancel= () => {
        this.setState({modelOpen:false});
    };

    dataUpdated = (newDataStatus,newData) => {
        if(newDataStatus==='updated'){
            this.props.load(newData.RPT_MONTH);
        }
    };

    componentDidMount() {
        this.props.loadVendors();
        const array = getPrevious12MonthsOfTheCurrent();
        this.setState({monthsArray: array});
        this.setState({date: array[0].text},()=>{
            const dateObj={};
            dateObj.S_MIXED_DATE=array[0].text;
            this.loadSearchData(dateObj);
        });
    }

    getdatevalue=(date)=>{
        this.setState({currentSelectMonth:date});
    };

    componentWillReceiveProps(nextProps){
        const {monthsArray,currentSelectMonth} = this.state;
        if(isNvl(currentSelectMonth)){
            this.setState({currentSelectMonth:monthsArray[1]?monthsArray[0].text:""});
        }
    }

    render() {
        const {monthsArray,currentSelectMonth} = this.state;
        const {data,vendors} = this.props;
        // if(isNvl(currentSelectMonth)){
        //     this.setState({currentSelectMonth:monthsArray[1]?monthsArray[0].text:""});
        // }

        return (
            <div>
                {this.state.modelOpen ?
                    (<WorkStatisticModel visible={this.state.modelOpen} handleCancel={this.handleCancel} currentSelectMonth={currentSelectMonth} destroyOnClose={true} />)
                    :null}
                <HomeSearchForm search={this.searchFromForm} monthsArray={monthsArray||[]} vendors={vendors||[]} getdatevalue={this.getdatevalue}  handleAddClicked={this.handleAddClicked}  />
                <AddCostModel monthsArray={monthsArray||[]} vendors={vendors||[]} onRef={this.onRef} dataUpdated = {this.dataUpdated}/>
                {/*<Button onClick={this.handleAddClicked} type={"primary"} size="small"  style={{margin:'5px'}} >新增</Button>*/}
                <DisplayCards cardsData={data||{}} date={this.state.date||""} openModal={this.openModal} />
                <Row style={{marginTop:'10px',marginLeft:'20px',marginRight:'20px'}} >
                    <Col span={12}>
                        <WorkStatisticChart chartData={data||{}}/>
                    </Col>
                    <Col span={12}>
                        <WorkStaticTable tableData={data||{}}/>
                    </Col>
                </Row>
            </div>
        )
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(WorkStatisticPage);