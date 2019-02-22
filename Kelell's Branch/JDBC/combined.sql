CREATE TABLE BIKE(
  BIKE_ID INT PRIMARY KEY NOT NULL,
  STATUS SMALLINT,
  LOCATION CHAR(30),
  START_DATE DATE,
  END_DATE DATE);

  CREATE TABLE CUSTOMER(
    CUSTOMER_ID INT PRIMARY KEY NOT NULL,
    CUSTOMER_NAME CHAR(30),
    CUSTOMER_ADDRESS CHAR(30),
    START_DATE DATE,
    DUE_DATE DATE);

    INSERT INTO BIKE VALUES
      (1, 2, 'District 1', '2019-02-22', '2019-02-23'),
      (2, 2, 'District 1', '2019-02-27', '2019-03-01'),
      (3, 2, 'District 1', '2019-02-07', '2019-02-27'),
      (4, 1, 'District 1', null, null),
      (5, 1, 'District 1', null, null),
      (6, 2, 'District 2', '2019-03-03', '2019-03-10'),
      (7, 2, 'District 2', '2019-05-19', '2019-05-23'),
      (8, 1, 'District 2', null, null),
      (9, 1, 'District 2', null, null),
      (10, 1, 'District 2', null, null),
      (11, 2, 'District 3', '2019-02-15', '2019-02-25'),
      (12, 1, 'District 3', null, null),
      (13, 1, 'District 3', null, null),
      (14, 1, 'District 3', null, null),
      (15, 1, 'District 3', null, null),
      (16, 2, 'District 4', '2019-02-23', '2019-02-24'),
      (17, 1, 'District 4', null, null),
      (18, 1, 'District 4', null, null),
      (19, 1, 'District 4', null, null),
      (20, 1, 'District 4', null, null);

      INSERT INTO CUSTOMER VALUES
        (1,'Eden Hood' , 'District 1', '2019-02-22', '2019-02-23'),
        (2,'Vanessa Summers' , 'District 4', '2019-02-23', '2019-02-24'),
        (3,'Zakaria Sanchez' , 'District 1', '2019-02-27', '2019-03-01'),
        (4,'Alexander Holden' , 'District 3', '2019-02-15', '2019-02-25'),
        (5,'Iona Moran' , 'District 2', '2019-03-03', '2019-03-10'),
        (6,'Xander Warren' , 'District 2', '2019-05-19', '2019-05-23'),
        (7,'Sonny Long' , 'District 1', '2019-02-07', '2019-02-27'),
        (8,'Aysha Stein' , 'District 3', '2019-02-18', '2019-02-21');