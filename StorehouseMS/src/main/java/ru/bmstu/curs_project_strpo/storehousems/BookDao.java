package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Класс, содержащий функции для работы с таблицей "Book"
@Component
public class BookDao
{
    //private static final String URL = "jdbc:postgresql://storehousebd:5432/storehousebd";
    private static final String URL = "jdbc:postgresql://localhost:5440/storehousebd";
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
    public List<Book> getAllBooks()
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
                book.setPrice(resultSet.getInt("price"));
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

    public List<Book> getRecommendedBooks()
    {
        List<Book> books = new ArrayList<>();


        return books;
    }

    public boolean checkBookQuantity(int book_id, int count)
    {
        boolean result = false;
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM books WHERE id=?;");
            prst.setInt(1,book_id);
            ResultSet resultSet = prst.executeQuery();
            resultSet.next();
            int storehouseCount = resultSet.getInt("count");
            if (count > storehouseCount)
                result = false;
            else
                result = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    public String dropBook(int book_id, int count)
    {
        String result = null;
        try
        {
            //Получаем текущее количество книг
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM books WHERE id=?;");
            prst.setInt(1,book_id);
            ResultSet resultSet = prst.executeQuery();
            resultSet.next();

            //Изменяем количество
            int storehouseCount = resultSet.getInt("count");
            int changed_count = storehouseCount - count;

            //Вносим изменения в базу
            prst = connection.
                    prepareStatement("UPDATE books SET count=? WHERE id=?");
            prst.setInt(1, changed_count);
            prst.setInt(2, book_id);
            prst.execute();
            result = "confirm";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            result = "error";
        }
        finally
        {
            return result;
        }
    }
}
