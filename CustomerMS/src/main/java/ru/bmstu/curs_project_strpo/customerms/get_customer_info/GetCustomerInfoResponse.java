package ru.bmstu.curs_project_strpo.customerms.get_customer_info;

import ru.bmstu.curs_project_strpo.customerms.Customer;

public class GetCustomerInfoResponse
{
    private String operation;
    private String result;
    private Customer data;

    public GetCustomerInfoResponse()
    {
    }


    public GetCustomerInfoResponse(String operation, String result, Customer data)
    {
        this.operation = operation;
        this.result = result;
        this.data = data;
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

    public Customer getData()
    {
        return data;
    }

    public void setData(Customer data)
    {
        this.data = data;
    }
}
