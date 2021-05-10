# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.28)
# Database: portal
# Generation Time: 2021-05-10 01:53:26 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table cate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cate`;

CREATE TABLE `cate` (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cate_title` varchar(255) NOT NULL DEFAULT '' COMMENT '美食标题',
  `cate_content` longtext NOT NULL COMMENT '美食介绍',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '1:有效，0无效',
  `read_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览量',
  `img` varchar(255) NOT NULL DEFAULT 'https://gitee.com/rzj2121/portal/raw/master/img/1.jpeg' COMMENT '标题图片',
  `type_id` int(11) unsigned NOT NULL COMMENT '菜系id',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='美食表';

LOCK TABLES `cate` WRITE;
/*!40000 ALTER TABLE `cate` DISABLE KEYS */;

INSERT INTO `cate` (`cate_id`, `cate_title`, `cate_content`, `gmt_create`, `gmt_modified`, `status`, `read_count`, `img`, `type_id`)
VALUES
	(9,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 14:59:10',NULL,1,7,'https://gitee.com/rzj2121/portal/raw/master/img/a256105110104834ad8a3083e1e73c3e.jpeg',1),
	(10,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:05:14',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/b2931e9776d4400e945e8ddf1c1c660c.jpeg',1),
	(11,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:05:21',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/cafd73c05c314c2591a0969ccab100da.jpeg',3),
	(12,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:05:22',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/90ed87eec9a04b86999470290072a91a.jpeg',3),
	(13,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:05:23',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/da6493e80ac042fe8af1649388ee0b7c.jpeg',3),
	(14,'hh','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:05:24',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/51cf9711823c457499430c9405a6331e.jpeg',3),
	(15,'ddd','22222asfhaifhuiahfshaosfhaiusfhashfoashf','2021-04-26 16:13:20','2021-04-28 12:46:22',0,2,'https://gitee.com/rzj2121/portal/raw/master/img/ab06fa51dc8147fcba859ccb4d69f4a5.png',2);

/*!40000 ALTER TABLE `cate` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` longtext NOT NULL COMMENT '评论内容',
  `news_id` int(11) NOT NULL COMMENT '新闻id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1:有效，0无效',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;

INSERT INTO `comment` (`comment_id`, `content`, `news_id`, `user_id`, `create_time`, `modified_time`, `status`)
VALUES
	(2,'hehe',15,2,'2021-04-28 09:50:21','2021-04-28 10:12:41',1),
	(3,'说得好',15,3,'2021-04-28 09:51:07',NULL,1),
	(4,'宁真棒',15,4,'2021-04-28 09:51:27',NULL,1),
	(5,'very good',15,5,'2021-04-28 09:51:40',NULL,1),
	(6,'大傻逼',15,6,'2021-04-28 09:51:56',NULL,1),
	(7,'谁赞成，谁反对',14,7,'2021-04-28 09:52:32',NULL,0);

/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table items_claim
# ------------------------------------------------------------

DROP TABLE IF EXISTS `items_claim`;

CREATE TABLE `items_claim` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '拾得物品id',
  `item_name` varchar(30) NOT NULL DEFAULT '' COMMENT '物品名称',
  `place_lost` varchar(80) NOT NULL DEFAULT '' COMMENT '拾得地点',
  `time_lost` varchar(50) NOT NULL DEFAULT '' COMMENT '拾得时间',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '联系电话',
  `time_published` datetime NOT NULL COMMENT '发布时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:有效,0:无效',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='寻物启事表';

LOCK TABLES `items_claim` WRITE;
/*!40000 ALTER TABLE `items_claim` DISABLE KEYS */;

INSERT INTO `items_claim` (`item_id`, `item_name`, `place_lost`, `time_lost`, `phone`, `time_published`, `user_id`, `status`)
VALUES
	(1,'书包','五邑大学','2021/5/4','1111188888','2021-05-06 10:44:40',1,1),
	(2,'身份证','五邑大学','2021','212312311','2021-05-08 15:31:45',1,1),
	(3,'身份证1','五邑大学','2021','212312311','2021-05-08 16:14:42',2,1);

/*!40000 ALTER TABLE `items_claim` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table items_search
# ------------------------------------------------------------

DROP TABLE IF EXISTS `items_search`;

CREATE TABLE `items_search` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '丢失物品id',
  `item_name` varchar(30) NOT NULL DEFAULT '' COMMENT '失物名称',
  `place_lost` varchar(80) NOT NULL DEFAULT '' COMMENT '丢失地点',
  `time_lost` varchar(50) NOT NULL DEFAULT '' COMMENT '丢失时间',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '联系电话',
  `time_published` datetime NOT NULL COMMENT '发布时间',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:有效,0:无效',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='寻物启事表';

LOCK TABLES `items_search` WRITE;
/*!40000 ALTER TABLE `items_search` DISABLE KEYS */;

INSERT INTO `items_search` (`item_id`, `item_name`, `place_lost`, `time_lost`, `phone`, `time_published`, `user_id`, `status`)
VALUES
	(1,'手机','五邑大学','2021年4月29日 2点左右','11122233344','2021-03-30 14:46:23',11,1),
	(2,'1ggff111','afafa','2021','2123123','2021-04-02 14:46:24',1,1),
	(3,'1ggff111','afafa','2021','2123123','2021-04-02 14:46:25',1,1),
	(4,'1ggff111','afafa','2021','2123123','2021-04-02 14:46:44',1,1),
	(5,'1ggff111','afafa','2021','2123123','2021-04-02 14:47:15',1,1),
	(6,'1ggff111','afafa','2021','2123123','2021-04-24 14:35:47',2,1),
	(7,'1ggff111','afafa','2022','2123123','2021-04-25 14:40:15',2,1),
	(8,'1ggff111','afafa','2021','2123123','2021-04-27 15:46:30',12,1),
	(20,'手机','五邑大学','2021','1888888888','2021-04-29 16:05:04',3,1),
	(21,'华为mate40','五邑大学西门','2021年4月25','1888888887','2021-04-29 16:49:39',4,1),
	(22,'水杯','五邑大学西门','2021年4月30','1888888887','2021-04-30 10:43:48',4,1),
	(23,'充电宝','五邑大学南门','2021年4月30','1888888881','2021-04-30 10:44:40',4,1);

/*!40000 ALTER TABLE `items_search` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table news
# ------------------------------------------------------------

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `news_title` varchar(255) NOT NULL COMMENT '新闻标题',
  `news_content` longtext NOT NULL COMMENT '新闻正文',
  `gmt_create` datetime DEFAULT NULL COMMENT '发布时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:有效,0:无效',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '点击量',
  `img` varchar(100) NOT NULL DEFAULT 'https://gitee.com/rzj2121/portal/raw/master/img/1.jpeg' COMMENT '图片地址',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表';

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;

INSERT INTO `news` (`news_id`, `news_title`, `news_content`, `gmt_create`, `gmt_modified`, `status`, `read_count`, `img`)
VALUES
	(1,'aaa','bbb',NULL,NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/df9265937d764f5082fb1b1315e0e8c2.jpg'),
	(2,'hahaha','bbbxixixi',NULL,NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/ee354b680e9a4fff83379afd7e4bb695.jpg'),
	(3,'hahaha','bbbxixixi','2021-04-09 10:25:12',NULL,0,0,'https://gitee.com/rzj2121/portal/raw/master/img/d01fc540ba39463da9199c1d083280f7.jpg'),
	(4,'xxxx','bbbxixixi','2021-04-09 14:31:25',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/495d58cfd4b34991aa67bc7549525b57.jpeg'),
	(5,'hahahaha','bbbxixixiaa','2021-04-09 14:33:59',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/ccad4fac105349cc9f2f05c01e7d7f55.jpeg'),
	(6,'121212','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n','2021-04-09 14:34:18','2021-04-25 13:11:27',1,1,'https://gitee.com/rzj2121/portal/raw/master/img/43da0fb2bf6a4010a9a7a553274c407d.jpeg'),
	(9,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-25 09:42:07',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/5aad035d0b0c4cb4a541eccb8af585c1.jpg'),
	(10,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-25 12:23:39',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/e4e886969ced4dc9bd035e9cb3914dba.jpg'),
	(11,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-25 12:34:23',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/dbf48e19ae034ffbbdceaa2bd23d7031.jpg'),
	(12,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-25 12:34:35','2021-04-25 13:11:08',1,6,'https://gitee.com/rzj2121/portal/raw/master/img/f9d373a62ed74aa7af5770905d6a9b59.png'),
	(13,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-25 15:36:34',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/d2f8dfb168dd43f9a2ac49f8feedaf2d.jpg'),
	(14,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-26 13:00:08',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/4b965ead31b348ddaf689b57b1de62bc.jpg'),
	(15,'期待！这个周日，大咖云集江门，多维度解读湾区时代蓬江作为！','西江之畔、南海之滨，一片生机蓬勃。大湾区地带，7000万人在这片5.6万平方公里的土地上辛勤耕耘。\n2019年全国两会前夕，中共中央、国务院印发《粤港澳大湾区发展规划纲要》，两年过去，蓝图正一步步变为现实。2020年大湾区经济总量达11.5万亿元，蕴藏的潜力举世瞩目，人们也愈加关注这片土地将拥有什么样的未来。江门，\n这个在大湾区承东启西的关键节点城市，\n两年的大湾区进程，\n给江门带来了哪些变化？\n4月25日，\n一场大咖云集的\n2021江门湾区经济发展高峰论坛，\n即将在江门启幕，\n共商江门未来发展走向！\n','2021-04-26 14:33:38',NULL,1,0,'https://gitee.com/rzj2121/portal/raw/master/img/0ce034209fcf49f3a684817b7785477f.jpg');

/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表主键',
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`role_id`, `role_name`)
VALUES
	(3,X'E5ADA6E59198'),
	(4,X'E69599E5B888'),
	(5,X'E7AEA1E79086E59198');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜系id',
  `type_name` varchar(30) NOT NULL DEFAULT '' COMMENT '菜系名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;

INSERT INTO `type` (`type_id`, `type_name`)
VALUES
	(1,'粤菜'),
	(2,'湘菜'),
	(3,'甜品'),
	(4,'川菜');

/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `user_real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `user_sex` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `user_address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地址',
  `user_age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `user_creatime` date DEFAULT NULL COMMENT '创建时间',
  `user_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户注册手机号',
  `user_password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '账户密码',
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `user_school` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学校信息',
  `user_birthday` date DEFAULT NULL COMMENT '出生日期',
  `user_educational` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学历信息',
  `user_photo_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像地址',
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '盐',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `active` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`user_id`, `user_name`, `user_real_name`, `user_sex`, `user_address`, `user_age`, `user_creatime`, `user_phone`, `user_password`, `user_email`, `user_school`, `user_birthday`, `user_educational`, `user_photo_url`, `salt`, `role_id`, `active`)
VALUES
	(1,X'70686F6E655F31383032323639353130313233343536',X'E69D8EE694BFE5ADA6',X'E794B7',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',22,'2021-03-06',X'3138303232363935313031',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363339374071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'3131',X'3161326233633464',5,1),
	(2,X'656D61696C5F313736303733363339384071712E636F6D',X'E5AEB9E6B3BDE5869B',X'E794B7',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',22,'2021-03-13',X'3138303232363935313034',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363339384071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',NULL,X'3161326233633464',3,1),
	(3,X'656D61696C5F313736303733363339394071712E636F6D',X'E5B08FE69D8E',X'E794B7',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',111,'2021-03-13',X'3135363232303838303839',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363339394071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'',X'3161326233633464',3,1),
	(4,X'7A68616E6773616E',X'E6A0A1E995BF',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',22,'2021-03-13',X'3133363330343835363538',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430314071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'737472696E67',X'3161326233633464',3,1),
	(5,X'E6B7BBE58AA0E6B58BE8AF95',X'E69D8EE5958AE5ADA6',X'E794B7',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',23,'2021-03-13',X'3133363330343835363538',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430324071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'',X'3161326233633464',3,1),
	(6,X'E6B7BBE58AA0E6B58BE8AF9532',X'E69D8EE5958AE5ADA6',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',23,'2021-03-13',X'3133363330343835363539',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430334071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'',X'3161326233633464',3,1),
	(7,X'E6B7BBE58AA0E6B58BE8AF9533',X'E6B7BBE58AA0E6B58BE8AF9533',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',23,'2021-03-13',X'3133363330343835363630',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430344071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6','2021-03-13',X'E69CACE7A791',X'',X'3161326233633464',3,1),
	(8,X'E6B7BBE58AA0E6B58BE8AF9533',X'E6B7BBE58AA0E6B58BE8AF9533',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',23,'2021-03-13',X'3133363330343835363630',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430354071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6',NULL,X'E69CACE7A791',X'',X'3161326233633464',NULL,1),
	(9,X'E6B7BBE58AA0E6B58BE8AF9534',X'E6B7BBE58AA0E6B58BE8AF9534',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',23,'2021-04-04',X'3133363330343835363631',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430364071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6',NULL,X'E69CACE7A791',X'',X'3161326233633464',NULL,1),
	(10,X'E6B7BBE58AA0E6B58BE8AF9536',X'E6B7BBE58AA0E6B58BE8AF9536',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',22,'2021-04-04',X'3135363232303838393031',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430374071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6',NULL,X'E69CACE7A791',X'',X'3161326233633464',NULL,1),
	(11,X'E6B7BBE58AA0E6B58BE8AF9537',X'E6B7BBE58AA0E6B58BE8AF9537',X'E794B7',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',22,'2021-04-04',X'3135363232303838393032',X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303733363430384071712E636F6D',X'E4BA94E98291E5A4A7E5ADA6',NULL,X'E69CACE7A791',X'',X'3161326233633464',NULL,0),
	(12,X'E6B7BBE58AA0E6B58BE8AF9537',X'313131',X'E5A5B3',X'E5B9BFE4B89CE79C81E6B19FE997A8E5B882',1,'2021-04-04',X'3131',X'6237373937636365303162346231333162343333623661636634616464343439',X'3131',X'E4BA94E98291E5A4A7E5ADA6',NULL,X'31',X'',X'3161326233633464',NULL,0),
	(13,NULL,NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393532324071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,NULL),
	(14,NULL,NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6535336433306230366434643066623265306430626436343132653636343430',X'333038303634393532334071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,NULL),
	(15,X'6875616E7A69',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393532344071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(16,X'6C69787565',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393532354071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(17,X'6C69787565313233',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393532364071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(18,X'6875616E7A69',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'3332646138613630373164663233663334636438323131383933323037343638',X'333038303634393532374071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(19,X'6875616E7A69',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393532384071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(20,X'6875616E7A69',NULL,NULL,NULL,NULL,'2021-04-10',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'333038303634393533304071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(21,X'6C69787565313030393232',NULL,NULL,NULL,NULL,'2021-04-18',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'31373630373437344071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(22,X'6C69787565313233',NULL,NULL,NULL,NULL,'2021-04-18',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'313736303333363339374071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(23,X'31323334',NULL,NULL,NULL,NULL,'2021-04-18',NULL,X'6237373937636365303162346231333162343333623661636634616464343439',X'31373630373930393030394071712E636F6D',NULL,NULL,NULL,NULL,X'3161326233633464',NULL,1),
	(39,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
