package ru.academits.khrushchev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ArrayListHomeInput.txt"))) {
            ArrayList<String> stringsList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                stringsList.add(scanner.nextLine());
            }

            System.out.println(stringsList);

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        ArrayList<Integer> integerNumbersList = new ArrayList<>(Arrays.asList(3, 3, 3, 7, 4, 4, 5, 6, 7, 8, 9, 10, 11, 11));

        for (int i = 0; i < integerNumbersList.size(); i++) {
            if (integerNumbersList.get(i) % 2 == 0) {
                integerNumbersList.remove(i);

                i--;
            }
        }

        System.out.println(integerNumbersList);

        ArrayList<Integer> integerNumbersWithoutRepetitionsList = new ArrayList<>(integerNumbersList.size());

        for (Integer integer : integerNumbersList) {
            if (!integerNumbersWithoutRepetitionsList.contains(integer)) {
                integerNumbersWithoutRepetitionsList.add(integer);
            }
        }

        System.out.println(integerNumbersWithoutRepetitionsList);
    }
}