CREATE TABLE IF NOT EXISTS `listings` (
                                        `listing_id` bigint NOT NULL AUTO_INCREMENT,
                                        `title` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `category` varchar(50) NOT NULL,
                                        `condition` varchar(50) NOT NULL,
                                        `status` varchar(30) NOT NULL,
                                        `latitude` DECIMAL(8, 6) NOT NULL,
                                        `longitude` DECIMAL(9, 6) NOT NULL,
                                        `version` int NOT NULL,
    `created_at` datetime(3) NOT NULL,
    `updated_at` datetime(3) NOT NULL,
    PRIMARY KEY (`listing_id`),
    UNIQUE KEY `uc_latitude_longitude` (`latitude`,`longitude`)
    ) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;