--��ʼ��ϵͳȨ�ޱ�
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
--����admin���Ȩ��
--ɾ��ԭ�е� 00000001 ��ɫ
delete from t_right_roledic t where t.roleid='00000001'
/

--�����µĳ���ϵͳ����Ա/����ҵ�����Ա��ɫ
insert into t_right_roledic(roleid,rolename,sid,type) values('00000001','����ϵͳ����Ա',1,1)
/

--ɾ����ɫȨ�ޱ��еĳ���ϵͳ����Ա/����ҵ�����ԱȨ����Ϣ
delete from t_right_roleright t where t.roleid='00000001'
/

--�ڽ�ɫȨ�ޱ��м��볬��ϵͳ����Ա/����ҵ�����Ա������Ȩ����Ϣ
insert into t_right_roleright(roleid,rightid,sid) select '00000001',t.rightid,t.sid from t_right_rightdic t where t.isparent='N'
/
--����SID=0���û�����ϵͳ��ɫ
delete from t_right_userrole t where t.empid=0 and roleid='00000001'
/
insert into t_right_userrole(roleid,empid,crtuser,uptuser,sid) values ('00000001','0','0','0',1)
/
commit;