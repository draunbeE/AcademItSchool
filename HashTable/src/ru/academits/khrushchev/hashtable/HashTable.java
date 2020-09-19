package ru.academits.khrushchev.hashtable;

import ru.academits.khrushchev.arraylist.ArrayList;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private int size;
    private List<T>[] hashTable;
    private int modCount;

    public HashTable() {
        hashTable = new ArrayList[10];
    }

    public HashTable(int initialSize) {
        hashTable = new ArrayList[initialSize];
    }

    private int getIndex(Object obj) {
        if (obj == null) {
            return 0;
        }

        if (hashTable.length == 0) {
            hashTable = Arrays.copyOf(hashTable, 2);
        }

        return Math.abs(obj.hashCode() % hashTable.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        int index = getIndex(o);

        return hashTable[index] != null && hashTable[index].contains(o);
    }

    private class MyCollectionIterator implements Iterator<T> {
        final int receivedModCount = modCount;
        int hashTableCurrentListIndex = -1;
        int listElementIndex = 0;

        @Override
        public boolean hasNext() {
            while (hashTableCurrentListIndex + 1 < hashTable.length && hashTable[hashTableCurrentListIndex + 1] == null) {
                hashTableCurrentListIndex++;
            }

            if (hashTableCurrentListIndex + 1 >= hashTable.length) {
                return false;
            }

            return !hashTable[hashTableCurrentListIndex + 1].isEmpty();
        }

        @Override
        public T next() {
            if (receivedModCount != modCount) {
                throw new ConcurrentModificationException("Collection was changed while iterator was running.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in specified collection.");
            }

            List<T> list = hashTable[hashTableCurrentListIndex + 1];

            if (listElementIndex < list.size()) {
                T listItem = list.get(listElementIndex);
                listElementIndex++;

                if (listElementIndex >= list.size()) {
                    listElementIndex = 0;
                    hashTableCurrentListIndex++;
                }

                return listItem;
            }

            return next();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyCollectionIterator();
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Objects[0];
        }

        Object[] hashTableElements = new Object[size];

        Iterator<T> iterator = iterator();
        int i = 0;

        while (iterator.hasNext()) {
            hashTableElements[i] = iterator.next();
            i++;
        }

        return Arrays.copyOf(hashTableElements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Specified array is null.");
        }

        if (a.length < size) {
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            return false;
        }

        int index = getIndex(t);

        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }

        hashTable[index].add(t);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int index = getIndex(o);

        if (hashTable[index] != null && hashTable[index].remove(o)) {
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Received collection is null");
        }

        if (c.isEmpty()) {
            return false;
        }

        Iterator<T> iterator = (Iterator<T>) c.iterator();

        while (iterator.hasNext()) {
            T collectionItem = iterator.next();

            int itemIndex = getIndex(collectionItem);

            if (hashTable[itemIndex] == null || !hashTable[itemIndex].contains(collectionItem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Received collection is null");
        }

        if (c.isEmpty()) {
            return false;
        }

        Iterator<T> iterator = (Iterator<T>) c.iterator();

        while (iterator.hasNext()) {
            T collectionItem = iterator.next();
            add(collectionItem);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Specified collection is null.");
        }

        Iterator<T> iterator = (Iterator<T>) c.iterator();
        boolean wasChanged = false;

        while (iterator.hasNext()) {
            T item = iterator.next();
            int hashTableItemIndex = getIndex(item);
            int hashTableListItemIndex = hashTable[hashTableItemIndex].indexOf(item);

            while (hashTableListItemIndex != -1) {
                hashTable[hashTableItemIndex].remove(hashTableListItemIndex);
                hashTableListItemIndex = hashTable[hashTableItemIndex].indexOf(item);

                wasChanged = true;
            }
        }

        return wasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Specified collection is null.");
        }

        int i = 0;
        int j;
        boolean wasChanged = false;

        while (i < hashTable.length) {
            if (hashTable[i] == null) {
                i++;
                continue;
            }

            j = 0;

            while (j < hashTable[i].size()) {
                T item = hashTable[i].get(j);

                if (c.contains(item)) {
                    j++;
                    continue;
                }

                hashTable[i].remove(j);
                wasChanged = true;
            }

            i++;
        }

        return wasChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        int i = 0;
        int j;

        while (i < hashTable.length) {
            if (hashTable[i] == null) {
                i++;
                continue;
            }

            j = 0;

            while (j < hashTable[i].size()) {
                hashTable[i].remove(j);
            }

            i++;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "No elements in table";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hashTable.length; i++) {
            sb.append("Index [").append(i).append("] ").append(hashTable[i]).append(System.lineSeparator());
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
