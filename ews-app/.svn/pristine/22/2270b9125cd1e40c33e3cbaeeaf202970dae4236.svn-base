import React, {Component} from "react";
import {Modal} from "antd";
import WrappedAddCostForm from "./AddCostForm";
import {addCostRecord} from "../../services/workStatisticService"


class AddCostModel extends Component {
    state = {
        ModalText: 'Content of the modal',
        visible: false,
        confirmLoading: false,
    };



    //点击对话框OK按钮触发的事件
    handleOk = (e) => {
        if(this.formRef.handleSubmit()!=='has err'){
            const formValues = this.formRef.getFormValues();
            console.log("datafrom Form:",formValues);
            addCostRecord(formValues).then((res)=>
                    {
                        this.setState({visible: false,});
                        this.props.dataUpdated("updated",formValues);
                        this.formRef.handleCancel();
                    }
                )
        }else{
            this.setState({
                visible: true,
            });
        }
    };


    //点击对话框Cancel按钮触发的事件
    handleCancel = () => {
        this.formRef.handleCancel();
        this.setState({
            visible: false,
        });
    };


    shouldComponentUpdate(nextProps, nextState){
        if(nextProps.visible !== this.state.visible || nextState.visible !== this.state.visible){
            this.state.visible=nextProps.visible;
            return true
        }
        return false;
    }

    showModel = () =>{
        this.setState({visible:true});
    };

    componentDidMount(){
        this.props.onRef(this);
    }

    render() {
        const {visible} = this.state;

        return (
            <div>
                <Modal title="添加单钣面月成本"
                       visible={visible}
                       onOk={this.handleOk}
                       onCancel={this.handleCancel}
                       okText="确认"
                       cancelText="取消"
                >
                    <WrappedAddCostForm {...this.props} wrappedComponentRef={(form) => this.formRef = form }/>
                </Modal>
            </div>
        );
    }
}

export default AddCostModel