/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author snehr
 */
import java.util.Scanner;
/**
 * A Connect4 Game.
 *
 * @author Surya Nehra
 * @version 1.0 
 */
public class Game
{
    private Scanner scan = new Scanner(System.in);
    private String player1;
    private String player2;
    private String player;
    private String playagain;
    private Board board;
    public boolean win;
    
    /**
     * Constructor for objects of class Game. 
     */
    public Game()
    {
        Game();    
    }

    /**
     * Constructs the Game
     */
    private void Game()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print('\u000c');
        win = false;
        System.out.println("Player 1, enter your name: ");
        player1 = scan.nextLine();
        System.out.println("Player 2, enter your name: ");
        player2 = scan.nextLine();
        System.out.print('\u000c');
        
        board = new Board();
        do {
            playerMove(true);
            playerMove(false);
        } while(true);

    }

    /**
     * Executes moves and checks for winners.
     */
    private void playerMove(boolean whoseturn)
    {
        if(whoseturn == true) {
            System.out.print(player1 + ", make your move: ");
            player = player1;
        } else {
            System.out.print(player2 + ", make your move: ");
            player = player2;
        }
        char letter = scan.next().toLowerCase().charAt(0);
        if(((letter - 'a') <= 6) && board.check(letter) == true) {
            board.move(letter);
            board.printBoard();
        }
        else{
            playerMove(whoseturn);
        }

        win = board.horizontalWin(letter);
        if(win == true){
            System.out.println(player + " wins!");
            playAgain();
        }
        win = board.VerticalWin(letter);
        if(win == true){
            System.out.println(player + " wins!");
            playAgain();
        }
        win = board.DiagonalWin1(letter);
        if(win == true){
            System.out.println(player + " wins!");
            playAgain();
        }
        win = board.DiagonalWin2(letter);
        if(win == true){
            System.out.println(player + " wins!");
            playAgain();
        }
        win = board.CatsGame(letter);
        if(win == true){
            System.out.println("Cat's Game! No winner.");
            playAgain();
        }
    }

    /**
     * Checks to see if players wish to play again. Exits if they do not.
     */
    private void playAgain()
    {
        System.out.println("Play again? (y/n)");
        Scanner b = new Scanner(System.in);
        String playagain = b.nextLine();
        if (playagain.equals("y")){
            Game();
        }
        else{
            System.exit(0);
        }
    }
} 
