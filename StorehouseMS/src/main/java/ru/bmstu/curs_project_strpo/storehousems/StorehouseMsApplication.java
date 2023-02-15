package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bmstu.curs_project_strpo.storehousems.properties.Properties;
import ru.bmstu.curs_project_strpo.storehousems.properties.SetPropertis;

@SpringBootApplication
public class StorehouseMsApplication
{
    public static Properties properties = SetPropertis.common();
    public static void main(String[] args)
    {
        SpringApplication.run(StorehouseMsApplication.class, args);
    }

}
