CREATE SCHEMA IF NOT EXISTS Store;

CREATE TABLE IF NOT EXISTS Store.Product(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL,
    price decimal(10,2) NOT NULL
);