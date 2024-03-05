CREATE TABLE IF NOT EXISTS counterparts
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255) NULL,
    address VARCHAR(255) NULL,
    CONSTRAINT pk_counterparts PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product_images
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255) NOT NULL,
    type    VARCHAR(255) NOT NULL,
    content MEDIUMBLOB NOT NULL, -- Для хранения изображений товаров (MEDIUMBLOB - 16 Mb)
    CONSTRAINT pk_product_images PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    barcode  VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    stock    INT          NOT NULL,
    image_id BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shipments
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    shipment_type   VARCHAR(255) NULL,
    product_id      BIGINT NULL,
    counterparty_id BIGINT NULL,
    shipment_start  datetime NULL,
    shipment_end    datetime NULL,
    count           INT NULL,
    CONSTRAINT pk_shipments PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT uc_products_barcode UNIQUE IF NOT EXISTS (barcode);

ALTER TABLE products
    ADD CONSTRAINT uc_products_image UNIQUE IF NOT EXISTS (image_id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_IMAGE FOREIGN KEY IF NOT EXISTS (image_id) REFERENCES product_images (id);

ALTER TABLE shipments
    ADD CONSTRAINT  FK_SHIPMENTS_ON_COUNTERPARTY FOREIGN KEY IF NOT EXISTS (counterparty_id) REFERENCES counterparts (id);

ALTER TABLE shipments
    ADD CONSTRAINT FK_SHIPMENTS_ON_PRODUCT FOREIGN KEY IF NOT EXISTS (product_id) REFERENCES products (id);
