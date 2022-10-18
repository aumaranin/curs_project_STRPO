package ru.bmstu.curs_project_strpo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("Мини-консоль 3");
        while (flag)
        {
            System.out.println("\nВведите команду: ");
            String line = sc.nextLine();
            switch (line)
            {
                case ("quit"):
                    flag = false;
                    break;
                case ("show_all"):
                    show_all();
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
        System.out.println("Завершение работы");
    }

    static void show_all()
    {
        try
        {
            URL obj = new URL("http://housestorems:8080/show_all");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "MiniConsole3");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            String jsonInputString = "{\"operation\": \"sub\", \"x\": \"15\", \"y\": \"5\"}";
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("operation", "sub");
            params.put("x", "100");
            params.put("y", "39");

            //ObjectMapper mapper = new ObjectMapper();
            //String jsonResult = mapper.writerWithDefaultPrettyPrinter()
            //        .writeValueAsString(params);
            String res = params.toString();



            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            //os.write(POST_PARAMS.getBytes());
            os.write(params.toString().getBytes());
            //os.write(jsonInputString.getBytes());
            //os.write(jsonResult.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
