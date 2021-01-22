package com.dieguinho;

import java.util.Scanner;

public class CinemaRoomManager {

    public static void main(String[] args) {

        int option = 5;
        int soldSeats = 0;
        int currentIncome = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int[][] cinemaSeats = new int[rows + 1][seats + 1];

        fillsCinema(cinemaSeats);

        while (option != 0) {
            System.out.print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    printsCinema(cinemaSeats);
                    break;
                case 2:
                    boolean takenTicket;
                    do {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        int desiredRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int desiredSeat = scanner.nextInt();
                        if (buysTicket(cinemaSeats, desiredRow, desiredSeat)) {
                            takenTicket = true;
                        } else {
                            int ticketPrice = computesTicketPrice(rows, seats, desiredRow, desiredSeat);
                            System.out.printf("Ticket price: $" + ticketPrice + "%n");
                            takenTicket = false;
                            soldSeats++;
                            currentIncome += ticketPrice;
                        }
                    } while(takenTicket);

                    break;
                case 3:
                    System.out.println();
                    System.out.printf("Number of purchased tickets: %d%n", soldSeats);
                    computesPercentage(rows, seats, soldSeats);
                    System.out.printf("Current income: $%d%n", currentIncome);
                    computesTotalIncome(rows, seats);
                    break;
                case 0:
                    option = 0;
                    break;
            }
        }

    }

    public static void fillsCinema(int[][] cinema) {

        for (int row = 0; row < cinema.length; row++) {
            for (int seat = 0; seat < cinema[row].length; seat++) {
                if (row == 0 && seat == 0) {
                    cinema[row][seat] = 32;
                } else if (row == 0) {
                    cinema[row][seat] = seat;
                } else if (seat == 0) {
                    cinema[row][seat] = row;
                } else {
                    cinema[row][seat] = 83;
                }
            }
        }

    }

    public static void printsCinema(int[][] cinema) {

        System.out.println();
        System.out.println("Cinema:");
        for (int row = 0; row < cinema.length; row++) {
            for (int seat = 0; seat < cinema[row].length; seat++) {
                if (row == 0 && seat == 0) {
                    System.out.printf("%c ", cinema[row][seat]);
                } else if (row == 0) {
                    System.out.printf("%d ", cinema[row][seat]);
                } else if (seat == 0) {
                    System.out.printf("%d ", cinema[row][seat]);
                } else  {
                    System.out.printf("%c ", cinema[row][seat]);
                }
            }
            System.out.println();
        }

    }

    public static int computesTicketPrice(int rows, int seats, int desiredRow, int desiredSeat) {

        int[][] seatsPrice = new int[rows+1][seats+1];

        if (rows * seats >= 60) {
            int frontHalf = rows / 2;
            for (int row = 0; row < seatsPrice.length; row++) {
                if (row == 0) {
                    continue;
                }
                for (int seat = 0; seat < seatsPrice[row].length; seat++) {
                    if (seat == 0) {
                        continue;
                    } else if (row <= frontHalf) {
                        seatsPrice[row][seat] = 10;
                    } else {
                        seatsPrice[row][seat] = 8;
                    }
                }
            }
        } else {
            for (int row = 0; row < seatsPrice.length; row++) {
                for (int seat = 0; seat < seatsPrice[row].length; seat++) {
                    seatsPrice[row][seat] = 10;
                }
            }
        }
        return seatsPrice[desiredRow][desiredSeat];

    }

    public static boolean buysTicket(int[][] cinema, int boughtRow, int boughtSeat) {

        boolean flag = true;
        if (boughtRow > 0 && boughtRow < cinema.length && boughtSeat > 0 && boughtSeat < cinema[boughtRow].length) {
            for (int row = 0; row < cinema.length; row++) {
                for (int seat = 0; seat < cinema[row].length; seat++) {
                    if (row == boughtRow && seat == boughtSeat && cinema[row][seat] != 66) {
                        cinema[row][seat] = 66;
                        flag = false;
                    } else if (row == boughtRow && seat == boughtSeat && cinema[row][seat] == 66) {
                        System.out.println();
                        System.out.print("That ticket has already been purchased!");
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Wrong input!");
        }
        return flag;

    }

    public static void computesTotalIncome(int rows, int seats) {

        int profit = 0;

        if (rows * seats >= 60) {
            int frontHalf = rows / 2;
            profit = (10 * seats * frontHalf) + (8 * seats * (rows - frontHalf));
        } else {
            profit = 10 * seats * rows;
        }
        System.out.printf("Total income: $%d%n", profit);

    }

    public static void computesPercentage(int rows, int seats, int soldSeats) {

        double percentage = (soldSeats * 100.0) / (rows * seats);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");

    }

}
