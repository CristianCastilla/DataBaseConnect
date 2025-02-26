package DbManager;
import  io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String DBPASSWORD = dotenv.get("DBPASSWORD");
    private static final String DBUSER = dotenv.get("DBUSER");
    private static final String DBRUTE = dotenv.get("DBRUTE");
    private Connection conn = null;


    public void connect() throws MyException{
        try {
            conn =DriverManager.getConnection(DBRUTE, DBUSER, DBPASSWORD);
            conn.setAutoCommit(false);
        } catch (SQLException error){
            throw new MyException("error hiejo pute ", error);

        }
    }

    public Connection getConn() {
        return conn;
    }

    public void disconnect () throws MyException{
        try {
            conn.close();
        } catch (SQLException error){
            throw new MyException("No se desconecto weyyy compruebelo" , error);
        }
    }
}