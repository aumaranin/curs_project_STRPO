package ru.bmstu.curs_project_strpo.customerms.changecurrency;

public class ChangeCurrencyRequest
{
    private String operation;
    private int person_id;
    private int count;

    public ChangeCurrencyRequest()
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

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
