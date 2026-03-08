-- 为协议模板表添加协议类型字段
alter table project_protocol_template add if not exists protocol_type varchar(20) not null default 'HTTP' comment '协议类型：HTTP、WebSocket、gRPC、MQTT等';
