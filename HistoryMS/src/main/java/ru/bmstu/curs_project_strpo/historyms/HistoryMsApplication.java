package ru.bmstu.curs_project_strpo.historyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bmstu.curs_project_strpo.historyms.properties.Properties;
import ru.bmstu.curs_project_strpo.historyms.properties.SetPropertis;

@SpringBootApplication
public class HistoryMsApplication
{
    public static Properties properties = SetPropertis.common();
    public static void main(String[] args)
    {
        SpringApplication.run(HistoryMsApplication.class, args);
    }
}
