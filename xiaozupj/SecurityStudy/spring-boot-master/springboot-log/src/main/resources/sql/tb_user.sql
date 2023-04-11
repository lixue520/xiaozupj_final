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

 Date: 26/08/2021 18:10:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwrod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deleted` int(1) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '宁在春', '123456', 0, '2021-07-23 14:32:46', '2021-07-29 23:56:10');
INSERT INTO `tb_user` VALUES ('2', '青冬栗', 'qwerasd', 0, '2021-07-23 15:02:02', '2021-07-23 15:49:55');
INSERT INTO `tb_user` VALUES ('3', 'wwwww', 'qwerasd', 0, '2021-07-23 14:32:46', '2021-07-23 15:50:16');
INSERT INTO `tb_user` VALUES ('4', '青冬栗', 'qwerasd', 0, '2021-07-23 15:02:02', '2021-07-23 15:05:15');
INSERT INTO `tb_user` VALUES ('5', 'wyh', '123456', 0, '2021-07-23 14:32:46', '2021-07-23 14:32:48');
INSERT INTO `tb_user` VALUES ('6', '青冬栗', 'qwerasd', 1, '2021-07-23 15:02:02', '2021-07-23 15:05:15');
INSERT INTO `tb_user` VALUES ('7', 'wyh', '123456', 0, '2021-07-23 14:32:46', '2021-07-23 14:32:48');
INSERT INTO `tb_user` VALUES ('8', '青冬栗', 'qwerasd', 1, '2021-07-23 15:02:02', '2021-07-23 15:05:15');
INSERT INTO `tb_user` VALUES ('870449429965180928', 'qqqq', '987456', 1, '2021-07-29 23:35:19', '2021-07-29 23:35:19');

SET FOREIGN_KEY_CHECKS = 1;
