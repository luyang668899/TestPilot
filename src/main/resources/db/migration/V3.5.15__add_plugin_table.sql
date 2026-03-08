CREATE TABLE `plugin` (
  `plugin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plugin_name` varchar(50) NOT NULL COMMENT '插件名称',
  `plugin_type` varchar(50) NOT NULL COMMENT '插件类型',
  `plugin_version` varchar(20) NOT NULL COMMENT '插件版本',
  `plugin_path` varchar(200) NOT NULL COMMENT '插件路径',
  `status` varchar(10) NOT NULL COMMENT '插件状态',
  `description` varchar(200) DEFAULT NULL COMMENT '插件描述',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='插件管理表';
