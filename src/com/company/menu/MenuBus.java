package com.company.menu;

import com.company.tableDB.Bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.tableDB.Bus.*;
import static com.company.menu.MenuBusPrint.menuBusPrint;
import static com.company.menu.MenuRoot.rootMenu;


public class MenuBus {

    public static final int INFO = 1;
    public static final int ADD = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int BACK = 0;

    public static void menuBus() throws SQLException, InterruptedException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:car_park.db");
        Statement statement = connection.createStatement();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuBusPrint();
            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();

            switch (menu) {


                case INFO:
                    ArrayList<Bus> buses = busSelectDB(statement);
                    showBuses(buses);
                    break;


                case ADD:
                    busInsertDB(connection, createBusConsole());
                    System.out.println("Автобус успешно добавлен.");
                    break;


                case UPDATE:
                    System.out.println("Введите ID автобуса:");
                    int id;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    busUpdateDB(id, connection, createBusConsole());
                    System.out.println("Автобус успешно изменен.");
                    break;


                case DELETE:
                    System.out.println("Введите ID автобуса:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    busDeleteDB(id, connection);
                    System.out.println("Автобус успешно удален.");
                    break;


                case BACK:
                    rootMenu();
                    break;


                default:
                    System.out.println("Выбран неверный пункт меню. Повторите!");
                    break;
            }
        }
    }
}
