package ru.bmstu.curs_project_strpo.client2.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.bmstu.curs_project_strpo.client2.Main;

import java.io.IOException;

public class Menu
{
    @FXML
    private Button btnPrivate;
    @FXML
    private Button btnHistory;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnBucket;

    @FXML
    void initialize()
    {
        assert btnBucket != null : "fx:id=\"btnBucket\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnHistory != null : "fx:id=\"btnHistory\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnPrivate != null : "fx:id=\"btnPrivate\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'menu.fxml'.";

        //Обработчик события нажатия кнопки "Личный кабинет пользователя"
        btnPrivate.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("private.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage stage = new Stage();
                stage.setTitle("Личный кабинет пользователя");  //Задаем название окна форма
                stage.setScene(scene_home);              //Устанавливаем сцену как основную
                stage.setResizable(false);               //Отлючаем возможность менять размер окна
                stage.show();                            //Отображение окна

                //закрываем форму главного меню
                Window stageMenu = btnPrivate.getScene().getWindow();
                stageMenu.hide();
            }
        });



        //Обработчик события нажатия кнопки "История покупок"
        btnHistory.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("history.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage historyStage = new Stage();
                historyStage.setTitle("Книжный интернет-магазин: История покупок");  //Задаем название окна форма
                historyStage.setScene(scene_home);              //Устанавливаем сцену как основную
                historyStage.setResizable(false);               //Отлючаем возможность менять размер окна
                historyStage.show();                            //Отображение окна

                //закрываем форму главного меню
                Window stageMenu = btnHistory.getScene().getWindow();
                stageMenu.hide();
            }
        });

        //Обработчик события нажатия кнопки "Поиск книг в магазине"
        btnSearch.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage searchStage = new Stage();
                searchStage.setTitle("Книжный интернет-магазин: поиск книг в магазине");  //Задаем название окна форма
                searchStage.setScene(scene_home);              //Устанавливаем сцену как основную
                searchStage.setResizable(false);               //Отлючаем возможность менять размер окна
                searchStage.show();                            //Отображение окна

                //закрываем форму главного меню
                Window stageMenu = btnSearch.getScene().getWindow();
                stageMenu.hide();
            }
        });

        //Обработчик события нажатия кнопки "Корзина"
        btnBucket.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("basket.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage basketStage = new Stage();
                basketStage.setTitle("Книжный интернет-магазин: корзина");  //Задаем название окна форма
                basketStage.setScene(scene_home);              //Устанавливаем сцену как основную
                basketStage.setResizable(false);               //Отлючаем возможность менять размер окна
                basketStage.show();                            //Отображение окна

                //закрываем форму главного меню
                Window stageMenu = btnSearch.getScene().getWindow();
                stageMenu.hide();




                /*
                //Добавление книги в историю УЖЕ ПОСЛЕ ПОКУПКИ
                HistoryNote note = new HistoryNote(1, "2023.01.01", "Одиссея капитана Блада", "Сабатини Рафаэль", "Приключение", 2022, 1);
                String setHistoryNoteResponse = PostRequest.postRequest("http://localhost:8080/sethistorynote", note.toString());

                 */
            }
        });

    }

}
