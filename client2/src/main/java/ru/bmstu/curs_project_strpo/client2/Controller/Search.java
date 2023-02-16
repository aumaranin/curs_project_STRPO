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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.bmstu.curs_project_strpo.client2.*;
import ru.bmstu.curs_project_strpo.client2.DataObjects.Book;

public class Search {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnMenu;

    @FXML
    private ChoiceBox<String> choiceBook;

    @FXML
    private ChoiceBox<Integer> choiceCount;

    @FXML
    private Label labelAdded;

    @FXML
    private Label labelChoice;

    @FXML
    private Label labelCount;

    @FXML
    private Label labelWarning;

    @FXML
    private Button btnAllBooks;

    @FXML
    private Button btnRecommended;

    static List<Book> books;

    static List<String> short_names;

    static List<Integer> books_count;

    //Загрузки книг из БД
    void loadBooks(String type)
    {
        books = new ArrayList<>();
        String getBooksRequest;
        String getBooksResponse;
        if (type.equals("all"))
        {
            //Подготовка JSON-запроса для микросервиса StorehouseMS для загрузки всех книг
            getBooksRequest = "{\n" + "  \"operation\": \"getallbooks\",\n" + "  \"person_id\": \"" + Login.active_user.getId() + "\"\n" + "}";

            //Отправка запроса на получение всех книг
            getBooksResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "getallbooks", getBooksRequest);
        }
        else
        {
            //Подготовка JSON-запроса для микросервиса StorehouseMS для загрузки рекоммендуемых книг
            getBooksRequest = "{\n" + "  \"operation\": \"getrecommendedbooks\",\n" + "  \"person_id\": \"" + Login.active_user.getId() + "\"\n" + "}";
            //Отправка запроса на получение рекомендуемых книг
            getBooksResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "getrecommendedbooks", getBooksRequest);
        }
        //десериализация из JSON в ассоциативный массив
        Map<String, Object> map = Deserialization.deserializeJson(getBooksResponse);

        //Выделение из ассоциативного массива значимых строк
        List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("books");
        //Преобразование списка ассоциативных массивов в список объектов Book
        ObjectMapper mapper = new ObjectMapper();
        //List<Book> books = new ArrayList<>();
        for (Map<String, Object> m: list)
            books.add(mapper.convertValue(m, Book.class));
    }

    void refreshPage()
    {
        choiceBook.getItems().clear();
        choiceCount.getItems().clear();

        //Загрузка книг в choiseBox
        short_names = new ArrayList<>();
        books_count = new ArrayList<>();

        for (Book book: books)
        {
            short_names.add(book.toStringShort());
            books_count.add(book.getCount());
        }

        List<Integer> counts = new ArrayList<>();
        for (int i=1; i<=10; i++)
            counts.add(i);

        choiceBook.getItems().addAll(short_names);
        choiceCount.getItems().addAll(counts);

        //Установка начальных (дефолтных) значений на форму
        choiceBook.setValue(short_names.get(0));
        choiceCount.setValue(1);
    }

    @FXML
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'search.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'search.fxml'.";
        assert choiceBook != null : "fx:id=\"choiceBook\" was not injected: check your FXML file 'search.fxml'.";
        assert choiceCount != null : "fx:id=\"choiceCount\" was not injected: check your FXML file 'search.fxml'.";
        assert labelAdded != null : "fx:id=\"labelAdded\" was not injected: check your FXML file 'search.fxml'.";
        assert labelChoice != null : "fx:id=\"labelChoice\" was not injected: check your FXML file 'search.fxml'.";
        assert labelCount != null : "fx:id=\"labelCount\" was not injected: check your FXML file 'search.fxml'.";
        assert labelWarning != null : "fx:id=\"labelWarning\" was not injected: check your FXML file 'search.fxml'.";
        assert btnAllBooks != null : "fx:id=\"btnAllBooks\" was not injected: check your FXML file 'search.fxml'.";
        assert btnRecommended != null : "fx:id=\"btnRecommended\" was not injected: check your FXML file 'search.fxml'.";

        //Начальные настройки формы
        labelAdded.setVisible(false);
        labelWarning.setVisible(false);


        //Обработчик события нажатия кнопки "Показать все книги"
        btnAllBooks.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                loadBooks("all");
                refreshPage();
            }
        });

        //Обработчик события нажатия кнопки "Рекомендуемые книги"
        btnRecommended.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                loadBooks("recommended");
                refreshPage();
            }
        });

        loadBooks("all");
        refreshPage();

        //Обработчик события нажатия кнопки "Добавить в корзину"
        btnAdd.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

                //Проверка, хватает ли книг в магазине
                String selectedBook = choiceBook.getSelectionModel().getSelectedItem();
                Integer selectedCount = choiceCount.getSelectionModel().getSelectedItem();
                Integer bookNumber = 0;
                for (int i=0; i<short_names.size(); i++)
                {
                    if (selectedBook.equals(short_names.get(i)))
                        bookNumber = i;
                }
                if (selectedCount > books_count.get(bookNumber))
                {
                    labelAdded.setVisible(false);
                    labelWarning.setVisible(true);
                }
                else
                {
                    labelWarning.setVisible(false);
                    labelAdded.setVisible(true);

                    //ДОБАВЛЕНИЕ КНИГИ В КОРЗИНУ
                    int active_user_id = 1;
                    active_user_id = Login.active_user.getId();
                    BasketAddItemRequest basketAddItemRequest = new BasketAddItemRequest();
                    basketAddItemRequest.setOperation("basketadditemrequest");
                    basketAddItemRequest.setPerson_id(active_user_id);
                    basketAddItemRequest.setTitle(books.get(bookNumber).getTitle());
                    basketAddItemRequest.setAuthor(books.get(bookNumber).getAuthor());
                    basketAddItemRequest.setId(books.get(bookNumber).getId());
                    basketAddItemRequest.setGenre(books.get(bookNumber).getGenre());
                    basketAddItemRequest.setYear(books.get(bookNumber).getYear());
                    basketAddItemRequest.setPrice(books.get(bookNumber).getPrice());
                    basketAddItemRequest.setCount(selectedCount);
                    String str1 = basketAddItemRequest.toString();
                    String basketAddItemResponse = PostRequest.postRequest(Main.properties.getApigatewayURL() + "basketadditem", basketAddItemRequest.toString());
                    System.out.println(basketAddItemResponse);
                }

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

                //закрываем форму Поиск книг
                Window stageSearch = btnMenu.getScene().getWindow();
                stageSearch.hide();
            }
        });

    }

}
