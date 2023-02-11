/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : store_carousel

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 11/02/2023 13:48:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `carousel_id` int NOT NULL AUTO_INCREMENT,
  `img_path` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `describes` char(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_id` int NULL DEFAULT NULL COMMENT '广告关联的商品图片',
  `priority` int NULL DEFAULT 10 COMMENT '优先级',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, 'public/imgs/cms_1.jpg', '123456', 1, 10);
INSERT INTO `carousel` VALUES (2, 'public/imgs/cms_2.jpg', '123456', 2, 10);
INSERT INTO `carousel` VALUES (3, 'public/imgs/cms_3.jpg', '123456', 3, 10);
INSERT INTO `carousel` VALUES (4, 'public/imgs/cms_4.jpg', '123456', 4, 10);

SET FOREIGN_KEY_CHECKS = 1;
