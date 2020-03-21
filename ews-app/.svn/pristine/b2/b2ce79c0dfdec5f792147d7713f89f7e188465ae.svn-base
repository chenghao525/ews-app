import React, { Component } from "react";
import { connect } from "react-redux";
import { Menu, Icon } from "antd";
import { Link } from "react-router-dom";

const { SubMenu } = Menu;

const mapStateToProps = (state) => {
    return {
        list: state.base.list
    }
}
const mapDispatchToProps = (dispatch) => {

    return {
    }
}

class HeaderMenus extends Component {

    menuClicked = (item, key, keyPath, domEvent) => {
        console.log(item, key)
    }

    render() {
        let { list } = this.props;
        list = list || [];
        return (
            <Menu theme="dark" mode="horizontal" onClick={this.menuClicked}>
                { list.map((val, index) => {
                    return (
                        <SubMenu key={index} title={
                            <span className="submenu-title-wrapper">
                                {index===0?
                                <Icon type="pie-chart"/>:(index===1?<Icon type="solution"/>:
                                        (index===2?<Icon type="user"/>:
                                            (index===3?<Icon type="branches"/>:
                                                (index===4?<Icon type="setting"/>:null))))
                                }
                                {val.name}
                            </span>
                        }>
                            { val.subs.map((sub, idx) => {
                                return (
                                    <Menu.Item key={idx}>
                                        <Link to={sub.to}>{sub.name}</Link>
                                    </Menu.Item>
                                )
                            })
                            }
                        </SubMenu>
                    )
                })

                }

            </Menu>
        );
    }

}
export default connect(mapStateToProps, mapDispatchToProps)(HeaderMenus);

