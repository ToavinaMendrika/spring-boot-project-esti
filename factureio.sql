-- MySQL Script generated by MySQL Workbench
-- Wed Oct 16 14:13:58 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles` ;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `ceo` VARCHAR(45) NOT NULL,
  `capital` VARCHAR(45) NOT NULL,
  `hq` VARCHAR(45) NULL,
  `activity` VARCHAR(45) NULL,
  `created_at` VARCHAR(45) NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`id`, `roles_id`),
  INDEX `fk_users_roles1_idx` (`roles_id` ASC) ,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clients` ;

CREATE TABLE IF NOT EXISTS `clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `factures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `factures` ;

CREATE TABLE IF NOT EXISTS `factures` (
  `id` INT NOT NULL,
  `created_at` VARCHAR(45) NOT NULL,
  `clients_id` INT NOT NULL,
  PRIMARY KEY (`id`, `clients_id`),
  INDEX `fk_factures_clients1_idx` (`clients_id` ASC) ,
  CONSTRAINT `fk_factures_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `factures_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `factures_users` ;

CREATE TABLE IF NOT EXISTS `factures_users` (
  `factures_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`factures_id`, `users_id`),
  INDEX `fk_factures_has_users_users1_idx` (`users_id` ASC) ,
  INDEX `fk_factures_has_users_factures_idx` (`factures_id` ASC) ,
  CONSTRAINT `fk_factures_has_users_factures`
    FOREIGN KEY (`factures_id`)
    REFERENCES `factures` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factures_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clients_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clients_users` ;

CREATE TABLE IF NOT EXISTS `clients_users` (
  `clients_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`clients_id`, `users_id`),
  INDEX `fk_clients_has_users_users1_idx` (`users_id` ASC) ,
  INDEX `fk_clients_has_users_clients1_idx` (`clients_id` ASC) ,
  CONSTRAINT `fk_clients_has_users_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_users_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;