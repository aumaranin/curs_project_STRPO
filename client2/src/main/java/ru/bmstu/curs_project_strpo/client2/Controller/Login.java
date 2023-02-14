package ru.bmstu.curs_project_strpo.client2.Controller;


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
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bmstu.curs_project_strpo.client2.DataObjects.Customer;
import ru.bmstu.curs_project_strpo.client2.Deserialization;
import ru.bmstu.curs_project_strpo.client2.Main;
import ru.bmstu.curs_project_strpo.client2.PostRequest;

public class Login
{

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label labelWarning;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReg;

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textPassword;

    //ВРЕМЕННО!!!
    //public static Customer active_user = new Customer(1,"login1", "password1", "123", "321", 5000);
    public static Customer active_user;

    @FXML
    void initialize()
    {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert btnReg != null : "fx:id=\"btnReg\" was not injected: check your FXML file 'login.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'login.fxml'.";
        assert label2 != null : "fx:id=\"label2\" was not injected: check your FXML file 'login.fxml'.";
        assert labelWarning != null : "fx:id=\"labelWarning\" was not injected: check your FXML file 'login.fxml'.";
        assert textLogin != null : "fx:id=\"textLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert textPassword != null : "fx:id=\"textPassword\" was not injected: check your FXML file 'login.fxml'.";

        //Создаем класс с пользователями
        //People people = People.getInstance();

        labelWarning.setVisible(false);     //при первоначальном входе предупреждение о
        //неправильном логине и пароле не отображается

        //Обработчик события нажатия кнопки входа
        btnLogin.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //флаг успешного прохождения аутентификации
                boolean loginSuccess = false;

                //ПРОВЕРКА УСПЕШНОСТИ ВХОДА В СИСТЕМУ
                //Отправляем запрос и получаем результат
                String authRequest = "{" + "\"operation\" : \"auth\", " + "\"login\" : " + "\"" + textLogin.getText() + "\"" + "," + "\"password\" : " + "\"" + textPassword.getText() + "\"" + "}";
                String authResponse = PostRequest.postRequest("http://localhost:8080/auth", authRequest);
                Map<String, Object> map = Deserialization.deserializeJson(authResponse);
                if (map.get("result").equals("confirm"))
                {
                    //Получаем информацию о пользователе по логину
                    String getIdRequest = "{" + "\"operation\" : \"getcustomerinfo\", " + "\"type\" : " + "\"login\"" + "," + "\"identifier\" : " + "\"" + textLogin.getText() + "\"" + "}";
                    String getIdResponse = PostRequest.postRequest("http://localhost:8080/getcustomerinfo", getIdRequest);
                    map = Deserialization.deserializeJson(getIdResponse);
                    map = (Map<String, Object>)map.get("data");
                    ObjectMapper mapper = new ObjectMapper();
                    Customer customer = mapper.convertValue(map,Customer.class);

                    //Проверяем, успешен ли логин и меняем форму

                    //убирается предупреждение о неправильном логине и пароле, если оно было
                    labelWarning.setVisible(false);

                    //указываем активного пользователя, для передачи данных на "домашнюю" страницу
                    active_user = customer;

                    //загружаем и отображаем форму домашней страницы
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
                    Scene scene_home = null;
                    try
                    {
                        scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                    } catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                    Stage menuStage = new Stage();
                    menuStage.setTitle("Главное меню");          //Задаем название окна форма
                    menuStage.setScene(scene_home);              //Устанавливаем сцену как основную
                    menuStage.setResizable(false);               //Отлючаем возможность менять размер окна
                    menuStage.show();                            //Отображение окна

                    //Закрываем текущую Стейж (сцену)
                    Window stageLogin = labelWarning.getScene().getWindow();
                    stageLogin.hide();
                }

                //отображается предупреждение о неверном логине и пароле
                else
                    labelWarning.setVisible(true);
            }
        });

        //Обработка нажатия кнопки "Регистрация"
        btnReg.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //открываем форму входа в систему
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registration.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage stage = new Stage();
                stage.setTitle("Добавление пользователя");          //Задаем название окна форма
                stage.setScene(scene_home);              //Устанавливаем сцену как основную
                stage.setResizable(false);               //Отлючаем возможность менять размер окна
                stage.show();                            //Отображение окна

                //закрываем форму регистрации
                Window stageLogin = btnReg.getScene().getWindow();
                stageLogin.hide();
            }
        });
    }
}