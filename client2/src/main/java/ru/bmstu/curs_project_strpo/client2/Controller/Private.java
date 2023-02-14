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
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.bmstu.curs_project_strpo.client2.ChangeCurrencyRequest;
import ru.bmstu.curs_project_strpo.client2.DataObjects.Customer;
import ru.bmstu.curs_project_strpo.client2.Deserialization;
import ru.bmstu.curs_project_strpo.client2.Main;
import ru.bmstu.curs_project_strpo.client2.PostRequest;

public class Private {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddCurrency;

    @FXML
    private Button btnMenu;

    @FXML
    private Label labelCurrencyAdded;

    @FXML
    private TextField textAddCurrency;

    @FXML
    private TextField textCurrentCurrency;

    @FXML
    private TextField textFirst;

    @FXML
    private TextField textLast;

    @FXML
    private Label labelInputIncorrect;

    @FXML
    private Label labelTextAreaEmpty;

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textPas;

    void refreshPage()
    {
        //при обновлении страницы - все оповещения отключены
        labelCurrencyAdded.setVisible(false);
        labelInputIncorrect.setVisible(false);
        labelTextAreaEmpty.setVisible(false);

        //Получаем информацию о пользователе по логину
        String getIdRequest = "{" + "\"operation\" : \"getcustomerinfo\", " + "\"type\" : " + "\"login\"" + "," + "\"identifier\" : " + "\"" + Login.active_user.getLogin() + "\"" + "}";
        String getIdResponse = PostRequest.postRequest("http://localhost:8080/getcustomerinfo", getIdRequest);
        Map<String, Object> map = Deserialization.deserializeJson(getIdResponse);
        map = (Map<String, Object>)map.get("data");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.convertValue(map,Customer.class);
        //Обновляем информацию по пользователю системы
        Login.active_user = customer;

        textLogin.setText(Login.active_user.getLogin());
        textPas.setText(Login.active_user.getPassword());
        textFirst.setText(Login.active_user.getFirst_name());
        textLast.setText(Login.active_user.getLast_name());
        textCurrentCurrency.setText(Integer.toString(Login.active_user.getCurrency()));

    }
    @FXML
    void initialize() {
        assert btnAddCurrency != null : "fx:id=\"btnAddCurrency\" was not injected: check your FXML file 'private.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'private.fxml'.";
        assert labelCurrencyAdded != null : "fx:id=\"labelCurrencyAdded\" was not injected: check your FXML file 'private.fxml'.";
        assert labelInputIncorrect != null : "fx:id=\"labelInputIncorrect\" was not injected: check your FXML file 'private.fxml'.";
        assert labelTextAreaEmpty != null : "fx:id=\"labelTextAreaEmpty\" was not injected: check your FXML file 'private.fxml'.";
        assert textAddCurrency != null : "fx:id=\"textAddCurrency\" was not injected: check your FXML file 'private.fxml'.";
        assert textCurrentCurrency != null : "fx:id=\"textCurrentCurrency\" was not injected: check your FXML file 'private.fxml'.";
        assert textFirst != null : "fx:id=\"textFirst\" was not injected: check your FXML file 'private.fxml'.";
        assert textLast != null : "fx:id=\"textLast\" was not injected: check your FXML file 'private.fxml'.";
        assert textLogin != null : "fx:id=\"textLogin\" was not injected: check your FXML file 'private.fxml'.";
        assert textPas != null : "fx:id=\"textPas\" was not injected: check your FXML file 'private.fxml'.";



        refreshPage();

        //Обработка нажатия кнопки "Добавить денежные средства"
        btnAddCurrency.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                labelCurrencyAdded.setVisible(false);
                labelInputIncorrect.setVisible(false);
                labelTextAreaEmpty.setVisible(false);

                //Проверка, является ли поле ввода пустым
                if (textAddCurrency.getText().strip().isEmpty()){
                    labelTextAreaEmpty.setVisible(true);
                    return;
                }

                //Проверка на правильный формат ввода
                String text = textAddCurrency.getText();
                Integer newCurrency = 0;
                try
                {
                    newCurrency = Integer.parseInt(textAddCurrency.getText().strip());
                } catch (NumberFormatException exception)
                {
                    labelInputIncorrect.setVisible(true);
                    return;
                }
                //Добавление средств на счет пользователя
                //Формирование JSON запроса
                ChangeCurrencyRequest changeCurrencyRequest =
                        new ChangeCurrencyRequest(Login.active_user.getId(), newCurrency);
                //Отправка JSON запроса на API-шлюз
                String changeCurrencyResponse = PostRequest.postRequest(
                        "http://localhost:8080/changecurrency", changeCurrencyRequest.toString());
                //Распознавание JSON ответа
                Map<String, Object> map = Deserialization.deserializeJson(changeCurrencyResponse);

                //Проверка, успешна ли операция
                if (map.get("result").equals("confirm"))
                {
                    refreshPage();
                    labelCurrencyAdded.setVisible(true);
                }
                else System.out.println("ОШИБКА");
            }
        });

        //Обработка нажатия кнопки "Главное меню"
        btnMenu.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //открываем форму входа в систему
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
                Scene scene_home = null;
                try
                {
                    scene_home = new Scene(fxmlLoader.load()); //Задаем сцену, размеры сцены не указываем
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                Stage stage = new Stage();
                stage.setTitle("Главное меню");          //Задаем название окна форма
                stage.setScene(scene_home);              //Устанавливаем сцену как основную
                stage.setResizable(false);               //Отлючаем возможность менять размер окна
                stage.show();                            //Отображение окна

                //закрываем форму регистрации
                Window currentStage = btnMenu.getScene().getWindow();
                currentStage.hide();
            }
        });

    }

}
