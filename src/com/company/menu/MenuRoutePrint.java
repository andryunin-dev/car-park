package com.company.menu;

public class MenuRoutePrint {

    public static void menuRoutePrint() {
        System.out.print("\u001B[34m");
        System.out.println("+------------------------------------------------------------------------------------------+");
        System.out.println("|- - - - - - - - - - - - - - - - - - - - -= МАРШРУТЫ =- - - - - - - - - - - - - - - - - - -|");
        System.out.println("+------------------+------------------+------------------+-----------------+---------------+");
        System.out.println("|   1 - Просмотр   |   2 - Добавить   |   3 - Изменить   |   4 - Удалить   |   0 - Назад   |");
        System.out.println("+------------------+------------------+------------------+-----------------+---------------+");
        System.out.print("\u001B[39m");
    }
}