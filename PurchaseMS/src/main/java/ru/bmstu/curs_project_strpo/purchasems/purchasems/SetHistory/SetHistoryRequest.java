package ru.bmstu.curs_project_strpo.purchasems.purchasems.SetHistory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.Book;

import java.util.List;

public class SetHistoryRequest
{
    private String operation;
    private int person_id;
    private String date;
    List<Book> books;

    public SetHistoryRequest()
    {
    }

    public SetHistoryRequest(int person_id, String date, List<Book> books)
    {
        this.operation = "sethistory";
        this.person_id = person_id;
        this.date = date;
        this.books = books;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public String toString()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonResult = null;
        try
        {
            jsonResult = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        return jsonResult;
    }
}
