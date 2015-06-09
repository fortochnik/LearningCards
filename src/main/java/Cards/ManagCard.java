package Cards;/**
 * Created by mipan on 25.05.2015.
 */
public class ManagCard {
    private static final int MAX = 3;
    private static final int MIN = 1;

    public static int UpBascket(int indexBascket){
        if (indexBascket<MAX){
            indexBascket++;
        }
        return indexBascket;
    }
    public static int DownBascket(int indexBascket){
        if (indexBascket>MIN){
            indexBascket--;
        }
        return indexBascket;
    }
}
