/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.6.21-log : Database - rowauth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rowauth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rowauth`;

/*Table structure for table `consumption` */

CREATE TABLE `consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `expenditure` decimal(10,0) DEFAULT NULL COMMENT '报销金额',
  `time` datetime DEFAULT NULL COMMENT '报销时间',
  `mark` varchar(50) DEFAULT NULL COMMENT '备注',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `consumption` */

insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (1,'500','2018-11-01 10:50:13','请客户吃饭',1);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (2,'20000','2018-10-02 10:50:55','采购电脑',2);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (3,'3000','2018-11-05 10:51:15','耍',1);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (4,'8000','2018-11-06 10:52:22','打广告',3);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (5,'100','2018-11-07 10:53:08','交通费报销',1);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (6,'50000','2018-11-02 12:06:26','买服务器',2);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (7,'789','2018-10-29 15:15:51','吃饭',4);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (8,'800','2018-10-30 15:16:04','聚餐',4);
insert  into `consumption`(`id`,`expenditure`,`time`,`mark`,`dept_id`) values (9,'50','2018-10-31 15:16:27','修电脑',2);

/*Table structure for table `dept` */

CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`id`,`name`) values (1,'销售部');
insert  into `dept`(`id`,`name`) values (2,'研发部');
insert  into `dept`(`id`,`name`) values (3,'市场部');
insert  into `dept`(`id`,`name`) values (4,'董事会');

/*Table structure for table `employee` */

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`) values (1,'admin');
insert  into `employee`(`id`,`name`) values (2,'waver');
insert  into `employee`(`id`,`name`) values (3,'chennan');
insert  into `employee`(`id`,`name`) values (4,'xiaolong');

/*Table structure for table `row_auth` */

CREATE TABLE `row_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tabName` varchar(30) DEFAULT NULL COMMENT '表名称',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工id',
  `dept_id` int(11) DEFAULT NULL COMMENT '存在权限部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `row_auth` */

insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (1,'consumption',2,1);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (2,'consumption',2,2);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (7,'consumption',3,1);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (8,'consumption',3,2);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (9,'consumption',3,3);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (10,'consumption',3,4);
insert  into `row_auth`(`id`,`tabName`,`employee_id`,`dept_id`) values (11,'consumption',4,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
