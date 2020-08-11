package ru.academits.khrushchev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ArrayListHomeInput.txt"))) {
            ArrayList<Integer> integerNumbersList = new ArrayList<>();

            while (scanner.hasNextLine()) { // п.1
                integerNumbersList.add(scanner.nextInt());
            }

//            for (int i = 0; i < integerNumbersList.size(); i++) { // п.2
//                if(integerNumbersList.get(i) % 2 == 0) {
//                    integerNumbersList.remove(i);
//                }
//            }

            System.out.println(integerNumbersList);

            ArrayList<Integer> newIntegerNumbersList = new ArrayList<>(); // п.3

            for (Integer integer : integerNumbersList) {
                if (!newIntegerNumbersList.contains(integer)) {
                    newIntegerNumbersList.add(integer);
                }
            }

            System.out.println(newIntegerNumbersList);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }
}