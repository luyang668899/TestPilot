-- 创建GitLab CI/CD集成表
CREATE TABLE IF NOT EXISTS `project_cicd_gitlab` (
  `gitlab_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'GitLab ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `gitlab_name` varchar(100) NOT NULL COMMENT 'GitLab名称',
  `gitlab_url` varchar(255) NOT NULL COMMENT 'GitLab URL',
  `project_path` varchar(255) NOT NULL COMMENT '项目路径',
  `access_token` varchar(255) NOT NULL COMMENT '访问令牌',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`gitlab_id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='GitLab CI/CD集成表';

-- 插入测试数据
INSERT INTO `project_cicd_gitlab` (`project_id`, `project_name`, `gitlab_name`, `gitlab_url`, `project_path`, `access_token`, `status`, `description`) VALUES
(1, '示例项目', 'GitLab测试服务器', 'https://gitlab.example.com', 'luckyframe/luckyframeweb', 'gitlab-token-123', 1, '测试环境GitLab服务器'),
(1, '示例项目', 'GitLab生产服务器', 'https://gitlab.production.com', 'luckyframe/luckyframeweb', 'gitlab-token-456', 1, '生产环境GitLab服务器'),
(2, '测试项目', 'GitLab开发服务器', 'https://gitlab.dev.com', 'test/testproject', 'gitlab-token-789', 1, '开发环境GitLab服务器');