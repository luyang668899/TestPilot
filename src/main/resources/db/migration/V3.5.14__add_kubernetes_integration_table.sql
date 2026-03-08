CREATE TABLE `ci_cd_kubernetes` (
  `kubernetes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `kubernetes_name` varchar(50) NOT NULL COMMENT 'Kubernetes名称',
  `cluster_url` varchar(100) NOT NULL COMMENT '集群地址',
  `namespace` varchar(50) NOT NULL COMMENT '命名空间',
  `auth_type` varchar(20) NOT NULL COMMENT '认证方式',
  `kubeconfig_path` varchar(200) NOT NULL COMMENT 'Kubeconfig文件路径',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` varchar(10) NOT NULL COMMENT '状态',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`kubernetes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Kubernetes集群管理表';
