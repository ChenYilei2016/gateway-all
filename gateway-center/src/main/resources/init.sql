
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