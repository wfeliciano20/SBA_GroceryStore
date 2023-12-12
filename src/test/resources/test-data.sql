
INSERT INTO categories (id, name, parent_category_id) VALUES
                                                          (1, 'Electronics', NULL), -- Main category
                                                          (2, 'Laptops', 1),       -- Sub-category under Electronics
                                                          (3, 'Clothing', NULL),    -- Main category
                                                          (4, 'Shoes', 3);         -- Sub-category under Clothing

INSERT INTO items (id,name, description, price, category_id, picture_url, weight, stock_amount) VALUES
                                                                                                 (1,'Smartphone', 'Latest model', 699.99, 1, 'https://example.com/smartphone.jpg', 0.3, 50),
                                                                                                 (2,'Laptop X1', 'Powerful laptop', 1499.99, 2, 'https://example.com/laptopx1.jpg', 2.5, 20),
                                                                                                 (3,'T-shirt', 'Cotton T-shirt', 19.99, 3, 'https://example.com/tshirt.jpg', 0.2, 100);