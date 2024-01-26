-- MySQL dump 10.13  Distrib 8.2.0, for macos13.5 (arm64)
--
-- Host: ucka.veleri.hr    Database: ipangos
-- ------------------------------------------------------
-- Server version	5.5.5-10.5.15-MariaDB-0+deb11u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `filmVideoteka`
--

DROP TABLE IF EXISTS `filmVideoteka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filmVideoteka` (
  `film_id` int(11) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(100) NOT NULL,
  `redatelj` varchar(100) NOT NULL,
  `trajanje` varchar(15) NOT NULL,
  `god_izdanja` varchar(15) NOT NULL,
  PRIMARY KEY (`film_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmVideoteka`
--

LOCK TABLES `filmVideoteka` WRITE;
/*!40000 ALTER TABLE `filmVideoteka` DISABLE KEYS */;
INSERT INTO `filmVideoteka` VALUES (1,'The Dark Knight','Christopher Nolan','152','2008'),(2,'2001 a Space Odyssey','Stanley Kubrick','149','1968'),(3,'Gladiator','Ridley Scott','170','2000'),(4,'Joker','Todd Philips','122','2019'),(5,'A Clockwork Orange','Stanley Kubrick','137','1971'),(6,'Dr. Strangelove','Stanley Kubrick','95','1963'),(7,'Mad Max: Fury Road','George Miller','120','2015'),(8,'Watchmen','Zack Snyder','215','2009'),(9,'Blade Runner (The Final Cut)','Ridley Scott','119','1982'),(10,'The Lord of the Rings: The Fellowship of the Ring','Peter Jackson','178','2001'),(11,'The Lord of the Rings: The Two Towers','Peter Jackson','180','2002'),(12,'The Lord of the Rings: Return of the King','Peter Jackson','201','2003'),(13,'Memento','Christopher Nolan','113','2000'),(14,'300','Zack Snyder','117','2006'),(15,'Inglorious Bastards','Quentin Tarantino','153','2009'),(18,'They Live','John Carpenter','94','1988'),(19,'The Thing','John Carpenter','109','1982'),(20,'Videodrome','David Cronenberg','89','1983'),(21,'Dead Ringers','David Cronenberg','115','1988'),(22,'Mulholland Drive','David Lynch','146','2001'),(23,'Lost Highway','David Lynch','134','1997'),(24,'Inland Empire','David Lynch','180','2006'),(25,'Dune','David Lynch','137','1984'),(26,'Brasil','Terry Gilliam','132','1985'),(27,'Monty Python and the Holy Grail','Terry Gilliam','91','1975'),(28,'Twelve Monkeys','Terry Gilliam','129','1995'),(29,'The Tenant','Roman Polanski','126','1976'),(30,'The Ninth Gate','Roman Polanski','132','1999'),(31,'The Pianist','Roman Polanski','150','2002'),(32,'The Butterfly Effect','Eric Bress','173 ','2004'),(33,'Transporter 2','Louis Leterrier','157','2005'),(34,'No Country for Old Man','Ethan Coen','122','2007'),(35,'Inception','Christopher Nolan','148','2010'),(36,'Harry Potter and the Deathly Hallows','David Baron','130','2011'),(37,'The Dark Knight Rises','Christopher Nolan','164','2012'),(38,'Gravity','David Heyman','151','2013'),(39,'Interstellar','Christopher Nolan','169','2014');
/*!40000 ALTER TABLE `filmVideoteka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clanVideoteka`
--

DROP TABLE IF EXISTS `clanVideoteka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clanVideoteka` (
  `clan_id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `broj_mob` varchar(20) NOT NULL,
  `lozinka` varchar(70) NOT NULL,
  PRIMARY KEY (`clan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clanVideoteka`
--

LOCK TABLES `clanVideoteka` WRITE;
/*!40000 ALTER TABLE `clanVideoteka` DISABLE KEYS */;
INSERT INTO `clanVideoteka` VALUES (1,'ivor','pangos','123','123'),(2,'mile','kitic','234','234'),(3,'clan 3','test','345','345'),(4,'pup','pupic','09112334567','1234'),(5,'Jerry','Spriger','091112233','1234'),(6,'Igor','Pangos','09120169000','0000'),(7,'igo','dd','0912016000','1');
/*!40000 ALTER TABLE `clanVideoteka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posudbaVideoteka`
--

DROP TABLE IF EXISTS `posudbaVideoteka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posudbaVideoteka` (
  `id_posudba` int(11) NOT NULL AUTO_INCREMENT,
  `clan_id` int(11) NOT NULL,
  `film_id` int(11) NOT NULL,
  `datum_posudbe` date NOT NULL,
  `datum_vracanja` date NOT NULL,
  PRIMARY KEY (`id_posudba`),
  KEY `film_id` (`film_id`),
  KEY `clan_id` (`clan_id`),
  CONSTRAINT `posudbaVideoteka_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `filmVideoteka` (`film_id`) ON UPDATE CASCADE,
  CONSTRAINT `posudbaVideoteka_ibfk_2` FOREIGN KEY (`clan_id`) REFERENCES `clanVideoteka` (`clan_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posudbaVideoteka`
--

LOCK TABLES `posudbaVideoteka` WRITE;
/*!40000 ALTER TABLE `posudbaVideoteka` DISABLE KEYS */;
INSERT INTO `posudbaVideoteka` VALUES (1,1,1,'2023-01-24','2023-02-22'),(2,2,29,'2023-01-10','2024-01-10'),(3,3,20,'2023-02-01','2023-06-01'),(4,1,1,'2024-01-07','2024-01-10'),(5,5,33,'2012-01-11','2022-01-12'),(6,1,30,'2023-10-15','2024-02-15'),(7,1,33,'2023-10-16','2024-02-16'),(8,1,21,'2023-10-16','2024-02-16');
/*!40000 ALTER TABLE `posudbaVideoteka` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26 10:17:23
