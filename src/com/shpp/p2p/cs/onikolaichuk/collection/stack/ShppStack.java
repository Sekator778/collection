package com.shpp.p2p.cs.onikolaichuk.collection.stack;

import com.shpp.p2p.cs.onikolaichuk.collection.chainlist.ShppLinkedList;

import java.util.EmptyStackException;

/**
 * Stack (LIFO)
 * is based on a linked list
 */
public class ShppStack<E> {
    private final ShppLinkedList<E> data = new ShppLinkedList<>();

    /**
     * add to the end of the list
     * @param element value for add
     */
    public void push(E element) {
        data.add(element);
    }

    /**
     * return value top element
     * and remove it
     * @return value
     */
    public E pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data.remove(data.size() - 1);
    }

    /**
     * look last added element
     * no remove
     *
     * @return data
     */
    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }

    /**
     * check if stack has element
     * @return true if empty
     */
    public boolean empty() {
        return data.size() == 0;
    }

    /**
     * search index specific element
     * @param element specific element
     * @return index
     */
    public int search(E element) {
        return data.indexOf(element);
    }

    /**
     * @return the length of this data structure
     */
    public int size() {
        return data.size();
    }

    /**
     * convenient display of the data structure
     * @return string with all value nods
     */
    public String toString() {
        return data.toString();
    }
}
