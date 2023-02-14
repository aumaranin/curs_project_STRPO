package ru.bmstu.curs_project_strpo.purchasems.purchasems.history;

import java.util.List;

public class GetHistoryResponse
{
    String operation;
    String result;
    List<BuyNote> buyNote;

    public GetHistoryResponse()
    {
    }

    public GetHistoryResponse(String operation, String result)
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

    public List<BuyNote> getHistory()
    {
        return buyNote;
    }

    public void setHistory(List<BuyNote> buyNote)
    {
        this.buyNote = buyNote;
    }
}
