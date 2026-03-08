-- 添加云服务表
CREATE TABLE `cloud_service` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务ID',
  `service_name` varchar(100) NOT NULL COMMENT '服务名称',
  `platform_type` varchar(50) NOT NULL COMMENT '云平台类型(AWS/Azure/GCP)',
  `access_key` varchar(200) NOT NULL COMMENT '访问密钥',
  `secret_key` varchar(200) NOT NULL COMMENT '密钥',
  `region` varchar(100) DEFAULT NULL COMMENT '区域',
  `endpoint` varchar(200) DEFAULT NULL COMMENT '端点',
  `status` char(1) DEFAULT '0' COMMENT '状态(0:禁用,1:启用)',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `uk_service_name` (`service_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='云服务表';

-- 添加云资源表
CREATE TABLE `cloud_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `service_id` int(11) NOT NULL COMMENT '服务ID',
  `resource_name` varchar(100) NOT NULL COMMENT '资源名称',
  `resource_type` varchar(50) NOT NULL COMMENT '资源类型',
  `resource_id_cloud` varchar(200) NOT NULL COMMENT '云平台资源ID',
  `status` varchar(50) DEFAULT NULL COMMENT '资源状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`resource_id`),
  KEY `idx_service_id` (`service_id`),
  CONSTRAINT `fk_cloud_resource_service` FOREIGN KEY (`service_id`) REFERENCES `cloud_service` (`service_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='云资源表';

-- 添加云测试执行表
CREATE TABLE `cloud_test_execution` (
  `execution_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '执行ID',
  `service_id` int(11) NOT NULL COMMENT '服务ID',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  `test_task_id` int(11) NOT NULL COMMENT '测试任务ID',
  `status` varchar(50) DEFAULT NULL COMMENT '执行状态',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `result` text COMMENT '执行结果',
  PRIMARY KEY (`execution_id`),
  KEY `idx_service_id` (`service_id`),
  KEY `idx_resource_id` (`resource_id`),
  CONSTRAINT `fk_cloud_execution_service` FOREIGN KEY (`service_id`) REFERENCES `cloud_service` (`service_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cloud_execution_resource` FOREIGN KEY (`resource_id`) REFERENCES `cloud_resource` (`resource_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='云测试执行表';
