package ru.bmstu.curs_project_strpo.purchasems.purchasems.basket;

import org.springframework.stereotype.Component;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.Book;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.PurchaseMsApplication;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.basket.BasketAddItemRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.basket.BasketAddItemResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasketDao
{
    private static final String URL = PurchaseMsApplication.properties.getPurchaseBdURL();
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "purchasebd";

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

    public BasketAddItemResponse basketAddItem(BasketAddItemRequest basketAddItemRequest)
    {
        BasketAddItemResponse response = new BasketAddItemResponse();
        try
        {
            PreparedStatement prst  = connection.prepareStatement("INSERT INTO basket (person_id, book_id, title, author, genre, year, price, count) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            prst.setInt(1, basketAddItemRequest.getPerson_id());
            prst.setInt(2, basketAddItemRequest.getId());
            prst.setString(3, basketAddItemRequest.getTitle());
            prst.setString(4, basketAddItemRequest.getAuthor());
            prst.setString(5, basketAddItemRequest.getGenre());
            prst.setInt(6, basketAddItemRequest.getYear());
            prst.setInt(7, basketAddItemRequest.getPrice());
            prst.setInt(8, basketAddItemRequest.getCount());
            prst.execute();
                response.setResult("confirm");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            response.setResult("deny");
        }
        finally
        {
            return response;
        }
    }

    //Метод для поиска всех позиций в корзине нужного пользователя
    public List<Book> getBasket(int person_id)
    {
        List<Book> books = new ArrayList<>();
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM basket WHERE person_id=?;");
            prst.setInt(1, person_id);
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next())
            {
                Book book = new Book();
                book.setId((resultSet.getInt("book_id")));
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

    //Метод для очистки корзины пользователя
    public String clearBasket(int person_id)
    {
        String response = "deny";
        try
        {
            PreparedStatement prst  = connection.prepareStatement(
                    "DELETE FROM basket WHERE person_id=?");
            prst.setInt(1, person_id);
            prst.execute();
            response ="confirm";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            response = "deny";
        }
        finally
        {
            return response;
        }
    }

    //Метод для покупки книг из корзины пользователя
    public String buyBasketItems(int person_id)
    {
        String response = "deny";
        /*
        try
        {
            PreparedStatement prst  = connection.prepareStatement(
                    "DELETE FROM basket WHERE person_id=?");
            prst.setInt(1, person_id);
            prst.execute();
            response ="confirm";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            response = "deny";
        }
        finally
        {
            return response;
        }

         */
        return response;
    }

}
