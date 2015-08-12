package Cards;/**
 * Created by mipan on 02.05.2015.
 */
public class Card {
    private String front = null;
    private String back = null;
    private int basket = 0;
    private int okValue = 0;
    private int failValue = 0;
    private int listCollection = 0;

    private int idCardInDB = 0;


    public Card(String front, String back, int basket, int okValue, int failValue, int listCollection) {
        this.back = back;
        this.front = front;
        this.basket = basket;
        this.okValue = okValue;
        this.failValue = failValue;
        this.listCollection = listCollection;
    }
    public Card(String front, String back, int basket, int okValue, int failValue, int listCollection, int idCardInDB) {
        this.back = back;
        this.front = front;
        this.basket = basket;
        this.okValue = okValue;
        this.failValue = failValue;
        this.listCollection = listCollection;
        this.idCardInDB = idCardInDB;
    }

    public Card() {

    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public int getFailValue() {
        return failValue;
    }

    public void setFailValue(int failValue) {
        this.failValue = failValue;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public int getListCollection() {
        return listCollection;
    }

    public void setListCollection(int listCollection) {
        this.listCollection = listCollection;
    }

    public int getOkValue() {
        return okValue;
    }

    public void setOkValue(int okValue) {
        this.okValue = okValue;
    }


    public int getIdCardInDB() {
        return idCardInDB;
    }

    public void setIdCardInDB(int idCardInDB) {
        this.idCardInDB = idCardInDB;
    }
}
