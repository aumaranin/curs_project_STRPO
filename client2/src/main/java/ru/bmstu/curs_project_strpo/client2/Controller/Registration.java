package ru.bmstu.curs_project_strpo.client2.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.bmstu.curs_project_strpo.client2.DataObjects.Customer;
import ru.bmstu.curs_project_strpo.client2.Deserialization;
import ru.bmstu.curs_project_strpo.client2.Main;
import ru.bmstu.curs_project_strpo.client2.PostRequest;
import ru.bmstu.curs_project_strpo.client2.RegistrationRequest;

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Label labelWarning;
    @FXML
    private Label labelWarning2;

    @FXML
    private TextField textFirst;

    @FXML
    private TextField textLast;

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textPas;

    @FXML
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'registration.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'registration.fxml'.";
        assert labelWarning != null : "fx:id=\"labelWarning\" was not injected: check your FXML file 'registration.fxml'.";
        assert textFirst != null : "fx:id=\"textFirst\" was not injected: check your FXML file 'registration.fxml'.";
        assert textLast != null : "fx:id=\"textLast\" was not injected: check your FXML file 'registration.fxml'.";
        assert textLogin != null : "fx:id=\"textLogin\" was not injected: check your FXML file 'registration.fxml'.";
        assert textPas != null : "fx:id=\"textPas\" was not injected: check your FXML file 'registration.fxml'.";
        assert labelWarning2 != null : "fx:id=\"labelWarning2\" was not injected: check your FXML file 'registration.fxml'.";

        labelWarning.setVisible(false);
        labelWarning2.setVisible(false);

        //Обработка нажатия кнопки "Добавить пользователя"
        btnAdd.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String login = textLogin.getText();
                String password = textPas.getText();
                String firstName = textFirst.getText();
                String lastName = textLast.getText();
                if (login.strip().isEmpty() || password.strip().isEmpty() || firstName.strip().isEmpty() || lastName.strip().isEmpty())
                {
                    labelWarning2.setVisible(true);
                    return;
                }

                //Отправка запроса в формате JSON на API-шлюз и CustomerMS
                RegistrationRequest registrationRequest =
                        new RegistrationRequest("registration", login, password, firstName, lastName);
                String registrationResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "registration", registrationRequest.toString());

                //Распознавание JSON ответа
                Map<String, Object> map = Deserialization.deserializeJson(registrationResponse);

                //Проверка, успешна ли операция
                if (map.get("result").equals("confirm"))
                {
                    //открываем форму входа в систему
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
                    Scene scene_home = null;
                    try
                    {
                        scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                    } catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Книжный интернет-магазин: вход");          //Задаем название окна форма
                    stage.setScene(scene_home);              //Устанавливаем сцену как основную
                    stage.setResizable(false);               //Отлючаем возможность менять размер окна
                    stage.show();                            //Отображение окна

                    //закрываем форму регистрации
                    Window stageRegistration = btnBack.getScene().getWindow();
                    stageRegistration.hide();
                }
                else
                {
                    labelWarning.setVisible(true);
                    labelWarning2.setVisible(false);
                }
            }
        });

        //Обработка нажатия кнопки "Назад"
        btnBack.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //открываем форму входа в систему
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage stage = new Stage();
                stage.setTitle("Книжный интернет-магазин: вход");          //Задаем название окна форма
                stage.setScene(scene_home);              //Устанавливаем сцену как основную
                stage.setResizable(false);               //Отлючаем возможность менять размер окна
                stage.show();                            //Отображение окна

                //закрываем форму регистрации
                Window stageRegistration = btnBack.getScene().getWindow();
                stageRegistration.hide();
            }
        });
    }

}
