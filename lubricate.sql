/*
MySQL Data Transfer
Source Host: localhost
Source Database: lubricate
Target Host: localhost
Target Database: lubricate
Date: 2013/10/19 16:19:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for devicelubricate
-- ----------------------------
DROP TABLE IF EXISTS `devicelubricate`;
CREATE TABLE `devicelubricate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `device_num` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lubricate
-- ----------------------------
DROP TABLE IF EXISTS `lubricate`;
CREATE TABLE `lubricate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `cleancycle` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `lube` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `refuelcycle` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `remindday` int(11) NOT NULL,
  `flag` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lubricate_record
-- ----------------------------
DROP TABLE IF EXISTS `lubricate_record`;
CREATE TABLE `lubricate_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `devnum` varchar(255) NOT NULL,
  `lubricatetime` datetime NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `devicelubricate` VALUES ('1', '0', 'menzuo001', '13121829903', '张三');
INSERT INTO `lubricate` VALUES ('1', '2', '六个月', '', 'VG460润滑油', '变幅、起升减速箱', 'menzuo001', '一周', '油池', '3', '0');
INSERT INTO `lubricate` VALUES ('2', '2', '六个月', '', 'N320润滑油', '行走减速箱', 'menzuo001', '一周', '油池', '3', '0');
INSERT INTO `lubricate` VALUES ('3', '2', '六个月', '', 'VG460润滑油', '旋转行星减速箱', 'menzuo001', '一周', '油池', '3', '0');
INSERT INTO `lubricate` VALUES ('4', '0', '', '', 'N320齿轮油', '操纵手柄及各脚踏铰点', 'menzuo001', '按需', '销轴', '3', '1');
INSERT INTO `lubricate` VALUES ('5', '0', '六个月', '', '刹车油', '回转刹车补充油箱', 'menzuo001', '按需', '油箱', '3', '1');
INSERT INTO `lubricate` VALUES ('6', '0', '', '', 'HL-20机械油', '起升、变幅及回转制动器杠杆铰点', 'menzuo001', '按需', '销轴', '3', '1');
INSERT INTO `lubricate` VALUES ('7', '0', '六个月', '', 'HL-20机械油', '起升、变幅及回转制动器杠杆铰点', 'menzuo001', '按需', '销轴', '3', '1');
INSERT INTO `lubricate` VALUES ('8', '2', '三个月', '', 'DB——10变压器', '起升、变幅及行走液压推杆油泵', 'menzuo001', '一周', '油池', '3', '0');
INSERT INTO `lubricate` VALUES ('9', '1', '', '', 'HL-20机械油', '行走制动器杠杆铰点', 'menzuo001', '一周', '销轴', '3', '0');
INSERT INTO `lubricate` VALUES ('10', '1', '', '', 'HL-20机械油', '超负荷限制器杠杆', 'menzuo001', '一天', '销轴', '3', '0');
INSERT INTO `lubricate` VALUES ('11', '2', '', '', '钙基润滑脂', '吊钩', 'menzuo001', '三个月', '油杯', '3', '1');
INSERT INTO `lubricate` VALUES ('12', '1', '', '', '钙基润滑脂', '防转接头', 'menzuo001', '三个月', '油杯', '3', '1');
INSERT INTO `lubricate` VALUES ('13', '1', '', '', '石墨钙基润滑脂', '齿条', 'menzuo001', '三个月', '涂', '3', '1');
INSERT INTO `lubricate` VALUES ('14', '1', '', '', '钙基润滑脂', '各电机轴承', 'menzuo001', '三个月', '油杯', '3', '1');
INSERT INTO `lubricate` VALUES ('15', '2', '', '', '石墨钙基润滑脂', '起重机钢丝绳', 'menzuo001', '三个月', '涂', '3', '1');
INSERT INTO `lubricate` VALUES ('16', '1', '', '', '锂基润滑脂', '回转部分集中润滑', 'menzuo001', '三个月', '', '3', '1');
INSERT INTO `lubricate_record` VALUES ('2', 'menzuo001', '2013-09-08 00:00:00', '8');
INSERT INTO `lubricate_record` VALUES ('3', 'jjsaos', '2013-10-10 00:00:00', '2');
