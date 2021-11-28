/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : takeawaymanager

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2021-11-28 22:43:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `foodlist`
-- ----------------------------
DROP TABLE IF EXISTS `foodlist`;
CREATE TABLE `foodlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FoodName` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodlist
-- ----------------------------
INSERT INTO `foodlist` VALUES ('1', '冒菜西施', '31');
INSERT INTO `foodlist` VALUES ('2', '干锅肥牛', '16');
INSERT INTO `foodlist` VALUES ('3', '小炒', '15');
INSERT INTO `foodlist` VALUES ('4', '新疆拉面（配包子鸡腿）', '11');
INSERT INTO `foodlist` VALUES ('5', '盖饭套餐', '10');

-- ----------------------------
-- Table structure for `foodorder`
-- ----------------------------
DROP TABLE IF EXISTS `foodorder`;
CREATE TABLE `foodorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `foodName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodorder
-- ----------------------------
INSERT INTO `foodorder` VALUES ('1', 'god', '冒菜', '20');
INSERT INTO `foodorder` VALUES ('2', 'god', '干锅肥牛', '16');
INSERT INTO `foodorder` VALUES ('3', 'god', '小炒', '15');
INSERT INTO `foodorder` VALUES ('4', 'god', '新疆拉面（配包子鸡腿）', '11');
INSERT INTO `foodorder` VALUES ('5', 'costomer', '小炒', '15');
INSERT INTO `foodorder` VALUES ('6', 'admin', '小炒', '15');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `passWord` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Type` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '1');
INSERT INTO `user` VALUES ('2', 'costomer', '123456', '2');
INSERT INTO `user` VALUES ('3', 'carrier', '123456', '3');
INSERT INTO `user` VALUES ('4', 'god', '123456', '3');
