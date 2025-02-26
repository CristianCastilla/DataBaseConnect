package org.example;

import DbManager.MyException;
import Menu.Menu;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws MyException, SQLException {
        Menu menu = new Menu();
        menu.Menu();
    }
}
