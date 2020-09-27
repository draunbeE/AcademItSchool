package ru.academits.khrushchev.hashtable;

import ru.academits.khrushchev.arraylist.ArrayList;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private int size;
    private final List<T>[] lists;
    private int modCount;

    public HashTable() {
        lists = new ArrayList[10];
    }

    public HashTable(int finalSize) {
        if (finalSize < 0) {
            throw new IllegalArgumentException("Hash table size cannot be less than 0.");
        }

        lists = new ArrayList[finalSize];
    }

    private int getIndex(Object obj) {
        if (lists.length == 0) {
            throw new UnsupportedOperationException("Get index operation is not supported for lists length equal to 0");
        }

        if (obj == null) {
            return 0;
        }

        return Math.abs(obj.hashCode() % lists.length);
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class MyCollectionIterator implements Iterator<T> {
        final private int receivedModCount = modCount;
        private int listsCurrentListIndex = -1;
        private int listElementIndex = 0;
        private int collectionCurrentIndex = 0;

        @Override
        public boolean hasNext() {
            if (collectionCurrentIndex >= size) {
                return false;
            }

            while (lists[listsCurrentListIndex + 1] == null || lists[listsCurrentListIndex + 1].isEmpty()) {
                listsCurrentListIndex++;
            }

            return true;
        }

        @Override
        public T next() {
            if (receivedModCount != modCount) {
                throw new ConcurrentModificationException("Collection was changed while iterator was running.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in specified collection.");
            }

            List<T> list = lists[listsCurrentListIndex + 1];

            if (listElementIndex < list.size()) {
                T item = list.get(listElementIndex);
                listElementIndex++;

                if (listElementIndex >= list.size()) {
                    listElementIndex = 0;
                    listsCurrentListIndex++;
                }

                collectionCurrentIndex++;
                return item;
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

        Object[] listsElements = new Object[size];

        int i = 0;

        for (Object item : this) {
            listsElements[i] = item;
            i++;
        }

        return listsElements;
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
        int index = getIndex(t);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(t);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
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
            return true;
        }

        for (Object collectionItem : c) {
            int itemIndex = getIndex(collectionItem);

            if (lists[itemIndex] == null || !lists[itemIndex].contains(collectionItem)) {
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

        for (T collectionItem : c) {
            add(collectionItem);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Specified collection is null.");
        }

        int listSizeBeforeRemoving;
        int initialSize = size;

        for (List<T> list : lists) {
            listSizeBeforeRemoving = list.size();
            list.removeAll(c);

            size = size - (listSizeBeforeRemoving - list.size());
        }

        return size != initialSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Specified collection is null.");
        }

        int listSizeBeforeRemoving;
        int initialSize = size;

        for (List<T> list : lists) {
            listSizeBeforeRemoving = list.size();
            list.retainAll(c);

            size = size - (listSizeBeforeRemoving - list.size());
        }

        return size != initialSize;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (List<T> list : lists) {
            list.clear();
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lists.length; i++) {
            sb.append("Index [").append(i).append("] ").append(lists[i]).append(System.lineSeparator());
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
