import React, {Component} from 'React';
import { Form, Input, Icon, Button,Select} from 'antd';
import {connect} from "react-redux";

let id = 0;
const { Option } = Select;

class DynamicFieldSet extends React.Component {
    remove = k => {
        const { form } = this.props;
        const keys = form.getFieldValue('keys');
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

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                const { keys, names } = values;
                console.log('Received values of form: ', values);
                console.log('Merged values:', keys.map(key => names[key]));
            }
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

    render() {
        const { getFieldDecorator, getFieldValue } = this.props.form;
        getFieldDecorator('keys', { initialValue: [] });
        const keys = getFieldValue('keys');
        const formItems = keys.map((k, index) => (
            <Form.Item
                label={index === 0 ? '自定义项1' : '自定义项'+ ++index }
                required={false}
                key={k}
            >
                {getFieldDecorator(`names[${k}]`, {
                    validateTrigger: ['onChange', 'onBlur'],
                    rules: [
                        {
                            required: true,
                            whitespace: true,
                            message: "Please input describe name or delete this field.",
                        },
                    ],
                })(<Input placeholder="描述" style={{ width: '30%', marginRight: 8 }} />)}
                <Select placeholder="数量" style={{width: 100, height: 28}} key={'s_gdst'} allowClear={true} >{this.getSelectedNumberOptions()}</Select>
                {keys.length > 1 ? (
                    <Icon
                        className="dynamic-delete-button"
                        type="minus-circle-o"
                        onClick={() => this.remove(k)}
                    />
                ) : null}
            </Form.Item>
        ));
        return (
            <Form onSubmit={this.handleSubmit}>
                {formItems}
                <Form.Item>
                    <Button type="dashed" onClick={this.add} style={{ width: '60%' }}>
                        <Icon type="plus" /> Add field
                    </Button>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

const WrappedDynamicFieldSet = Form.create({ name: 'dynamic_form_item' })(DynamicFieldSet);
export default connect()(WrappedDynamicFieldSet);