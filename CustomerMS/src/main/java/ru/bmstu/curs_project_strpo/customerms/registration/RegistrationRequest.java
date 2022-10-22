package ru.bmstu.curs_project_strpo.customerms.registration;

public class RegistrationRequest
{
    private String operation;
    private String login;
    private String password;
    private String first_name;
    private String last_name;

    public RegistrationRequest()
    {
    }

    public RegistrationRequest(String operation, String login, String password, String first_name, String last_name)
    {
        this.operation = operation;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
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

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }
}
