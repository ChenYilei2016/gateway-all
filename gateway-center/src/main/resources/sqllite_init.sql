
-- 无需启动数据库, 快速使用

DROP TABLE IF EXISTS http_statement;
CREATE TABLE http_statement (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                application TEXT NOT NULL COMMENT '应用名称',
                                interface_name TEXT NOT NULL COMMENT '服务接口；RPC、其他',
                                method_name TEXT NOT NULL COMMENT '服务方法；RPC#method',
                                parameter_type TEXT NOT NULL COMMENT '参数类型(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}',
                                uri TEXT NOT NULL COMMENT '网关接口',
                                http_command_type TEXT NOT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
                                auth INTEGER NOT NULL DEFAULT 0 COMMENT 'true = 1是、false = 0否',
                                create_time TEXT, -- 你可以在这里使用DATETIME或TEXT，取决于你的需求
                                update_time TEXT, -- 同样，你可以使用DATETIME或TEXT
    -- 你可能需要创建一个触发器来更新update_time字段
    -- 触发器示例将在下面给出
    -- ...
);

-- 插入数据的SQL保持不变（因为数据格式是兼容的）
BEGIN TRANSACTION;
INSERT INTO http_statement (application, interface_name, method_name, parameter_type, uri, http_command_type, auth, create_time, update_time) VALUES
    ('api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'sayHi', 'java.lang.String', '/wg/activity/sayHi', 'GET', 0, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
INSERT INTO http_statement (application, interface_name, method_name, parameter_type, uri, http_command_type, auth, create_time, update_time) VALUES
    ('api-gateway-test', 'io.github.chenyilei2016.providerApi.IActivityBooth', 'insert', 'io.github.chenyilei2016.providerApi.dto.XReq', '/wg/activity/insert', 'POST', 1, '2022-10-22 15:30:00', '2022-10-22 15:30:00');
COMMIT;

-- 触发器示例来更新update_time字段（当其他字段更新时）
CREATE TRIGGER update_http_statement_update_time
    AFTER UPDATE ON http_statement
    FOR EACH ROW
    WHEN OLD.id IS NOT NULL
BEGIN
    UPDATE http_statement SET update_time = datetime('now') WHERE id = OLD.id;
END;






DROP TABLE IF EXISTS application_interface;
CREATE TABLE application_interface (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  interface_id VARCHAR(64) DEFAULT NULL, -- 接口标识
  interface_name VARCHAR(128) DEFAULT NULL, -- 接口名称
  interface_version VARCHAR(16) DEFAULT NULL, -- 接口版本
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL -- 更新时间
);

-- ----------------------------
-- Records of application_interface
-- ----------------------------
-- No changes needed for BEGIN and COMMIT in SQLite

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
  parameter_type VARCHAR(256) DEFAULT NULL, -- 参数类型；(RPC 限定单参数注册)；new String[]{"java.lang.String"}、new String[]{"cn.bugstack.gateway.rpc.dto.XReq"}
  uri VARCHAR(126) DEFAULT NULL, -- 网关接口
  http_command_type VARCHAR(32) DEFAULT NULL, -- 接口类型；GET、POST、PUT、DELETE
  auth INTEGER DEFAULT NULL, -- true = 1是、false = 0否
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL -- 更新时间
);

-- ----------------------------
-- Records of application_interface_method
-- ----------------------------
-- No changes needed for BEGIN and COMMIT in SQLite

-- ----------------------------
-- Table structure for application_system
-- ----------------------------
DROP TABLE IF EXISTS application_system;
CREATE TABLE application_system (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  system_name VARCHAR(128) DEFAULT NULL, -- 系统名称
  system_type VARCHAR(4) DEFAULT NULL, -- 系统类型；RPC、HTTP
  system_registry VARCHAR(128) DEFAULT NULL, -- 注册中心
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL -- 更新时间
);

-- ----------------------------
-- Records of application_system
-- ----------------------------
-- No changes needed for BEGIN and COMMIT in SQLite

-- ----------------------------
-- Table structure for gateway_distribution
-- ----------------------------
DROP TABLE IF EXISTS gateway_distribution;
CREATE TABLE gateway_distribution (
  id INTEGER NOT NULL, -- 自增主键
  group_id VARCHAR(64) DEFAULT NULL, -- 分组标识
  gateway_id VARCHAR(64) DEFAULT NULL, -- 网关标识
  system_id VARCHAR(64) DEFAULT NULL, -- 系统标识
  system_name VARCHAR(128) DEFAULT NULL, -- 系统名称
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of gateway_distribution
-- ----------------------------
-- No changes needed for BEGIN and COMMIT in SQLite

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
-- No changes needed for BEGIN and COMMIT in SQLite

-- ----------------------------
-- Table structure for gateway_server_detail
-- ----------------------------
DROP TABLE IF EXISTS gateway_server_detail;
CREATE TABLE gateway_server_detail (
  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- 自增主键
  gateway_id VARCHAR(32) DEFAULT NULL, -- 网关标识
  gateway_name VARCHAR(128) DEFAULT NULL, -- 网关名称
  gateway_address VARCHAR(64) DEFAULT NULL, -- 网关地址：127.0.0.1
  status VARCHAR(4) DEFAULT NULL, -- 服务状态：0不可用、1可使用
  create_time DATETIME DEFAULT NULL, -- 创建时间
  update_time DATETIME DEFAULT NULL, -- 更新时间
  UNIQUE (gateway_id)
);


