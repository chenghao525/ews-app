import React, { Component } from 'react';
import { Modal } from 'antd';
import NewRepairOrderList from "../../pages/newrepairorders/newRepairOrderList" ;


class WorkStatisticModel extends Component {
    state = {
        ModalText: 'Content of the modal',
        visible: this.props.visible,
        confirmLoading: false,
    };

    //点击对话框Cancel按钮触发的事件
    handleCancel = () => {
        this.props.handleCancel();
    };

    render() {
        const {visible,currentSelectMonth} = this.props;
        const worktitle=currentSelectMonth+"工单列表";
        return (
            <div>
                <Modal title={worktitle}
                       visible={visible}
                       footer={null}
                       onCancel={this.handleCancel}
                       width={1300}
                       className="workmodalstyle"
                >
                    <NewRepairOrderList  currentSelectMonth={currentSelectMonth} />
                </Modal>
            </div>
        );
    }
}

export default WorkStatisticModel;
