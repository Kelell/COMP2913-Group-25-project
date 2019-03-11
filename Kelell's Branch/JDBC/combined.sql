CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(50) DEFAULT NULL,
  `password` text,
  `salt` text
);

INSERT INTO `admin` (`username`, `password`, `salt`) VALUES
	('admin', 'admin', NULL);

CREATE TABLE IF NOT EXISTS `bike` (
  `BIKE_ID` int(11) NOT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `LOCATION` char(30) DEFAULT NULL,
  `price` double DEFAULT '0',
  PRIMARY KEY (`BIKE_ID`)
);

INSERT INTO `bike` (`BIKE_ID`, `STATUS`, `LOCATION`, `price`) VALUES
	(1, 2, 'Alnmouth', 2),
	(2, 2, 'Bernslkey Interchange', 2),
	(3, 2, 'Beverly', 2),
	(4, 2, 'Bradford Interchange', 2),
	(5, 2, 'Halifax', 2),
	(6, 2, 'Harrogate', 2),
	(7, 1, 'Hebden Bridge', 2),
	(8, 1, 'Hexam', 2),
	(9, 1, 'Leeds', 2),
	(10, 1, 'Rotherham', 2),
	(11, 1, 'Shipley', 2),
	(12, 1, 'TodModen', 2),
	(13, 1, 'Manchester', 2),
	(14, 1, 'Bristol', 2),
	(15, 1, 'West Ham', 2),
	(16, 1, 'Bournemouth', 2),
	(17, 1, 'Crystal', 2),
	(18, 1, 'Burkinham', 2),
	(19, 1, 'Almouth', 2),
	(20, 1, 'Beverly', 2);

CREATE TABLE IF NOT EXISTS `customer` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_NAME` char(30) DEFAULT NULL,
  `CUSTOMER_ADDRESS` char(30) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
);

INSERT INTO `customer` (`CUSTOMER_ID`, `CUSTOMER_NAME`, `CUSTOMER_ADDRESS`) VALUES
	(1, 'Eden Hood', '179 Kings Road EXETER EX32 9IB'),
	(2, 'Vanessa Summers', '459 King Street ENFIELD EN61 4'),
	(3, 'Zakaria Sanchez', '220 Alexander Road NORTH WEST'),
	(4, 'Alexander Holden 179 Kings Roa', '7801 School Lane ROCHESTER ME3'),
	(5, 'Iona Moran', '422 York Road DORCHESTER DT88'),
	(6, 'Xander Warren', '7801 School Lane ROCHESTER ME3'),
	(7, 'Sonny Long', '9677 Windsor Road HARROW HA81'),
	(8, 'Aysha Stein', '8472 High Street HUDDERSFIELD'),
	(44, 'Michael Jakes', 'P.O BOX 56334, AVENUE NAIROBI'),
	(45, 'Max', 'P.O BX 2323'),
	(46, 'Lau', 'P>SS'),
	(47, 'MM', 'P.O BOX 343'),
	(48, 'New Customer', 'New Address');

CREATE TABLE IF NOT EXISTS `hires` (
  `hire_id` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `BIKE_ID` int(11) DEFAULT NULL,
  `days` int(11) DEFAULT '1',
  `cash` double DEFAULT '1',
  `barcode` int(11) DEFAULT '1',
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`hire_id`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  KEY `BIKE_ID` (`BIKE_ID`)
);

INSERT INTO `hires` (`hire_id`, `CUSTOMER_ID`, `BIKE_ID`, `days`, `cash`, `barcode`, `START_DATE`, `END_DATE`) VALUES
	(1, 43, 1, 8, 20, 183237397, '2019-03-06', '2019-03-14'),
	(2, 44, 2, 3, 10, 49082089, '2019-03-15', '2019-03-18'),
	(3, 45, 3, 2, 10, 350888753, '2019-03-08', '2019-03-10'),
	(4, 46, 4, 2, 10, 852693087, '2019-03-09', '2019-03-11'),
	(5, 47, 5, 1, 10, 292431738, '2019-03-15', '2019-03-16'),
	(6, 48, 6, 11, 122, 571202050, '2019-03-11', '2019-03-22');