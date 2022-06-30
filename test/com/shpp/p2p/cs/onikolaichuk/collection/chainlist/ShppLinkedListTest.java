package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import com.shpp.p2p.cs.onikolaichuk.collection.list.ShppList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ShppLinkedListTest {
    private ShppLinkedList<Integer> intLinkedList = new ShppLinkedList<>();
    @BeforeEach
    public void initList() {
        intLinkedList.add(1);
        intLinkedList.add(2);
        intLinkedList.add(3);
    }
    @Test
    void whenAddThenTrue() {
        assertTrue(intLinkedList.add(1));
        assertTrue(intLinkedList.add(10));
    }

    @Test
    void whenAddUseIndexThenExpectedShiftPresentElement() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
        intLinkedList.add(1, 888);
        assertEquals("[1, 888, 2, 3]", intLinkedList.toString());
    }

    @Test
    void whenAddAllThenGrowCurrent() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        assertEquals("[100, 200, 300]", list.toString());
        intLinkedList.addAll(1, list);
        assertEquals("[100, 200, 300]", intLinkedList.toString());
    }

    @Test
    void linkBefore() {
    }

    @Test
    void set() {
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void testToString() {
    }


}