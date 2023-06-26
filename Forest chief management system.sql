create
database forest_chief_management_system;

use
forest_chief_management_system;

create table account
(
    id                      char(20) primary key comment 'ID',

    user                    char(20) not null unique comment '账号',
    password                char(20) not null comment '密码',
    username                char(20) not null comment '名字',
    position                int      not null comment '职位',
    personal_information_id char(20) not null unique comment '个人信息表 ID',

    is_delete               int      not null comment '逻辑删除 0存在，1删除',
    create_time             DateTime not null comment '创建时间',
    create_user             char(20) not null comment '创建人',
    update_time             DateTime not null comment '修改时间',
    update_user             char(20) not null comment '修改人'
)comment '账号';

create table personal_information
(
    id            char(20) primary key comment 'ID',

    username      char(20)      not null comment '名字',
    sex           char(20)      not null comment '性别                        ',
    personal_img  char(40)      not null comment '图片名称                      ',
    woods_id      char(20)      not null comment '林地 ID                     ',
    position      int           not null comment '职位      0管理员，1林长，2监管员，3巡护员',
    entry_time    DateTime      not null comment '入职时间                      ',
    status        int default 0 not null comment '状态      0空闲，1工作中          ',
    phone_number  char(20)      not null unique comment '电话号码                      ',
    superior_id   char(20)      not null comment '上级 id   个人信息 id           ',
    superior_name char(20)      not null comment '上级名字                      ',

    is_delete     int           not null comment '逻辑删除 0存在，1删除',
    create_time   DateTime      not null comment '创建时间',
    create_user   char(20)      not null comment '创建人',
    update_time   DateTime      not null comment '修改时间',
    update_user   char(20)      not null comment '修改人'
)comment '个人信息';

create table woods
(
    id            char(20) primary key comment 'ID',

    woods_name    char(20) not null unique comment '林地名称',
    woods_number  char(20) not null unique comment '林地编号',
    woods_geojson text     not null comment '林地geojson',

    is_delete     int      not null comment '逻辑删除',
    create_time   DateTime not null comment '创建时间',
    create_user   char(20) not null comment '创建人',
    update_time   DateTime not null comment '修改时间',
    update_user   char(20) not null comment '修改人'
)comment '林地';

create table label
(
    id           char(20) primary key comment 'ID',

    label_name   char(20) not null unique comment '标注名称',
    label_img    char(40) not null comment '标注图标               ',
    label_type   int      not null comment '类型      0普通标注，1事件标注',
    label_remark text     not null comment '描述                 ',

    is_delete    int      not null comment '逻辑删除 0存在，1删除',
    create_time  DateTime not null comment '创建时间',
    create_user  char(20) not null comment '创建人',
    update_time  DateTime not null comment '修改时间',
    update_user  char(20) not null comment '修改人'
)comment '标注';

create table label_example_geojson
(
    id            char(20) primary key comment 'ID',

    label_id      char(20) not null comment '标注id',
    label_geojson text     not null comment '标注位置',
    example_id    char(20) not null comment '标注实例id',

    is_delete     int      not null comment '逻辑删除 0存在，1删除',
    create_time   DateTime not null comment '创建时间',
    create_user   char(20) not null comment '创建人',
    update_time   DateTime not null comment '修改时间',
    update_user   char(20) not null comment '修改人'
)comment '标注位置';

create table label_example
(
    id                    char(20) primary key comment 'ID',

    label_id              char(20)      not null comment '标注id',
    label_name            char(20)      not null comment '标注名称',
    example_status        int default 0 not null comment '标注实例状态  0待完成，1已完成',
    example_content       text          not null comment '标注内容',
    example_imgs          char(200) comment '实例图片组    限制5张图片',
    label_personal_name   char(20)      not null comment '标注人姓名             ',
    deal_with_person_id   char(20) comment '处理人               ',
    deal_with_person_name char(20) comment '处理人姓名             ',
    deal_with_content     text comment '处理结果              ',
    deal_with_imgs        char(200) comment '处理图片      限制5张图片',
    deal_with_time        DateTime comment '处理时间              ',

    is_delete             int           not null comment '逻辑删除 0存在，1删除',
    create_time           DateTime      not null comment '创建时间',
    create_user           char(20)      not null comment '创建人',
    update_time           DateTime      not null comment '修改时间',
    update_user           char(20)      not null comment '修改人'
)comment '标注实例';

create table patrol
(
    id               char(20) primary key comment 'ID',

    patrol_person_id char(20) not null comment '巡护人id',
    personal_name    char(20) not null comment '巡护人名字              ',
    end_time         DateTime comment '结束时间                ',
    patrol_duration  float comment '巡护时长                ',
    patrol_mileage   float comment '巡护里程                ',
    patrol_content   text comment '巡护报告                ',
    patrol_imgs      char(200) comment '巡护图片组  限制5张图片 ',

    is_delete        int      not null comment '逻辑删除 0存在，1删除',
    create_time      DateTime not null comment '创建时间',
    create_user      char(20) not null comment '创建人',
    update_time      DateTime not null comment '修改时间',
    update_user      char(20) not null comment '修改人'
)comment '巡护';

create table task
(
    id                    char(20) primary key comment 'ID',

    send_person_id        char(20)      not null comment '发起人',
    send_person_name      char(20)      not null comment '发起人名字                ',
    task_type             int           not null comment '任务类型      0普通，1事件    ',
    label_id              char(20) comment '事件id                 ',
    task_deadline         int           not null comment '任务期限                 ',
    task_status           int default 0 not null comment '任务状态      0待完成，1已完成 ',
    task_content          text          not null comment '任务内容                 ',
    reception_person_id   char(20)      not null comment '接收人                  ',
    reception_person_name char(20)      not null comment '接收人名字                ',
    task_report           text comment '任务报告                 ',
    task_imgs             char(20) comment '任务图片组    限制5张图片      ',
    task_completion       DateTime comment '任务完成时间               ',

    is_delete             int           not null comment '逻辑删除 0存在，1删除',
    create_time           DateTime      not null comment '创建时间',
    create_user           char(20)      not null comment '创建人',
    update_time           DateTime      not null comment '修改时间',
    update_user           char(20)      not null comment '修改人'
)comment '任务';

create table inform
(
    id             char(20) primary key comment 'ID',

    send_person_id char(20) not null comment '发起人',
    inform_title   char(60) not null comment '通知标题  限制60字                    ',
    inform_content text     not null comment '通知内容                           ',
    woods_id       char(20) comment '林地id    通知范围                   ',
    position       int      not null comment '职位      通知范围，0管理员，1林长，2监管员，3巡护员',

    is_delete      int      not null comment '逻辑删除 0存在，1删除',
    create_time    DateTime not null comment '创建时间',
    create_user    char(20) not null comment '创建人',
    update_time    DateTime not null comment '修改时间',
    update_user    char(20) not null comment '修改人'
)comment '通知';
