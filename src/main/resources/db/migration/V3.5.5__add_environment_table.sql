CREATE TABLE IF NOT EXISTS `project_environment` (
  `environment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '环境ID',
  `environment_name` varchar(50) NOT NULL COMMENT '环境名称',
  `environment_desc` varchar(200) DEFAULT NULL COMMENT '环境描述',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认环境',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`environment_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `project_environment_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `sys_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='环境配置表';

-- 为现有项目创建默认环境
INSERT INTO `project_environment` (`environment_name`, `environment_desc`, `project_id`, `is_default`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '默认环境', '项目默认测试环境', `project_id`, 'Y', 'system', NOW(), 'system', NOW(), '系统默认创建' FROM `sys_project`;
