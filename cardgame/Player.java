package cardgame;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> cards;
    
    public Player(String name){
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
