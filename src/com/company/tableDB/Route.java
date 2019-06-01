package com.company.tableDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Route {

    private int id_route;
    private int number_route;
    private String name_route;
    private double distance;
    private double ticket_price;

    public Route(int id_route, int number_route, String name_route, double distance, double ticket_price) {
        this.id_route = id_route;
        this.number_route = number_route;
        this.name_route = name_route;
        this.distance = distance;
        this.ticket_price = ticket_price;
    }

    public Route() {
    }

    public int getId_route() {
        return id_route;
    }

    public void setId_route(int id_route) {
        this.id_route = id_route;
    }

    public int getNumber_route() {
        return number_route;
    }

    public void setNumber_route(int number_route) {
        this.number_route = number_route;
    }

    public String getName_route() {
        return name_route;
    }

    public void setName_route(String name_route) {
        this.name_route = name_route;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }


    public static Route createRouteConsole() {
        int id_route = 0;
        Scanner scanner = new Scanner(System.in);


        int number_route;
        do {
            System.out.println("Введите номер маршрута:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            number_route = scanner.nextInt();
        } while (number_route < 0);


        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Введите описание или название машрута:");
        String name_route = scanner2.nextLine();


        double distance;
        do {
            System.out.println("Введите расстояние маршрута:");
            while (!scanner.hasNextDouble()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            distance = scanner.nextDouble();
        } while (distance < 0);


        double ticket_price;
        do {
            System.out.println("Введите цену билета:");
            while (!scanner.hasNextDouble()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            ticket_price = scanner.nextDouble();
        } while (ticket_price < 0);


        Route route = new Route(id_route, number_route, name_route, distance, ticket_price);
        return route;
    }

    //Выборка(просмотр) из БД
    public static ArrayList<Route> routeSelectDB(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select id_route, number_route, name_route, distance, ticket_price from routes");
        ArrayList<Route> routes = new ArrayList<>();
        while (resultSet.next()) {
            int id_route = resultSet.getInt(1);
            int number_route = resultSet.getInt(2);
            String name_route = resultSet.getString(3);
            double distance = resultSet.getDouble(4);
            double ticket_price = resultSet.getDouble(5);

            Route route = new Route(id_route, number_route, name_route, distance, ticket_price);
            routes.add(route);
        }
        return routes;
    }

    //Показать все
    public static void showRoutes(ArrayList<Route> routes) throws InterruptedException {
        System.out.printf("+-----+--------+---------------------------+---------------+---------------+\n");
        System.out.printf("| %3s | %6s | %25s | %13s | %13s |\n", "ID", "НОМЕР", "ОПИСАНИЕ", "РАССТОЯНИЕ", "ЦЕНА БИЛЕТА");
        System.out.printf("+-----+--------+---------------------------+---------------+---------------+\n");
        for (Route r : routes) {
            System.out.println(r);
            Thread.sleep(30);
        }
        System.out.printf("+-----+--------+---------------------------+---------------+---------------+\n\n\n");
    }

    @Override
    public String toString() {
        return String.format("| %3d | %6d | %25s | %13.1f | %13.1f |", id_route, number_route, name_route, distance, ticket_price);
    }

    //Вставка в БД
    public static void routeInsertDB(Connection connection, Route route) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into routes (number_route, name_route, distance, ticket_price) values (?,?,?,?)");
        preparedStatement.setInt(1, route.number_route);
        preparedStatement.setString(2, route.name_route);
        preparedStatement.setDouble(3, route.distance);
        preparedStatement.setDouble(4, route.ticket_price);
        preparedStatement.executeUpdate();
    }


    //Изменение в БД
    public static void routeUpdateDB(int id_route, Connection connection, Route route) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update routes set number_route=?, name_route=?, distance=?, ticket_price=? where id_route=?");
        preparedStatement.setInt(1, route.number_route);
        preparedStatement.setString(2, route.name_route);
        preparedStatement.setDouble(3, route.distance);
        preparedStatement.setDouble(4, route.ticket_price);
        preparedStatement.setInt(5, id_route);
        preparedStatement.executeUpdate();
    }


    //Удаление в БД
    public static void routeDeleteDB(int id_route, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from routes where id_route=?");
        preparedStatement.setInt(1, id_route);
        preparedStatement.executeUpdate();
    }


}
