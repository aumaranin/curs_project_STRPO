package ru.bmstu.curs_project_strpo.storehousems;

public class GetRecommendedBooksRequest
{
    private String operation;
    private int person_id;


    public GetRecommendedBooksRequest()
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

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }
}
