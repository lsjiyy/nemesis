/*
Navicat MySQL Data Transfer

Source Server         : MySql5.7
Source Server Version : 50725
Source Host           : 127.0.0.1:3306
Source Database       : tx-manager

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-19 10:16:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tx_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `ex_state` smallint(6) NOT NULL,
  `group_id` varchar(60) DEFAULT NULL,
  `mod_id` varchar(100) DEFAULT NULL,
  `registrar` smallint(6) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `transaction_state` int(11) DEFAULT NULL,
  `unit_id` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
