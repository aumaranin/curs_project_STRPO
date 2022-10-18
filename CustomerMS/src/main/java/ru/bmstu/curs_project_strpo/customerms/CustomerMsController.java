package ru.bmstu.curs_project_strpo.customerms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
