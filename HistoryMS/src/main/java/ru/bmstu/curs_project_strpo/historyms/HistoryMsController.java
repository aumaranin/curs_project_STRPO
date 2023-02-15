package ru.bmstu.curs_project_strpo.historyms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.curs_project_strpo.historyms.gethistory.GetHistoryRequest;
import ru.bmstu.curs_project_strpo.historyms.gethistory.GetHistoryResponse;
import ru.bmstu.curs_project_strpo.historyms.sethistory.SetHistoryRequest;
import ru.bmstu.curs_project_strpo.historyms.sethistory.SetHistoryResponse;
import ru.bmstu.curs_project_strpo.historyms.test.TestResponse;

@RestController
public class HistoryMsController
{
    private final HistoryNoteDao historyNoteDao;
    @Autowired
    public HistoryMsController(HistoryNoteDao historyNoteDao)
    {
        this.historyNoteDao = historyNoteDao;
    }
    @GetMapping("test")
    public String testGet()
    {
        return "Сервис: CustomerMS\n\tСтатус: работает\n";
    }

    @PostMapping("test")
    public TestResponse testPost()
    {
        return new TestResponse("ok");
    }

    @PostMapping("/gethistory")
    public GetHistoryResponse getHistory(@RequestBody GetHistoryRequest getHistoryRequest)
    {
        GetHistoryResponse responseToApiGateway = historyNoteDao.getHistory(
                getHistoryRequest.getPerson_id()
        );
        return responseToApiGateway;
    }

    @PostMapping("/sethistory")
    public SetHistoryResponse setHistory(@RequestBody SetHistoryRequest setHistoryRequest)
    {
        SetHistoryResponse responseToApiGateway = new SetHistoryResponse();

        String result = null;
        for (Book book: setHistoryRequest.getBooks())
        {
            result = historyNoteDao.setHistoryNote(setHistoryRequest.getPerson_id(), setHistoryRequest.getDate(), book);
            if (!result.equals("confirm"))
            {
                result = "error";
                break;
            }
        }
        responseToApiGateway.setResult(result);
        return responseToApiGateway;
    }


}
