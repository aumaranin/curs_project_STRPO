package ru.bmstu.curs_project_strpo.customerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bmstu.curs_project_strpo.customerms.properties.Properties;
import ru.bmstu.curs_project_strpo.customerms.properties.SetPropertis;

@SpringBootApplication
public class CustomerMsApplication
{
    public static Properties properties = SetPropertis.docker();
    public static void main(String[] args)
    {
        SpringApplication.run(CustomerMsApplication.class, args);
    }

}
