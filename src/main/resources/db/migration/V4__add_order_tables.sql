CREATE TABLE orders (
	id BIGINT auto_increment NOT NULL,
	customer_id BIGINT NOT NULL,
	status varchar(20) NOT NULL,
	created_at DATETIME DEFAULT current_timestamp NOT NULL,
	total_price decimal(10, 2) NOT NULL,
	CONSTRAINT orders_pk PRIMARY KEY (id),
	CONSTRAINT orders_users_FK FOREIGN KEY (customer_id) REFERENCES users(id)
);



CREATE TABLE order_items (
	id BIGINT auto_increment NOT NULL,
	order_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	unit_price DECIMAL(10, 2) NOT NULL,
	quantity INT NOT NULL,
	total_price DECIMAL(10,2) NOT NULL,
	CONSTRAINT order_items_pk PRIMARY KEY (id),
	CONSTRAINT order_items_cart_items_FK FOREIGN KEY (order_id) REFERENCES cart_items(id),
	CONSTRAINT order_items_products_FK FOREIGN KEY (product_id) REFERENCES products(id)
);