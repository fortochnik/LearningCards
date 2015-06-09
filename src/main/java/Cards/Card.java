package Cards;/**
 * Created by mipan on 02.05.2015.
 */
public class Card {
    public String front = null;
    public String back = null;
    public int bascket = 0;
    public int okValue = 0;
    public int failValue = 0;
    public int listCollection = 0;

    public Card(String front, String back, int bascket, int okValue, int failValue, int listCollection) {
        this.back = back;
        this.front = front;
        this.bascket = bascket;
        this.okValue = okValue;
        this.failValue = failValue;
        this.listCollection = listCollection;
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
        return bascket;
    }

    public void setBasket(int bascket) {
        this.bascket = bascket;
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
}
