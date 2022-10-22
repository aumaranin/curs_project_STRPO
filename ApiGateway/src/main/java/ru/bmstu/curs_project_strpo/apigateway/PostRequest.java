package ru.bmstu.curs_project_strpo.apigateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest
{
    public static String postRequest(String urlParam, String json) throws IOException
    {
        String result;

        //Отправка запроса
        URL url = new URL(urlParam);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream())
        {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //получение ответа
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8")))
        {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //System.out.println(response.toString());
            result = response.toString();
        }

        return result;
    }
}
