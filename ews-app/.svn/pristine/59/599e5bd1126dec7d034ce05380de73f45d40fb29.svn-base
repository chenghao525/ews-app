import React, { Component } from "react";
import { Form, Input, Row, Col, Icon, Select, Layout, Button, message} from "antd";
import {connect} from "react-redux";
import {getROPerfectAction} from "../store/actions/repairOrderActions";

const { Option } = Select;
const { TextArea } = Input;
let id = 0;

const mapStateToProps = state => {
    return {
        dataRopft: state.ro.dataRopft,//待完善保存是否成功
    }
}

const mapDispatchToProps = dispatch => {
    return {
        loadRoPerfectEndandPrint: (params,callback) => {
            dispatch(getROPerfectAction(params,callback));
        }

    }
}
class PerfectRODetails extends Component{
    state={
        curCarData : {
              LEFT_REAR_BUMPER:"left_rear_bumper",//左后保险杠
              RIGHT_REAR_BUMPER:"right_rear_bumper",//右后保险杠
              LEFT_FRONT_BUMPER:"left_front_bumper",//左前保险杠
              RIGHT_FRONT_BUMPER:"right_front_bumper", //右前保险杠
              LEFT_FRONT_FENDER:"left_front_fender",//左前叶子板
              RIGHT_FRONT_FENDER:"right_front_fender",//右前叶子板
              LEFT_REAR_FENDER:"left_rear_fender",//左后叶子板
              RIGHT_REAR_FENDER:"right_rear_fender",//右后叶子板
              LEFT_FRONT_DOOR:"left_front_door",//左前门
              RIGHT_FRONT_DOOR:"right_front_door",//右前门
              LEFT_REAR_DOOR:"left_rear_door",//左后门
              RIGHT_REAR_DOOR:"right_rear_door",//右后门
              ROOF:"roof",//车顶
              TRUNK:"trunk",//后备箱盖
              HOOD:"hood", //引擎盖

              REPEAT_NORMAL:"910", //默认状态为正常态
              REPEAT_COLOR:"911", //色差返工
              REPEAT_QUALITY:"912" //质量返工
        },
        shf_count:0,
        shf:[],
        oglShf:[],
        showShf:"",//钣面需要展示的内容
        imageUrl:"../../src/assets/",
        curSl2l:false,
        curSl2r:false,
        curSl2:false,
        curSl3:false,
        curSl4:false,
        curSl6:false,
        curSl7:false,
        curSl8:false,
        curSl9:false,
        curSl10:false,
        curSl12:false,
        curSl13:false,
        curSl14:false,
        curSl3l:false,
        curSl3r:false,
        modalVisible:this.props.modalVisible,
        oglRoShow:false,
        tempdata:{},
        MECHANIC_NAME:"",
        PAINT_MAKER:"",
    }
    constructor(props) {
        super(props);
    }
    componentDidMountMount() {
        // this.props.loadCarBrand();//车辆品牌
    }
    //车品牌选择
    getSelectedDatacbOptions =()=> {
        console.log('11111111');
        const children = this.props.datacb.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    //技师选择
    getSelectedmechanicsOptions =()=> {
        const children = this.props.mechanics.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    //调漆员选择
    getSelectedadjusterOptions =()=> {
        const children = this.props.adjuster.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    //工单状态选择
    getSelectedmechanicWorkOptions =()=> {

        const children = this.props.mechanicWork.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    //原来的工单号是否隐藏
    handleGdStateChange=(value)=>{
        if (value == this.state.curCarData.REPEAT_NORMAL) {
            this.setState({
                oglRoShow:false
            })
        }else {
            this.setState({
                oglRoShow:true
            })
        }
    }
    getSelectedDataorOptions=()=>{
        const children = this.props.dataor.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    clickcarname=(id)=>{
        const isSl = this.clickedState(id);
        console.log('isSl',isSl);
        const shfName = this.getSHFName(id);
        const count = this.getSHFCount(id);
        if (!isSl) {
            for (var i = 0 ; i < this.state.shf.length; i++) {
                if (this.state.shf[i].indexOf(shfName) >= 0) {
                    this.state.shf_count -= this.getSHFCount(id);
                    this.state.shf.splice(i, 1);
                    break;
                }
            }
            this.removeShf(id);
        }else {
            this.state.shf_count += this.getSHFCount(id);
            this.state.shf.push(shfName + '面'+ count);
            this.state.oglShf.push({"SHF_ID": id, "SHF_NAME": shfName, "SHF_NUMBER": count, "SHF_DESCRIPTION": ""});
        }
        if (this.state.shf_count > 0) {
            this.state.showShf = this.state.shf_count + '\n(' + this.state.shf.join(";") + ')';
            console.log('this.state.showShf:',this.state.showShf);
            this.props.form.setFieldsValue({
                SHEET_METAL_SURFACE: this.state.showShf,
            });
        }else {
            this.state.showShf = "";
            this.props.form.setFieldsValue({
                SHEET_METAL_SURFACE: this.state.showShf,
            });
        }
    }
    removeShf=(rmid)=>{
        for (let i = 0; i < this.state.oglShf.length; i++) {
            if (this.state.oglShf[i].SHF_ID == rmid) {
                this.setState({
                    oglShf:this.state.oglShf.splice(i, 1)
                });
                break;
            }
        }
    }
    //图片是否选中
    clickedState=(clickId)=>{
        if (clickId == "left_rear_bumper") {
            // return "左后保险杠";
            this.setState({
                curSl3l: this.state.curSl3l == true ? false : true,
            });
            return !this.state.curSl3l;
        } else if (clickId == "right_rear_bumper") {
            // return "右后保险杠";
            this.setState({
                curSl3r: this.state.curSl3r == true ? false : true,
            });
            return !this.state.curSl3r;
        } else if (clickId == "left_front_bumper") {
            // return "左前保险杠";
            this.setState({
                curSl2l: this.state.curSl2l == true ? false : true,
            });
            return !this.state.curSl2l;
        } else if (clickId == "right_front_bumper") {
            // return "右前保险杠";
            this.setState({
                curSl2r: this.state.curSl2r == true ? false : true,
            });
            return !this.state.curSl2r;
        } else if (clickId == "left_front_fender") {
            // return "左前叶子板";
            this.setState({
                curSl6: this.state.curSl6 == true ? false : true,
            });
            return !this.state.curSl6;
        } else if (clickId == "right_front_fender") {
            // return "右前叶子板";
            this.setState({
                curSl10: this.state.curSl10 == true ? false : true,
            });
            return !this.state.curSl10;
        } else if (clickId == "left_rear_fender") {
            // return "左后叶子板";
            this.setState({
                curSl9: this.state.curSl9 == true ? false : true,
            });
            return !this.state.curSl9;
        } else if (clickId == "right_rear_fender") {
            // return "右后叶子板";
            this.setState({
                curSl14: this.state.curSl14 == true ? false : true,
            });
            return !this.state.curSl14;
        } else if (clickId == "left_front_door") {
            // return "左前门";
            this.setState({
                curSl7: this.state.curSl7 == true ? false : true,
            });
            return !this.state.curSl7;
        } else if (clickId == "right_front_door") {
            // return "右前门";
            this.setState({
                curSl12: this.state.curSl12 == true ? false : true,
            });
            return !this.state.curSl12;
        } else if (clickId == "left_rear_door") {
            // return "左后门";
            this.setState({
                curSl8: this.state.curSl8 == true ? false : true,
            });
            return !this.state.curSl8;
        } else if (clickId == "right_rear_door") {
            // return "右后门";
            this.setState({
                curSl13: this.state.curSl13 == true ? false : true,
            });
            console.log('----curSl13',this.state.curSl13);
            return !this.state.curSl13;

        } else if (clickId == "roof") {
            // return "车顶";
            this.setState({
                curSl3: this.state.curSl3 == true ? false : true,
            });
            return !this.state.curSl3;
        } else if (clickId == "trunk") {
            // return "后备箱盖";
            this.setState({
                curSl4: this.state.curSl4 == true ? false : true,
            });
            return !this.state.curSl4;
        } else if (clickId == "hood") {
            // return "引擎盖";
            this.setState({
                curSl2: this.state.curSl2 == true ? false : true,
            });
            return !this.state.curSl2;
        } else {
            return null;
        }
    }
    //漆面名字
    getSHFName=(positionId)=>{
        if (positionId == "left_rear_bumper") {
            return "左后保险杠";
        } else if (positionId == "right_rear_bumper") {
            return "右后保险杠";
        } else if (positionId == "left_front_bumper") {
            return "左前保险杠";
        } else if (positionId == "right_front_bumper") {
            return "右前保险杠";
        } else if (positionId == "left_front_fender") {
            return "左前叶子板";
        } else if (positionId == "right_front_fender") {
            return "右前叶子板";
        } else if (positionId == "left_rear_fender") {
            return "左后叶子板";
        } else if (positionId == "right_rear_fender") {
            return "右后叶子板";
        } else if (positionId == "left_front_door") {
            return "左前门";
        } else if (positionId == "right_front_door") {
            return "右前门";
        } else if (positionId == "left_rear_door") {
            return "左后门";
        } else if (positionId == "right_rear_door") {
            return "右后门";
        } else if (positionId == "roof") {
            return "车顶";
        } else if (positionId == "trunk") {
            return "后备箱盖";
        } else if (positionId == "hood") {
            return "引擎盖";
        } else {
            return null;
        }
    }
    //漆面数量
    getSHFCount=(countid)=>{
        if (countid == "left_rear_bumper"
            || countid == "right_rear_bumper"
            || countid == "left_front_bumper"
            || countid == "right_front_bumper") {
            return 0.5;
        } else {
            return 1;
        }
    }
    remove = k => {
        const { form } = this.props;
        const keys = form.getFieldValue('keys');
        const names = form.getFieldValue('names');
        if (keys.length === 1) {
            return;
        }
        form.setFieldsValue({
            keys: keys.filter(key => key !== k),
        });
    };

    add = () => {
        const { form } = this.props;
        const keys = form.getFieldValue('keys');
        const nextKeys = keys.concat(id++);
        form.setFieldsValue({
            keys: nextKeys,
        });
    };
    //钣面自定义项数量选择
    getSelectedNumberOptions =()=> {
        const shfCountArray = [{id: '0.25', text: '0.25'}, {id: '0.5', text: '0.5'}, {id: '1', text: '1'}];
        const children = shfCountArray.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    handleCancel=()=>{
        console.log("handleCancel");
        this.props.modalHandle();
    }
    handleOk = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                const { keys, names ,namesNum} = values;
                console.log('Received values of form: ', values);
                console.log('Merged values:', keys.map(key => names[key]));
                values.names = keys.map(key => names[key]);
                values.namesNum = keys.map(key => namesNum[key]);
                console.log('合并后的values的制：：',values);
                this.state.tempdata = values;
            }else {
                console.log('-------errorrr',err);
                console.log('Received values of form: ', values);
                console.log('Merged values:', keys.map(key => names[key]));
            }
        });
        for (var i=0;i<this.state.tempdata.keys.length;i++){
               const curindex = i+1;
               const shfid = 'custom'+ curindex;
               const shfName = '自定义项'+ curindex;
               const shfnumber = this.state.tempdata.namesNum[i];
               const shfdescp = this.state.tempdata.names[i];
               this.state.oglShf.push({"SHF_ID": shfid, "SHF_NAME": shfName, "SHF_NUMBER": shfnumber, "SHF_DESCRIPTION": shfdescp});
                console.log('values.SHF====',this.state.oglShf);
        }
        this.state.tempdata.SHF = this.state.oglShf;
        this.state.SHEET_METAL_COUNT = this.state.shf_count;
        for (var i=0;i<this.props.adjuster.length;i++){
            if (this.state.tempdata.PAINT_MAKER_ID == this.props.adjuster[i].id){
                    this.state.PAINT_MAKER=this.props.adjuster[i].text;
                    break;
            }
        }
        for (var i=0;i<this.props.mechanics.length;i++){
            if (this.state.tempdata.MECHANIC_ID == this.props.mechanics[i].id){
                this.state.MECHANIC_NAME=this.props.mechanics[i].text;
                break;
            }
        }
        const { details } = this.props;
        this.state.tempdata.EWS_RO_CODE = details.EWS_RO_CODE;
        this.state.tempdata.SID = details.SID;

        this.state.tempdata.PAINT_MAKER = this.state.PAINT_MAKER;
        this.state.tempdata.MECHANIC_NAME = this.state.MECHANIC_NAME;
        console.log('this.state.tempdata==',this.state.tempdata);
        this.props.loadRoPerfectEndandPrint(this.state.tempdata,function (res) {
            console.log('res===33333333');
            console.log('res===',res);
            if (res.data.code == "0"){
                message.success(res.data.message, 3);
            }
            console.log('res===444444444');
        });//完善保存并打印
        this.props.searchFromForm();//待完善工单列表
        this.props.modalHandle();
        console.log('res===55555555');
    };
    render(){
        const { getFieldDecorator, getFieldValue } = this.props.form;
        const { details,datacb,dataor } = this.props;
        const {curCarData,imageUrl,oglRoShow,shf_count,showShf, curSl2l, curSl2r, curSl2, curSl3, curSl4, curSl6, curSl7, curSl8, curSl9, curSl10, curSl12, curSl13, curSl14, curSl3l, curSl3r} = this.state;

        const formItemLayout = {
            labelCol: { span: 9 },
            wrapperCol: { span: 13 },
        };
        const formItemLayoutBM = {
            labelCol: { span: 4  },
            wrapperCol: { span: 19 },
        };

        getFieldDecorator('keys', { initialValue: [] });
        const keys = getFieldValue('keys');
        const formItems = keys.map((k, index) => (

            <Form.Item
                label={index === 0 ? '自定义项1' : '自定义项'+ ++index }
                required={false}
                key={k}
            >
                <Row gutter={16}>
                    <Col span={6}>
                {getFieldDecorator(`names[${k}]`, {
                    validateTrigger: ['onChange', 'onBlur'],
                    rules: [
                        {
                            required: true,
                            whitespace: true,
                            message: "Please input describe name or delete this field.",
                        },
                    ],
                })(<Input placeholder="描述" style={{marginRight: 8}} />,)}
                    </Col>
                    <Col span={4}>
                <Form.Item>
                {getFieldDecorator(`namesNum[${k}]`, {
                    rules: [
                        {
                            required: true,
                            message: "Please choose one.",
                        },
                    ],
                })(<Select placeholder="数量" style={{height: 28}} key={'s_gdst'} allowClear={true} >{this.getSelectedNumberOptions()}</Select>)}
                </Form.Item>
                </Col>
                <Col span={1}>
                {keys.length > 1 ? (
                    <Icon
                        className="dynamic-delete-button"
                        type="minus-circle-o"
                        onClick={() => this.remove(k)}
                    />
                ) : null}
                </Col>
                </Row>
            </Form.Item>

        ));
        return(
            <div>
                <Row>
                    <Col span={15} className="boxborder-all">
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="工单号：" {...formItemLayout}>
                            {getFieldDecorator('RO_NUMBER', {
                                initialValue: details.RO_NUMBER,
                                rules: [{ required: true}],
                            })(<Input readOnly={true}/>,)}
                        </Form.Item>
                    </Col>
                    <Col span={12} className="gutter-boxframe">
                        <Form.Item label="PPG颜色编号：" {...formItemLayout}>
                            {getFieldDecorator('PPG_BRAND_CODE', {
                                initialValue: details.PPG_BRAND_CODE,
                                rules: [{ required: true}],
                            })(<Input readOnly={true}/>,)}
                        </Form.Item>
                    </Col>
                </Row>
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="车牌号：" {...formItemLayout}>
                            {getFieldDecorator('VEHICLE_LICENSE', {
                                initialValue: details.VEHICLE_LICENSE,
                                rules: [{ required: true}],
                            })(<Input />,)}
                        </Form.Item>
                    </Col>
                    <Col span={12} className="gutter-boxframe">
                        <Form.Item label="车辆品牌：" {...formItemLayout}>
                            {getFieldDecorator('CAR_BRAND', {
                                initialValue: details.CAR_BRAND,
                                rules: [{required: true}]
                            })(<Select showSearch style={{width: 155, height: 24}} key={'s_c'} allowClear={true} >{this.getSelectedDatacbOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                </Row>
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="色号：" {...formItemLayout}>
                            {getFieldDecorator('COLOR_CODE', {
                                initialValue:details.COLOR_CODE,
                                rules: [{required: true}],
                            })(<Input/>,)}
                        </Form.Item>
                    </Col>
                    <Col span={12} className="gutter-boxframe">
                        <Form.Item label="车身颜色：" {...formItemLayout}>
                            {getFieldDecorator('COLOR_NAME', {
                                initialValue:details.COLOR_NAME,
                                rules: [{ required: true}],
                            })(<Input />,)}
                        </Form.Item>
                    </Col>
                </Row>
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="技师：" {...formItemLayout}>
                            {getFieldDecorator(`MECHANIC_ID`, {
                                rules: [{required: true}]
                            })(<Select style={{width: 155, height: 24}} key={'s_mi'} allowClear={true} >{this.getSelectedmechanicsOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={12} className="gutter-boxframe">
                        <Form.Item label="调漆技师：" {...formItemLayout}>
                            {getFieldDecorator(`PAINT_MAKER_ID`, {
                                rules: [{required: true,}]
                            })(<Select style={{width: 155, height: 24}} key={'s_pmi'} allowClear={true} >{this.getSelectedadjusterOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                </Row>
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="工单是否废弃：" {...formItemLayout}>
                            {getFieldDecorator(`DISCARD`, {
                                rules: [{required: true}]
                            })(<Select showSearch style={{width: 155, height: 24}} key={'s_gd'} allowClear={true} >
                                  <Option value="no" key={"no"}>正常</Option>
                                  <Option value="yes" key={"yes"}>废弃</Option>
                               </Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={12} className="gutter-boxframe">
                        <Form.Item label="色母总消耗(g)：" {...formItemLayout}>
                            {getFieldDecorator('WEIGHT_TOTAL', {
                                initialValue: details.WEIGHT_TOTAL,
                                rules: [{ required: true}],
                            })(<Input readOnly={true}/>,)}
                        </Form.Item>
                    </Col>
                </Row>
                <Row className="gutter-examplepo boxborder-botom">
                    <Col span={12} className="gutter-boxframe boxborder-right">
                        <Form.Item label="工单状态：" {...formItemLayout}>
                            {getFieldDecorator(`REWORK_TYPE`, {
                                rules: [{required: true}]
                            })(<Select showSearch onChange={this.handleGdStateChange} style={{width: 155, height: 24}} key={'s_gdst'} allowClear={true} >{this.getSelectedmechanicWorkOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={12} style={{display:oglRoShow?'block':'none'}} className="gutter-boxframe">
                        <Form.Item label="原来的工单号：" {...formItemLayout}>
                            {getFieldDecorator(`ORIGINAL_RO_NUMBER`, {
                                initialValue: "",
                                rules: [{required: false,}]
                            })(<Select style={{width: 155, height: 24}} key={'s_frgd'} allowClear={true} >{this.getSelectedDataorOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                </Row>
                 <Row className="gutter-examplepo">
                     <Col className="gutter-boxframe">
                         <Form.Item label="钣面：" {...formItemLayoutBM}>
                             {getFieldDecorator('SHEET_METAL_SURFACE',{
                                 rules:[{required:false}]
                             })(<TextArea rows={3}/>)}
                         </Form.Item>
                     </Col>
                 </Row>
                    </Col>
                <Col  span={9}>
                    <Row>
                        <Col span={4}></Col>
                        <Col span={16}>
                            <img id="left_front_bumper" onClick={()=>this.clickcarname(curCarData.LEFT_FRONT_BUMPER)} style={{width:"49%"}} src={imageUrl + [curSl2l?"6_lf.png":"2_lf.png"]} />
                            <img id="right_front_bumper" onClick={()=>this.clickcarname(curCarData.RIGHT_FRONT_BUMPER)} style={{width:"50%"}} src={imageUrl + [curSl2r?"6_rt.png":"2_rt.png"]} />
                        </Col>
                        <Col span={4}></Col>
                    </Row>
                    <Row>
                        <Col span={9}>
                            <img id="left_front_fender" onClick={()=>this.clickcarname(curCarData.LEFT_FRONT_FENDER)} style={{width:"100%"}} src={imageUrl + [curSl6?"66.jpg":"6.jpg"]}/>
                            <img id="left_front_door" onClick={()=>this.clickcarname(curCarData.LEFT_FRONT_DOOR)} style={{width:"100%"}} src={imageUrl + [curSl7?"77.jpg":"7.jpg"]}/>
                            <img id="left_rear_door" onClick={()=>this.clickcarname(curCarData.LEFT_REAR_DOOR)} style={{width:"100%"}} src={imageUrl + [curSl8?"88.jpg":"8.jpg"]}/>
                            <img id="left_rear_fender" onClick={()=>this.clickcarname(curCarData.LEFT_REAR_FENDER)} style={{width:"100%"}} src={imageUrl + [curSl9?"99.jpg":"9.jpg"]}/>
                        </Col>
                        <Col span={6}>
                            <img id="hood" onClick={()=>this.clickcarname(curCarData.HOOD)} style={{width:"100%"}} src={imageUrl + [curSl2?"22.jpg":"2.jpg"]} />
                            <img id="roof" onClick={()=>this.clickcarname(curCarData.ROOF)} style={{width:"100%"}}  src={imageUrl + [curSl3?"33.jpg":"3.jpg"]}/>
                            <img id="trunk" onClick={()=>this.clickcarname(curCarData.TRUNK)} style={{width:"100%"}} src={imageUrl + [curSl4?"44.jpg":"4.jpg"]}/>
                        </Col>
                        <Col span={9}>
                            <img id="right_front_fender" onClick={()=>this.clickcarname(curCarData.RIGHT_FRONT_FENDER)} style={{width:"100%"}}  src={imageUrl + [curSl10?"1010.jpg":"10.jpg"]} />
                            <img id="right_front_door" onClick={()=>this.clickcarname(curCarData.RIGHT_FRONT_DOOR)} style={{width:"100%"}} src={imageUrl + [curSl12?"1212.jpg":"12.jpg"]} />
                            <img id="right_rear_door" onClick={()=>this.clickcarname(curCarData.RIGHT_REAR_DOOR)} style={{width:"100%"}} src={imageUrl + [curSl13?"1313.jpg":"13.jpg"]} />
                            <img id="right_rear_fender" onClick={()=>this.clickcarname(curCarData.RIGHT_REAR_FENDER)} style={{width:"100%"}} src={imageUrl + [curSl14?"1414.jpg":"14.jpg"]} />
                        </Col>
                    </Row>
                    <Row>
                        <Col span={4}></Col>
                        <Col span={16}>
                            <img id="left_rear_bumper" onClick={()=>this.clickcarname(curCarData.LEFT_REAR_BUMPER)} style={{width:"49%"}} src={imageUrl + [curSl3l?"4_lf.png":"3_lf.png"]} />
                            <img id="right_rear_bumper" onClick={()=>this.clickcarname(curCarData.RIGHT_REAR_BUMPER)} style={{width:"50%"}} src={imageUrl + [curSl3r?"4_rt.png":"3_rt.png"]} />
                        </Col>
                        <Col span={4}></Col>
                    </Row>
                </Col>
                </Row>
                <br/>
                <Form>
                    <Form.Item>
                        <Button type="primary" onClick={this.add}>添加钣面自定义项</Button>
                    </Form.Item>
                    {formItems}
                </Form>
                <Row type="flex" justify="end">
                    <Col span={19}></Col>
                    <Col span={2}><Button onClick={this.handleCancel}>取消</Button></Col>
                    <Col span={3}><Button onClick={this.handleOk} type="primary">保存并打印</Button></Col>
                </Row>
            </div>


        )
    }
}

PerfectRODetails=Form.create()(PerfectRODetails);
export default connect(mapStateToProps,mapDispatchToProps)(PerfectRODetails);