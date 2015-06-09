package DataBase;

import java.sql.*;

/**
 * Created by mipan on 02.05.2015.
 */
public class DBConnection {
    private static final String PASSWORD = "root";
    private static final String USER = "root";



    private static Connection connect; //= new DBConnection().getConnect();
    private Statement statement = null;
    private ResultSet resultSet = null;
    public Connection getConnection() {


        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/listcollection?"
                    + "user=" + USER + "&password=" + PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }

    public void closeDBConnection() throws SQLException {
        if (connect != null){
            connect.close();

        }
    }
}
