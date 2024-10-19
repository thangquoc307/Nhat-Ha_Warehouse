drop database `warehouse`;
create database `warehouse`;
use `warehouse`;

insert into `warehouses` (`name`) values
    ('Kho hàng hóa giao SSS HCM');
insert into `salers` (`name`) values 
    ('Linhttm'), ('Hantm'), ('HaiLA'),
    ('ĐucLĐ BU3'), ('ToTKV'), ('Anh Đức');
insert into `manufacturers` (`name`) values
    ('other'), ('Aruba'), ('Ruijie'),
    ('Cisco'), ('Juniper Networks'),
    ('Huawei'), ('TP-Link'), ('Ubiquiti Networks'),
    ('MikroTik'), ('Extreme Networks'),
    ('NETGEAR'), ('Dell EMC Networking'),
    ('Hewlett Packard Enterprise'), ('D-Link');
insert into `outbounds` (note, partner, release_date, so, sale_id, warehouse_id) values
    ('Đã có PXK', 'Tân Thanh', '2024-07-09', 'SO15075', 2, 1),
    ('Đã có PXK', 'ELITE', '2024-07-15', 'SO14985', 2, 1),
    ('Đã có PXK', 'Happy Home', '2024-07-22', 'SO13910', 1, 1),
    ('Đã có PXK', 'Sinh Hùng', '2024-07-25', 'SO14807', 2, 1),
    ('Đã có PXK', 'Thái Nguyễn', '2024-07-26', 'SO15080', 2, 1),
    ('Đã có PXK', 'TNS', '2024-07-30', 'SO15332', 2, 1),
    ('Đã có PXK', 'HUA YUE', '2024-07-30', 'SO15330', 3, 1),
    ('Thiếu PXK', 'Chính Nhân', '2024-08-01', 'SO15359', 4, 1),
    ('Thiếu PXK, Đã ra hóa đơn', 'Mai Nguyễn', '2024-08-06', 'SO15375', 5, 1),
    ('Đã có PXK', 'BÌNH MINH', '2024-08-06', 'SO15372', 3, 1),
    ('Đã có PXK', 'Khang Yến', '2024-08-07', 'SO15388', 2, 1),
    ('Đã có PXK', 'Innotel', '2024-08-09', 'SO15276', 2, 1),
    ('Đã có PXK', 'Sunshine', '2024-08-09', 'SO15418', 2, 1),
    ('Đã có PXK', 'SUNTEL', '2024-08-09', 'SO15390', 3, 1),
    ('Đã có PXK', 'ROBO', '2024-08-09', 'SO15415', 3, 1),
    ('Đã có PXK', 'SLA', '2024-08-09', 'SO15370', 2, 1),
    ('Đã có PXK', 'NMS', '2024-08-12', 'SO15414', 2, 1),
    ('Đã có PXK', 'ROBO', '2024-08-12', 'SO15437', 3, 1),
    ('Đổi bảo hành cho khách', 'Realtek', '2024-08-12', 'SO15360', 3, 1),
    ('Test bảo hành cho khách', 'Anh Đức', '2024-08-15', NULL, 6, 1),
    ('Đã có PXK', 'T2Q', '2024-08-15', 'SO15446', 3, 1),
    ('Đã có PXK', 'KTVT Vũng Tàu', '2024-08-18', 'SO15507', 3, 1),
    ('Gửi ra SSS HN theo mail của Long', 'Đức LD', '2024-08-24', 'SO15449', 3, 1),
    ('Đã có PXK', 'BIZFONE', '2024-08-24', 'SO15541', 3, 1),
    ('Đã có PXK', 'ROBO', '2024-08-27', 'SO15564', 3, 1),
    ('Đã có PXK', 'LE SATO', '2024-08-28', 'SO15579', 2, 1),
    ('Đã có PXK', 'GM', '2024-08-28', 'SO15580', 3, 1),
    ('Đã có PXK', 'TELECOM', '2024-08-30', 'SO15601', 3, 1),
    ('Đã có PXK', 'Nam Long', '2024-09-04', 'SO13228', 5, 1),
    ('Đã có PXK', 'Bình Minh', '2024-09-04', 'SO15591', 3, 1),
    ('Gửi ra nhập Kho HN', 'X', '2024-09-04', NULL, 3, 1),
    ('Đã có PXK', 'NMS', '2024-09-05', 'SO15619', 2, 1),
    ('Đã có PXK', 'CIRCLE K', '2024-09-04', 'SO15612', 3, 1),
    ('Đã có PXK', 'ĐA PHÚC', '2024-09-09', 'SO15622', 2, 1),
    ('Đã có PXK', 'NMS', '2024-09-09', 'SO15638', 2, 1),
    ('Đã có PXK', 'Nam Long', '2024-09-09', 'SO15634', 2, 1),
    ('Đã có PXK', 'Châu sa', '2024-09-10', 'SO15520', 2, 1),
    ('Đã có PXK', 'PHẠM THANH', '2024-09-10', 'SO15631', 3, 1),
    ('Xuất bảo hành cho khách', NULL, '2024-09-10', NULL, 3, 1),
    ('Đã có PXK', 'SLA', '2024-09-11', 'SO15641', 2, 1),
    ('Đã có PXK', 'NMS', '2024-09-11', 'SO15658', 2, 1),
    ('Xuất bảo hành cho khách', NULL, '2024-09-11', NULL, 3, 1),
    ('Đã có PXK', 'Lạc Việt', '2024-09-12', 'SO15523', 2, 1);
insert into `outbound_items` (count, description, part_number, outbound_id, manufacturer_id) values
    (4 ,'Thiết bị chuyển mạch CBS350 Managed 8-port GE, Ext PS, 2x1G Combo_ CBS350-8T-E-2G-EU', 'CBS350-8T-E-2G-EU', 1, 2),
    (NULL ,'Giá đỡ Aruba X414 1U Universal 4-post RM Kit_J9583B', 'J9583B', 2, 2),
    (NULL ,'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 'RG-AP820-L(V2)', 3, 3),
    (NULL ,'Nguồn Ruijie 150W AC power module_RG-PA150IB-F', 'RG-PA150IB-F', 3, 3),
    (NULL ,'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 'RG-S2915-24GT4MS-P-L', 3, 3),
    (NULL ,'Thiết bị quản lý thiết bị phát sóng không dây Ruijie Next-Gen Wireless Controller_RG-WS6008', 'RG-WS6008', 3, 3),
    (NULL ,'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 'RG-S2915-24GT4MS-P-L', 4, 3),
    (1 ,'Thiết bị chuyển mạch HPE Aruba 6200F 48G CL4 4SFP+740W Swch_JL728A', 'JL728A', 5, 2),
    (1 ,'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 'R8N88A', 5, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 6, 2),
    (NULL ,'Thiết bị thu phát vô tuyến Ruijie RG-AP720-L', 'RG-AP720-L', 7, 3),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 8, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 9, 2),
    (2 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 9, 1),
    (NULL ,'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 10, 3),
    (NULL ,'Thiết bị chuyển mạch Switch Ruijie 10 × 10/100/1000Base-T copper ports with auto-negotiation, 2 × 1GE/2.5GE SFP ports, 125 W PoE power supply_RG-S2915-10GT2MS-P-L', 'RG-S2915-10GT2MS-P-L', 10, 3),
    (NULL ,'Thiết bị chuyển mạch Aruba IOn 1930 24G 4SFP+ 370W Switch_JL684B', 'JL684B', 11, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 12, 2),
    (3 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 12, 1),
    (3 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 13, 1),
    (NULL ,'Thiết bị chuyển mạch CBS350 Managed 24-port GE, 4x10G SFP+_CBS350-24T-4X-EU', 'CBS350-24T-4X-EU', 14, 1),
    (NULL ,'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 15, 3),
    (NULL ,'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE-AF15', 'RG-POE-AF15', 15, 3),
    (NULL ,'Thiết bị chuyển mạch Aruba 6000 24G CL4 4 SFP Switch_R8N87A', 'R8N87A', 16, 2),
    (4 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 17, 1),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 17, 2),
    (NULL ,'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 18, 3),
    (NULL ,'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE-AF15', 'RG-POE-AF15', 18, 3),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 19, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 20, 2),
    (NULL ,'', 'CBS110-24T-EU', 21, 1),
    (NULL ,'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 'RG-S2915-24GT4MS-P-L', 22, 3),
    (NULL ,'Thiết bị chuyển mạch Cisco CBS250 Smart 48-port GE, 4x1G SFP_CBS250-48T-4G-EU', 'CBS250-48T-4G-EU', 23, 4),
    (NULL ,'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 'RG-AP820-L(V2)', 24, 3),
    (NULL ,'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 25, 3),
    (NULL ,'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE-AF15', 'RG-POE-AF15', 25, 3),
    (NULL ,'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 'R8N88A', 26, 2),
    (NULL ,'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE-AF15', 'RG-POE-AF15', 27, 3),
    (NULL ,'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, fixed single AC power supply_RG-S2915-24GT4MS-L', 'RG-S2915-24GT4MS-L', 28, 3),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 29, 2),
    (NULL ,'Thiết bị thu phát vô tuyến Ruijie RG-AP720-L', 'RG-AP720-L', 30, 3),
    (NULL ,'Thiết bị chuyển mạch Aruba Instant On 1930 48G- 4SFP+ Switch_JL685A', 'JL685A', 31, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 32, 2),
    (2 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 32, 1),
    (NULL ,'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 'RG-AP820-L(V2)', 33, 3),
    (NULL ,'Thiết bị chuyển mạch Cisco SB CBS350 Managed 48-port GE, PoE, 4x1G SFP_CBS350-48P-4 G-EU', 'CBS350-48P-4G-EU', 34, 4),
    (7 ,'Dây nguồn HPE PC-AC-EC Continental European/Schuko AC Power Cord_JW118A', 'JW118A', 34, 1),
    (7 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 34, 1),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 34, 2),
    (7 ,'Nguồn AP-AC2-12B 12V/48W AC/DC pwr adptr B_R3K00A', 'R3K00A', 34, 1),
    (3 ,'Dây nguồn HPE PC-AC-EC Continental European/Schuko AC Power Cord_JW118A', 'JW118A', 35, 1),
    (3 ,'Nguồn AP-AC2-12B 12V/48W AC/DC pwr adptr B_R3K00A', 'R3K00A', 35, 1),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 36, 2),
    (1 ,'Khung gắn thiết bị HPE AP-270-MNT-V2 270 Series Mt Kit _JW053A', 'JW053A', 37, 1),
    (11 ,'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 37, 1),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 37, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-565 (RW) Outdoor 11ax AP_R4W43A', 'R4W43A', 37, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-565 (RW) Outdoor 11ax AP_R4W43A', 'R4W43A', 38, 2),
    (NULL ,'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 'RG-S2915-24GT4MS-P-L', 39, 3),
    (NULL ,'Thiết bị chuyển mạch Aruba 6000 24G CL4 4 SFP Switch_R8N87A', 'R8N87A', 40, 2),
    (NULL ,'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 41, 2),
    (NULL ,'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 42, 3),
    (NULL ,'Thiết bị chuyển mạch CBS350 Managed 24-port GE, 4x1G SFP_CBS350-24T-4G-EU', 'CBS350-24T-4G-EU', 43, 1);
insert into `outbound_items_serials` (outbound_item_id, serial) values
    (2, 'TW349001GL'), (2, 'TW349001GG'),
    (3, 'G1SK79C012152'), (3, 'G1SK79C001130'), (3, 'G1SK79C004204'), (3, 'G1SK79C004672'),
    (3, 'G1SK79C01049B'), (3, 'G1SK79C020480'), (3, 'G1SK79C002916'), (3, 'G1SK79C004461'),
    (3, 'G1SK79C007269'), (3, 'G1SK79C013305'), (3, 'G1SK79C020763'), (3, 'G1SK79C00391B'),
    (3, 'G1SK79C007818'), (3, 'G1SK79C008447'), (3, 'G1SK79C008679'), (3, 'G1SK79C00891A'),
    (3, 'G1SK79C003988'), (3, 'G1SK79C008405'), (3, 'G1SK79C008502'), (3, 'G1SK79C008831'),
    (3, 'G1SK79C014757'), (3, 'G1SK79C033191'),
    (4, 'G1SLB3W001268'),
    (5, 'G1SU2SY003010'), (5, 'G1SU2SY002921'),
    (6, 'R572A2317110389'),
    (7, 'G1SU2SY002562'),
    (10, 'CNQDLJ6G2Z'),
    (11, 'G1SPCD108443A'), (11, 'G1SPCD1084486'), (11, 'G1SPCD1085833'), (11, 'G1SPCD1083009'),
    (11, 'G1SPCD1084794'), (11, 'G1SPCD1084609'), (11, 'G1SPCD1085829'), (11, 'G1SPCD108618C'),
    (11, 'G1SPCD1084537'), (11, 'G1SPCD108485C'), (11, 'G1SPCD1058049'), (11, 'G1SPCD1057964'),
    (11, 'G1SPCD1059324'), (11, 'G1SPCD105950A'), (11, 'G1SPCD1057922'), (11, 'G1SPCD1059560'),
    (11, 'G1SPCD1059463'), (11, 'G1SPCD1057871'), (11, 'G1SPCD105933B'), (11, 'G1SPCD105947A'),
    (12, 'CNQVLBMB5M'), (12, 'CNQVLBMB5D'), (12, 'CNQVLBMB5G'), (12, 'CNQVLBMB53'),
    (12, 'CNQVLBMB3J'), (12, 'CNQVLBMB5F'), (12, 'CNQVLBMB4X'), (12, 'CNQVLBMB59'),
    (12, 'CNQVLBMB52'), (12, 'CNQVLBMB56'),
    (13, 'CNQDLJ6G2T'), (13, 'CNQDLJ6GP'),
    (15, 'G1RU8BQ03308A'), (15, 'G1RU8BQ03315C'), (15, 'G1RU8BQ033124'), (15, 'G1RU8BQ033048'),
    (15, 'G1RU8BQ031878'),
    (16, 'G1SU4SL001162'), (16, 'G1SU4SL001310'), (16, 'G1SU4SL00110C'),
    (17, 'CN39LB39WZ'),
    (18, 'CNQDLJ6G16'), (18, 'CNQDLJ6G30'), (18, 'CNQDLJ6F7L'),
    (21, 'FOC2737Y3BN'),
    (22, 'G1RU8BQ030523'),
    (23, 'G1T03GV009561'),
    (24, 'CN34L3LB43'),
    (26, 'CNQDLJ6G33'), (26, 'CNQDLJ6G35'), (26, 'CNQDLJ6D9W'), (26, 'CNQDLJ6G2M'),
    (27, 'G1RU8BQ03311A'), (27, 'G1RU8BQ033069'), (27, 'G1RU8BQ01932A'), (27, 'G1RU8BQ010978'),
    (28, 'G1T03GV009612'), (28, 'G1T03GV009599'), (28, 'G1T03GV009540'), (28, 'G1T03GV00952C'),
    (29, 'CNQVLBMB4K'), (29, 'CNQVLBMB46'),
    (30, 'CNP8KPPFWS'), (30, 'CNQVLBMB4P'), (30, 'CNQVLBMB32'), (30, 'CNQVLBMB4W'),
    (30, 'CNQVLBMB4T'), (30, 'CNR5KPP297'), (30, 'CNQVLBMB4L'), (30, 'CNQVLBMB4J'),
    (30, 'CNQVLBMB4N'), (30, 'CNQVLBMB4H'),
    (31, 'DNI261500F2'),
    (32, 'G1SU2SY002444'), (32, 'G1SU2SY00194A'),
    (33, 'PSZ27081K33'), (33, 'PSZ27081JDN'),
    (34, 'G1SK79C028203'), (34, 'G1SK79C028194'), (34, 'G1SK79C031528'), (34, 'G1SK79C024487'),
    (34, 'G1SK79C023575'), (34, 'G1SK79C031507'),
    (35, 'G1RU8BQ030620'), (35, 'G1RU8BQ031194'),
    (36, 'G1T03GV009696'), (36, 'G1T03GV048030'),
    (37, 'CN34L3M5NC'),
    (38, 'G1T03GV009633'), (38, 'G1T03GV04848A'), (38, 'G1T03GV048220'), (38, 'G1T03GV00949C'),
    (38, 'G1T03GV048503'), (38, 'G1T03GV009654'), (38, 'G1T03GV048410'), (38, 'G1T03GV048448'),
    (38, 'G1T03GV009629'), (38, 'G1T03GV009582'), (38, 'G1T03GV048494'), (38, 'G1T03GV04834B'),
    (38, 'G1T03GV048241'), (38, 'G1T03GV009578'), (38, 'G1T03GV048427'), (38, 'G1T03GV04832A'),
    (38, 'G1T03GV00966B'), (38, 'G1T03GV048452'),
    (39, 'G1SU4SM003977'), (39, 'G1SU4SM005008'),
    (40, 'CNQVLBMB4P'), (40, 'CNQVLBMB32'), (40, 'CNQVLBMB4W'), (40, 'CNQVLBMB4H'),
    (40, 'CNP8KPPFWS'),
    (41, 'G1SPCD1088151'), (41, 'G1SPCD108511A'), (41, 'G1SPCD1084963'),
    (42, 'CN37KPH0LV'), (42, 'CN37KPH06D'), (42, 'CN37KPH0MC'), (42, 'CN37KPH0F3'),
    (42, 'CN37KPH045'),
    (43, 'CNQDLJ6DMN'), (43, 'CNQDLJ6DMK'),
    (45, 'G1SK79C02459B'), (45, 'G1SK79C02426C'),
    (46, 'PSZ27121WXR'),
    (49, 'CNQDLJ6CTW'), (49, 'CNQDLJ6DMJ'), (49, 'CNQDLJ6DMQ'), (49, 'CNQDLJ6DMR'),
    (49, 'CNQDLJ6DMP'), (49, 'CNQDLJ6D8Z'), (49, 'CNQDLJ6DCH'),
    (53, 'CNQDLJ6D8M'), (53, 'CNQDLJ6D8R'), (53, 'CNQDLJ6D8Q'), (53, 'CNQDLJ6CZX'),
    (53, 'CNQDLJ6CTG'), (53, 'CNQDLJ6D8L'), (53, 'CNQDLJ6D88'), (53, 'CNQDLJ6D8N'),
    (53, 'CNQDLJ6D8J'), (53, 'CNQDLJ6D8C'),
    (56, 'CNRSLBM5D8'), (56, 'CNRSLBM5GH'), (56, 'CNRSLBM5GJ'), (56, 'CNRSLBM57S'),
    (56, 'CNRSLBM5GP'), (56, 'CNRSLBM57M'), (56, 'CNRSLBM5GW'), (56, 'CNRSLBM5GR'),
    (56, 'CNRSLBM5GN'), (56, 'CNRSLBM5GQ'), (56, 'CNRSLBM586'),
    (57, 'CNPHKWC2LS'),
    (58, 'CNPHKWC2KB'), (58, 'CNPHKWC2LG'), (58, 'CNPHKWC2KD'), (58, 'CNPHKWC2M1'),
    (59, 'G1SU2SY00196B'),
    (60, 'CN34L3LB83'),
    (61, 'CNPHKD59XS'),
    (62, 'G1RU8BQ031097'), (62, 'G1RU8BQ011240'),
    (63, 'FOC2718Y9NM');
insert into `inbounds` (inbound_code, location_from, note, release_date, warehouse_id) values
    ('Mã nhập kho 1', 'K01_Kho hàng bán3SVN', '', '2024-08-07', 1),
    ('Mã nhập kho 2', 'K01_Kho hàng bán3SVN', '', '2024-08-22', 1),
    ('Mã nhập kho 3', 'K01_Kho hàng bán3SVN', '', '2024-08-22', 1),
    ('Mã nhập kho 4', 'K01_Kho hàng bán3SVN', 'Lạc việt SO15523', '2024-08-22', 1),
    ('Mã nhập kho 5', 'K01_Kho hàng bán3SVN', '', '2024-08-24', 1),
    ('Mã nhập kho 6', 'K01_Kho hàng bán3SVN', '', '2024-08-24', 1),
    ('Mã nhập kho 7', 'K01_Kho hàng bán3SVN', '', '2024-08-26', 1),
    ('Mã nhập kho 8', 'K01_Kho hàng bán3SVN', '', '2024-08-26', 1),
    ('Mã nhập kho 9', 'K01_Kho hàng bán3SVN', '', '2024-08-28', 1),
    ('Mã nhập kho 10', 'K01_Kho hàng bán3SVN', '', '2024-09-05', 1),
    ('Mã nhập kho 11', 'K01_Kho hàng bán3SVN', '', '2024-09-05', 1),
    ('Mã nhập kho 12', 'K01_Kho hàng bán3SVN', 'Keep cho Sla SO15641', '2024-09-11', 1);
insert into `inbound_items` (count, description, part_number, manufacturer_id, inbound_id) values
    (NULL, 'Thiết bị chuyển mạch Aruba 6000 24G CL4 4 SFP Switch_R8N87A', 'R8N87A', 2, 1),
    (10, 'Thiết bị thu phát vô tuyến Ruijie RG-AP720-L', 'RG-AP720-L', 3, 2),
    (1, 'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 'R8N88A', 2, 3),
    (1, 'Thiết bị chuyển mạch CBS350 Managed 24-port GE, 4x1G SFP_CBS350-24T-4G-EU', 'CBS350-24T-4G-EU', 1, 4),
    (2, 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A', 'Q9G71A', 1, 5),
    (1, 'Khung gắn thiết bị HPE AP-270-MNT-V2 270 Series Mt Kit _JW053A', 'JW053A', 1, 6),
    (20, 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 2, 7),
    (20, 'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 2, 8),
    (2, 'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 'R8N88A', 2, 9),
    (20, 'Nguồn AP-AC2-12B 12V/48W AC/DC pwr adptr B_R3K00A', 'R3K00A', 1, 10),
    (1, 'Thiết bị chuyển mạch Cisco SB CBS350 Managed 48-port GE, PoE, 4x1G SFP_CBS350-48P-4 G-EU', 'CBS350-48P-4G-EU', 4, 11),
    (1, 'Thiết bị chuyển mạch Aruba 6000 24G CL4 4 SFP Switch_R8N87A', 'R8N87A', 2, 12);
insert into `inbound_items_serials` (serial, inbound_item_id) values ('CN34L3LB43', 1);

insert into `inbounds` (inbound_code, location_from, note, release_date, warehouse_id) values
    ('initial', 'initial', 'Nhận bàn giao', '2024-07-08', 1);
insert into `inbound_items` (count, description, part_number, manufacturer_id, inbound_id) values
    (1, 'Thiết bị chuyển mạch HPE Aruba 6000 48G CL4 4SFP Swch _R8N85A', 'R8N85A', 2, 13),
    (1, 'Thiết bị chuyển mạch Aruba 6100 24G-CL4 4SFP + Switch_JL677A', 'JL677A', 2, 13),
    (8, 'Thiết bị chuyển mạch HPE Aruba IOn 1930 24G 4SFP+ 370W Sw_JL684A', 'JL684A', 2, 13),
    (5, 'RM-AP500-3SVN: Phụ kiện gắn tường/trần cho bộ thu phát sóng wifi Aruba 500', '3SVN-RM-AP500', 2, 13),
    (2, 'Mô-Đun HPE Aruba 1G SFP LC LX 10km SMF XCVR_J4859D', 'OEM_J4859D', 2, 13),
    (31, 'Dây nguồn HPE PC-AC-EC Continental European/Schuko AC Power Cord_JW118A', 'JW118A', 1, 13),
    (64, 'Giá đỡ HPE AP-220-MNT-W1W Flat Surface Wall/Ceiling White AP Basic Flat Surface Mount Kit_JW047A', 'JW047A', 1, 13),
    (16, '', 'Dây nguồn oem', 1, 13),
    (6, 'Thiết bị chuyển mạch HPE Aruba IOn 1930 8G 2SFP 124W Switch_JL681A', 'JL681A', 2, 13),
    (10, 'Cáp kết nối 2 thiết bị chuyển mạch làm 1 HPE X240 10G SFP+ SFP+ 1.2m DAC Cable_JD096C', 'JD096C', 1, 13),
    (8, 'Thiết bị chuyển mạch HPE FlexNetwork 5140-48G-4SFP+ EI Switch_JL829A', 'JL829A', 1, 13),
    (2, 'Mô đun quang Cisco 1000BASE-SX SFP transceiver module MMF 850nm DOM_GLC-SX-MMD=', 'OEM_GLC-SX-MMD=', 4, 13),
    (1, '', 'CBS110-24T-EU', 1, 13),
    (3, 'Thiết bị chuyển mạch Cisco Cisco SF550X-24 24-port 10/100 Stackable Switch_SF550X-24-K9-EU', 'SF550X-24-K9-EU', 4, 13),
    (19, 'Thiết bị chuyển mạch Cisco CBS250 Smart 48-port GE, 4x1G SFP_CBS250-48T-4G-EU', 'CBS250-48T-4G-EU', 4, 13),
    (8, 'Thiết bị chuyển mạch HPE Aruba IOn 1930 24G 4SFP+ Switch_JL682A', 'JL682A', 2, 13),
    (2, 'Nguồn AP-AC2-12B 12V/48W AC/DC pwr adptr B_R3K00A', 'R3K00A', 1, 13),
    (22, 'Thiết bị thu phát sóng không dây HPE Aruba AP-515 (RW) Unified AP_Q9H62A', 'Q9H62A', 2, 13),
    (1, '24-Port 10/100/1000BASE-T,and 4 1G/10G SFP+ Ports, Support HPoE(Port1~4),PoE+, PoE,max 370w for PoE', 'RG-S2910-24GT4XS-UP-H(V3.0)', 1, 13),
    (7, 'Thiết bị chuyển mạch Ruijie 24-Port 10/100/1000Base-T, 4-Port 1G/10G Base-X SFP+ (non-combo), AC_RG-S2910-24GT4XS-E', 'RG-S2910-24GT4XS-E', 3, 13),
    (1, 'Thiết bị chuyển mạch Cisco Catalyst 9300 24-port data only, Network Essentials, C9300-DNA-E-24-3Y_C9300-24T-E', 'C9300-24T-E-CTO', 4, 13),
    (6, 'Thiết bị chuyển mạch Ruijie 24-Port 10/100/1000BASE-T,and 4 1G/10G SFP+ Ports, support Poe/PoE+, max 740w for PoE_RG-CS83-24GT4XS-PD', 'RG-CS83-24GT4XS-PD', 3, 13),
    (1, 'Nguồn Ruijie 150W AC power module_RG-PA150IB-F', 'RG-PA150IB-F', 3, 13),
    (8, 'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 'RG-S2915-24GT4MS-P-L', 3, 13),
    (10, 'Thiết bị chuyển đổi quang Aruba Instant On 1G SFP LC SX 500m MMF Transceiver_R9D16A', 'R9D16A', 2, 13),
    (2, 'Thiết bị quản lý thiết bị phát sóng không dây Ruijie Next-Gen Wireless Controller_RG-WS6008', 'RG-WS6008', 3, 13),
    (8, 'Thiết bị chuyển mạch Cisco SB CBS350 Managed 16-port GE, Ext PS, 2x1G SFP_CBS350-16T-E-2G-EU', 'CBS350-16T-E-2G-EU', 4, 13),
    (1, 'Thiết bị chuyển mạch HPE Aruba 6000 12G CL4 2SFP 139W Swch_R8N89A', 'R8N89A', 2, 13),
    (1, 'Nguồn HPE Aruba X371 12VDC 250W PS_JL085A', 'JL085A', 2, 13),
    (29, 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 'R2H28A', 2, 13),
    (21, 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A/10', 'Q9G71A-1-SO', 1, 13),
    (14, 'Nguồn thiết bị HPE AP-POE-ATSR 1P SR 802.3at 30W Midspan_R6P67A', 'R6P67A', 1, 13),
    (1, 'Thiết bị chuyển mạch CBS350 Managed 24-port GE, 4x10G SFP+_CBS350-24T-4X-EU', 'CBS350-24T-4X-EU', 1, 13),
    (21, 'Thiết bị thu phát vô tuyến Ruijie RG-AP720-L', 'RG-AP720-L', 3, 13),
    (2, 'Giá đỡ Aruba X414 1U Universal 4-post RM Kit_J9583B', 'J9583B', 2, 13),
    (1, 'Thiết bị phát sóng không dây Cisco Meraki MR44 WiFi 6 Indoor AP_ MR44-HW', 'MR44-HW', 4, 13),
    (6, 'Thiết bị chuyển mạch Aruba IOn 1930 48G 4SFP+ 370W Switch_JL686B', 'JL686B', 2, 13),
    (1, 'Thiết bị chuyển mạch Aruba 1960 48G- 2XGT 2SFP+ Switch_JL808A', 'JL808A', 2, 13),
    (37, 'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 'RG-AP820-L(V2)', 3, 13),
    (9, 'Thiết bị phát sóng không dây RuijieWi-Fi 6E (802.11ax) indoor wireless access point_RG-AP880-L', 'RG-AP880-L', 3, 13),
    (5, 'Thiết bị chuyển mạch HPE Aruba IOn 1930 24G 4SFP+ 195W Sw_JL683A', 'JL683A', 2, 13),
    (41, 'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 'RG-AP810-L', 3, 13),
    (25, 'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE-AF15', 'RG-POE-AF15', 3, 13),
    (4, 'Thiết bị chuyển mạch Aruba IOn 1930 8G 2SFP Switch_JL680A', 'JL680A', 2, 13),
    (5, 'Thiết bị chuyển mạch Aruba IOn 1930 24G 4SFP+ 195W Switch_JL683B', 'JL683B', 2, 13),
    (2, 'Thiết bị chuyển mạch Aruba IOn 1930 24G 4SFP+ 370W Switch_JL684B', 'JL684B', 2, 13),
    (5, 'Thiết bị chuyển mạch Aruba Instant On 1930 48G- 4SFP+ Switch_JL685A', 'JL685A', 2, 13),
    (4, 'Mô đun quang Aruba 10G SFP+ LC LR 10km SMF XCVR_J9151E', '2-J9151E', 2, 13),
    (3, 'Thiết bị chuyển mạch Switch Ruijie 10 × 10/100/1000Base-T copper ports with auto-negotiation, 2 × 1GE/2.5GE SFP ports, 125 W PoE power supply_RG-S2915-10GT2MS-P-L', 'RG-S2915-10GT2MS-P-L', 3, 13),
    (5, 'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, fixed single AC power supply_RG-S2915-24GT4MS-L', 'RG-S2915-24GT4MS-L', 3, 13),
    (2, 'Thiết bị chuyển mạch Ruijie 48 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, fixed single AC power supply_RG-S2915-48GT4MS-L', 'RG-S2915-48GT4MS-L', 3, 13),
    (3, 'Thiết bị định tuyến Ruijie 8x10/100/1000 Base-T ports, 2x100/1000 Base-X ports, 2 USB ports and 1 Console port_RG-NBR6205-E', 'RG-NBR6205-E', 3, 13),
    (1, 'Thiết bị định tuyến Ruijie 8x10/100/1000 Base-T ports, 2x100/1000 Base-X ports, 2 USB ports and 1 Console port_RG-NBR6210-E', 'RG-NBR6210-E', 3, 13),
    (3, 'Thiết bị định tuyến Ruijie 8x10/100/1000 Base-T ports, 1x100/1000 Base-X port, 1x10Gb Base-X port, 2 USB ports and 1 Console port_RG-NBR6215-E', 'RG-NBR6215-E', 3, 13),
    (1, 'Thiết bị quản lý thiết bị phát sóng không dây Cisco Catalyst 9800-L Wireless Controller-Copper Uplink_C9800-L-C-K9', 'C9800-L-C-K9', 4, 13),
    (7, 'Thiết bị thu phát sóng không dây HPE Aruba AP-565 (RW) Outdoor 11ax AP_R4W43A', 'R4W43A', 2, 13),
    (1, 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 10-pack D_Q9G71A', 'Q9G71A', 1, 13),
    (3, 'Thiết bị chuyển mạch Cisco Catalyst 9300L 24p PoE, Network Essentials, 4x1G Uplink, C9300L-DNA-E-24-3Y_C9300L-24P-4G-E', 'C9300L-24P-4G-E-CTO', 4, 13),
    (1, 'Thiết bị chuyển mạch HPE Aruba 6200F 48G CL4 4SFP+740W Swch_JL728A', 'JL728A', 2, 13),
    (1, 'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 'R8N88A', 2, 13),
    (1, 'Thiết bị chuyển mạch Cisco Catalyst 1000 24port GE, POE, 4x1G SFP_C1000-24P-4G-L', 'C1000-24P-4G-L', 4, 13),
    (4, 'Thiết bị chuyển mạch CBS350 Managed 8-port GE, Ext PS, 2x1G Combo_ CBS350-8T-E-2G-EU', 'CBS350-8T-E-2G-EU', 1, 13);


select o.id, o.count, if(count(ois.id) = 0, NULL, count(ois.id)) from outbound_items o
left join outbound_items_serials ois on o.id = ois.outbound_item_id
group by o.id;

select * from outbound_items left join warehouse.manufacturers m on outbound_items.manufacturer_id = m.id

SELECT o.id, o.note, o.partner,
       o.release_date, o.image, o.so, i.description, i.part_number,
       s.name, IFNULL(i.count, COUNT(i.id)) AS count, m.name AS manufacturer
FROM outbounds o
LEFT JOIN outbound_items i ON o.id = i.outbound_id
LEFT JOIN salers s ON o.sale_id = s.id
LEFT JOIN outbound_items_serials sr ON i.id = sr.outbound_item_id
LEFT JOIN manufacturers m ON i.manufacturer_id = m.id
WHERE ((i.description IS NULL OR i.description LIKE '%searchKey%')
OR (i.part_number IS NULL OR i.part_number LIKE '%searchKey%')
OR (sr.serial IS NULL OR sr.serial LIKE '%searchKey%')
OR (s.name IS NULL OR s.name LIKE '%searchKey%')
OR (o.partner IS NULL OR o.partner LIKE '%searchKey%')
OR (o.so IS NULL OR o.so LIKE '%searchKey%'))
AND ('2024-08-01' IS NULL OR o.release_date >= '2024-08-01')
AND ('2024-09-01' IS NULL OR o.release_date <= '2024-09-01')
AND o.warehouse_id = 1
AND (o.is_delete = 0
AND (i.is_delete IS NULL OR i.is_delete = 0)
AND (sr.is_delete IS NULL OR sr.is_delete = 0))
GROUP BY i.id, o.release_date, o.so, o.id
ORDER BY o.release_date DESC, o.so;


select i.id, i.image, i.inbound_code, i.location_from,
       i.note, i.release_date, IFNULL(ii.count, count(ii.id)) as count,
       ii.description, ii.part_number, m.name
from inbounds i
left join inbound_items ii on i.id = ii.inbound_id
left join inbound_items_serials iis on ii.id = iis.inbound_item_id
left join manufacturers m on m.id = ii.manufacturer_id
where ((i.inbound_code IS NULL OR i.inbound_code LIKE '%%')
OR (i.location_from IS NULL OR i.location_from LIKE '%%')
OR (i.note IS NULL OR i.note LIKE '%%')
OR (ii.description IS NULL OR ii.description LIKE '%%')
OR (ii.part_number IS NULL OR ii.part_number LIKE '%%')
OR (m.name IS NULL OR m.name LIKE '%%'))
AND (null IS NULL OR i.release_date >= '2024-08-01')
AND (null IS NULL OR i.release_date <= '2024-09-01')
AND i.warehouse_id = 1
AND (i.is_delete = 0
AND (ii.is_delete IS NULL OR ii.is_delete = 0))
GROUP BY i.id, i.release_date, i.inbound_code, ii.id
ORDER BY i.release_date DESC, i.inbound_code;

select i.part_number, i.description, ifnull(i.count, count(iis.id)) as count from inbound_items i
left join inbound_items_serials iis on i.id = iis.inbound_item_id
group by i.part_number, i.description, i.count

select i.part_number, ifnull(i.count, count(ii.serial))
from inbound_items i
    left join inbound_items_serials ii on i.id = ii.inbound_item_id
    join inbounds ib on ib.id = i.inbound_id
where i.part_number = 'R3K00A'
  and i.is_delete = 0
  and ib.release_date < '2024-07-09'
group by i.count, i.part_number


select ii.id, ii.manufacturer_id
from inbound_items ii

select o.id, o.manufacturer_id
from outbound_items o

select ob.id, ob.so, o.part_number, o.description, ifnull(o.count, count(os.id)), o.part_number, o.description, m.name
from outbound_items o
join outbounds ob on o.outbound_id = ob.id
left join outbound_items_serials os on o.id = os.outbound_item_id
left join manufacturers m on o.manufacturer_id = m.id
where (isnull(null) or '2024-07-09' <= ob.release_date)
and (isnull(null) or '2024-07-09' >= ob.release_date)
            and o.is_delete = 0
            and ob.is_delete = 0
            and ob.warehouse_id = 1
            group by o.id, ob.id


select o.release_date, oi.part_number, oi.description, oi.count, o.so, o.partner,s.name
    from outbound_items oi
    join outbounds o on oi.outbound_id = o.id
    join warehouses w on w.id = o.warehouse_id
    left join salers s on o.sale_id = s.id
    where o.is_delete = 0
    and w.id = 1
    and ('2024-07-07' is null or o.release_date >= '2024-07-07')
    and ('2025-07-07' is null or o.release_date <= '2025-07-07');

select * from outbound_items o
left join outbound_items_serials ois on o.id = ois.outbound_item_id
where o.id = 2

