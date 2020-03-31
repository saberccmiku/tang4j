/*
 Navicat Premium Data Transfer

 Source Server         : tang4j
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.0.243:3306
 Source Schema         : tang4j

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/11/2019 18:23:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for th_sys_action
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_action`;
CREATE TABLE `th_sys_action`  (
  `id` bigint(18) NOT NULL DEFAULT 0,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '动作点(对应代码的方法名)',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提交方式（POST，GET，PUT，DELTET）',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础动作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_action
-- ----------------------------
INSERT INTO `th_sys_action` VALUES (1, '预览', 'view', NULL, '预览', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_action` VALUES (2, '新增', 'plus', NULL, '新增', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_action` VALUES (3, '修改', 'edit', NULL, '修改', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_action` VALUES (4, '删除', 'delete', NULL, '删除', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_controller
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_controller`;
CREATE TABLE `th_sys_controller`  (
  `id` bigint(18) NOT NULL DEFAULT 0 COMMENT '主键ID',
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `pcode` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父编码',
  `pcodes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父编码',
  `os_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `request_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `levels` int(11) NULL DEFAULT NULL COMMENT '层级',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '控制器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_controller
-- ----------------------------
INSERT INTO `th_sys_controller` VALUES (74, 'system', '0', '[0],', 'rbac', '系统设置', '#', 1, 0, '系统设置', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (75, 'sysUser', 'hrManager', '[0],[hrManager],', 'rbac', '用户管理', 'main/user', 2, 0, '用户管理', 3, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (76, 'sysDepartment', 'hrManager', '[0],[hrManager],', 'rbac', '组织结构', 'main/department', 2, 0, '组织结构配置', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (77, 'sysRole', 'system', '[0],[system],', 'rbac', '角色管理', 'main/role', 2, 0, '角色管理', 6, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (78, 'sysMenu', 'system', '[0],[system],', 'rbac', '菜单管理', 'main/menus', 2, 0, '菜单管理', 2, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (99, 'sysAction', 'system', '[0],[system],', 'rbac', '动作点管理', 'main/action', 2, 0, '动作点管理', 3, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (301, 'extraAction', 'system', '[0],[system],', 'rbac', '扩展动作点', 'main/extraAction', 2, 0, '', 5, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (342, 'sysOs', 'system', '[0],[system],', 'rbac', '业务系统管理', 'main/sysOs', 2, 0, '', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (547, 'attendance', '0', '[0],', 'rbac', '考勤管理', '', 1, 0, '', 20, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (548, 'attendanceRecord', 'attendance', '[0],[attendance],', 'rbac', '考勤记录管理', '', 2, 0, '', 2, NULL, 294, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (549, 'statistical', 'attendance', '[0],[attendance],', 'rbac', '考勤统计', '', 2, 0, '', 3, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (550, 'attendanceParam', 'attendance', '[0],[attendance],', 'rbac', '考勤参数设定', '', 2, 0, '', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (551, 'hrManager', '0', '[0],', 'rbac', '人事管理', '', 1, 0, '', 10, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (561, 'layoutMgr', '0', '[0],', 'chartos', '页面布局管理', 'main/chart/chartLayout', 1, 0, '图表页面布局设计', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (562, 'chartDom', '0', '[0],', 'chartos', '图表元素管理', 'main/chart/chartDom', 1, 0, '图表元素管理', 2, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (563, 'searchDom', '0', '[0],', 'chartos', '查询元素管理', 'main/chart/thChartQueryDom', 1, 0, '查询元素管理', 3, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (564, 'chartPage', '0', '[0],', 'chartos', '报表版面设计', 'main/chart/ThChartPage', 1, 0, '图表版面设置', 4, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (568, 'chartPageType', '0', '[0],', 'chartos', '版面类型管理', 'main/chart/ThChartPageType', 1, 0, '版面类型管理', 5, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (668, 'thChartCss', '0', '[0],', 'chartos', '样式文件管理', '', 1, 0, '', 6, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (669, 'county', 'system', '[0],[system],', 'rbac', '区县管理', '', 2, 0, '区县管理', 4, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (674, 'taskMan', '0', '[0],', 'rbac', '任务管理', '', 1, 0, '', 4, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (675, 'deptVerifyEveryday', 'taskMan', '[0],[taskMan],', 'rbac', '单位每日任务统计', '', 2, 0, '', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (676, 'deptVerifyEveryweek', 'taskMan', '[0],[taskMan],', 'rbac', '单位每周任务统计', '', 2, 0, '', 2, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (677, 'landReceive', 'taskMan', '[0],[taskMan],', 'rbac', '收件管理', '', 2, 0, '', 3, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (678, 'bigScreenParam', 'system', '[0],[system],', 'rbac', '大屏参数设定', '', 2, 0, '', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (679, 'holiday', 'attendance', '[0],[attendance],', 'rbac', '休息日设置', '', 2, 0, '考勤休息日设置', 2, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (680, 'supervision', 'taskMan', '[0],[taskMan],', 'rbac', '监理管理', '', 2, 0, '', 1, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (681, 'projectMan', '0', '[0],', 'rbac', '项目管理', '', 1, 0, '', 5, NULL, 1, NULL, NULL);
INSERT INTO `th_sys_controller` VALUES (682, 'countyProcess', 'projectMan', '[0],[projectMan],', 'rbac', '区县进度管理', '', 2, 0, '', 1, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_controller_action_info
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_controller_action_info`;
CREATE TABLE `th_sys_controller_action_info`  (
  `controller_id` bigint(18) NOT NULL COMMENT '控制器id',
  `action_id` bigint(18) NOT NULL COMMENT '动作点id',
  `request_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`controller_id`, `action_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '控制器对应的动作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for th_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_dept`;
CREATE TABLE `th_sys_dept`  (
  `id` bigint(18) NOT NULL COMMENT '部门id',
  `pId` bigint(18) NOT NULL COMMENT '父部门id',
  `order_no` tinyint(11) NULL DEFAULT NULL COMMENT '排序',
  `name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '修改人',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_dept
-- ----------------------------
INSERT INTO `th_sys_dept` VALUES (1, 0, 1, '项目部', '2019-11-21 09:15:42', NULL, NULL, NULL, 0);
INSERT INTO `th_sys_dept` VALUES (2, 0, 2, '行政部', '2019-11-21 09:16:15', NULL, NULL, NULL, 0);
INSERT INTO `th_sys_dept` VALUES (3, 1, 1, '研发组', '2019-11-21 09:17:22', NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_dept` VALUES (4, 1, 2, '运维组', '2019-11-21 09:18:07', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_dict`;
CREATE TABLE `th_sys_dict`  (
  `id` bigint(18) NOT NULL DEFAULT 0 COMMENT '主键ID',
  `pid` bigint(18) NULL DEFAULT NULL COMMENT '父级字典',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文的编码',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `pcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级编码',
  `tips` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提示',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for th_sys_navigation
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_navigation`;
CREATE TABLE `th_sys_navigation`  (
  `id` bigint(18) NOT NULL DEFAULT 0 COMMENT '主键ID',
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `pcode` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父编码',
  `pcodes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父编码',
  `os_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon_css` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `request_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `levels` int(11) NULL DEFAULT NULL COMMENT '层级',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_navigation
-- ----------------------------
INSERT INTO `th_sys_navigation` VALUES (1, 'sys', 'bar_base_manage', NULL, NULL, '系统设置', 'icon-menu-sys_config', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (2, 'right', 'bar_base_manage', NULL, NULL, '权限设置', 'icon-menu-key', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (3, 'sys_params', 'sys', NULL, NULL, '系统参数', NULL, 'pages/baseParam/browse.html', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (4, 'sys_manage', 'sys', NULL, NULL, '系统管理', NULL, 'pages/roles/browse.html', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (5, 'sys_func', 'sys', NULL, NULL, '功能管理', NULL, 'pages/roles/browse.html', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (6, 'sys_action', 'sys', NULL, NULL, '动作点管理', NULL, 'pages/roles/browse.html', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (7, 'sys_menu_manage', 'sys', NULL, NULL, '菜单管理', NULL, 'pages/module/browse.html', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (8, 'sys_menu_btn', 'sys', NULL, NULL, '菜单按钮', NULL, 'pages/roles/browse.html', NULL, NULL, NULL, 6, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (9, 'right_role_manage', 'right', NULL, NULL, '角色管理', 'icon-menu-role', 'pages/roles/browse.html', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (10, 'right_user_manage', 'right', '', NULL, '用户管理', 'icon-menu-user', 'pages/user/browse.html', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (11, 'bar_base_manage', 'bar', NULL, NULL, '基础管理平台', 'icon-menu-base_manage', 'http://127.0.0.1/rbac/index.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (12, 'bar_wf', 'bar', NULL, NULL, '工作流', 'icon-menu-wf', 'http://192.168.0.93:8099', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (13, 'bar_report', 'bar', NULL, NULL, '图形报表', 'icon-menu-report', 'http://localhost:8000/#/login', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (14, 'bar_service_manage', 'bar', NULL, NULL, '服务治理', 'icon-menu-service', 'http://192.168.0.244:8848/nacos/#/configurationManagement?dataId=&group=&appName=&namespace=', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation` VALUES (15, 'bar_operations', 'bar', NULL, NULL, '运行维护', 'icon-menu-operations', 'http://192.168.0.249:3000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_navigation_controller_info
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_navigation_controller_info`;
CREATE TABLE `th_sys_navigation_controller_info`  (
  `navigation_id` bigint(18) NOT NULL COMMENT '菜单id',
  `controller_id` bigint(18) NOT NULL COMMENT '控制器id',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`navigation_id`, `controller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单和控制器绑定关系表1' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for th_sys_navigation_event
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_navigation_event`;
CREATE TABLE `th_sys_navigation_event`  (
  `id` bigint(18) NOT NULL DEFAULT 0,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮对应的名称',
  `navigation_id` bigint(18) NULL DEFAULT NULL COMMENT '导航编号',
  `icon_css` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮图标',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导航页面对应的页面按钮' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_navigation_event
-- ----------------------------
INSERT INTO `th_sys_navigation_event` VALUES (1, '新增', 'insert', 10, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (2, '修改', 'update', 10, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (3, '删除', 'delete', 10, NULL, NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (4, '重置密码', 'reset_password', 10, NULL, NULL, 4, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (5, '重置签名', 'reset_sign', 10, NULL, NULL, 5, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (6, '查询', 'select', 10, NULL, NULL, 6, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_event` VALUES (7, '重置', 'reset', 10, NULL, NULL, 7, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_navigation_role_info
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_navigation_role_info`;
CREATE TABLE `th_sys_navigation_role_info`  (
  `navigation_id` bigint(18) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `role_id` bigint(18) NOT NULL COMMENT '角色ID',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`navigation_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_navigation_role_info
-- ----------------------------
INSERT INTO `th_sys_navigation_role_info` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (2, 2, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (3, 3, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (4, 4, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (5, 5, 3, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (6, 6, 4, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (7, 7, 5, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (8, 8, 6, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (9, 9, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (10, 10, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (11, 11, 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (12, 12, 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (13, 13, 3, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (14, 14, 4, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_navigation_role_info` VALUES (15, 15, 5, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_os
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_os`;
CREATE TABLE `th_sys_os`  (
  `id` bigint(18) NOT NULL DEFAULT 0,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `pcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首页地址',
  `system` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可运行的平台[web,app,wechat]',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `memo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_no` int(8) UNSIGNED NULL DEFAULT NULL COMMENT '排序编号',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_os
-- ----------------------------
INSERT INTO `th_sys_os` VALUES (1, 'base', NULL, '基础配置', 'http://127.0.0.1:8089/', NULL, NULL, '基础配', 0, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_os` VALUES (2, 'flow', NULL, '工作流', 'http://127.0.0.1:8089/nacos/', NULL, NULL, '工作流', 1, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_os` VALUES (3, 'report', NULL, '图形报表', 'http://127.0.0.1:8089/nacos/', NULL, NULL, '图形报表', 2, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_os` VALUES (4, 'gateway', NULL, '服务治理', 'http://192.168.0.244:8848/nacos/', NULL, NULL, '服务治理', 3, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_os` VALUES (5, 'grafana', NULL, '运行维护', 'http://192.168.0.249:3000/grafana/', NULL, NULL, '运行维护', 4, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for th_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_role`;
CREATE TABLE `th_sys_role`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `os_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门CODE',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_role
-- ----------------------------
INSERT INTO `th_sys_role` VALUES (1, NULL, '系统设置', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS');
INSERT INTO `th_sys_role` VALUES (2, NULL, '权限管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'RIGHT');
INSERT INTO `th_sys_role` VALUES (3, NULL, '系统参数', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_PARAMS');
INSERT INTO `th_sys_role` VALUES (4, NULL, '系统管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_MANAGE');
INSERT INTO `th_sys_role` VALUES (5, NULL, '功能管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_FUNC');
INSERT INTO `th_sys_role` VALUES (6, NULL, '动作点管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_ACTION');
INSERT INTO `th_sys_role` VALUES (7, NULL, '菜单管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_MENU_MANAGE');
INSERT INTO `th_sys_role` VALUES (8, NULL, '菜单按钮', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYS_MENU_BTN');
INSERT INTO `th_sys_role` VALUES (9, NULL, '角色管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'RIGHT_ROLE_MANAGE');
INSERT INTO `th_sys_role` VALUES (10, NULL, '用户管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'RIGHT_USER_NAMAGE');
INSERT INTO `th_sys_role` VALUES (11, NULL, '基础管理平台', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BAR_BASE_MANEGE');
INSERT INTO `th_sys_role` VALUES (12, NULL, '工作流', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BAR_WORK_FLOW');
INSERT INTO `th_sys_role` VALUES (13, NULL, '图形报表', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BAR_REPORT');
INSERT INTO `th_sys_role` VALUES (14, NULL, '服务治理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BAR_SERVICE_MANAGE');
INSERT INTO `th_sys_role` VALUES (15, NULL, '运行维护', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BAR_OPERATIONS');
INSERT INTO `th_sys_role` VALUES (16, NULL, '新增', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'insert');
INSERT INTO `th_sys_role` VALUES (17, NULL, '修改', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'update');
INSERT INTO `th_sys_role` VALUES (18, NULL, '删除', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'delete');
INSERT INTO `th_sys_role` VALUES (19, NULL, '重置密码', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'reset_password');
INSERT INTO `th_sys_role` VALUES (20, NULL, '重置签名', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'reset_sign');
INSERT INTO `th_sys_role` VALUES (21, NULL, '查询', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'select');
INSERT INTO `th_sys_role` VALUES (22, NULL, '重置', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'reset');

-- ----------------------------
-- Table structure for th_sys_role_controller_action_info
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_role_controller_action_info`;
CREATE TABLE `th_sys_role_controller_action_info`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(18) NULL DEFAULT NULL COMMENT '角色ID',
  `controller_id` bigint(18) NULL DEFAULT NULL COMMENT '菜单',
  `action_id` bigint(18) NULL DEFAULT NULL COMMENT '动作点编号',
  `request_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单动作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for th_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_user`;
CREATE TABLE `th_sys_user`  (
  `id` bigint(18) NOT NULL,
  `english_login_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文的登录id',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码（加密存储）',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称（花名）',
  `head_img_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址(完整的http地址)',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `enable` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_user
-- ----------------------------
INSERT INTO `th_sys_user` VALUES (1, 'admin', '$2a$10$p53QZ2cvzingWxQmjwxaC.pNSm3HSW0YSGS55IyLMNHzsciqs4qHy', '超级管理员', NULL, NULL, NULL, '2019-11-20 07:36:21', NULL, '2019-11-20 01:16:52', NULL, 0);
INSERT INTO `th_sys_user` VALUES (1197056065611522050, 'test1', '$2a$10$52lL8bh/7MEPlN153qZPd.9zQorSqfxmS8z450ZVFvpICt7Bd8B4K', '测试员1', NULL, NULL, NULL, '2019-11-20 07:36:21', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for th_sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_user_dept`;
CREATE TABLE `th_sys_user_dept`  (
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `user_id` int(11) NOT NULL COMMENT '用户id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_user_dept
-- ----------------------------
INSERT INTO `th_sys_user_dept` VALUES (3, 1);

-- ----------------------------
-- Table structure for th_sys_user_role_info
-- ----------------------------
DROP TABLE IF EXISTS `th_sys_user_role_info`;
CREATE TABLE `th_sys_user_role_info`  (
  `user_id` bigint(18) NOT NULL COMMENT '用户ID',
  `role_id` bigint(18) NOT NULL COMMENT '角色ID',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(18) NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(18) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of th_sys_user_role_info
-- ----------------------------
INSERT INTO `th_sys_user_role_info` VALUES (1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 5, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 6, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 7, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 8, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 9, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 10, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 11, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 12, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 13, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 14, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `th_sys_user_role_info` VALUES (1, 15, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
