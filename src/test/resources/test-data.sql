-- Assuming the id is an auto-incremented primary key

INSERT INTO items (name, description, price, category, picture_url, weight, stock_amount)
VALUES
    ('Item 1', 'Description for Item 1', 19.99, 'Category A', 'https://example.com/item1.jpg', 500, 100),
    ('Item 2', 'Description for Item 2', 29.99, 'Category B', 'https://example.com/item2.jpg', 750, 50),
    ('Item 3', 'Description for Item 3', 14.50, 'Category A', 'https://example.com/item3.jpg', 300, 75);

INSERT INTO categories (name, parent_category_id) VALUES
                                                      ('Electronics', NULL), -- Main category
                                                      ('Laptops', 1),       -- Sub-category under Electronics
                                                      ('Clothing', NULL),    -- Main category
                                                      ('Shoes', 3);         -- Sub-category under Clothing

DELETE FROM items;
INSERT INTO items (name, description, price, category_id, picture_url, weight, stock_amount) VALUES
                                                                                                 ('Smartphone', 'Latest model', 699.99, 1, 'https://example.com/smartphone.jpg', 0.3, 50),
                                                                                                 ('Laptop X1', 'Powerful laptop', 1499.99, 2, 'https://example.com/laptopx1.jpg', 2.5, 20),
                                                                                                 ('T-shirt', 'Cotton T-shirt', 19.99, 3, 'https://example.com/tshirt.jpg', 0.2, 100);