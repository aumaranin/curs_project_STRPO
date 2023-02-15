package ru.bmstu.curs_project_strpo.historyms.test;

public class TestResponse
{
    String operation;
    String result;

    public TestResponse()
    {
        this.operation = "test";
    }

    public TestResponse(String result)
    {
        this.operation = "test";
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
}
