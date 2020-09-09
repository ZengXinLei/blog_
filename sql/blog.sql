/*
 Navicat Premium Data Transfer

 Source Server         : blog
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/09/2020 23:58:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `aTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aText` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `aHtml` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `aUserId` int(8) NULL DEFAULT NULL,
  `aTime` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `aState` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'true',
  `aClickCount` int(8) NULL DEFAULT 0,
  `aReviewCount` int(8) NULL DEFAULT 0,
  `aReadCount` int(8) NULL DEFAULT 0,
  `aType` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`aId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `cId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cText` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cTouristId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cArticleId` int(11) NULL DEFAULT 0,
  `cReviewId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `cTime` bigint(20) NULL DEFAULT NULL,
  `cState` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'true',
  PRIMARY KEY (`cId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for draft
-- ----------------------------
DROP TABLE IF EXISTS `draft`;
CREATE TABLE `draft`  (
  `did` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dtitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dtext` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `dhtml` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `dtime` bigint(20) NULL DEFAULT NULL,
  `dstate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'true',
  `duserId` int(10) NULL DEFAULT NULL,
  `dtype` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '无',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for give
-- ----------------------------
DROP TABLE IF EXISTS `give`;
CREATE TABLE `give`  (
  `gArticleId` int(11) NULL DEFAULT NULL,
  `gTouristId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gTime` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `lTime` bigint(20) NULL DEFAULT NULL,
  `lLevel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lClass` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lAction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lMethod` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for looked
-- ----------------------------
DROP TABLE IF EXISTS `looked`;
CREATE TABLE `looked`  (
  `lArticleId` int(11) NULL DEFAULT NULL,
  `lTouristId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lTime` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tourist
-- ----------------------------
DROP TABLE IF EXISTS `tourist`;
CREATE TABLE `tourist`  (
  `tId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tIp` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tTime` bigint(20) NULL DEFAULT NULL,
  `tName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tPhoto` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://s2.ax1x.com/2019/12/06/QY0hQ0.png',
  PRIMARY KEY (`tId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uPassword` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uTime` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uHeadPortrait` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'https://s1.ax1x.com/2020/05/19/YIKHwn.jpg',
  `uTel` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uLoginTime` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uAttention` int(11) NOT NULL DEFAULT 0,
  `uFans` int(11) NOT NULL DEFAULT 0,
  `uSex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uQQ` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uEmail` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uSchool` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uCompany` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uBirth` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uFaith` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `uLevel` int(2) NULL DEFAULT 0,
  PRIMARY KEY (`uId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for addComment
-- ----------------------------
DROP PROCEDURE IF EXISTS `addComment`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addComment`(
			in cId varchar(60),
			in cText varchar(1000),
			in cTouristId varchar(60),
			in cArticleId int,
			in cReviewId varchar(60),
			in cTime BIGINT	
 )
add1:BEGIN
		declare c int;
		SELECT count(*) into c from tourist as t where t.tId=cTouristId;
		if c=0 then 
			leave add1;
		end if;
		if cReviewId!='' then 
			SELECT count(*) into c from comment as t where t.cId=cReviewId;
			if c=0 then 
				leave add1;
			end if;
		end if;
		select count(*) into c from article as  a where a.aId=cArticleId;
		if c=0 then
			if cArticleId!=-1 then
				leave add1;
			end if;
		end if;
		insert into comment (cId,cText,cTouristId,cArticleId,cReviewId,cTime) values (cId,cText,cTouristId,cArticleId,cReviewId,cTime);
   END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
