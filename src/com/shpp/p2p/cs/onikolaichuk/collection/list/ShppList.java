package com.shpp.p2p.cs.onikolaichuk.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * implementation of a parameterized dynamic array
 * an iterator Fail Fast implementation
 */
public class ShppList<E> implements Iterable<E> {
    /*internal array*/
    private E[] data;
    /*the number of filled cells in the internal array*/
    private int size = 0;
    /*size internal array*/
    private int defaultCapacity = 10;
    /*counter of changes in occupied cells of the internal array*/
    int modCount = 0;

    /**
     * default constructor
     * inner array init default value
     */
    public ShppList() {
        this.data = (E[]) new Object[defaultCapacity];
    }

    /**
     * if a number greater than the default value comes to the
     * input of the constructor,
     * then we initialize the internal array with that number
     *
     * @param initialCapacity the size of the internal array entered by the user
     */
    public ShppList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = (E[]) new Object[initialCapacity];
        } else {
            this.data = (E[]) new Object[defaultCapacity];
        }
    }

    /**
     * adds an element to the end of the array
     *
     * @param data - element
     * @return true if success
     */
    public boolean add(E data) {
        if (size >= defaultCapacity) {
            grow();
        }
        this.data[size++] = data;
        modCount++;
        return true;
    }

    /**
     * search for a value on the index of the array
     * checking whether the index is valid can throw an exception
     * IndexOutOfBoundsException
     *
     * @param index search index
     * @return the value stored below this index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        return data[index];
    }

    /**
     * insert an element at the given cell number
     *
     * @param index   cell number to place
     * @param element data
     * @return true if success
     */
    public boolean set(int index, E element) {
        E[] addArr = (E[]) new Object[size - index];
        int k = 0;
        Objects.checkIndex(index, size);
        for (int j = index; j < size; j++) {
            addArr[k++] = data[j];
        }
        data[index] = element;
        index++;
        size++;
        for (int i = 0; i < addArr.length; i++) {
            data[index + i] = addArr[i];
        }
        return true;
    }

    /**
     * when filling the internal array, we increase the size
     * we create a new array according to this size and copy it there
     */
    private void grow() {
        int newSize = defaultCapacity * 3 / 2 + 1;
        E[] newArr = (E[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = data[i];
        }
        data = newArr;
        defaultCapacity = newSize;
        /* According to the condition
         When its size becomes small,
         you have to create an X times larger array,
         copy all the links from the old array,
         and then destroy the old array.
         but it is not necessary as GK sees everything. */
        newArr = null;
    }

    /**
     * delete element by array index
     * @param index list cell
     * @return value element which remove from list
     */
    public E remove(int index) {
        E element = data[index];
        for (int i = 0; i < size - 1; i++) {
            if (i >= index) {
                data[i] = data[i + 1];
            }
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * delete element by data
     * @param element data for delete
     * @return true is success
     */
    public boolean remove(E element) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                result = true;
            }
            if (result && i < (size - 1)) {
                data[i] = data[i + 1];
            }
        }
        if (result) {
            size--;
            modCount++;
        }
        return result;
    }

    /**
     * represent size list
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * find specific position
     * when no find return -1
     * @param element data for search
     * @return index where present data
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * delete all element into list
     */
    public void clear() {
        modCount++;
        for (E el : data) {
            el = null;
        }
        size = 0;
    }

    /**
     * test if current ele,ent present in list
     * @param element data for look
     * @return true if it's find
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if list no contains elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * presentation in the form of a іекштп
     * @return string which contain all element
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder(size);
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]).append(", ");
        }
        if (size > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return custom class for use Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ShppListIterator();
    }

    /**
     * inner class which override behavior of methods
     */
    private class ShppListIterator implements Iterator<E> {
        /* iterator step counter */
        private int index;
        /* fixed amount of modifications */
        private final int expectedModCount;

        /**
         * when creating an instance, we fix it
         * modCount
         */
        public ShppListIterator() {
            this.expectedModCount = modCount;
        }

        /**
         * comparison of the number of steps taken and the size of the array
         * @return true if next step available
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * a Fail-Fast iterator stops immediately
         * if it detects that the collection has changed
         *
         * @return next value and increase index
         */
        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return data[index++];
            }
        }
    }
}
