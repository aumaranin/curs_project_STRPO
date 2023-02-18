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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.bmstu.curs_project_strpo.client2.DataObjects.Book;
import ru.bmstu.curs_project_strpo.client2.Deserialization;
import ru.bmstu.curs_project_strpo.client2.Main;
import ru.bmstu.curs_project_strpo.client2.PostRequest;

public class Basket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnMenu;

    @FXML
    private ScrollPane scrollPane2;

    @FXML
    private VBox vbox2;

    @FXML
    private Label labelBought;

    @FXML
    private Label labelCleared;
    @FXML
    private Label labelWarningCurrency;

    static List<Book> books;

    void loadBasket()
    {
        //ОТПРАВКА ЗАПРОСА НА PURCHASE для загрузки книг из корзины
        //Подготовка JSON-запроса для микросервиса StorehouseMS для загрузки всех книг
        int active_user_id = Login.active_user.getId();
        String getBasketRequest = "{" + "\"operation\" : \"getbasket\"," + "\"person_id\" : " + "\"" + active_user_id + "\"" + "}";
        //Отправка запроса на получение всех книг
        String getBasketResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "getbasket", getBasketRequest);

        //Распознавание JSON ответа
        Map<String, Object> map = Deserialization.deserializeJson(getBasketResponse);

        //Проверка, успешна ли операция
        if (map.get("result").equals("confirm"))
        {
            //Выделение из ассоциативного массива значимых строк
            List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("books");

            //Преобразование списка ассоциативных массивов в список обзъектов
            books = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            for (Map<String, Object> m: list)
                books.add(mapper.convertValue(m, Book.class));
        }
    }

    void refreshPage()
    {
        labelBought.setVisible(false);
        labelCleared.setVisible(false);
        labelWarningCurrency.setVisible(false);
        vbox2.getChildren().clear();
        for (Book book: books)
        {
            vbox2.getChildren().add(new Label(book.toStringShort()));
        }
    }

    @FXML
    void initialize() {
        assert btnBuy != null : "fx:id=\"btnBuy\" was not injected: check your FXML file 'basket.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'basket.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'basket.fxml'.";
        assert scrollPane2 != null : "fx:id=\"scrollPane2\" was not injected: check your FXML file 'basket.fxml'.";
        assert vbox2 != null : "fx:id=\"vbox2\" was not injected: check your FXML file 'basket.fxml'.";
        assert labelBought != null : "fx:id=\"labelBought\" was not injected: check your FXML file 'basket.fxml'.";
        assert labelCleared != null : "fx:id=\"labelCleared\" was not injected: check your FXML file 'basket.fxml'.";
        assert labelWarningCurrency != null : "fx:id=\"labelWarningCurrency\" was not injected: check your FXML file 'basket.fxml'.";

        //загрузка содержимого корзины из микросервиса Purchase
        loadBasket();

        //обновление содержимого формы.
        refreshPage();

        //Обработчик события нажатия кнопки "Очистить корзину"
        btnClear.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                //ОТПРАВКА ЗАПРОСА НА ОЧИСТКУ СОДЕРЖИМОГО КОРЗИНЫ В МС PURCHASE
                //Подготовка JSON-запроса для микросервиса StorehouseMS для очистки корзины
                int active_user_id = 1;
                active_user_id = Login.active_user.getId();
                String clearBasketRequest = "{" + "\"operation\" : \"clearbasket\"," + "\"person_id\" : " + "\"" + active_user_id + "\"" + "}";
                //Отправка запроса
                String getBasketResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "clearbasket", clearBasketRequest);

                //ОБНОВЛЕНИЕ СОДЕРЖИМОГО ФОРМЫ
                loadBasket();
                refreshPage();
                labelBought.setVisible(false);
                labelCleared.setVisible(true);
                labelWarningCurrency.setVisible(false);
            }
        });

        //Обработчик события нажатия кнопки "Купить"
        btnBuy.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent actionEvent)
            {
                //ОТПРАВКА ЗАПРОСА НА ПОКУПКУ СОДЕРЖИМОГО КОРЗИНЫ В МС PURCHASE
                //Подготовка JSON-запроса для микросервиса StorehouseMS для очистки корзины
                int active_user_id = 1;
                active_user_id = Login.active_user.getId();
                String buyBusketItemsRequest = "{" + "\"operation\" : \"buybusketitems\"," + "\"person_id\" : " + "\"" + active_user_id + "\"" + "}";
                //Отправка запроса
                String buyBusketItemsResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "buybasketitems", buyBusketItemsRequest);
                //Распознавание JSON ответа
                Map<String, Object> buyBusketItemsMap = Deserialization.deserializeJson(buyBusketItemsResponse);

                //ОБНОВЛЕНИЕ СОДЕРЖИМОГО ФОРМЫ
                loadBasket();
                refreshPage();
                labelCleared.setVisible(false);
                labelBought.setVisible(false);
                labelWarningCurrency.setVisible(false);
                //Проверка, успешна ли операция проверки денежных средств
                if (buyBusketItemsMap.get("result").equals("Денежных средств на счете пользователя не достаточно"))
                    labelWarningCurrency.setVisible(true);
                else
                    labelBought.setVisible(true);
            }
        });

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
                Window stageBasket = btnMenu.getScene().getWindow();
                stageBasket.hide();
            }
        });
    }

}
