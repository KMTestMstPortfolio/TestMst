
DROP TABLE IF EXISTS M01;
DROP TABLE IF EXISTS M02;

CREATE TABLE M01
(
   kanri_no INT,
   user_id VARCHAR(30),
   user_nm VARCHAR(30),
   bumon_cd VARCHAR(5),
   role_id VARCHAR(5),
   address VARCHAR(30),
   phone VARCHAR(13),
   torokubi DATETIME,
   koshinbi DATETIME,
   muko_flg boolean,
   PRIMARY KEY(kanri_no)
);

CREATE TABLE M02
(
   bumon_cd VARCHAR(5),
   bumon_mei VARCHAR(30),
   PRIMARY KEY(bumon_cd)
);