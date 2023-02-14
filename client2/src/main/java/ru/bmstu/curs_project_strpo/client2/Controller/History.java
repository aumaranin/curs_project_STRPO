package ru.bmstu.curs_project_strpo.client2.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ru.bmstu.curs_project_strpo.client2.DataObjects.HistoryNote;
import ru.bmstu.curs_project_strpo.client2.Deserialization;
import ru.bmstu.curs_project_strpo.client2.Main;
import ru.bmstu.curs_project_strpo.client2.PostRequest;

public class History {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnMenu;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private VBox vbox1;

    @FXML
    void initialize() {
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'history.fxml'.";
        assert vbox1 != null : "fx:id=\"vbox1\" was not injected: check your FXML file 'history.fxml'.";

        //Отправка запроса к API-шлюзу для получения списка купленных книг
        int active_user_id = 1;
        active_user_id = Login.active_user.getId();

        String getHistoryRequest =  "{" + "\"operation\" : \"gethistory\", " + "\"person_id\" : " + "\"" + active_user_id + "\"}";
        String getHistoryResponse = PostRequest.postRequest("http://localhost:8080/gethistory", getHistoryRequest);

        //Распознавание JSON ответа
        Map<String, Object> map = Deserialization.deserializeJson(getHistoryResponse);

        //Проверка, успешна ли операция
        if (map.get("result").equals("confirm"))
        {
            //Выделение из ассоциативного массива значимых строк
            List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("history");

             //Преобразование списка ассоциативных массивов в список объектов Book
            ObjectMapper mapper = new ObjectMapper();
            List<HistoryNote> histories = new ArrayList<>();
            for (Map<String, Object> m: list)
            {
                histories.add(mapper.convertValue(m, HistoryNote.class));
            }
            for (HistoryNote note: histories)
                vbox1.getChildren().add(new Label(note.toStringShort()));
        }
        //Обработчик события нажатия кнопки "Главное меню"
        btnMenu.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                //открываем форму главного меню
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

                //закрываем форму Истории покупок
                Window stageHistory = btnMenu.getScene().getWindow();
                stageHistory.hide();
            }
        });

    }

}

