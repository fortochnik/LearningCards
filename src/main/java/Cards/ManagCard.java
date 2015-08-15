package Cards;

import DataBase.DBData;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mipan on 25.05.2015.
 */
public class ManagCard {
    private static final int MAX = 3;
    private static final int MIN = 1;

    public static int UpBasket(int indexBascket){
        if (indexBascket<MAX){
            indexBascket++;
        }
        return indexBascket;
    }
    public static int DownBasket(int indexBascket){
        if (indexBascket>MIN){
            indexBascket--;
        }
        return indexBascket;
    }


    public int getNumberOfBasket(int countFirstBasket, int countSecondBasket, int amountCardInFirstBasket, int amountCardInSecondBasket, int amountCardInThidBasket) {
        int numberOfBasket =0;

        if(countFirstBasket>5 && countSecondBasket>2){
            numberOfBasket = 3;
        }

        if(countFirstBasket>5 && countSecondBasket<=2){
            numberOfBasket = 2;
        }

        if(countFirstBasket<=5){
            numberOfBasket = 1;
        }

        if( numberOfBasket==1 && amountCardInFirstBasket==0){
            numberOfBasket = 2;
        }
        if( numberOfBasket==2 && amountCardInSecondBasket==0){
            numberOfBasket = 3;
        }

        if( numberOfBasket==3 && amountCardInThidBasket==0){
            numberOfBasket = 2;
        }

        if( numberOfBasket==2 && amountCardInSecondBasket==0){
            numberOfBasket = 1;
        }

        if( amountCardInFirstBasket==2 && amountCardInSecondBasket==0){
            numberOfBasket = 3;
        }

        return numberOfBasket;
    }

    public void setBasketAndStatistic(int idCardInDB, int indexBascket, int Statistic, boolean kindOfStatistic) {
        DBData DBData = new DBData();
        DBData.setBasketAndStatistic(idCardInDB, indexBascket, Statistic, kindOfStatistic);
    }

    public Card updateBackForDisplay(Card card) {
        int lengthCoefficient = 60;
        int lengthAddSubTag = 4;
        String back = card.getBack();

        int length = (int)Math.abs(back.length() / lengthCoefficient);//back.length()/100;
        for (int i = 1; i <= length; i++) {
            int indOfSpace = back.indexOf(" ", i*lengthCoefficient - (6 + lengthAddSubTag*i));
            String subBack = back.substring(0, indOfSpace) + "<br>" + back.substring(indOfSpace+1);
            back = subBack;
        }
        card.setBack(back);
        return card;
    }
}
