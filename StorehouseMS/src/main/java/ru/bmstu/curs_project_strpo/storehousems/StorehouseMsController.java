package ru.bmstu.curs_project_strpo.storehousems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.storehousems.test.TestResponse;

import java.util.*;

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

    //Старая служба рекомендаций. Рекомендует просто 3 случайных книги.
    /*
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
     */

    //Новая УМНАЯ служба рекомендаций, анализирующая историю покупок покупателя.
    @PostMapping("/getrecommendedbooks")
    public GetBooksResponse getRecommendedBooks(@RequestBody GetRecommendedBooksRequest getRecommendedBooksRequest)
    {
        GetBooksResponse getBooksResponse = new GetBooksResponse();
        getBooksResponse.setOperation("getrecommendedbooks");

        //Загружаем все книги которые есть
        List<Book> allBooks = bookDao.getAllBooks();

        //загружаем историю покупок пользователя
        String getHistoryRequest = "{\n" + "  \"operation\": \"getrecommendedbooks\",\n" + "  \"person_id\": \"" + getRecommendedBooksRequest.getPerson_id() + "\"\n" + "}";
        String historyResponse = PostRequest.postRequest(StorehouseMsApplication.properties.getHistoryMsURL() + "gethistory", getHistoryRequest);
        //десериализация из JSON в ассоциативный массив
        Map<String, Object> historyResponseMap = Deserialization.deserializeJson(historyResponse);
        List<Map<String, Object>> histories = (List<Map<String, Object>>)historyResponseMap.get("history");

        //если история покупок пуста - предлагаем 3 случайные книги
        if (histories == null || histories.isEmpty())
        {
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

        HashSet<String> authors = new HashSet<>();
        HashSet<Integer> readedBooksId = new HashSet<>();
        for (Map<String, Object> item: histories)
        {
            //выписываем всех уникальных авторов в множество authors
            String author = (String)item.get("author");
            authors.add(author);

            //выписываем все уникальные книги в множество readedBooksId
            Integer book_id = (Integer)item.get("book_id");
            readedBooksId.add(book_id);
        }

        //из всех книг вычитаем книги, которые он уже читал
        List<Book> shortedBookList = new ArrayList<>(allBooks);
        for (Book book : allBooks)
        {
            if (readedBooksId.contains(book.getId()))
                shortedBookList.remove(book);
        }

        //из оставшихся книг заполняем новый массив с авторами, которых читал покупатель
        List<Book> recBooks = new ArrayList<>();
        for (Book book : shortedBookList)
        {
            if (authors.contains(book.getAuthor()))
                recBooks.add(book);
        }

        //если подходящих книг не найдено - выборка трех случайных книг
        if (recBooks==null || recBooks.isEmpty())
        {
            for (int i = 0; i < 3; i++)
            {
                int num = rand(0, shortedBookList.size());
                Book book = shortedBookList.get(num);
                shortedBookList.remove(num);
                recBooks.add(book);
            }
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
