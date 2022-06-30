package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import static org.junit.jupiter.api.Assertions.*;

import com.shpp.p2p.cs.onikolaichuk.collection.list.ShppList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;


public class ShppListTest {
    private ShppList<String> stringShppList = new ShppList<>();

    @BeforeEach
    public void initList() {
        stringShppList.add("one");
        stringShppList.add("two");
        stringShppList.add("three");
    }

    @Test
    public void whenAddElementThenTrue() {
        assertTrue(stringShppList.add("four"));
    }

    @Test
    public void whenGetIndexPresentThenEquals() {
        assertEquals("one", stringShppList.get(0));
        assertEquals("three", stringShppList.get(2));
    }

    @Test
    public void whenSetInTheMiddleOfThearrayExpectTrueAndElementsToTheRightAreContained() {
        assertEquals("[one, two, three]", stringShppList.toString());
        assertTrue(stringShppList.set(1, "XXX"));
        assertEquals("[one, XXX, two, three]", stringShppList.toString());
        assertTrue(stringShppList.set(3, "YYY"));
        assertEquals("[one, XXX, two, YYY, three]", stringShppList.toString());
    }

    @Test
    public void whenSetExpectTrueAndSizeGrow() {
        assertEquals("one", stringShppList.get(0));
        assertTrue(stringShppList.set(0, "doubleOne"));
        assertEquals("doubleOne", stringShppList.get(0));
    }

    @Test
    public void whenRemoveByIndexThenGiveElement() {
        assertEquals("one", stringShppList.remove(0));
    }

    @Test
    public void whenRemoveByElementThenTrueIfSuccess() {
        assertTrue(stringShppList.remove("one"));
    }

    @Test
    public void whenRemoveByElementThenFalseIfNoPresentElement() {
        stringShppList.remove("one");
        assertFalse(stringShppList.remove("one"));
    }


    @Test
    public void testThrowsIndexOutOfBoundsException() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> stringShppList.get(13));
        assertEquals("Illegal index: 13", exception.getMessage());
    }

    @Test
    public void whenAddOrRemoveThenSizeChange() {
        assertEquals(3, stringShppList.size());
        stringShppList.add("five");
        assertEquals(4, stringShppList.size());
        stringShppList.remove(4);
        assertEquals(3, stringShppList.size());
    }

    @Test
    public void whenIndexOfElementThenItsIndex() {
        assertEquals(1, stringShppList.indexOf("two"));
    }

    @Test
    public void whenClearThenSizeListZero() {
        assertEquals(3, stringShppList.size());
        stringShppList.clear();
        assertEquals(0, stringShppList.size());
    }

    @Test
    public void whenContainsThenTrue() {
        assertTrue(stringShppList.contains("three"));
    }

    @Test
    public void whenNoContainsThenFalse() {
        assertFalse(stringShppList.contains("zzz"));
    }

    @Test
    public void whenListElementPresentIsEmptyThenFalse() {
        assertFalse(stringShppList.isEmpty());
    }

    @Test
    public void whenListEmptyIsEmptyThenTrue() {
        stringShppList = new ShppList<>();
        assertTrue(stringShppList.isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals("[one, two, three]", stringShppList.toString());
    }

    @Test
    public void whenIteratorHasNextTrueItsNextGiveNextElement() {
        int i = 0;
        Iterator<String> iterator = stringShppList.iterator();
        while (iterator.hasNext()) {
            assertEquals(stringShppList.get(i++), iterator.next());
        }
    }

    @Test
    public void whenExpectedCallGrowMethodAndResizeInnerArray() {
        ShppList<Integer> list = new ShppList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(10, list.size());
        list.add(11);
        assertEquals(11, list.size());
    }

    @Test
    public void whenCreateUseConstructorWithParameterThenCreateListWithSpecificSize() {
        ShppList<Boolean> list = new ShppList<>(17);
        assertTrue(list.add(true));
        list = new ShppList<>(2);
        assertTrue(list.add(true));
    }
}