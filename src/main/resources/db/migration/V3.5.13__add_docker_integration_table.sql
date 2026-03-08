CREATE TABLE `ci_cd_docker` (
  `docker_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `docker_name` varchar(50) NOT NULL COMMENT 'Docker名称',
  `docker_url` varchar(100) NOT NULL COMMENT 'Docker地址',
  `port` int(11) NOT NULL COMMENT '端口',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` varchar(10) NOT NULL COMMENT '状态',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`docker_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Docker容器管理表';
