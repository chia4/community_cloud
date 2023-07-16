use unit_room;

insert into unit (unit_number, caretaker, cleaner)
values ('10', '杨衍嘉', '刘晨昕'),
       ('11', '徐佳驰', '龚睿'),
       ('12', '邵婕', '李卓勋'),
       ('13', '郑智', '黄俊卿'),
       ('14', '庞昕宇', '李云婷'),
       ('15', '李瀚明', '陈新');

insert into room (unit_number, room_number, house_holder)
values ('10', '108', 'a'),
       ('10', '109', 'c'),
       ('10', '208', null),
       ('10', '209', 'i'),
       ('10', '210', null),
       ('11', '108', 'k'),
       ('11', '109', 'n'),
       ('11', '307', null),
       ('11', '308', null),
       ('13', '107', 't'),
       ('13', '108', 'v'),
       ('13', '208', 'y'),
       ('13', '209', null),
       ('13', '210', null),
       ('13', '211', 'z'),
       ('14', '207', null),
       ('15', '307', '3'),
       ('15', '407', '6'),
       ('15', '408', null),
       ('15', '409', '9');

use residence;

insert into residence (username, password, name, unit_number, room_number)
values ('a', 'a', '傅浩悦', '10', '108'),
       ('b', 'b', '廖俊豪', '10', '109'),
       ('c', 'c', '母卓尔', '10', '109'),
       ('d', 'd', '马傲', '10', '109'),
       ('e', 'e', '赵之星', '10', '208'),
       ('f', 'f', '黄致昊', '10', '208'),
       ('g', 'g', '赵恩良', '10', '209'),
       ('h', 'h', '侯哲', '10', '209'),
       ('i', 'i', '石循磊', '10', '209'),
       ('j', 'j', '李子程', '10', '210'),
       ('k', 'k', '李奇钟', '11', '108'),
       ('l', 'l', '唐瑞祥', '11', '108'),
       ('m', 'm', '李秉泽', '11', '108'),
       ('n', 'n', '陈轩', '11', '109'),
       ('o', 'o', '龚明丽', '11', '109'),
       ('p', 'p', '何立舟', '11', '307'),
       ('q', 'q', '胡世强', '11', '307'),
       ('r', 'r', '潘奕霏', '11', '307'),
       ('s', 's', '任芷潞', '11', '308'),
       ('t', 't', '李怡豪', '13', '107'),
       ('u', 'u', '赵子珺', '13', '107'),
       ('v', 'v', '赵曜', '13', '108'),
       ('w', 'w', '邢成博', '13', '108'),
       ('x', 'x', '杨紫璇', '13', '108'),
       ('y', 'y', '向宣', '13', '208'),
       ('z', 'z', '关雯月', '13', '211'),
       ('1', '1', '张碧珊', '13', '211'),
       ('2', '2', '姜博乾', '15', '307'),
       ('3', '3', '郑赢', '15', '307'),
       ('4', '4', '王修齐', '15', '407'),
       ('5', '5', '张笑涵', '15', '407'),
       ('6', '6', '邓亚强', '15', '407'),
       ('7', '7', '苗乘', '15', '407'),
       ('8', '8', '田中原', '15', '409'),
       ('9', '9', '刘澜', '15', '409');

use announcement_message_board;

insert into announcement (timestamp, content)
values (1676121755,
        '物业费是提高服务的基础和各项开支的来源，当小区业主按时足额交纳物业费后，小区的绿化、治安、公共设备等维护工作才能得以顺利开展，从而使我们的生活品质得到有力保障。您完成缴费义务的行为，就是对我们携手共建美好家园的最大支持！'),
       (1676190749,
        '接供电公司通知，因高压线路需要检修，定于明后两日整个小区将会停电。请广大业主/住户互相告知，并做好相应的停电准备。对您的生活造成不便之处，敬请见谅！');

insert into message_board (username, timestamp, content, feedback)
VALUES ('f', 1676132684,
        '望相关部门走访民生，与开发商调解降低停车费，让合法的公民权益得到保障，实现真正的人民当家作主，实现真正的互惠互利，共同双赢！',
        '您好！您向留言板反映的诉求已收到。业主反映停车费高的问题我社区工作人员从2021年收房到现在已组织多次协调。我社区工作人员将业主的要求已转交给物业和销售方，让其向公司反映。'),
       ('k', 1676146289,
        '请问下，35周岁单身青年，有购房后离婚产权变更给女方，是否能申请大龄青年房的资格，如果有的话，要如何申请？',
        '1.农村户籍 2.自己与前妻均未申请宅基地 3.户籍所在村允许审批宅基地'),
       ('3', 1676158257,
        '11-109做为公职人员带头违反业主共同决议的事项，自私安装大玻璃和小区全体业主决议方案严重不符，不仅引起小区不和谐，品质影响，严重损害全体业主共同利益。找物业，物业表示也多次和业主沟通，一直拒绝整改，态度恶劣。',
        null),
       ('y', 1676159450,
        '已经2023年了，黄泥浆色的自来水还在继续，每天都在喝。恳请有关部门关注一下，街道的居民想喝点干净的自来水不过分吧',
        '为配合轨道交通施工需要，对供水环网西线管线进行割接施工。第一阶段施工完毕后进行了管道高强度冲洗，管网水的压力、流向发生变化，引起了局部区域水质波动。给您生活带来不便，敬请谅解。');
