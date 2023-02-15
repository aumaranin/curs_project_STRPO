package ru.bmstu.curs_project_strpo.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bmstu.curs_project_strpo.apigateway.properties.Properties;
import ru.bmstu.curs_project_strpo.apigateway.properties.SetPropertis;

@SpringBootApplication
public class ApiGatewayApplication
{
    public static Properties properties = SetPropertis.docker();
    public static void main(String[] args)
    {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
