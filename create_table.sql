DROP DATABASE IF EXISTS njult;
CREATE DATABASE njult default charset utf8 COLLATE utf8_general_ci;


USE njult;


CREATE TABLE COMMODITY_CATEGORY (
	ID integer not null,
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
	ID varchar(36),
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
	ID varchar(36),
	LOSS_AND_GAIN_DOC_ID varchar(20) not null,
	COMMODITY_ID varchar(36) not null,
	COUNT integer not null,
	TYPE varchar(20) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT (
	ID varchar(20) not null,
	NAME varchar(20) not null,
	AMOUNT float(8) not null,
	primary key(ID)
);


CREATE TABLE ACCOUNT_CHANGE (
	ID varchar(36),
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
	ID varchar(36),
	ACCOUNT_ID_DOC_ID varchar(36) not null,
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
	ID varchar(36),
	COST_DOC_ID varchar(36) not null,
	TYPE varchar(20) not null,
	AMOUNT float(8) not null,
	COMMENT varchar(300),
	primary key(ID)
);


CREATE TABLE LOG (
	ID varchar(36),
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
	primary key(ID)
);


CREATE TABLE INIT_ACCOUNT_INFO (
	ID varchar(20) not null,
	NAME varchar(20) not null,
	AMOUNT float(8) not null,
	INIT_ID varchar(5) not null,
	primary key(ID, INIT_ID)
);


CREATE TABLE INIT_COMMODITY_INFO (
	ID varchar(36) not null,
	NAME varchar(50) not null,
	CATEGORY_ID integer,
	MODEL_NUMBER varchar(36) not null,
	SELL_PRICE float(8) not null,
	IN_PRICE float(8) not null,
	INIT_ID varchar(5) not null,
	primary key(ID, INIT_ID)
);


CREATE TABLE INIT_COMMODITY_CATEGORY_INFO (
	ID integer not null,
	NAME varchar(40) not null,
	UPPER_CATEGORY_ID integer,
	INIT_ID varchar(5) not null,
	primary key(ID, INIT_ID)
);


CREATE TABLE GIFT_DOC (
	ID varchar(20) not null,
	PROMOTION_ID varchar(20) not null,
	CREATE_ID varchar(20) not null,
	REPOSITORY_ID varchar(5),
	TOTAL float(8) not null,
	CREATE_TIME DATETIME not null,
	COMMENT varchar(300),
	primary key(ID)
);


CREATE TABLE GIFT_DOC_ITEM (
	ID varchar(36),
	PROMOTION_ID varchar(20) not null,
	COMMODITY_ID varchar(36) not null,
	COUNT integer not null,
	SUB_TOTAL float(8) not null,
	primary key(ID)
);


CREATE TABLE CUSTOMER (
	ID varchar(20),
	CUSTOMER_TYPE varchar(10) not null,
	CUSTOMER_GRADE varchar(10) not null,
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


CREATE TABLE INIT_CUSTOMER_INFO (
	ID varchar(20),
	CUSTOMER_TYPE varchar(10) not null,
	CUSTOMER_GRADE varchar(10) not null,
	NAME varchar(20) not null,
	TELEPHONE varchar(20),
	ADDRESS varchar(20),
	POSTAGE varchar(20),
	EMAIL varchar(40),
	RECEIVABLE_LIMIT float(8) not null,
	RECEIVE_AMOUNT float(8) not null,
	PAY_AMOUNT float(8) not NULL,
	SALES_MAN varchar(20),
	INIT_ID varchar(5) not null,
	primary key(ID, INIT_ID)
);

CREATE TABLE PROMOTION (
	ID varchar(20),
	NAME varchar(36) not null,
	PROMOTION_TYPE varchar(20) NOT NULL,
	CREATE_TIME DATETIME NOT NULL,
	START_TIME DATETIME NOT NULL,
	END_TIME DATETIME NOT NULL,
	CUSTOMER_GRADE varchar(10) not null,
	TOTAL float(8) NOT NULL,
	OFF float(8) NOT NULL,
	VOUCHER float(8) NOT NULL,
	VOUCHER_END_DATE DATETIME NOT NULL,
	primary key(ID)
);


CREATE TABLE SALES_DOC (
	ID varchar(36) NOT NULL,
	CUSTOMER_ID varchar(20) NOT NULL,
	SALES_MAN varchar(20),
	REPOSITORY_ID varchar(5),
	USER_ID varchar(20) NOT NULL, 
	REMARKS varchar(300),
	BEFORE_DISCOUNT_AMOUNT float(8) NOT NULL, 
	DISCOUNT float(8) NOT NULL, 
	VOUCHER float(8),
	FINAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE SALES_RETURN_DOC (
	ID varchar(36) NOT NULL,
	CUSTOMER_ID varchar(20) NOT NULL,
	SALES_MAN varchar(20),
	REPOSITORY_ID varchar(5),
	USER_ID varchar(20) NOT NULL, 
	REMARKS varchar(300),
	BEFORE_DISCOUNT_AMOUNT float(8) NOT NULL, 
	DISCOUNT float(8) NOT NULL, 
	VOUCHER float(8),
	FINAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_DOC (
	ID varchar(36) NOT NULL,
	CUSTOMER_ID varchar(20) NOT NULL,
	REPOSITORY_ID varchar(5),
	USER_ID varchar(20) NOT NULL, 
	REMARKS varchar(300),
	TOTAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_RETURN_DOC (
	ID varchar(36) NOT NULL,
	CUSTOMER_ID varchar(20) NOT NULL,
	REPOSITORY_ID varchar(5),
	USER_ID varchar(20) NOT NULL, 
	REMARKS varchar(300),
	TOTAL_AMOUNT float(8) NOT NULL,
	primary key(ID)
);


CREATE TABLE STOCK_DOC_ITEM (
	ID varchar(36),
	STOCK_DOC_ID varchar(36) NOT NULL,
	COMMODITY_ID varchar(36) NOT NULL,
	COUNT integer NOT NULL,
	TOTAL_AMOUNT float(8) NOT null,
	REMARKS varchar(300),
	primary key(ID)
);


CREATE TABLE SALES_DOC_ITEM (
	ID varchar(36),
	SALES_DOC_ID varchar(36) NOT NULL,
	COMMODITY_ID varchar(36) NOT NULL,
	COUNT integer NOT NULL,
	TOTAL_AMOUNT float(8) NOT null,
	REMARKS varchar(300),
	primary key(ID)
);