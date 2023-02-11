/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : store_user

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 11/02/2023 13:49:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址主键',
  `linkman` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '联系人',
  `phone` varchar(13) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '详细地址',
  `user_id` int NULL DEFAULT NULL COMMENT '用户主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (4, 'carl', '18127662226', '天津兴安盟印台区', 17);
INSERT INTO `address` VALUES (5, 'carl', '15276537256', '天桥地下', 19);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` char(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `PASSWORD` char(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_phonenumber` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, 'admin3322', 'DC483E80A7A0BD9EF71D8CF973673924', '15276537256');
INSERT INTO `user` VALUES (5, 'admin332244', '9701C3182D0AAC2E089BB6665FC430A5', NULL);
INSERT INTO `user` VALUES (6, 'zhao11111', '8348898D532023C994920A16074C8387', NULL);
INSERT INTO `user` VALUES (7, 'zhaoweifeng1111', '47200157CE5E1B5EFA5258250680C708', NULL);
INSERT INTO `user` VALUES (9, 'admin321', '47200157CE5E1B5EFA5258250680C708', NULL);
INSERT INTO `user` VALUES (10, 'admin1111', '33E8299D8A8E65A8B3D67C1E9F4C8B5', NULL);
INSERT INTO `user` VALUES (11, 'admin321321', '9701C3182D0AAC2E089BB6665FC430A5', NULL);
INSERT INTO `user` VALUES (12, 'admion123', 'A511CDA3184F6567298D6063B2CC989', '15276537256');
INSERT INTO `user` VALUES (13, '冯秀兰^冯秀兰', '970D4EBF474458DD82D4787247418EC4', '19629101907');
INSERT INTO `user` VALUES (14, 'test001', '9CB2197DA5A32B2BE7B76AD403ED45AD', '15276537256');
INSERT INTO `user` VALUES (15, 'test002', 'B97C802206F9604AB9E4E5E242414467', '15276537256');
INSERT INTO `user` VALUES (16, 'Jennifer', '81DF9468CDEC0AB13E8046FCE7F4AA81', '17634407875');
INSERT INTO `user` VALUES (17, 'carl123', 'EBCADA146651723D07FEF0F5D8D411BB', '17634407875');
INSERT INTO `user` VALUES (18, 'test123', '2787693EA1110FB77ADB60BD22CBAE58', '15276537256');
INSERT INTO `user` VALUES (19, 'test1234', 'EF142A79ED7B01A42057A8E34E491326', '15276537256');

SET FOREIGN_KEY_CHECKS = 1;
