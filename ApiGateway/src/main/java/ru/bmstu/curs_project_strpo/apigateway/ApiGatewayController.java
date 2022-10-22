package ru.bmstu.curs_project_strpo.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.apigateway.customer.AuthRequest;
import ru.bmstu.curs_project_strpo.apigateway.customer.RegistrationRequest;

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
}
