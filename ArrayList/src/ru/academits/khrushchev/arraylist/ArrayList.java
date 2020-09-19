package ru.academits.khrushchev.arraylist;

import java.util.*;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {
    private T[] listElements;
    private int size;
    private int modCount;

    public ArrayList() {
        listElements = (T[]) new Object[2];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be more than -1. Now it is " + capacity);
        }

        listElements = (T[]) new Object[capacity];
    }

    public void trimToSize() {
        if (size < listElements.length) {
            listElements = Arrays.copyOf(listElements, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= 0) {
            throw new IllegalArgumentException("Minimal capacity must be more than 0. Now it is " + minCapacity);
        }

        if (minCapacity <= size) {
            throw new IllegalArgumentException("Minimal capacity cannot be less than size or equal it. Now argument is " +
                    minCapacity + ", size is " + size + ".");
        }

        listElements = Arrays.copyOf(listElements, minCapacity);
    }

    private void increaseSize() {
        listElements = Arrays.copyOf(listElements, listElements.length * 2 + 1);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int receivedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (receivedModCount != modCount) {
                throw new ConcurrentModificationException("List was changed while it was running");
            }

            currentIndex++;

            if (currentIndex >= size) {
                throw new NoSuchElementException("No such element in the specified list. Size is " + size +
                        ". Requested element index is " + currentIndex);
            }

            return listElements[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(listElements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(listElements, size, a.getClass());
        }

        System.arraycopy(listElements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("Received element is null");
        }

        if (size >= listElements.length) {
            increaseSize();
        }

        listElements[size] = t;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int matchIndex = indexOf(o);

        if (matchIndex != -1) {
            System.arraycopy(listElements, matchIndex + 1, listElements, matchIndex, size - matchIndex);

            listElements[size] = null;
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        for (Iterator<T> iterator = (Iterator<T>) c.iterator(); iterator.hasNext(); ) {
            T item = iterator.next();

            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be more than -1 and less and list length(" + size + "). " +
                    "Now it is " + index);
        }

        if (c == null || c.size() == 0) {
            return false;
        }

        int resultSize = c.size() + size;
        ensureCapacity(resultSize);

        System.arraycopy(listElements, index, listElements, c.size() + index, size - index);

        int i = index;

        for (Iterator<T> iterator = (Iterator<T>) c.iterator(); iterator.hasNext(); i++) {
            listElements[i] = iterator.next();
        }

        size = resultSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.size() == 0 || size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (c.contains(listElements[i])) {
                remove(i);
                i--;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0 || c == null || c.size() == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!c.contains(listElements[i])) {
                remove(i);
                i--;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            listElements[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        return listElements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T changedElement = listElements[index];
        listElements[index] = element;
        modCount++;

        return changedElement;
    }

    @Override
    public void add(int index, T element) {
        if (element == null) {
            throw new NullPointerException("Received element is null");
        }

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be less than 0 or list size (" + size +
                    "). Now it is " + index);
        }

        if (index == size) {
            add(element);
            return;
        }

        if (listElements.length == size) {
            increaseSize();
        }

        System.arraycopy(listElements, index, listElements, index + 1, size - index);
        listElements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T deletedElement = listElements[index];
        System.arraycopy(listElements, index + 1, listElements, index, size - index - 1);

        if (listElements.length > size) {
            listElements[size] = null;
        }

        size--;
        modCount++;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (listElements[i] != null && listElements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }

        int i = size - 1;

        while (i >= 0) {
            if (listElements[i] != null && listElements[i].equals(o)) {
                return i;
            }

            i--;
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (size == 0) {
            return "{}";
        }

        sb.append("{");

        for (int i = 0; i < size; i++) {
            sb.append(listElements[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()).append("}");

        return sb.toString();
    }
}
