DROP DATABASE IF EXISTS njult;
CREATE DATABASE njult default charset utf8 COLLATE utf8_general_ci;


USE njult;


CREATE TABLE COMMODITY_CATEGORY (
	ID integer,
	NAME varchar(40) not null,
	UPPER_CATEGORY_ID integer,
	primary key(ID)
);


CREATE TABLE COMMODITY (
	ID varchar(36) not null,
	NAME varchar(50) not null,
	CATEGORY_ID integer,
	MODEL_NUMBER varchar(36) not null,
	REP_COUNT integer not null,
	SELL_PRICE float(8) not null,
	RECENT_SELL_PRICE float(8) not null,
	IN_PRICE float(8) not null,
	RECENT_IN_PRICE float(8) not null,
	BATCH varchar(40) not null,
	BATCH_NUMBER varchar(40) not null,
	DATA_OF_PRODUCTION DATETIME not null,
	primary key(ID)
);


CREATE TABLE REPOSITORY_CHANGE (
	ID integer,
	COMMODITY_ID varchar(50) not null,
	TYPE varchar(20) not null,
	COUNT integer not null,
	AMOUNT float(8) not null,
	CHANGE_TIME DATETIME not null,
	primary key(ID)
);


CREATE TABLE ALERT_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	COMMENT varchar(300),
	TRIGGERED varchar(1) not null,
	EXPIRED varchar(1) not null,
	primary key(ID)
);


CREATE TABLE ALERT_DOC_ITEM (
	ID int,
	ALERT_DOC_ID varchar(20) not null,
	COMMODITY_ID varchar(36) not null,
	COUNT integer not null,
	primary key(ID)
);


CREATE TABLE LOSS_AND_GAIN_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	COMMENT varchar(300),
	primary key(ID)
);


CREATE TABLE LOSS_AND_GAIN_DOC_ITEM (
	ID int,
	LOSS_AND_GAIN_DOC_ID varchar(20) not null,
	COMMODITY_ID varchar(36) not null,
	COUNT integer not null,
	TYPE varchar(20) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT (
	ID varchar(20) not null,
	NAME varchar(40) not null,
	AMOUNT float(8) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT_CHANGE (
	ID integer,
	ACCOUNT_ID varchar(20) not null,
	ACCOUNT_CHANGE_TYPE varchar(20) not null,
	CHANGE_TIME DATETIME not null,
	DELTA float(8) not null,
	AMOUNT float(8) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT_IO_DOC (
	ID varchar(36) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	IO_TYPE varchar(20) not null,
	CUSTOMER_ID varchar(30) not null,
	TOTAL float(8) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT_TRANSFER (
	ID int,
	ACCOUNT_IO_DOC_ID varchar(36) not null,
	ACCOUNT_ID varchar(20) not null,
	AMOUNT float(8) not null,
	COMMENT varchar(300),
	primary key(ID)
);


CREATE TABLE COST_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	ACCOUNT_ID varchar(20) not null,
	TOTAL float(8) not null,
	primary key(ID)
);


CREATE TABLE COST_DOC_ITEM (
	ID int,
	COST_DOC_ID varchar(36) not null,
	TYPE varchar(20) not null,
	AMOUNT float(8) not null,
	COMMENT varchar(300),
	primary key(ID)
);


CREATE TABLE LOG (
	ID integer,
	CREATE_TIME DATETIME not null,
	CONTENT varchar(300) not null,
	USER_ID varchar(20) not null,
	primary key(ID)
);


CREATE TABLE USER_INFO (
	ID varchar(20) not null,
	NAME varchar(20) not null,
	PASSWORD varchar(30) not null,
	IDENTITY varchar(20) not null,
	AUTHORIZED varchar(1) not null,
	primary key(ID)
);


CREATE TABLE INIT_INFO (
	ID varchar(5),
	USER_ID varchar(20) not null, 
	CREATE_TIME DATETIME not null,
	URL varchar(255),
	primary key(ID)
);


CREATE TABLE GIFT_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	PROMOTION_ID int not null,
	REPOSITORY_ID varchar(5),
	CUSTOMER_ID int not null,
	TOTAL float(8) not null,
	primary key(ID)
);


CREATE TABLE GIFT_DOC_ITEM (
	ID int,
	GIFT_DOC_ID varchar(36) not null,
	COMMODITY_ID varchar(36) not null,
	COUNT integer not null,
	SUB_TOTAL float(8) not null,
	primary key(ID)
);


CREATE TABLE CUSTOMER (
	ID integer,
	CUSTOMER_TYPE varchar(20) not null,
	CUSTOMER_GRADE varchar(20) not null,
	NAME varchar(20) not null,
	TELEPHONE varchar(20),
	ADDRESS varchar(20),
	POSTAGE varchar(20),
	EMAIL varchar(40),
	RECEIVABLE_LIMIT float(8) not null,
	RECEIVE_AMOUNT float(8) not null,
	PAY_AMOUNT float(8) not NULL,
	SALES_MAN varchar(20),
	primary key(ID)
);


CREATE TABLE PROMOTION (
	ID integer,
	CREATOR_ID varchar(20) not null,
	NAME varchar(36) not null,
	PROMOTION_TYPE varchar(20) NOT NULL,
	CREATE_TIME DATETIME NOT NULL,
	START_TIME DATETIME NOT NULL,
	END_TIME DATETIME NOT NULL,
	CUSTOMER_GRADE varchar(20) not null,
	TOTAL float(8) NOT NULL,
	OFF float(8) NOT NULL,
	VOUCHER float(8) NOT NULL,
	VOUCHER_END_DATE DATETIME NOT NULL,
	primary key(ID)
);

CREATE TABLE PROMOTION_COMMODITY (
	ID integer,
	PROMOTION_ID integer,
	GOOD_ID varchar(36),
	primary key(ID)
);


CREATE TABLE SALES_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	CUSTOMER_ID varchar(20) NOT NULL,
	SALES_MAN varchar(20),
	REPOSITORY_ID varchar(5), 
	REMARKS varchar(300),
	DISCOUNT float(8) NOT NULL, 
	VOUCHER float(8),
	FINAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE SALES_RETURN_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	CUSTOMER_ID varchar(20) NOT NULL,
	SALES_MAN varchar(20),
	REPOSITORY_ID varchar(5), 
	REMARKS varchar(300),
	DISCOUNT float(8) NOT NULL, 
	VOUCHER float(8),
	FINAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	CUSTOMER_ID varchar(20) NOT NULL,
	REPOSITORY_ID varchar(5), 
	REMARKS varchar(300),
	TOTAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_RETURN_DOC (
	ID varchar(20) not null,
	USER_ID varchar(20) not null,
	CHECKER_ID varchar(20),
	STATE varchar(20) not null,
	CHECKER_COMMENT varchar(300),
	CHECK_TIME DATETIME,
	CREATE_TIME DATETIME not null,
	CUSTOMER_ID varchar(20) NOT NULL,
	REPOSITORY_ID varchar(5), 
	REMARKS varchar(300),
	TOTAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_DOC_ITEM (
	ID int,
	STOCK_DOC_ID varchar(36) NOT NULL,
	COMMODITY_ID varchar(36) NOT NULL,
	COUNT integer NOT NULL,
	TOTAL_AMOUNT float(8) NOT null,
	REMARKS varchar(300),
	primary key(ID)
);


CREATE TABLE SALES_DOC_ITEM (
	ID int,
	SALES_DOC_ID varchar(36) NOT NULL,
	COMMODITY_ID varchar(36) NOT NULL,
	COUNT integer NOT NULL,
	TOTAL_AMOUNT float(8) NOT null,
	REMARKS varchar(300),
	primary key(ID)
);

#部分修改 
alter table COMMODITY_CATEGORY modify column ID int auto_increment;
alter table REPOSITORY_CHANGE modify column ID int auto_increment;
alter table ACCOUNT_CHANGE modify column ID int auto_increment;
alter table CUSTOMER modify column ID int auto_increment;
alter table PROMOTION modify column ID int auto_increment;
alter table PROMOTION_COMMODITY modify column ID int auto_increment;
alter table INIT_INFO modify column ID int auto_increment;
alter table LOG modify column ID int auto_increment;
alter table ALERT_DOC_ITEM modify column ID int auto_increment;
alter table COST_DOC_ITEM modify column ID int auto_increment;
alter table GIFT_DOC_ITEM modify column ID int auto_increment;
alter table LOSS_AND_GAIN_DOC_ITEM modify column ID int auto_increment;
alter table ACCOUNT_TRANSFER modify column ID int auto_increment;
alter table SALES_DOC_ITEM modify column ID int auto_increment;
alter table STOCK_DOC_ITEM modify column ID int auto_increment;