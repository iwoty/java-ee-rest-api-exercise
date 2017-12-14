CREATE TABLE employees (
    id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    shop_id SERIAL,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);