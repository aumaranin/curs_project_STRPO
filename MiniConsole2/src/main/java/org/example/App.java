package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.net.URL;



public class App
{
    private static final String USER_AGENT = "Mini-console1";
    private static final String GET_URL = "http://192.168.1.44:8080/add/10/50";

    //private static final String POST_URL = "http://192.168.1.44:8080/sub";
    private static final String POST_URL = "http://localhost:8080/sub";

    private static final String POST_PARAMS = "operation=sub&x=10&y=10";

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("Мини-консоль");
        while (flag)
        {
            System.out.println("\nВведите команду: ");
            String line = sc.nextLine();
            switch (line)
            {
                case ("quit"):
                {
                    flag = false;
                    break;
                }

                case ("send get"):
                    sendGET();
                    break;

                case ("send post"):
                    sendPOST();
                    break;
            }
        }
        System.out.println("Завершение работы");
    }

    static void sendGET() throws IOException
    {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
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
        }
    }

    static void sendPOST() throws IOException
    {
        URL obj = new URL(POST_URL);
        /*
        Map<String, String> params = new LinkedHashMap<>();
        params.put("operation", "sub");
        params.put("x", "10");
        params.put("y", "9");
        */
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        String jsonInputString = "{\"operation\": \"sub\", \"x\": \"15\", \"y\": \"5\"}";
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("operation", "sub");
        params.put("x", "100");
        params.put("y", "39");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(params);
        //String res = params.toString();



        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        //os.write(POST_PARAMS.getBytes());
        //os.write(params.toString().getBytes());
        //os.write(jsonInputString.getBytes());
        os.write(jsonResult.getBytes());
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

    }

}
