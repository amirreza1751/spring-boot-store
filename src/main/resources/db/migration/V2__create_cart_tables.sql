-- store_api.carts definition
CREATE TABLE store_api.carts (
	id varchar(36) DEFAULT (UUID()) NOT NULL,
	date_created DATE DEFAULT (curdate()) NOT NULL,
	CONSTRAINT carts_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


-- store_api.cart_items definition

CREATE TABLE store_api.cart_items (
	id BIGINT auto_increment NOT NULL,
	cart_id varchar(36) NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT DEFAULT 1 NOT NULL,
	CONSTRAINT cart_items_pk PRIMARY KEY (id),
	CONSTRAINT cart_items_cart_product_unique UNIQUE KEY (cart_id,product_id),
	CONSTRAINT cart_items_cart_items_FK FOREIGN KEY (cart_id) REFERENCES store_api.carts(id) ON DELETE CASCADE,
	CONSTRAINT cart_items_products_FK FOREIGN KEY (product_id) REFERENCES store_api.products(id) ON DELETE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
