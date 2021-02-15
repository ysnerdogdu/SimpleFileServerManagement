CREATE DATABASE IF NOT EXISTS simple_file_management_db;

USE simple_file_management_db;

CREATE TABLE folder
(
    id                      BIGINT(20) NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(255) DEFAULT NULL,
    size                    BIGINT(20)   DEFAULT NULL,
    creation_date           DATETIME     DEFAULT NULL,
    last_modification_date  DATETIME     DEFAULT NULL,
    type                    VARCHAR(50) DEFAULT NULL,
    parent_folder_id        BIGINT(20)   DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `folder_fk` FOREIGN KEY (`parent_folder_id`) REFERENCES `folder` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE file
(
    id                      BIGINT(20) NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(255) DEFAULT NULL,
    extension               VARCHAR(255) DEFAULT NULL,
    content                 VARCHAR(255) DEFAULT NULL,
    size                    BIGINT(20)   DEFAULT NULL,
    creation_date           DATETIME     DEFAULT NULL,
    last_modification_date  DATETIME     DEFAULT NULL,
    type                    VARCHAR(50) DEFAULT NULL,
    parent_folder_id        BIGINT(20)   DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `file_fk` FOREIGN KEY (`parent_folder_id`) REFERENCES `folder` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


