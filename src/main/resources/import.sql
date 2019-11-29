CREATE  TABLE  city(
   id BIGINT PRIMARY KEY ,
   province_id BIGINT,
   city_name VARCHAR(20),
   description VARCHAR(20)
);


INSERT  INTO  city VALUES (1,1,'长沙','省会');
INSERT  INTO  city VALUES (2,2,'常德','老家');
INSERT  INTO  city VALUES (3,3,'永州','没去过');
INSERT  INTO  city VALUES (4,4,'ac','没去过');