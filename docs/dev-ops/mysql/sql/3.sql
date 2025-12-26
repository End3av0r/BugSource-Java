# ************************************************************
# Sequel Ace SQL dump
# 版本号： 20086
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# 主机: localhost (MySQL 8.0.32)
# 数据库: bug_source
# 生成时间: 2025-12-26 13:44:47 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# 转储表 tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '标签描述',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识：0-未删除，1-已删除',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tag_name` (`tag_name`) USING BTREE COMMENT '标签名称唯一索引',
  KEY `idx_tag_name` (`tag_name`) USING BTREE COMMENT '标签名称索引',
  KEY `idx_is_deleted` (`is_deleted`) USING BTREE COMMENT '逻辑删除索引'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;

INSERT INTO `tag` (`id`, `tag_name`, `description`, `is_deleted`, `created_time`, `updated_time`)
VALUES
	(1,'SQL Injection','通过拼接SQL语句导致数据库被未授权访问',0,'2025-12-23 21:06:24','2025-12-23 21:06:24'),
	(2,'Command Injection','用户输入被拼接进系统命令执行',0,'2025-12-23 21:06:29','2025-12-23 21:06:29'),
	(3,'XSS','恶意脚本注入前端执行',0,'2025-12-23 21:06:36','2025-12-23 21:06:36'),
	(4,'SSRF','服务器端请求被攻击者控制',0,'2025-12-23 21:06:42','2025-12-23 21:06:42'),
	(5,'RCE','可直接执行任意代码',0,'2025-12-23 21:06:56','2025-12-23 21:06:56'),
	(6,'LFI / RFI','本地 / 远程文件包含',0,'2025-12-23 21:07:08','2025-12-23 21:07:08'),
	(7,'Deserialization','不安全反序列化',0,'2025-12-23 21:07:15','2025-12-23 21:07:15'),
	(8,'Logic Flaw','业务逻辑漏洞',0,'2025-12-23 21:07:25','2025-12-23 21:07:25'),
	(9,'Auth Bypass','身份认证绕过',0,'2025-12-23 21:07:32','2025-12-23 21:07:32'),
	(10,'Privilege Escalation','权限提升',0,'2025-12-23 21:07:37','2025-12-23 21:07:37');

/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
