--liquibase formatted sql
--changeset wfeliciano20:002 splitStatements:true endDelimiter:;

INSERT INTO items (name, description, price, category, picture_url, weight, stock_amount)
VALUES
    ('Item 1', 'Description for Item 1', 19.99, 'Category A', 'https://example.com/item1.jpg', 500, 100),
    ('Item 2', 'Description for Item 2', 29.99, 'Category B', 'https://example.com/item2.jpg', 750, 50),
    ('Item 3', 'Description for Item 3', 14.50, 'Category A', 'https://example.com/item3.jpg', 300, 75);

-- rollback delete from items where name in ('Item 1', 'Item 2', 'Item 3');