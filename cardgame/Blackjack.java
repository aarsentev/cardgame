package cardgame;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        System.out.println("Welcome to our version of Blackjack!");
        System.out.println("Our version has only one mode, where is dealer plays against only one player");

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = input.nextLine();
        GameEngine.getPlayer().setName(name);

        GameEngine.play();
        boolean again = GameEngine.restart();

        while(again){
            GameEngine.play();
            again = GameEngine.restart();
        }
    }
}
