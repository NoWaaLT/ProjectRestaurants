CREATE TABLE menus (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(255) NOT NULL,
    fk_restaurant_id BIGINT NOT NULL,
    CONSTRAINT fk_menu_restaurant FOREIGN KEY (fk_restaurant_id) REFERENCES restaurants(id)
);
