/*
 Navicat Premium Data Transfer

 Source Server         : 远程数据库mysql8
 Source Server Type    : MySQL
 Source Server Version : 100332
 Source Host           : www.meet0208.icu:30336
 Source Schema         : gift_shop

 Target Server Type    : MySQL
 Target Server Version : 100332
 File Encoding         : 65001

 Date: 27/05/2023 17:50:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理表id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'libb', '123456', '2023-05-08 18:35:20', NULL, 'https://www.meet0208.icu:9092/IUrK5I.jpg', 'admin', '0');
INSERT INTO `admin` VALUES (2, 'admin', '123456', '2023-05-09 00:30:12', NULL, 'https://www.meet0208.icu:9092/ltoGtg.jpg', 'admin', '0');
INSERT INTO `admin` VALUES (3, 'admin2', '123456', NULL, NULL, NULL, NULL, '1');
INSERT INTO `admin` VALUES (4, '测试1', '123456', NULL, NULL, NULL, NULL, '1');
INSERT INTO `admin` VALUES (5, '测试2.1', '123456', NULL, NULL, NULL, NULL, '1');
INSERT INTO `admin` VALUES (6, '测试2', '111111', NULL, NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int NOT NULL AUTO_INCREMENT COMMENT '客户表id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户联系方式',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户地址',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '大法', 'admin', '134', '广西来宾', '2023-05-07 20:53:16', '2023-05-13 00:35:41', 'https://www.meet0208.icu:9092/OeSOpJ.jpg', '0');
INSERT INTO `customer` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `customer` VALUES (3, '张三', '1234566', '1852', '广西南宁邕宁区南宁学院', NULL, '2023-05-13 00:57:09', 'https://www.meet0208.icu:9092/OeSOpJ.jpg', '0');
INSERT INTO `customer` VALUES (4, '三三', '123456', '123', '我我我我', NULL, '2023-05-13 00:57:09', '', '1');
INSERT INTO `customer` VALUES (5, 'user', '1234', NULL, NULL, NULL, '2023-05-13 00:57:11', '', '1');
INSERT INTO `customer` VALUES (6, 'user', '1234', NULL, NULL, NULL, '2023-05-22 23:15:02', NULL, '1');
INSERT INTO `customer` VALUES (7, '测试', '123', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `customer` VALUES (8, '测试', NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `customer` VALUES (9, 'dasd', 'dasdas', 'dsad', 'dsad', NULL, NULL, 'dsad', '1');
INSERT INTO `customer` VALUES (10, '测试', '123456', '13481092344', '广西南宁', NULL, NULL, ' ', '1');

-- ----------------------------
-- Table structure for gifts
-- ----------------------------
DROP TABLE IF EXISTS `gifts`;
CREATE TABLE `gifts`  (
  `gift_id` int NOT NULL AUTO_INCREMENT COMMENT '礼品id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '礼品名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '礼品描述',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '礼品价格',
  `stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '礼品库存',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述标题',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片连接',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`gift_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gifts
-- ----------------------------
INSERT INTO `gifts` VALUES (1, '充电宝', '支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充支持65W快充', 134.90, '3', '快速充电宝', '2023-05-11 21:48:51', '2023-05-12 12:29:08', 'https://www.meet0208.icu:9092/BpIZA8.jpg', '0');
INSERT INTO `gifts` VALUES (2, 'IPad', '精致好看', 4800.00, '1', '苹果ipad', '2023-05-11 21:49:44', '2023-05-12 12:29:15', 'https://www.meet0208.icu:9092/CbvPkh.jpg', '0');
INSERT INTO `gifts` VALUES (3, '鼠标', '好用', 128.00, '1', '罗技G304', '2023-05-11 21:49:44', '2023-05-23 10:10:25', 'https://www.meet0208.icu:9092/bfjWH1.jpg', '0');
INSERT INTO `gifts` VALUES (4, '辣椒酱', '好吃爱吃！', 16.00, '100', '好吃', '2023-05-11 21:50:56', '2023-05-12 12:29:29', 'https://www.meet0208.icu:9092/PmdzCs.jpg', '0');
INSERT INTO `gifts` VALUES (5, '用过的眼药水', '用过，介意勿拍。用过，介意勿拍。用过，介意勿拍。用过，介意勿拍。用过，介意勿拍。', 15.00, '1', '眼药水', '2023-05-11 21:51:34', '2023-05-12 12:29:35', 'https://www.meet0208.icu:9092/OfMm1e.jpg', '0');
INSERT INTO `gifts` VALUES (6, '米家小台灯', '好用', 178.00, '1', '台灯', '2023-05-11 21:52:02', '2023-05-12 12:29:39', 'https://www.meet0208.icu:9092/eieVW7.jpg', '0');
INSERT INTO `gifts` VALUES (7, '黎锦斌', '帅哥', 20000.00, '2000', '帅哥', NULL, '2023-05-12 12:29:41', 'https://www.meet0208.icu:9092/8WZQTQ.jpg', '1');
INSERT INTO `gifts` VALUES (8, '黎锦斌', '晚睡早起精神好', 20.00, '222', '大帅哥', NULL, '2023-05-12 14:36:32', 'https://www.meet0208.icu:9092/8WZQTQ.jpg', '1');
INSERT INTO `gifts` VALUES (9, '测试', NULL, NULL, NULL, NULL, NULL, '2023-05-22 23:14:55', NULL, '1');
INSERT INTO `gifts` VALUES (10, '测试23', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `inventory_id` int NOT NULL COMMENT '库存id',
  `gift_id` int NULL DEFAULT NULL COMMENT '礼品id',
  `number` int NULL DEFAULT NULL COMMENT '数量',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '进货价格',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`inventory_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `gift_id` int NULL DEFAULT NULL,
  `number` int NULL DEFAULT NULL,
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_product
-- ----------------------------
INSERT INTO `order_product` VALUES (1, 1, 1, 2, '0');
INSERT INTO `order_product` VALUES (2, 1, 2, 2, '0');
INSERT INTO `order_product` VALUES (3, 1, 5, 1, '1');
INSERT INTO `order_product` VALUES (7, 1, 5, 2, '1');
INSERT INTO `order_product` VALUES (8, 4, 1, 3, '0');
INSERT INTO `order_product` VALUES (9, 5, 1, 3, '0');
INSERT INTO `order_product` VALUES (10, 1, 3, 2, '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `customer_id` int NULL DEFAULT NULL COMMENT '客户id',
  `create_at` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `update_at` datetime NULL DEFAULT NULL COMMENT '订单状态',
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `completed_at` datetime NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, '2023-05-27 00:00:58', NULL, '已完成', '0', '2023-05-27 00:00:58', '内蒙古自治区 呼和浩特市 新城区', '黎锦斌', '南宁学院');
INSERT INTO `orders` VALUES (4, 3, '2023-05-27 00:00:46', NULL, '已完成', '0', '2023-05-27 00:00:46', '河北省 秦皇岛市 抚宁区', 'libb', '北大');
INSERT INTO `orders` VALUES (5, 1, '2023-05-27 00:05:12', NULL, '已完成', '0', '2023-05-27 00:05:12', '北京市 市辖区 东城区', 'libb', '清华大学');

SET FOREIGN_KEY_CHECKS = 1;
