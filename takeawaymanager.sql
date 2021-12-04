/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : takeawaymanager

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2021-12-04 10:32:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', 'Chengyui', '四川大学江安校区西园六舍');
INSERT INTO `address` VALUES ('2', 'god', '五川大学江安校区西园六舍');
INSERT INTO `address` VALUES ('3', 'admin', '六川大学江安校区西园六舍');
INSERT INTO `address` VALUES ('4', 'costomer', '七川大学江安校区西园六舍');
INSERT INTO `address` VALUES ('5', 'aragaki', '日本东京');
INSERT INTO `address` VALUES ('6', 'llq', '西元十八');
INSERT INTO `address` VALUES ('7', '海星', '同济大学');

-- ----------------------------
-- Table structure for `foodlist`
-- ----------------------------
DROP TABLE IF EXISTS `foodlist`;
CREATE TABLE `foodlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FoodName` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodlist
-- ----------------------------
INSERT INTO `foodlist` VALUES ('1', '冒菜', '21');
INSERT INTO `foodlist` VALUES ('2', '干锅肥牛', '16');
INSERT INTO `foodlist` VALUES ('3', '小炒', '15');
INSERT INTO `foodlist` VALUES ('4', '新疆拉面（配包子鸡腿）', '11');
INSERT INTO `foodlist` VALUES ('5', '盖饭套餐', '10');
INSERT INTO `foodlist` VALUES ('6', '张良麻辣烫', '29');

-- ----------------------------
-- Table structure for `foodorder`
-- ----------------------------
DROP TABLE IF EXISTS `foodorder`;
CREATE TABLE `foodorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `foodName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `done` int(11) DEFAULT '0',
  `evaluation` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodorder
-- ----------------------------
INSERT INTO `foodorder` VALUES ('1', 'god', '冒菜', '20', '1', '好');
INSERT INTO `foodorder` VALUES ('2', 'god', '干锅肥牛', '16', '1', '好');
INSERT INTO `foodorder` VALUES ('3', 'god', '小炒', '15', '1', '好');
INSERT INTO `foodorder` VALUES ('4', 'god', '新疆拉面（配包子鸡腿）', '11', '1', '好');
INSERT INTO `foodorder` VALUES ('5', 'costomer', '小炒', '15', '1', '好');
INSERT INTO `foodorder` VALUES ('6', 'admin', '小炒', '15', '1', '好');
INSERT INTO `foodorder` VALUES ('7', 'god', '新疆拉面（配包子鸡腿）', '11', '1', '好');
INSERT INTO `foodorder` VALUES ('8', 'god', '冒菜', '21', '1', '好');
INSERT INTO `foodorder` VALUES ('9', 'god', '小炒', '15', '1', '好');
INSERT INTO `foodorder` VALUES ('10', 'god', '张良麻辣烫', '29', '1', 'nice');
INSERT INTO `foodorder` VALUES ('11', 'Chengyui', '冒菜', '21', '0', '好');
INSERT INTO `foodorder` VALUES ('12', 'aragaki', '张良麻辣烫', '29', '0', '好');
INSERT INTO `foodorder` VALUES ('13', 'aragaki', '新疆拉面（配包子鸡腿）', '11', '0', '好');
INSERT INTO `foodorder` VALUES ('14', 'llq', '张良麻辣烫', '29', '1', '好');
INSERT INTO `foodorder` VALUES ('15', '程锦国', '新疆拉面（配包子鸡腿）', '11', '0', '好');

-- ----------------------------
-- Table structure for `incomeperday`
-- ----------------------------
DROP TABLE IF EXISTS `incomeperday`;
CREATE TABLE `incomeperday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(30) DEFAULT NULL,
  `income` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of incomeperday
-- ----------------------------
INSERT INTO `incomeperday` VALUES ('1', '2021.12.04', '269');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passWord` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Type` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '1');
INSERT INTO `user` VALUES ('2', 'costomer', '123456', '2');
INSERT INTO `user` VALUES ('3', 'carrier', '123456', '3');
INSERT INTO `user` VALUES ('4', 'god', '123456', '3');
INSERT INTO `user` VALUES ('5', 'reg', '1', '3');
INSERT INTO `user` VALUES ('6', 'cjg', '111', '3');
INSERT INTO `user` VALUES ('7', '程锦国', '123', '3');
INSERT INTO `user` VALUES ('8', '娄智鹏', '111', '3');
INSERT INTO `user` VALUES ('9', 'Chengyui', '123456', '3');
INSERT INTO `user` VALUES ('10', 'aragaki', '520', '3');
INSERT INTO `user` VALUES ('11', 'llq', '111', '3');
INSERT INTO `user` VALUES ('12', '海星', 'sb', '3');
