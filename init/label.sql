create table label
(
    id           char(20) not null comment 'ID'
        primary key,
    label_name   char(20) not null comment '标注名称',
    label_img    char(40) not null comment '标注图标               ',
    label_type   int      not null comment '类型      0普通标注，1事件标注',
    label_remark text     not null comment '描述                 ',
    is_delete    int      not null comment '逻辑删除 0存在，1删除',
    create_time  datetime not null comment '创建时间',
    create_user  char(20) not null comment '创建人',
    update_time  datetime not null comment '修改时间',
    update_user  char(20) not null comment '修改人',
    constraint label_name
        unique (label_name)
)
    comment '标注';

INSERT INTO forest_chief_management_system.label (id, label_name, label_img, label_type, label_remark, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669143564021231617', '滥伐', 'label1686789791848', 1, '有滥伐迹象', 0, '2023-06-15 08:43:32', '1', '2023-06-15 08:43:32', '1');
INSERT INTO forest_chief_management_system.label (id, label_name, label_img, label_type, label_remark, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669149557824253953', '安全屋', 'label1686791199396', 0, '紧急避险', 0, '2023-06-15 09:07:21', '1', '2023-06-15 09:07:21', '1');
INSERT INTO forest_chief_management_system.label (id, label_name, label_img, label_type, label_remark, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669149636631031810', '火灾', 'label1686791247940', 1, '发生火灾', 0, '2023-06-15 09:07:39', '1', '2023-06-15 09:07:39', '1');
INSERT INTO forest_chief_management_system.label (id, label_name, label_img, label_type, label_remark, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669149760581103617', '泥石流', 'label1686791269437', 1, '发生泥石流', 0, '2023-06-15 09:08:09', '1', '2023-06-15 09:08:09', '1');
INSERT INTO forest_chief_management_system.label (id, label_name, label_img, label_type, label_remark, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669149932283326466', '地滑', 'label1686791294467', 0, '地面湿滑', 0, '2023-06-15 09:08:50', '1', '2023-06-15 09:08:50', '1');
