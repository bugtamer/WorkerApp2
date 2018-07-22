CREATE DATABASE  IF NOT EXISTS `WorkerApp2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `WorkerApp2`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: WorkerApp2
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `educacion`
--

DROP TABLE IF EXISTS `educacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educacion` (
  `edu_id` int(11) NOT NULL,
  `educacion` varchar(255) NOT NULL,
  KEY `FKsbjy4q6w3hqm9suiem2bfyu9q` (`edu_id`),
  CONSTRAINT `FKsbjy4q6w3hqm9suiem2bfyu9q` FOREIGN KEY (`edu_id`) REFERENCES `manitas` (`fk_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `experiencia`
--

DROP TABLE IF EXISTS `experiencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiencia` (
  `exp_id` int(11) NOT NULL,
  `experiencia` varchar(255) NOT NULL,
  KEY `FKsmrfqhtj7dttsff94c43k8ajq` (`exp_id`),
  CONSTRAINT `FKsmrfqhtj7dttsff94c43k8ajq` FOREIGN KEY (`exp_id`) REFERENCES `manitas` (`fk_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manitas`
--

DROP TABLE IF EXISTS `manitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manitas` (
  `profesion` varchar(255) NOT NULL,
  `fk_usu` int(11) NOT NULL,
  PRIMARY KEY (`fk_usu`),
  CONSTRAINT `FK84vl94m4x3t8nl1wm2qgtjsyi` FOREIGN KEY (`fk_usu`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `men_id` int(11) NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) DEFAULT NULL,
  `timestamp` datetime NOT NULL,
  `urlImagen` varchar(255) DEFAULT NULL,
  `emisor_usu_id` int(11) NOT NULL,
  `receptor_usu_id` int(11) NOT NULL,
  PRIMARY KEY (`men_id`),
  KEY `FKhd7ltt8m7shmwjwhp7xp9qa8s` (`emisor_usu_id`),
  KEY `FKk24qlb6dn3yh1regbjud4c2vd` (`receptor_usu_id`),
  CONSTRAINT `FKhd7ltt8m7shmwjwhp7xp9qa8s` FOREIGN KEY (`emisor_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKk24qlb6dn3yh1regbjud4c2vd` FOREIGN KEY (`receptor_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ubicacion`
--

DROP TABLE IF EXISTS `ubicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicacion` (
  `ubi_id` int(11) NOT NULL AUTO_INCREMENT,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  PRIMARY KEY (`ubi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usu_id` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) NOT NULL,
  `url_avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fk_ubi` int(11) NOT NULL,
  PRIMARY KEY (`usu_id`),
  UNIQUE KEY `UK_334t2e48y60e75et53jb9wh9t` (`fk_ubi`),
  CONSTRAINT `FKh5kvpi2lsemuko37nthrps9yx` FOREIGN KEY (`fk_ubi`) REFERENCES `ubicacion` (`ubi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `valoracion`
--

DROP TABLE IF EXISTS `valoracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valoracion` (
  `val_id` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `autor_usu_id` int(11) NOT NULL,
  `receptor_fk_usu` int(11) NOT NULL,
  PRIMARY KEY (`val_id`),
  KEY `FKk4pnx9q8sjcbbgy2r47k74cvo` (`autor_usu_id`),
  KEY `FKmutaoidtesn179y6mrl5800em` (`receptor_fk_usu`),
  CONSTRAINT `FKk4pnx9q8sjcbbgy2r47k74cvo` FOREIGN KEY (`autor_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKmutaoidtesn179y6mrl5800em` FOREIGN KEY (`receptor_fk_usu`) REFERENCES `manitas` (`fk_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'WorkerApp2'
--

--
-- Dumping routines for database 'WorkerApp2'
--
/*!50003 DROP FUNCTION IF EXISTS `calcDistanciaEnKm` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcDistanciaEnKm`(lat1 DOUBLE, lon1 DOUBLE, lat2 DOUBLE, lon2 DOUBLE) RETURNS double
    NO SQL
    DETERMINISTIC
BEGIN

	DECLARE radLat1 double DEFAULT lat1 * PI()/180;
	DECLARE radLat2 double DEFAULT lat2 * PI()/180;
	DECLARE theta double DEFAULT lon1 - lon2;
	DECLARE radTheta double DEFAULT theta * PI()/180;
	DECLARE dist double DEFAULT SIN(radLat1) * SIN(radLat2) + COS(radLat1) * COS(radLat2) * COS(radTheta);
    
	IF dist > 1 THEN
		SET dist = 1;
	END IF;
    
	SET dist = ACOS(dist);
	SET dist = dist * 180/PI();
	SET dist = dist * 60 * 1.1515;
	SET dist = dist * 1.609344;
    
	RETURN dist;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-20  0:22:16
