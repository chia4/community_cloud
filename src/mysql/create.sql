create schema unit_room;

use unit_room;

create table unit
(
    unit_number char(10) not null comment '楼栋号',
    caretaker   char(20) null comment '楼管',
    cleaner     char(20) null comment '保洁',
    constraint unit_pk
        primary key (unit_number)
)
    comment '楼栋表';

create table room
(
    unit_number  char(10) not null comment '楼栋号',
    room_number  char(10) not null comment '门牌号',
    house_holder char(20) null comment '户主',
    constraint room_pk
        primary key (unit_number, room_number),
    constraint room_unit_unit_number_fk
        foreign key (unit_number) references unit (unit_number) on delete cascade
)
    comment '房屋表';

create schema residence;

use residence;

create table residence
(
    username    char(20) not null comment '登录用户名',
    password    char(20) not null,
    name        char(20) not null comment '姓名',
    unit_number char(10) not null comment '楼栋号',
    room_number char(10) not null comment '门牌号',
    constraint residence_pk
        primary key (username)
)
    comment '住户表';

create schema announcement_message_board;

use announcement_message_board;

create table message_board
(
    id        int auto_increment,
    username  char(20)  not null,
    timestamp int       not null comment '发帖时间戳',
    content   char(255) not null comment '内容',
    feedback  char(255) null comment '反馈',
    constraint message_board_pk
        primary key (id)
)
    comment '留言板';

create table message_board_like
(
    id       int      not null,
    username char(20) not null,
    constraint message_board_like_pk
        primary key (id, username),
    constraint message_board_like_message_board_id_fk
        foreign key (id) references message_board (id) on delete cascade

)
    comment '留言板点赞记录';

create table announcement
(
    id        int auto_increment,
    timestamp int       not null comment '发公告时间戳',
    content   char(255) not null comment '内容',
    constraint announcement_pk
        primary key (id)
)
    comment '公告';

create table announcement_like
(
    id       int      not null,
    username char(20) not null,
    constraint announcement_like_pk
        primary key (id, username),
    constraint announcement_like_announcement_id_fk
        foreign key (id) references announcement (id) on delete cascade

)
    comment '公告点赞记录';
