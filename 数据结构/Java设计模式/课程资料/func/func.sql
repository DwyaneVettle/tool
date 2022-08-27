/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.6.21-log : Database - func
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`func` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `func`;

/*Table structure for table `t_func` */

CREATE TABLE `t_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '功能名称',
  `pageName` varchar(20) DEFAULT NULL COMMENT '显示名称',
  `pid` int(11) DEFAULT NULL COMMENT '父级功能id',
  `url` varchar(255) DEFAULT NULL COMMENT '权限url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_func` */

insert  into `t_func`(`id`,`name`,`pageName`,`pid`,`url`) values (1,'empList','员工管理',NULL,'/empMgrList.action');
insert  into `t_func`(`id`,`name`,`pageName`,`pid`,`url`) values (2,'proList','产品管理',NULL,'/proMgrList.action');

/*Table structure for table `t_role` */

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`) values (1,'管理员');
insert  into `t_role`(`id`,`name`) values (2,'用户');

/*Table structure for table `t_role_func` */

CREATE TABLE `t_role_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `func_id` int(11) DEFAULT NULL COMMENT '功能id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_func` */

insert  into `t_role_func`(`id`,`role_id`,`func_id`) values (1,1,1);
insert  into `t_role_func`(`id`,`role_id`,`func_id`) values (2,1,2);
insert  into `t_role_func`(`id`,`role_id`,`func_id`) values (3,2,2);

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`) values (1,'waver');
insert  into `t_user`(`id`,`name`) values (2,'chennan');
insert  into `t_user`(`id`,`name`) values (3,'xiaolong');

/*Table structure for table `t_user_role` */

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`user_id`,`role_id`) values (1,1,1);
insert  into `t_user_role`(`id`,`user_id`,`role_id`) values (2,2,2);
insert  into `t_user_role`(`id`,`user_id`,`role_id`) values (3,3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
