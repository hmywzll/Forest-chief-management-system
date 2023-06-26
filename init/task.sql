create table task
(
    id                  char(20)      not null comment 'ID'
        primary key,
    send_person_id      char(20)      not null comment '发起人',
    task_type           int           not null comment '任务类型      0普通，1事件    ',
    label_id            char(20)      null comment '事件id                 ',
    task_deadline       date          not null comment '任务期限                 ',
    task_status         int default 0 not null comment '任务状态      0待完成，1已完成 ',
    task_content        text          not null comment '任务内容                 ',
    reception_person_id char(20)      not null comment '接收人                  ',
    task_report         text          null comment '任务报告                 ',
    task_imgs           char(200)     null comment '任务图片组    限制5张图片      ',
    task_completion     datetime      null comment '任务完成时间               ',
    is_delete           int           not null comment '逻辑删除 0存在，1删除',
    create_time         datetime      not null comment '创建时间',
    create_user         char(20)      not null comment '创建人',
    update_time         datetime      not null comment '修改时间',
    update_user         char(20)      not null comment '修改人'
)
    comment '任务';

INSERT INTO forest_chief_management_system.task (id, send_person_id, task_type, label_id, task_deadline, task_status, task_content, reception_person_id, task_report, task_imgs, task_completion, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669176045818703874', '1', 0, '0', '2023-06-22', 1, '完成今日巡护', '1669148793932443649', '巡护完成', 'task1687137824632', '2023-06-19 09:23:46', 0, '2023-06-15 10:52:36', '1', '2023-06-19 09:23:46', '1');
INSERT INTO forest_chief_management_system.task (id, send_person_id, task_type, label_id, task_deadline, task_status, task_content, reception_person_id, task_report, task_imgs, task_completion, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669176148172304385', '1', 1, '1669150616189759490', '2023-06-15', 0, '完成事件任务', '1669148993342238721', null, null, null, 0, '2023-06-15 10:53:00', '1', '2023-06-15 10:53:00', '1');
INSERT INTO forest_chief_management_system.task (id, send_person_id, task_type, label_id, task_deadline, task_status, task_content, reception_person_id, task_report, task_imgs, task_completion, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669176237510979585', '1', 1, '1669150616189759490', '2023-06-23', 0, '完成事件任务', '1669149162100060161', null, null, null, 0, '2023-06-15 10:53:21', '1', '2023-06-15 10:53:21', '1');
INSERT INTO forest_chief_management_system.task (id, send_person_id, task_type, label_id, task_deadline, task_status, task_content, reception_person_id, task_report, task_imgs, task_completion, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669541314168733697', '1', 1, '1669178385804128257', '2023-06-23', 0, '2023616', '16691568815991316494', null, null, null, 0, '2023-06-16 11:04:03', '1', '2023-06-16 11:04:03', '1');
INSERT INTO forest_chief_management_system.task (id, send_person_id, task_type, label_id, task_deadline, task_status, task_content, reception_person_id, task_report, task_imgs, task_completion, is_delete, create_time, create_user, update_time, update_user) VALUES ('1670436113037934594', '1', 1, '1669150616189759490', '2023-06-14', 0, 'fdgdgf', '1669148793932443649', null, null, null, 0, '2023-06-18 22:19:39', '1', '2023-06-18 22:19:39', '1');

