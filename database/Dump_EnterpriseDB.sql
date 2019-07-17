CREATE DATABASE  IF NOT EXISTS `enterprisedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `enterprisedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: enterprisedb
-- ------------------------------------------------------
-- Server version	5.5.50-MariaDB

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
-- Table structure for table `solar_system_snapshot`
--

DROP TABLE IF EXISTS `solar_system_snapshot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solar_system_snapshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(32) NOT NULL,
  `weather` varchar(8) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3655 DEFAULT CHARSET=utf8mb4 COMMENT='Stores the weather of the solar system each day';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weather_period`
--

DROP TABLE IF EXISTS `weather_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weather_period` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_snapshot_id` int(11) NOT NULL,
  `end_snapshot_id` int(11) DEFAULT NULL,
  `weather_type` varchar(8) NOT NULL,
  `peak_rain_snapshot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `weather_period_start_snapshot_solar_system_snapshot_fk` (`start_snapshot_id`),
  KEY `weather_period_end_snapshot_solar_system_snapshot_fk` (`end_snapshot_id`),
  KEY `weather_period_peak_rain_snapshot_solar_system_snapshot_fk` (`peak_rain_snapshot_id`),
  CONSTRAINT `weather_period_start_snapshot_solar_system_snapshot_fk` FOREIGN KEY (`start_snapshot_id`) REFERENCES `solar_system_snapshot` (`id`),
  CONSTRAINT `weather_period_end_snapshot_solar_system_snapshot_fk` FOREIGN KEY (`end_snapshot_id`) REFERENCES `solar_system_snapshot` (`id`),
  CONSTRAINT `weather_period_peak_rain_snapshot_solar_system_snapshot_fk` FOREIGN KEY (`peak_rain_snapshot_id`) REFERENCES `solar_system_snapshot` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03 17:36:43
