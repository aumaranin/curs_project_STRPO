package ru.bmstu.curs_project_strpo.purchasems.purchasems.history;

public class GetHistoryRequest
{
    private String operation;
    private int person_id;

    public GetHistoryRequest()
    {
    }

    public GetHistoryRequest(String operation, int person_id)
    {
        this.operation = operation;
        this.person_id = person_id;
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
}