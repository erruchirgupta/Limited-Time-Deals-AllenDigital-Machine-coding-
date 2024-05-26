CREATE DATABASE `demo`;

-- demo.customer definition

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `contact` bigint NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_id` (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- demo.seller definition

CREATE TABLE `seller` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `aadhar_number` varchar(255) NOT NULL,
  `pan_number` varchar(255) NOT NULL,
  `contact` bigint NOT NULL,
  `gstin` varchar(255) NOT NULL,
  `status` bit(1) DEFAULT b'1',
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aadhar_number_id` (`aadhar_number`),
  UNIQUE KEY `pan_number_id` (`pan_number`),
  UNIQUE KEY `contact_id` (`contact`),
  UNIQUE KEY `gstin_id` (`gstin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- demo.orders definition

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `store_id` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  `order_processing_time` datetime NOT NULL,
  `payment_type` varchar(255) NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `txn_id` varchar(255) DEFAULT NULL,
  `amt_to_be_collected` double NOT NULL,
  `amt_collected` double NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_processing_time_idx` (`order_processing_time`),
  KEY `status_idx` (`status`),
  KEY `orders_customer_id_fk` (`customer_id`),
  CONSTRAINT `orders_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- demo.products definition

CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `version` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `uom` varchar(255) NOT NULL,
  `unit_value` varchar(255) NOT NULL,
  `max_selling_price` double NOT NULL,
  `remaining_stock` int NOT NULL,
  `total_stock` int NOT NULL,
  `seller_id` int NOT NULL,
  `status` bit(1) DEFAULT b'1',
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_id` (`name`),
  KEY `seller_id_fk` (`seller_id`),
  KEY `name_idx` (`name`),
  CONSTRAINT `seller_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- demo.deals_config definition

CREATE TABLE `deals_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `version` bigint DEFAULT NULL,
  `seller_id` int NOT NULL,
  `deal_start_time` timestamp NOT NULL,
  `deal_end_time` timestamp NOT NULL,
  `deal_banner` varchar(255) DEFAULT NULL,
  `product_id_on_sale` int NOT NULL,
  `discount` double NOT NULL,
  `max_quantity_per_order` int NOT NULL,
  `total_sale_quantity` int NOT NULL,
  `sale_quantity_remaining` int NOT NULL,
  `status` bit(1) DEFAULT b'1',
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deals_config_seller_fk` (`seller_id`),
  KEY `product_id_on_sale_fk` (`product_id_on_sale`),
  CONSTRAINT `deals_config_seller_fk` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`),
  CONSTRAINT `product_id_on_sale_fk` FOREIGN KEY (`product_id_on_sale`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- demo.order_items definition

CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `price_per_unit` double NOT NULL,
  `uom` varchar(255) NOT NULL,
  `unit_value` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(127) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_items_product_id_fk` (`product_id`),
  KEY `order_items_order_id_fk` (`order_id`),
  CONSTRAINT `order_items_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_items_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;