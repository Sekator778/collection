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
    void whenTestToStringThenViewStringSequencesElements() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
    }

    @Test
    void whenAddThenTrue() {
        assertTrue(intLinkedList.add(1));
        assertTrue(intLinkedList.add(10));
    }

    @Test
    void whenAddFirstThenTrue() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
        intLinkedList.add(0, 777);
        assertEquals("[777, 1, 2, 3]", intLinkedList.toString());
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
        assertEquals("[1, 100, 200, 300, 2, 3]", intLinkedList.toString());
    }

    @Test
    void whenPeekThanLookFirstElement() {
        assertEquals(1, intLinkedList.peek());
        assertEquals(1, intLinkedList.remove());
        assertEquals(2, intLinkedList.peek());
    }

    @Test
    void whenAddFirstHeadUpdate() {
        assertEquals(1, intLinkedList.peek());
        intLinkedList.addFirst(31);
        assertEquals(31, intLinkedList.peek());

        ShppLinkedList<String> list = new ShppLinkedList<>();

        list.addFirst("one");
        assertEquals("one", list.peek());
        assertEquals("[one]", list.toString());

        list.addFirst("zero");
        assertEquals("zero", list.peek());
        assertEquals("[zero, one]", list.toString());
    }

    @Test
    void whenAddLastThenElementAddToTailChain() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
        intLinkedList.addLast(777);
        assertEquals("[1, 2, 3, 777]", intLinkedList.toString());
    }


    @Test
    void whenSetInCurrentPositionThanElementsShiftToTheRight() {
        assertTrue(intLinkedList.set(0, 17));
        assertEquals(17, intLinkedList.peek());
        assertEquals("[17, 1, 2, 3]", intLinkedList.toString());

    }

    @Test
    void rt() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("[1, 2, 3]", list.toString());
        assertEquals(1, list.set(0, 17));
        assertEquals(17, list.peek());
        assertEquals("[17, 2, 3]", list.toString());


    }

    @Test
    void get() {
    }

    @Test
    void whenRemoveThenGetFirstElement() {
        assertEquals(1, intLinkedList.remove());
        assertEquals(2, intLinkedList.remove());
        assertEquals(3, intLinkedList.remove());
    }

    @Test
    void size() {
    }

    @Test
    void testRemove() {
    }


    @Test
    void whenClearThanEmptyChain() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());
        intLinkedList.clear();
        assertEquals("[]", intLinkedList.toString());
    }
    @Test
    void whenClearEmptyThanEmptyChain() {
        ShppLinkedList<Boolean> list = new ShppLinkedList<>();
        assertEquals("[]", list.toString());

        list.clear();
        assertEquals("[]", list.toString());

        list.add(true);
        assertEquals("[true]", list.toString());
        list.clear();
        assertEquals("[]", list.toString());
    }


}