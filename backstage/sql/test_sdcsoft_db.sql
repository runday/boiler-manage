/*
Navicat MySQL Data Transfer

Source Server         : 本地-测试数据库
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : test_sdcsoft_db

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-21 09:38:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Auxiliary_Machine_Large_Class
-- ----------------------------
DROP TABLE IF EXISTS `Auxiliary_Machine_Large_Class`;
CREATE TABLE `Auxiliary_Machine_Large_Class` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `Name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '大类名称',
  `Sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Auxiliary_Machine_Large_Class
-- ----------------------------
INSERT INTO `Auxiliary_Machine_Large_Class` VALUES ('40', '燃烧器', '1');
INSERT INTO `Auxiliary_Machine_Large_Class` VALUES ('41', '控制器', '2');
INSERT INTO `Auxiliary_Machine_Large_Class` VALUES ('42', '水泵', '3');
INSERT INTO `Auxiliary_Machine_Large_Class` VALUES ('43', '风机', '4');
INSERT INTO `Auxiliary_Machine_Large_Class` VALUES ('44', '水处理', '5');

-- ----------------------------
-- Table structure for Auxiliary_Machine_Small_Class
-- ----------------------------
DROP TABLE IF EXISTS `Auxiliary_Machine_Small_Class`;
CREATE TABLE `Auxiliary_Machine_Small_Class` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `LargeClassId` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Sort` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Auxiliary_Machine_Small_Class
-- ----------------------------
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('2', '40', '无', '0');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('3', '41', 'CTL_E3', '1');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('4', '41', 'CTL_T2', '2');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('5', '41', 'CTL_IPK2', '3');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('6', '41', 'CTL_IPT2', '4');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('7', '41', 'PLC_SOFT', '5');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('8', '42', '补/给水泵', '1');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('9', '42', '注油泵', '2');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('10', '42', '循环泵', '3');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('11', '42', '冷凝泵', '4');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('12', '42', '除氧泵', '5');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('13', '42', '真空泵', '6');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('14', '42', '其它', '100');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('15', '43', '鼓风机', '1');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('16', '43', '引风机', '2');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('17', '43', '沼气风机', '3');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('18', '43', '炉排机', '4');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('19', '43', '出渣机', '5');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('20', '44', '无', '0');
INSERT INTO `Auxiliary_Machine_Small_Class` VALUES ('21', '43', '其它', '100');

-- ----------------------------
-- Table structure for Boiler_Customer
-- ----------------------------
DROP TABLE IF EXISTS `Boiler_Customer`;
CREATE TABLE `Boiler_Customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CustomerNo` varchar(20) DEFAULT NULL COMMENT '客户编号',
  `Name` varchar(30) DEFAULT NULL COMMENT '客户名称',
  `Phone` varchar(11) NOT NULL COMMENT '电话',
  `WeiXin` varchar(20) DEFAULT NULL COMMENT '微信',
  `Province` varchar(30) DEFAULT NULL COMMENT '省',
  `City` varchar(30) DEFAULT NULL COMMENT '市',
  `District` varchar(30) DEFAULT NULL COMMENT '区',
  `OrgType` int(11) DEFAULT NULL COMMENT '组织类型',
  `OrgId` varchar(50) DEFAULT NULL COMMENT '组织Id',
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE KEY `customer_no_unique` (`CustomerNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Boiler_Customer
-- ----------------------------

-- ----------------------------
-- Table structure for Boiler_Model
-- ----------------------------
DROP TABLE IF EXISTS `Boiler_Model`;
CREATE TABLE `Boiler_Model` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `Label` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '锅炉型号名称',
  `Value` int(11) DEFAULT NULL COMMENT '型号值',
  `OrgType` int(11) DEFAULT NULL COMMENT '组织类型',
  `OrgId` varchar(50) DEFAULT NULL COMMENT '组织Id',
  `Sort` int(255) NOT NULL COMMENT '排序',
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE KEY `label_unique` (`Label`,`OrgType`,`OrgId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=530 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Boiler_Model
-- ----------------------------
INSERT INTO `Boiler_Model` VALUES ('529', 'CTLR-0333444', '1', '3', '11', '1');

-- ----------------------------
-- Table structure for Customer_Resource
-- ----------------------------
DROP TABLE IF EXISTS `Customer_Resource`;
CREATE TABLE `Customer_Resource` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `CustomerId` int(11) NOT NULL COMMENT '客户id',
  `ResourceId` int(11) NOT NULL COMMENT '功能id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Customer_Resource
-- ----------------------------
INSERT INTO `Customer_Resource` VALUES ('50', '11', '37');
INSERT INTO `Customer_Resource` VALUES ('51', '11', '38');
INSERT INTO `Customer_Resource` VALUES ('52', '11', '39');

-- ----------------------------
-- Table structure for Customer_User
-- ----------------------------
DROP TABLE IF EXISTS `Customer_User`;
CREATE TABLE `Customer_User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `CustomerId` int(11) NOT NULL COMMENT '客户id',
  `UserId` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`Id`,`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Customer_User
-- ----------------------------
INSERT INTO `Customer_User` VALUES ('11', '11', '113');
INSERT INTO `Customer_User` VALUES ('12', '11', '161');

-- ----------------------------
-- Table structure for Dictionary
-- ----------------------------
DROP TABLE IF EXISTS `Dictionary`;
CREATE TABLE `Dictionary` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `Name` varchar(50) DEFAULT NULL COMMENT '字典名称',
  `Type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `Sort` int(255) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Dictionary
-- ----------------------------
INSERT INTO `Dictionary` VALUES ('1', '燃料', 'fuel', '0');
INSERT INTO `Dictionary` VALUES ('2', '介质', 'medium', '1');
INSERT INTO `Dictionary` VALUES ('5', '是否售出', 'isSell', '4');

-- ----------------------------
-- Table structure for Dictionary_Value
-- ----------------------------
DROP TABLE IF EXISTS `Dictionary_Value`;
CREATE TABLE `Dictionary_Value` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `Type` varchar(50) DEFAULT NULL COMMENT '类型',
  `Label` varchar(20) DEFAULT NULL COMMENT '字典名称',
  `Value` int(2) DEFAULT NULL COMMENT '字典值 ',
  `Sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Dictionary_Value
-- ----------------------------
INSERT INTO `Dictionary_Value` VALUES ('1', 'fuel', '油气', '0', '1');
INSERT INTO `Dictionary_Value` VALUES ('2', 'fuel', '电', '1', '2');
INSERT INTO `Dictionary_Value` VALUES ('3', 'fuel', '煤', '2', '3');
INSERT INTO `Dictionary_Value` VALUES ('4', 'medium', '热水', '0', '1');
INSERT INTO `Dictionary_Value` VALUES ('5', 'medium', '导热油', '2', '3');
INSERT INTO `Dictionary_Value` VALUES ('6', 'medium', '热风', '3', '4');
INSERT INTO `Dictionary_Value` VALUES ('14', 'isSell', '是', '1', '1');
INSERT INTO `Dictionary_Value` VALUES ('15', 'isSell', '否', '0', '2');
INSERT INTO `Dictionary_Value` VALUES ('19', 'medium', '蒸汽', '1', '2');
INSERT INTO `Dictionary_Value` VALUES ('20', 'medium', '真空', '4', '5');
INSERT INTO `Dictionary_Value` VALUES ('57', 'fuel', '生物质', '3', '4');

-- ----------------------------
-- Table structure for Product
-- ----------------------------
DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `BoilerNo` varchar(20) DEFAULT NULL COMMENT '锅炉编号',
  `BoilerModelNumber` int(2) DEFAULT NULL COMMENT '锅炉型号',
  `ControllerNo` varchar(50) DEFAULT NULL COMMENT '控制器编号',
  `TonnageNum` float(5,2) DEFAULT NULL COMMENT '吨位',
  `Medium` int(2) DEFAULT NULL COMMENT '介质',
  `Fuel` int(2) DEFAULT NULL COMMENT '燃料',
  `IsSell` int(2) DEFAULT '0' COMMENT '是否售出',
  `BoilerCustomerId` int(11) DEFAULT NULL COMMENT '客户编号',
  `BoilerCustomerName` varchar(10) DEFAULT NULL COMMENT '客户姓名',
  `SaleDate` date DEFAULT NULL COMMENT '出售日期',
  `Longitude` varchar(10) DEFAULT NULL COMMENT '经度',
  `Latitude` varchar(10) DEFAULT NULL COMMENT '纬度',
  `Province` varchar(20) DEFAULT NULL COMMENT '省',
  `City` varchar(20) DEFAULT NULL COMMENT '市',
  `District` varchar(20) DEFAULT NULL COMMENT '区',
  `Street` varchar(20) DEFAULT NULL COMMENT '街道',
  `CreateDateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `EditDateTime` datetime DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Product
-- ----------------------------
INSERT INTO `Product` VALUES ('44', '0100000014', '1', '0100000014', '3.50', '1', '0', '0', null, null, null, null, null, null, null, null, null, '2018-11-15 08:02:53', '2018-11-15 08:02:53');

-- ----------------------------
-- Table structure for Product_Auxiliary_Machine_Info
-- ----------------------------
DROP TABLE IF EXISTS `Product_Auxiliary_Machine_Info`;
CREATE TABLE `Product_Auxiliary_Machine_Info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `ProductId` int(11) DEFAULT NULL COMMENT '产品Id',
  `LargeClassId` int(11) DEFAULT NULL COMMENT '大类Id',
  `SmallClassId` int(11) DEFAULT NULL COMMENT '小类Id',
  `BrandName` varchar(50) DEFAULT NULL COMMENT '品牌Id',
  `ModelName` varchar(50) DEFAULT NULL COMMENT '型号Id',
  `AmountOfUser` int(11) DEFAULT NULL COMMENT '使用数量',
  `Supplier` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '供货厂家',
  `Remarks` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Product_Auxiliary_Machine_Info
-- ----------------------------

-- ----------------------------
-- Table structure for Product_User
-- ----------------------------
DROP TABLE IF EXISTS `Product_User`;
CREATE TABLE `Product_User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `ProductId` int(11) DEFAULT NULL COMMENT '产品Id',
  `UserId` int(11) DEFAULT NULL COMMENT '用户Id',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Product_User
-- ----------------------------
INSERT INTO `Product_User` VALUES ('44', '44', '113');

-- ----------------------------
-- Table structure for Resource
-- ----------------------------
DROP TABLE IF EXISTS `Resource`;
CREATE TABLE `Resource` (
  `ResId` int(11) NOT NULL AUTO_INCREMENT,
  `ResName` varchar(50) DEFAULT NULL,
  `PId` int(11) DEFAULT NULL,
  `Url` varchar(50) DEFAULT NULL,
  `PageUrl` varchar(50) DEFAULT NULL COMMENT '跳转的页面',
  `Hidden` tinyint(1) DEFAULT NULL COMMENT '1:是 0:否',
  `Permission` varchar(20) DEFAULT NULL,
  `Sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ResId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Resource
-- ----------------------------
INSERT INTO `Resource` VALUES ('1', '系统管理', '0', '', null, '0', null, '0');
INSERT INTO `Resource` VALUES ('2', '产品管理', '0', '', null, '0', null, '20');
INSERT INTO `Resource` VALUES ('3', '用户管理', '1', 'user', '/user/index', '0', null, '10');
INSERT INTO `Resource` VALUES ('4', '角色管理', '1', 'role', '/role/index', '0', null, '20');
INSERT INTO `Resource` VALUES ('5', '菜单管理', '1', 'resource', '/resource/index', '0', null, '30');
INSERT INTO `Resource` VALUES ('6', '产品信息', '2', 'product', '/product/index', '0', null, '10');
INSERT INTO `Resource` VALUES ('7', '地图分布', '2', 'map', '', '0', null, '20');
INSERT INTO `Resource` VALUES ('12', '客户管理', '17', 'boiler-customer', '/boiler-customer/index', '0', null, '10');
INSERT INTO `Resource` VALUES ('13', '字典管理', '1', 'dictionary', '/dictionary/index', '0', null, '60');
INSERT INTO `Resource` VALUES ('14', '字典值管理', '1', 'dictionary-value', '/dictionary/dictionary-value', '1', null, '70');
INSERT INTO `Resource` VALUES ('17', '客户管理', '0', '', '', '0', null, '10');
INSERT INTO `Resource` VALUES ('18', '辅机管理', '1', 'auxiliary-machine-large-class', '/auxiliary-machine-large-class/index', '0', '', '40');
INSERT INTO `Resource` VALUES ('20', '辅机小类管理', '1', 'auxiliary-machine-small-class', '/auxiliary-machine-small-class/index', '1', '', '50');
INSERT INTO `Resource` VALUES ('21', '锅炉型号管理', '2', 'boiler-model', '/boiler-model/index', '0', '', '30');
INSERT INTO `Resource` VALUES ('35', '企业管理', '1', 'enterprise', '/enterprise/index', '0', null, '15');
INSERT INTO `Resource` VALUES ('36', '客户管理', '1', 'customer', '/customer/index', '0', null, '18');
INSERT INTO `Resource` VALUES ('37', '运行信息', '0', '', '', '0', null, '30');
INSERT INTO `Resource` VALUES ('38', '基本运行信息', '37', 'base-run-info', '/run-info/base-run-info', '0', null, '0');
INSERT INTO `Resource` VALUES ('39', '异常运行信息', '37', 'exception-run-info', '/run-info/exception-run-info', '0', null, '10');

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(25) DEFAULT NULL,
  `RoleDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RoleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Role
-- ----------------------------
INSERT INTO `Role` VALUES ('1', '系统管理员', '可以看系统所有的信息');
INSERT INTO `Role` VALUES ('2', '企业管理员', '执行本企业所有管理功能');
INSERT INTO `Role` VALUES ('3', '客户管理员', '执行客户所有管理功能');
INSERT INTO `Role` VALUES ('4', '企业员工', '执行本企业部分管理功能的人员');
INSERT INTO `Role` VALUES ('5', '客户员工', '执行客户理功能的人员');
INSERT INTO `Role` VALUES ('6', '公司管理员', '执行对系统中所有企业的管理、系统中 所有数据的查阅');
INSERT INTO `Role` VALUES ('7', '公司员工', '权限为公司管理员的子集，由公司管理员管理');
INSERT INTO `Role` VALUES ('8', '最终用户管理员', '查看自己的购买记录');

-- ----------------------------
-- Table structure for Role_Resource
-- ----------------------------
DROP TABLE IF EXISTS `Role_Resource`;
CREATE TABLE `Role_Resource` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) NOT NULL,
  `ResId` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of Role_Resource
-- ----------------------------
INSERT INTO `Role_Resource` VALUES ('93', '5', '2');
INSERT INTO `Role_Resource` VALUES ('94', '5', '6');
INSERT INTO `Role_Resource` VALUES ('95', '5', '7');
INSERT INTO `Role_Resource` VALUES ('104', '6', '1');
INSERT INTO `Role_Resource` VALUES ('105', '6', '17');
INSERT INTO `Role_Resource` VALUES ('106', '6', '2');
INSERT INTO `Role_Resource` VALUES ('107', '6', '3');
INSERT INTO `Role_Resource` VALUES ('108', '6', '35');
INSERT INTO `Role_Resource` VALUES ('109', '6', '36');
INSERT INTO `Role_Resource` VALUES ('110', '6', '4');
INSERT INTO `Role_Resource` VALUES ('111', '6', '5');
INSERT INTO `Role_Resource` VALUES ('112', '6', '18');
INSERT INTO `Role_Resource` VALUES ('113', '6', '20');
INSERT INTO `Role_Resource` VALUES ('114', '6', '13');
INSERT INTO `Role_Resource` VALUES ('115', '6', '14');
INSERT INTO `Role_Resource` VALUES ('116', '6', '12');
INSERT INTO `Role_Resource` VALUES ('117', '6', '6');
INSERT INTO `Role_Resource` VALUES ('118', '6', '7');
INSERT INTO `Role_Resource` VALUES ('130', '3', '1');
INSERT INTO `Role_Resource` VALUES ('131', '3', '17');
INSERT INTO `Role_Resource` VALUES ('132', '3', '2');
INSERT INTO `Role_Resource` VALUES ('133', '3', '3');
INSERT INTO `Role_Resource` VALUES ('134', '3', '12');
INSERT INTO `Role_Resource` VALUES ('135', '3', '6');
INSERT INTO `Role_Resource` VALUES ('136', '3', '7');
INSERT INTO `Role_Resource` VALUES ('137', '3', '21');

-- ----------------------------
-- Table structure for User_Role
-- ----------------------------
DROP TABLE IF EXISTS `User_Role`;
CREATE TABLE `User_Role` (
  `UrId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `RoleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`UrId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of User_Role
-- ----------------------------
INSERT INTO `User_Role` VALUES ('1', '112', '6');
INSERT INTO `User_Role` VALUES ('39', '135', '3');
INSERT INTO `User_Role` VALUES ('40', '135', '5');
INSERT INTO `User_Role` VALUES ('41', '137', '5');
INSERT INTO `User_Role` VALUES ('42', '138', '3');
INSERT INTO `User_Role` VALUES ('43', '138', '5');
INSERT INTO `User_Role` VALUES ('47', '113', '3');
INSERT INTO `User_Role` VALUES ('48', '113', '5');
INSERT INTO `User_Role` VALUES ('49', '31', '3');
INSERT INTO `User_Role` VALUES ('50', '161', '5');
