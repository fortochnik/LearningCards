package DataBase;

import Cards.Card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mipan on 02.05.2015.
 */
public class DBData {
    DBConnection DB = new DBConnection();
    private static Connection connect;
    private Statement statement = null;
    private ResultSet resultSet = null;

    List<String> dataList = new ArrayList<String>();
    List<String> cardsFrontList = new ArrayList<String>();
    List<Card> cardsList = new ArrayList<Card>();

    public List<String> getListCollection(){
        connect = DB.getConnection();
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from listcollection.listcollection");
            while (resultSet.next()) {
                // Сохраняем всё в список
                dataList.add(resultSet.getString("nameCollection"));
            }
            closeResultSet();
            DB.closeDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
     return dataList;
    }

    private void closeResultSet() throws SQLException {
        resultSet.close();
    }

    public List<Card> getCardsCollection(String nameCollection) {
        connect = DB.getConnection();
        try {
            statement = connect.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM listcollection.cards inner join listcollection.listcollection \n" +
                    "ON cards.listCollection_id = listcollection.id where listcollection.nameCollection=\"" + nameCollection + '\"');
            while (resultSet.next()) {
                Card card = new Card();
                card.setFront(resultSet.getString("Front"));
                card.setBack(resultSet.getString("Back"));
                card.setBasket(resultSet.getInt("Basket"));
                card.setOkValue(resultSet.getInt("ok"));
                card.setFailValue(resultSet.getInt("fail"));
                card.setListCollection(resultSet.getInt("listCollection_id"));

                cardsList.add(card);
            }
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardsList;
    }
}
