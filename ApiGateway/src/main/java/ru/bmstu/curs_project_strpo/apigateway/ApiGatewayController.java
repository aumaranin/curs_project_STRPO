package ru.bmstu.curs_project_strpo.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.apigateway.customer.*;

import java.io.*;

@RestController
public class ApiGatewayController
{
    private static final String CUSTOMER_URL = "http://localhost:8041/";
    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest) throws IOException
    {
        return PostRequest.postRequest(CUSTOMER_URL + "auth", authRequest.toString());
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
}
