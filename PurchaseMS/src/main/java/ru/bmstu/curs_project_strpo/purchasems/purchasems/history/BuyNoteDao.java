package ru.bmstu.curs_project_strpo.purchasems.purchasems.history;

import org.springframework.stereotype.Component;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.PurchaseMsApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuyNoteDao
{
    private static final String URL = PurchaseMsApplication.properties.getPurchaseBdURL();
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "purchasebd";

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

    public GetHistoryResponse getHistory(int person_id)
    {
        GetHistoryResponse getHistoryResponse = new GetHistoryResponse("gethistory","confirm");
        List<BuyNote> list = new ArrayList<>();
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM history WHERE person_id=? ORDER BY date;");
            prst.setInt(1,person_id);
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next())
            {
                BuyNote buyNote = new BuyNote();
                buyNote.setId((resultSet.getInt("id")));
                buyNote.setPerson_id((resultSet.getInt("person_id")));
                buyNote.setDate(resultSet.getString("date"));
                buyNote.setId((resultSet.getInt("book_id")));
                buyNote.setTitle(resultSet.getString("title"));
                buyNote.setAuthor(resultSet.getString("author"));
                buyNote.setGenre(resultSet.getString("genre"));
                buyNote.setYear(resultSet.getInt("year"));
                buyNote.setPrice(resultSet.getInt("price"));
                buyNote.setCount(resultSet.getInt("count"));
                list.add(buyNote);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        if (list.isEmpty())
        {
            getHistoryResponse.setResult("deny");
            return getHistoryResponse;
        }
        getHistoryResponse.setHistory(list);
        return getHistoryResponse;
    }

    public String setHistoryNote(BuyNote buyNote)
    {
        String result = null;
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("INSERT INTO history (person_id, date, " +
                    "book_id, title, author, genre, year, price, count)  " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            prst.setInt(1, buyNote.getPerson_id());
            prst.setString(2, buyNote.getDate());
            prst.setInt(3, buyNote.getBook_id());
            prst.setString(4, buyNote.getTitle());
            prst.setString(5, buyNote.getAuthor());
            prst.setString(6, buyNote.getGenre());
            prst.setInt(7, buyNote.getYear());
            prst.setInt(8, buyNote.getPrice());
            prst.setInt(9, buyNote.getCount());
            //ResultSet resultSet = prst.executeQuery();
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
