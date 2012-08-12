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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

#
# Source for table qth_user_interest
#

DROP TABLE IF EXISTS `qth_user_interest`;
CREATE TABLE `qth_user_interest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) DEFAULT NULL,
  `interest` varchar(50) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
