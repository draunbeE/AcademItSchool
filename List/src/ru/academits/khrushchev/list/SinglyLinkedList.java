package ru.academits.khrushchev.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int listLength = 0;

    public SinglyLinkedList() {
    }

    public void add(T data) {
        head = new ListItem<>(data, head);

        listLength++;
    }

    public void add(T data, int index) {
        if (index == 0) {
            add(data);
            return;
        }

        int i = 0;

        ListItem<T> temp = head;
        ListItem<T> previousItem = head;

        while (i < index) {
            previousItem = head;
            head = head.getNext();

            i++;
        }

        previousItem.setNext(new ListItem<>(data, head));
        head = temp;
        listLength++;
    }

    public int getSize() {
        return listLength;
    }

    public T getFirstItemData() {
        return head.getData();
    }

    public T getData(int index) {
        ListItem<T> temp = head;

        int i = 0;

        while (i < index) {
            head = head.getNext();

            i++;
        }

        T result = head.getData();
        head = temp;

        return result;
    }

    public T setData(int index, T data) {
        int i = 0;
        ListItem<T> temp = head;

        while (i < index) {
            head = head.getNext();
            i++;
        }

        T previousValue = head.getData();

        head.setData(data);
        head = temp;

        return previousValue;
    }

    public T deleteData(int index) {
        if (index == 0) {
            T result = head.getData();
            head = head.getNext();

            listLength--;

            return result;
        }

        int i = 0;

        ListItem<T> temp = head;
        ListItem<T> previousItem = head;

        while (i < index) {
            previousItem = head;
            head = head.getNext();

            i++;
        }

        previousItem.setNext(head.getNext());

        T result = head.getData();
        head = temp;
        listLength--;

        return result;
    }

    public boolean deleteData(T data) {
        int i = 0;

        ListItem<T> temp = head;
        ListItem<T> previousItem = head;

        while (head != null && !head.getData().equals(data)) {
            previousItem = head;
            head = head.getNext();

            i++;
        }

        if (head != null) {
            if (i == 0) {
                head = head.getNext();
            } else {
                previousItem.setNext(head.getNext());
                head = temp;
            }

            listLength--;

            return true;
        }

        head = temp;

        return false;
    }

    public T deleteFirstElement() {
        return deleteData(0);
    }

    public void reverse() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        while (head != null) {
            newList.add(head.getData());
            head = head.getNext();
        }

        head = newList.head;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        resultList.head = head;
        resultList.listLength = listLength;

        return resultList;
    }

    @Override
    public String toString() {
        if (listLength == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        ListItem<T> temp = head;

        sb.append("{");

        while (head != null) {
            sb.append(head.getData()).append(", ");

            head = head.getNext();
        }

        sb.delete(sb.length() - 2, sb.length()).append("}");

        head = temp;

        return sb.toString();
    }
}
