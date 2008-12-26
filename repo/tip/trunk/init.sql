
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

-- here

insert into category (name, project_id, description)
values ('default category', 1, 'default category creted by initial data');

update taskitem set category_id = 1
where category_id = null;

insert into userprofile
(abletovote, accesslevel, totalpoint, user_id, project_id)
select user.ableToVote, user.accessLevel, user.point, user.id, user.current_project_id
from user

-- updating project for local, should be covered by above insert into command
-- thin only to fix my local mistake
update userprofile up, user u
set up.project_id = u. current_project_id
where up.user_id = u.id

-- script ini gak berlaku kalo sudah multiple project
update userprofile up, user u
set u.current_profile_id = up.id
where up.user_id = u.id

ALTER TABLE `tip`.`user` DROP COLUMN `ableToVote`,
 DROP COLUMN `accessLevel`,
 DROP COLUMN `profileId`,
 DROP COLUMN `point`;

