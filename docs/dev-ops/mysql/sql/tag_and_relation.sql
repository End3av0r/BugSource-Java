/*
 Navicat Premium Dump SQL

 Source Server         : Docker80
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:13306
 Source Schema         : bug_source

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 23/12/2025 - 新增标签表和关联表
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '标签描述',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tag_name` (`tag_name`) USING BTREE COMMENT '标签名称唯一索引',
  KEY `idx_tag_name` (`tag_name`) USING BTREE COMMENT '标签名称索引',
  KEY `idx_is_deleted` (`is_deleted`) USING BTREE COMMENT '逻辑删除索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- ----------------------------
-- Table structure for vulnerability_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `vulnerability_tag_relation`;
CREATE TABLE `vulnerability_tag_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `vuln_id` int NOT NULL COMMENT '漏洞ID，关联vulnerability表',
  `tag_id` int NOT NULL COMMENT '标签ID，关联tag表',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_vuln_tag` (`vuln_id`, `tag_id`) USING BTREE COMMENT '漏洞-标签唯一索引，防止重复关联',
  KEY `idx_vuln_id` (`vuln_id`) USING BTREE COMMENT '漏洞ID索引',
  KEY `idx_tag_id` (`tag_id`) USING BTREE COMMENT '标签ID索引',
  CONSTRAINT `fk_relation_vuln` FOREIGN KEY (`vuln_id`) REFERENCES `vulnerability` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_relation_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='漏洞-标签关联表';

SET FOREIGN_KEY_CHECKS = 1;
