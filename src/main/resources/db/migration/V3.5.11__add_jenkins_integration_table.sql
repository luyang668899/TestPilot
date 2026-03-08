-- 创建Jenkins集成表
CREATE TABLE IF NOT EXISTS `project_cicd_jenkins` (
  `jenkins_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Jenkins ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `jenkins_name` varchar(100) NOT NULL COMMENT 'Jenkins名称',
  `jenkins_url` varchar(255) NOT NULL COMMENT 'Jenkins URL',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `api_token` varchar(255) DEFAULT NULL COMMENT 'API令牌',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`jenkins_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Jenkins集成表';

-- 插入测试数据
INSERT INTO `project_cicd_jenkins` (`project_id`, `project_name`, `jenkins_name`, `jenkins_url`, `username`, `password`, `api_token`, `status`, `description`) VALUES
(1, '示例项目', 'Jenkins测试服务器', 'https://jenkins.example.com', 'admin', 'admin123', 'jenkins-api-token-123', 1, '测试环境Jenkins服务器'),
(1, '示例项目', 'Jenkins生产服务器', 'https://jenkins.production.com', 'user', 'user123', 'jenkins-api-token-456', 1, '生产环境Jenkins服务器'),
(2, '测试项目', 'Jenkins开发服务器', 'https://jenkins.dev.com', 'dev', 'dev123', 'jenkins-api-token-789', 1, '开发环境Jenkins服务器');