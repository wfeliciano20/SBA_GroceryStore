--liquibase formatted sql
--changeset wfeliciano20:004 splitStatements:true endDelimiter:;

INSERT INTO categories (name, parent_category_id) VALUES
                                                    ('Electronics', NULL), -- Main category
                                                    ('Laptops', 1),       -- Sub-category under Electronics
                                                    ('Clothing', NULL),    -- Main category
                                                    ('Shoes', 3);         -- Sub-category under Clothing


-- rollback delete from categories where name in ('Electronics', 'Laptops', 'Clothing', 'Shoes');




DELETE FROM items;
INSERT INTO items (name, description, price, category_id, picture_url, weight, stock_amount) VALUES
    ('Smartphone', 'Latest model', 699.99, 1, 'https://example.com/smartphone.jpg', 0.3, 50),
    ('Laptop X1', 'Powerful laptop', 1499.99, 2, 'https://example.com/laptopx1.jpg', 2.5, 20),
    ('T-shirt', 'Cotton T-shirt', 19.99, 3, 'https://example.com/tshirt.jpg', 0.2, 100);

-- rollback delete from items where name in ('Smartphone', 'Laptop X1', 'T-shirt');

