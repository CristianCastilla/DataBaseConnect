package DAOs;
import Models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DbManager.MyException;
import DAOs.DAO;
import DbManager.DbManager;

public class DAOActores extends DAO <Actor> {

    private String columna;
    private static final String MOSTRARTODO = "SELECT * FROM actor";
    private String MOSTRAR = "SELECT * FROM Actor WHERE Actor_id = ?";
    private String ACTUALIZAR = "UPDATE Actor SET " + columna + " = ? WHERE actor_id = ?";
    private String ELIMINAR = "DELETE FROM Actor where actor_id = ?";
    private String INSERTAR ="INSERT INTO Actor (actor_id, first_name, last_name, last_update) VALUES (?,?,?,?)";
    private PreparedStatement stmt = null;



    public DAOActores(DbManager conexion) {
        this.conexion = conexion.getConn();
    }
    @Override
    public void actualizar(String columna, Object objeto, int id) throws SQLException, MyException {
        try {
            String sql ="UPDATE Actor SET " + columna + " = ? WHERE actor_id = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setObject(1, objeto);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            conexion.commit();
        } catch (SQLException error){
            conexion.rollback();
            throw new MyException( "TE HAS EQUIVOCADO ME CAGO EN DIOS",error);
        } finally {
            cerrarEstado(stmt);
        }
    }

    @Override
    public void eliminar(int objeto) throws SQLException, MyException {
        try {
            String sqlEliminarRelacion = "DELETE FROM film_actor WHERE actor_id = ?";
            PreparedStatement stmtEliminarRelacion = conexion.prepareStatement(sqlEliminarRelacion);
            stmtEliminarRelacion.setInt(1, objeto);
            stmtEliminarRelacion.executeUpdate();
            String sql = "DELETE FROM Actor WHERE actor_id = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, objeto);
            stmt.executeUpdate();
            conexion.commit();

        }catch (SQLException error){
            conexion.rollback();
            throw new MyException("ha dado error al eliminar", error);

        } finally {
            cerrarEstado(stmt);
        }

    }

    @Override
    public void insertar(Actor objeto) throws SQLException, MyException {
        PreparedStatement stmt = null;
        try {
            String sql = INSERTAR;
            stmt = conexion.prepareStatement(sql);
            meterDatos(stmt, objeto, 1,2,3,4);
            stmt.executeUpdate();
            conexion.commit();
        } catch (SQLException error){
            conexion.rollback();
            throw new MyException("QUE POLLAS ES",error);
        } finally {
            cerrarEstado(stmt);
        }

    }








    @Override
    public List<Actor> mostrarTodo () throws SQLException, MyException {
        List<Actor> actores = new ArrayList<>();
        try {
            String sql = MOSTRARTODO;
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                actores.add(convertir(rs));


            }
            return actores;
        } catch (SQLException e){
            throw new MyException("no", e);

        }


    }

    @Override
    public Actor mostrar(int id) throws SQLException, MyException{
        try {
            String sql = MOSTRAR;
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return convertir(rs);
            }
            return null;
        } catch (SQLException error){
            throw new MyException("QUE POLLAS ES",error);
        }

    }

    @Override
    public Actor convertir(ResultSet rs) throws SQLException {
        try {
            int actorID = rs.getInt("actor_id");
            String actor_first_name = rs.getString("first_name");
            String actor_last_name = rs.getString("last_name");
            Date actor_last_update = rs.getDate("last_update");
            Actor actor = new Actor(actorID, actor_first_name, actor_last_name, actor_last_update);
            return actor;
        } catch (SQLException e){
            throw new SQLException("gilipollas");
        }
    }
}