import { Layout, Breadcrumb } from "antd";
import React, { Component } from "react";
import { connect } from "react-redux";
import HeaderMenus from "./HeaderMenus";
import AppRouter from "../router/AppRouter";
import { withRouter } from "react-router-dom";
import logo from "../assets/ews-logo.png";

const { Header, Content } = Layout;


class AppLayout extends Component {

    render() {
        return (
            <Layout className="layout" style={{ height: '100%'}}>
                <Header className="header-box">
                    <img className="logo" src={logo} />
                    <HeaderMenus/>
                </Header>
                <Content style={{ padding: '0 10px'}}>
                    <Breadcrumb style={{ margin: '10px 0' }}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>List</Breadcrumb.Item>
                        <Breadcrumb.Item>App</Breadcrumb.Item>
                    </Breadcrumb>
                    <div style={{ background: '#fff', height: '95%' }}>
                        <AppRouter/>
                    </div>
                </Content>
            </Layout>
        );
    }

}

export default withRouter(connect()(AppLayout));
