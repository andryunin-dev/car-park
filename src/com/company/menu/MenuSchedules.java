package com.company.menu;

import com.company.tableDB.Schedule;
import com.company.tableDB.Schedules_view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static com.company.menu.MenuRoot.rootMenu;
import static com.company.menu.MenuSchedulesPrint.menuSchedulesPrint;
import static com.company.tableDB.Bus.createBusConsole;
import static com.company.tableDB.Schedule.*;
import static com.company.tableDB.Schedules_view.schedules_viewSelectDB;
import static com.company.tableDB.Schedules_view.showSchedules_view;

public class MenuSchedules {

    public static final int INFO = 1;
    public static final int ADD = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int BACK = 0;

    public static void menuSchedules() throws SQLException, InterruptedException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:car_park.db");
        Statement statement = connection.createStatement();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuSchedulesPrint();
            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();

            switch (menu) {


                case INFO:
                    ArrayList<Schedules_view> schedules_views  = schedules_viewSelectDB(statement);
                    showSchedules_view(schedules_views);
                    break;


                case ADD:
                    scheduleInsertDB(connection, createScheduleConsole());
                    System.out.println("Рейс успешно добавлен.");
                    break;


                case UPDATE:
                    System.out.println("Введите ID рейса:");
                    int id;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    scheduleUpdateDB(id, connection, createScheduleConsole());
                    System.out.println("Рейс успешно изменен.");
                    break;


                case DELETE:
                    System.out.println("Введите ID рейса:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Неверный формат. Повторите!");
                        scanner.next();
                    }
                    id = scanner.nextInt();
                    scheduleDeleteDB(id, connection);
                    System.out.println("Рейс успешно удален.");
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
