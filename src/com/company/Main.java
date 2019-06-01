package com.company;

import java.sql.SQLException;

import static com.company.menu.MenuRoot.rootMenu;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        //Загрузка драйвера
        Class.forName("org.sqlite.JDBC");

        //Загрузка основного меню
        rootMenu();
    }
}
