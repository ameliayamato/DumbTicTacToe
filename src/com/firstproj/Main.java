package com.firstproj;
import java.util.*;

public class Main {
    static ArrayList<Integer> playerPosList = new ArrayList<Integer>();
    static ArrayList<Integer> comPosList = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        //create a 2D array of characters
        /* There are three rows and three columns; between each row, they are also separated by lines. Hence needs 5
        rows account for the lines */
        printBoard(gameBoard);
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter in your placement number (1-9 only!)");
            int playerPos = sc.nextInt();
            System.out.println("Integer is " + playerPos); //takes in user input from console
            while(playerPosList.contains(playerPos) || comPosList.contains(playerPos)){//To make sure each player input is committed and not randomly overriden by Comp
                System.out.println("Oops that position is taken! Try again ;-)");
                int tryAgain = sc.nextInt();
            }
            playGame(gameBoard, playerPos, "player");
            Random rand = new Random();
            int comPos = rand.nextInt(9) + 1; //generating random integer from 0 to 8 then adding 1
            while(playerPosList.contains(comPos) || comPosList.contains(comPos)){ //the computer doesnt need a prompt bc it has no feelings </3
                int newAttempt = rand.nextInt(9) +1;
            }
            playGame(gameBoard, comPos, "computer");
            printBoard(gameBoard);
            String finalResult = checkWinner();
            System.out.println(finalResult);
        }
    }

    public static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void playGame(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) { //must use .equals() method here because strings cannot use "=="
            symbol = 'X';
            playerPosList.add(position); //populating the arraylist with the positions that the "X" is in, this is to keep track of winner
        } else {
            symbol = 'O';
            comPosList.add(position); //populating the arraylist with the positions that the "O" is in
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;

            default:
                break;
        }

    }

    public static String checkWinner() {
        //these are all the winning conditions, hence check if the player moves contains all three of any combinations
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List leftDiag = Arrays.asList(1, 5, 9);
        List rightDiag = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>(); //create a list of lists to store all the winning conditions
        //ArrayList and List is kinda related to you can do this uwu

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(leftDiag);
        winning.add(rightDiag);

        for (List w : winning) {
            if (playerPosList.containsAll(w)) {
                return "Congratulations you won!";
            } else if (comPosList.containsAll(w)) {
                return "Sorry you lost :-(";
            } else if (playerPosList.size() + comPosList.size() == 9) { //When the board is totally populated
                return "OOOO it's a tie, everyone won (or lost) here";
            }
        }
        return "";
    }

}
