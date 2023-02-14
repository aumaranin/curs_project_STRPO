package ru.bmstu.curs_project_strpo.apigateway;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.apigateway.customer.*;
import ru.bmstu.curs_project_strpo.apigateway.purchase.*;
import ru.bmstu.curs_project_strpo.apigateway.storehouse.GetBooksRequest;

import java.io.*;

@RestController
public class ApiGatewayController
{
    private static final String STOREHOUSE_URL = "http://localhost:8040/";
    private static final String CUSTOMER_URL = "http://localhost:8041/";
    private static final String PURCHASE_URL = "http://localhost:8042/";
    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "auth",
                authRequest.toString());
    }

    @PostMapping("/registration")
    public String registration(@RequestBody RegistrationRequest registrationRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "registration",
                registrationRequest.toString());
    }

    @PostMapping("/getcustomerinfo")
    public String getCustomerInfo(@RequestBody GetCustomerInfoRequest getCustomerInfoRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "getcustomerinfo",
                getCustomerInfoRequest.toString());
    }

    @PostMapping("/checkcurrency")
    public String checkCurrency(@RequestBody CheckCurrencyRequest checkCurrencyRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "checkcurrency",
                checkCurrencyRequest.toString());
    }

    @PostMapping("/changecurrency")
    public String changeCurrency(@RequestBody ChangeCurrencyRequest changeCurrencyRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "changecurrency",
                changeCurrencyRequest.toString());
    }

    @PostMapping("/gethistory")
    public String getHistory(@RequestBody GetHistoryRequest getHistoryRequest) throws IOException
    {
        return PostRequest.postRequest(PURCHASE_URL + "gethistory",
                getHistoryRequest.toString());
    }

    @PostMapping("/getallbooks")
    public String getAllBooks(@RequestBody GetBooksRequest getBooksRequest) throws IOException
    {
        return PostRequest.postRequest(STOREHOUSE_URL + "getallbooks",
                getBooksRequest.toString());
    }

    @PostMapping("/getrecommendedbooks")
    public String getRecommendedBooks(@RequestBody GetBooksRequest getBooksRequest) throws IOException
    {
        return PostRequest.postRequest(STOREHOUSE_URL + "getrecommendedbooks",
                getBooksRequest.toString());
    }

    @PostMapping("/basketadditem")
    public String basketAddItem(@RequestBody BasketAddItemRequest basketAddItemRequest) throws IOException
    {
        return PostRequest.postRequest(PURCHASE_URL + "basketadditem",
                basketAddItemRequest.toString());
    }

    @PostMapping("/getbasket")
    public String getBasket(@RequestBody GetBasketRequest getBasketRequest) throws IOException
    {
        return PostRequest.postRequest(PURCHASE_URL + "getbasket",
                getBasketRequest.toString());
    }

    @PostMapping("/clearbasket")
    public String clearBasket(@RequestBody ClearBasketRequest clearBasketRequest) throws IOException
    {
        return PostRequest.postRequest(PURCHASE_URL + "clearbasket",
                clearBasketRequest.toString());
    }

    @PostMapping("/buybasketitems")
    public String buyBasketItems(@RequestBody BuyBasketItemsRequest buyBasketItemsRequest) throws IOException
    {
        return PostRequest.postRequest(PURCHASE_URL + "buybasketitems",
                buyBasketItemsRequest.toString());
    }
}
