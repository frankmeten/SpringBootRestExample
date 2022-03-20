CREATE DATABASE IF NOT EXISTS bird_sight;
use bird_sight;
DROP
  TABLE IF EXISTS `bird`;
CREATE TABLE `bird` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `height` double NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UniqueNameAndColor` (`name`, `color`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
LOCK TABLES `bird` WRITE;
INSERT INTO `bird`
VALUES
  (
    1, 'Black', 34.9, 'American Black Duck',
    96.9
  ),
  (
    2, 'Wood', 34.9, 'Australian Wood Duck',
    9436.9
  ),
  (3, 'Red', 34.9, 'Lake Duck', 96.9),
  (
    4, 'Black', 34.9, 'Pacific Black Duck',
    96.9
  ),
  (
    5, 'Red', 34.9, 'Red-Billed Duck',
    96.9
  ),
  (
    6, 'Black', 34.9, 'African Black Duck',
    96.9
  );
UNLOCK TABLES;
DROP
  TABLE IF EXISTS `sighting`;
CREATE TABLE `sighting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sighting_time` datetime(6) DEFAULT NULL,
  `bird_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UniqueBirdAndLocationAndDateTime` (
    `bird_id`, `location`, `sighting_time`
  ),
  CONSTRAINT `FKBirdTableID` FOREIGN KEY (`bird_id`) REFERENCES `bird` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
LOCK TABLES `sighting` WRITE;
INSERT INTO `sighting`
VALUES
  (
    6, 'Beijing China', '2012-03-19 14:14:53',
    2
  ),
  (
    3, 'Shanghai China', '2020-03-19 14:14:53',
    3
  ),
  (
    8, 'Tianjin China', '2021-03-19 14:14:53',
    3
  ),
  (
    10, 'Chongqing China', '2022-01-19 14:14:53',
    3
  ),
  (
    1, 'Shandong China', '2022-02-19 14:14:53',
    4
  );
UNLOCK TABLES;
