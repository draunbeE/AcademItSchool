package ru.academits.khrushchev.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        elements = (T[]) new Object[2];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be more than -1. Now it is " + capacity);
        }

        elements = (T[]) new Object[capacity];
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
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

    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= 0) {
            throw new IllegalArgumentException("Minimal capacity must be more than 0. Now it is " + minCapacity);
        }

        if (minCapacity <= elements.length) {
            return;
        }

        elements = Arrays.copyOf(elements, minCapacity);
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2 + 1);
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

            if (!hasNext()) {
                throw new NoSuchElementException("No such element in the specified list. Size is " + size +
                        ". Requested element index is " + currentIndex);
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        add(size, t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[size - 1] = null;
        size--;
        modCount++;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Received collection is null");
        }

        for (Object item : c) {
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

    private boolean checkIndex(int index) {
        return index < 0 || index > size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException("Index must be more than -1 or equal to list length(" + size + "). " +
                    "Now it is " + index);
        }

        if (c == null) {
            throw new IllegalArgumentException("Received collection is null");
        }

        if (c.size() == 0) {
            return false;
        }

        int resultSize = c.size() + size;
        ensureCapacity(resultSize);

        System.arraycopy(elements, index, elements, c.size() + index, size - index);

        int i = index;

        for (Object item : c) {
            add(i, (T) item);
            i++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Received collection is null");
        }

        if (c.size() == 0 || size == 0) {
            return false;
        }

        int initialModCount = modCount;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i);
                i--;
            }
        }

        return initialModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Received collection is null");
        }

        if (size == 0 || c.size() == 0) {
            return false;
        }

        int initialModCount = modCount;

        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(i);
                i--;
            }
        }

        return modCount != initialModCount;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
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

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T changedElement = elements[index];
        elements[index] = element;

        return changedElement;
    }

    @Override
    public void add(int index, T element) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException("Index cannot be less than 0 or more than list size (" + size +
                    "). Now it is " + index);
        }

        if (elements.length == size) {
            increaseCapacity();
        }

        if (index == size) {
            elements[index] = element;
            size++;
            modCount++;
            return;
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T deletedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[size - 1] = null;
        size--;
        modCount++;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
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
        if (size == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                sb.append("null").append(", ");
                continue;
            }

            sb.append(elements[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()).append("}");

        return sb.toString();
    }
}
