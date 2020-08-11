package ru.academits.khrushchev.csv.table;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Row {
    private Detail[] details;
    private String row;

    public Row(String row) {
        this.row = row;
    }

}
