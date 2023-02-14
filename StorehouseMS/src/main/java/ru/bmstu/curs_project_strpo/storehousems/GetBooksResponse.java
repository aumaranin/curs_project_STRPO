package ru.bmstu.curs_project_strpo.storehousems;

import java.util.List;

public class GetBooksResponse
{
    private String operation;
    private String result;
    private List<Book> books;

    public GetBooksResponse()
    {
        this.operation = "getbook";
    }

    public GetBooksResponse(String operation, String result)
    {
        this.operation = operation;
        this.result = result;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
