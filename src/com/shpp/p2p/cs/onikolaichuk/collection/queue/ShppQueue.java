package com.shpp.p2p.cs.onikolaichuk.collection.queue;


import com.shpp.p2p.cs.onikolaichuk.collection.chainlist.ShppLinkedList;

/**
 * this data structure works as follows FIFO
 * the queue is implemented on a linked list
 * <p>
 * we have the same linked list under one condition
 * that we insert at the end and take from the beginning
 *
 * @param <E> data
 */
public class ShppQueue<E> extends ShppLinkedList<E> {
    private final ShppLinkedList<E> linkedList = new ShppLinkedList<>();

    /**
     * add element into queue
     *
     * @param element specific data
     * @return true if success
     */
    @Override
    public boolean add(E element) {
        return linkedList.add(element);
    }

    /**
     * remove first(head) nod
     *
     * @return data
     */
    @Override
    public E remove() {
        return linkedList.remove();
    }

    /**
     * returns
     * but does not remove,
     * the head of this queue.
     *
     * @return head data
     */
    public E element() {
        return linkedList.peek();
    }

    /**
     * returns and removes the head of this queue,
     * or returns null if this queue is empty.
     */
    public E poll() {
        return linkedList.remove();
    }

    /**
     * returns but does not delete
     * null if empty queue
     *
     * @return data
     */
    public E peek() {
        return linkedList.peek();
    }

    /**
     * make string
     *
     * @return string with all data
     */
    @Override
    public String toString() {
        return linkedList.toString();
    }
}
