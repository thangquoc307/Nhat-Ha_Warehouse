create database `warehouse`;
use `warehouse`;
insert into `warehouses` (`name`) values
('Hàng xuất bán kho LVS'), ('Hàng hóa kho HN - Nhà C.Tố'), ('Hàng xuất demo Test bảo hành');
insert into `salers` (`name`) values 
('Linhttm'), ('Hantm'), ('HaiLA'), ('ĐucLĐ BU3'), ('ToTKV'), ('Anh Đức');

insert into `stock_notes` (`edit_time`, `warehouse_id`, `release_date`, `so`, `partner`, `sale_id`, `note`) values
('2024-08-29 14:30:00', 1, '2024-07-22', 'SO13910', 'Happy Home', 1, ''),
('2024-08-29 14:30:00', 1, '2024-07-25', 'SO14807', 'Sinh Hùng', 2, ''),
('2024-08-29 14:30:00', 1, '2024-07-30', 'SO15332', 'TNS', 2, ''),
('2024-08-29 14:30:00', 1, '2024-07-30', 'SO15330', 'HUA YUE', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-01', 'SO15359', 'Chính Nhân', 4, ''),
('2024-08-29 14:30:00', 1, '2024-08-06', 'SO15375', 'Mai Nguyễn', 5, ''),
('2024-08-29 14:30:00', 1, '2024-08-06', 'SO15372', 'BÌNH MINH', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-07', 'SO15388', 'Khang Yến', 2, ''),
('2024-08-29 14:30:00', 1, '2024-08-09', 'SO15276', 'Innotel', 2, ''),
('2024-08-29 14:30:00', 1, '2024-08-09', 'SO15418', 'Sunshine', 2, ''),
('2024-08-29 14:30:00', 1, '2024-08-09', 'SO15390', 'SUNTEL', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-09', 'SO15415', 'ROBO', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-09', 'SO15370', 'SLA', 2, 'Xuất kho 96VC (Nhà chị Tố)'),
('2024-08-29 14:30:00', 1, '2024-08-12', 'SO15414', 'NMS', 2, ''),
('2024-08-29 14:30:00', 1, '2024-08-12', 'SO15437', 'ROBO', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-12', 'SO15360', 'Realtek', 3, 'Đổi bảo hành cho khách của anh hải'),
('2024-08-29 14:30:00', 1, '2024-08-15', '', '', 6, 'Test bảo hành cho khách'),
('2024-08-29 14:30:00', 1, '2024-08-15', 'SO15446', 'T2Q', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-18', 'SO15507', 'KTVT Vũng Tàu', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-24', '', '', null, 'Lấy về nhà chị tố'),
('2024-08-29 14:30:00', 1, '2024-08-24', 'SO15449', '', 4, 'Gửi ra SSS HN theo mail của Long Đặng Văn Long'),
('2024-08-29 14:30:00', 1, '2024-08-24', 'SO15541', 'BIZFONE', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-27', '', '', null, 'Lấy về nhà chị tố'),
('2024-08-29 14:30:00', 1, '2024-08-27', 'SO15564', 'ROBO', 3, ''),
('2024-08-29 14:30:00', 1, '2024-08-28', 'SO15579', 'LE SATO', 2, 'Xuất kho 96VC (Nhà chị Tố)'),
('2024-08-29 14:30:00', 1, '2024-08-28', 'SO15580', 'GM', 3, '');

insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP820- L(V2)', 'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 1);
insert into `items_serials` (`serial`, `item_id`) values
('G1SK79C012152', 1), ('G1SK79C001130', 1), ('G1SK79C004204', 1),
('G1SK79C004672', 1), ('G1SK79C01049B', 1), ('G1SK79C020480', 1),
('G1SK79C002916', 1), ('G1SK79C004461', 1), ('G1SK79C007269', 1),
('G1SK79C013305', 1), ('G1SK79C020763', 1), ('G1SK79C00391B', 1),
('G1SK79C007818', 1), ('G1SK79C008447', 1), ('G1SK79C008679', 1),
('G1SK79C00891A', 1), ('G1SK79C003988', 1), ('G1SK79C008405', 1),
('G1SK79C008502', 1), ('G1SK79C008831', 1), ('G1SK79C014757', 1),
('G1SK79C033191', 1);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-PA150 IB-F', 'Nguồn Ruijie 150W AC power module_RG-PA150 IB-F', 1);
insert into `items_serials` (`serial`, `item_id`) values
('G1SLB3W001268', 2);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-S2915- 24GT4MS-P-L', 'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with autonegotiation, 4 × 1GE/2.5GE SFP ports, 375 W PoE power supply_RGS2915-24GT4MS-P-', 1);
insert into `items_serials` (`serial`, `item_id`) values
('G1SU2SY003010', 3), ('G1SU2SY002921', 3);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-WS6008', 'Thiết bị quản lý thiết bị phát sóng không dây Ruijie Next-Gen Wireless Controller, 6 1000BASE-T ports, 2 1000BASET/1000BASE-X combo ports, 32 APs by default_ RG-WS6008', 1);
insert into `items_serials` (`serial`, `item_id`) values
('R572A2317110389', 4);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-S2915-24GT4MS-P-L', 'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with autonegotiation, 4 × 1GE/2.5GE SFP ports, 375 W PoE power supply_RGS2915-24GT4MS-P-L', 2);
insert into `items_serials` (`serial`, `item_id`) values
('G1SU2SY002562', 5);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('Q9H62A', 'Thiết bị thu phát sóng không dây HPE Aruba AP515 (RW) Unified AP_Q9 H62A', 3);
insert into `items_serials` (`serial`, `item_id`) values
('CNQDLJ6G2Z', 6);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP720- L', 'Thiết bị thu phát vô tuyến Ruijie RG-AP720-L', 4);
insert into `items_serials` (`serial`, `item_id`) values
('G1SPCD108443A', 7), ('G1SPCD1084486', 7), ('G1SPCD1085833', 7),
('G1SPCD1083009', 7), ('G1SPCD1084794', 7), ('G1SPCD1084609', 7),
('G1SPCD1085829', 7), ('G1SPCD108618C', 7), ('G1SPCD1084537', 7),
('G1SPCD108485C', 7), ('G1SPCD1058049', 7), ('G1SPCD1057964', 7),
('G1SPCD1059324', 7), ('G1SPCD105950A', 7), ('G1SPCD1057922', 7),
('G1SPCD1059560', 7), ('G1SPCD1059463', 7), ('G1SPCD1057871', 7),
('G1SPCD105933B', 7), ('G1SPCD105947A', 7);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R2H28A', 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 5);
insert into `items_serials` (`serial`, `item_id`) values
('CNQVLBMB5M', 8), ('CNQVLBMB5D', 8), ('CNQVLBMB5G', 8),
('CNQVLBMB53', 8), ('CNQVLBMB3J', 8), ('CNQVLBMB5F', 8),
('CNQVLBMB4X', 8), ('CNQVLBMB59', 8), ('CNQVLBMB52', 8),
('CNQVLBMB56', 8);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('Q9H62A', 'Thiết bị thu phát sóng không dây HPE Aruba AP515 (RW) Unified AP_Q9 H62A', 6);
insert into `items_serials` (`serial`, `item_id`) values
('CNQDLJ6G2T', 9), ('CNQDLJ6GP', 9);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9G71A-1-SO', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_Q9G71A', 6, 2);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP810-L', 'Thiết bị phát sóng không dây Ruijie Wi-Fi 6(802.11ax) indoor wireless access point_RG-AP810-L', 7);
insert into `items_serials` (`serial`, `item_id`) values
('G1RU8BQ03308A', 11), ('G1RU8BQ03315C', 11), ('G1RU8BQ033124', 11),
('G1RU8BQ033048', 11), ('G1RU8BQ031878', 11);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-S2915-10GT2MS-P-L', 'Thiết bị chuyển mạch Switch Ruijie 10 × 10/100/1000Base-T copper ports with auto-negotiation, 2 × 1GE/2.5GE SFP ports, 125 W PoE power supply_RG-S2915-10GT2MS-P-L', 7);
insert into `items_serials` (`serial`, `item_id`) values
('G1SU4SL001162', 12), ('G1SU4SL001310', 12), ('G1SU4SL00110C', 12);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('JL684B', 'Thiết bị chuyển mạch Aruba IOn 1930 24G 4SFP+ 370W Switch_ JL684B', 8);
insert into `items_serials` (`serial`, `item_id`) values
('CN39LB39WZ', 13);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('Q9H62A', 'Thiết bị thu phát sóng không dây HPE Aruba AP515 (RW) Unified AP_Q9 H62A', 9);
insert into `items_serials` (`serial`, `item_id`) values
('CNQDLJ6G16', 14), ('CNQDLJ6G30', 14), ('CNQDLJ6F7L', 14);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9G71A-1- SO', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_ Q9G71A/10', 9, 3);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9G71A-1- SO', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_ Q9G71A/10', 10, 3);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('CBS350-24 T-4X-EU', 'Thiết bị chuyển mạch CBS350 Managed 24-port GE, 4x10G SFP+_CBS350 -24T-4X-EU', 11);
insert into `items_serials` (`serial`, `item_id`) values
('FOC2737Y3BN', 17);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP810-L', 'Thiết bị phát sóng không dây Ruijie Wi-Fi 6 (802.11ax) indoor wireless access point_RG-AP810-L', 12);
insert into `items_serials` (`serial`, `item_id`) values
('G1RU8BQ030523', 18);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-POE-AF15', 'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POEAF15', 12);
insert into `items_serials` (`serial`, `item_id`) values
('G1T03GV009561', 19);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R8N87A', 'Thiết bị chuyển mạch Aruba 6000 24G CL4 4 SFP Switch_R8N87A', 13);
insert into `items_serials` (`serial`, `item_id`) values
('CN34L3LB43', 20);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('Q9G71A-1- SO', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_ Q9G71A/10', 14);
insert into `items_serials` (`serial`, `item_id`) values
('CNQDLJ6G33', 21), ('CNQDLJ6G35', 21), ('CNQDLJ6D9W', 21), ('CNQDLJ6G2M', 21);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9H62A', 'Thiết bị thu phát sóng không dây HPE Aruba AP515 (RW) Unified AP_Q9 H62A', 14, 4);

insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP810-L', 'Thiết bị phát sóng không dây Ruijie Wi-Fi 6 (802.11ax) indoor wireless access point_RG-AP810-L', 15);
insert into `items_serials` (`serial`, `item_id`) values
('G1RU8BQ03311A', 23), ('G1RU8BQ033069', 23), ('G1RU8BQ01932A', 23), ('G1RU8BQ010978', 23);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-POE-AF15', 'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POEAF15', 15);
insert into `items_serials` (`serial`, `item_id`) values
('G1T03GV009612', 24), ('G1T03GV009599', 24), ('G1T03GV009540', 24), ('G1T03GV00952C', 24);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R2H28A', 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 16);
insert into `items_serials` (`serial`, `item_id`) values
('CNQVLBMB4K', 25), ('CNQVLBMB46', 25);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R2H28A', 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 17);
insert into `items_serials` (`serial`, `item_id`) values
('CNP8KPPFWS', 26), ('CNQVLBMB4P', 26), ('CNQVLBMB32', 26),
('CNQVLBMB4W', 26), ('CNQVLBMB4T', 26), ('CNR5KPP297', 26),
('CNQVLBMB4L', 26), ('CNQVLBMB4J', 26), ('CNQVLBMB4N', 26),
('CNQVLBMB4H', 26);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('CBS110-24T-EU', 'Thiết bị chuyển mạch Cisco SB CBS110 Unmanaged 24-port GE, 2 x1G SFP Shared_CBS110- 24T-EU', 18);
insert into `items_serials` (`serial`, `item_id`) values
('DNI261500F2', 27);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-S2915-24GT4MS-P-L', 'Thiết bị chuyển mạch Ruijie 24 × 10/100/1000Base-T copper ports with auto-negotiation, 4 × 1GE/2.5GE SFP ports, 375W PoE power supply_RG-S2915-24GT4MS-P-L', 19);
insert into `items_serials` (`serial`, `item_id`) values
('G1SU2SY002444', 28), ('G1SU2SY00194A', 28);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9G71A', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_Q9G71A', 20, 1);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('Q9G71A-1-SO', 'Khung gắn thiết bị HPE AP-MNT-MP10-D AP mount bracket 1-pack D_Q9G71A', 20, 6);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('CBS250-48T-4G-EU', 'Thiết bị chuyển mạch Cisco CBS250 Smart 48-port GE, 4x1G SFP_CBS250-48T-4G-EU', 21);
insert into `items_serials` (`serial`, `item_id`) values
('PSZ27081K33', 31), ('PSZ27081JDN', 31);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP820-L(V2)', 'Thiết bị thu phát vô tuyến RUIJIE_RG-AP820-L(V2)', 22);
insert into `items_serials` (`serial`, `item_id`) values
('G1SK79C028203', 32), ('G1SK79C028194', 32), ('G1SK79C031528', 32),
('G1SK79C024487', 32), ('G1SK79C023575', 32), ('G1SK79C031507', 32);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R4W43A', 'Thiết bị thu phát sóng không dây HPE Aruba AP-565 (RW) Outdoor 11ax AP_R4W43A', 23);
insert into `items_serials` (`serial`, `item_id`) values
('CNPHKWC2LS', 33);
insert into `items` (`part_number`, `description`, `stock_node_id`, `count`) value ('R2H28A', 'Thiết bị thu phát sóng không dây HPE Aruba AP-505 (RW) Unified AP_R2H28A', 23, 7);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-AP810-L', 'Thiết bị phát sóng không dây Ruijie Wi-Fi 6 (802.11ax) indoor wireless access point_RG-AP810-L', 24);
insert into `items_serials` (`serial`, `item_id`) values
('G1RU8BQ030620', 35), ('G1RU8BQ031194', 35);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-POE-AF15', 'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE AF15', 24);
insert into `items_serials` (`serial`, `item_id`) values
('G1T03GV009696', 36), ('G1T03GV048030', 36);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('R8N88A', 'Thiết bị chuyển mạch Aruba 6000 24G 4SFP Switch_R8N88A', 25);
insert into `items_serials` (`serial`, `item_id`) values
('CN34L3M5NC', 37);
insert into `items` (`part_number`, `description`, `stock_node_id`) value ('RG-POE-AF15', 'Bộ nguồn Ruijie 1-port PoE adapter (1000Base-T, 52V, 15.6W)_RG-POE AF15', 26);
insert into `items_serials` (`serial`, `item_id`) values
('G1T03GV009633', 38), ('G1T03GV04848A', 38), ('G1T03GV048220', 38),
('G1T03GV00949C', 38), ('G1T03GV048503', 38), ('G1T03GV009654', 38),
('G1T03GV048410', 38), ('G1T03GV048448', 38), ('G1T03GV009629', 38),
('G1T03GV009582', 38), ('G1T03GV048494', 38), ('G1T03GV04834B', 38),
('G1T03GV048241', 38), ('G1T03GV009578', 38), ('G1T03GV048427', 38),
('G1T03GV04832A', 38), ('G1T03GV00966B', 38), ('G1T03GV048452', 38);

select n.note, n.partner, n.release_date, n.so, i.description, i.part_number, s.name, ifnull(i.count, count(sr.item_id)) as count from stock_notes n
join items i on n.id = i.stock_node_id
left join salers s on n.sale_id = s.id
left join items_serials sr on i.id = sr.item_id
where (i.description like '%%'
or i.part_number like '%%'
or sr.serial like '%%'
or s.name like '%%'
or n.partner like '%%'
or n.so like '%%')
and (isnull(null) or n.release_date >= '2024-07-30')
and (isnull(null) or n.release_date <= '2024-08-06')
and n.warehouse_id = 1
and (n.is_delete = 0 and i.is_delete = 0)
group by i.id, n.release_date, n.so
order by n.release_date desc, n.so;

select * from items;

select id, name from warehouses where is_delete = 0;

select i.* from items i
left join items_serials s on i.id = s.item_id
where i.stock_node_id = 20 and i.is_delete = 0;

select s.id, s.serial from items_serials s
join items i on i.id = s.item_id
where i.is_delete = 0 and s.item_id = 1;

select s.id, s.name from salers s where s.is_delete = 0;

select * from data_input;

select i.part_number partNumber, i.count countInput, d.count countData from items i
left join stock_notes s on s.id = i.stock_node_id
left join data_input d on d.part_number = i.part_number
where i.is_delete = 0 and s.is_delete = 0
and (isnull(null) or s.release_date >= '2024-07-30')
and (isnull(null) or s.release_date <= '2024-08-06')
group by i.part_number, i.count, d.count
# union

select d.part_number partNumber, i.count countInput, d.count countData from items i
left join stock_notes s on s.id = i.stock_node_id
right join data_input d on d.part_number = i.part_number
where i.is_delete = 0 and s.is_delete = 0
and (isnull(s.release_date) or (isnull(null) or s.release_date >= '2024-07-30'))
and (isnull(s.release_date) or (isnull(null) or s.release_date <= '2024-08-06'))
group by d.part_number, i.count, d.count


select * from data_input d
left join items i on d.part_number = i.part_number
order by d.part_number

select part_number from items i
join warehouse.stock_notes sn on sn.id = i.stock_node_id
where i.is_delete = 0 and sn.is_delete = 0
and sn.warehouse_id = 1
and (isnull(null) or sn.release_date >= '2024-07-30')
and (isnull(null) or sn.release_date <= '2024-08-06')
group by part_number
union
select part_number from data_input group by part_number

select *
from data_input;

select d.count
from data_input d where d.part_number = 'C1000-24P-4G-L';

select part_number, count(*) from items i
group by part_number


select
#     ifnull(i.count, count(s.id)) countData,
    sn.so, sn.release_date
from items i
left join items_serials s on i.id = s.item_id
join stock_notes sn on sn.id = i.stock_node_id
where part_number like 'R2H28A'
and sn.warehouse_id = 1
and (isnull(null) or sn.release_date >= '2024-07-30')
and (isnull(null) or sn.release_date <= '2024-08-06')
and i.is_delete = 0 and sn.is_delete = 0
and (isnull(s.is_delete) or s.is_delete = 0)
group by i.stock_node_id, i.count;

select * from warehouses;
select * from items_serials;