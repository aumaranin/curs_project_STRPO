package ru.bmstu.curs_project_strpo.purchasems.purchasems.basket;

import ru.bmstu.curs_project_strpo.purchasems.purchasems.Book;

import java.util.List;

public class GetBasketResponse
{
    String operation;
    String result;
    List<Book> books;

    public GetBasketResponse()
    {
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
