import React, { Component } from 'react';
import { Modal } from 'antd';
import WrappedAddNewMechanicForm from './MechanicTablePopForm';
import {getMechanicAdjustment} from '../../services/mechanicInfoServices';


class MechanicModel extends Component {
   state = {
       ModalText: 'Content of the modal',
       visible: false,
       confirmLoading: false,
   };

   showModel = () =>{
       this.setState({visible:true});
   };

    //点击对话框OK按钮触发的事件
    handleOk = (e) => {
        const update='updated';
        if(this.formRef.handleSubmit()!=="has err") {
            const rowValues = this.formRef.getItemsValue();
            if (this.props.type === 'edit') {
                rowValues.type = 'edit';
                rowValues.SID = this.props.editingSID;
            } else if (this.props.type === 'add') {
                rowValues.type = 'add';
            }
            getMechanicAdjustment(rowValues).then((res)=> {
                this.props.dataUpdated(update);
                this.setState({visible:false});
                this.formRef.handleCancel();
            })
        }
        else{
            this.setState({visible: true});
        }
    };

    //点击对话框Cancel按钮触发的事件
    handleCancel = () => {
        this.formRef.handleCancel();
        this.setState({
            visible: false,
        });
    };

    componentDidMount(){
        this.props.onRef(this);
    }

    render() {
        const {visible} = this.state;
        return (
            <div>
                <Modal title="新增/编辑技师"
                       visible={visible}
                       onOk={this.handleOk}
                       onCancel={this.handleCancel}
                       okText="确认"
                       cancelText="取消"
                ><WrappedAddNewMechanicForm mechanicSelectedOptions={this.props.mechanicSelectedOptions||{}}
                                            wrappedComponentRef={(form) => this.formRef = form}
                                            dataFromRow={this.props.dataFromRow||{}}/>
                </Modal>
            </div>
        );
    }
}

export default MechanicModel;

