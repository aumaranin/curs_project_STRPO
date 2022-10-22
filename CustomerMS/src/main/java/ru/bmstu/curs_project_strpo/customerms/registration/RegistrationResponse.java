package ru.bmstu.curs_project_strpo.customerms.registration;

public class RegistrationResponse
{
    private String operation;
    private String result;

    public RegistrationResponse()
    {
    }

    public RegistrationResponse(String operation, String result)
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
}
