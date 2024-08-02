-- ----------------------------
-- Table structure for application_interface
-- ----------------------------
DROP TABLE IF EXISTS application_interface;
CREATE TABLE application_interface (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  interface_id VARCHAR(64) DEFAULT NULL, -- 接口标识
  interface_name VARCHAR(128) DEFAULT NULL, -- 接口名称
  interface_version VARCHAR(16) DEFAULT NULL, -- 接口版本
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  UNIQUE (system_id, interface_id) -- 索引
);

-- ----------------------------
-- Records of application_interface
-- ----------------------------
BEGIN;
INSERT INTO application_interface VALUES (1, 'api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'sayHi', 'v1.0.0', '2022-11-13 13:13:00', '2022-11-13 13:13:00');
COMMIT;

-- ----------------------------
-- Table structure for application_interface_method
-- ----------------------------
DROP TABLE IF EXISTS application_interface_method;
CREATE TABLE application_interface_method (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  interface_id VARCHAR(64) DEFAULT NULL, -- 接口标识
  method_id VARCHAR(64) DEFAULT NULL, -- 方法标识
  method_name VARCHAR(128) DEFAULT NULL, -- 方法名称
  parameter_type VARCHAR(256) DEFAULT NULL, -- 参数类型
  uri VARCHAR(126) DEFAULT NULL, -- 网关接口
  http_command_type VARCHAR(32) DEFAULT NULL, -- 接口类型
  auth INTEGER DEFAULT NULL, -- true = 1是、false = 0否
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  UNIQUE (system_id, interface_id, method_id) -- 索引
);

-- ----------------------------
-- Records of application_interface_method
-- ----------------------------
BEGIN;
INSERT INTO application_interface_method VALUES (1, 'api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'sayHi', '测试方法', 'java.lang.String', '/wg/activity/sayHi', 'GET', 0, '2022-11-13 13:16:52', '2022-11-13 13:16:52');
INSERT INTO application_interface_method VALUES (2, 'api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'insert', '插入方法', 'io.github.chenyilei2016.providerApi.dto.XReq', '/wg/activity/insert', 'POST', 1, '2022-11-13 13:16:52', '2022-11-13 13:16:52');
COMMIT;

-- ----------------------------
-- Table structure for application_system
-- ----------------------------
DROP TABLE IF EXISTS application_system;
CREATE TABLE application_system (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  system_name VARCHAR(128) DEFAULT NULL, -- 系统名称
  system_type VARCHAR(4) DEFAULT NULL, -- 系统类型
  system_registry VARCHAR(128) DEFAULT NULL, -- 注册中心
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  UNIQUE (system_id) -- 索引
);

-- ----------------------------
-- Records of application_system
-- ----------------------------
BEGIN;
INSERT INTO application_system VALUES (1, 'lottery-api', '抽奖API系统', 'RPC', '127.0.0.1', '2022-11-13 13:10:03', '2022-11-13 13:10:03');
INSERT INTO application_system VALUES (3, 'api-gateway-test', '网关测试系统', 'RPC', '127.0.0.1', '2022-11-13 13:12:54', '2022-11-13 13:12:54');
COMMIT;

-- ----------------------------
-- Table structure for gateway_distribution
-- ----------------------------
DROP TABLE IF EXISTS gateway_distribution;
CREATE TABLE gateway_distribution (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  group_id VARCHAR(64) DEFAULT NULL, -- 分组标识
  gateway_id VARCHAR(64) DEFAULT NULL, -- 网关标识
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  system_name VARCHAR(128) DEFAULT NULL, -- 系统名称
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL -- 更新时间
);

-- ----------------------------
-- Records of gateway_distribution
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gateway_server
-- ----------------------------
DROP TABLE IF EXISTS gateway_server;
CREATE TABLE gateway_server (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  group_id VARCHAR(32) DEFAULT NULL, -- 分组标识
  group_name VARCHAR(128) DEFAULT NULL -- 分组名称
);

-- ----------------------------
-- Records of gateway_server
-- ----------------------------
BEGIN;
INSERT INTO gateway_server VALUES (1, '10001', '缺省的');
COMMIT;

-- ----------------------------
-- Table structure for gateway_server_detail
-- ----------------------------
DROP TABLE IF EXISTS gateway_server_detail;
CREATE TABLE gateway_server_detail (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  group_id VARCHAR(32) DEFAULT NULL, -- 分组标识
  gateway_id VARCHAR(32) DEFAULT NULL, -- 网关标识
  gateway_name VARCHAR(128) DEFAULT NULL, -- 网关名称
  gateway_address VARCHAR(64) DEFAULT NULL, -- 网关地址：127.0.0.1
  status VARCHAR(4) DEFAULT NULL, -- 服务状态：0不可用、1可使用
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  UNIQUE (gateway_id, gateway_address) -- 索引
);

-- ----------------------------
-- Records of gateway_server_detail
-- ----------------------------
BEGIN;
INSERT INTO gateway_server_detail VALUES (13, '10001', 'api-gateway-g1', '电商支付网关', '127.0.0.196', '1', '2022-11-06 15:22:11', '2022-11-06 15:22:11');
INSERT INTO gateway_server_detail VALUES (14, '10001', 'api-gateway-g2', '电商支付网关', '127.0.0.197', '1', '2022-11-06 15:22:11', '2022-11-06 15:22:11');
INSERT INTO gateway_server_detail VALUES (15, '10001', 'api-gateway-g3', '电商配送网关', '127.0.0.198', '1', '2022-11-06 15:23:19', '2022-11-06 15:23:19');
COMMIT;

-- ----------------------------
-- Table structure for http_statement
-- ----------------------------
DROP TABLE IF EXISTS http_statement;
CREATE TABLE http_statement (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  application VARCHAR(128) NOT NULL, -- 应用名称
  interface_name VARCHAR(256) NOT NULL, -- 服务接口
  method_name VARCHAR(128) NOT NULL, -- 服务方法
  parameter_type VARCHAR(256) NOT NULL, -- 参数类型
  uri VARCHAR(128) NOT NULL, -- 网关接口
  http_command_type VARCHAR(32) NOT NULL, -- 接口类型
  auth INTEGER NOT NULL DEFAULT 0, -- true = 1是、false = 0否
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL -- 更新时间
);

-- ----------------------------
-- Records of http_statement
-- ----------------------------
BEGIN;
INSERT INTO http_statement VALUES (1, 'api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'sayHi', 'java.lang.String', '/wg/activity/sayHi', 'GET', 0, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
INSERT INTO http_statement VALUES (2, 'api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'insert', 'io.github.chenyilei2016.providerApi.dto.XReq', '/wg/activity/insert', 'POST', 1, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
COMMIT;