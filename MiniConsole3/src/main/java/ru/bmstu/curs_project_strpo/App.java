package ru.bmstu.curs_project_strpo;

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
                default:
                    System.out.println("Неизвестная команда");
            }
        }
        System.out.println("Завершение работы");
    }
}
