import React, { Component } from "react";
import { Form, Row, Col, Input, Button, Select, Icon, DatePicker, message, Radio  } from "antd";
import { formIsNvl, getSearchForm, isManager } from "../utils/G";
import locale from "antd/es/date-picker/locale/zh_CN";
import moment from "moment";
import "moment/locale/zh-cn"

moment.locale("zh-cn");

const { Option } = Select;
const dateFormat = 'YYYY-MM-DD';
const teaday = moment().subtract('1','day').format(dateFormat);


class EfficiencySearchForm extends Component {

    state = {
        expand: false,
        loading: true,
        ismonth: 1
    };
    onChange= e => {
        console.log('radio checked', e.target.value);
        this.props.ismonth( e.target.value);
        this.setState({
            ismonth: e.target.value,
        });
    };

    handleSearch = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (formIsNvl(values)) {
                message.warning("请输入需要查询的值");
                return;
            }
            let json ={};
            if(this.state.ismonth==1) {
                json=getSearchForm(values, {
                    TYPE: "YYYY-MM"
                });
            }else{
                json = getSearchForm(values, {
                    TYPE: "YYYY"
                });
            }
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
    getSelectedOptions() {
        const children = this.props.vendors.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    getSelectedPaintOptions() {
        const children = this.props.paint.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }

    adjusterchange = (value) =>  {
        this.props.form.setFieldsValue({
            'MECHANIC_ID': ''
        });

    }

    paintchange = (value) => {
        this.props.form.setFieldsValue({
            'PAINT_MAKER_ID': ''
        });
    }

    getSelectedMechanicOptions() {
        const children = this.props.adjuster.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
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
                            rules: [
                                {
                                    required: false,
                                    message: 'Input something!',
                                },
                            ]
                        })(<Input placeholder="placeholder"/>)}
                    </Form.Item>

                </Col>
            )
        }
        return children;
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        const rangeConfig = [{type: 'array', required: true, message: 'Please select time!' }];
        const { datevalue } =this.props;
        return (
            <Form className="ant-advanced-search-form" onSubmit={this.handleSearch}>
                <Row gutter={24} style={{padding: '5px'}}>
                    <Col span={4} key={'s_11'}>
                        <Radio.Group onChange={this.onChange} value={this.state.ismonth} size="small" style={{marginTop: '7px'}}>
                            <Radio value={1} >按月统计</Radio>
                            <Radio value={2} >按年统计</Radio>
                        </Radio.Group>
                    </Col>
                    <Col span={6} key={'s_1'}>
                        <Form.Item label={"请选择图表的时间"} >
                            {getFieldDecorator(`TYPE`, {initialValue: moment(datevalue, 'YYYY-MM-DD')})(<DatePicker locale={locale}  size="small" />)}
                        </Form.Item>
                    </Col>
                    {isManager ?
                        (<Col span={6} key={'s_2'}>
                        <Form.Item label={"门店"} key={'s_3'} >
                            {getFieldDecorator(`PROV_SID`, {
                                rules:[
                                    {
                                        required: false,
                                        message: 'Input something!'
                                    }
                                ]
                            })(<Select  key={'s_c'} style={{ width: 150 }} allowClear={true} size="small" >{this.getSelectedOptions()}</Select>)}
                        </Form.Item>
                    </Col>):null}
                    </Row>
                    <Row gutter={24} style={{paddingLeft: '5px',paddingRight:'5px',paddingBottom:'5px'}}>
                        <Col span={4} key={'s_9'}>
                        <Form.Item label={"调漆员"} key={'s_4'} >
                            {getFieldDecorator(`PAINT_MAKER_ID`, {
                                rules:[
                                    {
                                        required: false,
                                        message: 'Input something!'
                                    }
                                ]
                            })(<Select style={{ width: 120 }} key={'s_6'} allowClear={true}  onChange={this.adjusterchange} size="small" >{this.getSelectedMechanicOptions()}</Select>)}
                        </Form.Item>
                        </Col>
                        <Col span={4} key={'s_10'}>
                        <Form.Item label={"油漆员"} key={'s_5'} >
                            {getFieldDecorator(`MECHANIC_ID`, {
                                rules:[
                                    {
                                        required: false,
                                        message: 'Input something!'
                                    }
                                ]
                            })(<Select style={{ width: 120 }} key={'s_7'} allowClear={true} onChange={this.paintchange} size="small" >{this.getSelectedPaintOptions()}</Select>)}
                        </Form.Item>
                        </Col>
                    <Col span={8}  key={'s_8'} style={{marginTop: '7px'}}>
                        <Button type="primary" htmlType="submit" size="small">
                            查询
                        </Button>
                        <Button style={{ marginLeft: 5 }} onClick={this.handleReset} size="small">
                            重置
                        </Button>
                    </Col>
                    </Row>
            </Form>
        );
    }

}
export default Form.create({name: "advanced_search"})(EfficiencySearchForm);
