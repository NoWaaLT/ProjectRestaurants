CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_name VARCHAR(255),
    order_owner VARCHAR(255),
    order_created TIMESTAMP NOT NULL
)