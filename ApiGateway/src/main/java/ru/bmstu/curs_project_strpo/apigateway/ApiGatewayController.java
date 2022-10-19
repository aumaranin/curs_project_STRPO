package ru.bmstu.curs_project_strpo.apigateway;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController
{
    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest request)
    {
        System.out.println(request.getOperation());
        System.out.println(request.getLogin());
        System.out.println(request.getPassword());

        return "OK";
    }
}
