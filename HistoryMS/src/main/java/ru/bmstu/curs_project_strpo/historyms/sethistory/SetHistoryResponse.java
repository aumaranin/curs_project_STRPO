package ru.bmstu.curs_project_strpo.historyms.sethistory;

public class SetHistoryResponse
{
    private String operation;
    private String result;

    public SetHistoryResponse()
    {
        this.operation = "sethistory";
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
