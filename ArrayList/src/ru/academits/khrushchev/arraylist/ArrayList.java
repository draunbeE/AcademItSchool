package ru.academits.khrushchev.arraylist;

import java.util.*;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;

    public ArrayList() {
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be more than 0. Now it is " + capacity);
        }

        array = new Object[capacity];
    }

    public void trimToSize() {
        array = Arrays.copyOf(array, size);
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

        array = Arrays.copyOf(array, minCapacity * 2);
    }

    private void increaseSize() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public void remove() {
            currentIndex++;

            System.arraycopy(array, currentIndex + 1, array, currentIndex, size - currentIndex);

            size--;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {

        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            currentIndex++;

            return (T) array[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) Arrays.copyOf(array, size);
    }

    @Override
    public boolean add(T t) {
        if (size >= array.length) {
            increaseSize();
        }

        array[size] = t;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                System.arraycopy(array, i + 1, array, i, size - i);

                size--;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] givenCollection = c.toArray();
        int givenCollectionSize = c.size();

        for (int i = 0; i < givenCollectionSize; i++) {
            if (!contains(givenCollection[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] givenCollection = c.toArray();

        if (givenCollection.length == 0) {
            return false;
        }

        int resultSize = c.size() + size;
        ensureCapacity(resultSize);

        System.arraycopy(givenCollection, 0, array, size, c.size());

        size = resultSize;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be more than -1 and less and list length(" + size + "). " +
                    "Now it is " + index);
        }

        Object[] givenCollection = c.toArray();

        if (givenCollection.length == 0) {
            return false;
        }

        int resultSize = c.size() + size;
        ensureCapacity(resultSize);

        System.arraycopy(array, index, array, c.size() + index, size - index);

        System.arraycopy(givenCollection, 0, array, index, c.size());

        size = resultSize;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] givenCollection = c.toArray();

        if (givenCollection.length == 0) {
            return false;
        }

        for (Object item : givenCollection) {
            for (int i = 0; i < size; i++) {
                if (item.equals(array[i])) {
                    System.arraycopy(array, i + 1, array, i, size - i);
                    i--;
                    size--;
                }
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] givenCollection = c.toArray();

        if (givenCollection.length == 0) {
            return false;
        }

        boolean isContained;

        for (int i = 0; i < size; i++) {
            isContained = false;

            for (Object item : givenCollection) {
                if (array[i] == item) {
                    isContained = true;
                    break;
                }
            }

            if (!isContained) {
                System.arraycopy(array, i + 1, array, i, size - i);
                i--;
                size--;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        array = new Object[2];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T elementBefore = (T) array[index];
        array[index] = element;

        return elementBefore;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        if (index == size) {
            add(element);
            return;
        }

        if (array.length == size) {
            increaseSize();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be greater than -1 and less than list size(" + size +
                    "). Now it is " + index);
        }

        T deletedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                result = i;
            }
        }

        return result;
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
            sb.append(array[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()).append("}");

        return sb.toString();
    }
}
