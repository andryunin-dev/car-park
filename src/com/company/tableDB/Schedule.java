package com.company.tableDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.tableDB.Bus.busSelectDB;
import static com.company.tableDB.Bus.showBuses;
import static com.company.tableDB.Driver.driverSelectDB;
import static com.company.tableDB.Driver.showDrivers;
import static com.company.tableDB.Route.routeSelectDB;
import static com.company.tableDB.Route.showRoutes;

public class Schedule {

    private int id_schedule;
    private int id_route;
    private int id_driver;
    private int id_bus;
    private int sold_tickets;

    public Schedule(int id_schedule, int id_route, int id_driver, int id_bus, int sold_tickets) {
        this.id_schedule = id_schedule;
        this.id_route = id_route;
        this.id_driver = id_driver;
        this.id_bus = id_bus;
        this.sold_tickets = sold_tickets;
    }

    public Schedule() {
    }

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public int getId_route() {
        return id_route;
    }

    public void setId_route(int id_route) {
        this.id_route = id_route;
    }

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public int getSold_tickets() {
        return sold_tickets;
    }

    public void setSold_tickets(int sold_tickets) {
        this.sold_tickets = sold_tickets;
    }


    public static Schedule createScheduleConsole() throws SQLException, InterruptedException {
        int id_schedule = 0;
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:car_park.db");
        Statement statement = connection.createStatement();

        int id_route;
        do {
            ArrayList<Route> routes = routeSelectDB(statement);
            showRoutes(routes);
            System.out.println("Введите ID маршрута:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            id_route = scanner.nextInt();
        } while (id_route < 0);



        int id_driver;
        do {
            ArrayList<Driver> drivers = driverSelectDB(statement);
            showDrivers(drivers);
            System.out.println("Введите ID водителя:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            id_driver = scanner.nextInt();
        } while (id_driver < 0);



        int id_bus;
        do {
            ArrayList<Bus> buses = busSelectDB(statement);
            showBuses(buses);
            System.out.println("Введите ID автобуса:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            id_bus = scanner.nextInt();
        } while (id_bus < 0);



        int sold_tickets;
        do {
            System.out.println("Введите количество проданных билетов:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            sold_tickets = scanner.nextInt();
        } while (sold_tickets < 0);



        Schedule schedule = new Schedule(id_schedule, id_route, id_driver, id_bus, sold_tickets);
        return schedule;
    }

    //Выборка(просмотр) из БД
    public static ArrayList<Schedule> scheduleSelectDB(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select id_schedule, id_route, id_driver, id_bus, sold_tickets from schedules");
        ArrayList<Schedule> schedules = new ArrayList<>();
        while (resultSet.next()) {
            int id_schedule = resultSet.getInt(1);
            int id_route = resultSet.getInt(2);
            int id_driver = resultSet.getInt(3);
            int id_bus = resultSet.getInt(4);
            int sold_tickets = resultSet.getInt(5);

            Schedule schedule = new Schedule(id_schedule, id_route, id_driver, id_bus, sold_tickets);
            schedules.add(schedule);
        }
        return schedules;
    }

    //Показать все
    public static void showSchedules(ArrayList<Schedule> schedules) throws InterruptedException {
        System.out.printf("+--------+------------------------+----------------------+----------------------+----------+\n");
        System.out.printf("| %6s | %22s | %20s | %20s | %8s |\n", "РЕЙС", "МАРШРУТ", "ВОДИТЕЛЬ", "АВТОБУС", "БИЛЕТОВ");
        System.out.printf("+--------+------------------------+----------------------+----------------------+----------+\n");
        for (Schedule s : schedules) {
            System.out.println(s);
            Thread.sleep(30);
        }
        System.out.printf("+--------+------------------------+----------------------+----------------------+----------+\n\n\n");
    }

    @Override
    public String toString() {
        return String.format("| %6d | %22d | %20s | %20d | %8d |", id_schedule, id_route, id_driver, id_bus, sold_tickets);
    }

    //Вставка в БД
    public static void scheduleInsertDB(Connection connection, Schedule schedule) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into schedules (id_route, id_driver, id_bus, sold_tickets) values (?,?,?,?)");
        preparedStatement.setInt(1, schedule.id_route);
        preparedStatement.setInt(2, schedule.id_driver);
        preparedStatement.setInt(3, schedule.id_bus);
        preparedStatement.setInt(4, schedule.sold_tickets);

        preparedStatement.executeUpdate();
    }


    //Изменение в БД
    public static void scheduleUpdateDB(int id_schedule, Connection connection, Schedule schedule) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update schedules set id_route=?, id_driver=?, id_bus=?, sold_tickets=? where id_schedule=?");
        preparedStatement.setInt(1, schedule.id_route);
        preparedStatement.setInt(2, schedule.id_driver);
        preparedStatement.setInt(3, schedule.id_bus);
        preparedStatement.setInt(4, schedule.sold_tickets);
        preparedStatement.setInt(5, id_schedule);
        preparedStatement.executeUpdate();
    }


    //Удаление в БД
    public static void scheduleDeleteDB(int id_schedule, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from schedules where id_schedule=?");
        preparedStatement.setInt(1, id_schedule);
        preparedStatement.executeUpdate();
    }



}
