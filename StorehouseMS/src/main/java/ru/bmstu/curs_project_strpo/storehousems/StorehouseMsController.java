package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Аннотация контроллер дает классу дополниительные возможности в соответствии с шаблоном
//проектирования MVC (Model-View-Controller). Подобный класс расширяет свои возможности
//и может обрабатывать HTTP-запросы. Возвращает json-объекты.
@RestController
public class StorehouseMsController
{
    //Добавление компонентов, хранящих функции работы с базой данных
    private final BookDao bookDao;

    @Autowired
    public StorehouseMsController(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }

    @PostMapping("/show_all")
    public String show_all()
    {
        String res = "";
        List<Book> books = bookDao.show_all();
        Book book = books.get(0);
        res = book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getYear()+ " " + book.getCount();
        //System.out.println(books);
        return res;
    }

    @GetMapping("/test")
    public String test()
    {
        String res = "";
        List<Book> books = bookDao.show_all();
        Book book = books.get(0);
        res = book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + " " + book.getYear()+ " " + book.getCount();
        //System.out.println(books);
        return res;
    }
}
