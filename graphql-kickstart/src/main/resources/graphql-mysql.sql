# mysql sql脚本

# 创建一个graphql用户,密码为graphql@12345
create user 'graphql'@'%' identified by 'graphql@12345';

# 校验用户是否创建成功
select user,host from mysql.user;

# 分配权限(把所有权限都给了它)
grant all privileges on *.* TO graphql@'%';

# 查询用户权限(确认是否分配成功)
show grants for graphql@'%';


# 创建用户表
create database graphql;
use graphql;
CREATE TABLE `user_info`  (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键,自增',
      `user_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号',
      `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
      `age` int NOT NULL COMMENT '年龄',
      `salary` double COMMENT '工资',
      `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '地址',
      `profession` int DEFAULT 0 NOT NULL COMMENT '职业 0未知 1软件开发 2律师 3老师 4医生 5设计师 6守护者 7呀哈哈 8最终Boss 9小Boss 10NPC 11主角 12公主 13最终不想编了',
      `create_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建日期',
      `update_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新日期',
      `is_delete` tinyint DEFAULT '1' NOT NULL COMMENT '删除状态 0删除 1未删除/存活',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

# 数据
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (1, 'zelda_001', '林克', 117, NULL, '海拉鲁大陆-初始台地', 11, '2021-12-26 18:13:30', '2021-12-26 18:13:30', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (2, 'zelda_002', '塞尔达', 116, NULL, '海拉鲁大陆-城堡', 12, '2021-12-26 18:14:04', '2021-12-26 18:14:04', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (3, 'zelda_003', '盖侬', 10000, NULL, '海拉鲁大陆-城堡', 8, '2021-12-26 18:15:15', '2021-12-26 18:15:15', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (4, 'zelda_004', '守护者I型', 1000, NULL, '海拉鲁大陆-神庙', 6, '2021-12-26 18:17:32', '2021-12-26 18:17:32', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (5, 'zelda_005', '守护者II型', 1001, NULL, '海拉鲁大陆-到处都是', 6, '2021-12-26 18:21:10', '2021-12-26 18:21:10', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (6, 'zelda_006', '守护者III型', 1002, NULL, '海拉鲁大陆', 6, '2021-12-26 18:22:02', '2021-12-26 18:22:02', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (7, 'zelda_007', '人马', 150, NULL, '海拉鲁大陆', 9, '2021-12-26 18:22:56', '2021-12-26 18:22:56', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (8, 'zelda_008', '米法', 100, NULL, '海拉鲁大陆-水神兽', 10, '2021-12-26 18:25:19', '2021-12-26 18:25:19', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (9, 'china_1', '孔子', 2487, NULL, '春秋战国-鲁国', 3, '2021-12-26 18:29:26', '2021-12-26 18:29:26', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (10, 'china_2', '华佗', 1874, NULL, '东汉末年-沛国', 4, '2021-12-26 18:30:50', '2021-12-26 18:30:50', 1);
INSERT INTO `user_info` (`id`, `user_id`, `name`, `age`, `salary`, `address`, `profession`, `create_time`, `update_time`, `is_delete`) VALUES (11, 'china_3', '成吉思汗', 786, NULL, '宋朝末期-蒙古国', 0, '2021-12-26 18:34:29', '2021-12-26 18:34:29', 1);
