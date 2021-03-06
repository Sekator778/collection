package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShppLinkedListTest {
    private final ShppLinkedList<Integer> intLinkedList = new ShppLinkedList<>();

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
        List<Integer> list = List.of(100, 200, 300);
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
    void whenPeekIfListEmptyThanGetNull() {
        intLinkedList.clear();
        assertNull(intLinkedList.peek());
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
    void whenSetInCurrentPositionThanElementChange() {
        assertTrue(intLinkedList.set(0, 17));
        assertEquals(17, intLinkedList.peek());
        assertEquals("[17, 2, 3]", intLinkedList.toString());
    }

    @Test
    void whenSetInWrongIndexNumberThanExpectIndexOutBoundException() {
        Throwable throwable = assertThrows(
                IndexOutOfBoundsException.class, () -> intLinkedList.set(777, 31));
        assertEquals("Wrong index 777", throwable.getMessage());
    }

    @Test
    void whenGetThanGetElement() {
        assertEquals(1, intLinkedList.get(0));
        assertEquals(2, intLinkedList.get(1));
        assertEquals(3, intLinkedList.get(2));
    }

    @Test
    void whenGetWrongIndexThanGetIndexOutBoundException() {
        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class, () -> intLinkedList.get(31));
        assertEquals("wrong index 31", exception.getMessage());
    }

    @Test
    void whenRemoveThenGetFirstElement() {
        assertEquals(1, intLinkedList.remove());
        assertEquals(2, intLinkedList.remove());
        assertEquals(3, intLinkedList.remove());
    }

    @Test
    void sizeReturnCountElementsInList() {
        assertEquals(3, intLinkedList.size());
        intLinkedList.add(17);
        assertEquals(4, intLinkedList.size());
    }

    @Test
    void whenIndexOfThanReturnFirstPositionCurrentElement() {
        assertEquals(0, intLinkedList.indexOf(1));
        assertEquals(1, intLinkedList.indexOf(2));
        assertEquals(2, intLinkedList.indexOf(3));
    }

    @Test
    void whenIndexOfElementNoPresentThanReturnNegativeOne() {
        assertEquals(-1, intLinkedList.indexOf(-1));
        assertEquals(-1, intLinkedList.indexOf(765));
    }

    @Test
    void whenPollThanGetDataOfCurrentHead() {
        assertEquals(1, intLinkedList.poll());
        assertEquals(2, intLinkedList.poll());
        assertEquals(3, intLinkedList.poll());
        assertNull(intLinkedList.poll());
    }

    @Test
    void testRemove() {
        assertEquals(3, intLinkedList.size());

        assertEquals(1, intLinkedList.remove());
        assertEquals(2, intLinkedList.size());

        assertEquals(2, intLinkedList.remove());
    }

    @Test
    void whenRemoveUseIndexThanGetElement() {
        assertEquals("[1, 2, 3]", intLinkedList.toString());

        assertEquals(2, intLinkedList.remove(1));
        assertEquals("[1, 3]", intLinkedList.toString());


        assertEquals(3, intLinkedList.remove(1));
        assertEquals("[1]", intLinkedList.toString());

        assertEquals(1, intLinkedList.remove(0));
        assertEquals("[]", intLinkedList.toString());
    }

    @Test
    void whenRemoveWrongIndexThanGetIndexOutOfBoundException() {
        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class, () -> intLinkedList.remove(31));
        assertEquals("wrong index 31", exception.getMessage());
    }

    @Test
    void whenRemoveEmptyLinkedListThanError() {
        ShppLinkedList<Boolean> list = new ShppLinkedList<>();
        Throwable exception = assertThrows(NoSuchElementException.class, list::remove);
        assertEquals("list empty size: 0", exception.getMessage());
    }

    @Test
    void whenContainsThenTrue() {
        assertTrue(intLinkedList.contains(1));
        assertTrue(intLinkedList.contains(2));
        assertTrue(intLinkedList.contains(3));
        assertFalse(intLinkedList.contains(4));
        assertFalse(intLinkedList.contains(null));
        intLinkedList.add(null);
        assertTrue(intLinkedList.contains(null));
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

    @Test
    void whenToArrayThanGetArrayObjects() {
        Integer[] integers = {1, 2, 3};
        assertArrayEquals(integers, intLinkedList.toArray());
    }

    @Test
    void whenIteratorTestThanSequenceElement() {
        int k = 1;
        Iterator<Integer> iterator = intLinkedList.iterator();
        while (iterator.hasNext()) {
            assertEquals(k++, iterator.next());
        }
    }

    @Test
    void whenTestFailFastBehaviorIteratorExpectConcurrentModificationException() {
        Iterator<Integer> iterator = intLinkedList.iterator();
        intLinkedList.add(7);
        Throwable except = assertThrows(ConcurrentModificationException.class, iterator::next);
        assertEquals("list changed", except.getMessage());
    }


}