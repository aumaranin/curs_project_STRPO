package ru.bmstu.curs_project_strpo.storehousems;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CheckBookQuantityRequest
{
    String operation;
    int book_id;
    int count;

    public CheckBookQuantityRequest()
    {
    }

    public CheckBookQuantityRequest(int book_id, int count)
    {
        this.operation = "checkbookquantity";
        this.book_id = book_id;
        this.count = count;
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

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public int getBook_id()
    {
        return book_id;
    }

    public void setBook_id(int book_id)
    {
        this.book_id = book_id;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}