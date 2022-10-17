package com.example.calculator1;

public class Request
{
    private String operation;
    private int x;
    private int y;

    public Request(String operation, int x, int y)
    {
        this.operation = operation;
        this.x = x;
        this.y = y;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
