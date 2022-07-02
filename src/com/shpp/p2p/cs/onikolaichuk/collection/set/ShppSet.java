package com.shpp.p2p.cs.onikolaichuk.collection.set;

import com.shpp.p2p.cs.onikolaichuk.collection.list.ShppList;

/**
 * educational data structure to show the uniqueness of elements
 * and presence of null
 *
 * @param <E> generic
 */
public class ShppSet<E> {
    private final ShppList<E> list = new ShppList<>();
    /* monitors availability null */
    private boolean checkAddNull;

    /**
     * method checks the presence of an element in the structure
     * @param value data for add
     * @return true if possible
     */
    public boolean contains(E value) {
        if (value == null && !checkAddNull) {
            return false;
        }
        if (checkAddNull) {
            return true;
        }
        for (E element : list) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * add an element if it doesn't have one
     * or return false
     * @param data element for add
     * @return true if success
     */
    public boolean add(E data) {
        if (contains(data)) {
            return false;
        } else {
            if (data == null) {
                checkAddNull = true;
            }
            return list.add(data);
        }
    }
}
