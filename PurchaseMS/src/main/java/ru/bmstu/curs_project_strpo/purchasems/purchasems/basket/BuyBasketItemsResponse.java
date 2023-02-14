package ru.bmstu.curs_project_strpo.purchasems.purchasems.basket;

public class BuyBasketItemsResponse
{
    String operation;
    String result;

    public BuyBasketItemsResponse()
    {
        this.operation = "buybasketitems";
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
