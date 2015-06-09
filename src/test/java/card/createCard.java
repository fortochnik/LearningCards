package card;


import Cards.Card;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class createCard {
    Card card;

    @BeforeClass
    public void createCardTest(){
        card = new Card("front", "back", 2, 8, 15, 1);
    }

    @Test
    public void checkFront(){
        assertThat(card.getFront(), is("front"));
    }

    @Test
    public void checkBack(){
        assertThat(card.getBack(), is("back"));
    }


    @Test
    public void checkBascket(){
        assertThat(card.getBasket(), is(2));
    }

    @Test
    public void checkOkValue(){
        assertThat(card.getOkValue(), is(8));
    }

    @Test
    public void checkFailValue(){
        assertThat(card.getFailValue(), is(15));
    }
    @Test
    public void checklistCollection(){
        assertThat(card.getListCollection(), is(1));
    }
}
