-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eventtracker
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eventtracker` ;

-- -----------------------------------------------------
-- Schema eventtracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eventtracker` DEFAULT CHARACTER SET utf8 ;
USE `eventtracker` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `msrp` DOUBLE NOT NULL,
  `description` TEXT NULL,
  `online_mp` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `publisher` ;

CREATE TABLE IF NOT EXISTS `publisher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_publisher` ;

CREATE TABLE IF NOT EXISTS `game_publisher` (
  `game_id` INT NOT NULL,
  `publisher_id` INT NOT NULL,
  PRIMARY KEY (`game_id`, `publisher_id`),
  INDEX `fk_game_has_publisher_publisher1_idx` (`publisher_id` ASC),
  INDEX `fk_game_has_publisher_game_idx` (`game_id` ASC),
  CONSTRAINT `fk_game_has_publisher_game`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_has_publisher_publisher1`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `publisher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre` ;

CREATE TABLE IF NOT EXISTS `genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_genre` ;

CREATE TABLE IF NOT EXISTS `game_genre` (
  `game_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`game_id`, `genre_id`),
  INDEX `fk_game_has_genre_genre1_idx` (`genre_id` ASC),
  INDEX `fk_game_has_genre_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_game_has_genre_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sale` ;

CREATE TABLE IF NOT EXISTS `sale` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_start` DATETIME NOT NULL,
  `date_end` DATETIME NOT NULL,
  `percent_discount` DOUBLE NOT NULL,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sale_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sale_game` ;

CREATE TABLE IF NOT EXISTS `sale_game` (
  `sale_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`sale_id`, `game_id`),
  INDEX `fk_sale_has_game_game1_idx` (`game_id` ASC),
  INDEX `fk_sale_has_game_sale1_idx` (`sale_id` ASC),
  CONSTRAINT `fk_sale_has_game_sale1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_has_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS appuser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'appuser' IDENTIFIED BY 'appuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'appuser';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `user` (`id`, `username`, `first_name`) VALUES (1, 'admin', 'administrator');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (1, 'Minecraft', 29.99, 'A fun blockbuilding game', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (2, 'Grand Theft Auto V', 29.99, 'Commit crimes in an open world environment', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (3, 'The Elder Scrolls V: Skyrim Anniversary Edition', 49.99, 'Enjoy Skyrim for the 20th time!', 0);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (4, 'EA SPORTS FIFA 23', 69.99, 'Play the same exact game as last year, but with an updated roster. Now with more microtransactions!', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (5, 'FIFA 22', 59.99, 'Play some virtual soccer, because you don\'t have the friends to play real soccer', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (6, 'Farcry 5', 59.99, 'Take on a cult leader on in rural Montana. It\'s definitely not a parable for anything at all.', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (7, 'Call of Duty: Modern Warfare II', 69.99, 'Replay your favorite COD game from your childhood! Now with sligthly better graphics!', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (8, 'Call of Duty: Black Ops', 39.99, 'A COD game. What more do you want from me?', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (9, 'Call of Duty: WWII', 59.99, 'COD, but based in WWII. Ain\'t that exciting?', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (10, 'Cyberpunk 2077', 59.99, 'Its like GTA, but you have Keanu Reaves stuck in your head! And Future!', 0);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (11, 'Hearts of Iron IV', 39.99, '\"Manchuria is invading Italy!\" has never sounded more plausible', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (12, 'Cities: Skylines', 29.99, 'It\'s like the Sims, but the stupid creature you\'re trying to take care of and keep alive is an entire city', 0);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (13, 'Sid Meier\'s Civilization V', 29.99, 'Ghandi is still nuke happy in this simulator, so go wild invading every other civilization', 1);
INSERT INTO `game` (`id`, `title`, `msrp`, `description`, `online_mp`) VALUES (14, 'Sid Meier\'s Civilization VI', 59.99, 'Same, loveable war crimes, but this time with natural disasters!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `publisher`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `publisher` (`id`, `name`) VALUES (1, 'Mojang');
INSERT INTO `publisher` (`id`, `name`) VALUES (2, 'Rockstar Games');
INSERT INTO `publisher` (`id`, `name`) VALUES (3, 'Bethesda');
INSERT INTO `publisher` (`id`, `name`) VALUES (4, 'EA');
INSERT INTO `publisher` (`id`, `name`) VALUES (5, 'Activision');
INSERT INTO `publisher` (`id`, `name`) VALUES (6, 'CD Projekt Red');
INSERT INTO `publisher` (`id`, `name`) VALUES (7, 'Paradox Interactive');
INSERT INTO `publisher` (`id`, `name`) VALUES (8, 'Firaxis Games');
INSERT INTO `publisher` (`id`, `name`) VALUES (9, 'Ubisoft');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_publisher`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (1, 1);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (2, 2);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (3, 3);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (4, 4);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (5, 4);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (6, 9);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (7, 5);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (8, 5);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (9, 5);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (10, 6);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (11, 7);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (12, 7);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (13, 8);
INSERT INTO `game_publisher` (`game_id`, `publisher_id`) VALUES (14, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `genre` (`id`, `name`) VALUES (1, 'Simulator');
INSERT INTO `genre` (`id`, `name`) VALUES (2, 'Sandbox');
INSERT INTO `genre` (`id`, `name`) VALUES (3, 'Open-World');
INSERT INTO `genre` (`id`, `name`) VALUES (4, 'FPS');
INSERT INTO `genre` (`id`, `name`) VALUES (5, 'Online Multiplayer');
INSERT INTO `genre` (`id`, `name`) VALUES (6, 'Historic');
INSERT INTO `genre` (`id`, `name`) VALUES (7, 'Sports');
INSERT INTO `genre` (`id`, `name`) VALUES (8, 'Strategy');
INSERT INTO `genre` (`id`, `name`) VALUES (9, 'Turn-Based');
INSERT INTO `genre` (`id`, `name`) VALUES (10, 'RPG');
INSERT INTO `genre` (`id`, `name`) VALUES (11, 'Action');
INSERT INTO `genre` (`id`, `name`) VALUES (12, 'Co-op');
INSERT INTO `genre` (`id`, `name`) VALUES (13, 'War');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (1, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (1, 2);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (1, 3);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (1, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (1, 12);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (2, 2);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (2, 3);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (2, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (2, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (2, 10);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (3, 10);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (3, 3);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (4, 7);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (4, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (4, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (5, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (5, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (5, 7);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (6, 3);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (6, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (6, 11);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (6, 12);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (7, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (7, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (7, 13);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (8, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (8, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (8, 13);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (9, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (9, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (9, 13);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (10, 3);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (10, 4);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (10, 10);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (10, 11);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (11, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (11, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (11, 6);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (11, 8);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (11, 13);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (12, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (12, 2);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (12, 8);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (13, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (13, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (13, 6);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (13, 8);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (13, 9);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (14, 1);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (14, 5);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (14, 6);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (14, 8);
INSERT INTO `game_genre` (`game_id`, `genre_id`) VALUES (14, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sale`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `sale` (`id`, `date_start`, `date_end`, `percent_discount`, `name`, `description`) VALUES (1, '2022-12-24 00:00:00', '2022-12-25 23:59:59', 60, 'Everything must go sale!', 'Everything must fly off the digital shelves! Everything is 60% off!');
INSERT INTO `sale` (`id`, `date_start`, `date_end`, `percent_discount`, `name`, `description`) VALUES (2, '2022-11-04 00:00:00', '2022-11-11 23:59:59', 20, 'Functional Sale', 'A sale to ensure functionality');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sale_game`
-- -----------------------------------------------------
START TRANSACTION;
USE `eventtracker`;
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 1);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 2);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 3);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 4);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 5);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 6);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 7);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 8);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 9);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 10);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 11);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 12);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 13);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (1, 14);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (2, 13);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (2, 14);
INSERT INTO `sale_game` (`sale_id`, `game_id`) VALUES (2, 6);

COMMIT;

