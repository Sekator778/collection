package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ShppLinkedList<E> {
    private Node<E> head;
    private int size;
    private int modCount;

    /**
     * Adds the specified element to the end of this list.
     */
    public boolean add(E element) {
        if (head == null) {
            head = new Node<>(element);
            size++;
            modCount++;
        } else {
            Node<E> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<>(element));
            size++;
        }
        return true;
    }


    /**
     * Inserts an element at the specified position in this list.
     * Throws IndexOutOfBoundsException,
     * if the specified index is out of range (index < 0 || index > size()).
     */
    public void add(int index, E element) {
        if (head == null || index == 0) {
            Node<E> nodeAdd = new Node<>(element);
            nodeAdd.setNext(head);
            head = nodeAdd;
            size++;
            modCount++;
        } else {
            Node<E> current = head;
            int i = 0;
            while (current.getNext() != null) {
                if (i == index) {
                    linkBefore(current, element);
                    break;
                }
                current = current.getNext();
                i++;
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

    public void addFirst(E element) {
        if (head == null) {
            this.head = new Node<>(element);
            size++;
            modCount++;
        } else {
            add(0, element);
        }
    }

    public void addLast(E element) {
        linkLast(element);
    }

    /**
     * returns head but does not delete
     *
     * @return first element data
     */
    public E peek() {
        return head == null ? null : head.data;
    }

    /**
     * insert a new node from the element before the given one
     */
    private void linkBefore(Node<E> node, E element) {
        Node<E> pred = null;
        pred = geteNode(node, pred);
        Node<E> newNode = new Node<>(element);
        pred.setNext(newNode);
        newNode.setNext(node);
        size++;
        modCount++;
    }

    private void linkLast(E element) {
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.setNext(new Node<>(element));
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке указанным элементом.
     * Вызывает IndexOutOfBoundsException,
     * если указанный индекс выходит за пределы диапазона (index < 0 || index >= size()).
     */
    public boolean set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (head.data.equals(element) && index == 0) {
            return true;
        } else {
            Node<E> temp = head;
            int k = 0;
            while (temp.getNext() != null || k == index) {
                if (k == index) {
                    temp.data = element;
                    return true;
                }
                temp = temp.getNext();
                k++;
            }
        }
        return false;
    }


    public E get() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    /**
     * remove first
     */
    public E remove() {
        Node<E> temp = head;
        head = head.next;
        size--;
        return temp.data;
    }

    public int size() {
        return size;
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     * Вызывает исключение NoSuchElementException, если этот список пуст.
     */
    public E remove(int index) {
        if (index == 0) {
            Node<E> result = head;
            remove();
            return result.data;
        }
        Node<E> temp = head;
        int k = 0;
        Node<E> pred = null;
        while (temp != null) {
            if (k == index) {
                pred = geteNode((Node<E>) temp, (Node<E>) pred);
                pred.setNext(temp.getNext());
            }
            k++;
            temp = temp.getNext();
        }
        size--;
        assert pred != null;
        return pred.data;
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
}
