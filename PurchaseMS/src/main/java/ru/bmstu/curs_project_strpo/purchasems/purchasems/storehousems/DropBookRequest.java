package ru.bmstu.curs_project_strpo.purchasems.purchasems.storehousems;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DropBookRequest
{
    String operation;
    int id; //book_id
    int count;

    public DropBookRequest()
    {
        this.operation = "dropbook";
    }

    public DropBookRequest(int id, int count)
    {
        this.operation = "dropbook";
        this.id = id;
        this.count = count;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
