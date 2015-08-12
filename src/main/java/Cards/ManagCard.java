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

    /*public ArrayList<Object> getCardForDisplay(List<List<Card>> listOfCollections, int countForFirstBasket, int countForSecondBasket, int countForThirdBasket) {
        ArrayList<Object> result = new ArrayList<Object>();
        int indOfBasketInArrays=0;
        int lenghtCardArray = listOfCollections.get(indOfBasketInArrays).size();
        int indOfCard=0;

        countForFirstBasket++;

        if (countForFirstBasket>5){

            countForSecondBasket++;

            if (countForSecondBasket>2){
//                need return Card from 3th basket
                indOfBasketInArrays = 2;
                // if Basket3 is empty, need return Card from Basket2
                if (listOfCollections.get(indOfBasketInArrays).isEmpty()){
                    indOfBasketInArrays = 1;
                }
                countForSecondBasket=0;
                countForFirstBasket=0;
            }
            else //need return Card from Basket2
            {
                indOfBasketInArrays = 1;
                // if Basket2 is empty, need return Card from Basket1
                if (listOfCollections.get(indOfBasketInArrays).isEmpty()){
                    indOfBasketInArrays = 0;
                }
                countForFirstBasket = 0;
            }
        }
        else // need return Card from Basked1
        {
            indOfBasketInArrays = 0;
            // if Basket1 is empty, need return Card from Basket2 or Basket3
            if (listOfCollections.get(indOfBasketInArrays).isEmpty()){
                indOfBasketInArrays = 1;
                if (listOfCollections.get(indOfBasketInArrays).isEmpty()){
                    indOfBasketInArrays = 2;
                }

            }

        }
//      get Basket
        lenghtCardArray =  listOfCollections.get(indOfBasketInArrays).size();
//      get random Card from this basket
        indOfCard = (int)(Math.random() * lenghtCardArray);

        try
        {
            File file = new File("D:\\testID.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Basket = " + indOfBasketInArrays + ":");

            fileWriter.write(listOfCollections.get(indOfBasketInArrays).get(indOfCard).getFront() + "|");
            fileWriter.write("random = " + indOfCard + ":__");
            fileWriter.write("lenght2 = " + listOfCollections.get(1).size());
            fileWriter.write("__ lenght3 = " + listOfCollections.get(2).size());
            fileWriter.close();
        }
        catch (Exception e1) {	}

        result.add(listOfCollections.get(indOfBasketInArrays).get(indOfCard));
        result.add(countForFirstBasket);
        result.add(countForSecondBasket);
        result.add(countForThirdBasket);
        return result;
    }*/


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
}
