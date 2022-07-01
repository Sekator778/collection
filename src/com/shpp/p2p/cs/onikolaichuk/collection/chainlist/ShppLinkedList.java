package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.*;

/**
 * this data structure is based on nodes that contain data
 * and a link to the next node
 *
 * @param <E> generic
 */
public class ShppLinkedList<E> implements Iterable<E> {
    /* start node in the chain */
    private Node<E> head;
    private int size;
    private int modCount;

    /**
     * Adds the specified element to the end of this list.
     */
    public boolean add(E data) {
        if (head == null) {
            head = new Node<>(data);
            size++;
            modCount++;
        } else {
            Node<E> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<>(data));
            size++;
            modCount++;
        }
        return true;
    }

    /**
     * Inserts an element at the specified position in this list.
     * Throws IndexOutOfBoundsException,
     * if the specified index is out of range (index < 0 || index > size()).
     */
    public void add(int index, E data) {
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index);
        }
        if (head == null || index == 0) {
            Node<E> nodeAdd = new Node<>(data);
            nodeAdd.setNext(head);
            head = nodeAdd;
            size++;
            modCount++;
        } else {
            Node<E> current = head;
            int i = 0;
            while (current.getNext() != null) {
                if (i == index) {
                    linkBefore(current, data);
                    break;
                }
                current = current.getNext();
                i++;
            }
        }
    }

    public void add2(int index, E data) {
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index);
        }
        if (head == null || index == 0) {
            Node<E> nodeAdd = new Node<>(data);
            nodeAdd.setNext(head);
            head = nodeAdd;
            size++;
            modCount++;
        } else {
            Node<E> current = head;
            Node<E> next = current.next;
            int k = 0;
            while (current != null) {
                if (k == index) {
                    Node<E> tmp = current;
                    current = new Node<>(data);
                    current.next = tmp;
                    tmp.next = next;
                    size++;
                    modCount++;
                    break;
                } else {
                    current = current.next;
                    next = current.next;
                    k++;
                }
            }
        }
    }

    /**
     * add collection items starting from the specified index
     *
     * @param index      specified index
     * @param collection it's add
     * @return true if success
     */
    public boolean addAll(int index, Collection<? extends E> collection) {
        Node<E> current = head;
        int i = 0;
        while (current.getNext() != null) {
            if (i == index) {
                for (E el : collection) {
                    add(i++, el);
                }
                break;
            }
            current = current.getNext();
            i++;
        }
        modCount++;
        return true;
    }

    /**
     * insert new element in the head of the chain
     *
     * @param data - element for add to node
     */
    public void addFirst(E data) {
        if (head == null) {
            this.head = new Node<>(data);
            size++;
            modCount++;
        } else {
            add(0, data);
        }
    }

    public void addLast(E data) {
        linkLast(data);
    }

    /**
     * returns head but does not delete
     * if list empty return null
     *
     * @return first data
     */
    public E peek() {
        return head == null ? null : head.data;
    }

    /**
     * returns and remove head
     *
     * @return data head
     */
    public E poll() {
        if (head == null) {
            return null;
        }
        E result = head.data;
        head = head.next;
        size--;
        modCount++;
        return result;
    }

    /**
     * insert a new node from the data before the given one
     */
    private void linkBefore(Node<E> node, E data) {
        Node<E> previous = null;
        previous = geteNode(node, previous);
        Node<E> newNode = new Node<>(data);
        previous.setNext(newNode);
        newNode.setNext(node);
        size++;
        modCount++;
    }

    /**
     * insert new node into tail list
     *
     * @param data specific element for insert into node
     */
    private void linkLast(E data) {
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.setNext(new Node<>(data));
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Throws IndexOutOfBoundsException,
     * if the specified index is out of range (index < 0 || index >= size()).
     */
    public boolean set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index " + index);
        }
        if (head.data.equals(data) && index == 0) {
            return true;
        } else {
            Node<E> temp = head;
            int k = 0;
            while (temp.getNext() != null || k == index) {
                if (k == index) {
                    temp.data = data;
                    return true;
                }
                temp = temp.getNext();
                k++;
            }
        }
        return false;
    }

    /**
     * returns the element in the given index
     *
     * @param index number node
     * @return data node
     */
    public E get(int index) {
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index);
        } else {
            int k = 0;
            Node<E> current = head;
            while (current != null) {
                if (k == index) {
                    return current.data;
                } else {
                    k++;
                    current = current.next;
                }
            }
        }
        return null;
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
     * remove first node
     */
    public E remove() {
        Node<E> temp = head;
        head = head.next;
        size--;
        return temp.data;
    }

    /**
     * get size linkedList
     *
     * @return number nodes
     */
    public int size() {
        return size;
    }

    /**
     * Removes the element at the specified position in this list.
     * Throws NoSuchElementException if this list is empty.
     */
    public E remove(int index) {
        if (isIndexNoCorrect(index)) {
            throw new IndexOutOfBoundsException("wrong index " + index);
        } else if (index == 0) {
            E result = head.data;
            head = null;
            size--;
            modCount++;
            return result;
        } else {
            int k = 0;
            Node<E> previous = null;
            Node<E> current = head;
            Node<E> next = current.next;
            while (current != null) {
                if (k == index) {
                    previous.next = next;
                    E result = current.data;
                    current = null;
                    size--;
                    modCount++;
                    return result;
                } else {
                    previous = current;
                    current = current.next;
                    next = current.next;
                }
                k++;
            }
        }
        return null;
    }

    private Node<E> geteNode(Node<E> temp, Node<E> pred) {
        Node<E> eNode = head;
        while (eNode.getNext() != null) {
            pred = eNode;
            if (eNode.getNext().equals(temp)) {
                break;
            }
            eNode = eNode.getNext();
        }
        assert pred != null;
        return pred;
    }

    /**
     * return number node with first equals with data
     * @param data specific element
     * @return number position if found
     */
    public int indexOf(E data) {
        Node<E> current = head;
        int k = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return k;
            }
            current = current.next;
            k++;
        }
        return -1;
    }

    /**
     * convert linkedList to array
     * @return array
     */
    public Object[] toArray() {
        if (head == null) {
            return null;
        }
        Object[] result = new Object[size];
        Node<E> current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.data;
            current = current.next;
        }
        return result;
    }

    public void reverse() {
        Node<E> previous = null;
        Node<E> current = head;
        Node<E> next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;

            head = current;
            current = next;
        }
    }

    /**
     * clear all nodes
     */
    public void clear() {
        if (head != null) {
            Node<E> next = head.next;
            while (next != null) {
                head.data = null;
                head = next;
                next = next.next;
                size--;
            }
            size--;
            modCount++;
            head = null;
        }
    }

    /**
     * representation in the form of a String
     * @return string include all nodes
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> temp = head;
        while (temp.getNext() != null) {
            joiner.add(temp.toString());
            temp = temp.getNext();
        }
        joiner.add(temp.toString());
        return joiner.toString();
    }

    /**
     * find specific element into list
     * @param data specific element
     * @return true if present
     */
    public boolean contains(E data) {
        if (head == null) {
            return false;
        } else {
            Node<E> current = head;
            while (current != null) {
                if ((current.data == null && data == null) || current.data.equals(data)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * it's return implementation fail fast iterator
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new InnerListIterator();
    }

    /**
     * inner class node
     * @param <E> specific data
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    private class InnerListIterator implements Iterator<E> {
        /* iterator step counter */
        private int index;
        /* fixed amount of modifications */
        private final int expectedModCount;
        private Node<E> current = head;

        private InnerListIterator() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("list changed");
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                index++;
                E result = current.data;
                current = current.next;
                return result;
            }
        }
    }
}
