CREATE TABLE recipe_quantity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_product_id BIGINT NOT NULL,
    fk_recipe_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT fk_recipe_quantity_product FOREIGN KEY (fk_product_id) REFERENCES products(id),
    CONSTRAINT fk_recipe_quantity_recipe FOREIGN KEY (fk_recipe_id) REFERENCES recipes(id)
);