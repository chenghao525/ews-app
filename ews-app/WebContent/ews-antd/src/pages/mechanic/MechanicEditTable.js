import {Button, Divider,Table,Popconfirm, Form, Icon } from 'antd';
import { Resizable } from 'react-resizable';
import React from "react";
import { connect } from "react-redux";
import MechanicModel from "../../component/mechanicComponents/MechanicModel"
import MechanicSearchForm from "../../component/mechanicComponents/MechanicSearchForm"
import {vendorsAction} from "../../store/actions/generalActions";
import {getmechanicInfoList,mechanicWorkAction, mechanicSexAction,mechanicGradeAction} from "../../store/actions/mechanicInfoActions";
import {getMechanicDelete} from "../../services/mechanicInfoServices"
import '../../css/MechanicEditTable.css';

const ResizeableTitle = props => {
    const { onResize, width, ...restProps } = props;

    if (!width) {
        return <th {...restProps} />;
    }

    return (
        <Resizable
            width={width}
            height={0}
            onResize={onResize}
            draggableOpts={{ enableUserSelectHack: false }}
        >
            <th {...restProps} />
        </Resizable>
    );
};

const mapStateToProps = state => {
    return {
        data: state.mechanicInfo.data,
        total: state.mechanicInfo.total,
        pagination: state.mechanicInfo.pagination,
        loading: state.mechanicInfo.loading,
        vendors: state.base.vendors,
        mechanicWork: state.base.mechanicWork,
        mechanicSex: state.base.mechanicSex,
        mechanicGrade: state.base.mechanicGrade,
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        load: (params = {}, panigation = {}, sorter = {}) => {
            dispatch(getmechanicInfoList(params, panigation, sorter));
        },
        loadMechanics: () => {
            dispatch(vendorsAction({}));
        },
        loadMechanicsWork: () => {
            dispatch(mechanicWorkAction({"infotype":"50"}));
        },
        loadMechanicSex: () => {
          dispatch(mechanicSexAction({"infotype":"2"}));
        },
        loadMechanicGrade: ()  => {
            dispatch(mechanicGradeAction({"infotype":"52"}));
        },
    }
};

class MechanicEditable extends React.Component {
        state = {
            data: [], pagination: {
                pageSize: 10,
                current: parseInt(window.location.hash.slice(1), 0) || 1,
            },
            loading: false, newRowValues: {}, okClicked: false, type: '',editingSID: '',
            columns : [
                {
                    title: 'Number',
                    dataIndex: 'number',
                    key: 'number',
                    width: 200,
                    render: (text, record, index) => (
                        <span>{10*((this.state.pagination.current?this.state.pagination.current:1)-1)+index+1}</span>
                    ),
                },
                {
                    title: '姓名',
                    dataIndex: 'MECHANIC_NAME',
                    key: 'MECHANIC_NAME',
                    width: 200,
                },
                {
                    title: '门店',
                    dataIndex: 'PROV_NAME',
                    key: 'PROV_NAME',
                    width: 300,

                },
                {
                    title: '技师岗位',
                    dataIndex: 'MECHANIC_WORK_NAME',
                    key: 'MECHANIC_WORK_NAME',
                    width: 250,
                },
                {
                    title: '电话',
                    dataIndex: 'TEL',
                    key: 'TEL',
                    width: 250,
                },
                {
                    title: '操作',
                    dataIndex: 'operation',
                    width: 300,
                    render: (text, record, index) => (
                        <span>
                        <a href="javascript:;" onClick={() => this.handleEdit(record, index)}><Icon type="edit" style={{marginRight:'3px'}} />edit</a>
                        <Divider type="vertical"/>
                        <Popconfirm title="确认删除?" onConfirm={() => {
                            this.handleDelete(record);
                            this.setState({data:this.props.data});
                        }}>
                            <a><Icon type="minus-circle" style={{marginRight:'3px'}}/>Delete</a>
                        </Popconfirm>
                    </span>
                    ),
                }
            ],
        };

    constructor(props) {
        super(props);
        this.addRow = this.addRow.bind(this);
    }

    onRef = (ref) => {
        this.child = ref
    }

    components = {
        header: {
            cell: ResizeableTitle,
        },
    };

    cancel = () => {
        this.setState({ editingKey: '' });
    };

    handleEdit(record, key) {
        this.child.showModel();
        this.setState({ editingKey: key, type: 'edit',editingSID: record.SID});
        this.props.load({},this.state.pagination,{});
    }

    addRow(){
        this.child.showModel();
        this.setState({editingKey: undefined, type:'add',});
    };

    dataUpdated = (newData) => {
        if(newData==='updated'){
            this.props.load({},this.state.pagination,{});
        }
    };

    searchFromForm = (values) => {
        this.props.load(values);
    };


    componentDidMount() {
        this.props.load();
        this.props.loadMechanics();
        this.props.loadMechanicsWork();
        this.props.loadMechanicSex();
        this.props.loadMechanicGrade();
    }

    handleTableChange = (pagination, filters, sorter, currentPageData) => {
        this.props.load({}, pagination, sorter);
        this.setState({data:currentPageData});
    };

    handleResize = index => (e, { size }) => {
        this.setState(({ columns }) => {
            const nextColumns = [...columns];
            nextColumns[index] = {
                ...nextColumns[index],
                width: size.width,
            };
            return { columns: nextColumns };
        });
    };

    handleDelete= (args) => {
        args.type='del';
        getMechanicDelete(args).then(res=>{
            if(this.props.data.length===1){
                this.setState((prevState)=>(
                {pagination:{current:prevState.pagination.current-1}}
            ))}
            this.props.load({},this.state.pagination,{});
        })
    };

    reloadTable = () =>{
        this.setState({pagination:{current:1}},()=>{
            this.props.load({},this.state.pagination,{});
        })
    }


    render() {
        const {data, total, loading, pagination, vendors,mechanicWork,mechanicGrade,mechanicSex } = this.props;
        const mechanicSelectedOptions = {vendors:vendors,mechanicWork:mechanicWork,mechanicGrade:mechanicGrade,
            mechanicSex:mechanicSex};
        const columns = this.state.columns.map((col, index) => ({
            ...col,
            onHeaderCell: column => ({
                width: column.width,
                onResize: this.handleResize(index),
            }),
        }));

        return (
            <div>
                <MechanicSearchForm search={this.searchFromForm} vendors={vendors||[]} mechanicWork={mechanicWork||[]}
                                    reloadTable={this.reloadTable}/>
                <Button onClick={this.addRow} type='primary' style={{margin:10}} size="small"><Icon type="plus-square" />新增技师</Button>
                <MechanicModel editingSID={this.state.editingSID} dataUpdated = {this.dataUpdated}
                               mechanicSelectedOptions={mechanicSelectedOptions||{}} dataFromRow={data[this.state.editingKey]}
                               type={this.state.type} onRef={this.onRef}/>

                <Table
                    bordered
                    rowKey={record=>record.SID}
                    components={this.components}
                    dataSource={data||[]}
                    loading={loading}
                    columns={columns}
                    pagination={{
                        total: total,
                        current:　this.state.pagination.current,
                        onChange: (page, pageSize)=>{
                            this.setState({
                                pagination:{current: page},
                                    },()=>{
                                        window.location.hash = `#${page}`;
                                    })
                            }
                    }}
                    onChange = { this.handleTableChange }
                    size="small"
                />
            </div>
        );
    }
}
MechanicEditable=Form.create()(MechanicEditable);

export default connect(mapStateToProps, mapDispatchToProps)(MechanicEditable);