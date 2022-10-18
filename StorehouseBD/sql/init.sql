CREATE DATABASE storehousebd;
\c storehousebd


CREATE TABLE books ( id SERIAL PRIMARY KEY, title VARCHAR(100) NOT NULL, author VARCHAR(50) NOT NULL, genre VARCHAR(50) NOT NULL, year INT NOT NULL, count INT NOT NULL);

INSERT INTO books (title, author, genre, year, count) VALUES ('Хоббит, или Туда и обратно', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2019, 100);

INSERT INTO books (title, author, genre, year, count) VALUES ('Властелин колец. Две твердыни', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2020, 100);

INSERT INTO books (title, author, genre, year, count) VALUES ('Властелин колец. Хранители кольца', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2021, 100);

INSERT INTO books (title, author, genre, year, count) VALUES ('Властелин колец. Возвращение короля', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2021, 100);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и философский камень', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 14);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и Тайная комната', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 15);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и узник Азкабана', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 15);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и Кубок Огня', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 15);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и Орден Феникса', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2004, 17);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и Принц-полукровка', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2005, 17);

INSERT INTO books (title, author, genre, year, count) VALUES ('Гарри Поттер и Дары Смерти', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2007, 20);

INSERT INTO books (title, author, genre, year, count) VALUES ('Случайная вакансия', 'Роулинг Джоан Кэтлин', 'Роман', 2013, 57);

INSERT INTO books (title, author, genre, year, count) VALUES ('Кровь и железо', 'Аберкромби Джо', 'Фэнтези', 2022, 20);

INSERT INTO books (title, author, genre, year, count) VALUES ('Прежде чем их повесят', 'Аберкромби Джо', 'Фэнтези', 2022, 50);

INSERT INTO books (title, author, genre, year, count) VALUES ('Последний довод королей', 'Аберкромби Джо', 'Фэнтези', 2021, 47);

INSERT INTO books (title, author, genre, year, count) VALUES ('Пробуждение Левиафана', 'Кори Джеймс', 'Фантастика', 2021, 37);

INSERT INTO books (title, author, genre, year, count) VALUES ('Война Калибана', 'Кори Джеймс', 'Фантастика', 2021, 27);

INSERT INTO books (title, author, genre, year, count) VALUES ('Врата Абаддона', 'Кори Джеймс', 'Фантастика', 2022, 50);

INSERT INTO books (title, author, genre, year, count) VALUES ('Инквизитор. Ордо Ксенос', 'Абнетт Дэн', 'Фантастика', 2007, 10);

INSERT INTO books (title, author, genre, year, count) VALUES ('Инквизитор. Ордо Маллеус', 'Абнетт Дэн', 'Фантастика', 2010, 14);

INSERT INTO books (title, author, genre, year, count) VALUES ('Инквизитор. Ордо Еретикус', 'Абнетт Дэн', 'Фантастика', 2012, 17);

INSERT INTO books (title, author, genre, year, count) VALUES ('Джейн Эйр', 'Бронте Шарлотта', 'Роман', 2005, 27);
 
