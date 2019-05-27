/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80003
Source Host           : localhost:3306
Source Database       : freeter

Target Server Type    : MYSQL
Target Server Version : 80003
File Encoding         : 65001

Date: 2019-05-27 11:21:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `alias_name` varchar(50) DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0',
  `sex` varchar(32) DEFAULT NULL COMMENT 'man:男 woman:女',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Jone', '20', 'test1@baomidou.com', 'liqi_01', '0', 'man', '0');
INSERT INTO `user` VALUES ('2', 'Jack', '21', 'test2@baomidou.com', 'liqi_02', '0', 'man', '0');
INSERT INTO `user` VALUES ('3', 'Tom', '21', 'test3@baomidou.com', 'liqi_03', '0', 'man', '0');
INSERT INTO `user` VALUES ('4', 'Sandy', '20', 'test4@baomidou.com', 'liqi_04', '0', 'woman', '0');
INSERT INTO `user` VALUES ('5', 'billie', '20', 'test5@baomidou.com', 'liqi_05', '0', 'woman', '0');
INSERT INTO `user` VALUES ('1128910018956279809', 'optlocker', '22', 'test@baomidou.com', 'alias_name_optlocker', '0', 'man', '1');
INSERT INTO `user` VALUES ('1128910843644485634', 'optlocker', '21', 'test@baomidou.com', 'alias_name_optlocker', '0', 'man', '1');
DROP TRIGGER IF EXISTS `hardware_room_id`;
DELIMITER ;;
CREATE TRIGGER `hardware_room_id` BEFORE UPDATE ON `user` FOR EACH ROW BEGIN
	IF (OLD.age=20 AND OLD.id=1 AND NEW.age=21)
	 THEN 
		SET NEW.age=OLD.age;
	END IF;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `user_test_trigger`;
DELIMITER ;;
CREATE TRIGGER `user_test_trigger` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
	IF (OLD.age=20 AND OLD.id=1 AND NEW.age=21)
	 THEN 
		INSERT INTO user_trigger_log(update_time,old_field,new_field)
			VALUES(NOW(),OLD.age,NEW.age);
	END IF;
END
;;
DELIMITER ;
