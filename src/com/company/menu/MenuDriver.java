package com.company.menu;

import com.company.tableDB.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.tableDB.Driver.*;
import static com.company.tableDB.Driver.driverDeleteDB;
import static com.company.tableDB.Driver.driverUpdateDB;
import static com.company.menu.MenuDriverPrint.menuDriverPrint;
import static com.company.menu.MenuRoot.rootMenu;

public class MenuDriver {

    public static final int INFO = 1;
    public static final int ADD = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int BACK = 0;

    public static void menuDriver() throws SQLException, InterruptedException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:car_park.db");
        Statement statement = connection.createStatement();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuDriverPrint();

            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();
            switch (menu) {


                case INFO:
                    ArrayList<Driver> drivers = driverSelectDB(statement);
                    showDrivers(drivers);
                    break;



                case ADD:
                    driverInsertDB(connection, createDriverConsole());
                    System.out.println("Водитель успешно добавлен.");
                    break;


                case UPDATE:
                    System.out.println("Введите ID водителя:");
                    int id;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    driverUpdateDB(id, connection, createDriverConsole());
                    System.out.println("Водитель успешно изменен.");
                    break;



                case DELETE:
                    System.out.println("Введите ID водителя:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    driverDeleteDB(id, connection);
                    System.out.println("Водитель успешно удален.");
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
