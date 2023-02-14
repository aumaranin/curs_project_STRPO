CREATE DATABASE storehousebd;
\c storehousebd


CREATE TABLE books ( id SERIAL PRIMARY KEY, title VARCHAR(100) NOT NULL, author VARCHAR(50) NOT NULL, genre VARCHAR(50) NOT NULL, year INT NOT NULL, price INT NOT NULL, count INT NOT NULL);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Хоббит, или Туда и обратно', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2019, 1000, 100);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Властелин колец. Две твердыни', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2020, 1100, 100);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Властелин колец. Хранители кольца', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2021, 1200, 100);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Властелин колец. Возвращение короля', 'Толкин Джон Рональд Руэл', 'Фэнтези', 2021, 1300, 100);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и философский камень', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1400, 14);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и Тайная комната', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1500, 15);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и узник Азкабана', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1600, 15);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и Кубок Огня', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2002, 1700, 15);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и Орден Феникса', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2004, 1800, 17);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и Принц-полукровка', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2005, 1900, 17);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Гарри Поттер и Дары Смерти', 'Роулинг Джоан Кэтлин', 'Фэнтези', 2007, 1000, 20);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Случайная вакансия', 'Роулинг Джоан Кэтлин', 'Роман', 2013, 1100, 57);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Кровь и железо', 'Аберкромби Джо', 'Фэнтези', 2022, 1200, 20);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Прежде чем их повесят', 'Аберкромби Джо', 'Фэнтези', 2022, 1300, 50);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Последний довод королей', 'Аберкромби Джо', 'Фэнтези', 2021, 1400, 47);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Пробуждение Левиафана', 'Кори Джеймс', 'Фантастика', 2021, 1500, 37);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Война Калибана', 'Кори Джеймс', 'Фантастика', 2021, 1600, 27);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Врата Абаддона', 'Кори Джеймс', 'Фантастика', 2022, 1700, 50);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Инквизитор. Ордо Ксенос', 'Абнетт Дэн', 'Фантастика', 2007, 1800, 10);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Инквизитор. Ордо Маллеус', 'Абнетт Дэн', 'Фантастика', 2010, 1900, 14);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Инквизитор. Ордо Еретикус', 'Абнетт Дэн', 'Фантастика', 2012, 2000, 17);

INSERT INTO books (title, author, genre, year, price, count) VALUES ('Джейн Эйр', 'Бронте Шарлотта', 'Роман', 2005, 1000, 27);
 
