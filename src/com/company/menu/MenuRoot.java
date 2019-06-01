package com.company.menu;

import java.sql.SQLException;
import java.util.Scanner;

import static com.company.menu.MenuAreYouSure.menuAreYouSure;
import static com.company.menu.MenuBus.menuBus;
import static com.company.menu.MenuDriver.menuDriver;
import static com.company.menu.MenuRootPrint.menuRootPrint;
import static com.company.menu.MenuRoute.menuRoute;
import static com.company.menu.MenuSchedules.menuSchedules;

public class MenuRoot {

    public static final int BUS = 1;
    public static final int DRIVER = 2;
    public static final int ROUTE = 3;
    public static final int SCHEDULES = 4;
    public static final int MENU_EXIT = 0;

    public static void rootMenu() throws SQLException, InterruptedException {


        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuRootPrint();
            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();
            switch (menu) {
                case BUS:
                    menuBus();
                    break;
                case DRIVER:
                    menuDriver();
                    break;
                case ROUTE:
                    menuRoute();
                    break;
                case SCHEDULES:
                    menuSchedules();
                    break;
                case MENU_EXIT:
                    menuAreYouSure();
                    break;
                default:
                    System.out.println("Выбран неверный пункт меню. Повторите!");
                    break;

            }
        }

    }
}
