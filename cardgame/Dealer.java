package cardgame;

import java.util.ArrayList;

public class Dealer {
    
    private static Dealer onlyObject = null;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Dealer(){

    }

    public static Dealer getInstance(){
        if (onlyObject == null){
            onlyObject = new Dealer();
        }
        return onlyObject;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
