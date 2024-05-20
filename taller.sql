-- MariaDB dump 10.19-11.3.1-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: taller
-- ------------------------------------------------------
-- Server version	11.3.1-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `IdCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `PhoneNumber` int(9) DEFAULT NULL,
  `DNI` char(50) DEFAULT NULL,
  `PlateNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdCustomer`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES
(1,'Jose Francisco',671984457,'12345678A','1234DDS'),
(2,'Pepillo',678224455,'23456774H','1234AFK');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `IdEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Admin` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`IdEmployee`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES
(1,'12345678A','Santiago','1234',1),
(2,'12345678E','Pepillo','1234',0),
(3,'12345678E','Alberto','1234',0);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_employee`
--

DROP TABLE IF EXISTS `repair_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repair_employee` (
  `IdRepair` int(11) NOT NULL,
  `IdEmployee` int(11) NOT NULL,
  PRIMARY KEY (`IdRepair`,`IdEmployee`) USING BTREE,
  KEY `Índice 3` (`IdRepair`),
  KEY `FK_repair_employee_employees` (`IdEmployee`),
  CONSTRAINT `FK_repair_employee_employees` FOREIGN KEY (`IdEmployee`) REFERENCES `employees` (`IdEmployee`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_repair_employee_repairs` FOREIGN KEY (`IdRepair`) REFERENCES `repairs` (`IdRepair`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_employee`
--

LOCK TABLES `repair_employee` WRITE;
/*!40000 ALTER TABLE `repair_employee` DISABLE KEYS */;
INSERT INTO `repair_employee` VALUES
(1,1);
/*!40000 ALTER TABLE `repair_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repairs`
--

DROP TABLE IF EXISTS `repairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repairs` (
  `IdRepair` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL DEFAULT '0000-00-00',
  `status` enum('En progreso','Terminada','Sin empezar') DEFAULT 'Sin empezar',
  `description` varchar(200) DEFAULT '"Introducir Descripcion Reparacion"',
  `plateNumber` varchar(200) DEFAULT 'Añadir',
  PRIMARY KEY (`IdRepair`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repairs`
--

LOCK TABLES `repairs` WRITE;
/*!40000 ALTER TABLE `repairs` DISABLE KEYS */;
INSERT INTO `repairs` VALUES
(1,'2024-11-12','En progreso','Se ha realizado un cambio de aceite','1234DDS'),
(2,'2022-12-12','Sin empezar','Cambiar pastillas de freno','1234AFK');
/*!40000 ALTER TABLE `repairs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20 17:24:42
