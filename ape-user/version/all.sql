--2024-06-14 增加usersql
CREATE TABLE `user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `age`         int                                    DEFAULT NULL,
    `create_by`   varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT NULL,
    `update_by`   varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL,
    `delete_flag` tinyint                                DEFAULT NULL,
    `version`     int                                    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;