package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.*;

public class ShppLinkedList<E> implements Iterable<E> {
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
            modCount++;
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
     * if list empty return null
     *
     * @return first element data
     */
    public E peek() {
        return head == null ? null : head.data;
    }

    /**
     * returns and remove head
     *
     * @return element head
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
            throw new IndexOutOfBoundsException("Wrong index " + index);
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

    private boolean isIndexNoCorrect(int index) {
        return (index < 0 || index >= size);
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

    public int indexOf(E e) {
        Node<E> current = head;
        int k = 0;
        while (current != null) {
            if (current.data.equals(e)) {
                return k;
            }
            current = current.next;
            k++;
        }
        return -1;
    }

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

    public boolean contains(E element) {
        if (head == null) {
            return false;
        } else {
            Node<E> current = head;
            while (current != null) {
                if ((current.data == null && element == null) || current.data.equals(element)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerListIterator();
    }


    class Node<E> {
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
