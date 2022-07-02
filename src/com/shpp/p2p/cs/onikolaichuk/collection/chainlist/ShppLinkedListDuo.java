package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ShppLinkedListDuo<E> {
    private Node<E> head = null;
    private int size = 0;

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
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index + " size: " + size);
        }
        Node<E> nodeForAdd = new Node<>(element);
        if (head == null && index == 0) {
            this.head = new Node<>(element);
        } else if (head == null) {
            throw new IndexOutOfBoundsException("wrong index " + index + " size: " + size);
        }
        if (index == 0) {
            nodeForAdd.setNext(head);
            head.setPrevious(nodeForAdd);
            head = nodeForAdd;
        } else {
            int k = 1;
            Node<E> temp = head;
            while (temp.getNext() != null) {
                if (k == index) {
                    nodeForAdd.setPrevious(temp);
                    nodeForAdd.setNext(temp.getNext());
                    temp.getNext().setPrevious(nodeForAdd);
                    temp.setNext(nodeForAdd);
                    break;
                }
                k++;
                temp = temp.getNext();
            }
        }
        size++;
    }

    public void addLast(E data) {
        if (size == 0) {
            head = new Node<>(data);
        } else {
            Node<E> previous = head;
            while (previous.next != null) {
                previous = previous.next;
            }
            previous.setNext(new Node<>(data));
            previous.getNext().setPrevious(previous);
        }
        size++;
    }

    public void addAll(int index, Collection<? extends E> collection) {
        if (index == 0 && head == null) {
            collection.forEach(this::add);
        }
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index + " size: " + size);
        }
        Node<E> current = head.next;
        int k = 1;
        while (current != null) {
            if (k == index) {
                for (E e : collection) {
                    Node<E> nodeForAdd = new Node<>(e);
                    current.getPrevious().setNext(nodeForAdd);
                    nodeForAdd.setPrevious(current.getPrevious());
                    nodeForAdd.setNext(current);
                    current.setPrevious(nodeForAdd);
                    size++;
                }
            }
            current = current.next;
            k++;
        }
    }

    /**
     * check if index possible
     *
     * @param index current number for check
     * @return true if index possible
     */
    private boolean isIndexNoCorrect(int index) {
        return (index < 0 || index >= size);
    }

    /**
     * @return remove head
     */
    public E deleteFirst() {
        Node<E> tmp = head;
        head = head.next;
        head.previous = null;
        size--;
        return tmp.data;
    }

    /**
     * @return remove tail
     */
    public E deleteLast() {
        if (head == null) {
            throw new NoSuchElementException("Empty linked list");
        }
        Node<E> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        E result = tmp.data;
        size--;
        tmp.getPrevious().setNext(null);
        return result;
    }

    /**
     * Removes the element at the specified position in this list.
     */
    public void remove(int index) {
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index + " size: " + size);
        }
        if (index == 0) {
            head = head.next;
            head.previous = null;
        } else {
            int k = 1;
            Node<E> current = head.next;
            while (current != null) {
                if (k == index) {
                    current.previous.setNext(current.getNext());
                }
                current = current.next;
                k++;
            }
        }
        size--;
    }

    /**
     * represent first element
     *
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
