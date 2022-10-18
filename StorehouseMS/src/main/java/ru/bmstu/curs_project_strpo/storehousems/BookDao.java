package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Класс, содержащий функции для работы с таблицей "Book"
@Component
public class BookDao
{
    private static final String URL = "jdbc:postgresql://storehousebd:5432/storehousebd";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "storehousebd";

    //Создание и настройка соединения с базой данных
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Метод для поиска всех книг в базе данных
    public List<Book> show_all()
    {
        List<Book> books = new ArrayList<>();
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM books;");
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next())
            {
                Book book = new Book();
                book.setId((resultSet.getInt("id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setYear(resultSet.getInt("year"));
                book.setCount(resultSet.getInt("count"));
                books.add(book);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }
}
