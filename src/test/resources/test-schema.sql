CREATE TABLE IF NOT EXISTS items (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR NOT NULL,
                       description VARCHAR NOT NULL,
                       price NUMERIC NOT NULL,
                       category VARCHAR NOT NULL,
                       picture_url VARCHAR,
                       weight INTEGER,
                       stock_amount INTEGER
);