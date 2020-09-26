package ru.academits.khrushchev.arraylist_main;

import ru.academits.khrushchev.arraylist.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> arrayList1 = new ArrayList<>(0);

        arrayList1.add(null);
        arrayList1.add(4);
        arrayList1.add(1);
        arrayList1.add(2);

        List<Integer> arrayList2 = new ArrayList<>();

        arrayList2.add(4);
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(null);

//        System.out.println(arrayList1.size());
//        System.out.println(arrayList1.isEmpty());
//
//        System.out.println(arrayList1.contains(5));
//
//        Object[] array = arrayList1.toArray();
//        System.out.println(Arrays.toString(array));
//
//        Object[] array1 = new Object[4];
//        array1 = arrayList1.toArray(array1);
//        System.out.println(Arrays.toString(array1));
//
//        System.out.println(arrayList1.remove(Integer.valueOf(7)));
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList2);
//        System.out.println(arrayList1.containsAll(arrayList2));
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList2);
//        System.out.println(arrayList2.addAll(arrayList1));
//        System.out.println(arrayList2);
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList2);
//        System.out.println(arrayList1.addAll(4, arrayList2));
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList2);
//        System.out.println(arrayList1.removeAll(arrayList2));
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList2);
//        System.out.println(arrayList1.retainAll(arrayList2));
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        arrayList1.clear();
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        arrayList1.set(2, 7);
//        System.out.println(arrayList1.get(2));
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList1.remove(2));
//        System.out.println(arrayList1);
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList1.indexOf(null));
//
//        System.out.println(arrayList1);
//        System.out.println(arrayList1.lastIndexOf(null));
    }
}
