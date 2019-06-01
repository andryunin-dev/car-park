package com.company.menu;

import com.company.tableDB.Route;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static com.company.menu.MenuRoot.rootMenu;
import static com.company.menu.MenuRoutePrint.menuRoutePrint;
import static com.company.tableDB.Route.*;

public class MenuRoute {

    public static final int INFO = 1;
    public static final int ADD = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int BACK = 0;

    public static void menuRoute() throws SQLException, InterruptedException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:car_park.db");
        Statement statement = connection.createStatement();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuRoutePrint();
            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();

            switch (menu) {


                case INFO:
                    ArrayList<Route> routes = routeSelectDB(statement);
                    showRoutes(routes);
                    break;


                case ADD:
                    routeInsertDB(connection, createRouteConsole());
                    System.out.println("Маршрут успешно добавлен.");
                    break;


                case UPDATE:
                    System.out.println("Введите ID маршрута:");
                    int id;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    routeUpdateDB(id, connection, createRouteConsole());
                    System.out.println("Маршрут успешно изменен.");
                    break;


                case DELETE:
                    System.out.println("Введите ID маршрута:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    routeDeleteDB(id, connection);
                    System.out.println("Маршрут успешно удален.");
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
