package ru.academits.khrushchev.hashtable_main;

import ru.academits.khrushchev.arraylist.ArrayList;
import ru.academits.khrushchev.hashtable.HashTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> hashTable1 = new HashTable<>(5);

        hashTable1.add(5);
        hashTable1.add(2);
        hashTable1.add(31);
        hashTable1.add(3);
        hashTable1.add(11);
        hashTable1.add(8);
        hashTable1.add(9);

        Collection<Integer> hashTable2 = new HashTable<>(5);

        hashTable2.add(21);
        hashTable2.add(11);
        hashTable2.add(2);
        hashTable2.add(33);
        hashTable2.add(51);
        hashTable2.add(99);
        hashTable2.add(100);
        hashTable2.add(4);
        hashTable2.add(7);

//        hashTable2.add(31);
//        hashTable2.add(11);
//        hashTable2.add(2);
//        hashTable2.add(3);
//        hashTable2.add(5);
//        hashTable2.add(8);
//        hashTable2.add(9);

//        List<Integer> arrayList1 = new ArrayList<>();
//
//        arrayList1.add(8);
//        arrayList1.add(2);
//        arrayList1.add(2);
//        arrayList1.add(2);
//        arrayList1.add(2);
//        arrayList1.add(31);
//        arrayList1.add(100);

//        System.out.println(hashTable1);
//        System.out.println(hashTable2);

//        Collection<String> hashTable3 = new HashTable<>(5);
//
//        hashTable3.add("abc");
//        hashTable3.add("cba");
//        hashTable3.add("test1");
//        hashTable3.add("test2");
//        hashTable3.add("bca");
//        hashTable3.add("A bit longer sentence");
//
//        System.out.println(hashTable3);

//        System.out.println(hashTable3);
//        System.out.println(hashTable3.remove("CBA"));
//        System.out.println(hashTable3);

//        System.out.println(hashTable1);
//        System.out.println(hashTable1.remove(5));
//        System.out.println(hashTable1);

//        System.out.println(hashTable1);
//        System.out.println(hashTable1.contains(7));

//        System.out.println(hashTable1);
//        System.out.println();
//        System.out.println(hashTable2);
//        System.out.println(hashTable1.containsAll(hashTable2));

//        System.out.println(hashTable1);
//        System.out.println();
//        System.out.println(hashTable2);
//        System.out.println();
//        hashTable1.addAll(hashTable2);
//        System.out.println(hashTable1);

//        System.out.println(hashTable1);
//        System.out.println(Arrays.toString(hashTable1.toArray()));

//        Integer[] array = new Integer[10];
//        array = hashTable1.toArray(array);
//        System.out.println(Arrays.toString(array));

//        System.out.println(hashTable1);
//        System.out.println();
//        System.out.println(hashTable2);
//        System.out.println(hashTable1.removeAll(hashTable2));
//        System.out.println(hashTable1);

//        System.out.println(hashTable1);
//        System.out.println();
//        System.out.println(hashTable2);
//        System.out.println(hashTable1.retainAll(hashTable2));
//        System.out.println(hashTable1);

//        System.out.println(hashTable1);
//        hashTable1.clear();
//        System.out.println(hashTable1);
    }
}
