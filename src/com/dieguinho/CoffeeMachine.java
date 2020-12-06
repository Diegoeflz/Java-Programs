package com.dieguinho;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int availableWater = 400;
        int availableMilk = 540;
        int availableBeans = 120;
        int availableCups = 9;
        int availableMoney = 550;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String option = scanner.next();
        System.out.println();

        while (!option.equals("exit")) {

            switch (option) {

                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String typeCoffee = scanner.next();

                    switch (typeCoffee) {
                        case "1":
                            if (availableWater >= 250 && availableBeans >= 16) {

                                System.out.println("I have enough resources, making you a coffee!\n");
                                availableWater -= 250;
                                availableBeans -= 16;
                                availableCups -= 1;
                                availableMoney += 4;
                            } else if (availableWater < 250) {
                                System.out.println("Sorry, not enough water!\n");
                            } else if (availableBeans < 16) {
                                System.out.println("Sorry, not enough coffee beans!\n");
                            } else {
                                System.out.println("Can't make a cup of coffee\n");
                            }
                            break;

                        case "2":
                            if (availableWater >= 350 && availableMilk >= 45 && availableBeans >= 20) {
                                System.out.println("I have enough resources, making you a coffee!\n");
                                availableWater -= 350;
                                availableMilk -= 75;
                                availableBeans -= 20;
                                availableCups -= 1;
                                availableMoney += 7;
                            } else if (availableWater < 250) {
                                System.out.println("Sorry, not enough water!\n");
                            } else if (availableMilk < 45) {
                                System.out.println("Sorry, not enough milk!\n");
                            } else if (availableBeans < 20) {
                                System.out.println("Sorry, not enough coffee beans!\n");
                            } else {
                                System.out.println("Can't make a cup of coffee\n");
                            }
                            break;

                        case "3":
                            if (availableWater >= 200 && availableMilk >= 100 && availableBeans >= 12) {
                                System.out.println("I have enough resources, making you a coffee!\n");
                                availableWater -= 200;
                                availableMilk -= 100;
                                availableBeans -= 12;
                                availableCups -= 1;
                                availableMoney += 6;
                            } else if (availableWater < 200) {
                                System.out.println("Sorry, not enough water!\n");
                            } else if (availableMilk < 100) {
                                System.out.println("Sorry, not enough milk!\n");
                            } else if (availableBeans < 12) {
                                System.out.println("Sorry, not enough coffee beans!\n");
                            } else {
                                System.out.println("Can't make a cup of coffee\n");
                            }
                            break;

                        case "back":
                            break;
                    }
                    break;

                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    int addedWater = scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    int addedMilk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    int addedBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    int addedCups = scanner.nextInt();
                    System.out.println();

                    availableWater += addedWater;
                    availableMilk += addedMilk;
                    availableBeans += addedBeans;
                    availableCups += addedCups;
                    break;

                case "take":
                    System.out.printf("I gave you $%d%n%n", availableMoney);
                    availableMoney = 0;
                    break;

                case "remaining":
                    printMessage(availableWater, availableMilk, availableBeans, availableCups, availableMoney);
                    break;
            }

            System.out.println("Write action (buy, fill, take, remaining, exit):");
            option = scanner.next();
            System.out.println();
        }
    }

    public static void printMessage(int water, int milk, int beans, int cups, int money) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n", water);
        System.out.printf("%d of milk%n", milk);
        System.out.printf("%d of coffee beans%n", beans);
        System.out.printf("%d of disposable cups%n", cups);
        System.out.printf("$%d of money%n%n", money);
    }
}
