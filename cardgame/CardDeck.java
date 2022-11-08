/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardgame;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck
{
    private static ArrayList<Card> usedCards = new ArrayList<Card>();
    private static CardDeck onlyObject = null;

    public static CardDeck getInstance(){
        if (onlyObject == null){
            onlyObject = new CardDeck();
        }
        return onlyObject;
    }

    private CardDeck(){

    }

    public static Card generateCard(){
        Random random = new Random();
        boolean unique = false;
        Card newCard = null;
        while (!unique){
            Value value = Value.values()[random.nextInt(13)];
            Suit suit = Suit.values()[random.nextInt(4)];
            newCard = new Card(value, suit);
            unique = checkIfCardIsAlreadyUsed(newCard);
        }
        usedCards.add(newCard);
        return newCard;
    }

    private static boolean checkIfCardIsAlreadyUsed(Card cardToCheck){
        for (Card card: usedCards){
            if ((card.getValue() == cardToCheck.getValue()) && (card.getSuit() == cardToCheck.getSuit())){
                return false;
            }
        }
        return true;
    }

    public static void restartTheDeck(){
        usedCards.clear();
    }

}
