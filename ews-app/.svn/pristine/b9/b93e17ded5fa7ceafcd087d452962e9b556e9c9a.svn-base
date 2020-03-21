import React, {Component} from 'React';
import { Form, Input, Button, Row, Col} from "antd";
import { formIsNvl, getSearchForm, isManager} from "../utils/G";
import {DatePicker, message, Select} from "antd/lib/index";
import moment from "moment";
import "moment/locale/zh-cn"

import {connect} from 'react-redux';

moment.locale("zh-cn");

const { Option } = Select;
const { RangePicker } = DatePicker;

const mapStateToProps = state => {
    return {
    }
}

const mapDispatchToProps = dispatch => {
    return {
    }
}

class SingleFormItem extends Component {
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

    }
    handleReset = () => {
        this.props.form.resetFields();
    }
    constructor(props) {
        super(props);
    }
    componentDidMount() {
    }
    getSelectedOptions() {
        const children = this.props.vendors.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }
    render() {
        const { getFieldDecorator } = this.props.form;
        const { vendors } = this.props;
        return (
            <Form className="ant-advanced-search-form" onSubmit={this.handleSearch}>
                <Row>
                    {isManager?
                    (<Col span={4}>
                        <Form.Item label={"门店"} key={'p_s'}>
                            {getFieldDecorator(`S_PROV_SID`, {
                                rules: [
                                    {
                                        required: false,
                                        message: 'Input something!'
                                    }
                                ]
                            })(<Select style={{width: 120}} key={'s_c'} allowClear={true} >{this.getSelectedOptions()}</Select>)}
                        </Form.Item>
                    </Col>):null}
                    <Col span={4}>
                    <Form.Item label={"工单号"}>
                        {getFieldDecorator(`S_RO_NUMBER`, {
                            rules:[
                                {
                                    required: false,
                                    message: 'Input something!'
                                }
                            ]
                        })(<Input placeholder="工单号" allowClear/>)}
                    </Form.Item>
                    </Col>
                    <Col span={4} style={{ marginLeft: '16px' }} key={'s_4'}>
                        <Button type="primary" htmlType="submit">
                            查询
                        </Button>
                        <Button style={{ marginLeft: 8 }} onClick={this.handleReset}>
                            重置
                        </Button>
                    </Col>
                </Row>

            </Form>
        );
    }
}

export default Form.create({name:"single_formitem"})(SingleFormItem);

// SingleFormItem=Form.create()(SingleFormItem);
// export default connect(mapStateToProps,mapDispatchToProps)(SingleFormItem);
