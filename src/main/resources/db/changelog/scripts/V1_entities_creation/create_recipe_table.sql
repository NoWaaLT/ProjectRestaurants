CREATE TABLE recipes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipe_name VARCHAR(255) NOT NULL,
    recipe_price FLOAT NOT NULL,
    fk_menu_id BIGINT NOT NULL,
    CONSTRAINT fk_recipes_menu FOREIGN KEY (fk_menu_id) REFERENCES menus(id)
);