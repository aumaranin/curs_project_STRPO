package ru.bmstu.curs_project_strpo.customerms;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDao
{
    //private static final String URL = "jdbc:postgresql://storehousebd:5432/customerbd";
    private static final String URL = "jdbc:postgresql://localhost:5432/customerbd";
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

}
