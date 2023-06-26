create table inform
(
    id             char(20) not null comment 'ID'
        primary key,
    send_person_id char(20) not null comment '发起人',
    inform_title   char(60) not null comment '通知标题  限制60字                    ',
    inform_content text     not null comment '通知内容                           ',
    woods_id       char(20) null comment '林地id    通知范围                   ',
    position       int      not null comment '职位      通知范围，0管理员，1林长，2监管员，3巡护员',
    is_delete      int      not null comment '逻辑删除 0存在，1删除',
    create_time    datetime not null comment '创建时间',
    create_user    char(20) not null comment '创建人',
    update_time    datetime not null comment '修改时间',
    update_user    char(20) not null comment '修改人'
)
    comment '通知';

INSERT INTO forest_chief_management_system.inform (id, send_person_id, inform_title, inform_content, woods_id, position, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669176771747868674', '1', '森林防火宣传', '森林是人类生存的绿色屏障，农业的保姆、水利的源泉，具有经济、社会、生态三大效益和整体文明水平的最高荣誉称号，人们的日常生活和生产都与森林资源息息相关，火灾是森林的大敌会使艰辛培育了几十年的森林毁于一旦，严重危及国家和人民的生命财产安全。

“从我做起，从现在做起”

森林防火是全民应尽的义务

让我们牢牢记住教训

从我做起，从现在做起

争当森林防火的楷模

消防安全知识普及 ●●

爱护森林是我们每个人义不容辞的责任。为了让明天的生活更加美好，大家都要保护森林，消灭森林的天敌——森林火灾，争做森林防火好公民。

怎样才能让大家都了解森林防火知识?这就需要每一个人都当森林防火宣传员，积极参加各种森林防火宣传活动，向亲属、邻居和周围的群众宣传森林防火法律法规以及防火灭火的基本知识，弘扬森林防火的好人好事好思想，这样才能有效提高全民的森林防火意识。根据森林火灾燃烧部位、性质和危害程度，可将森林火灾分为地表火、树冠火和地下火三大类。地表火是最常见的一种林火，指火从地表面地被物以及近地面根系、幼树、树干下皮层开始燃烧，并沿地表面蔓延的火灾。树冠火是指地表火遇到强风或遇到针叶幼树群、枯立木或低垂树枝，烧至树冠，并沿树冠顺风扩展。地下火一般容易发生在干旱季节的针叶林内，蔓延速度慢，温度高，持续时间长，破坏力极强。

!

森林防火“十不要”

不要携带火种进山', '1669129198026682370', 3, 0, '2023-06-15 10:55:29', '1', '2023-06-15 10:55:29', '1');
INSERT INTO forest_chief_management_system.inform (id, send_person_id, inform_title, inform_content, woods_id, position, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669177177232207873', '1', '森林防火宣传2', '当前我县已进入夏森林防火高风险期，随着进入林区旅游避暑人员的增多，野外用火点多面广，致使火源管控难度增大，森林防火形势严峻。

为认真贯彻《关于全面加强新形势下森林草原防灭火工作的意见》（中办发〔2022〕60号）精神，按照市局及县委县政府工作安排，奉节县林业局从5月31日起，分8个片区，对全县33个乡镇（街道）、370个有林村（社）依次开展森林防火业务培训。奉节县乡镇（街道）分管领导、应急办和农服中心负责人、林业业务骨干及村（社区）主要负责人共计600余人参训。', '1669129198026682370', 0, 0, '2023-06-15 10:57:06', '1', '2023-06-15 10:57:06', '1');
INSERT INTO forest_chief_management_system.inform (id, send_person_id, inform_title, inform_content, woods_id, position, is_delete, create_time, create_user, update_time, update_user) VALUES ('1669541071041708033', '1', '防火', '设立设立累死了', '1669130411958923265', 0, 0, '2023-06-16 11:03:05', '1', '2023-06-16 11:03:05', '1');
