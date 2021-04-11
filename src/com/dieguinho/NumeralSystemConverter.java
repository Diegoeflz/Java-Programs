package com.dieguinho;

import java.io.IOException;
import java.util.Scanner;

public class NumeralSystemConverter {
    public static void main(String[] args) throws IOException {
        /*String sourceRadix = scan.nextLine();
        String sourceNumber = scan.nextLine();
        String targetRadix = scan.nextLine();*/

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        String[] values;
        while ((line = reader.readLine()) != null) {
            sb.append(line.trim());
            sb.append(System.lineSeparator());
        }
        values = sb.toString().split("\\s+");
        String sourceRadix = values[0];
        String sourceNumber = values[1];
        String targetRadix = values[2];*/


        try (Scanner scan = new Scanner(System.in);) {
            scan.useDelimiter("\n");
            String sourceRadix = scan.next();
            if (sourceRadix.length() > 2 || Integer.parseInt(sourceRadix) <= 0 || Integer.parseInt(sourceRadix) > 36 )
                throw new Exception();

            String sourceNumber = scan.next();

            String targetRadix = scan.next();
            if (targetRadix.length() > 2 || Integer.parseInt(targetRadix) <= 0 || Integer.parseInt(targetRadix) > 36 )
                throw new Exception();

            if (sourceRadix.length() > 2 || Integer.parseInt(sourceRadix) <= 0 || Integer.parseInt(sourceRadix) > 36 ||
                    targetRadix.length() > 2 || Integer.parseInt(targetRadix) <= 0 || Integer.parseInt(targetRadix) > 36) {
                System.out.println("error");
            } else if (!sourceNumber.contains(".")) {
                System.out.println(convertIntegerToBase(sourceRadix, sourceNumber, targetRadix));
            } else {
                String[] floatingNumber = sourceNumber.split("\\.");
                if (floatingNumber[0].equals("0")) {
                    System.out.println(floatingNumber[0] + "." + convertFractionToBase(sourceRadix, floatingNumber[1], targetRadix));
                } else {
                    String convertedInteger = convertIntegerToBase(sourceRadix, floatingNumber[0], targetRadix);
                    StringBuilder convertedFraction = convertFractionToBase(sourceRadix, floatingNumber[1], targetRadix);
                    System.out.println(convertedInteger + "." + convertedFraction);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public static String convertIntegerToBase(String sourceBase, String sourceNumber, String targetBase) {

        if (sourceBase.equals(targetBase)) {
            return sourceNumber;
        }

        int decimalNumber = sourceBase.equals("1") ? sourceNumber.length() : Integer.parseInt(sourceNumber, Integer.parseInt(sourceBase));

        if (targetBase.equals("1")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= decimalNumber; i++) {
                sb.append(1);
            }
            return sb.toString();
        }

        return Integer.toString(decimalNumber, Integer.parseInt(targetBase));

    }

    public static StringBuilder convertFractionToBase(String sourceBase, String sourceNumber, String targetBase) {

        if (sourceBase.equals(targetBase)) {
            return new StringBuilder(sourceNumber);
        }

        if (!sourceBase.equals("10")) {
            sourceNumber = fractionToBaseTen(sourceBase, sourceNumber);
        }

        if (Double.parseDouble(sourceNumber) >= 1.0) {
            sourceNumber = "0." + sourceNumber;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            double temp = Double.parseDouble(sourceNumber) * Double.parseDouble(targetBase);
            String[] numberParts = Double.toString(temp).split("\\.");
            int fractionPart = Integer.parseInt(numberParts[0]);
            if (fractionPart > 9) {
                char symbol = Character.forDigit(fractionPart, Integer.parseInt(targetBase));
                result.append(symbol);
            } else {
                result.append(fractionPart);
            }
            sourceNumber = "0." + numberParts[1];
        }
        return result;

    }

    private static String fractionToBaseTen(String sourceBase, String sourceNumber) {

        double result = 0;
        for (int i = 0; i < sourceNumber.length(); i++) {
            result += Character.getNumericValue(sourceNumber.charAt(i)) / Math.pow(Double.parseDouble(sourceBase), i + 1);
        }
        return Double.toString(result);

    }

}
