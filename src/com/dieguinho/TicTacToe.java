package com.dieguinho;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char[][] cells = new char[3][5];
        char lastValue = 'O';
        int desiredRow = 0;
        int desiredColumn = 0;
        boolean condition = true;

        printsInitialGameStatus(cells);
        gameInitialization(cells);

        do {
            try {
                System.out.print("Enter the coordinates: ");
                desiredRow = scanner.nextInt();
                desiredColumn = scanner.nextInt();

                if (desiredRow > 3 || desiredColumn > 3) {
                    throw new IndexOutOfBoundsException();
                }

                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j < cells[i].length; j++) {
                        if (i == desiredRow - 1 && j == desiredColumn && (cells[desiredRow - 1][j] == '_' || cells[desiredRow - 1][j] == ' ' || cells[desiredRow - 1][j] == 0)) {
                            lastValue = readsLastValue(lastValue);
                            cells[desiredRow - 1][j] = lastValue;
                            printsGameStatus(cells);
                            condition = reviewsGameStatus(cells);
                        } else if (i == desiredRow - 1 && j == desiredColumn && (cells[desiredRow - 1][j] != '_' || cells[desiredRow - 1][j] != ' ' || cells[desiredRow - 1][j] != 0)) {
                            throw new Exception();
                        }
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (Exception e) {
                System.out.println("This cell is occupied! Choose another one!");
            }

        } while (condition);
    }

    public static void printsInitialGameStatus(char[][] array2D) {

        System.out.println("---------");
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                if (j == 0) {
                    System.out.print('|' + " ");
                }else if (j == 4) {
                    System.out.println('|');
                }
                else {
                    System.out.print(' ' + " ");
                }
            }
        }
        System.out.println("---------");

    }

    public static char[][] gameInitialization(char[][] array2D) {

        for (int i=0; i<array2D.length; i++) {
            for (int j=0; j<array2D[i].length; j++) {
                if (j == 0 || j == 4) {
                    continue;
                }else if (i == 0 && j == 2) {
                    array2D[i][j] = ' ';
                }else if (i == 1 && j == 2) {
                    array2D[i][j] = ' ';
                }else if (i == 2 && j == 1 || i == 2 && j == 3) {
                    array2D[i][j] = ' ';
                }
            }
        }
        return array2D;

    }

    public static void printsGameStatus(char[][] array2D) {

        System.out.println("---------");
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                if (j == 0) {
                    System.out.print('|' + " ");
                }else if (j == 4) {
                    System.out.println('|');
                }
                else if (array2D[i][j] == 0) {
                    System.out.print(' ' + " ");
                } else {
                    System.out.print(array2D[i][j] + " ");
                }
            }
        }
        System.out.println("---------");

    }

    public static char readsLastValue(char value) {

        if (value == 'X') {
            value = 'O';
        } else if (value == 'O') {
            value = 'X';
        }
        return value;

    }

    public static boolean reviewsGameStatus (char[][] array2D) {

        int xs = 0;
        int os = 0;
        char winner = ' ';
        int winners = 0;
        int totalValue = 0;
        boolean flag = true;

        for (char[] array : array2D) {
            for (char value : array) {
                if (value == '|') {
                    continue;
                } else if (value == 'X') {
                    xs++;
                } else if (value == 'O') {
                    os++;
                }
                totalValue += value;
            }
        }

        if (array2D[0][1] == array2D[0][2] && array2D[0][2] == array2D[0][3]) {
            winner = array2D[0][2];
            winners++;
        }
        if (array2D[1][1] == array2D[1][2] && array2D[1][2] == array2D[1][3]) {
            winner = array2D[1][2];
            winners++;
        }
        if (array2D[2][1] == array2D[2][2] && array2D[2][2] == array2D[2][3]) {
            winner = array2D[2][2];
            winners++;
        }
        if (array2D[0][1] == array2D[1][1] && array2D[1][1] == array2D[2][1]) {
            winner = array2D[1][1];
            winners++;
        }
        if (array2D[0][2] == array2D[1][2] && array2D[1][2] == array2D[2][2]) {
            winner = array2D[1][2];
            winners++;
        }
        if (array2D[0][3] == array2D[1][3] && array2D[1][3] == array2D[2][3]) {
            winner = array2D[1][3];
            winners++;
        }
        if (array2D[0][1] == array2D[1][2] && array2D[1][2] == array2D[2][3]) {
            winner = array2D[1][2];
            winners++;
        }
        if (array2D[2][1] == array2D[1][2] && array2D[1][2] == array2D[0][3]) {
            winner = array2D[1][2];
            winners++;
        }

        if (winners == 0 && (totalValue == 756 || totalValue == 747)) {
            System.out.println("Draw");
            flag = false;
        } else if (winners > 1 || xs - os > 1 || os - xs > 1) {
            System.out.println("Impossible");
            flag = false;
        } else if (winners == 0 && totalValue > 756) {
            System.out.println("Game not finished");
        }else if (winners >= 1) {
            System.out.println(winner + " wins");
            flag = false;
        }

        return flag;
    }

}
