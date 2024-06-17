CREATE TABLE `orders` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_name VARCHAR(255) NOT NULL,
    fk_restaurant_id BIGINT NOT NULL,
    fk_user_id BIGINT NOT NULL,
    CONSTRAINT fk_orders_restaurant FOREIGN KEY (fk_restaurant_id) REFERENCES restaurants(id),
    CONSTRAINT fk_orders_user FOREIGN KEY (fk_user_id) REFERENCES users(id)
);
