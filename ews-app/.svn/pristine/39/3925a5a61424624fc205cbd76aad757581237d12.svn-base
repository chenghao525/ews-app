import React, { Component } from "react";
import { Form, Row, Col, Input, Button, Select, Icon, DatePicker, message } from "antd";
import { formIsNvl, getSearchForm } from "../../utils/G";
import locale from "antd/es/date-picker/locale/zh_CN";
import moment from "moment";
import "moment/locale/zh-cn"

moment.locale("zh-cn");

const { Option } = Select;


class HomeSearchForm extends Component {

    state = {
        expand: false,
        loading: true,
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

    getSelectedOptions() {
        const children = this.props.monthsArray.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }


    render() {
        const { getFieldDecorator } = this.props.form;
        //const rangeConfig = [{type: 'array', required: true, message: 'Please select time!' }];
        return (
            <Form className="ant-advanced-search-form home-search-form">
                <Row gutter={24} style={{padding: '10px 5px'}}>
                    <Col span={6} key={'s_1'}>
                        <Form.Item label={"调色时间"} key={'p_s'}>
                            {getFieldDecorator(`S_MIXED_DATE`, {
                                rules:[
                                    {
                                        required: false,
                                        message: 'Input something!'
                                    }
                                ]
                            })(<Select style={{ width: 120 }} key={'s_c'} allowClear={true}>{this.getSelectedOptions()}</Select>)}
                        </Form.Item>
                    </Col>

                    <Col span={8}  key={'s_4'} style={{ marginTop: 5 }}>
                        <Button type="primary" onClick={this.handleSearch}>
                            查询
                        </Button>
                    </Col>
                </Row>
            </Form>
        );
    }

}
export default Form.create({name: "advanced_search"})(HomeSearchForm);
