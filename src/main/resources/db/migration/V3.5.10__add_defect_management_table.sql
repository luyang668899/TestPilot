-- 创建缺陷管理集成表
CREATE TABLE IF NOT EXISTS `project_defect_management` (
  `defect_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '缺陷管理ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `defect_type` int(11) NOT NULL COMMENT '缺陷系统类型：1-JIRA，2-Bugzilla，3-其他',
  `defect_system_name` varchar(100) NOT NULL COMMENT '缺陷系统名称',
  `defect_system_url` varchar(255) NOT NULL COMMENT '缺陷系统URL',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `api_token` varchar(255) DEFAULT NULL COMMENT 'API令牌',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`defect_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_defect_type` (`defect_type`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='缺陷管理集成表';

-- 插入测试数据
INSERT INTO `project_defect_management` (`project_id`, `project_name`, `defect_type`, `defect_system_name`, `defect_system_url`, `username`, `password`, `api_token`, `status`, `description`) VALUES
(1, '示例项目', 1, 'JIRA测试系统', 'https://jira.example.com', 'admin', 'admin123', 'jira-api-token-123', 1, 'JIRA缺陷管理系统集成'),
(1, '示例项目', 2, 'Bugzilla测试系统', 'https://bugzilla.example.com', 'admin', 'admin123', 'bugzilla-api-token-123', 1, 'Bugzilla缺陷管理系统集成'),
(2, '测试项目', 1, 'JIRA生产系统', 'https://jira.production.com', 'user', 'user123', 'jira-api-token-456', 1, 'JIRA生产环境缺陷管理系统集成');