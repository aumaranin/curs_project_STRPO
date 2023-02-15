CREATE DATABASE historybd;
\c historybd

CREATE TABLE history ( id SERIAL PRIMARY KEY, person_id INT NOT NULL, date VARCHAR(100) NOT NULL, book_id INT NOT NULL, title VARCHAR(100) NOT NULL, author VARCHAR(50) NOT NULL, genre VARCHAR(50) NOT NULL, year INT NOT NULL, price INT NOT NULL, count INT NOT NULL);

INSERT INTO history (person_id, date, book_id, title, author, genre, year, price, count) VALUES (1, '2022.12.31', 1, 'Хоббит, или Туда и обратно', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2019, 1000, 1);
INSERT INTO history (person_id, date, book_id, title, author, genre, year, price, count) VALUES (1, '2022.12.31', 5, 'Гарри Поттер и философский камень', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1400, 1);
INSERT INTO history (person_id, date, book_id, title, author, genre, year, price, count) VALUES (1, '2022.12.31', 6, 'Гарри Поттер и Тайная комната', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1500, 1);

