# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 10.20.155.4    Database: test
# ------------------------------------------------------
# Server version 5.1.40sp1-enterprise-gpl-pro-log

#
# Source for table admin_user
#

DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `login_id` varchar(200) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2860 DEFAULT CHARSET=utf8;

#
# Dumping data for table admin_user
#

INSERT INTO `admin_user` VALUES (2858,'2012-08-15 17:05:18','system','2012-08-15 17:05:18','system','Y','admin','6f04f0d75f6870858bae14ac0b6d9f73','zr','afb@123.com');

#
# Source for table qth_attachment
#

DROP TABLE IF EXISTS `qth_attachment`;
CREATE TABLE `qth_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2548 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_attachment
#

INSERT INTO `qth_attachment` VALUES (2540,NULL,NULL,'2012-08-15 17:05:24',NULL,'Y','image','/test/img1.jpb',1,'sale');
INSERT INTO `qth_attachment` VALUES (2542,NULL,NULL,'2012-08-15 17:05:24',NULL,'N','image','/test/img2.jpb',1,'sale');
INSERT INTO `qth_attachment` VALUES (2544,NULL,NULL,'2012-08-15 17:05:24',NULL,'N','image','/test/img3.jpb',1,'sale');
INSERT INTO `qth_attachment` VALUES (2546,'2012-08-15 17:05:24','system','2012-08-15 17:05:24','system','N','image','/test/img4.jpb',1,'sale');

#
# Source for table qth_audit_log
#

DROP TABLE IF EXISTS `qth_audit_log`;
CREATE TABLE `qth_audit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `auditor` varchar(50) DEFAULT NULL,
  `audit_type` varchar(20) DEFAULT NULL,
  `audit_id` bigint(20) DEFAULT NULL,
  `audit_result` varchar(50) DEFAULT NULL,
  `description` varbinary(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=612 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_audit_log
#

INSERT INTO `qth_audit_log` VALUES (610,'2012-08-15 17:05:18','system','2012-08-15 17:05:18','system','N','test auditor','user',1,'fail','desc');

#
# Source for table qth_msg
#

DROP TABLE IF EXISTS `qth_msg`;
CREATE TABLE `qth_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `msg_title` varchar(200) DEFAULT NULL,
  `sender` varchar(50) DEFAULT NULL,
  `receiver` varchar(50) DEFAULT NULL,
  `msg_content` text,
  `is_opened` char(1) DEFAULT NULL,
  `sender_deleted` char(1) DEFAULT NULL,
  `receiver_deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=662 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_msg
#

INSERT INTO `qth_msg` VALUES (1,NULL,NULL,'2012-08-15 17:05:21',NULL,'N','测试标题1','s1更新','r1','描述信息xxx，短信内容','N','N','N');
INSERT INTO `qth_msg` VALUES (2,NULL,NULL,NULL,NULL,'N','测试标题2','s2','r2','描述信息xxx，短信内容','N','N','N');
INSERT INTO `qth_msg` VALUES (3,NULL,NULL,NULL,NULL,'N','测试标题3','s3','r3','描述信息xxx，短信内容','N','N','N');

#
# Source for table qth_pro_category
#

DROP TABLE IF EXISTS `qth_pro_category`;
CREATE TABLE `qth_pro_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `is_material` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=648 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_pro_category
#

INSERT INTO `qth_pro_category` VALUES (1,NULL,NULL,NULL,NULL,'N','测试类别1','描述信息xxx','Y');
INSERT INTO `qth_pro_category` VALUES (2,NULL,NULL,NULL,NULL,'N','测试类别2','描述信息xxx','Y');
INSERT INTO `qth_pro_category` VALUES (3,NULL,NULL,NULL,NULL,'N','测试类别3','描述信息xxx','N');

#
# Source for table qth_product_buy
#

DROP TABLE IF EXISTS `qth_product_buy`;
CREATE TABLE `qth_product_buy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `lowest_deal_size` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  `product_type` varchar(50) DEFAULT NULL,
  `is_sale` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=706 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_product_buy
#

INSERT INTO `qth_product_buy` VALUES (1,NULL,NULL,'2012-08-15 17:05:20',NULL,'N','测试产品1',1,NULL,NULL,NULL,NULL,'new','zhangren','resource','Y');
INSERT INTO `qth_product_buy` VALUES (2,NULL,NULL,NULL,NULL,'N','测试产品2',1,NULL,NULL,NULL,NULL,'new','xhm','resource','Y');
INSERT INTO `qth_product_buy` VALUES (3,NULL,NULL,NULL,NULL,'N','测试产品3',1,NULL,NULL,NULL,NULL,'new','zhangren','resource','N');

#
# Source for table qth_product_sale
#

DROP TABLE IF EXISTS `qth_product_sale`;
CREATE TABLE `qth_product_sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `lowest_deal_size` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  `product_type` varchar(50) DEFAULT NULL,
  `is_sale` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=646 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_product_sale
#

INSERT INTO `qth_product_sale` VALUES (1,NULL,NULL,'2012-08-15 17:05:24',NULL,'N','测试产品1',1,NULL,NULL,NULL,NULL,'new','zhangren','resource','Y');
INSERT INTO `qth_product_sale` VALUES (2,NULL,NULL,NULL,NULL,'N','测试产品2',1,NULL,NULL,NULL,NULL,'new','xhm','resource','Y');
INSERT INTO `qth_product_sale` VALUES (3,NULL,NULL,NULL,NULL,'N','测试产品3',1,NULL,NULL,NULL,NULL,'new','zhangren','resource','N');

#
# Source for table qth_user
#

DROP TABLE IF EXISTS `qth_user`;
CREATE TABLE `qth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_area` char(4) DEFAULT NULL,
  `phone_number` char(13) DEFAULT NULL,
  `mobile_phone` char(15) DEFAULT NULL,
  `company` varchar(200) DEFAULT NULL,
  `company_address` varchar(500) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `id_card_num` varchar(20) DEFAULT NULL,
  `busi_license` varchar(200) DEFAULT NULL,
  `user_level` varchar(50) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8018 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_user
#

INSERT INTO `qth_user` VALUES (8004,'zhangren',NULL,'姓名1','f','abc@abc.com','0571','85022088',NULL,'公司1',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,'2012-08-16 20:43:16',NULL,'new');
INSERT INTO `qth_user` VALUES (8006,'xhm',NULL,'姓名2','f','abc@abc.com','0571','85022088',NULL,'公司2',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,NULL,NULL,'new');
INSERT INTO `qth_user` VALUES (8008,'admin',NULL,'姓名3','f','abc@abc.com','0571','85022088',NULL,'公司3',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,NULL,NULL,'new');
INSERT INTO `qth_user` VALUES (8010,'zhangren1',NULL,'姓名4','f','abc@abc.com','0571','85022088',NULL,'公司4',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,NULL,NULL,'new');
INSERT INTO `qth_user` VALUES (8012,'xhm1',NULL,'姓名5','f','abc@abc.com','0571','85022088',NULL,'公司5',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,NULL,NULL,'new');
INSERT INTO `qth_user` VALUES (8014,'admin1',NULL,'姓名6','f','abc@abc.com','0571','85022088',NULL,'公司6',NULL,'innerUser',NULL,NULL,'golden','N',NULL,NULL,NULL,NULL,'new');
INSERT INTO `qth_user` VALUES (8016,'addzhangren','6f04f0d75f6870858bae14ac0b6d9f73','单测张三','f','afb@123.com','0571','88889988','12355478965','单测公司名','单元测试公司地址','innerUser','365587458755','11234','common','N','2012-08-16 20:43:16','system','2012-08-16 20:43:16','system','new');

#
# Source for table qth_user_interest
#

DROP TABLE IF EXISTS `qth_user_interest`;
CREATE TABLE `qth_user_interest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) DEFAULT NULL,
  `interest` varchar(50) DEFAULT NULL,
  `value` bigint(20) DEFAULT '0',
  `is_deleted` char(1) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3564 DEFAULT CHARSET=utf8;

#
# Dumping data for table qth_user_interest
#

INSERT INTO `qth_user_interest` VALUES (3546,'zhangren','buy',1,'Y',NULL,NULL,'2012-08-16 20:43:16',NULL);
INSERT INTO `qth_user_interest` VALUES (3548,'zhangren','buy',2,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3550,'zhangren','sale',3,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3552,'xhm1','buy',1,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3554,'xhm1','buy',2,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3556,'xhm1','sale',3,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3558,'xhm','buy',1,'N',NULL,NULL,NULL,NULL);
INSERT INTO `qth_user_interest` VALUES (3560,'addzhangren','buy',1,'N','2012-08-16 20:43:16','system','2012-08-16 20:43:16','system');
INSERT INTO `qth_user_interest` VALUES (3562,'zhangren','sale',1,'N','2012-08-16 20:43:16','system','2012-08-16 20:43:16','system');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
