--初始化系统权限表
delete from t_right_roledic
/
delete from t_right_roleright
/
delete from t_right_userright
/
delete from t_right_userrole
/
commit;

-------------------------------------------------------------------------------------------
--赋予admin最大权限
--删除原有的 00000001 角色
delete from t_right_roledic t where t.roleid='00000001'
/

--插入新的超级系统管理员/超级业务管理员角色
insert into t_right_roledic(roleid,rolename,sid,type) values('00000001','超级系统管理员',1,1)
/

--删除角色权限表中的超级系统管理员/超级业务管理员权限信息
delete from t_right_roleright t where t.roleid='00000001'
/

--在角色权限表中加入超级系统管理员/超级业务管理员的所有权限信息
insert into t_right_roleright(roleid,rightid,sid) select '00000001',t.rightid,t.sid from t_right_rightdic t where t.isparent='N'
/
--赋予SID=0的用户所有系统角色
delete from t_right_userrole t where t.empid=0 and roleid='00000001'
/
insert into t_right_userrole(roleid,empid,crtuser,uptuser,sid) values ('00000001','0','0','0',1)
/
commit;