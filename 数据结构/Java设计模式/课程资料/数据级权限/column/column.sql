/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.6.21-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `cup` */

CREATE TABLE `cup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '产品名称',
  `version` varchar(20) DEFAULT NULL COMMENT '型号',
  `ordinary_price` int(11) DEFAULT NULL COMMENT '普通进价',
  `wholesale_price` int(11) DEFAULT NULL COMMENT '批发进价',
  `retail_price` int(11) DEFAULT NULL COMMENT '零售价',
  `membership_price` int(11) DEFAULT NULL COMMENT '会员售价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cup` */

insert  into `cup`(`id`,`name`,`version`,`ordinary_price`,`wholesale_price`,`retail_price`,`membership_price`) values (1,'茶杯','cp123',80,60,120,100);
insert  into `cup`(`id`,`name`,`version`,`ordinary_price`,`wholesale_price`,`retail_price`,`membership_price`) values (2,'纸杯','zb987',30,20,50,40);
insert  into `cup`(`id`,`name`,`version`,`ordinary_price`,`wholesale_price`,`retail_price`,`membership_price`) values (3,'陶瓷杯','tcb999',150,120,200,180);

/*Table structure for table `field_cup_user` */

CREATE TABLE `field_cup_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `fieldName` varchar(50) DEFAULT NULL COMMENT '字段名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `field_cup_user` */

/*Table structure for table `tab_filed_order` */

CREATE TABLE `tab_filed_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tableName` varchar(50) DEFAULT NULL COMMENT '表名称',
  `fieldName` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `fieldOrder` int(11) DEFAULT NULL COMMENT '字段在对应表中排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tab_filed_order` */

insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (1,'cup','id',1);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (2,'cup','name',2);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (3,'cup','version',3);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (4,'cup','ordinary_price',4);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (5,'cup','wholesale_price',5);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (6,'cup','retail_price',6);
insert  into `tab_filed_order`(`id`,`tableName`,`fieldName`,`fieldOrder`) values (7,'cup','membership_price',7);

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`) values (1,'admin');
insert  into `user`(`id`,`name`) values (2,'waver');
insert  into `user`(`id`,`name`) values (3,'chennan');
insert  into `user`(`id`,`name`) values (4,'xiaolong');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
