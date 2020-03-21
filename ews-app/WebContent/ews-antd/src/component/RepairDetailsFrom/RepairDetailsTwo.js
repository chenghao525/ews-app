import React, { Component } from "react";
import { Table, Row} from "antd";

class RepairDetailsTwo extends Component {

    constructor(props) {
        super(props);
    }

    components=()=>{
        this.props.components();
    }

    render() {
        const { columnssm, datasm,loading } = this.props;
        console.log("props:!!!",this.props);
        return (
            <div>
                {/*<Row className="gutter-example" style={{backgroundColor:"#f7f7f7",padding:5}}>色母详情</Row>*/}
                <Table bordered className="table-example"
                       columns={columnssm}
                       components={this.components}
                       dataSource={datasm||[]}
                       loading={loading}
                       pagination={ false }
                />

            </div>
        );
    }
}

export default RepairDetailsTwo;

