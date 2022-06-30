package com.shpp.p2p.cs.onikolaichuk.collection.queue;


import com.shpp.p2p.cs.onikolaichuk.collection.chainlist.ShppLinkedList;

import java.util.LinkedList;
import java.util.Queue;

public class ShppQueue<E> extends ShppLinkedList<E> {
    private ShppLinkedList<E> linkedList = new ShppLinkedList<>();
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("one");
        queue.add("two");
        queue.add("three");
        System.out.println(queue);

        String element = queue.element();
        System.out.println(element);
        System.out.println(queue);

        String head = queue.poll();
        System.out.println(queue);
    }

    @Override
    public boolean add(E element) {
        linkedList.add(element);
        return true;
    }

    @Override
    public void remove() {
        linkedList.remove();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return head
     */
    public E element() {
        return linkedList.get();
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     */
    public E poll() {
        E result = linkedList.get();
        linkedList.remove(0);
        return result;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
