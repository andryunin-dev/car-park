package com.company.tableDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {
    private int id_driver;
    private String fio;
    private int age;
    private int experience;

    public Driver(int id_driver, String fio, int age, int experience) {
        this.id_driver = id_driver;
        this.fio = fio;
        this.age = age;
        this.experience = experience;
    }

    public Driver(){}

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public static Driver createDriverConsole() {
        int id_driver = 0;
        Scanner scanner = new Scanner(System.in);



        String fio;
        System.out.println("Введите ФИО водителя в формате (Иванов И.И.):");
        while (true) {
            fio = scanner.nextLine();
            Pattern pattern = Pattern.compile("([А-Я][а-я]+ )([А-Я]\\.){2}"); //Иванов И.И.
            Matcher matcher = pattern.matcher(fio);
            if (matcher.matches()) {
                System.out.println("OK");
                break;
            } else {
                System.out.println("Неверный формат. Повторите!");
            }
        }



        int age;
        do {
            System.out.println("Введите возраст водителя (от 21 до 80):");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            age = scanner.nextInt();
        } while (age <= 21 || age >= 80);



        int experience;
        do {
            System.out.println("Введите стаж водителя (от 3 до 59):");
            while (!scanner.hasNextInt()) {
                System.out.println("Неверный формат. Повторите!");
                scanner.next();
            }
            experience = scanner.nextInt();
        } while (experience <= 3 || experience >= 59 || (age - 18) < experience);



        Driver driver = new Driver(id_driver,fio, age, experience);
        return driver;
    }

    //Выборка из БД
    public static ArrayList<Driver> driverSelectDB(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select id_driver,fio, age, experience from drivers");
        ArrayList<Driver> drivers = new ArrayList<>();
        while (resultSet.next()) {
            int id_driver = resultSet.getInt(1);
            String fio = resultSet.getString(2);
            int age = resultSet.getInt(3);
            int experience = resultSet.getInt(4);

            Driver driver = new Driver(id_driver,fio, age, experience);
            drivers.add(driver);
        }
        return drivers;
    }

    //Показать все
    public static void showDrivers(ArrayList<Driver> drivers) throws InterruptedException {
        System.out.printf("+-----+----------------------+---------+---------+\n");
        System.out.printf("| %3s | %20s | %7s | %7s |\n", "ID", "ФИО", "ВОЗРАСТ", "СТАЖ");
        System.out.printf("+-----+----------------------+---------+---------+\n");
        for (Driver d : drivers) {
            System.out.println(d);
            Thread.sleep(30);
        }
        System.out.printf("+-----+----------------------+---------+---------+\n\n\n");
    }

    @Override
    public String toString() {
        return String.format("| %3d | %20s | %7d | %7d |", id_driver,fio, age, experience);
    }

    //Вставка в БД
    public static void driverInsertDB(Connection connection, Driver driver) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into drivers (fio, age, experience) values (?,?,?)" );
        preparedStatement.setString(1, driver.fio);
        preparedStatement.setInt(2, driver.age);
        preparedStatement.setInt(3, driver.experience);
        preparedStatement.executeUpdate();
    }

    //Изменение в БД
    public static void driverUpdateDB(int id_driver, Connection connection, Driver driver) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update drivers set fio=?, age=?, experience=? where id_driver=?");
        preparedStatement.setString(1, driver.fio);
        preparedStatement.setInt(2, driver.age);
        preparedStatement.setInt(3, driver.experience);
        preparedStatement.setInt(4, id_driver);
        preparedStatement.executeUpdate();
    }

    //Удаление в БД
    public static void driverDeleteDB(int id_driver, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from drivers where id_driver=?");
        preparedStatement.setInt(1, id_driver);
        preparedStatement.executeUpdate();
    }
}
