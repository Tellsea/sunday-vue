/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : sunday_base

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 18/04/2020 13:42:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录设备',
  `code` int(11) NULL DEFAULT NULL COMMENT '状态码',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 1 正常 2 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for map_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `map_role_resource`;
CREATE TABLE `map_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resource_id` int(11) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 274 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of map_role_resource
-- ----------------------------
INSERT INTO `map_role_resource` VALUES (105, 2, 1);
INSERT INTO `map_role_resource` VALUES (106, 2, 4);
INSERT INTO `map_role_resource` VALUES (107, 2, 12);
INSERT INTO `map_role_resource` VALUES (108, 2, 13);
INSERT INTO `map_role_resource` VALUES (109, 2, 14);
INSERT INTO `map_role_resource` VALUES (110, 2, 15);
INSERT INTO `map_role_resource` VALUES (111, 2, 16);
INSERT INTO `map_role_resource` VALUES (112, 2, 3);
INSERT INTO `map_role_resource` VALUES (113, 2, 11);
INSERT INTO `map_role_resource` VALUES (114, 3, 2);
INSERT INTO `map_role_resource` VALUES (115, 3, 7);
INSERT INTO `map_role_resource` VALUES (116, 3, 25);
INSERT INTO `map_role_resource` VALUES (117, 3, 26);
INSERT INTO `map_role_resource` VALUES (118, 3, 8);
INSERT INTO `map_role_resource` VALUES (119, 3, 27);
INSERT INTO `map_role_resource` VALUES (120, 3, 28);
INSERT INTO `map_role_resource` VALUES (121, 3, 9);
INSERT INTO `map_role_resource` VALUES (122, 3, 3);
INSERT INTO `map_role_resource` VALUES (123, 3, 11);
INSERT INTO `map_role_resource` VALUES (124, 4, 4);
INSERT INTO `map_role_resource` VALUES (125, 4, 12);
INSERT INTO `map_role_resource` VALUES (126, 4, 13);
INSERT INTO `map_role_resource` VALUES (127, 4, 14);
INSERT INTO `map_role_resource` VALUES (128, 4, 15);
INSERT INTO `map_role_resource` VALUES (129, 4, 16);
INSERT INTO `map_role_resource` VALUES (130, 5, 1);
INSERT INTO `map_role_resource` VALUES (131, 5, 4);
INSERT INTO `map_role_resource` VALUES (132, 5, 12);
INSERT INTO `map_role_resource` VALUES (153, 8, 1);
INSERT INTO `map_role_resource` VALUES (154, 8, 4);
INSERT INTO `map_role_resource` VALUES (155, 8, 12);
INSERT INTO `map_role_resource` VALUES (156, 8, 13);
INSERT INTO `map_role_resource` VALUES (157, 8, 14);
INSERT INTO `map_role_resource` VALUES (158, 8, 16);
INSERT INTO `map_role_resource` VALUES (159, 9, 4);
INSERT INTO `map_role_resource` VALUES (160, 9, 12);
INSERT INTO `map_role_resource` VALUES (161, 9, 13);
INSERT INTO `map_role_resource` VALUES (162, 9, 14);
INSERT INTO `map_role_resource` VALUES (163, 9, 15);
INSERT INTO `map_role_resource` VALUES (164, 9, 16);
INSERT INTO `map_role_resource` VALUES (165, 9, 30);
INSERT INTO `map_role_resource` VALUES (166, 9, 31);
INSERT INTO `map_role_resource` VALUES (167, 10, 4);
INSERT INTO `map_role_resource` VALUES (168, 10, 12);
INSERT INTO `map_role_resource` VALUES (169, 10, 13);
INSERT INTO `map_role_resource` VALUES (170, 10, 14);
INSERT INTO `map_role_resource` VALUES (171, 10, 15);
INSERT INTO `map_role_resource` VALUES (172, 10, 16);
INSERT INTO `map_role_resource` VALUES (173, 10, 30);
INSERT INTO `map_role_resource` VALUES (174, 10, 31);
INSERT INTO `map_role_resource` VALUES (175, 11, 5);
INSERT INTO `map_role_resource` VALUES (176, 11, 17);
INSERT INTO `map_role_resource` VALUES (177, 11, 18);
INSERT INTO `map_role_resource` VALUES (178, 11, 19);
INSERT INTO `map_role_resource` VALUES (179, 11, 20);
INSERT INTO `map_role_resource` VALUES (180, 12, 6);
INSERT INTO `map_role_resource` VALUES (181, 12, 21);
INSERT INTO `map_role_resource` VALUES (182, 12, 22);
INSERT INTO `map_role_resource` VALUES (183, 12, 23);
INSERT INTO `map_role_resource` VALUES (184, 12, 24);
INSERT INTO `map_role_resource` VALUES (185, 13, 7);
INSERT INTO `map_role_resource` VALUES (186, 13, 25);
INSERT INTO `map_role_resource` VALUES (187, 13, 26);
INSERT INTO `map_role_resource` VALUES (188, 13, 32);
INSERT INTO `map_role_resource` VALUES (189, 14, 7);
INSERT INTO `map_role_resource` VALUES (190, 14, 25);
INSERT INTO `map_role_resource` VALUES (191, 14, 26);
INSERT INTO `map_role_resource` VALUES (192, 14, 32);
INSERT INTO `map_role_resource` VALUES (193, 14, 8);
INSERT INTO `map_role_resource` VALUES (194, 14, 27);
INSERT INTO `map_role_resource` VALUES (195, 14, 28);
INSERT INTO `map_role_resource` VALUES (196, 15, 2);
INSERT INTO `map_role_resource` VALUES (197, 15, 9);
INSERT INTO `map_role_resource` VALUES (198, 16, 4);
INSERT INTO `map_role_resource` VALUES (199, 16, 12);
INSERT INTO `map_role_resource` VALUES (200, 16, 13);
INSERT INTO `map_role_resource` VALUES (201, 16, 14);
INSERT INTO `map_role_resource` VALUES (202, 16, 15);
INSERT INTO `map_role_resource` VALUES (203, 16, 16);
INSERT INTO `map_role_resource` VALUES (204, 16, 30);
INSERT INTO `map_role_resource` VALUES (205, 16, 31);
INSERT INTO `map_role_resource` VALUES (206, 17, 3);
INSERT INTO `map_role_resource` VALUES (207, 17, 10);
INSERT INTO `map_role_resource` VALUES (208, 17, 11);
INSERT INTO `map_role_resource` VALUES (215, 7, 1);
INSERT INTO `map_role_resource` VALUES (216, 7, 4);
INSERT INTO `map_role_resource` VALUES (217, 7, 12);
INSERT INTO `map_role_resource` VALUES (218, 7, 13);
INSERT INTO `map_role_resource` VALUES (219, 7, 14);
INSERT INTO `map_role_resource` VALUES (220, 7, 15);
INSERT INTO `map_role_resource` VALUES (221, 7, 16);
INSERT INTO `map_role_resource` VALUES (243, 18, 3);
INSERT INTO `map_role_resource` VALUES (244, 18, 10);
INSERT INTO `map_role_resource` VALUES (245, 18, 11);
INSERT INTO `map_role_resource` VALUES (246, 1, 1);
INSERT INTO `map_role_resource` VALUES (247, 1, 4);
INSERT INTO `map_role_resource` VALUES (248, 1, 12);
INSERT INTO `map_role_resource` VALUES (249, 1, 13);
INSERT INTO `map_role_resource` VALUES (250, 1, 14);
INSERT INTO `map_role_resource` VALUES (251, 1, 15);
INSERT INTO `map_role_resource` VALUES (252, 1, 16);
INSERT INTO `map_role_resource` VALUES (253, 1, 5);
INSERT INTO `map_role_resource` VALUES (254, 1, 17);
INSERT INTO `map_role_resource` VALUES (255, 1, 18);
INSERT INTO `map_role_resource` VALUES (256, 1, 19);
INSERT INTO `map_role_resource` VALUES (257, 1, 20);
INSERT INTO `map_role_resource` VALUES (258, 1, 6);
INSERT INTO `map_role_resource` VALUES (259, 1, 21);
INSERT INTO `map_role_resource` VALUES (260, 1, 22);
INSERT INTO `map_role_resource` VALUES (261, 1, 23);
INSERT INTO `map_role_resource` VALUES (262, 1, 24);
INSERT INTO `map_role_resource` VALUES (263, 1, 7);
INSERT INTO `map_role_resource` VALUES (264, 1, 25);
INSERT INTO `map_role_resource` VALUES (265, 1, 26);
INSERT INTO `map_role_resource` VALUES (266, 1, 8);
INSERT INTO `map_role_resource` VALUES (267, 1, 27);
INSERT INTO `map_role_resource` VALUES (268, 1, 28);
INSERT INTO `map_role_resource` VALUES (269, 1, 2);
INSERT INTO `map_role_resource` VALUES (270, 1, 9);
INSERT INTO `map_role_resource` VALUES (271, 1, 3);
INSERT INTO `map_role_resource` VALUES (272, 1, 10);
INSERT INTO `map_role_resource` VALUES (273, 1, 11);

-- ----------------------------
-- Table structure for map_user_role
-- ----------------------------
DROP TABLE IF EXISTS `map_user_role`;
CREATE TABLE `map_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of map_user_role
-- ----------------------------
INSERT INTO `map_user_role` VALUES (1, 1, 1);
INSERT INTO `map_user_role` VALUES (2, 1, 2);
INSERT INTO `map_user_role` VALUES (3, 1, 3);
INSERT INTO `map_user_role` VALUES (6, 4, 8);
INSERT INTO `map_user_role` VALUES (7, 4, 7);
INSERT INTO `map_user_role` VALUES (8, 4, 5);

-- ----------------------------
-- Table structure for resource_info
-- ----------------------------
DROP TABLE IF EXISTS `resource_info`;
CREATE TABLE `resource_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `pid` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `perms` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '0 未使用 1 菜单 2 按钮 3 链接',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 1 正常 2删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_info
-- ----------------------------
INSERT INTO `resource_info` VALUES (1, 0, '系统管理', '/system', '/system', 'system', 1, 'shopping', 1, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (2, 0, '系统监控', '/monitor', '/montir', 'log', 1, 'skill', 2, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (3, 0, '项目文档', '/document', '/document', 'document', 1, 'tree-table', 3, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (4, 1, '用户管理', '/system/user-info/index', '/system/user-info/index', 'system:userInfo:list', 1, 'language', 4, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (5, 1, '角色管理', '/system/role-info/index', '/system/role-info/index', 'system:roleInfo:list', 1, 'message', 5, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (6, 1, '菜单管理', '/system/resource-info/index', '/system/resource-info/index', 'system:resourceInfo:list', 1, 'list', 6, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (7, 1, '系统日志', '/system/system-log/index', '/system/system-log/index', 'system:systemLog:list', 1, 'message', 7, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (8, 1, '登录日志', '/system/login-log/index', '/system/login-log/index', 'system:loginLog:list', 1, 'pdf', 8, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (9, 2, '数据监控', 'druid', 'druid', 'druid', 1, NULL, 9, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (10, 3, 'Swagger API', 'document/swagger', 'document/swagger', 'swagger', 1, NULL, 10, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (11, 3, 'GitHub 源码', 'document/github', 'document/github', 'github', 1, NULL, 11, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (12, 4, '新增用户', '/system/user-info/add', '/system/user-info/UserInfoAdd', 'system:userInfo:add', 2, NULL, 12, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (13, 4, '更新用户', '/system/user-info/update', '/system/user-info/UserInfoUpdate', 'system:userInfo:update', 2, NULL, 13, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (14, 4, '删除用户', '', '', 'system:userInfo:delete', 2, NULL, 14, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (15, 4, '查询用户', '', '', 'system:userInfo:select', 2, NULL, 15, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (16, 4, '查看用户', NULL, NULL, 'userInfo:detail', 2, NULL, 16, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (17, 5, '新增角色', NULL, NULL, 'roleInfo:add', 2, NULL, 17, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (18, 5, '更新角色', NULL, NULL, 'roleInfo:update', 2, NULL, 18, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (19, 5, '删除角色', NULL, NULL, 'roleInfo:delete', 2, NULL, 19, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (20, 5, '查询角色', NULL, NULL, 'roleInfo:select', 2, NULL, 20, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (21, 6, '新增权限', NULL, NULL, 'resourceInfo:add', 2, NULL, 21, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (22, 6, '更新权限', NULL, NULL, 'resourceInfo:update', 2, NULL, 22, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (23, 6, '删除权限', NULL, NULL, 'resourceInfo:delete', 2, NULL, 23, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (24, 6, '查询权限', NULL, NULL, 'resourceInfo:select', 2, NULL, 24, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (25, 7, '删除日志', NULL, NULL, 'systemLog:delete', 2, NULL, 1, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (26, 7, '查询日志', NULL, NULL, 'systemLog:select', 2, NULL, 26, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (27, 8, '删除日志', NULL, NULL, 'loginLog:delete', 2, NULL, 27, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (28, 8, '查询日志', NULL, NULL, 'loginLog:select', 2, NULL, 28, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (30, 4, '导入用户', '', '', 'system:userInfo:import', 2, '', 30, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (31, 4, '导出用户', '', '', 'system:userInfo:export', 2, '', 31, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `resource_info` VALUES (32, 7, '导出日志', '', '', 'system:systemLog:export', 2, '', 27, 0, '2020-04-04 14:28:53', 1);

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 1 正常 2删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES (1, 'admin', '超级管理员', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (2, 'system', '系统管理员', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (3, 'monitor', '系统监控员', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (4, 'test', '测试', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (5, 'tigger', '测试001', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (7, 'boom', '测试002', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (8, 'dom', '测试003', 0, 0, '2020-04-04 14:28:53', 1);
INSERT INTO `role_info` VALUES (16, 'aaa', '啊啊啊', 0, 0, '2020-04-04 16:20:56', 1);
INSERT INTO `role_info` VALUES (17, 'fff', '发发发', 0, 0, '2020-04-04 16:55:27', 1);
INSERT INTO `role_info` VALUES (18, 'ggg', '滚滚滚', 0, 0, '2020-04-04 17:00:07', 1);

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '操作用户id',
  `operation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `time` int(11) NULL DEFAULT 0 COMMENT '耗时(毫秒)',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作设备',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态(1正常，2删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES (1, 0, '更新角色', 24, 'cn.tellsea.sunday.system.controller.RoleInfoController.update()', '{\"roleInfo\":{\"description\":\"滚滚滚\",\"id\":18,\"limit\":0,\"name\":\"ggg\",\"page\":0,\"resourceIds\":\"3,10,11\",\"sort\":0}}', '127.0.0.1', '0|0|0|内网IP|内网IP', 'CHROME -- WINDOWS_10', '2020-04-04 19:09:17', 1);
INSERT INTO `system_log` VALUES (2, 0, '更新角色', 75, 'cn.tellsea.sunday.system.controller.RoleInfoController.update()', '{\"roleInfo\":{\"description\":\"滚滚滚\",\"id\":18,\"limit\":0,\"name\":\"ggg\",\"page\":0,\"resourceIds\":\"3,10,11\",\"sort\":0}}', '127.0.0.1', '0|0|0|内网IP|内网IP', 'CHROME -- WINDOWS_10', '2020-04-05 09:16:56', 1);
INSERT INTO `system_log` VALUES (3, 0, '更新角色', 138, 'cn.tellsea.sunday.system.controller.RoleInfoController.update()', '{\"roleInfo\":{\"description\":\"超级管理员\",\"id\":1,\"limit\":0,\"name\":\"admin\",\"page\":0,\"resourceIds\":\"1,4,12,13,14,15,16,5,17,18,19,20,6,21,22,23,24,7,25,26,8,27,28,2,9,3,10,11\",\"sort\":0}}', '127.0.0.1', '0|0|0|内网IP|内网IP', 'CHROME -- WINDOWS_10', '2020-04-18 13:32:08', 1);
INSERT INTO `system_log` VALUES (4, 0, '更新菜单', 12, 'cn.tellsea.sunday.system.controller.ResourceInfoController.update()', '{\"resourceInfo\":{\"component\":\"/system\",\"icon\":\"shopping\",\"id\":1,\"limit\":0,\"name\":\"系统管理\",\"page\":0,\"perms\":\"system\",\"pid\":0,\"sort\":1,\"type\":1,\"url\":\"/system\"}}', '127.0.0.1', '0|0|0|内网IP|内网IP', 'CHROME -- WINDOWS_10', '2020-04-18 13:33:32', 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户，登录名，不可更改',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `sex` int(1) NULL DEFAULT 0 COMMENT '性别 1男 2女',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_times` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 1 正常 2 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'Tellsea', '超级管理员', 'a994fef0ddad5f9eed20804e7b18f559', 'VKqvum8YW0xNaTDFbrcijhTG6wUP5xAZ', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2562372298,495845374&fm=26&gp=0.jpg', '18386474459', 0, 'I am Iron Man', 1, '2019-06-21 16:16:30', 12, '2019-12-20 10:15:58', 1);
INSERT INTO `user_info` VALUES (2, 'system', '系统管理员', '44eb196feb810ee770047cc34da66133', 'V1D9h266hLYqYfF5DnZCjlJKyG3Gx36c', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3041599764,2432042915&fm=26&gp=0.jpg', '15836541252', 1, '我不想打字', 1, '2019-12-24 17:07:19', 0, NULL, 1);
INSERT INTO `user_info` VALUES (3, 'monitor', '系统监控员', '221605ef8256bb0cfbbfa5fb7688eaf1', '2HI9EdHJjSYsfyqSdbWlXZRxJNXvFnrE', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=584948523,1006436504&fm=26&gp=0.jpg', '15356521441', 2, '中二病晚期', 1, '2019-12-24 17:17:17', 0, NULL, 2);
INSERT INTO `user_info` VALUES (4, 'testAdd', 'testAdd', '123456', NULL, '', '18675455444', 2, '测试新增', 0, NULL, 0, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
