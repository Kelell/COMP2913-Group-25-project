CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(50) DEFAULT NULL,
  `password` text,
  `salt` text
)

INSERT INTO `admin` (`username`, `password`, `salt`) VALUES
	('admin', 'admin', NULL);

CREATE TABLE IF NOT EXISTS `bike` (
  `BIKE_ID` int(11) NOT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `LOCATION` char(30) DEFAULT NULL,
  `price` double DEFAULT '0',
  PRIMARY KEY (`BIKE_ID`)
)

CREATE TABLE IF NOT EXISTS `customer` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_NAME` char(30) DEFAULT NULL,
  `CUSTOMER_ADDRESS` char(30) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
)

INSERT INTO `customer` (`CUSTOMER_ID`, `CUSTOMER_NAME`, `CUSTOMER_ADDRESS`) VALUES
	(1, 'Eden Hood', '179 Kings Road EXETER EX32 9IB'),
	(2, 'Vanessa Summers', '459 King Street ENFIELD EN61 4'),
	(3, 'Zakaria Sanchez', '220 Alexander Road NORTH WEST'),
	(4, 'Alexander Holden 179 Kings Roa', '7801 School Lane ROCHESTER ME3'),
	(5, 'Iona Moran', '422 York Road DORCHESTER DT88'),
	(6, 'Xander Warren', '7801 School Lane ROCHESTER ME3'),
	(7, 'Sonny Long', '9677 Windsor Road HARROW HA81'),
	(8, 'Aysha Stein', '8472 High Street HUDDERSFIELD'),
	(44, 'Michael Jakes', 'P.O BOX 56334, AVENUE NAIROBI');

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
	(19, 1, 'Alnmouth', 47.72),
	(20, 1, 'Beverly', 28.13);
	(21, 1, 'Beverly', 28.13);
	(22, 1, 'Beverly', 28.13);
	(23, 1, 'Hexam', 35.87)

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
)

INSERT INTO `hires` (`hire_id`, `CUSTOMER_ID`, `BIKE_ID`, `days`, `cash`, `barcode`, `START_DATE`, `END_DATE`) VALUES
	(1, 43, 1, 8, 20, 183237397, '2019-03-06', '2019-03-14'),
	(2, 44, 2, 3, 10, 49082089, '2019-03-15', '2019-03-18');
