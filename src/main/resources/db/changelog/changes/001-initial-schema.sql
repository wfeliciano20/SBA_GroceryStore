--liquibase formatted sql
--changeset wfeliciano20:001 splitStatements:true endDelimiter:;

CREATE TABLE items (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR NOT NULL,
                                description VARCHAR NOT NULL,
                                price NUMERIC NOT NULL,
                                category VARCHAR NOT NULL,
                                picture_url VARCHAR,
                                weight INTEGER,
                                stock_amount INTEGER
);

-- rollback drop table items;
