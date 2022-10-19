package ru.bmstu.curs_project_strpo.apigateway;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController
{
    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest)
    {
        System.out.println(authRequest.getOperation());
        System.out.println(authRequest.getLogin());
        System.out.println(authRequest.getPassword());
        return "OK";
    }
}
