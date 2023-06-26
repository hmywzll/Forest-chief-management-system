create table patrol
(
    id               char(20)      not null comment 'ID'
        primary key,
    patrol_person_id char(20)      not null comment '巡护人id',
    end_time         datetime      null comment '结束时间                ',
    patrol_duration  float         null comment '巡护时长                ',
    patrol_mileage   float         null comment '巡护里程                ',
    patrol_content   text          null comment '巡护报告                ',
    start_time       datetime      not null comment '开始时间',
    patrol_imgs      char(200)     null comment '巡护图片组  限制5张图片 ',
    is_delete        int           not null comment '逻辑删除 0存在，1删除',
    create_time      datetime      not null comment '创建时间',
    create_user      char(20)      not null comment '创建人',
    update_time      datetime      not null comment '修改时间',
    update_user      char(20)      not null comment '修改人',
    patrol_area      text          not null comment '巡护区域',
    patrol_status    int default 0 not null comment '巡护状态 0进行中 1已完成'
)
    comment '巡护';

INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1669175727542333441', '1', '2023-06-15 10:51:50', 29552, 2, 'A点巡护完成', '2023-06-15 10:51:20', 'patrol1686797504491,patrol1686797507441', 0, '2023-06-15 10:51:20', '1', '2023-06-15 10:51:50', '1', 'A点', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1669179310740434945', '1669148993342238721', '2023-06-15 11:05:49', 15232, 12, '巡护完成', '2023-06-15 11:05:34', 'patrol1686798346521', 0, '2023-06-15 11:05:34', '1', '2023-06-15 11:05:49', '1', 'b点巡护', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1669179427497275393', '1669148993342238721', '2023-06-15 11:06:30', 27926, 3, 'c点巡护完成', '2023-06-15 11:06:02', 'patrol1686798346521,patrol1686798387052', 0, '2023-06-15 11:06:02', '1', '2023-06-15 11:06:30', '1', 'c点巡护', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1669179647094255617', '1669148993342238721', '2023-06-15 11:07:16', 22255, 5, 'n点巡护完成', '2023-06-15 11:06:54', 'patrol1686798346521,patrol1686798431638', 0, '2023-06-15 11:06:54', '1', '2023-06-15 11:07:16', '1', 'n点', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1670692667813310466', '1', '2023-06-19 15:19:32', 25061, 12, 'c点巡护完成', '2023-06-19 15:19:07', 'patrol1687159166184,patrol1687159170223', 0, '2023-06-19 15:19:07', '1', '2023-06-19 15:19:32', '1', 'c点', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1670692863389511681', '1', '2023-06-19 16:04:43', 2690010, 12, 'cwqjwqjkd', '2023-06-19 15:19:53', 'patrol1687161876374,patrol1687161880498', 0, '2023-06-19 15:19:53', '1', '2023-06-19 16:04:43', '1', 'd点', 1);
INSERT INTO forest_chief_management_system.patrol (id, patrol_person_id, end_time, patrol_duration, patrol_mileage, patrol_content, start_time, patrol_imgs, is_delete, create_time, create_user, update_time, update_user, patrol_area, patrol_status) VALUES ('1670704171568640001', '1', null, null, null, null, '2023-06-19 16:04:49', null, 0, '2023-06-19 16:04:49', '1', '2023-06-19 16:04:49', '1', 'wqdwqdwq', 0);
