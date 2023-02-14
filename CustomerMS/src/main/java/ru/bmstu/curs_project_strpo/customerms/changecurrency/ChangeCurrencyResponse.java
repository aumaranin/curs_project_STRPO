package ru.bmstu.curs_project_strpo.customerms.changecurrency;

public class ChangeCurrencyResponse
{
    private String operation;
    private String result;

    public ChangeCurrencyResponse() {
        this.operation = "changecurrency";
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
