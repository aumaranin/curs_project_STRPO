package ru.bmstu.curs_project_strpo.purchasems.purchasems.basket;

public class ClearBasketResponse
{
    String operation;
    String result;

    public ClearBasketResponse()
    {
        this.operation = "clearbasket";
    }

    public ClearBasketResponse(String result)
    {
        this.operation = "clearbasket";
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
