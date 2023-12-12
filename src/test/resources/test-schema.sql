
CREATE TABLE categories (
                            id BIGINT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            parent_category_id BIGINT REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS items (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR NOT NULL,
                       description VARCHAR NOT NULL,
                       price NUMERIC NOT NULL,
                       category_id BIGINT NOT NULL REFERENCES categories (id),
                       picture_url VARCHAR,
                       weight FLOAT,
                       stock_amount INTEGER
);


