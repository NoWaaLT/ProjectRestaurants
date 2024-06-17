CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price FLOAT NOT NULL,
    product_balance INTEGER NOT NULL,
    fk_restaurant_id BIGINT NOT NULL,
    CONSTRAINT fk_products_restaurant FOREIGN KEY (fk_restaurant_id) REFERENCES restaurants(id)
);