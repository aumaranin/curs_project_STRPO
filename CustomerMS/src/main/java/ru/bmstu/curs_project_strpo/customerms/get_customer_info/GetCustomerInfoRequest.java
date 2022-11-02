package ru.bmstu.curs_project_strpo.customerms.get_customer_info;

public class GetCustomerInfoRequest
{
    private String operation;
    private String type;
    private String identifier;

    public GetCustomerInfoRequest()
    {
    }

    public GetCustomerInfoRequest(String operation, String type, String identifier)
    {
        this.operation = operation;
        this.type = type;
        this.identifier = identifier;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }
}
