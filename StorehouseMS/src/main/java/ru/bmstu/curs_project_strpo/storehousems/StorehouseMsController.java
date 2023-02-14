package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.storehousems.test.TestResponse;

import java.util.Random;

import java.util.ArrayList;
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

    @GetMapping("test")
    public String testGet()
    {
        return "Сервис: StorehouseMS\n\tСтатус: работает\n";
    }

    @PostMapping("test")
    public TestResponse testPost()
    {
        return new TestResponse("ok");
    }

    @PostMapping("/getallbooks")
    public GetBooksResponse getAllBooks()
    {
        List<Book> books = bookDao.getAllBooks();
        GetBooksResponse getBooksResponse = new GetBooksResponse();
        getBooksResponse.setOperation("getallbooks");
        getBooksResponse.setResult("confirm");
        getBooksResponse.setBooks(books);
        return getBooksResponse;
    }

    int rand(int min, int max)
    {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

    @PostMapping("/getrecommendedbooks")
    public GetBooksResponse getRecommendedBooks()
    {
        GetBooksResponse getBooksResponse = new GetBooksResponse();
        getBooksResponse.setOperation("getrecommendedbooks");

        //Загружаем все книги которые есть
        List<Book> allBooks = bookDao.getAllBooks();

        List<Book> recBooks = new ArrayList<>();
        for (int i=0; i<3; i++)
        {
            int num = rand(0,allBooks.size());
            Book book = allBooks.get(num);
            allBooks.remove(num);
            recBooks.add(book);
        }
        getBooksResponse.setResult("confirm");
        getBooksResponse.setBooks(recBooks);

        return getBooksResponse;
    }

    @PostMapping("/checkbookquantity")
    public String checkBookQuantity(@RequestBody CheckBookQuantityRequest checkBookQuantityRequest)
    {
        boolean result = bookDao.checkBookQuantity(checkBookQuantityRequest.getBook_id(), checkBookQuantityRequest.getCount());
        CheckBookQuantityResponse response = new CheckBookQuantityResponse(result);
        return response.toString();
    }

    @PostMapping("/dropbook")
    public DropBookResponse dropBook(@RequestBody DropBookRequest dropBookRequest)
    {
        DropBookResponse response = new DropBookResponse();
        String result = bookDao.dropBook(dropBookRequest.getId(), dropBookRequest.getCount());
        response.setResult(result);
        return response;
    }
}
