CREATE DATABASE  IF NOT EXISTS `restaurant_review` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `restaurant_review`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant_review
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu_items`
--

DROP TABLE IF EXISTS `menu_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_items` (
  `menu_items_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `menu_items_restaurant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_items_id`),
  KEY `menu_items_restaurant_id` (`menu_items_restaurant_id`),
  CONSTRAINT `menu_items_ibfk_1` FOREIGN KEY (`menu_items_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_items`
--

LOCK TABLES `menu_items` WRITE;
/*!40000 ALTER TABLE `menu_items` DISABLE KEYS */;
INSERT INTO `menu_items` VALUES (25,'Chicken Fried Rice','rice dish','8','Entree',16),(26,'Oyster Pancake with Gravy','','9','Appetizer',16),(27,'Preserved Egg with Chilled Tofu','Non-Veg','7','Appetizer',16),(28,'Chicken Teriyaki','Non-Veg','13','Entree',16),(29,'Original Bubble Tea','Drink','4','Beverage',16);
/*!40000 ALTER TABLE `menu_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `restaurant_id` int(11) NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(50) NOT NULL,
  `location` varchar(50) DEFAULT NULL,
  `cuisuine` varchar(50) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `restaurant_user_id` int(11) DEFAULT NULL,
  `isDisabled` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`),
  KEY `restaurant_user_id` (`restaurant_user_id`),
  CONSTRAINT `restaurant_ibfk_1` FOREIGN KEY (`restaurant_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (16,'Dumpling Palace','Allston','Chinese','3457869854','$$$',NULL,27,'false'),(17,'P.F. Chang\'s','Bolyston','Chinese','1157869854','$$',NULL,27,'false'),(18,'Indian Restaurant','Brighton','Indian','7654328990','$$',NULL,27,'false'),(19,'Punjabi Palace','Quincy','Indian','2346578765','$$','',3,'false'),(20,'Cafe Tomato','New York','American','2134567890','$',NULL,3,'false'),(21,'Hanover Gourmet Deli','New York','American','7654567890','$$',NULL,3,'false');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_order`
--

DROP TABLE IF EXISTS `restaurant_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_item1` int(11) DEFAULT NULL,
  `menu_item2` int(11) DEFAULT NULL,
  `menu_item3` int(11) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `payment_type` varchar(50) DEFAULT NULL,
  `order_user_id` int(11) DEFAULT NULL,
  `order_restaurant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `menu_item1` (`menu_item1`),
  KEY `menu_item2` (`menu_item2`),
  KEY `menu_item3` (`menu_item3`),
  KEY `order_user_id` (`order_user_id`),
  KEY `order_restaurant_id` (`order_restaurant_id`),
  CONSTRAINT `restaurant_order_ibfk_1` FOREIGN KEY (`menu_item1`) REFERENCES `menu_items` (`menu_items_id`),
  CONSTRAINT `restaurant_order_ibfk_2` FOREIGN KEY (`menu_item2`) REFERENCES `menu_items` (`menu_items_id`),
  CONSTRAINT `restaurant_order_ibfk_3` FOREIGN KEY (`menu_item3`) REFERENCES `menu_items` (`menu_items_id`),
  CONSTRAINT `restaurant_order_ibfk_4` FOREIGN KEY (`order_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `restaurant_order_ibfk_5` FOREIGN KEY (`order_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_order`
--

LOCK TABLES `restaurant_order` WRITE;
/*!40000 ALTER TABLE `restaurant_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(50) DEFAULT NULL,
  `review_date` datetime DEFAULT NULL,
  `review_user_id` int(11) DEFAULT NULL,
  `review_restaurant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `review_user_id` (`review_user_id`),
  KEY `review_restaurant_id` (`review_restaurant_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`review_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`review_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (28,'Overall pretty decent food.','2016-12-10 07:00:37',29,16),(29,'The food was soggy and disappointing.','2016-12-15 07:01:02',29,17),(30,'The food was late and cold','2016-12-15 07:10:36',30,16),(31,'Would order again given a chance','2016-12-13 07:10:56',30,17);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_reply`
--

DROP TABLE IF EXISTS `review_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review_reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(50) DEFAULT NULL,
  `review_date` datetime DEFAULT NULL,
  `reply_user_id` int(11) DEFAULT NULL,
  `reply_restaurant_id` int(11) DEFAULT NULL,
  `reply_review_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `reply_user_id` (`reply_user_id`),
  KEY `reply_restaurant_id` (`reply_restaurant_id`),
  KEY `reply_review_id` (`reply_review_id`),
  CONSTRAINT `review_reply_ibfk_1` FOREIGN KEY (`reply_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `review_reply_ibfk_2` FOREIGN KEY (`reply_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `review_reply_ibfk_3` FOREIGN KEY (`reply_review_id`) REFERENCES `review` (`review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_reply`
--

LOCK TABLES `review_reply` WRITE;
/*!40000 ALTER TABLE `review_reply` DISABLE KEYS */;
INSERT INTO `review_reply` VALUES (43,'thank you for the review.','2016-12-05 07:12:21',27,16,28),(45,'Apologies.We shall work on improving .','2016-11-15 07:13:09',27,16,30);
/*!40000 ALTER TABLE `review_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `AGE` datetime DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Full_NAME` varchar(50) DEFAULT NULL,
  `PHONE_NUMBER` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `ZipCode` varchar(45) DEFAULT NULL,
  `USER_TYPE` varchar(50) NOT NULL,
  `State` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `IsDisabled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'pprabhu123','password',NULL,'Male','prasadprabhu','2013545340','prasad.net@gmail.com','1315 commonwealth ave',NULL,NULL,'Customer',NULL,NULL,NULL),(3,'test123','password123',NULL,'Male','testuser','2013545340','prasad.net@gmail.com','1315 commonwealth ave',NULL,NULL,'Owner',NULL,NULL,NULL),(25,'admin','admin',NULL,'Female','systemadmin','23412423455','sys@gmail.com','boston',NULL,NULL,'Admin',NULL,NULL,NULL),(27,'owner','owner',NULL,'Female','ownero','5082028199','owner@gmail.com','Boston',NULL,NULL,'Owner',NULL,NULL,NULL),(28,'customer','customer',NULL,'Female','customerc','2015345430','customer@gmail.com','Boston',NULL,NULL,'Customer',NULL,NULL,NULL),(29,'prasad','prasad',NULL,'Female','prasadprabhu','2347865432','prasad.prabhu@gmail.com','Boston',NULL,NULL,'Customer',NULL,NULL,NULL),(30,'varsha','varsha',NULL,'Female','varshasharma','2347865432','prasad.prabhu@gmail.com','Boston',NULL,NULL,'Customer',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_payment_details`
--

DROP TABLE IF EXISTS `user_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_payment_details` (
  `user_payment_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(50) NOT NULL,
  `cvv` varchar(30) NOT NULL,
  `expire_date` varchar(30) NOT NULL,
  `card_type` varchar(50) NOT NULL,
  `user_payment_details_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_payment_details_id`),
  KEY `user_payment_details_user_id` (`user_payment_details_user_id`),
  CONSTRAINT `user_payment_details_ibfk_1` FOREIGN KEY (`user_payment_details_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_payment_details`
--

LOCK TABLES `user_payment_details` WRITE;
/*!40000 ALTER TABLE `user_payment_details` DISABLE KEYS */;
INSERT INTO `user_payment_details` VALUES (1,'1231436546577474','013','12/3/2020','credit',3),(2,'123143654657000074','013','12/3/2020','debit',3),(3,'123143654657000074','013','12/3/2020','debit',3);
/*!40000 ALTER TABLE `user_payment_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-15  7:37:09
