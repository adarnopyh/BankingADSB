DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE IF NOT EXISTS role (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO role(name)
VALUES ('user'),
       ('admin');