/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.62 : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hotel`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` varchar(18) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`id`,`name`,`pwd`,`phone`) values ('1234','dx','123','12345678901'),('440882200007150064','黑雄','123456123456','13025167216');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(18) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

/*Table structure for table `newhotel` */

DROP TABLE IF EXISTS `newhotel`;

CREATE TABLE `newhotel` (
  `name` varchar(20) NOT NULL,
  `evaluate` varchar(188) DEFAULT NULL,
  `like` int(11) DEFAULT NULL,
  `unlike` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `newhotel` */

insert  into `newhotel`(`name`,`evaluate`,`like`,`unlike`) values ('求生酒店','我们酒店地理位置优越，并且有着浓厚的吃鸡气氛，很适合鸡友入住，在我们酒店吃鸡我敢保证你会从绝地非酋变绝地欧皇！',31,3),('王者酒店','我们王者酒店依傍山水，入住我们酒店，你会在不知不觉中拥有无敌的王者气息，即使你现在还只是是青铜！',41,6);

/*Table structure for table `reservationroom` */

DROP TABLE IF EXISTS `reservationroom`;

CREATE TABLE `reservationroom` (
  `hotel` varchar(10) DEFAULT NULL,
  `customer` varchar(18) DEFAULT NULL,
  `room` varchar(10) DEFAULT NULL,
  `payOrNo` varchar(4) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reservationroom` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `hotel` varchar(20) DEFAULT NULL,
  `id` varchar(4) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `bed` varchar(5) DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room` */

insert  into `room`(`hotel`,`id`,`type`,`area`,`bed`,`price`) values ('王者酒店','753','铂金套房','66.6','双人','99'),('王者酒店','749','钻石套房','99.9','双人','999'),('王者酒店','751','黄金套房','30.3','单人','9'),('王者酒店','755','王者套房','960.0','多人','9999'),('求生酒店','754','黄金套房','88.8','地板','8'),('求生酒店','754','黄金套房','88.8','地板','8'),('求生酒店','754','黄金套房','88.8','地板','8'),('求生酒店','754','黄金套房','88.8','地板','8'),('求生酒店','746','白银套房','66.7','单','89');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
