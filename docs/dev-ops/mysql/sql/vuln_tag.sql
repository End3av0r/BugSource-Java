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

 Date: 17/03/2025 11:19:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vuln_tag
-- ----------------------------
DROP TABLE IF EXISTS `vuln_tag`;
CREATE TABLE `vuln_tag`  (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `vuln_id` int NOT NULL COMMENT '外键',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签\r\n',
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = armscii8 COLLATE = armscii8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vuln_tag
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
