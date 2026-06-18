CREATE TABLE IF NOT EXISTS `country` (
                           `country_id` bigint NOT NULL AUTO_INCREMENT,
                           `country_name` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `iso3` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `version` int NOT NULL,
                           `created_at` datetime(3) NOT NULL,
                           `updated_at` datetime(3) NOT NULL,
                           PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `state` (
                         `state_id` bigint NOT NULL AUTO_INCREMENT,
                         `country_id` bigint NOT NULL,
                         `version` int NOT NULL,
                         `state_name` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                         `state_abbreviation` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `created_at` datetime(3) NOT NULL,
                         `updated_at` datetime(3) NOT NULL,
                         PRIMARY KEY (`state_id`),
                         KEY `fk_country_in_state` (`country_id`),
                         CONSTRAINT `fk_country_in_state` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `city` (
                        `city_id` bigint NOT NULL AUTO_INCREMENT,
                        `state_id` bigint NOT NULL,
                        `country_id` bigint NOT NULL,
                        `version` int NOT NULL,
                        `city_name` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                        `created_at` datetime(3) NOT NULL,
                        `updated_at` datetime(3) NOT NULL,
                        PRIMARY KEY (`city_id`),
                        KEY `idx_city_name` (`city_name`),
                        KEY `fk_country_in_city` (`country_id`),
                        KEY `fk_state_in_city` (`state_id`),
                        CONSTRAINT `fk_country_in_city` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
                        CONSTRAINT `fk_state_in_city` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29611 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `zipcode` (
                           `zipcode_id` bigint NOT NULL AUTO_INCREMENT,
                           `city_id` bigint NOT NULL,
                           `state_id` bigint NOT NULL,
                           `country_id` bigint NOT NULL,
                           `version` int NOT NULL,
                           `zipcode` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `latitude` decimal(8,6) DEFAULT NULL,
                           `longitude` decimal(9,6) DEFAULT NULL,
                           `created_at` datetime(3) NOT NULL,
                           `updated_at` datetime(3) NOT NULL,
                           PRIMARY KEY (`zipcode_id`),
                           UNIQUE KEY `uc_country_state_city` (`country_id`,`state_id`,`city_id`,`zipcode`),
                           KEY `idx_city_id` (`city_id`),
                           KEY `idx_state_id` (`state_id`),
                           KEY `idx_country_id` (`country_id`),
                           KEY `idx_zipcode` (`zipcode`),
                           CONSTRAINT `zipcode_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
                           CONSTRAINT `zipcode_ibfk_2` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`),
                           CONSTRAINT `zipcode_ibfk_3` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;