CREATE DATABASE  IF NOT EXISTS `springmvc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `springmvc`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: springmvc
-- ------------------------------------------------------
-- Server version	5.5.42

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
-- Table structure for table `email_account_info`
--

DROP TABLE IF EXISTS `email_account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `from_user` varchar(255) NOT NULL COMMENT '发件人',
  `passwd` varchar(255) NOT NULL COMMENT '密码',
  `host` varchar(255) NOT NULL COMMENT 'HOST',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件发送账号信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_account_info`
--

LOCK TABLES `email_account_info` WRITE;
/*!40000 ALTER TABLE `email_account_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_info`
--

DROP TABLE IF EXISTS `email_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `to_user` char(18) DEFAULT NULL,
  `from_user` char(21) DEFAULT NULL,
  `title` varchar(225) DEFAULT NULL,
  `subject` varchar(225) DEFAULT NULL,
  `content` varchar(5120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_info`
--

LOCK TABLES `email_info` WRITE;
/*!40000 ALTER TABLE `email_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `permissions_name` varchar(20) NOT NULL COMMENT '权限名',
  `permissions_value` varchar(20) NOT NULL COMMENT '权限值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'1','2016-11-23 16:34:28','2016-12-15 16:41:28','管理员查看','admin:read'),(2,'1','2016-11-23 16:35:47','2016-12-15 16:41:12','管理员添加','admin:insert'),(3,'1','2016-11-23 16:36:23','2016-12-15 16:41:00','管理员删除','admin:delete'),(4,'1','2016-11-23 16:36:55','2016-12-15 16:40:46','管理员修改','admin:update'),(5,'1','2016-11-23 16:37:54','2016-11-23 16:37:54','用户修改','user:update'),(6,'1','2016-11-23 16:38:22','2016-11-23 16:38:22','用户查看','user:read'),(7,'1','2016-11-23 16:38:45','2016-12-21 10:56:56','用户添加','user:insert'),(8,'1','2016-11-23 16:39:25','2016-11-23 16:39:25','用户删除','user:delete');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role_name` varchar(40) NOT NULL DEFAULT '' COMMENT '角色名',
  `role_value` varchar(20) NOT NULL DEFAULT '' COMMENT '角色值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'1','2016-11-23 16:34:28','2016-12-15 16:37:24','管理员','admin'),(2,'1','2016-11-23 16:34:28','2016-12-15 17:16:08','普通用户','user'),(9,'1','2018-04-08 11:30:56','2018-04-08 11:30:56','测试','test');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=630 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (137,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,8),(138,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,7),(139,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,6),(140,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,5),(141,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,4),(142,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,3),(143,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,2),(144,'1','2016-12-15 16:37:24','2016-12-15 16:37:24',1,1),(164,'1','2016-12-15 17:16:08','2016-12-15 17:16:08',2,8),(165,'1','2016-12-15 17:16:08','2016-12-15 17:16:08',2,7),(166,'1','2016-12-15 17:16:08','2016-12-15 17:16:08',2,6),(167,'1','2016-12-15 17:16:08','2016-12-15 17:16:08',2,5),(629,'1','2018-04-08 11:30:56','2018-04-08 11:30:56',9,6);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `user_no` varchar(40) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` int(1) DEFAULT '1' COMMENT '性别(0为女 1为男)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','2016-11-23 16:20:17','2016-12-15 16:34:50','admin','YWRtaW4=','管理员',1),(2,'1','2016-11-23 16:21:39','2016-12-22 10:55:54','user','dXNlcg==','普通用户',1),(6,'1','2018-04-08 11:28:00','2018-04-08 11:31:08','test','MTIzNDU2','测试',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_logs`
--

DROP TABLE IF EXISTS `user_login_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `roncoo_no` varchar(255) NOT NULL COMMENT '龙果账号',
  `login_ip` varchar(255) NOT NULL COMMENT '登录IP',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_logs`
--

LOCK TABLES `user_login_logs` WRITE;
/*!40000 ALTER TABLE `user_login_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '账号',
  `roles_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (49,'1','2016-12-15 16:34:50','2016-12-15 16:34:50',1,2),(50,'1','2016-12-15 16:34:50','2016-12-15 16:34:50',1,1),(119,'1','2016-12-22 10:55:54','2016-12-22 10:55:54',2,2),(121,'1','2018-04-08 11:31:08','2018-04-08 11:31:08',6,9);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vul_category`
--

DROP TABLE IF EXISTS `vul_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vul_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vul_category`
--

LOCK TABLES `vul_category` WRITE;
/*!40000 ALTER TABLE `vul_category` DISABLE KEYS */;
INSERT INTO `vul_category` VALUES (1,'SQL注入','2018-03-16 11:15:33'),(2,'信息泄露','2018-03-16 11:15:33'),(3,'普通反射XSS','2018-03-16 11:15:33'),(4,'存储XSS','2018-03-16 11:15:33'),(5,'上传漏洞','2018-03-16 11:15:33'),(6,'文件包含','2018-03-16 11:15:33'),(7,'逻辑漏洞','2018-03-16 11:15:33'),(8,'权限绕过','2018-03-16 11:15:33'),(9,'URL跳转漏洞','2018-03-16 11:15:33'),(10,'目录浏览漏洞','2018-03-16 11:15:33'),(11,'SSRF漏洞','2018-03-16 11:15:33'),(12,'越权操作','2018-03-16 11:15:33'),(13,'CSRF','2018-03-16 11:15:33'),(14,'短信轰炸','2018-03-16 11:15:33'),(15,'弱口令','2018-03-16 11:15:33'),(16,'内部绝密信息泄露','2018-03-16 11:15:33'),(17,'开发缺陷','2018-03-16 11:15:33'),(18,'命令执行','2018-03-16 11:15:33');
/*!40000 ALTER TABLE `vul_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-18  9:21:45
