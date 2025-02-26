package Menu;

import DAOs.DAOActores;
import Models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DbManager.MyException;
import DAOs.DAO;
import DbManager.DbManager;
import  io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void Menu() throws MyException, SQLException {
        boolean salir = false;

        do {
            DbManager baseDatos = new DbManager();
            baseDatos.connect();
            DAOActores actorDao = new DAOActores(baseDatos);
            System.out.println("hola vamos a gestionar una base de datos");
            System.out.println("para ello elige la opcion que quieras hacer");
            System.out.println("1.- Mostrar 1 Resultado en concreto");
            System.out.println("2.- Mostrar toda la tabla");
            System.out.println("3.- Insertar una fila");
            System.out.println("4.- Eliminar una fila");
            System.out.println("5.- Actualizar una fila");
            System.out.println("6.- Para salir");
            String opcion = scanner.nextLine();


            switch (opcion){

                case "1":
                    System.out.println("has elegido opcion de mostrar un resultado dime que ID te muestro");
                    int id = scanner.nextInt();
                    System.out.println(actorDao.mostrar(id));
                    break;

                case "2":
                    System.out.println("has elegido mostrar toda la tabla");
                    System.out.println(actorDao.mostrarTodo());
                    break;

                case "3":
                    System.out.println("has elegido insertar fila");
                    System.out.println("para ello vamos a crear una Actor nuevo para insertarlo");
                    System.out.println("dime la ID del Actor");
                    int id_actor = scanner.nextInt();
                    System.out.println("dime el nombre del Actor");
                    String actor_name = scanner.nextLine();
                    System.out.println("ahora dime el apellido");
                    String actor_apellido = scanner.nextLine();
                    System.out.println("por ultimo la fecha de ultima actualizacion que es la hora actual");
                    long tiempoactual = System.currentTimeMillis();
                    Date fechaActual = new Date(tiempoactual);
                    Actor actor = new Actor(id_actor, actor_name, actor_apellido, fechaActual);
                    actorDao.insertar(actor);
                    break;

                case "4":
                    System.out.println("has elegido eliminar una fila");
                    System.out.println("para ello dime la id del actor que quieres eliminar");
                    int actor_eliminar = scanner.nextInt();
                    actorDao.eliminar(actor_eliminar);
                    break;

                case "5":
                    System.out.println("Has elegido la opcion de actualizar");
                    System.out.println("para ello dime el nombre de la columna que quieres cambiar");
                    String columna = scanner.nextLine();
                    System.out.println("ahora el valor que quieres introducir");
                    String valor = scanner.nextLine();
                    System.out.println("Te falta solo la ID de la fila que quieres actualizar");
                    int id_actualizar = scanner.nextInt();
                    actorDao.actualizar(columna,valor, id_actualizar);
                    break;

                case "6":
                    baseDatos.disconnect();
                    System.out.println("Adios");
                    System.out.println("Data of Base disconnect");
                    salir = true;
                    break;


            }

        } while(salir != true);


    }
}