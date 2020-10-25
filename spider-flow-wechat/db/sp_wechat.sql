SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sp_wechat`;
CREATE TABLE `sp_wechat` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `timeout` int(6) NULL DEFAULT NULL COMMENT '超时时间',
  `create_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
