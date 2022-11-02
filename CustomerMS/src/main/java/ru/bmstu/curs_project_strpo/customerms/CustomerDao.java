package ru.bmstu.curs_project_strpo.customerms;

import org.springframework.stereotype.Component;
import ru.bmstu.curs_project_strpo.customerms.auth.AuthResponse;
import ru.bmstu.curs_project_strpo.customerms.get_customer_info.GetCustomerInfoResponse;
import ru.bmstu.curs_project_strpo.customerms.registration.RegistrationResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDao
{
    private static String URL = "jdbc:postgresql://localhost:5441/customerbd";;
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "customerbd";


    //Создание и настройка соединения с базой данных
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Метод для поиска всех книг в базе данных
    public List<Customer> show_all()
    {
        List<Customer> customers = new ArrayList<>();
        try
        {
            PreparedStatement prst = connection.
                    prepareStatement("SELECT * FROM customers;");
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next())
            {
                Customer customer = new Customer();
                customer.setId((resultSet.getInt("id")));
                customer.setLogin((resultSet.getString("login")));
                customer.setPassword((resultSet.getString("password")));
                customer.setFirst_name((resultSet.getString("first_name")));
                customer.setLast_name((resultSet.getString("last_name")));
                customer.setCurrency(resultSet.getInt("currency"));
                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return customers;
    }

    public AuthResponse auth(String login, String password)
    {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setOperation("auth");
        try
        {
            PreparedStatement prst = connection.prepareStatement(
                    "SELECT * FROM customers WHERE login=? AND password=?;");
            prst.setString(1, login);
            prst.setString(2, password);
            ResultSet resultSet = prst.executeQuery();
            if (resultSet.next())
                authResponse.setResult("confirm");
            else
                authResponse.setResult("deny");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return authResponse;
    }

    public RegistrationResponse registration(
            String login,
            String password,
            String first_name,
            String last_name)
    {
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setOperation("registration");
        try
        {
            //Проверка, зарегистрирован ли пользователь с таким же логином
            PreparedStatement prst = connection.prepareStatement(
                    "SELECT * FROM customers WHERE login=?");
            prst.setString(1, login);
            ResultSet resultSet = prst.executeQuery();
            if (resultSet.next())
                registrationResponse.setResult("deny");
            else
            {
                prst = connection.prepareStatement(
                        "INSERT INTO customers (login, password, first_name, last_name, currency) " +
                                "VALUES (?, ?, ?, ?, 0);");
                prst.setString(1, login);
                prst.setString(2, password);
                prst.setString(3, first_name);
                prst.setString(4, last_name);
                prst.execute();
                registrationResponse.setResult("confirm");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            registrationResponse.setResult("deny");
        }
        finally
        {
            return registrationResponse;
        }
    }

    public GetCustomerInfoResponse getCustomerInfo(String type, String identifier)
    {
        GetCustomerInfoResponse getCustomerInfoResponse = new GetCustomerInfoResponse();
        getCustomerInfoResponse.setOperation("getcustomerinfo");

        try
        {
            PreparedStatement prst;
            if (type.equals("login"))
            {
                prst = connection.prepareStatement(
                        "SELECT * FROM customers WHERE login=?");
                prst.setString(1, identifier);
            }
            else
            {
                prst = connection.prepareStatement(
                        "SELECT * FROM customers WHERE id=?");
                prst.setInt(1, Integer.parseInt(identifier));
            }

            ResultSet resultSet = prst.executeQuery();
            if (!resultSet.next())
                getCustomerInfoResponse.setResult("deny");
            else
            {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setFirst_name(resultSet.getString("first_name"));
                customer.setLast_name(resultSet.getString("last_name"));
                customer.setCurrency(resultSet.getInt("currency"));
                getCustomerInfoResponse.setData(customer);
                getCustomerInfoResponse.setResult("confirm");

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            getCustomerInfoResponse.setResult("deny");
        }

        return getCustomerInfoResponse;
    }

}
