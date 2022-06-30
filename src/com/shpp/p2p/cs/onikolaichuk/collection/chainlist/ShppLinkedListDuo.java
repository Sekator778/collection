package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ShppLinkedListDuo<E> {
    Node<E> head = null;
    int size = 0;

    /**
     * Appends the specified element to the end of this list.
     */
    public void add(E element) {
        if (head == null) {
            head = new Node<>(element);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<E>(element);
            current.getNext().setPrevious(current);
            current.getNext().setNext(null);
        }
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     */
    public void add(int index, E element) {
        Node<E> nodeForAdd = new Node<>(element);
        int k = 1;
        if (head == null && index == 0) {
            this.head = new Node<>(element);
        } else if (head == null) {
            throw new NoSuchElementException("wtf");
        }
        Node<E> temp = head;
        while (temp.getNext() != null) {
            if (k == index) {
                nodeForAdd.setPrevious(temp);
                nodeForAdd.setNext(temp.getNext());
                temp.getNext().setPrevious(nodeForAdd);
                break;
            }
            k++;
            temp = temp.getNext();
        }
        size++;
    }

    /**
     * @return remove head
     */
    public E deleteFirst() {
        Node<E> tmp = head;
        head = head.next;
        head.previous = null;
        return tmp.data;
    }

    /**
     * Removes the element at the specified position in this list.
     */
    public void remove(int index) {
        Node<E> temp = head;
        int k = 0;
        while (k != index) {
            temp = temp.getNext();
            k++;
        }
        if (temp.getPrevious() != null) {
            temp.getPrevious().setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPrevious(temp.getPrevious());
            }
        } else {
            temp.getNext().setPrevious(null);
            head = temp.getNext();
        }
        size--;
        temp = null;
    }

    /**
     * @return head
     */
    public E get() {
        if (head == null) {
            throw new NoSuchElementException("Empty LinkedList");
        }
        return head.data;
    }

    /**
     * find element to data
     */
    public Node<E> get(E element) {
        Node<E> current = head;
        while (current.next != null) {
            if (current.data.equals(element)) {
                return current;
            }
            current = current.next;
        }
        throw new NoSuchElementException("No node");
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> tmp = head;
        while (tmp.getNext() != null) {
            joiner.add(tmp.data.toString());
            tmp = tmp.getNext();
        }
        joiner.add(tmp.data.toString());
        return joiner.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        private Node(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
