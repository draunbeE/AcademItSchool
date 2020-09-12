package ru.academits.khrushchev.list_main;

import ru.academits.khrushchev.list.*;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertAtTheBeginning(5);
        list.insertAtTheBeginning(6);
        list.insertAtTheBeginning(7);
        list.insertAtTheBeginning(10);

//        System.out.println(list.getSize());

//        System.out.println(list.getFirstItemData());

//        System.out.println(list);
//        System.out.println(list.getData(0));
//        System.out.println(list);

//        System.out.println(list.setData(1, 10));
//        System.out.println(list);

//        System.out.println(list.getData(2));
//        System.out.println(list.setData(3, 20));

//        System.out.println(list);
//        System.out.println(list.deleteData(4));
//        System.out.println(list);

//        System.out.println(list.deleteData(null));
//        System.out.println(list);

//        System.out.println(list.deleteFirstElement());
//        System.out.println(list);

//        list.insert(8, 4);
//        System.out.println(list);

//        System.out.println(list);
//        list.reverse();
//        System.out.println(list);

//        System.out.println(list);
//        System.out.println(list.deleteData(Integer.valueOf(5)));
//        System.out.println(list);

        System.out.println(list);
        SinglyLinkedList<Integer> newList = list.copy();
        System.out.println(newList);
    }
}