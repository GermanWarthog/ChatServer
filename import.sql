CREATE TABLE IF NOT EXISTS `Chat` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `owner` VARCHAR(64) DEFAULT 'unkown', 
    `content` LONGTEXT DEFAULT '',
    
    PRIMARY KEY (`id`)
);