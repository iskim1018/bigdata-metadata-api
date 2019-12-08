-- -----------------------------------------------------
-- Metadata 담당자
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NULL,
  `email` VARCHAR(50) NULL,
  `phone` VARCHAR(20) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Platform 정보
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `platforms` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `manager_id` BIGINT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `code` VARCHAR(30) NULL,
  `api_url` VARCHAR(255) NOT NULL,
  `api_key` VARCHAR(255) NULL,
  `token` VARCHAR(255) NULL,
  `last_fetched` VARCHAR(20) NULL DEFAULT '',
  PRIMARY KEY (`id`),
  INDEX `fk_platforms_managers_idx` (`manager_id` ASC),
  CONSTRAINT `fk_platforms_managers`
    FOREIGN KEY (`manager_id`)
    REFERENCES `managers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- -- Center 정보
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `centers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `platform_id` BIGINT NOT NULL,
  `code` VARCHAR(30) NULL,
  `name` VARCHAR(50) NULL,
  `email` VARCHAR(50) NULL,
  `phone` VARCHAR(20) NULL,
  `homepage` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_centers_platforms1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_centers_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platforms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Catalog 관리
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalogs` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `platform_id` BIGINT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_catalogs_platforms1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_catalogs_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platforms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Dataset 관리
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datasets` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `platform_id` BIGINT NOT NULL,
  `catalog_id` BIGINT NULL,
  `contact_point_id` BIGINT NULL,
  `creator_id` BIGINT NULL,
  `publisher_id` BIGINT NULL,
  `title` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `identifier` VARCHAR(255) NULL,
  `theme` VARCHAR(255) NULL,
  `license` VARCHAR(255) NULL,
  `access_rights` VARCHAR(10) NULL,
  `language` VARCHAR(2) NULL,
  `landing_page` VARCHAR(255) NULL,
  `rights` VARCHAR(255) NULL,
  `accrual_periodicity` VARCHAR(30) NULL,
  `issued` VARCHAR(50) NULL,
  `modified` VARCHAR(50) NULL,
  `spatial` LONGTEXT NULL,
  `spatial_resolution_in_meters` VARCHAR(20) NULL,
  `temporal` LONGTEXT NULL,
  `temporal_resolution` VARCHAR(30) NULL,
  `version` VARCHAR(50) NULL,
  `version_description` VARCHAR(255) NULL,
  `price_info` TEXT NULL,
  `quality_info` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_datasets_platforms1_idx` (`platform_id` ASC),
  INDEX `fk_datasets_catalogs1_idx` (`catalog_id` ASC),
  INDEX `fk_datasets_managers1_idx` (`contact_point_id` ASC),
  INDEX `fk_datasets_centers1_idx` (`creator_id` ASC),
  INDEX `fk_datasets_centers2_idx` (`publisher_id` ASC),
  CONSTRAINT `fk_datasets_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platforms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datasets_catalogs1`
    FOREIGN KEY (`catalog_id`)
    REFERENCES `catalogs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datasets_managers1`
    FOREIGN KEY (`contact_point_id`)
    REFERENCES `managers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datasets_centers1`
    FOREIGN KEY (`creator_id`)
    REFERENCES `centers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_datasets_centers2`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `centers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- FileData(Distribution) 관리
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `file_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `dataset_id` BIGINT NOT NULL,
  `title` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `filename` VARCHAR(255) NULL,
  `access_service` VARCHAR(255) NULL,
  `access_url` VARCHAR(255) NULL,
  `download_url` VARCHAR(255) NULL,
  `compress_format` VARCHAR(255) NULL,
  `media_type` VARCHAR(255) NULL,
  `package_format` VARCHAR(255) NULL,
  `format` VARCHAR(255) NULL,
  `rights` VARCHAR(255) NULL,
  `mime_type` VARCHAR(255) NULL,
  `ext` VARCHAR(10) NULL,
  `issued` VARCHAR(50) NULL,
  `modified` VARCHAR(50) NULL,
  `byte_size` BIGINT NULL,
  `license` VARCHAR(255) NULL,
  `spatial_resolution_in_meters` VARCHAR(20) NULL,
  `temporal_resolution` VARCHAR(30) NULL,
  `access_rights` VARCHAR(10) NULL,
  `version` VARCHAR(50) NULL,
  `version_description` VARCHAR(255) NULL,
  `price_info` TEXT NULL,
  `quality_info` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_file_data_datasets1_idx` (`dataset_id` ASC),
  CONSTRAINT `fk_file_data_datasets1`
    FOREIGN KEY (`dataset_id`)
    REFERENCES `datasets` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- API(DataService) 관리
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `api_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `platform_id` BIGINT NOT NULL,
  `catalog_id` BIGINT NULL,
  `contact_point_id` BIGINT NULL,
  `creator_id` BIGINT NULL,
  `publisher_id` BIGINT NULL,
  `title` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `identifier` VARCHAR(255) NULL,
  `theme` VARCHAR(255) NULL,
  `license` VARCHAR(255) NULL,
  `access_rights` VARCHAR(10) NULL,
  `language` VARCHAR(2) NULL,
  `landing_page` VARCHAR(255) NULL,
  `rights` VARCHAR(255) NULL,
  `version` VARCHAR(50) NULL,
  `version_description` VARCHAR(255) NULL,
  `issued` VARCHAR(50) NULL,
  `modified` VARCHAR(50) NULL,
  `endpoint_url` VARCHAR(255) NULL,
  `endpoint_desc` TEXT NULL,
  `price_info` TEXT NULL,
  `quality_info` TEXT NULL,
  `serves_dataset` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_api_data_platforms1_idx` (`platform_id` ASC),
  INDEX `fk_api_data_catalogs1_idx` (`catalog_id` ASC),
  INDEX `fk_api_data_managers1_idx` (`contact_point_id` ASC),
  INDEX `fk_api_data_centers1_idx` (`creator_id` ASC),
  INDEX `fk_api_data_centers2_idx` (`publisher_id` ASC),
  CONSTRAINT `fk_api_data_platforms1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platforms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_api_data_catalogs1`
    FOREIGN KEY (`catalog_id`)
    REFERENCES `catalogs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_api_data_managers1`
    FOREIGN KEY (`contact_point_id`)
    REFERENCES `managers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_api_data_centers1`
    FOREIGN KEY (`creator_id`)
    REFERENCES `centers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_api_data_centers2`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `centers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Keyword
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `keywords` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `dataset_id` BIGINT NULL,
  `api_datum_id` BIGINT NULL,
  `data_type` VARCHAR(20) NULL,
  `keyword` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_keywords_datasets1_idx` (`dataset_id` ASC),
  INDEX `fk_keywords_api_data1_idx` (`api_datum_id` ASC),
  CONSTRAINT `fk_keywords_datasets1`
    FOREIGN KEY (`dataset_id`)
    REFERENCES `datasets` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_keywords_api_data1`
    FOREIGN KEY (`api_datum_id`)
    REFERENCES `api_data` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

