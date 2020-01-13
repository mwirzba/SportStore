-- noinspection SqlNoDataSourceInspectionForFile


insert into Manufacturer(Name,YEAR_OF_CREATION,Representative) values('KFD Nutrition',1998,'Andrzej Reszka')
insert into Manufacturer(Name,YEAR_OF_CREATION,Representative) values('ActivLab',1993,'Marcin Rudkowski')
insert into Manufacturer(Name,YEAR_OF_CREATION,Representative) values('7 Nutrition',1990,'Mateusz Izba')
insert into Manufacturer(Name,YEAR_OF_CREATION,Representative) values('Hi Tec',1987,'Jan Kowalaski')


insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('KFD Premium WPC 80',120.99,12,1)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('KFD Premium Creatine',89.99,7,1)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('KFD F-Burner ',120.99,12,1)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Activlab Lunch Protein',120.99,12,2)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Activlab Premium Creatine',90.00,3,2)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Activlab Thermo Shape Hydro',99.99,15,1)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('7 Nutrition - Proteon',120.99,12,2)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('7 Nutrition Creatine',89.99,15,2)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('7 Nutrition Jungle Girl Burner',89.99,12,2)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Hi Tec Premium Protein',190.00,11,3)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Hi Tec Premium Creatine',70.9,3,3)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Hi Tec F-Burner ',130.99,9,3)
insert into Product(Name,Price,NUMBER_IN_STOCK,MANUFACTURER_ID) values('Hi Tec F-Burner ',130.99,9,4)


INSERT INTO CATEGORY (NAME) VALUES ('Sport')
INSERT INTO CATEGORY (NAME) VALUES ('Pojazdy')
INSERT INTO CATEGORY (NAME) VALUES ('Fittness')
INSERT INTO CATEGORY (NAME) VALUES ('Trenning')
INSERT INTO CATEGORY (NAME) VALUES ('Suplementy diety')
INSERT INTO CATEGORY (NAME) VALUES ('Odżywki białkowe')
INSERT INTO CATEGORY (NAME) VALUES ('Kreatyna')

INSERT INTO PRODUCT_CATEGORY ( PRODUCT_ID, CATEGORY_ID ) VALUES (1 ,1)
INSERT INTO PRODUCT_CATEGORY ( PRODUCT_ID, CATEGORY_ID ) VALUES (1 ,2)
INSERT INTO PRODUCT_CATEGORY ( PRODUCT_ID, CATEGORY_ID ) VALUES (2 ,2)
INSERT INTO PRODUCT_CATEGORY ( PRODUCT_ID, CATEGORY_ID ) VALUES (2 ,3)
