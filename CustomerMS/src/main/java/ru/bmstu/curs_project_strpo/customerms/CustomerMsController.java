package ru.bmstu.curs_project_strpo.customerms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.customerms.auth.AuthRequest;
import ru.bmstu.curs_project_strpo.customerms.auth.AuthResponse;
import ru.bmstu.curs_project_strpo.customerms.get_customer_info.GetCustomerInfoRequest;
import ru.bmstu.curs_project_strpo.customerms.get_customer_info.GetCustomerInfoResponse;
import ru.bmstu.curs_project_strpo.customerms.registration.RegistrationRequest;
import ru.bmstu.curs_project_strpo.customerms.registration.RegistrationResponse;

import java.util.List;

//Аннотация контроллер дает классу дополниительные возможности в соответствии с шаблоном
//проектирования MVC (Model-View-Controller). Подобный класс расширяет свои возможности
//и может обрабатывать HTTP-запросы. Возвращает json-объекты.
@RestController
public class CustomerMsController
{

    private final CustomerDao customerDao;

    @Autowired
    public CustomerMsController(CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }

    @GetMapping("/test")
    public String test()
    {

        List<Customer> customers = customerDao.show_all();

        String res = "Customer Controller";
        return customers.get(1).getFirst_name();


        //return "123123123";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest authRequest)
    {
        AuthResponse responseToApiGateway = customerDao.auth(
                authRequest.getLogin(),
                authRequest.getPassword()
        );
        return responseToApiGateway;
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest registrationRequest)
    {
        RegistrationResponse responseToApiGateway = customerDao.registration(
                registrationRequest.getLogin(),
                registrationRequest.getPassword(),
                registrationRequest.getFirst_name(),
                registrationRequest.getLast_name()
        );
        return responseToApiGateway;
    }

    @PostMapping("/getcustomerinfo")
    public GetCustomerInfoResponse getCustomerInfo(@RequestBody GetCustomerInfoRequest getCustomerInfoRequest)
    {
        GetCustomerInfoResponse response = customerDao.getCustomerInfo(
                getCustomerInfoRequest.getType(),
                getCustomerInfoRequest.getIdentifier()
        );

        return response;
    }

}
