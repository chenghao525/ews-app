import React, { Component } from "react";
import { Button, Input, Icon, Spin, Form, message } from "antd";
import { connect } from "react-redux";
import { withRouter } from 'react-router-dom';
import { formIsNvl, isNvl } from "../utils/G";
import { login } from "../services/generalServices";
import { menuListAction } from "../store/actions/generalActions";
import { loginInfo } from "../utils/storage";
import logo from "../assets/ews-logo.png";

class Login extends Component {

    constructor(props) {
        super(props);

        const login = loginInfo();
        const defaultUser = isNvl(login) ? "" : login.EMPID;

        this.state = {
            defaultUser,
        }
    }
    loginClicked = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (formIsNvl(values)) {
                message.warning("请输入对应的值");
                return;
            }
            values.AUTH_CODE = "1234";
            login(values).then(rsp => {
                const code = rsp.code;
                if (code == 0) {
                    const details = rsp.details;
                    const menuList = details.MENU;
                    delete details.MENU;
                    loginInfo(details); // 存储到localStorage

                    this.props.setMenu(menuList);
                    this.props.history.push("/main");
                } else  {
                    message.warning(rsp.message);
                }

            }).catch(err => {
               message.error(err);
            });
        });
    }
    render() {
        const { defaultUser, loading } = this.state;
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="loginBox">
                <div className="loginHeader">
                    <img src={logo}/>
                </div>
                <div className="loginMiddle">
                        <div className="loginInfo">
                            <div style={{ margin: "20px 0"}}>
                                <span style={{color: "#ed784c", fontWeight: "bold", marginRight: "20px", fontSize: "32px"}}>用户登录</span>
                                <span style={{color: "#fff", fontWeight: "bold", fontSize: "22px"}}>login</span>
                            </div>
                            <Form onSubmit={this.loginClicked} style={{ marginTop: "20px"}}>
                                <Form.Item>
                                    {getFieldDecorator('USER', {
                                        rules: [
                                            {required: true, message: "请输入您的用户名"}
                                        ],
                                        initialValue: defaultUser
                                    })(<Input placeholder="请输入您的用户名"
                                              style={{ height: "36px",
                                                  backgroundColor: "transparent !important"}}
                                              allowClear/>)}
                                </Form.Item>
                                <Form.Item>
                                    {getFieldDecorator('PWD', {
                                        rules: [
                                            {required: false, message: "请输入账号"}
                                        ],
                                        initialValue: "070809"
                                    })(<Input.Password placeholder="密码6~18位"
                                                       style={{ height: "36px",
                                                           backgroundColor: "transparent !important"}}
                                                       allowClear/>)}

                                </Form.Item>
                                <Button type="primary" size="large"
                                        htmlType="submit"
                                        style={{width: '40%', border: 'none',
                                            height: "40px", backgroundColor: "#e47a24"}}>登录</Button>
                            </Form>
                        </div>
                </div>
                <div className="loginFooter">
                    <p>推荐使用 谷歌浏览器</p>
                    <p>服务供应商：上海聚车信息有限公司</p>
                </div>
            </div>
        );
    }

}
const mapStateToProps = (state) => {

    return state;

}

export default connect(mapStateToProps, dispath => {

    return {
        setMenu: (args) => {
            dispath(menuListAction(args))
        }
    }

})(Form.create({name: "login_form"})(withRouter(Login)));
