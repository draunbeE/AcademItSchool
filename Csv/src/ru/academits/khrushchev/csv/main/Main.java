package ru.academits.khrushchev.csv.main;

import ru.academits.khrushchev.csv.table.*;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("input.txt");

        System.out.println(file.length());
    }
}
