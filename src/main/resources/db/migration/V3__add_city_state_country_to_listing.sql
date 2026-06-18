ALTER TABLE listings
    ADD COLUMN `city_id` bigint
    AFTER longitude;

ALTER TABLE listings
    ADD COLUMN `state_id` bigint
        AFTER city_id;
ALTER TABLE listings
    ADD COLUMN `country_id` bigint
        AFTER state_id;

Alter table listings
    ADD CONSTRAINT `fk_city_in_listings` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`);

Alter table listings
    ADD CONSTRAINT `fk_state_in_listings` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`);


Alter table listings
    ADD CONSTRAINT `fk_country_in_listings` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`);