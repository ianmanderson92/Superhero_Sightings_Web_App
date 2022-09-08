CREATE DATABASE  IF NOT EXISTS `superherosightings` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `superherosightings`;
-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: superherosightings
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

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
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(450) DEFAULT 'Unknown',
  `address` varchar(450) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'New York','The Big Apple','New York State',40.71280000,74.00600000),(2,'London','Britian\'s Finest','United Kingdom',51.50720000,0.12760000),(3,'Los Angeles','Home of the Stars','California, US',34.05220000,118.24370000),(4,'Tokyo','Japan\'s most popular tourist spot','Japan, Asia',35.67620000,139.65030000),(5,'New Delhi','Capitol of India','India',28.61390000,77.20900000),(6,'Gotham City','Fictional imagination of Chicago','Illinois, USA',41.87810000,81.62980000);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(450) DEFAULT 'Unknown',
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'X-men','Group of super mutants.','Secret house somewhere in midwest.','charles@xmen.com','1-800-we-are-mutants'),(2,'Avengers','Large Collection of heros originally formed to combat Theros','Many locations around multiverse','contact@avengers.com','1-800-avergers-rule'),(3,'Fantastic 4','a small group of super friends','Baxter Building, New York','fantastic4@fantastic.com','1-866-fantastic');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sighting`
--

DROP TABLE IF EXISTS `sighting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sighting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `heroId` int(11) NOT NULL COMMENT 'foreign key for hero table',
  `locationId` int(11) NOT NULL COMMENT 'foreign key for location table',
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_heroId` (`heroId`),
  KEY `fk_locationId` (`locationId`),
  CONSTRAINT `fk_heroId` FOREIGN KEY (`heroId`) REFERENCES `superhero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_locationId` FOREIGN KEY (`locationId`) REFERENCES `location` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sighting`
--

LOCK TABLES `sighting` WRITE;
/*!40000 ALTER TABLE `sighting` DISABLE KEYS */;
INSERT INTO `sighting` VALUES (1,4,1,'2022-03-05'),(2,2,1,'2022-03-05'),(3,6,1,'2022-03-05'),(4,4,1,'2022-03-06'),(5,4,1,'2022-03-07'),(6,4,1,'2022-03-08'),(7,4,1,'2022-03-09'),(8,4,1,'2022-03-10'),(9,4,1,'2022-03-11'),(10,4,1,'2022-03-11'),(11,4,1,'2022-03-11'),(12,4,1,'2022-03-11'),(13,4,1,'2022-03-11'),(14,4,1,'2022-03-11'),(15,4,1,'2022-03-12'),(16,4,1,'2022-03-12'),(17,4,1,'2022-03-14'),(18,4,1,'2022-10-16'),(19,1,6,'2022-04-23'),(20,1,6,'2022-04-22'),(21,1,6,'2022-04-21'),(22,2,6,'2022-04-21'),(23,2,6,'2022-04-22'),(24,2,6,'2022-04-23'),(25,8,1,'2022-02-24'),(26,8,1,'2021-10-10');
/*!40000 ALTER TABLE `sighting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superhero`
--

DROP TABLE IF EXISTS `superhero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `superhero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(450) DEFAULT 'Unknown',
  `superpower` varchar(450) DEFAULT 'Unknown',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superhero`
--

LOCK TABLES `superhero` WRITE;
/*!40000 ALTER TABLE `superhero` DISABLE KEYS */;
INSERT INTO `superhero` VALUES (1,'Catwoman','acts like a cat','very agile'),(2,'Batman','Rich guy who has an array of tools','Born Rich and uses it well'),(4,'Spiderman','Got bit by a spider and protects New York','Makes web, has Spidey Sense, can climb real good.'),(5,'Wolverine','Government experiment turned badass','Metal skeletal enhancements, rapid injury recovery'),(6,'Cyclops','Mutant you cant look at you safely','Death Ray eyes'),(7,'Dr.Strange','Surgeon Turned Magic Master','Master at many of the Arcane Arts'),(8,'Human Torch','Too hot to handle','Control Flame');
/*!40000 ALTER TABLE `superhero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superhero_br_organization`
--

DROP TABLE IF EXISTS `superhero_br_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `superhero_br_organization` (
  `superheroId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`superheroId`,`organizationId`),
  KEY `fk_organizationId` (`organizationId`),
  CONSTRAINT `fk_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_superheroId` FOREIGN KEY (`superheroId`) REFERENCES `superhero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superhero_br_organization`
--

LOCK TABLES `superhero_br_organization` WRITE;
/*!40000 ALTER TABLE `superhero_br_organization` DISABLE KEYS */;
INSERT INTO `superhero_br_organization` VALUES (4,2),(5,1),(6,1),(7,2);
/*!40000 ALTER TABLE `superhero_br_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'superherosightings'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-08 10:05:38
