--liquibase formatted sql
--changeset wfeliciano20:003 splitStatements:true endDelimiter:;

CREATE TABLE categories (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    parent_category_id INT,
                    FOREIGN KEY (parent_category_id) REFERENCES categories (id)
);

-- rollback drop table category;

ALTER TABLE items
    DROP  COLUMN  category,
    ADD COLUMN  category_id INT,
    ADD CONSTRAINT fk_category
    FOREIGN KEY (category_id) REFERENCES category(id);

-- rollback alter table items drop column category_id;


