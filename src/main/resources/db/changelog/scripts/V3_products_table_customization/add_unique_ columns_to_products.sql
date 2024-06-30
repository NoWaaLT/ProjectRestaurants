ALTER TABLE products
ADD CONSTRAINT products_group_unique_name_id UNIQUE(product_name, fk_restaurant_id);