package ru.bmstu.curs_project_strpo.historyms;

import org.springframework.stereotype.Component;
import ru.bmstu.curs_project_strpo.historyms.gethistory.GetHistoryResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryNoteDao
{
    private static final String URL = HistoryMsApplication.properties.getHistoryBdURL();
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "historybd";

    //Создание и настройка соединения с базой данных
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ОШИБКА! Драйвер Postgres не загружен!");
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ОШИБКА! Поключение к базе данных невозможно!");
        }
    }

    public GetHistoryResponse getHistory(int person_id)
    {
        GetHistoryResponse getHistoryResponse = new GetHistoryResponse("gethistory","confirm");
        List<HistoryNote> list = new ArrayList<>();
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM history WHERE person_id=? ORDER BY date;");
            prst.setInt(1,person_id);
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next())
            {
                HistoryNote historyNote = new HistoryNote();
                historyNote.setId((resultSet.getInt("id")));
                historyNote.setPerson_id((resultSet.getInt("person_id")));
                historyNote.setDate(resultSet.getString("date"));
                historyNote.setId((resultSet.getInt("book_id")));
                historyNote.setTitle(resultSet.getString("title"));
                historyNote.setAuthor(resultSet.getString("author"));
                historyNote.setGenre(resultSet.getString("genre"));
                historyNote.setYear(resultSet.getInt("year"));
                historyNote.setPrice(resultSet.getInt("price"));
                historyNote.setCount(resultSet.getInt("count"));
                list.add(historyNote);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            getHistoryResponse.setResult("error");
            System.out.println("ОШИБКА! Транзакция на получения заметок не выполнена!");
        }
        finally
        {
            if (list.isEmpty())
            {
                getHistoryResponse.setResult("deny");
                return getHistoryResponse;
            }
            getHistoryResponse.setHistory(list);
            return getHistoryResponse;
        }
    }

    public String setHistoryNote(int person_id, String date, Book book)
    {
        String result = null;
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("INSERT INTO history (person_id, date, " +
                    "book_id, title, author, genre, year, price, count)  " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            prst.setInt(1, person_id);
            prst.setString(2, date);
            prst.setInt(3, book.getId());
            prst.setString(4, book.getTitle());
            prst.setString(5, book.getAuthor());
            prst.setString(6, book.getGenre());
            prst.setInt(7, book.getYear());
            prst.setInt(8, book.getPrice());
            prst.setInt(9, book.getCount());
            prst.execute();
            result = "confirm";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("ОШИБКА! Транзакция на довабление заметки завершилась неудачей!");
            result = "error";
        }
        finally
        {
            return result;
        }
    }
}
