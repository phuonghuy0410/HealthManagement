-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: health_management
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `healthprofiles`
--

DROP TABLE IF EXISTS `healthprofiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `healthprofiles` (
  `profile_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `bmi` float DEFAULT NULL,
  `water_intake` float DEFAULT NULL,
  `steps` int DEFAULT NULL,
  `heart_rate` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `healthprofiles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthprofiles`
--

LOCK TABLES `healthprofiles` WRITE;
/*!40000 ALTER TABLE `healthprofiles` DISABLE KEYS */;
INSERT INTO `healthprofiles` VALUES (1,1,23.67,2.5,5000,75,27,165.7,65),(2,2,21.83,3.4,7998,80,29,175.2,67),(3,3,26.23,1.5,3000,90,NULL,NULL,NULL),(4,4,20.2,2.8,7000,70,NULL,NULL,NULL),(5,5,22.49,3.2,6000,78,NULL,NULL,NULL),(8,7,27.89,1.5,2000,96,20,155,67),(9,9,22.49,2,1000,80,20,170,65);
/*!40000 ALTER TABLE `healthprofiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `sent_time` datetime DEFAULT NULL,
  `sender_id` int DEFAULT NULL,
  `receiver_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'123','2025-05-30 00:13:17',1,3),(2,'Tôi bận','2025-05-30 00:25:39',3,1),(3,'11','2025-05-31 17:16:24',1,3);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plans`
--

DROP TABLE IF EXISTS `plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plans` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `plans_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (1,1,'Chạy bộ','Ngày chạy 2000 bước','2025-05-22'),(2,1,'Nghỉ ngơi','Không làm gì cả','2025-05-29'),(3,1,'Tập tạ','100 cái','2025-05-29'),(4,1,'Nhảy dây','Chơi','2025-05-30'),(5,1,'Đá cầu','Chơi','2025-05-31');
/*!40000 ALTER TABLE `plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reminders`
--

DROP TABLE IF EXISTS `reminders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reminders` (
  `reminder_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `reminder_type` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `reminder_time` varchar(255) DEFAULT NULL,
  `reminder_date` date DEFAULT NULL,
  PRIMARY KEY (`reminder_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reminders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reminders`
--

LOCK TABLES `reminders` WRITE;
/*!40000 ALTER TABLE `reminders` DISABLE KEYS */;
INSERT INTO `reminders` VALUES (1,1,'Water','Nhớ uống nước mỗi 2 giờ','08:00:00',NULL),(2,2,'Exercise','Thực hiện bài tập yoga','09:00:00',NULL),(3,3,'Rest','Nghỉ ngơi và giãn cơ','15:00:00',NULL),(4,4,'Water','Uống nước sau khi tập','10:30:00',NULL),(5,5,'Exercise','Bài tập đạp xe 60 phút','18:00:00',NULL),(6,1,'Rest','aa','13:03:00',NULL),(7,1,'Rest','1111','19:21','2025-05-31');
/*!40000 ALTER TABLE `reminders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `report_date` date NOT NULL,
  `bmi` float DEFAULT NULL,
  `steps` int DEFAULT NULL,
  `calories_burned` float DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,1,'2025-05-15',22.86,5000,200,'Sức khỏe tốt, duy trì tốc độ'),(2,2,'2025-05-15',21.26,8000,150,'Tăng cường tập luyện để giảm mỡ'),(3,3,'2025-05-14',26.23,3000,300,'Cần giảm cân, theo dõi nhịp tim'),(4,4,'2025-05-13',20.2,7000,100,'Cố gắng duy trì lượng nước'),(5,5,'2025-05-15',22.49,6000,400,'Tập gym tốt, tăng thời gian nghỉ ngơi');
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `height` float NOT NULL,
  `weight` float NOT NULL,
  `role` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1','password1','Nguyen Van A',25,1.75,70,'User','2025-05-16 14:36:32'),(2,'user2','password2','Le Thi B',30,1.68,60,'User','2025-05-16 14:36:32'),(3,'expert1','password3','Tran Van C',40,1.8,85,'Expert','2025-05-16 14:36:32'),(4,'user3','password4','Pham Van D',22,1.65,55,'User','2025-05-16 14:36:32'),(5,'expert2','password5','Nguyen Thi E',35,1.7,65,'Expert','2025-05-16 14:36:32'),(6,'phuonghuy','12345','Lê Phương Huy',19,170,60,'User',NULL),(7,'thanhlong','12345','Nguyễn Thành Long',0,0,0,'User',NULL),(8,'hlv','12345','Huy',0,0,0,'Expert',NULL),(9,'123','123456','Duy',0,0,0,'User',NULL),(10,'user20','123456','Lê Phương Huy',0,0,0,'Người dùng',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workouts`
--

DROP TABLE IF EXISTS `workouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workouts` (
  `workout_id` int NOT NULL AUTO_INCREMENT,
  `calories_burned` int DEFAULT NULL,
  `workout_date` date DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `exercise_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `calories` int NOT NULL,
  `completed` bit(1) NOT NULL,
  `date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `caloriesBurned` int DEFAULT NULL,
  PRIMARY KEY (`workout_id`),
  KEY `FKpf8ql3wbw2drijbk1ugfvki3d` (`user_id`),
  CONSTRAINT `FKpf8ql3wbw2drijbk1ugfvki3d` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workouts`
--

LOCK TABLES `workouts` WRITE;
/*!40000 ALTER TABLE `workouts` DISABLE KEYS */;
INSERT INTO `workouts` VALUES (1,2000,'2025-05-29',100,'Tot','Tot',1,NULL,NULL,0,_binary '\0',NULL,NULL,NULL),(2,2000,'2025-05-30',20,'Chạy bộ','Vui',1,NULL,NULL,0,_binary '\0',NULL,NULL,NULL),(3,2000,'2025-05-30',20,'Nhảy dây','Vui',1,NULL,NULL,0,_binary '\0',NULL,NULL,NULL);
/*!40000 ALTER TABLE `workouts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-31 21:34:49
