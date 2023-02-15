package ru.bmstu.curs_project_strpo.purchasems.purchasems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.SetHistory.SetHistoryRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.basket.*;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.customerms.ChangeCurrencyRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.customerms.CheckCurrencyRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.history.BuyNote;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.history.BuyNoteDao;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.history.GetHistoryRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.history.GetHistoryResponse;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.storehousems.CheckBookQuantityRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.storehousems.DropBookRequest;
import ru.bmstu.curs_project_strpo.purchasems.purchasems.test.TestResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//Аннотация контроллер дает классу дополниительные возможности в соответствии с шаблоном
//проектирования MVC (Model-View-Controller). Подобный класс расширяет свои возможности
//и может обрабатывать HTTP-запросы. Возвращает json-объекты.
@RestController
public class PurchaseMsController
{
    private static final String STOREHOUSE_URL = PurchaseMsApplication.properties.getStorehouseMsURL();
    private static final String CUSTOMER_URL = PurchaseMsApplication.properties.getCustomerMsURL();
    private static final String HISTORY_URL = PurchaseMsApplication.properties.getHistoryMsURL();

    //Добавление компонентов, хранящих функции работы с базой данных
    private final BuyNoteDao buyNoteDao;
    private final BasketDao basketDao;

    @Autowired
    public PurchaseMsController(BuyNoteDao buyNoteDao, BasketDao basketDao)
    {
        this.buyNoteDao = buyNoteDao;
        this.basketDao = basketDao;
    }

    @GetMapping("test")
    public String testGet()
    {
        return "Сервис: PurchaseMS\n\tСтатус: работает\n";
    }

    @PostMapping("test")
    public TestResponse testPost()
    {
        return new TestResponse("ok");
    }

    @PostMapping("/gethistory")
    public GetHistoryResponse getHistory(@RequestBody GetHistoryRequest getHistoryRequest)
    {
        GetHistoryResponse responseToApiGateway = buyNoteDao.getHistory(
                getHistoryRequest.getPerson_id()
        );
        return responseToApiGateway;
    }

    //УСТАНОВКА ЗАМИТКИ ИСТОРИИ

    @PostMapping("/basketadditem")
    public BasketAddItemResponse basketAddItem(@RequestBody BasketAddItemRequest basketAddItemRequest)
    {
        BasketAddItemResponse responseToApiGateway = basketDao.basketAddItem(basketAddItemRequest);
        return responseToApiGateway;
    }

    @PostMapping("/getbasket")
    public GetBasketResponse getBasket(@RequestBody GetBasketRequest getBasketRequest)
    {
        List<Book> books = basketDao.getBasket(getBasketRequest.getPerson_id());
        GetBasketResponse responseToApiGateway = new GetBasketResponse();
        responseToApiGateway.setOperation("getbasket");
        responseToApiGateway.setResult("confirm");
        responseToApiGateway.setBooks(books);
        return responseToApiGateway;
    }

    //обработка запроса очистки корзины пользователя
    @PostMapping("/clearbasket")
    public ClearBasketResponse clearBasket(@RequestBody ClearBasketRequest clearBasketRequest)
    {
        ClearBasketResponse responseToApiGateway = new ClearBasketResponse();
        responseToApiGateway.setOperation("clearbasket");
        String result = basketDao.clearBasket(clearBasketRequest.getPerson_id());
        responseToApiGateway.setResult(result);
        return responseToApiGateway;
    }

    /*
    //обработка запроса покупки книг из корзины пользователя
    @PostMapping("/buybasketitems")
    public BuyBasketItemsResponse buyBasketItems(@RequestBody BuyBasketItemsRequest buyBasketItemsRequest)
    {
        String result = "deny";
        BuyBasketItemsResponse responseToApiGateway = new BuyBasketItemsResponse();
        boolean flag_error = false;
        int requied_currency = 0;           //переменная с необходимой суммой денежных средств

        //ПРОВЕРКА, ЕСТЬ ЛИ В КОРЗИНЕ ЧТО-ТО
        List<Book> books = basketDao.getBasket(buyBasketItemsRequest.getPerson_id());
        if ((books == null) || (books.isEmpty()) || (books.size() == 0))
        {
            flag_error = true;
            responseToApiGateway.setResult("Корзина пуста");
        }
        else
        {
            //ПРОВЕРКА НАЛИЧИЯ ТРЕБУЕМЫХ КНИГ в микросервисе StorehouseMS
            //подготовка запроса к StorehouseMS о проверки количества книг
            for (Book book : books)
            {
                CheckBookQuantityRequest req = new CheckBookQuantityRequest(book.getId(), book.getCount());
                String checkBookQuantityResponse = PostRequest.postRequest(STOREHOUSE_URL + "checkbookquantity", req.toString());
                Map<String, Object> map = Deserialization.deserializeJson(checkBookQuantityResponse);
                if (map.get("result").equals("false"))
                {
                    flag_error = true;
                    responseToApiGateway.setResult("Требуемых книг на складе не хвататет.");
                }
            }

            //ПРОВЕРКА ДЕНЕЖНЫХ СРЕДСТВ ПОЛЬЗОВАТЕЛЯ в микросервисе CustomerMS
            //Подсчет необходимых денежных средств
            for (Book book : books)
                requied_currency += book.getCount() * book.getPrice();

            CheckCurrencyRequest req = new CheckCurrencyRequest(buyBasketItemsRequest.getPerson_id(), requied_currency);
            String checkCurrencyResponse = PostRequest.postRequest(CUSTOMER_URL + "checkcurrency", req.toString());
            Map<String, Object> map = Deserialization.deserializeJson(checkCurrencyResponse);
            if (!map.get("result").equals("confirm"))
            {
                flag_error = true;
                responseToApiGateway.setResult("Денежных средств на счете пользователя не достаточно");
            }
        }

        if (!flag_error)
        {
            //СНЯТИЕ СРЕДСТВ С ПОЛЬЗОВАТЕЛЯ
            requied_currency = requied_currency * (-1);
            ChangeCurrencyRequest req = new ChangeCurrencyRequest(buyBasketItemsRequest.getPerson_id(), requied_currency);
            String changeCurrencyResponse = PostRequest.postRequest(CUSTOMER_URL + "changecurrency", req.toString());
            Map<String, Object> map = Deserialization.deserializeJson(changeCurrencyResponse);

            //УМЕНЬШЕНИЕ КОЛИЧЕСТВА КНИГ В STOREHOUSEMS
            for (Book book: books)
            {
                //Подготавливается запрос на уменьшение книг
                DropBookRequest dropBookRequest = new DropBookRequest(book.getId(), book.getCount());
                String dropBookResponse = PostRequest.postRequest(STOREHOUSE_URL + "dropbook", dropBookRequest.toString());
            }

            //ОЧИСТКА КОРЗИНЫ
            ClearBasketResponse clearBasketResponse = new ClearBasketResponse();
            String res = basketDao.clearBasket(buyBasketItemsRequest.getPerson_id());

            //ДОБАВЛЕНИЕ КНИГ В HISTORY
            //Формирование списка BuyNote
            List<BuyNote> histories = new ArrayList<>();

            //Определяем текущее время
            SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
            Date curDate = new Date();
            String currentDate = formater.format(curDate);

            for (Book book: books)
            {
                BuyNote history = new BuyNote();
                history.setDate(currentDate);
                history.setPerson_id(buyBasketItemsRequest.getPerson_id());
                history.setId(book.getId());
                history.setTitle(book.getTitle());
                history.setAuthor(book.getAuthor());
                history.setGenre(book.getGenre());
                history.setYear(book.getYear());
                history.setPrice(book.getPrice());
                history.setCount(book.getCount());
                histories.add(history);
            }

            //Вписываем в таблицу history список BuyNote
            for (BuyNote history: histories)
            {
                String setNoteResult = buyNoteDao.setHistoryNote(history);
            }
            responseToApiGateway.setResult("confirm");
        }

        if (!flag_error)
            responseToApiGateway.setResult("confirm");
        return responseToApiGateway;
    }

     */


    //НОВАЯ ПОКУПКА с учетом существующего сервиса HistoryMS
    //обработка запроса покупки книг из корзины пользователя
    @PostMapping("/buybasketitems")
    public BuyBasketItemsResponse buyBasketItems(@RequestBody BuyBasketItemsRequest buyBasketItemsRequest)
    {
        String result = "deny";
        BuyBasketItemsResponse responseToApiGateway = new BuyBasketItemsResponse();
        boolean flag_error = false;
        int requied_currency = 0;           //переменная с необходимой суммой денежных средств

        //ПРОВЕРКА, ЕСТЬ ЛИ В КОРЗИНЕ ЧТО-ТО
        List<Book> books = basketDao.getBasket(buyBasketItemsRequest.getPerson_id());
        if ((books == null) || (books.isEmpty()) || (books.size() == 0))
        {
            flag_error = true;
            responseToApiGateway.setResult("Корзина пуста");
        }
        else
        {
            //ПРОВЕРКА НАЛИЧИЯ ТРЕБУЕМЫХ КНИГ в микросервисе StorehouseMS
            //подготовка запроса к StorehouseMS о проверки количества книг
            for (Book book : books)
            {
                CheckBookQuantityRequest req = new CheckBookQuantityRequest(book.getId(), book.getCount());
                String checkBookQuantityResponse = PostRequest.postRequest(STOREHOUSE_URL + "checkbookquantity", req.toString());
                Map<String, Object> map = Deserialization.deserializeJson(checkBookQuantityResponse);
                if (map.get("result").equals("false"))
                {
                    flag_error = true;
                    responseToApiGateway.setResult("Требуемых книг на складе не хвататет.");
                }
            }

            //ПРОВЕРКА ДЕНЕЖНЫХ СРЕДСТВ ПОЛЬЗОВАТЕЛЯ в микросервисе CustomerMS
            //Подсчет необходимых денежных средств
            for (Book book : books)
                requied_currency += book.getCount() * book.getPrice();

            CheckCurrencyRequest req = new CheckCurrencyRequest(buyBasketItemsRequest.getPerson_id(), requied_currency);
            String checkCurrencyResponse = PostRequest.postRequest(CUSTOMER_URL + "checkcurrency", req.toString());
            Map<String, Object> map = Deserialization.deserializeJson(checkCurrencyResponse);
            if (!map.get("result").equals("confirm"))
            {
                flag_error = true;
                responseToApiGateway.setResult("Денежных средств на счете пользователя не достаточно");
            }
        }

        if (!flag_error)
        {
            //СНЯТИЕ СРЕДСТВ С ПОЛЬЗОВАТЕЛЯ
            requied_currency = requied_currency * (-1);
            ChangeCurrencyRequest req = new ChangeCurrencyRequest(buyBasketItemsRequest.getPerson_id(), requied_currency);
            String changeCurrencyResponse = PostRequest.postRequest(CUSTOMER_URL + "changecurrency", req.toString());
            Map<String, Object> map = Deserialization.deserializeJson(changeCurrencyResponse);

            //УМЕНЬШЕНИЕ КОЛИЧЕСТВА КНИГ В STOREHOUSEMS
            for (Book book: books)
            {
                //Подготавливается запрос на уменьшение книг
                DropBookRequest dropBookRequest = new DropBookRequest(book.getId(), book.getCount());
                String dropBookResponse = PostRequest.postRequest(STOREHOUSE_URL + "dropbook", dropBookRequest.toString());
            }

            //ОЧИСТКА КОРЗИНЫ
            ClearBasketResponse clearBasketResponse = new ClearBasketResponse();
            String res = basketDao.clearBasket(buyBasketItemsRequest.getPerson_id());
            if (!res.equals("confirm"))
                flag_error = true;

            //ДОБАВЛЕНИЕ КНИГ В HISTORY

            //Определяем текущее время
            SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
            Date curDate = new Date();
            String currentDate = formater.format(curDate);

            SetHistoryRequest setHistoryRequest =
                    new SetHistoryRequest(buyBasketItemsRequest.getPerson_id(), currentDate, books);

            //Отправка запроса на микросервис HistoryMS
            String setHistoryResponse = PostRequest.postRequest(HISTORY_URL + "sethistory", setHistoryRequest.toString());

            if (!setHistoryResponse.equals("confirm"))
                flag_error = true;
        }

        if (!flag_error)
            responseToApiGateway.setResult("confirm");

        return responseToApiGateway;
    }

}
