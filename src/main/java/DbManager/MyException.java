package DbManager;

import java.sql.SQLException;

public class MyException extends Exception{
    public MyException(SQLException mensaje) throws SQLException {
        super(mensaje);
    }

    public MyException(String mensaje, Throwable causa){
        super(mensaje, causa);

    }

}