-- TEST THUMBNAIL IMAGE FILE INIT
INSERT INTO FILE(file_id, FILE_NAME, FILE_ORIGIN_NAME, FILE_PATH, SIZE, ADD_TIME) VALUES (1, 'ez2db.png', 'afawefawe-awefawefaw-awefawef.png', '/img/', 1024, '2022-08-07 20:46' );

-- TEST MEMBER INIT
INSERT INTO MEMBER(MEMBER_ID, ADD_TIME, AUTHORITY, NAME, PASSWORD, SALT, USER_ID) VALUES (2, '2022-08-07 20:46', 'REGULAR', 'name01', 'b8be2633601c671026b266bda08662abb271580f70df6a8b0434261a4ca4ac27c6a3983080076083eba340eae596c6f6fa2c700f025d241ee9143ce410f62b0d', 5682692210363896418, 'test01');
INSERT INTO MEMBER(MEMBER_ID, ADD_TIME, AUTHORITY, NAME, PASSWORD, SALT, USER_ID) VALUES (3, '2022-08-07 20:46', 'REGULAR', 'name01', 'a1eb6922e73ce411cd0fce5b3909fe243f18a04a93b757c2ba8fab8c7786d198ebc302ff72d29e2a8d9149e13c3866b52edde34392a475c126e95acee1806df7', -55620108149577836, 'test02');
INSERT INTO MEMBER(MEMBER_ID, ADD_TIME, AUTHORITY, NAME, PASSWORD, SALT, USER_ID) VALUES (4, '2022-08-07 20:46', 'REGULAR', 'name01', '25e3158c0c155371129cff38b68840dcc0a6c5fdaaf0f04e0aa13f7fde608401537f695829667acb9702b01dc3b3074e2baa2f746c53fd87ecb885e9c9722c67', -3291134816380597248, 'test03');

-- TEST MUSIC INFO INIT
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (5,  '2022-08-10 13:56:11.000000', 'SHIKI', 1100000, 'R1ST', 'testData', 'EZ',  'FIVE', 8,  'AIR(EZ2ON Ver.)', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (6,  '2022-08-10 13:56:11.000000', 'SHIKI', 1100000, 'R1ST', 'testData', 'NM',  'FIVE', 14, 'AIR(EZ2ON Ver.)', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (7,  '2022-08-10 13:56:11.000000', 'SHIKI', 1100000, 'R1ST', 'testData', 'HD',  'FIVE', 17, 'AIR(EZ2ON Ver.)', 4, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (8,  '2022-08-10 13:56:11.000000', 'SHIKI', 1100000, 'R1ST', 'testData', 'SHD', 'FIVE', 20, 'AIR(EZ2ON Ver.)', 4, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (9,  '2022-08-10 13:56:11.000000', 'CROOVE', 1100000, 'R4TH', 'testData', 'EZ',  'FIVE', 7,  'Fire Storm', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (10, '2022-08-10 13:56:11.000000', 'CROOVE', 1100000, 'R4TH', 'testData', 'NM',  'FIVE', 12, 'Fire Storm', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (11, '2022-08-10 13:56:11.000000', 'CROOVE', 1100000, 'R4TH', 'testData', 'HD',  'FIVE', 16, 'Fire Storm', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (12, '2022-08-10 13:56:11.000000', 'CROOVE', 1100000, 'R4TH', 'testData', 'SHD', 'FIVE', 20, 'Fire Storm', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (13, '2022-08-10 13:56:11.000000', 'Cosmograph', 1100000, 'R2021', 'testData', 'EZ',  'FIVE', 9,  'Ultimatum', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (14, '2022-08-10 13:56:11.000000', 'Cosmograph', 1100000, 'R2021', 'testData', 'NM',  'FIVE', 15, 'Ultimatum', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (15, '2022-08-10 13:56:11.000000', 'Cosmograph', 1100000, 'R2021', 'testData', 'HD',  'FIVE', 18, 'Ultimatum', 3, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (16, '2022-08-10 13:56:11.000000', 'Cosmograph', 1100000, 'R2021', 'testData', 'SHD', 'FIVE', 20, 'Ultimatum', 3, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (17, '2022-08-10 13:56:11.000000', 'SHK', 1100000, 'DO2JAM', 'testData', 'EZ',  'FIVE', 5,  'Identity Part 2', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (18, '2022-08-10 13:56:11.000000', 'SHK', 1100000, 'DO2JAM', 'testData', 'NM',  'FIVE', 12, 'Identity Part 2', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (19, '2022-08-10 13:56:11.000000', 'SHK', 1100000, 'DO2JAM', 'testData', 'HD',  'FIVE', 17, 'Identity Part 2', 4, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (20, '2022-08-10 13:56:11.000000', 'SHK', 1100000, 'DO2JAM', 'testData', 'SHD', 'FIVE', 20, 'Identity Part 2', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (21, '2022-08-10 13:56:11.000000', 'Springhead', 1100000, 'R7TH', 'testData', 'EZ',  'FIVE', 9,  'Revelation', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (22, '2022-08-10 13:56:11.000000', 'Springhead', 1100000, 'R7TH', 'testData', 'NM',  'FIVE', 13, 'Revelation', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (23, '2022-08-10 13:56:11.000000', 'Springhead', 1100000, 'R7TH', 'testData', 'HD',  'FIVE', 17, 'Revelation', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (24, '2022-08-10 13:56:11.000000', 'Springhead', 1100000, 'R7TH', 'testData', 'SHD', 'FIVE', 20, 'Revelation', 4, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (25, '2022-08-10 13:56:11.000000', 'Morimori Atsushi', 1100000, 'R2021', 'testData', 'EZ',  'FIVE', 5,  'PUPA', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (26, '2022-08-10 13:56:11.000000', 'Morimori Atsushi', 1100000, 'R2021', 'testData', 'NM',  'FIVE', 10, 'PUPA', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (27, '2022-08-10 13:56:11.000000', 'Morimori Atsushi', 1100000, 'R2021', 'testData', 'HD',  'FIVE', 16, 'PUPA', 1, 1);
insert into PUBLIC.MUSIC_INFO (MUSIC_INFO_ID, ADD_TIME, ARTIST, BEST_SCORE, CATEGORY, DESCRIPTION, DIFFICULTY, KEY_TYPE, LEVEL, NAME, RANK, FILE_ID) values (28, '2022-08-10 13:56:11.000000', 'Morimori Atsushi', 1100000, 'R2021', 'testData', 'SHD', 'FIVE', 20, 'PUPA', 5, 1);

-- TEST RECORD DETAIL
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(29, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 8);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(30, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 12);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(31, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 15);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(32, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 16);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(33, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 19);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(34, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 20);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(35, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 23);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(36, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 24);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(37, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 27);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(38, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 28);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(39, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 8);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(40, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 12);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(41, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 15);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(42, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 16);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(43, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 19);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(44, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 20);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(45, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 23);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(46, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 24);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(47, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 27);
INSERT INTO RECORD_DETAIL(record_detail_id, add_time, grade, is_all_cool, is_no_miss, score, percentage, music_info_id) VALUES(48, '2022-08-10 13:56:11.000000', 'SP', FALSE, FALSE, 1110000, 99.99, 28);

-- TEST RECORD INIT
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(49, '2022-08-10 13:56:11.000000', 29, 2, 8);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(50, '2022-08-10 13:56:11.000000', 30, 2, 12);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(51, '2022-08-10 13:56:11.000000', 31, 2, 15);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(52, '2022-08-10 13:56:11.000000', 32, 2, 16);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(53, '2022-08-10 13:56:11.000000', 33, 2, 19);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(54, '2022-08-10 13:56:11.000000', 34, 2, 20);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(55, '2022-08-10 13:56:11.000000', 35, 2, 23);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(56, '2022-08-10 13:56:11.000000', 36, 2, 24);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(57, '2022-08-10 13:56:11.000000', 37, 2, 27);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(58, '2022-08-10 13:56:11.000000', 38, 2, 28);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(59, '2022-08-10 13:56:11.000000', 39, 3, 8);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(60, '2022-08-10 13:56:11.000000', 40, 3, 12);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(61, '2022-08-10 13:56:11.000000', 41, 3, 15);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(62, '2022-08-10 13:56:11.000000', 42, 3, 16);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(63, '2022-08-10 13:56:11.000000', 43, 3, 19);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(64, '2022-08-10 13:56:11.000000', 44, 3, 20);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(65, '2022-08-10 13:56:11.000000', 45, 3, 23);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(66, '2022-08-10 13:56:11.000000', 46, 3, 24);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(67, '2022-08-10 13:56:11.000000', 47, 3, 27);
INSERT INTO RECORD(record_id, add_time, record_detail_id, member_id, music_info_id) VALUES(68, '2022-08-10 13:56:11.000000', 48, 3, 28);

-- COMMIT
COMMIT;