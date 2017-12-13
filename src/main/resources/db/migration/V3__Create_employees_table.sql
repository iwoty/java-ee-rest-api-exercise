CREATE TABLE employees (
    id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    shop_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);