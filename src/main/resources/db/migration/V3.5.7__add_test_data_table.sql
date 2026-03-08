CREATE TABLE IF NOT EXISTS `project_test_data` (
  `data_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `project_id` int(8) NOT NULL COMMENT '项目ID',
  `environment_id` int(11) NOT NULL COMMENT '环境ID',
  `data_name` varchar(100) NOT NULL COMMENT '数据名称',
  `data_type` varchar(20) NOT NULL COMMENT '数据类型',
  `data_value` text NOT NULL COMMENT '数据值',
  `description` varchar(200) DEFAULT NULL COMMENT '数据描述',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`data_id`),
  KEY `project_id` (`project_id`),
  KEY `environment_id` (`environment_id`),
  CONSTRAINT `project_test_data_ibfk_project` FOREIGN KEY (`project_id`) REFERENCES `sys_project` (`project_id`) ON DELETE CASCADE,
  CONSTRAINT `project_test_data_ibfk_env` FOREIGN KEY (`environment_id`) REFERENCES `project_environment` (`environment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目测试数据表';

-- 插入测试数据
INSERT IGNORE INTO `project_test_data` (`data_id`, `project_id`, `environment_id`, `data_name`, `data_type`, `data_value`, `description`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, 1, 1, '测试用户名', 'string', 'testuser', '测试用的用户名', 'admin', NOW(), 'admin', NOW()),
(2, 1, 1, '测试密码', 'string', 'password123', '测试用的密码', 'admin', NOW(), 'admin', NOW()),
(3, 1, 1, '测试邮箱', 'email', 'test@example.com', '测试用的邮箱地址', 'admin', NOW(), 'admin', NOW()),
(4, 1, 2, '测试手机号', 'phone', '13800138000', '测试用的手机号码', 'admin', NOW(), 'admin', NOW()),
(5, 1, 2, '测试数字', 'number', '12345', '测试用的数字', 'admin', NOW(), 'admin', NOW());