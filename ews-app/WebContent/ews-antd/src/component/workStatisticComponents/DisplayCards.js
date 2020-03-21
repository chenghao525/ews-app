import React, { Component } from "react";
import {Row, Col, Card } from "antd";



class DisplayCards extends Component{

    openModal=()=>{
        this.props.openModal();
    }

    render(){
        const {cardsData,date}=this.props;
        const myDate=date.S_MIXED_DATE?date.S_MIXED_DATE:date;
        const costs=cardsData.details?(cardsData.details.cost?cardsData.details.cost:{}):{};
        const shfTotal=cardsData.details?(cardsData.details.SHF_TOTAL?cardsData.details.SHF_TOTAL:0):0;
        const total=cardsData.details?(cardsData.details.TOTAL?cardsData.details.TOTAL:0):0;
        const dateArray=myDate.split('-');

        const colStyle = {
            //width: "50%",
            //paddingBottom: "16px",
            paddingRight:'10px',
            width:'13%'
        };
        const collStyle = {
            paddingRight:'10px',
            marginLeft:'30px',
            width:'13%'
        };
        const cardStyleo = {
            color:'#fc7944',
            fontSize: '18px',
            textAlign: 'center'
        };
        const cardStylel = {
            color:'#007AFF',
            fontSize: '18px',
            textAlign: 'center'
        };
        const cardStyler = {
            color:'#FF0000',
            fontSize: '18px',
            textAlign: 'center'
        };



        return(
            <div style={{ background: '#ECECEC', padding: '10px' }}>
                <Row>
                    <Col  span={6} style={{paddingLeft:'35px'}}>
                        <h3 >{dateArray[0]}年{dateArray[1]}月</h3>
                    </Col>
                    <Col span={18} style={{paddingLeft:'105px'}}>
                        <h3>{dateArray[0]}年{dateArray[1]}月单钣面成本(元)</h3>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        {/*<div style={leftDivStyle}>*/}
                        {/*<h3 >{dateArray[0]}年{dateArray[1]}月</h3>*/}
                        <Col style={collStyle} span={3} >
                            <Card title="施工钣面（面）" bordered={false} style={cardStyleo} headStyle={{color:'#fc7944'}} onClick={this.openModal}>
                                {shfTotal}
                            </Card>
                        </Col>
                        <Col style={colStyle} span={3}  >
                            <Card title="施工台次（台）" bordered={false} style={cardStyleo} headStyle={{color:'#fc7944'}} onClick={this.openModal}>
                                {total}
                            </Card>
                        </Col>
                        {/*</div>*/}
                    </Col>
                    <Col >
                        {/*<div style={rightDivStyle}>*/}
                        {/*<h3>{dateArray[0]}年{dateArray[1]}月单钣面成本(元)</h3>*/}
                        <Col style={{marginLeft:'50px',paddingRight:'10px'}} span={3}>
                            <Card title="色母" bordered={false} style={cardStylel} headStyle={{color:'#007AFF'}}>
                                {costs.COLOR_MASTER_COST?costs.COLOR_MASTER_COST:"0"}
                            </Card>
                        </Col>
                        <Col style={colStyle} span={3}>
                            <Card title="清漆" bordered={false} style={cardStylel} headStyle={{color:'#007AFF'}}>
                                {costs.CLEAR_COAT_COST?costs.CLEAR_COAT_COST:"0"}
                            </Card>
                        </Col>
                        <Col style={colStyle} span={3}>
                            <Card title="辅料" bordered={false} style={cardStylel} headStyle={{color:'#007AFF'}}>
                                {costs.SUNDRY_COST?costs.SUNDRY_COST:"0"}
                            </Card>
                        </Col>
                        <Col style={colStyle} span={3}>
                            <Card title="耗材" bordered={false} style={cardStylel} headStyle={{color:'#007AFF'}}>
                                {costs.OTHER_COST?costs.OTHER_COST:"0"}
                            </Card>
                        </Col>
                        <Col style={colStyle} span={3}>
                            <Card title="总额" bordered={false} style={cardStyler} headStyle={{color:'#FF0000'}}>
                                {costs.TOTAL_COST?costs.TOTAL_COST:"0"}
                            </Card>
                        </Col>
                        {/*</div>*/}
                    </Col>
                </Row>
            </div>
        )
    }

}

export default DisplayCards;