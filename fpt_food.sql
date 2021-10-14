CREATE DATABASE  IF NOT EXISTS `fpt_food` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fpt_food`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fpt_food
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `salesQuantity` int DEFAULT NULL,
  `reviewPoint` float DEFAULT NULL,
  `numberOfReview` int DEFAULT NULL,
  `foodCategoryId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `restaurantId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e3a3c6af27a006002d38aaa6984` (`foodCategoryId`),
  KEY `FK_7c9492140866fe2a0867b381dcf` (`restaurantId`),
  CONSTRAINT `FK_7c9492140866fe2a0867b381dcf` FOREIGN KEY (`restaurantId`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FK_e3a3c6af27a006002d38aaa6984` FOREIGN KEY (`foodCategoryId`) REFERENCES `food_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES ('4rfd4t5',NULL,NULL,NULL,NULL,'Cơm Bò Sốt','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_bo_sot_f4jj7r.jpg',35000,23,4.7,19,'food','nguyentuan'),('6758hrg',NULL,NULL,NULL,NULL,'Nem Nướng','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/nem_nuong_ep5gai.jpg',25000,64,4.9,43,'noodle','dongdoi'),('c7364u2',NULL,NULL,NULL,NULL,'Bún Bò Huế','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_bo_hue_mhhwtp.jpg',35000,130,4.7,23,'noodle','dongdoi'),('ds67fg',NULL,NULL,NULL,NULL,'Phở Cuốn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/pho_cuon_ogiiyb.jpg',30000,34,4.6,25,'noodle','nguyentuan'),('e09u45',NULL,NULL,NULL,NULL,'Bún Cá','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_ca_k0pehs.jpg',30000,189,4.9,89,'noodle','namle'),('e736ey4',NULL,NULL,NULL,NULL,'Bánh Mì','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/banh_mi_nam_dinh_msawq2.jpg',25000,119,4.7,10,'sandwich','nguyentuan'),('eug56g',NULL,NULL,NULL,NULL,'Cơm Xã Đàn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_xa_dan_e5mj3l.jpg',30000,43,4.9,29,'food','namle'),('fdg654d',NULL,NULL,NULL,NULL,'Gà Rán KFC','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/ga_ran_KFC_ks9mei.jpg',35000,38,4.7,20,'streetfood','dongdoi'),('h387gfd',NULL,NULL,NULL,NULL,'Bánh Mì Bò','https://res.cloudinary.com/fpt-food/image/upload/v1633196769/FPT%20FOOD/banh_mi_bo_qrhpwg.jpg',25000,55,4.5,34,'sandwich','dongdoi'),('o0394eu',NULL,NULL,NULL,NULL,'Hindlands Coffe','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/highlands_coffe_hrvjsp.jpg',49000,128,4.8,22,'drinks','hindlandcoffe'),('re84rt4',NULL,NULL,NULL,NULL,'Bún Trộn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/bun_tron_tchl5i.jpg',30000,56,4.7,45,'noodle','namle'),('rt5fg6',NULL,NULL,NULL,NULL,'Cơm Thố','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_tho_khxisw.jpg',35000,89,4.8,77,'food','nguyentuan'),('u7584riu',NULL,NULL,NULL,NULL,'Bún Miến Ngan','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/bun_mien_ngan_dnac1q.jpg',30000,34,4.6,29,'noodle','nguyentuan'),('u8437ed',NULL,NULL,NULL,NULL,'Cơm Gà','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/com_ga_lmyncr.jpg',30000,112,4.5,6,'food','dongdoi');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_category`
--

DROP TABLE IF EXISTS `food_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_category` (
  `id` varchar(36) NOT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES ('drinks',NULL,'2021-09-21 00:00:00',NULL,NULL,'Đồ uống','https://res.cloudinary.com/fpt-food/image/upload/v1632839707/FPT%20FOOD/drinks_uqnubb.png'),('food',NULL,'2021-09-20 00:00:00',NULL,NULL,'Đồ ăn','https://res.cloudinary.com/fpt-food/image/upload/v1632896251/FPT%20FOOD/food_jf6qur.jpg'),('noodle',NULL,'2021-09-22 00:00:00',NULL,NULL,'Bún phở','https://res.cloudinary.com/fpt-food/image/upload/v1632896252/FPT%20FOOD/noodle_kd4vhx.jpg'),('sandwich',NULL,'2021-09-23 00:00:00',NULL,NULL,'Bánh mì','https://res.cloudinary.com/fpt-food/image/upload/v1632896251/FPT%20FOOD/sand_wich_pbx7or.jpg'),('streetfood',NULL,'2021-09-24 00:00:00',NULL,NULL,'Vỉa hè','https://res.cloudinary.com/fpt-food/image/upload/v1632839413/FPT%20FOOD/street_food_bayyhn.png');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `migrations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `timestamp` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migrations`
--

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;
INSERT INTO `migrations` VALUES (1,1570200270081,'CreateTables1570200270081'),(2,1570200490072,'SeedUsersRoles1570200490072');
/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('dongdoi',NULL,NULL,NULL,NULL,'Đồng Đội',NULL,NULL),('hindlandcoffe',NULL,NULL,NULL,NULL,'Hindland Coffe',NULL,NULL),('namle',NULL,NULL,NULL,NULL,'Nam Lê',NULL,NULL),('nguyentuan',NULL,NULL,NULL,NULL,'Nguyễn Tuấn',NULL,NULL);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `activated` tinyint NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_a000cca60bcf04454e72769949` (`phone`),
  UNIQUE KEY `IDX_2d443082eccd5198f95f2a36e2` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('10e8ddd1-012f-4baa-bbc6-c663bd7b8421',NULL,NULL,NULL,NULL,NULL,NULL,'0968565986',0,'$2b$08$X0AkiyClyOdkX9QXdeIhuu4Pilo84n50Vny3ZP37mYJrq2WKYejYu',NULL,'Tran Quang Dung'),('153dfa19-8efe-4694-ad36-f7b90e3d98e9',NULL,NULL,NULL,NULL,NULL,NULL,'0968955465',0,'$2b$08$FSjIFvz9JppcE8ED5K8s8OkwbT6T1EPn9L2HhRpNNDak6g87/RcNW',NULL,'Nguyen Thi Thom'),('39c75f68-926e-4ee6-a2b8-40c4b85129eb',NULL,NULL,NULL,NULL,NULL,NULL,'0964895214',0,'$2b$08$NlktqUza5v2Nf.LP67CRH.Ny8BEiseeRr0gQkTm1QE2FbgEg4zxyu',NULL,'Nguyen Thi Tra My'),('406309b0-445c-4505-a923-2c5e8e90158a',NULL,NULL,NULL,NULL,NULL,NULL,'0362649546',0,'$2b$08$BFI26oY/Gtx3Es1wD1eRdeNV3s5.eXPc0w/3ep.gTdeoGu6KnA1YW',NULL,'Tran Thi Hoai Thu'),('54d0c4bb-dec3-4108-a9d7-e8e4ee336a29','system',NULL,'system',NULL,'admin','admin@localhost.it','0968904963',1,'$2b$10$e8rNzl1wZHf7iM6iGH/wGO37lHZSRWNRt/YxDcHfV4a4bF/4jWFq6','https://res.cloudinary.com/fpt-food/image/upload/v1632841010/FPT%20FOOD/profile_bax7pp.jpg','Admin'),('57f44f26-7488-4fd4-8c7c-76aa985d5817',NULL,NULL,NULL,NULL,NULL,NULL,'0968565987',0,'$2b$08$OsNMRDR.UxRqM7lHkKn3WeKWM29SamFR9TZddttV14Pjgdb5IjNJy',NULL,'Tran Quang Dung'),('7318959e-b8b1-4c90-a3a6-a302ac237f80','system',NULL,'system',NULL,'user','user@localhost.it','0968904964',1,'$2b$10$TstT7iCGttQdSsqT76A87OEZ7YeCWa6D.hGGcLPe70jhjtiNCbV1u','','Người Dùng Thử'),('7d6e75d8-ca0c-4bee-9c6d-a973471cbc56','system',NULL,'system',NULL,'system','system@localhost.it','0968904961',1,'$2b$10$H58CoGECIpcnbSghEAPQkuE5KPP6oa0IfaJ7CNjcrYAOloEdCFQSu','','Hệ Thống'),('a6935885-52af-49d0-9633-16dba3850cb3','system',NULL,'system',NULL,'anonymoususer','anonymoususer@localhost.it','0968904962',1,'$2b$10$vACKvuOv7J1Q2oNVCc7iLOG7RAmhCZxefUcKZQgk20fC0jVQMP0i.','','Vô Danh'),('b9ebb5b5-4e86-4508-8ecb-6b0d1b3b1010',NULL,NULL,NULL,NULL,NULL,NULL,'0968904965',0,'$2b$08$w0Tn0E2w0mupjz3aTRI8Weq1y07Fq.eEZeYqalh932JmC.IppCzTa',NULL,'Ha Van Quang'),('d65f2f7e-b28f-425d-99fc-8490a4c2c482',NULL,NULL,NULL,NULL,NULL,NULL,'0968904967',0,'$2b$08$QyG3aQe4HRWRqPwNwELqzuI5L/xwT.Kh1qEap/C1VGpaRyGcjGID6',NULL,'Tran Thi Truc'),('db85f7c5-596d-42d6-a338-373300e43af8',NULL,NULL,NULL,NULL,NULL,NULL,'0968904966',0,'$2b$08$ETKIXY4JHboPxPE3k9ScAeeVtNGXriHleNjMRdfC2.3Fi37PSoO6m',NULL,'Ly Anh Dung'),('dd307754-c1e5-4e47-bb1c-7e02b33526ad',NULL,NULL,NULL,NULL,NULL,NULL,'0345845961',0,'$2b$08$Gay5Zan2EGHKuKr.tjQBtuLxIsdqm3s6Y6zsmXxMIAxq.j/9dtzw.',NULL,'Let Tieu Bao'),('e6df2b82-6146-48b1-9134-35100edbf8f9',NULL,NULL,NULL,NULL,NULL,NULL,'0968904968',0,'$2b$08$kiW2mQ0ycqa309Jo1EJtcuZk51s18Q8PEgyhahA19Oru2E4dtIin2',NULL,'Do Thi Thu Hang');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_authorities_authority`
--

DROP TABLE IF EXISTS `users_authorities_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_authorities_authority` (
  `usersId` varchar(36) NOT NULL,
  `authorityName` varchar(255) NOT NULL,
  PRIMARY KEY (`usersId`,`authorityName`),
  KEY `IDX_40f9a46b50f9eaea1cd2abeecb` (`usersId`),
  KEY `IDX_e2819143582ac430b17fd89de8` (`authorityName`),
  CONSTRAINT `FK_40f9a46b50f9eaea1cd2abeecbd` FOREIGN KEY (`usersId`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_e2819143582ac430b17fd89de81` FOREIGN KEY (`authorityName`) REFERENCES `authority` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_authorities_authority`
--

LOCK TABLES `users_authorities_authority` WRITE;
/*!40000 ALTER TABLE `users_authorities_authority` DISABLE KEYS */;
INSERT INTO `users_authorities_authority` VALUES ('54d0c4bb-dec3-4108-a9d7-e8e4ee336a29','ROLE_ADMIN'),('54d0c4bb-dec3-4108-a9d7-e8e4ee336a29','ROLE_USER'),('7318959e-b8b1-4c90-a3a6-a302ac237f80','ROLE_USER'),('7d6e75d8-ca0c-4bee-9c6d-a973471cbc56','ROLE_ADMIN'),('7d6e75d8-ca0c-4bee-9c6d-a973471cbc56','ROLE_USER');
/*!40000 ALTER TABLE `users_authorities_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'fpt_food'
--

--
-- Dumping routines for database 'fpt_food'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-13 13:13:15
