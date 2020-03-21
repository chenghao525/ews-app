import React, { Component } from "react";
import { Form, Row, Col, Input, Button, Select, Icon, DatePicker, message } from "antd";
import { formIsNvl, getSearchForm, getLastDayOfMonth,isNvl } from "../utils/G";
 import locale from "antd/es/date-picker/locale/zh_CN";
import moment from "moment";
import "moment/locale/zh-cn";

moment.locale("zh-cn");

const { Option } = Select;
const { RangePicker } = DatePicker;
const dateFormat = 'YYYY-MM-DD HH:mm';
//const teaday = moment().subtract('1','day').format(dateFormat);

class ROSearchForm extends Component {

    state = {
        expand: false,
        loading: true
    };

    handleSearch = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (formIsNvl(values)) {
                message.warning("请输入需要查询的值");
                return;
            }
            const json = getSearchForm(values, {
                S_MIXED_DATE: "YYYY-MM-DD"
            });
            this.props.search(json);

        });

    };
    handleReset = () => {
        this.props.form.resetFields();
    };
    toggle = () => {
        const { expand } = this.state;
        this.setState({ expand: !expand })
    };
    lastmonthday=()=>{
        const dateFormat = 'YYYY-MM-DD HH:mm';
        const teaday=getLastDayOfMonth(this.props.currentSelectMonth);
        return teaday;
    }
    //selected门店
    getSelectedOptions() {
        const children = this.props.vendors.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    //服务技师
    getSelectedMechanicsOptions() {
        const children = this.props.mechanics.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        })
        return children;
    }
    //selected调漆员
    getSelectedAdjusterOptions() {
        const children = this.props.adjuster.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        })
        return children;
    }
    //selected 工单状态
    getSelectedmechanicWorkOptions() {
        const children = this.props.mechanicWork.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        })
        return children;
    }
    getFields() {
        const count = this.state.expand ? 10 : 6;
        const { getFieldDecorator } = this.props.form;
        const children = [];
        for (let i = 0; i < 10; i++) {
            children.push(
                <Col span={4} key={i} style={{ display: i < count ? 'block': 'none', padding: '0 10px'}}>
                    <Form.Item label={`Field ${i}`}>
                        {getFieldDecorator(`field-${i}`, {
                            rules: [{required: false}]
                        })(<Input placeholder="placeholder"/>)}
                    </Form.Item>
                </Col>
            )
        }
        return children;
    }
    render() {
        const { getFieldDecorator } = this.props.form;
        const { currentSelectMonth } = this.props;
        const rangeConfig = [{type: 'array', required: true, message: 'Please select time!' }];
        return (
            <Form className="ant-advanced-search-form" onSubmit={this.handleSearch}>
                <Row gutter={24} style={{padding: '5px'}}>
                    <Col span={4} key={'s_1'}>
                        <Form.Item label={"门店"} key={'p_s'}>
                            {getFieldDecorator(`S_PROV_SID`, {
                                rules: [{required: false}]
                            })(<Select style={{ width: 150 }} key={'s_c'} allowClear={true} >{this.getSelectedOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_2'}>
                        <Form.Item label={"工单号"}>
                            {getFieldDecorator(`S_RO_NUMBER`, {
                                rules: [{required: false}]
                            })(<Input placeholder="工单号" allowClear />)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_3'}>
                        <Form.Item label={"车牌号"}>
                            {getFieldDecorator(`S_VEHICLE_LICENSE`, {
                                rules: [{required: false}]
                            })(<Input placeholder="车牌号" allowClear />)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_4'}>
                        <Form.Item label={"服务技师"} key={'s_t'}>
                            {getFieldDecorator(`S_MECHANIC_ID`, {
                                rules: [{required: false}]
                            })(<Select style={{ width: 120 }}  key={'s_t'} allowClear={true}>{this.getSelectedMechanicsOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_5'}>
                        <Form.Item label={"调漆员"} key={'s_p'}>
                            {getFieldDecorator(`S_PAINT_MAKER_ID`, {
                                rules: [{required: false}]
                            })(<Select style={{ width: 120 }} key={'s_p'}  allowClear={true}>{this.getSelectedAdjusterOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_6'}>
                        <Form.Item label={"色号"}>
                            {getFieldDecorator(`S_PPG_BRAND_CODE`, {
                                rules: [{required: false}]
                            })(<Input placeholder="色号" allowClear />)}
                        </Form.Item>
                    </Col>
                    <Col span={4} key={'s_7'}>
                        <Form.Item label={"工单状态"} key={'s_s'}>
                            {getFieldDecorator(`S_REWORK_TYPE`, {
                                rules: [{required: false}]
                            })(<Select style={{ width: 120 }} key={'s_s'}  allowClear={true}>{this.getSelectedmechanicWorkOptions()}</Select>)}
                        </Form.Item>
                    </Col>
                    <Col span={8} key={'s_8'}>
                        <Form.Item label={"调色时间"}>
                            {getFieldDecorator(`S_MIXED_DATE`, {initialValue: !isNvl(currentSelectMonth)?[moment(currentSelectMonth, 'YYYY-MM-DD HH:mm'),this.lastmonthday()]:null})(<RangePicker locale={locale}  />)}
                        </Form.Item>
                    </Col>

                    <Col span={4} style={{ textAlign: 'left',marginTop:'10px' }} key={'s_9'}>
                        <Button type="primary" htmlType="submit" >
                            查询
                        </Button>
                        <Button style={{ marginLeft: 8 }} onClick={this.handleReset} >
                            重置
                        </Button>
                        {/*<a style={{ marginLeft: 8, fontSize: 12 }} onClick={this.toggle}>*/}
                        {/*    Collapse <Icon type={this.state.expand ? 'up' : 'down'} />*/}
                        {/*</a>*/}
                    </Col>
                </Row>
            </Form>
        );
    }

}
export default Form.create({name: "advanced_search"})(ROSearchForm);
