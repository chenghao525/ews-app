import React, { Component } from "react";
import {Form, InputNumber, Select} from "antd";

const { Option } = Select;

class AddCostForm extends Component{


    getFormValues = () =>{    //自定义方法，用来传递数据（需要在父组件中调用获取数据）
        const values= this.props.form.getFieldsValue();       //getFieldsValue：获取一组输入控件的值，如不传入参数，则获取全部组件的值
        return values;
    };

    getVendorsSelectedOptions=()=>{

        const children = this.props.vendors.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }


    getMonthSelectedOptions=()=>{
        const children = this.props.monthsArray.map(it => {
            return (
                <Option value={it.id} key={it.id}>{it.text}</Option>
            );
        });
        return children;
    }


    handleCancel = (e) => {
        this.props.form.resetFields(e)
    };

    handleSubmit = (e) => {
        let result = "";
        this.props.form.validateFields((err, values) => {
            if (err) {
                result = "has err";
            }
            else{
                result = values;
            }
        });
        return result;
    };


    render() {
        const { getFieldDecorator } = this.props.form;

        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };
        return (
            <Form {...formItemLayout} onSubmit={this.handleSubmit}>
                <Form.Item label="记录时间" hasFeedback>
                    {getFieldDecorator('RPT_MONTH', {
                        initialValue:"",
                        rules: [
                            {
                                required: true,
                                message:"请选择记录时间！"
                            },
                        ],
                    })(<Select>{this.getMonthSelectedOptions()}</Select>)}
                </Form.Item>
                <Form.Item label="门店" hasFeedback>
                    {getFieldDecorator('PROV_SID', {
                        initialValue: "",
                        rules: [
                            {
                                required: true,
                                message:"请选择门店！"
                            },
                        ],
                    })(<Select>{this.getVendorsSelectedOptions()}</Select>)}
                </Form.Item>
                <Form.Item label="色母" >
                    {getFieldDecorator('COLOR_MASTER_COST', {
                        initialValue: 0,
                        rules: [
                            {
                                required: false,
                            },
                        ],
                    })(<InputNumber min={0} max={99999}/>)}
                </Form.Item>
                <Form.Item label="清漆" >
                    {getFieldDecorator('CLEAR_COAT_COST', {
                        initialValue: 0,
                        rules: [
                            {
                                required: false,
                            },
                        ],
                    })(<InputNumber min={0} max={99999}/>)}
                </Form.Item>
                <Form.Item label="辅料">
                    {getFieldDecorator('SUNDRY_COST', {
                        initialValue: 0,
                        rules: [
                            {
                                required: false,
                            },
                        ],
                    })(<InputNumber min={0} max={99999}/>)}
                </Form.Item>
                <Form.Item label="耗材">
                    {getFieldDecorator('OTHER_COST', {
                        initialValue: 0,
                        rules: [
                            {
                                required: false,
                            },
                        ],
                    })(<InputNumber min={0} max={99999}/>)}
                </Form.Item>
                <Form.Item label="总额">
                    {getFieldDecorator('TOTAL_COST', {
                        initialValue: 0,
                        rules: [
                            {
                                required: false,
                            },
                        ],
                    })(<InputNumber min={0} max={99999}/>)}
                </Form.Item>
            </Form>
        );
    }
}


const WrappedAddCostForm = Form.create({ name: 'addCost' })(AddCostForm);
export default WrappedAddCostForm