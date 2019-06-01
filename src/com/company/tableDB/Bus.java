package com.company.tableDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bus {

    private int id_bus;
    private String bus_number;
    private String mark;
    private int year;
    private int mileage;

    public Bus(int id_bus, String bus_number, String mark, int year, int mileage) {
        this.id_bus = id_bus;
        this.bus_number = bus_number;
        this.mark = mark;
        this.year = year;
        this.mileage = mileage;
    }

    public Bus() {
    }

    public String getBus_number() {
        return bus_number;
    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }


//    @Override
//    public String toString() {
//        return "Bus{" +
//                "id_bus=" + id_bus +
//                ", bus_number='" + bus_number + '\'' +
//                ", mark='" + mark + '\'' +
//                ", year=" + year +
//                ", mileage=" + mileage +
//                '}';
//    }


    public static Bus createBusConsole() {
        int id_bus = 0;
        Scanner scanner = new Scanner(System.in);


        String bus_number;
        System.out.println("Введите гос. номер автобуса русскими буквами  в формате (а000аа77 или а000аа777):");
        while (true) {
            bus_number = scanner.nextLine();
            Pattern pattern = Pattern.compile("([а,в,е,к,м,н,о,р,с,т,у,х][0-9]{3}" +
                    "[а,в,е,к,м,н,о,р,с,т,у,х]{2})([0-9]{2}|[1,7][0-9]{2})"); //А, В, Е, К, М, Н, О, Р, С, Т, У, Х
            Matcher matcher = pattern.matcher(bus_number);
            if (matcher.matches()) {
                System.out.println("OK");
                break;
            } else {
                System.out.println("Неверный формат. Повторите!");
            }
        }


        System.out.println("Введите марку автобуса:");
        String mark = scanner.nextLine();


        int year;
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        do {
            System.out.println("Введите год выпуска автобуса:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            year = scanner.nextInt();
        } while (year < 1900 || year > nowYear);


        int mileage;
        do {
            System.out.println("Введите пробег автобуса:");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            mileage = scanner.nextInt();
        } while (mileage < 0 || mileage > 9999999);


        Bus bus = new Bus(id_bus, bus_number, mark, year, mileage);
        return bus;
    }

    //Выборка(просмотр) из БД
    public static ArrayList<Bus> busSelectDB(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select id_bus, bus_number, mark, year, mileage from buses");
        ArrayList<Bus> buses = new ArrayList<>();
        while (resultSet.next()) {
            int id_bus = resultSet.getInt(1);
            String bus_number = resultSet.getString(2);
            String mark = resultSet.getString(3);
            int year = resultSet.getInt(4);
            int mileage = resultSet.getInt(5);

            Bus bus = new Bus(id_bus, bus_number, mark, year, mileage);
            buses.add(bus);
        }
        return buses;
    }

    //Показать все
    public static void showBuses(ArrayList<Bus> buses) throws InterruptedException {
        System.out.printf("+-----+------------+------------------+--------+---------+\n");
        System.out.printf("| %3s | %10s | %16s | %6s | %7s |\n", "ID", "НОМЕР", "МАРКА", "ГОД", "ПРОБЕГ");
        System.out.printf("+-----+------------+------------------+--------+---------+\n");
        for (Bus b : buses) {
            System.out.println(b);
            Thread.sleep(30);
        }
        System.out.printf("+-----+------------+------------------+--------+---------+\n\n\n");
    }

    @Override
    public String toString() {
        return String.format("| %3d | %10s | %16s | %6d | %7d |", id_bus, bus_number, mark, year, mileage);
    }

    //Вставка в БД
    public static void busInsertDB(Connection connection, Bus bus) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into buses (bus_number, mark, year, mileage) values (?,?,?,?)");
        preparedStatement.setString(1, bus.bus_number);
        preparedStatement.setString(2, bus.mark);
        preparedStatement.setInt(3, bus.year);
        preparedStatement.setInt(4, bus.mileage);
        preparedStatement.executeUpdate();
    }

    //Изменение в БД
    public static void busUpdateDB(int id_bus, Connection connection, Bus bus) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update buses set bus_number=?, mark=?, year=?, mileage=? where id_bus=?");
        preparedStatement.setString(1, bus.bus_number);
        preparedStatement.setString(2, bus.mark);
        preparedStatement.setInt(3, bus.year);
        preparedStatement.setInt(4, bus.mileage);
        preparedStatement.setInt(5, id_bus);
        preparedStatement.executeUpdate();
    }

    //Удаление в БД
    public static void busDeleteDB(int id_bus, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from buses where id_bus=?");
        preparedStatement.setInt(1, id_bus);
        preparedStatement.executeUpdate();
    }
}
