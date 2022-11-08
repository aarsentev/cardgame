package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
    private static GameEngine onlyObject = null;
    private static Player p1 = new Player("Player");
    private static Dealer dealer = Dealer.getInstance();

    private GameEngine(){
    }

    public static Player getPlayer() {
        return p1;
    }

    public static GameEngine getInstance(){
        if (onlyObject == null){
            onlyObject = new GameEngine();
        }
        return onlyObject;
    }
    
    public static boolean restart(){
        boolean again = false;
        try{
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to play again?\n 1 - Yes \n 2 - No \n Your choice: ");
        
        int answer = input.nextInt();
        switch(answer){
            case 1:
                again = true;
                p1.getCards().clear();
                dealer.getCards().clear();
                break;
            case 2:
                again = false;
                break;
            default:
                System.out.println("Invalid option, closing the program");
                again = false;
                break;
        }
        
        }catch(Exception e){
            System.out.println("exception error");
        }
        return again;
    }
        

    private static void finalise(){
        int playerScore = countTheValue(p1.getCards());
        int dealerScore = countTheValue(dealer.getCards());

        System.out.println("Player's cards:");
        for (Card card : p1.getCards()){
            System.out.println(card.toString());
        }

        System.out.println();

        System.out.println("Dealer's cards");
        for (Card card : dealer.getCards()){
            System.out.println(card.toString());
        }

        if (playerScore > dealerScore && playerScore <= 21){
            System.out.println("Congratulations! You have won!");
        }
        else if (playerScore < dealerScore && dealerScore <= 21){
            System.out.println("Sorry! You have lost!");
        }
        else if (playerScore >= 21 && dealerScore >= 21){
            System.out.println("Even, you are both over 21! That was a bust!");
        }
        else if (playerScore < dealerScore && dealerScore > 21){
            System.out.println("Sorry! You have lost!");
        }
        else{
            System.out.println("Hmmm");
        }

    }
    private static boolean hitOrStand(){
        boolean newCard = true;
        System.out.print(p1.getName() + " decide:\n 1 - Hit\n 2 - Stand?\n Your choice: ");

        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();
        System.out.println();

        switch(choice){
            case 1:
                newCard = true;
                break;
            case 2:
                newCard = false;
                break;
            default:
                System.out.println("Invalid option, automatically hitting");
                newCard = true;
                break;
        }
        return newCard; 
    }

    private static int countTheValue(ArrayList<Card> countCard){
        int returnValue = 0;
        for (int i = 0; i < countCard.size(); i++){
            switch(countCard.get(i).getValue()){
                case TWO:
                    returnValue += 2;
                    break;
                case THREE:
                    returnValue += 3;
                    break;
                case FOUR:
                    returnValue += 4;
                    break;
                case FIVE:
                    returnValue += 5;
                    break;
                case SIX:
                    returnValue += 6;
                    break;
                case SEVEN:
                    returnValue += 7;
                    break;
                case EIGHT:
                    returnValue += 8;
                    break;
                case NINE:
                    returnValue += 9;
                    break;
                case TEN:
                    returnValue += 10;
                    break;
                case JACK:
                    returnValue += 10;
                    break;
                case QUEEN:
                    returnValue += 10;
                    break;
                case KING:
                    returnValue += 10;
                    break;
                case ACE:
                    if (returnValue + 11 > 21){
                        returnValue += 1;
                    }
                    else if(returnValue + 11 <= 21){
                        returnValue += 11;
                    }
                    break;
                default:
                    break;
            }
        }
        return returnValue;
    }

    private static boolean checkForBlackjack(ArrayList<Card> cardsToCheck){
        int checkSum = countTheValue(cardsToCheck);
        return (checkSum == 21);
    }

    private static boolean checkIfContainsBySuit(ArrayList<Card> cards, Suit suit){
        for (Card card: cards){
            if (card.getSuit() == suit){
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfContainsByValue(ArrayList<Card> cards, Value value){
        for (Card card: cards){
            if (card.getValue() == value){
                return true;
            }
        }
        return false;
    }

    public static void play(){
       
        System.out.println("Starting the new round...");
        
      
        //ROUND 1
        System.out.println("=======================================");
        Card dealerCard = CardDeck.generateCard();
        System.out.println("Dealer puts out the first card...\nThe card is " + dealerCard.toString());
        dealer.getCards().add(dealerCard);

        System.out.println("=======================================");
        Card playerCard = CardDeck.generateCard();
        System.out.println("The player receives the first card...\nThe card is " + playerCard.toString());
        p1.getCards().add(playerCard);

        //ROUND 2
        System.out.println("=======================================");
        dealerCard = CardDeck.generateCard();
        dealer.getCards().add(dealerCard);
        System.out.println("The dealer received his second card, the player is not to see the second card");
    
        boolean hasBlackjack = checkForBlackjack(dealer.getCards());
        if (hasBlackjack){
            System.out.println("Too bad! The dealer has blackjack!\nThe dealer's cards:\n");
            for(Card card : dealer.getCards()){
                System.out.println(card.toString());
            }
            System.exit(1);
        }
        else{
            System.out.println("The dealer does not have blackjack");
        }

        System.out.println("=======================================");
        playerCard = CardDeck.generateCard();
        System.out.println("The player receives the second card...\nThe card is " + playerCard.toString());
        p1.getCards().add(playerCard);

        if (checkForBlackjack(p1.getCards())){
            System.out.println("Congratulations! You have blackjack!");
            System.exit(1);
        }

        System.out.println("=======================================");
        boolean hitOrStand = hitOrStand();
        
        while (hitOrStand){
            playerCard = CardDeck.generateCard();
            System.out.println("The player receives the card №" + (p1.getCards().size() + 1) + "...\nThe card is " + playerCard.toString());
            p1.getCards().add(playerCard);
            if (countTheValue(p1.getCards()) <= 21){
                hitOrStand = hitOrStand();
            }
            else{
                System.out.println("Sorry! You are over 21! You lost!");
                hitOrStand = false;
                System.exit(1);
            }
        }

        System.out.println("=======================================");
        System.out.println("The player finished his hitting the cards. Now it is dealer's turn");

        System.out.println("The second card of the dealer is " + dealer.getCards().get(1).toString());
        while(countTheValue(dealer.getCards()) <= 17){
            dealerCard = CardDeck.generateCard();
            System.out.println("The dealer's card №" + (dealer.getCards().size() + 1) + " is " + dealerCard.toString());
            dealer.getCards().add(dealerCard);
            System.out.println();
        }
        
        System.out.println("=======================================");
        finalise();
    }
    
      
}
