package ru.bmstu.curs_project_strpo.customerms.checkCurrency;

public class CheckCurrencyResponse
{
    private String operation;
    private String result;

    public CheckCurrencyResponse() {
    }

    public CheckCurrencyResponse(String operation, String result) {
        this.operation = operation;
        this.result = result;
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
