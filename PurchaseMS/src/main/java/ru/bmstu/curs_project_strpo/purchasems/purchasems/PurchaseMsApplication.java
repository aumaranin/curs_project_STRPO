package ru.bmstu.curs_project_strpo.purchasems.purchasems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.properties.Properties;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.properties.SetPropertis;

@SpringBootApplication
public class PurchaseMsApplication
{
    public static Properties properties = SetPropertis.docker();
    public static void main(String[] args)
    {
        SpringApplication.run(PurchaseMsApplication.class, args);
    }

}
