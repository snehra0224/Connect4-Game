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
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board
{
    private char[][] values;
    char move;
    int column;
    boolean flag;

    /**
     * Constructor for objects of class Tester
     * Creates and prints the Connect4 board.
     */
    public Board()
    {
        move = '0';
        column = 0;
        flag = false;
        values = new char[7][6];
        for(int i = 0; i < 7; i++) {    // columns
            for(int j = 0; j < 6; j++) {    // rows
                values[i][j] = ' ';
            }
        }
        printBoard();
    }

    /**
     * A method for printing the board.
     */
    public void printBoard()
    {
        System.out.println('\u000c');
        System.out.print('\t');
        for(char i = 'A'; i <= 'G'; i++) {
            System.out.print("  " + (i) + " ");
        }
        System.out.println();
        System.out.println('\t' + "+---+---+---+---+---+---+---+");
        for(int i = 0; i <= 5; i++) {       //rows
            System.out.print('\t');
            for(int j = 0; j <= 6; j++) {   //columns
                System.out.print("| " + values[j][i] + " ");
            }
            System.out.println("|");
        }
        System.out.println('\t' + "+---+---+---+---+---+---+---+");
    }

    /**
     * Sets row for move.
     */
    public int rowSet(char letter)
    {
        int row = 0;
        for(char i = 'a'; i <= 'g'; i++) {
            if(letter == i) {
                row = i - 'a';
            }
        }
        return row;
    }

    /**
     * Sets column for move.
     */
    public int setColumn(char letter)
    {
        for(char i = 'a'; i < 'f'; i++) {
            for(int j = 5; j > -1; j--) {
                int f = rowSet(letter);
                if(values[f][j] == ' ') {
                    column = j;
                    break;
                }
            }
        }
        return column;
    }

    /**
     * Sets the grid position value to the given token.
     */
    public void move(char letter)
    {
        setTurn();
        values[rowSet(letter)][setColumn(letter)] = move;
    }

    /**
     * Switches the turns.
     */
    private void setTurn()
    {
        if(move == 'O') {
            move = '0';
        }
        else if(move == '0') {
            move = 'O';
        }
    }

    /**
     * Checks for horizontal winning combinations.
     */
    public boolean horizontalWin(char letter)
    {
        flag = false;
        int winCounter = 0;
        int x = rowSet(letter);
        int y = setColumn(letter);

        for(int a = 0; a < 4; a++){    
            if (flag == true)
            {break;}
            for(int b = 0; b < 6; b++){
                if (flag == true)
                {break;}
                winCounter = 0;
                for(int c = a; c < (a+4); c++){
                    flag = false;
                    if (values[c][b] == move)
                    {winCounter++;
                    }
                    if(winCounter > 3){
                        flag = true;
                        break;
                    }
                }
            }   
        }
        return flag;
    }

    /**
     * Checks for vertical winning combinations.
     */
    public boolean VerticalWin(char letter)
    {
        flag = false;
        int winCounter = 0;
        int x = rowSet(letter);
        int y = setColumn(letter);

        for(int a = 0; a < 3; a++){    
            if (flag == true)
            {break;}
            for(int b = 0; b < 7; b++){
                if (flag == true)
                {break;}
                winCounter = 0;
                for(int c = a; c < (a+4); c++){
                    flag = false;
                    if (values[b][c] == move)
                    {winCounter++;
                    }
                    if(winCounter > 3){
                        flag = true;
                        break;
                    }
                }
            }   
        }
        return flag;
    }

    /**
     * Checks for diagonal winning combinations in the forward direction.
     */
    public boolean DiagonalWin1(char letter)
    {
        flag = false;
        int winCounter = 0;
        int x2;
        int y2;
        for(int y = 0; y < 3; y++){    
            if (flag == true)
            {break;}
            for(int x = 0; x < 4; x++){
                if (flag == true)
                {break;}
                winCounter = 0;
                for(x2 = x, y2 = y; 
                (x2 < (x+4)&&(y2 < (y+4))); 
                x2++,y2++)
                {
                    flag = false;
                    if (values[x2][y2] == move)
                    {winCounter++;
                    }
                    if(winCounter > 3){
                        flag = true;
                        break;
                    }
                }
            }   
        }
        return flag;
    }

    /**
     * Checks for diagonal winning combinations in the reverse direction.
     */
    public boolean DiagonalWin2(char letter)
    {
        flag = false;
        int winCounter = 0;
        int x2;
        int y2;
        for(int y = 0; y < 3; y++){    
            if (flag == true)
            {break;}
            for(int x = 6; x > 2; x--){
                if (flag == true)
                {break;}
                winCounter = 0;
                for(x2 = x, y2 = y; 
                (x2 > (x-4)&&(y2 < (y+4))); 
                x2--,y2++)
                {
                    flag = false;
                    if (values[x2][y2] == move)
                    {winCounter++;
                    }
                    if(winCounter > 3){
                        flag = true;
                        break;
                    }
                }
            }   

        }
        return flag;
    }

    /**
     * Checks for a tie.
     */
    public boolean CatsGame(char letter)
    {
        int counter = 0; 
        flag = false;
        for(int j = 0; j <= 5; j++) {   
            for(int i = 0; i <= 6; i++) {
                if(values[i][j] != ' '){
                    counter++;
                }
            }
        }
        if(counter == 42){
            flag = true;
        }
        return flag;
    }

    /**
     * Checks to see if a move in the given column is possible.
     */
    public boolean check(char letter)
    {
        boolean yes = true;
        for(char i = 'A'; i <= 'G'; i++) {
            if(letter == i || letter == (i + 32)) {
                int f = rowSet(letter);
                if(values[f][5] != ' ' && values[f][4] != ' ' && values[f][3] != ' ' &&
                values[f][2] != ' ' && values[f][1] != ' ' && values[f][0] != ' ') {
                    yes = false;
                }
            }
        }
        return yes;
    }
}
