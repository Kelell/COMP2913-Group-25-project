CREATE TABLE BIKES(
	BIKE_ID INT PRIMARY KEY NOT NULL,
	STATUS SMALLINT, 
	LOCATION CHAR(30)
);

CREATE TABLE CUSTOMERS(
	CUSTOMER_ID INT PRIMARY KEY NOT NULL,
	CUSTOMER_NAME CHAR(30),
	CUSTOMER_ADDRESS CHAR(30)
);

CREATE TABLE HIRES(
	HIRE_ID INT PRIMARY KEY NOT NULL,
	CUSTOMER_ID INT,
	BIKE_ID INT,
	START_DATE DATE,
	END_DATE DATE,

	FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(CUSTOMER_ID),
	FOREIGN KEY (BIKE_ID) REFERENCES BIKES(BIKE_ID)
);


INSERT INTO BIKES VALUES
	(1, 2, 'District 1'), 
	(2, 2, 'District 1'), 
	(3, 2, 'District 1'), 
	(4, 1, 'District 1'), 
	(5, 1, 'District 1'), 
	(6, 2, 'District 2'), 
	(7, 2, 'District 2'), 
	(8, 1, 'District 2'), 
	(9, 1, 'District 2'), 
	(10, 1, 'District 2'),
	(11, 2, 'District 3'),
	(12, 1, 'District 3'),
	(13, 1, 'District 3'),
	(14, 1, 'District 3'),
	(15, 1, 'District 3'),
	(16, 2, 'District 4'),
	(17, 1, 'District 4'),
	(18, 1, 'District 4'),
	(19, 1, 'District 4'),
	(20, 1, 'District 4');

INSERT INTO CUSTOMERS VALUES
	(1,'Eden Hood' , 'District 1'),
	(2,'Vanessa Summers' , 'District 4'),
	(3,'Zakaria Sanchez' , 'District 1'),
	(4,'Alexander Holden' , 'District 3'),
	(5,'Iona Moran' , 'District 2'),
	(6,'Xander Warren' , 'District 2'),
	(7,'Sonny Long' , 'District 1'),
	(8,'Aysha Stein' , 'District 3');

INSERT INTO HIRES VALUES
	('1', '1', '8', '2018-12-20', '2018-12-23'),
	('2', '3', '9', '2018-10-13', '2018-10-15'),
	('3', '5', '12', '2018-05-07', '2018-05-09'),
	('4', '8', '15', '2019-01-10', '2019-01-11'),
	('5', '7', '19', '2019-02-01', '2019-02-02');












