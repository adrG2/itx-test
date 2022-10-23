CREATE TABLE IF NOT EXISTS `prices`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `brand_id`   INT         NOT NULL,
    `start_date` DATETIME    NOT NULL,
    `end_date`   DATETIME    NOT NULL,
    `price_list` INT         NOT NULL,
    `product_id` VARCHAR(20) NOT NULL,
    `priority`   INT         NOT NULL,
    `price`      VARCHAR(10) NOT NULL,
    `currency`   VARCHAR(5)  NOT NULL,
    PRIMARY KEY (`id`)
);