package DAOs;

import DbManager.MyException;
import Models.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO <T>{


    protected Connection conexion;



    public abstract void actualizar(String columna ,Object objeto, int id) throws SQLException, MyException;
    public abstract void eliminar (int objeto) throws SQLException, MyException;
    public abstract void insertar (T objeto) throws SQLException, MyException;
    public abstract List<T> mostrarTodo() throws SQLException, MyException;
    public abstract T mostrar(int id) throws SQLException, MyException;
    public abstract T convertir(ResultSet rs) throws SQLException;

    protected void meterDatos(PreparedStatement stmt, Actor objeto, int numero1, int numero2, int numero3, int numero4) throws SQLException {
        stmt.setInt(numero1, objeto.getActor_id());
        stmt.setString(numero2, objeto.getFirst_name());
        stmt.setString(numero3, objeto.getLast_name());
        stmt.setDate(numero4,objeto.getLast_update());
    }
    protected void cerrarEstado(PreparedStatement stmt, ResultSet rs) throws SQLException{
        if (stmt != null){
            stmt.close();
        }
        if (rs != null){
            rs.close();
        }
    }
    protected void cerrarEstado(PreparedStatement stmt) throws SQLException{
        if (stmt != null){
            stmt.close();
        }

    }





}