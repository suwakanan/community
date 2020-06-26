/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-06-22 13:36:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ad`
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `image` varchar(256) DEFAULT NULL,
  `gmt_start` bigint(20) DEFAULT NULL,
  `gmt_end` bigint(20) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `pos` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '父类ID',
  `type` int(11) NOT NULL COMMENT '父类类型',
  `commentator` bigint(20) NOT NULL COMMENT '评论人id',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '更新时间',
  `like_count` bigint(20) DEFAULT '0',
  `content` varchar(1024) DEFAULT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '1', '1592552993623', '1592552993623', '0', '这是一个回复内容', '0');
INSERT INTO `comment` VALUES ('2', '1', '1', '13', '1592729484522', '1592729484522', '0', '这是一个回复内容', '0');
INSERT INTO `comment` VALUES ('3', '2', '1', '13', '1592731309506', '1592731309506', '0', '康一康能不能正确传递数据', '0');
INSERT INTO `comment` VALUES ('4', '2', '1', '13', '1592731388137', '1592731388137', '0', '来自页面的测试', '0');
INSERT INTO `comment` VALUES ('5', '2', '1', '13', '1592731646899', '1592731646898', '0', '测试测试', '0');
INSERT INTO `comment` VALUES ('6', '6', '1', '13', '1592732191910', '1592732191910', '0', '测试', '0');
INSERT INTO `comment` VALUES ('7', '6', '1', '13', '1592732671462', '1592732671462', '0', '再来一个测试', '0');
INSERT INTO `comment` VALUES ('8', '6', '1', '13', '1592732725011', '1592732725011', '0', '回复测试测试', '0');
INSERT INTO `comment` VALUES ('9', '3', '1', '13', '1592738959492', '1592738959492', '0', '我是回复', '0');
INSERT INTO `comment` VALUES ('10', '17', '1', '13', '1592743185051', '1592743185051', '0', '测试回复', '0');
INSERT INTO `comment` VALUES ('11', '6', '1', '13', '1592743388174', '1592743388174', '0', '测试回复', '0');
INSERT INTO `comment` VALUES ('12', '6', '1', '13', '1592749627096', '1592749627096', '0', '我又来回复了', '0');
INSERT INTO `comment` VALUES ('13', '6', '1', '13', '1592749767046', '1592749767046', '0', '没错还是我', '0');
INSERT INTO `comment` VALUES ('14', '4', '1', '13', '1592785133633', '1592785133633', '0', '回复回复', '0');
INSERT INTO `comment` VALUES ('15', '4', '1', '13', '1592785654523', '1592785654523', '0', '重构后的新评论', '1');
INSERT INTO `comment` VALUES ('16', '15', '2', '13', '1592786044962', '1592786044962', '0', '重构的评论的评论', '0');
INSERT INTO `comment` VALUES ('17', '15', '2', '13', '1592788942113', '1592788942113', '0', '还是评论的评论', '0');
INSERT INTO `comment` VALUES ('18', '15', '2', '13', '1592789220688', '1592789220688', '0', '这是最新的评论', '0');
INSERT INTO `comment` VALUES ('19', '14', '2', '13', '1592791837431', '1592791837431', '0', '我来回复了', '0');
INSERT INTO `comment` VALUES ('20', '15', '2', '13', '1592792934311', '1592792934311', '0', '又来评论了', '0');
INSERT INTO `comment` VALUES ('21', '18', '1', '13', '1592802329596', '1592802329596', '0', '这是第一个回复', '2');
INSERT INTO `comment` VALUES ('22', '21', '2', '13', '1592802341994', '1592802341994', '0', '这是在回复里面回复的回复', '0');
INSERT INTO `comment` VALUES ('23', '21', '2', '13', '1592802407708', '1592802407708', '0', '这是第二个回复里面的回复的回复', '0');
INSERT INTO `comment` VALUES ('24', '6', '1', '13', '1592802732388', '1592802732388', '0', '回复', '0');
INSERT INTO `comment` VALUES ('25', '4', '1', '13', '1592802828478', '1592802828478', '0', '来试试回复', '1');
INSERT INTO `comment` VALUES ('26', '25', '2', '13', '1592802834650', '1592802834650', '0', '再试一下', '0');
INSERT INTO `comment` VALUES ('27', '18', '1', '13', '1592802977125', '1592802977125', '0', '试一下回复', '1');
INSERT INTO `comment` VALUES ('28', '27', '2', '13', '1592802984202', '1592802984202', '0', '试一下回复的回复', '0');
INSERT INTO `comment` VALUES ('29', '4', '1', '13', '1592803114125', '1592803114125', '0', '试试回复', '1');
INSERT INTO `comment` VALUES ('30', '29', '2', '13', '1592803119076', '1592803119076', '0', '再试一下', '0');
INSERT INTO `comment` VALUES ('31', '6', '1', '13', '1592803309659', '1592803309659', '0', '回复一下', '1');
INSERT INTO `comment` VALUES ('32', '31', '2', '13', '1592803317538', '1592803317538', '0', '再试试回复', '0');
INSERT INTO `comment` VALUES ('33', '20', '1', '13', '1592803342828', '1592803342828', '0', '我来发个回复试一下', '2');
INSERT INTO `comment` VALUES ('34', '33', '2', '13', '1592803354181', '1592803354181', '0', '我再来发个回复试一下', '0');
INSERT INTO `comment` VALUES ('35', '33', '2', '13', '1592803437025', '1592803437025', '0', '再试试功能', '0');
INSERT INTO `comment` VALUES ('36', '20', '1', '13', '1592803443601', '1592803443601', '0', '可以了', '0');

-- ----------------------------
-- Table structure for `nav`
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `priority` int(11) DEFAULT '0',
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nav
-- ----------------------------

-- ----------------------------
-- Table structure for `notification`
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notifier` bigint(20) NOT NULL,
  `receiver` bigint(20) NOT NULL,
  `outerid` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '标题', '内容', '1591627133718', '1591627133718', '13', '2', '15', '0', '标签,标签2');
INSERT INTO `question` VALUES ('2', '标题2', '内容2', '1591628480548', '1591628480548', '13', '3', '24', '0', '标签2');
INSERT INTO `question` VALUES ('3', '标题3', '内容3', '1591629672504', '1591629672504', '13', '1', '27', '0', '标签3');
INSERT INTO `question` VALUES ('4', '发布问题标题', '发布问题补充', '1591685133183', '1591685133183', '13', '4', '62', '0', '标签4');
INSERT INTO `question` VALUES ('6', '标题5', '补充56', '1591688623622', '1591688623622', '13', '8', '68', '0', '标签5');
INSERT INTO `question` VALUES ('7', '标题6', '补充6', '1591688647145', '1591688647145', '13', '0', '1', '0', '标签6,标签6,标签6');
INSERT INTO `question` VALUES ('8', '标题7', '补充7', '1591688665141', '1591688665141', '13', '0', '0', '0', '标签7,标签7,标签7');
INSERT INTO `question` VALUES ('9', '8', '8', '1591688682161', '1591688682161', '13', '0', '0', '0', '8');
INSERT INTO `question` VALUES ('10', '9', '9', '1591688697526', '1591688697526', '13', '0', '0', '0', '9');
INSERT INTO `question` VALUES ('11', '10', '10', '1591688704337', '1591688704337', '13', '0', '0', '0', '10');
INSERT INTO `question` VALUES ('12', '11', '11', '1591688708641', '1591688708641', '13', '0', '0', '0', '11');
INSERT INTO `question` VALUES ('13', '12', '12', '1591688713513', '1591688713513', '13', '0', '0', '0', '12');
INSERT INTO `question` VALUES ('14', '15', '15', '1591713430180', '1591713430180', '13', '0', '0', '0', '15');
INSERT INTO `question` VALUES ('15', '测试修改', '测试修改', '1591774017606', '1591782579567', '13', '0', '0', '0', '修改');
INSERT INTO `question` VALUES ('16', '测试发布2', '测试发布2', '1592534071403', '1592535087380', '13', '0', '2', null, '测试发布2');
INSERT INTO `question` VALUES ('17', '测试修改3', '测试发布3', '1592535420859', '1592535420859', '13', '1', '10', '0', '测试发布3');
INSERT INTO `question` VALUES ('18', '怎么快速学习spring boot', '如题，how to learn spring boot fast', '1592744100617', '1592744100617', '13', '2', '11', '0', 'spring boot');
INSERT INTO `question` VALUES ('19', '为啥我总觉得哪里不对', '确实不对', '1592744290220', '1592744290220', '13', '0', '3', '0', '不对劲');
INSERT INTO `question` VALUES ('20', '我来发个修改试一下', '我来发个修改试一下', '1592803334132', '1592803334132', '13', '2', '8', '0', '测试修改');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `big` varchar(256) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', '66005691', '南小嘘', 'c53cef72-b85d-4dcf-8121-c8ee1e87089f', '1591757794518', '1592803423842', null, 'https://avatars1.githubusercontent.com/u/66005691?v=4');
