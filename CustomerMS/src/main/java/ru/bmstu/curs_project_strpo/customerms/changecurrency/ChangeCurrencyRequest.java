package ru.bmstu.curs_project_strpo.customerms.changecurrency;

public class ChangeCurrencyRequest
{
    private String operation;
    private int id;
    private int count;

    public ChangeCurrencyRequest() {
    }

    public ChangeCurrencyRequest(String operation, int id, int count) {
        this.operation = operation;
        this.id = id;
        this.count = count;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
