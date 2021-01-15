package com.dieguinho;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberSize = 0;
        int characterSize = 0;
        String firstInput = "";
        String secondInput = "";

        try {
            System.out.println("Input the length of the secret code:");
            firstInput = scanner.nextLine();
            numberSize = Integer.parseInt(firstInput);
            System.out.println("Input the number of possible symbols in the code:");
            secondInput = scanner.nextLine();
            characterSize = Integer.parseInt(secondInput);
            if (numberSize == 0) {
                throw new InputMismatchException();
            }
            if (characterSize > 36) {
                throw new IndexOutOfBoundsException();
            } else if (characterSize < numberSize) {
                throw new InputMismatchException();
            }

            System.out.print("The secret is prepared: ");

            for (int i = 1; i <= numberSize; i++) {
                System.out.print("*");
            }
            System.out.println(" (0-9, a-f).");

            if (generatesRandomNumber(numberSize, characterSize) != "0") {
                String randomCode = generatesRandomNumber(numberSize, characterSize);
                System.out.println("Okay, let's start a game!");
                System.out.printf("Secret code %s.%n", randomCode);
                guessesAttempt(randomCode);
            } else {
                System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.%n", numberSize);
            }

        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", firstInput);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Error: maximum number of possible symbols in the code is 36 (0 - 9, a-z).");
        } catch (InputMismatchException e) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", numberSize, characterSize);
        }
    }

    public static String generatesRandomNumber(int size, int characterSize) {
        if (size <= 36 && characterSize <= 36) {
            Random random = new Random();
            int upperCharacter = 0;

            if (characterSize > 25) {
                upperCharacter = 97 + 25;
            } else {
                upperCharacter = 97 + characterSize;
            }

            int randomValue = 0;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < size; i++) {
                if (random.nextBoolean()) {
                    randomValue = random.nextInt(57 - 48 + 1) + 48;
                } else {
                    randomValue = random.nextInt(upperCharacter - 97 + 1) + 97;
                }

                if (sb.toString().contains(Integer.toString(randomValue))) {
                    do {
                        sb.insert(i, (char)randomValue);
                    } while (!sb.toString().contains(Integer.toString(randomValue)));
                } else {
                    sb.append((char)randomValue);
                }
            }
            if (characterSize == 36 && sb.charAt(sb.length()-1) != 'z') {
                sb.replace(sb.length()-1, sb.length(),"z");
            }
            return sb.toString();
        } else {
            return "0";
        }
    }

    public static void guessesAttempt(String code) {
        Scanner scanner = new Scanner(System.in);
        int cows;
        int bulls;
        int turn = 0;

        do {
            StringBuilder surrogateCode = new StringBuilder(code);
            System.out.printf("Turn %d:%n", ++turn);
            String[] fragmentedCode = code.split("");
            String input = scanner.nextLine();
            cows = 0;
            bulls = 0;

            String[] fragmentedInput = input.split("");
            for (int i = 0; i < fragmentedInput.length; i++) {
                if (surrogateCode.toString().contains(fragmentedInput[i]) && fragmentedCode[i].equals(fragmentedInput[i])) {
                    bulls++;
                    surrogateCode = surrogateCode.deleteCharAt(surrogateCode.indexOf(fragmentedInput[i]));
                    //surrogateCode = surrogateCode.replace(i+1, surrogateCode.length(),surrogateCode.toString());
                } else {
                    continue;
                }
            }

            for (int i = 0; i < fragmentedInput.length; i++) {
                if (surrogateCode.toString().contains(fragmentedInput[i]) && !fragmentedInput[i].equals("")) {
                    cows++;
                    surrogateCode = surrogateCode.deleteCharAt(surrogateCode.indexOf(fragmentedInput[i]));
                } else {
                    continue;
                }
            }

            if (bulls == 2 && cows == 0) {
                printsGrade(cows, bulls - 1, code);
            } else {
                printsGrade(cows, bulls, code);
            }
        } while(bulls != code.length());
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static void printsGrade(int cows, int bulls, String code) {
        if (cows == 1 && bulls == 1) {
            System.out.printf("Grade: %d bull and %d cow.%n", bulls, cows);
        } else if (cows > 1 && bulls > 1) {
            System.out.printf("Grade: %d bulls and %d cows.%n", bulls, cows);
        } else if (cows == 1 && bulls == 0) {
            System.out.printf("Grade: %d cow.%n", cows);
        } else if (cows > 1 && bulls == 0) {
            System.out.printf("Grade: %d cows.%n", cows);
        } else if (cows > 1 && bulls == 1) {
            System.out.printf("Grade: %d cows and %d bull.%n", cows, bulls);
        } else if (bulls == 1 && cows == 0) {
            System.out.printf("Grade: %d bull.%n", bulls);
        } else if (bulls > 1 && cows == 0) {
            System.out.printf("Grade: %d bulls.%n", bulls);
        } else if (bulls > 1 && cows == 1) {
            System.out.printf("Grade: %d bulls and %d cow.%n", bulls, cows);
        } else {
            System.out.println("Grade: None.");
        }
    }
}
