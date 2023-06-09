﻿-- Script was generated by Devart dbForge Studio for MySQL, Version 6.0.315.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 2019/8/19 10:12:28
-- Server version: 5.7.17-log
-- Client version: 4.1

-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

-- 
-- Set default database
--
USE
demo_ds_1;

--
-- Definition for table t_order_0
--
DROP TABLE IF EXISTS t_order_0;
CREATE TABLE t_order_0
(
    id       VARCHAR(64) NOT NULL COMMENT '主键',
    order_id BIGINT(20) NOT NULL,
    user_id  INT(11) NOT NULL,
    status   VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Definition for table t_order_1
--
DROP TABLE IF EXISTS t_order_1;
CREATE TABLE t_order_1
(
    id       VARCHAR(64) NOT NULL COMMENT '主键',
    order_id BIGINT(20) NOT NULL,
    user_id  INT(11) NOT NULL,
    status   VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Definition for table t_order_item_0
--
DROP TABLE IF EXISTS t_order_item_0;
CREATE TABLE t_order_item_0
(
    id            VARCHAR(64) NOT NULL COMMENT '主键',
    order_item_id BIGINT(20) NOT NULL,
    order_id      BIGINT(20) NOT NULL,
    user_id       INT(11) NOT NULL,
    status        VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Definition for table t_order_item_1
--
DROP TABLE IF EXISTS t_order_item_1;
CREATE TABLE t_order_item_1
(
    id            VARCHAR(64) NOT NULL COMMENT '主键',
    order_item_id BIGINT(20) NOT NULL,
    order_id      BIGINT(20) NOT NULL,
    user_id       INT(11) NOT NULL,
    status        VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Definition for table undo_log
--
DROP TABLE IF EXISTS undo_log;
CREATE TABLE undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX ux_undo_log (xid, branch_id)
) ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

-- 
-- Dumping data for table t_order_0
--

-- Table demo_ds_1.t_order_0 does not contain any data (it is empty)

-- 
-- Dumping data for table t_order_1
--

-- Table demo_ds_1.t_order_1 does not contain any data (it is empty)

-- 
-- Dumping data for table t_order_item_0
--

-- Table demo_ds_1.t_order_item_0 does not contain any data (it is empty)

-- 
-- Dumping data for table t_order_item_1
--

-- Table demo_ds_1.t_order_item_1 does not contain any data (it is empty)

-- 
-- Dumping data for table undo_log
--

-- Table demo_ds_1.undo_log does not contain any data (it is empty)

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;