/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : commons_utils

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/08/2021 18:10:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(10) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(10) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES ('07f42f8fe9224e48869a0bd290ed349a', 1, '宁在春', '127.0.0.1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-15 10:10:45', '', '2021-08-15 10:10:45');
INSERT INTO `tb_log` VALUES ('2b5c6d46-51c8-4f23-bcda-26a0dae71b5c', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, 'http://localhost:8093/user/log', '查询用户信息', '2021-08-14 15:24:24', '', '2021-08-14 15:24:24');
INSERT INTO `tb_log` VALUES ('5f86d84c-240b-4374-afd2-f8797a089d41', 1, '宁在春', '127.0.0.1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-15 01:08:15', '', '2021-08-15 01:08:15');
INSERT INTO `tb_log` VALUES ('6dc9db11-e7e8-43fc-8f5f-ea9ab1d64a5c', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-14 10:12:29', '', '2021-08-14 10:12:29');
INSERT INTO `tb_log` VALUES ('7ee87b92603a42c8ac880006a35401e4', 1, '宁在春', '127.0.0.1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-15 01:09:04', '', '2021-08-15 01:09:04');
INSERT INTO `tb_log` VALUES ('92461d2a-3b46-432a-879c-58f0958963c8', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-14 09:57:09', NULL, '2021-08-14 09:57:09');
INSERT INTO `tb_log` VALUES ('d10cabd5-55c7-48b3-b2e5-8aed66cf4cc1', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-15 00:22:58', '', '2021-08-15 00:22:58');
INSERT INTO `tb_log` VALUES ('dafb6e3f-1e70-4b11-901b-484bbfda2508', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, 'http://localhost:8091/user/log', '查询用户信息', '2021-08-15 00:05:47', '', '2021-08-15 00:05:47');
INSERT INTO `tb_log` VALUES ('e5b49465-b20a-453f-b15c-b284733f2f8e', 1, '宁在春', '0:0:0:0:0:0:0:1', 1, '127.0.0.1', '查询用户信息', '2021-08-15 01:04:31', '', '2021-08-15 01:04:31');

SET FOREIGN_KEY_CHECKS = 1;
