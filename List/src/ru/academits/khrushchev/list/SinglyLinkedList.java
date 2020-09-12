package ru.academits.khrushchev.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length = 0;

    public SinglyLinkedList() {
    }

    public void insertAtTheBeginning(T data) {
        head = new ListItem<>(data, head);

        length++;
    }

    public void insert(T data, int index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        if (index == 0) {
            insertAtTheBeginning(data);
            return;
        }

        int i = 0;

        ListItem<T> item = head;
        ListItem<T> previousItem = head;

        while (i < index) {
            previousItem = item;
            item = item.getNext();

            i++;
        }

        previousItem.setNext(new ListItem<>(data, item));
        length++;
    }

    public int getSize() {
        return length;
    }

    public T getFirstItemData() {
        if (length == 0) {
            throw new NoSuchElementException("There is no element. Length is " + length);
        }

        return head.getData();
    }

    private ListItem<T> getListItem(int index) {
        int i = 0;
        ListItem<T> item = head;

        while (i < index) {
            item = item.getNext();
            i++;
        }

        return item;
    }

    public T getData(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        ListItem<T> item = getListItem(index);

        return item.getData();
    }

    public T setData(int index, T data) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        ListItem<T> previousListItem = getListItem(index);
        T previousValue = previousListItem.getData();

        previousListItem.setData(data);

        return previousValue;
    }

    public T deleteData(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        if (index == 0) {
            T result = head.getData();
            head = head.getNext();

            length--;

            return result;
        }

        int i = 0;

        ListItem<T> item = head;
        ListItem<T> previousItem = head;

        while (i < index) {
            previousItem = item;
            item = item.getNext();

            i++;
        }

        previousItem.setNext(item.getNext());
        length--;

        return item.getData();
    }

    public boolean deleteData(T data) {
        if (length == 0) {
            return false;
        }

        int i = 0;

        ListItem<T> item = head;
        ListItem<T> previousItem = head;

        while (item != null && !item.getData().equals(data)) {
            previousItem = item;
            item = item.getNext();

            i++;
        }

        if (item == null) {
            return false;
        }

        if (i == 0) {
            head = item.getNext();
            length--;
            return true;
        }

        previousItem.setNext(item.getNext());
        length--;

        return true;
    }

    public T deleteFirstElement() {
        return deleteData(0);
    }

    public void reverse() {
       ListItem<T> currentHead = head;
       ListItem<T> newHead;
       ListItem<T> next = null;

       while(currentHead != null) {
           newHead = currentHead.getNext();
           currentHead.setNext(next);
           next = currentHead;

           currentHead = newHead;
       }

       head = next;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        ListItem<T> item = head;
        resultList.length = length;

        while (item != null) {
            resultList.insertAtTheBeginning(item.getData());

            item = item.getNext();
        }

        resultList.reverse();

        return resultList;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        ListItem<T> item = head;

        sb.append("{");

        while (item != null) {
            sb.append(item.getData()).append(", ");

            item = item.getNext();
        }

        sb.delete(sb.length() - 2, sb.length()).append("}");

        return sb.toString();
    }
}
