
insert into project
(id, defaultproject, name, versions) 
values (null, true, 'default project', '0.1');

insert into tip.user
(id, username, password, accessLevel, current_project_id) 
values (null, 'abangkis', 'aa', 0, 1) 

update user set current_project_id = 1;

update user set access

update taskitem set taskstatus = 0;

update taskitem set votedown = 0;

update taskitem set voteup = vote;

alter table taskItem drop column vote;