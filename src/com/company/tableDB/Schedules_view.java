package com.company.tableDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Schedules_view {

    private int id_schedules;
    private String name_route;
    private String fio;
    private String bus_number;
    private String mark;
    private Double ticket_price;
    private int sold_tickets;

    public Schedules_view(int id_schedules, String name_route, String fio, String bus_number, String mark, Double ticket_price, int sold_tickets) {
        this.id_schedules = id_schedules;
        this.name_route = name_route;
        this.fio = fio;
        this.bus_number = bus_number;
        this.mark = mark;
        this.ticket_price = ticket_price;
        this.sold_tickets = sold_tickets;
    }

    public Schedules_view() {
    }

    public int getId_schedules() {
        return id_schedules;
    }

    public void setId_schedules(int id_schedules) {
        this.id_schedules = id_schedules;
    }

    public String getName_route() {
        return name_route;
    }

    public void setName_route(String name_route) {
        this.name_route = name_route;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getSold_tickets() {
        return sold_tickets;
    }

    public void setSold_tickets(int sold_tickets) {
        this.sold_tickets = sold_tickets;
    }


    //Выборка(просмотр) из БД
    public static ArrayList<Schedules_view> schedules_viewSelectDB(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select id_schedule, name_route, FIO, bus_number, mark, ticket_price, sold_tickets from schedules_view");
        ArrayList<Schedules_view> schedules_views = new ArrayList<>();
        while (resultSet.next()) {
            int id_schedule = resultSet.getInt(1);
            String name_route = resultSet.getString(2);
            String fio = resultSet.getString(3);
            String bus_number = resultSet.getString(4);
            String mark = resultSet.getString(5);
            Double ticket_price = resultSet.getDouble(6);
            int sold_tickets = resultSet.getInt(7);


            Schedules_view schedules_view = new Schedules_view(id_schedule, name_route, fio, bus_number, mark, ticket_price, sold_tickets);
            schedules_views.add(schedules_view);
        }
        return schedules_views;
    }

    //Показать все
    public static void showSchedules_view(ArrayList<Schedules_view> schedules_views) throws InterruptedException {
        System.out.printf("+--------+------------------------+----------------------+------------+-----------------+----------+----------+\n");
        System.out.printf("| %6s | %22s | %20s | %10s | %15s | %8s | %8s |\n", "РЕЙС", "МАРШРУТ", "ВОДИТЕЛЬ", "АВТОБУС", "АВТОБУС", "ЦЕНА", "БИЛЕТОВ");
        System.out.printf("+--------+------------------------+----------------------+------------+-----------------+----------+----------+\n");
        for (Schedules_view s : schedules_views) {
            System.out.println(s);
            Thread.sleep(30);
        }
        System.out.printf("+--------+------------------------+----------------------+------------+-----------------+----------+----------+\n\n\n");
    }

    @Override
    public String toString() {
        return String.format("| %6d | %22s | %20s | %10s | %15s | %8.1f | %8d |", id_schedules, name_route, fio, bus_number, mark, ticket_price, sold_tickets);
    }
}
