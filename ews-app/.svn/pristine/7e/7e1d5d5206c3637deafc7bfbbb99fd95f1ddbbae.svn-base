import React, { Component } from "react";
import { Row, Col} from "antd";


class RepairDetailsOne extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const { details } = this.props;
        return (
            <div>
                         <Row className="gutter-example">
                            <Col span={4} className="gutter-box">EWS工单号：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.EWS_RO_CODE}</Col>
                            <Col span={4} className="gutter-box">门店工单号：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.RO_NUMBER}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">门店：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.PROV_SID}</Col>
                            <Col span={4} className="gutter-box">调色时间：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.MIXED_DATE}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">车牌号：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.VEHICLE_LICENSE}</Col>
                            <Col span={4} className="gutter-box">车辆品牌：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.CAR_BRAND}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">色号：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.COLOR_CODE}</Col>
                            <Col span={4} className="gutter-box">车身颜色：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.COLOR_NAME}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">技师：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.MECHANIC_NAME}</Col>
                            <Col span={4} className="gutter-box">调漆技师：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.PAINT_MAKER}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">色母总消耗(g)：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.WEIGHT_TOTAL}</Col>
                            <Col span={4} className="gutter-box">工单状态：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.REWORK_TYPE}</Col>
                        </Row>
                        <Row className="gutter-example">
                            <Col span={4} className="gutter-box">原工单号：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.ORIGINAL_RO_NUMBER}</Col>
                            <Col span={4} className="gutter-box">钣面数：</Col>
                            <Col span={8} className="gutter-boxtwo">{details.SHEET_METAL_COUNT}</Col>
                        </Row>

            </div>
        );
    }
}

export default RepairDetailsOne;
