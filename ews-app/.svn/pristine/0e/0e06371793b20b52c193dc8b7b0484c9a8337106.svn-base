import React, { Component } from "react";
import {Table, message, Modal, Button, Row, Col, Card } from "antd";



class DisplayCards extends Component{

    render(){
        const {cardsData}=this.props;
        const costs=cardsData.details?cardsData.details.cost:"";
        console.log("Costs: ", this.props);

        return(
            <div style={{ background: '#ECECEC', padding: '10px' }}>
                <Row gutter={16}>
                    <div>
                        <h3>some text</h3>
                        <Col span={3}>
                            <Card title="施工钣面（面）" bordered={false}>
                                Card content
                            </Card>
                        </Col>
                        <Col span={3}>
                            <Card title="施工台次（台）" bordered={false}>
                                Card content
                            </Card>
                        </Col>
                    </div>
                    <div>
                        <h3>some text22</h3>
                        <Col span={3}>
                            <Card title="色母" bordered={false}>
                                {costs.COLOR_MASTER_COST}
                            </Card>
                        </Col>
                        <Col span={3}>
                            <Card title="清漆" bordered={false}>
                                {costs.CLEAR_COAT_COST}
                            </Card>
                        </Col>
                        <Col span={3}>
                            <Card title="辅料" bordered={false}>
                                {costs.SUNDRY_COST}
                            </Card>
                        </Col>
                        <Col span={3}>
                            <Card title="耗材" bordered={false}>
                                {costs.OTHER_COST}
                            </Card>
                        </Col>
                        <Col span={3}>
                            <Card title="总额" bordered={false}>
                                {costs.TOTAL_COST}
                            </Card>
                        </Col>
                    </div>
                </Row>
            </div>
        )
    }

}

export default DisplayCards;