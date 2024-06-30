ALTER TABLE orders
add CONSTRAINT fk_orders_user FOREIGN KEY (fk_username) REFERENCES users(username)