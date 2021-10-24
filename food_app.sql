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
  `lastModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `salesQuantity` int DEFAULT NULL,
  `reviewPoint` float DEFAULT NULL,
  `numberOfReview` int DEFAULT NULL,
  `foodCategoryId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `restaurantId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
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
INSERT INTO `food` VALUES ('4rfd4t5',NULL,NULL,'Cơm Bò Sốt','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_bo_sot_f4jj7r.jpg',35000,23,4.7,19,'food','nguyentuan','2021-10-13 07:16:59','2021-10-13 07:16:59'),('6758hrg',NULL,NULL,'Nem Nướng','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/nem_nuong_ep5gai.jpg',25000,64,4.9,43,'noodle','dongdoi','2021-10-13 07:16:59','2021-10-13 07:16:59'),('c7364u2',NULL,NULL,'Bún Bò Huế','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_bo_hue_mhhwtp.jpg',35000,130,4.7,23,'noodle','dongdoi','2021-10-13 07:16:59','2021-10-13 07:16:59'),('ds67fg',NULL,NULL,'Phở Cuốn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/pho_cuon_ogiiyb.jpg',30000,34,4.6,25,'noodle','nguyentuan','2021-10-13 07:16:59','2021-10-13 07:16:59'),('e09u45',NULL,NULL,'Bún Cá','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_ca_k0pehs.jpg',30000,189,4.9,89,'noodle','namle','2021-10-13 07:16:59','2021-10-13 07:16:59'),('e736ey4',NULL,NULL,'Bánh Mì','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/banh_mi_nam_dinh_msawq2.jpg',25000,119,4.7,10,'sandwich','nguyentuan','2021-10-13 07:16:59','2021-10-13 07:16:59'),('eug56g',NULL,NULL,'Cơm Xã Đàn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_xa_dan_e5mj3l.jpg',30000,43,4.9,29,'food','namle','2021-10-13 07:16:59','2021-10-13 07:16:59'),('fdg654d',NULL,NULL,'Gà Rán KFC','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/ga_ran_KFC_ks9mei.jpg',35000,38,4.7,20,'streetfood','dongdoi','2021-10-13 07:16:59','2021-10-13 07:16:59'),('h387gfd',NULL,NULL,'Bánh Mì Bò','https://res.cloudinary.com/fpt-food/image/upload/v1633196769/FPT%20FOOD/banh_mi_bo_qrhpwg.jpg',25000,55,4.5,34,'sandwich','dongdoi','2021-10-13 07:16:59','2021-10-13 07:16:59'),('o0394eu',NULL,NULL,'Hindlands Coffe','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/highlands_coffe_hrvjsp.jpg',49000,128,4.8,22,'drinks','hindlandcoffe','2021-10-13 07:16:59','2021-10-13 07:16:59'),('re84rt4',NULL,NULL,'Bún Trộn','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/bun_tron_tchl5i.jpg',30000,56,4.7,45,'noodle','namle','2021-10-13 07:16:59','2021-10-13 07:16:59'),('rt5fg6',NULL,NULL,'Cơm Thố','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/com_tho_khxisw.jpg',35000,89,4.8,77,'food','nguyentuan','2021-10-13 07:16:59','2021-10-13 07:16:59'),('u7584riu',NULL,NULL,'Bún Miến Ngan','https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/bun_mien_ngan_dnac1q.jpg',30000,34,4.6,29,'noodle','nguyentuan','2021-10-13 07:16:59','2021-10-13 07:16:59'),('u8437ed',NULL,NULL,'Cơm Gà','https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/com_ga_lmyncr.jpg',30000,112,4.5,6,'food','dongdoi','2021-10-13 07:16:59','2021-10-13 07:16:59');
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
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES ('drinks',NULL,NULL,'Đồ uống','https://res.cloudinary.com/fpt-food/image/upload/v1632839707/FPT%20FOOD/drinks_uqnubb.png','2021-10-13 07:17:00','2021-10-13 07:17:00'),('food',NULL,NULL,'Đồ ăn','https://res.cloudinary.com/fpt-food/image/upload/v1632896251/FPT%20FOOD/food_jf6qur.jpg','2021-10-13 07:17:00','2021-10-13 07:17:00'),('noodle',NULL,NULL,'Bún phở','https://res.cloudinary.com/fpt-food/image/upload/v1632896252/FPT%20FOOD/noodle_kd4vhx.jpg','2021-10-13 07:17:00','2021-10-13 07:17:00'),('sandwich',NULL,NULL,'Bánh mì','https://res.cloudinary.com/fpt-food/image/upload/v1632896251/FPT%20FOOD/sand_wich_pbx7or.jpg','2021-10-13 07:17:00','2021-10-13 07:17:00'),('streetfood',NULL,NULL,'Vỉa hè','https://res.cloudinary.com/fpt-food/image/upload/v1632839413/FPT%20FOOD/street_food_bayyhn.png','2021-10-13 07:17:00','2021-10-13 07:17:00');
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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` varchar(36) NOT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `lastModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `orderedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) NOT NULL,
  `totalAmount` int NOT NULL,
  `userId` varchar(36) DEFAULT NULL,
  `totalQuantity` int NOT NULL,
  `totalItem` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_caabe91507b3379c7ba73637b84` (`userId`),
  CONSTRAINT `FK_caabe91507b3379c7ba73637b84` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('418f740c-2c21-4efe-976f-61b9d5fa0972',NULL,'2021-10-22 22:39:56',NULL,'2021-10-22 22:39:56','2021-10-22 22:39:57','KTX - D205R',155000,'64bf8c18-d12d-441b-b782-af83b23d758a',5,2),('4875759b-e034-423f-8365-8fa82f9c9672',NULL,'2021-10-22 21:51:48',NULL,'2021-10-22 21:51:48','2021-10-22 21:51:48','KTX - A308R',95000,'54d0c4bb-dec3-4108-a9d7-e8e4ee336a29',3,2),('8ab71325-ea6d-4062-9125-c060c4520b50',NULL,'2021-10-22 22:24:58',NULL,'2021-10-22 22:24:58','2021-10-22 22:24:58','KTX - F201L',145000,'7fc11602-31e9-4ecb-ac1a-5b04af5faaaf',5,2),('8baf39ad-25b8-4041-8040-6397b0de8446',NULL,'2021-10-22 21:57:44',NULL,'2021-10-22 21:57:44','2021-10-22 21:57:45','342 - Beer 1988',90000,'54d0c4bb-dec3-4108-a9d7-e8e4ee336a29',3,2),('a2f79908-f319-4e1d-ba07-fe1196de9f2e',NULL,'2021-10-22 21:51:24',NULL,'2021-10-22 21:51:24','2021-10-22 21:51:24','KTX - A308R',165000,'54d0c4bb-dec3-4108-a9d7-e8e4ee336a29',5,2),('e9ac0965-389e-4a15-8948-18e463498340',NULL,'2021-10-22 21:57:59',NULL,'2021-10-22 21:57:59','2021-10-22 21:57:59','342 - Beer 1988',30000,'54d0c4bb-dec3-4108-a9d7-e8e4ee336a29',1,1),('f209cb90-2320-4a99-8f56-c2a8033e7278',NULL,'2021-10-22 22:43:15',NULL,'2021-10-22 22:43:15','2021-10-22 22:43:16','KTX - C501L',148000,'0b30cc13-875b-4c96-b7d4-2e3ced2b3f26',4,2);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
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
  `lastModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('dongdoi',NULL,NULL,'Đồng Đội',NULL,NULL,'2021-10-13 07:16:59','2021-10-13 07:16:59'),('hindlandcoffe',NULL,NULL,'Hindland Coffe',NULL,NULL,'2021-10-13 07:16:59','2021-10-13 07:16:59'),('namle',NULL,NULL,'Nam Lê',NULL,NULL,'2021-10-13 07:16:59','2021-10-13 07:16:59'),('nguyentuan',NULL,NULL,'Nguyễn Tuấn',NULL,NULL,'2021-10-13 07:16:59','2021-10-13 07:16:59');
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
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `activated` tinyint NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `imageUrl` varchar(255) DEFAULT '',
  `fullName` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
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
INSERT INTO `users` VALUES ('0b30cc13-875b-4c96-b7d4-2e3ced2b3f26',NULL,NULL,NULL,NULL,'0326589654',0,'$2b$08$/GXESuK2lJpxvbep.ovNn.NLpAG2IuhaZLl31T5W1l4ymU5xDlRtW','','Nguyen Xuan Hieu','2021-10-22 22:42:32','2021-10-22 22:42:32'),('54d0c4bb-dec3-4108-a9d7-e8e4ee336a29','system','system','admin','','0968904963',1,'$2b$10$e8rNzl1wZHf7iM6iGH/wGO37lHZSRWNRt/YxDcHfV4a4bF/4jWFq6','https://res.cloudinary.com/fpt-food/image/upload/v1632841010/FPT%20FOOD/profile_bax7pp.jpg','Hà Văn Quang','2021-10-13 07:16:58','2021-10-13 07:16:58'),('64bf8c18-d12d-441b-b782-af83b23d758a',NULL,NULL,NULL,NULL,'0965896321',0,'$2b$08$ItMTh0JQHIyHP3H4..TuB.xVdGJHRn3P3SXwVDUh0OEEY985rrqJq','','Nguyen Nhu Nguyet','2021-10-22 22:39:00','2021-10-22 22:39:00'),('7318959e-b8b1-4c90-a3a6-a302ac237f80','system','system','user','','0968904964',1,'$2b$10$TstT7iCGttQdSsqT76A87OEZ7YeCWa6D.hGGcLPe70jhjtiNCbV1u','','Người Dùng Thử','2021-10-13 07:16:58','2021-10-13 07:16:58'),('7a558c6d-e805-4777-958e-64d172179eef',NULL,NULL,NULL,NULL,'0352637465',0,'$2b$08$/ekVoI0oPaiF4HekOTixgO1dYb4x90QVibMfWzanOXMU7YiLYgeWW','','Do Duc Hai','2021-10-22 22:34:04','2021-10-22 22:34:04'),('7d6e75d8-ca0c-4bee-9c6d-a973471cbc56','system','system','system','','0968904961',1,'$2b$10$H58CoGECIpcnbSghEAPQkuE5KPP6oa0IfaJ7CNjcrYAOloEdCFQSu','','Hệ Thống','2021-10-13 07:16:58','2021-10-13 07:16:58'),('7fc11602-31e9-4ecb-ac1a-5b04af5faaaf',NULL,NULL,NULL,NULL,'0369865986',0,'$2b$08$9W5ETRFdKjjxHnmEFPo7g.0XNsE358hEC8D9xtclHV6.WfNR.0mRO','','Nguyen And Dung','2021-10-22 22:24:09','2021-10-22 22:24:09'),('a6935885-52af-49d0-9633-16dba3850cb3','system','system','anonymoususer','','0968904962',1,'$2b$10$vACKvuOv7J1Q2oNVCc7iLOG7RAmhCZxefUcKZQgk20fC0jVQMP0i.','','Vô Danh','2021-10-13 07:16:58','2021-10-13 07:16:58'),('d70a7027-6ad5-4f2b-80e7-5e4c6ed81e44',NULL,NULL,NULL,NULL,'0362569865',0,'$2b$08$V2n444pzja/23GK5pP2.r.JIPNoi/wpPEY0fabYYn/RlND5VKgyjy','','Nguyen Tuan Kiet','2021-10-24 03:11:07','2021-10-24 03:11:07');
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

-- Dump completed on 2021-10-24 10:26:15
