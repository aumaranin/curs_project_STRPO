package ru.bmstu.curs_project_strpo.historyms.gethistory;

import ru.bmstu.curs_project_strpo.historyms.HistoryNote;

import java.util.List;

public class GetHistoryResponse
{
    String operation;
    String result;
    List<HistoryNote> historyNote;

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

    public List<HistoryNote> getHistory()
    {
        return historyNote;
    }

    public void setHistory(List<HistoryNote> historyNote)
    {
        this.historyNote = historyNote;
    }
}
