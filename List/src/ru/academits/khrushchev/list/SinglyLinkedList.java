package ru.academits.khrushchev.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public void insertAtTheBeginning(T data) {
        head = new ListItem<>(data, head);

        length++;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        if (index == 0) {
            insertAtTheBeginning(data);
            return;
        }

        ListItem<T> item = getListItem(index - 1);
        item.setNext(new ListItem<>(data, item.getNext()));
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
            throw new IndexOutOfBoundsException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        ListItem<T> item = getListItem(index);

        return item.getData();
    }

    public T setData(int index, T data) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        ListItem<T> changedListItem = getListItem(index);
        T previousValue = changedListItem.getData();

        changedListItem.setData(data);

        return previousValue;
    }

    public T deleteData(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index is out of bounds. Length is " + length + ". Index is " + index);
        }

        if (index == 0) {
            T deletedData = head.getData();
            head = head.getNext();
            length--;

            return deletedData;
        }

        ListItem<T> previousItem = getListItem(index - 1);
        ListItem<T> deletedItem = previousItem.getNext();

        previousItem.setNext(previousItem.getNext().getNext());
        length--;

        return deletedItem.getData();
    }

    public boolean deleteData(T data) {
        if (length == 0) {
            return false;
        }

        if (head.getData() != null && head.getData().equals(data)) {
            head = head.getNext();
            length--;
            return true;
        }

        if (head.getData() == null && data == null) {
            head = head.getNext();
            length--;
            return true;
        }

        ListItem<T> item = head;
        ListItem<T> previousItem = head;

        while (item != null) {
            if (item.getData() == null) {
                if (data == null) {
                    break;
                }

                while (item.getData() == null) {
                    previousItem = item;
                    item = item.getNext();

                    if (item == null) {
                        return false;
                    }
                }

                continue;
            }

            if (item.getData().equals(data)) {
                break;
            }

            previousItem = item;
            item = item.getNext();
        }

        if (item == null) {
            return false;
        }

        previousItem.setNext(item.getNext());
        length--;

        return true;
    }

    public T deleteFirstElement() {
        if (length == 0) {
            throw new NoSuchElementException("List is empty");
        }

        return deleteData(0);
    }

    public void reverse() {
        ListItem<T> currentHead = head;
        ListItem<T> next = null;

        while (currentHead != null) {
            ListItem<T> newHead = currentHead.getNext();
            currentHead.setNext(next);
            next = currentHead;

            currentHead = newHead;
        }

        head = next;
    }

    public SinglyLinkedList<T> copy() {
        if (length == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        ListItem<T> resultListHead = new ListItem<>(head.getData(), head.getNext());
        ListItem<T> resultListNext = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            resultList.insertAtTheBeginning(currentItem.getData());
            resultList.head.setNext(resultListNext);
            resultListNext = currentItem;

            currentItem = currentItem.getNext();
        }

        resultList.head = resultListHead;
        resultList.length = length;

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
