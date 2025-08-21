package data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String Url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER  = "postgres";
    private static final String PASSWORD  = "123456";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection =  DriverManager .getConnection( Url , USER , PASSWORD   );


        }catch ( SQLException e  ){

            //System.out.println(  "error connecting to the sql server  :   " + e.getMessage() );
            //e.printStackTrace();

            handleSqlException( "dataUtils . get connection " ,   e );

        }

        return connection;
    }


    public static void  handleSqlException ( String method , SQLException e ){

        System.out.println( method + e.getMessage() );
        throw new RuntimeException(e );
    }

}
