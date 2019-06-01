package com.company.menu;

import java.sql.SQLException;
import java.util.Scanner;

import static com.company.menu.MenuAreYouSurePrint.menuAreYouSurePrint;
import static com.company.menu.MenuRoot.rootMenu;

public class MenuAreYouSure {

    public static final int MENU_EXIT = 1;
    public static final int BACK = 0;


    public static void menuAreYouSure() throws SQLException, InterruptedException {


        while (true) {
            Scanner scanner = new Scanner(System.in);
            menuAreYouSurePrint();
            int menu;
            while (!scanner.hasNextInt()) {
                System.out.println("Выбран неверный пункт меню. Повторите!");
                scanner.next();
            }
            menu = scanner.nextInt();
            switch (menu) {
                case MENU_EXIT:
                    System.exit(0);
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
