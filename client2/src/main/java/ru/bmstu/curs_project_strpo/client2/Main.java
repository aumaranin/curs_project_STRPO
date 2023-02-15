package ru.bmstu.curs_project_strpo.client2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.bmstu.curs_project_strpo.client2.properties.Properties;
import ru.bmstu.curs_project_strpo.client2.properties.SetPropertis;

import java.io.IOException;

public class Main extends Application
{
    public static Properties properties = SetPropertis.common();
    //Запуск потока приложения
    @Override
    public void start(Stage stage) throws IOException
    {
        //Загрузка основной формы для входа в систему
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());         //Задаем сцену, размеры сцены не указываем
        stage.setTitle("Книжный интернет-магазин: вход");   //Задаем название окна форма
        stage.setScene(scene);                              //Устанавливаем сцену как основную
        stage.setResizable(false);                          //Отлючаем возможность менять размер окна
        stage.show();                                       //Отображение окна
    }

    //Основная функция запуска приложения

    public static void main(String[] args) {
        launch();
    }
}