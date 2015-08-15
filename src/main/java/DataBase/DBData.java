package DataBase;

import Cards.Card;
import Cards.ManagCard;
import exceptions.XMLAddingException;

import java.io.*;
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

    ManagCard ManagCard = new ManagCard();
    DBConnection DB = new DBConnection();
    private static Connection connect;
    private Statement statement = null;
    private ResultSet resultSet = null;


    List<String> cardsFrontList = new ArrayList<String>();
    List<Card> cardsList = new ArrayList<Card>();

    int counteOfUpdRow;

    public List<String> getListCollection(){

        connect = DB.getConnection();
        List<String> dataList = new ArrayList<String>();
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
        if (resultSet!=null) {
            resultSet.close();
        }
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

    public List<Card> getCardsCollection(String nameCollection, int idOfBasket) {
        List<Card> cardsList = new ArrayList<Card>();
        connect = DB.getConnection();
        try {
            statement = connect.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM listcollection.cards inner join listcollection.listcollection \n" +
                    "ON cards.listCollection_id = listcollection.id where listcollection.nameCollection=\"" + nameCollection + "\" and  Basket=" + idOfBasket);
            while (resultSet.next()) {
                Card card = new Card();
                card.setFront(resultSet.getString("Front"));
                card.setBack(resultSet.getString("Back"));
                card.setBasket(resultSet.getInt("Basket"));
                card.setOkValue(resultSet.getInt("ok"));
                card.setFailValue(resultSet.getInt("fail"));
                card.setListCollection(resultSet.getInt("listCollection_id"));
                card.setIdCardInDB(idOfBasket);

                cardsList.add(card);
            }
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardsList;

    }

    public boolean setCardToCollection(String collectionName, String cardFront, String cardBack) {
        if((cardFront.length()==0) || (cardBack.length()==0)){
            return false;
        }

        connect = DB.getConnection();

        try {
            statement = connect.createStatement();

            resultSet = statement.executeQuery("SELECT id FROM listcollection.listcollection " +
                    "WHERE nameCollection=\"" + collectionName + "\";");
            resultSet.next();
            String collectionId = resultSet.getString("id");

            counteOfUpdRow = statement.executeUpdate("insert into listcollection.cards(Front, Back, listCollection_id) " +
                    "values (\"" + cardFront + "\",\"" + cardBack + "\", " + collectionId + ");");
            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
//        if counteOfUpdRow !=0 then DB wasn't update
        if (counteOfUpdRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean setCardToCollection(Card card) throws XMLAddingException {

        connect = DB.getConnection();

        try {
            statement = connect.createStatement();

            counteOfUpdRow = statement.executeUpdate("insert into listcollection.cards(Front, Back, listCollection_id, ok, fail, Basket)  values (\""
                    + card.getFront()+ "\",\"" + card.getBack()+ "\", \"" + card.getListCollection() + "\",\"" + card.getOkValue()
                    + "\",\" " +card.getFailValue() + "\",\"" + card.getBasket() + "\");");
            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
//          todo delete added collection
            throw new XMLAddingException();


//            return false;
        }
//        if counteOfUpdRow !=0 then DB wasn't update
        if (counteOfUpdRow > 0) {
            return true;
        } else {
//          todo delete added collection
            throw new XMLAddingException();
//            return false;
        }

    }

    public int getIdCollection(String collectionName){
        connect = DB.getConnection();
        String collectionId=null;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM listcollection.listcollection " +
                "WHERE nameCollection=\"" + collectionName + "\";");
        resultSet.next();
        collectionId = resultSet.getString("id");

            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return Integer.parseInt(collectionId);
    }

    public boolean createCollection(String collectionName) throws XMLAddingException {
        connect = DB.getConnection();
        try {
            statement = connect.createStatement();
            counteOfUpdRow = statement.executeUpdate("insert into listcollection.listcollection(nameCollection) " +
                    "values (\"" + collectionName + "\");");

            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new XMLAddingException();
//            return false;
        }
        if (counteOfUpdRow > 0) {
            return true;
        } else {
            throw new XMLAddingException();
//            return false;

        }
    }

    public boolean setOkValueToCard (int idCollection, int idCard, int OkValue) throws XMLAddingException {

        return false;
    }

    /*it's strange and bad way use it for Rollback. I know*/
    public boolean deleteCollection (int idCollection) throws XMLAddingException {
        connect = DB.getConnection();
        try {
            statement = connect.createStatement();
            counteOfUpdRow = statement.executeUpdate("delete from listcollection.cards WHERE listCollection_id=" + idCollection + ";");
            counteOfUpdRow = statement.executeUpdate("delete from listcollection.listcollection WHERE id=" + idCollection + ";");

            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new XMLAddingException();
//            return false;
        }
        if (counteOfUpdRow > 0) {
            return true;
        } else {
            throw new XMLAddingException();
//            return false;

        }
//        return true;
    }


    public boolean setDisplayCollection(int idCollection) {
        connect = DB.getConnection();
        int  countOfUpdRow;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from listcollection.display_of_collection");

            if (!resultSet.next()){
                countOfUpdRow = statement.executeUpdate("insert into  listcollection.display_of_collection(id_collection, basketFirstCount, basketSecondCount) values (" + idCollection + ", 0, 0);");
            }
            else{
                countOfUpdRow = statement.executeUpdate("update listcollection.display_of_collection set basketFirstCount=0, basketSecondCount=0, id_collection = " + idCollection + " where id=1");
            }


            statement.close();
            closeResultSet();
            DB.closeDBConnection();
        } catch (SQLException e) {

            return false;
        }
        if (countOfUpdRow > 0) {
            return true;
        } else {
            return false;
        }

    }


    public Card getCardForDisplay() {

        int idCollection = 0;
        int countFirstBasket = 0;
        int countSecondBasket;
        int amountCardInFirstBasket=0;
        int amountCardInSecondBasket=0;
        int amountCardInThirdBasket=0;
        int numberOfBasket = 0;
        int indOfCard = 0;
        connect = DB.getConnection();


        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from listcollection.display_of_collection");
            resultSet.next();
            idCollection = resultSet.getInt("id_collection");
            countFirstBasket = resultSet.getInt("basketFirstCount");
            countSecondBasket = resultSet.getInt("basketSecondCount");



            resultSet = statement.executeQuery("select count(Front) from listcollection.cards WHERE listCollection_id= "
                                                                                                            + idCollection +" and Basket=1;");
            resultSet.next();
            amountCardInFirstBasket = resultSet.getInt(1);

            resultSet = statement.executeQuery("select count(Front) from listcollection.cards WHERE listCollection_id= "
                                                                                                            + idCollection +" and Basket=2;");
            resultSet.next();
            amountCardInSecondBasket = resultSet.getInt(1);

            resultSet = statement.executeQuery("select count(Front) from listcollection.cards WHERE listCollection_id= "+ idCollection +" and Basket=3;");
            resultSet.next();
            amountCardInThirdBasket = resultSet.getInt(1);

            numberOfBasket = ManagCard.getNumberOfBasket(countFirstBasket, countSecondBasket, amountCardInFirstBasket, amountCardInSecondBasket, amountCardInThirdBasket);


            if (numberOfBasket == 1)
            {
                indOfCard = (int)(Math.random() * amountCardInFirstBasket);
                countFirstBasket++;
                statement.executeUpdate("update listcollection.display_of_collection set basketFirstCount=" + countFirstBasket + " where id=1");
            }
            if (numberOfBasket == 2)
            {
                indOfCard = (int)(Math.random() * amountCardInSecondBasket);
                countSecondBasket++;
                statement.executeUpdate("update listcollection.display_of_collection set basketFirstCount=0, basketSecondCount=" + countSecondBasket + " where id=1");

            }
            if (numberOfBasket == 3)
            {
                indOfCard = (int)(Math.random() * amountCardInThirdBasket);
                statement.executeUpdate("update listcollection.display_of_collection set basketFirstCount=0, basketSecondCount=0 where id=1");
            }


        }
        catch (SQLException e){

        }

        Card card = new Card();
        try {
            resultSet = statement.executeQuery("select * from listcollection.cards WHERE listCollection_id= "+ idCollection +" and Basket= "+ numberOfBasket);

            for (int i = 0; i <= indOfCard; i++) {
                resultSet.next();
            }

            card.setFront(resultSet.getString("Front"));
            card.setBack(resultSet.getString("Back"));
            card.setBasket(resultSet.getInt("Basket"));
            card.setOkValue(resultSet.getInt("ok"));
            card.setFailValue(resultSet.getInt("fail"));
            card.setListCollection(resultSet.getInt("listCollection_id"));

            card.setIdCardInDB(resultSet.getInt("id"));


            statement.close();
            closeResultSet();
            DB.closeDBConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(card.getBack().length()>100){
            card = ManagCard.updateBackForDisplay(card);
        }
        return card;
    }

    public void setBasketAndStatistic(int idCardInDB, int indexBasket, int statistic, boolean kindOfStatistic) {
        connect = DB.getConnection();
        
        String typeOfStatisticForSQLQuery;

        if (kindOfStatistic){
            typeOfStatisticForSQLQuery = "ok";
        }
        else{
            typeOfStatisticForSQLQuery = "fail";
        }
        try {
            statement = connect.createStatement();

            statement.executeUpdate("update listcollection.cards set Basket="+ indexBasket +", " + typeOfStatisticForSQLQuery + "=" + statistic + " where id=" + idCardInDB);


            statement.close();
            closeResultSet();
            DB.closeDBConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
