INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (101, 'sampleFolder1', 0, 'FOLDER', '2012-07-29 17:08:42', '2018-01-16 15:34:30', null);
INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (102, 'sampleFolder2', 0, 'FOLDER', '2012-11-13 11:42:42', '2018-01-16 15:34:30', null);
INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (103, 'sampleFolder3', 0, 'FOLDER', '2012-07-29 10:24:42', '2018-01-16 15:34:30', 101);
INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (104, 'sampleFolder4', 0, 'FOLDER', '2014-02-28 14:02:05', '2014-04-16 15:34:30', 102);
INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (105, 'sampleFolder5', 0, 'FOLDER', '2012-02-28 14:02:05', '2012-05-16 12:34:30', 102);
INSERT INTO folder (id, name, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (106, 'sampleFolder6', 0, 'FOLDER', '2015-02-28 11:02:05', '2016-04-16 13:34:30', 103);

INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (1, 'simpleFile1', 'txt', 'Hello from \n\tSimpleFile1', 10362, 'FILE', '2015-07-29 17:08:42', '2015-08-12 13:02:30', 101);
INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (2, 'simpleFile2', 'docx', 'Hello from \n\tSimpleFile2', 1500, 'FILE', '2016-08-22 14:08:42', '2016-08-26 13:02:30', 102);
INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (3, 'simpleFile3', 'csv', 'Hello from \n\tSimpleFile3', 2511, 'FILE', '2017-01-15 08:25:00', '2018-01-16 15:34:30', 103);
INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (4, 'simpleFile4', 'txt', 'Hello from \n\tSimpleFile4', 121, 'FILE', '2019-01-15 08:25:00', '2020-01-16 15:34:30', 103);
INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (5, 'simpleFile5', 'java', 'Hello from \n\tSimpleFile5', 2455, 'FILE', '2017-07-15 08:25:00', '2017-09-16 15:34:30', 104);
INSERT INTO file (id, name, extension, content, size, type, creation_date, last_modification_date, parent_folder_id)
    VALUES (6, 'simpleFile6', 'js', 'Hello from \n\tSimpleFile6', 55, 'FILE', '2020-02-11 12:23:11', '2020-02-13 14:41:15', null);

