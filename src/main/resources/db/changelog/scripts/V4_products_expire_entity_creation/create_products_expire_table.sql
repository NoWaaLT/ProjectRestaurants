CREATE TABLE products_expire (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     expire DATE NOT NULL,
     batch_quantity INTEGER NOT NULL,
     fk_product_id BIGINT NOT NULL,
     CONSTRAINT fk_expire_products FOREIGN KEY (fk_product_id) REFERENCES products(id)
)