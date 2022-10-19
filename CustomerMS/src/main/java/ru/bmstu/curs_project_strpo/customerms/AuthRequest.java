package ru.bmstu.curs_project_strpo.customerms;

public class AuthRequest
{
    private String operation;
    private String login;
    private String password;


    public AuthRequest()
    {
    }

    public AuthRequest(String operation, String login, String password)
    {
        this.operation = operation;
        this.login = login;
        this.password = password;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
