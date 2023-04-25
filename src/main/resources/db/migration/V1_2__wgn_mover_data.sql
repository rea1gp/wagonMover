INSERT INTO wgn_mover.cargo ( name, number) VALUES ( 'ГРУЗ-001', 1010);
INSERT INTO wgn_mover.cargo ( name, number) VALUES ( 'ГРУЗ-002', 2020);
INSERT INTO wgn_mover.cargo ( name, number) VALUES ( 'ГРУЗ-003', 3030);
INSERT INTO wgn_mover.cargo ( name, number) VALUES ( 'ГРУЗ-004', 4040);

INSERT INTO wgn_mover.type ( name) VALUES ( 'TYPE-001');
INSERT INTO wgn_mover.type ( name) VALUES ( 'TYPE-002');
INSERT INTO wgn_mover.type ( name) VALUES ( 'TYPE-003');
INSERT INTO wgn_mover.type ( name) VALUES ( 'TYPE-004');

INSERT INTO wgn_mover.status ( name) VALUES ( 'NEW');
INSERT INTO wgn_mover.status ( name) VALUES ( 'STATION');
INSERT INTO wgn_mover.status ( name) VALUES ( 'RZD');

INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 1000, 1, 1000.00, 3000.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 1111, 1, 1100.00, 3100.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 2222, 2, 1200.00, 3200.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 3333, 3, 1300.00, 3300.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 4444, 3, 1400.00, 3400.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 5555, 1, 1500.00, 3300.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 6666, 2, 1600.00, 3600.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 7777, 3, 1700.00, 3800.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 8888, 1, 1800.00, 3200.00);
INSERT INTO wgn_mover.wagon_passport ( number, type_id, weight, capacity) VALUES ( 9999, 2, 1900.00, 3300.00);


INSERT INTO wgn_mover.station ( name) VALUES ( 'Станция №1');
INSERT INTO wgn_mover.station ( name) VALUES ( 'Станция №2');
INSERT INTO wgn_mover.station ( name) VALUES ( 'Станция №3');


INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 1,1 );
INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 2,1 );
INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 3,2 );
INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 4,2 );
INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 5,3 );
INSERT INTO wgn_mover.line ( number, station_id) VALUES ( 6,3 );


INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 2, 1, 1000.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 1, 1, 1200.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 2, 1, 1300.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 3, 1, 1400.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 2, 1, 1500.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 3, 1, 1600.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 1, 1, 1700.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 3, 1, 1800.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 2, 1, 1900.00);
INSERT INTO wgn_mover.wagon ( cargo_id, status_id, weight) VALUES ( 1, 1, 2000.00);

