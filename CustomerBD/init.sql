CREATE DATABASE customerbd;
\c customerbd

CREATE TABLE customers ( id SERIAL PRIMARY KEY, login VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, first_name VARCHAR(100) NOT NULL, last_name VARCHAR(100) NOT NULL, currency INT NOT NULL);

INSERT INTO customers (login, password, first_name, last_name, currency) VALUES ('login1', 'password1', 'Александр', 'Маранин', 100000);
INSERT INTO customers (login, password, first_name, last_name, currency) VALUES ('login2', 'password2', 'Дмитрий', 'Сапронов', 80000);
INSERT INTO customers (login, password, first_name, last_name, currency) VALUES ('login3', 'password3', 'Алексей', 'Терентьев', 120000);
