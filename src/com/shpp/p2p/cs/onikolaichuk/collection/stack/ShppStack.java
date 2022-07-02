package com.shpp.p2p.cs.onikolaichuk.collection.stack;

import com.shpp.p2p.cs.onikolaichuk.collection.list.ShppList;

import java.util.EmptyStackException;

/**
 * Stack (LIFO) ;)
 */
public class ShppStack<E> extends ShppList<E> {
    private ShppList<E> data = new ShppList<>();

    public void push(E element) {
        data.add(element);
    }

    public E pop() {
        E peek = peek();
        data.remove(peek);
        return peek;
    }

    public E peek() {
        if ((data.size() - 1) < 0) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }

    public boolean empty() {
        return data.size() == 0;
    }

    public int search(E element) {
        return data.indexOf(element);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
