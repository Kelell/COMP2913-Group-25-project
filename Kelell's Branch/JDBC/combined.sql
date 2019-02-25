CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(50) DEFAULT NULL,
  `password` text,
  `salt` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `admin` (`username`, `password`, `salt`) VALUES
	('admin', 'admin', NULL);

CREATE TABLE IF NOT EXISTS `bike` (
  `BIKE_ID` int(11) NOT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `LOCATION` char(30) DEFAULT NULL,
  `price` double DEFAULT '0',
  PRIMARY KEY (`BIKE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `bike` (`BIKE_ID`, `STATUS`, `LOCATION`, `price`) VALUES
	(1, 2, 'Alnmouth', 36.79),
	(2, 2, 'Bernslkey Interchange', 28.51),
	(3, 2, 'Beverly', 32.17),
	(4, 1, 'Bradford Interchange', 25.31),
	(5, 1, 'Halifax', 30.07),
	(6, 2, 'Harrogate', 24.4),
	(7, 2, 'Hebden Bridge', 31.81),
	(8, 1, 'Hexam', 35.87),
	(9, 1, 'Leeds', 33.92),
	(10, 1, 'Rotherham', 12.01),
	(11, 2, 'Shipley', 8.28),
	(12, 1, 'TodModen', 5.38),
	(13, 1, 'Manchester', 2.07),
	(14, 1, 'Bristol', 44.19),
	(15, 1, 'West Ham', 14.77),
	(16, 2, 'Bournemouth', 41.29),
	(17, 1, 'Crystal', 12.14),
	(18, 1, 'Burkinham', 36.83),
	(19, 1, 'Almouth', 47.72),
	(20, 1, 'Beverly', 28.13);

CREATE TABLE HIRES(
	HIRE_ID INT PRIMARY KEY NOT NULL,
	CUSTOMER_ID INT,
	BIKE_ID INT,
	START_DATE DATE,
	END_DATE DATE,

	FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(CUSTOMER_ID),
	FOREIGN KEY (BIKE_ID) REFERENCES BIKES(BIKE_ID)
);

INSERT INTO HIRES VALUES
  ('1', '1', '8', '2018-12-20', '2018-12-23'),
  ('2', '3', '9', '2018-10-13', '2018-10-15'),
  ('3', '5', '12', '2018-05-07', '2018-05-09'),
  ('4', '8', '15', '2019-01-10', '2019-01-11'),
  ('5', '7', '19', '2019-02-01', '2019-02-02');

CREATE TABLE IF NOT EXISTS `customer` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_NAME` char(30) DEFAULT NULL,
  `CUSTOMER_ADDRESS` char(30) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT INTO `customer` (`CUSTOMER_ID`, `CUSTOMER_NAME`, `CUSTOMER_ADDRESS`) VALUES
	(1, 'Eden Hood', '179 Kings Road EXETER EX32 9IB'),
	(2, 'Vanessa Summers', '459 King Street ENFIELD EN61 4'),
	(3, 'Zakaria Sanchez', '220 Alexander Road NORTH WEST'),
	(4, 'Alexander Holden', '7801 School Lane ROCHESTER ME3'),
	(5, 'Iona Moran', '422 York Road DORCHESTER DT88'),
	(6, 'Xander Warren', '7801 School Lane ROCHESTER ME3'),
	(7, 'Sonny Long', '9677 Windsor Road HARROW HA81'),
	(8, 'Aysha Stein', '8472 High Street HUDDERSFIELD');

CREATE TABLE IF NOT EXISTS `ticket` (
  `ticket_id` varchar(50) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `bike_id` int(11) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `ticket` (`ticket_id`, `customer_id`, `bike_id`, `START_DATE`, `END_DATE`) VALUES
	('#20147082', 4, 14, '2019-02-25', '2019-02-25'),
	('#374537245', 1, 1, '2019-02-24', '2019-02-26');
