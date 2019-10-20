CREATE DATABASE IF NOT EXISTS calendar;

CREATE TABLE calendar.schedules
(
    `id`      int(11)      NOT NULL AUTO_INCREMENT,
    `name`    varchar(100) NOT NULL,
    `date`    varchar(100) NOT NULL,
    `all_day` tinyint(1)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE calendar.additional_information
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `schedule_id` int(11)      NOT NULL,
    `name`        varchar(100) NOT NULL,
    `value`       varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE calendar.schedules_categories
(
    `schedule_id` int(11) NOT NULL,
    `category_id` int(11) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE calendar.categories
(
    `id`   int(11)      NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

