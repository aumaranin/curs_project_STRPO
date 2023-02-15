package ru.bmstu.curs_project_strpo.apigateway.storehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GetBooksRequest
{
    private String operation;
    private int person_id;

    public GetBooksRequest()
    {
    }

    public GetBooksRequest(String operation)
    {
        this.operation = operation;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
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

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }
}
