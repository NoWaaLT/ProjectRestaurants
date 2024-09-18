ALTER TABLE orders DROP CONSTRAINT fk_orders_user;

ALTER TABLE orders ADD CONSTRAINT fk_orders_user FOREIGN KEY (fk_username) REFERENCES users(username);

--ALTER TABLE orders DROP FOREIGN KEY fk_orders_user;
--
--ALTER TABLE orders
--ADD CONSTRAINT fk_orders_user
--FOREIGN KEY (fk_username) REFERENCES users(username);
