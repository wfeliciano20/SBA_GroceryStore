CREATE TABLE IF NOT EXISTS items (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR NOT NULL,
                       description VARCHAR NOT NULL,
                       price NUMERIC NOT NULL,
                       category_id INT NOT NULL,
                       picture_url VARCHAR,
                       weight INTEGER,
                       stock_amount INTEGER
                       FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE categories (
                            id BIGINT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            parent_category_id INT,
                            FOREIGN KEY (parent_category_id) REFERENCES categories(id)
);

