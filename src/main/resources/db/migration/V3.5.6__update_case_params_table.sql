-- 修改project_case_params表，将envName字段替换为environmentId字段
ALTER TABLE `project_case_params` ADD COLUMN IF NOT EXISTS `environment_id` int(11) DEFAULT NULL COMMENT '环境ID' AFTER `params_id`;

-- 为现有数据设置默认环境ID
UPDATE `project_case_params` pcp
JOIN `project_environment` pe ON pcp.project_id = pe.project_id AND pe.is_default = 'Y'
SET pcp.environment_id = pe.environment_id;

-- 删除原有的envName字段（如果存在）
SET @exists := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema = 'luckyframe' AND table_name = 'project_case_params' AND column_name = 'env_name');
SET @sql := IF(@exists > 0, 'ALTER TABLE `project_case_params` DROP COLUMN `env_name`;', 'SELECT 1');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 添加外键约束（如果不存在）
SET @constraint_exists := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS WHERE constraint_schema = 'luckyframe' AND constraint_name = 'project_case_params_ibfk_env');
SET @sql := IF(@constraint_exists = 0, 'ALTER TABLE `project_case_params` ADD CONSTRAINT `project_case_params_ibfk_env` FOREIGN KEY (`environment_id`) REFERENCES `project_environment` (`environment_id`) ON DELETE CASCADE ON UPDATE CASCADE;', 'SELECT 1');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
