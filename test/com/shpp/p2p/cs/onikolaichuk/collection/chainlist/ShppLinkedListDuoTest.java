package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShppLinkedListDuoTest {
    private final ShppLinkedListDuo<String> listDuo = new ShppLinkedListDuo<>();

    @BeforeEach
    void initList() {
        listDuo.add("one");
        listDuo.add("two");
        listDuo.add("three");
    }

    @Test
    void testToString() {
        assertEquals("[one, two, three]", listDuo.toString());
    }

    @Test
    void testAdd() {
        listDuo.add(0, "zero");
        assertEquals("[zero, one, two, three]", listDuo.toString());

        listDuo.add(1, "seven");
        assertEquals("[zero, seven, one, two, three]", listDuo.toString());

        listDuo.add(4, "end");
        assertEquals("[zero, seven, one, two, end, three]", listDuo.toString());
    }

    @Test
    void deleteFirst() {
        assertEquals("one", listDuo.deleteFirst());
        assertEquals("[two, three]", listDuo.toString());
        assertEquals(2, listDuo.size());
    }

    @Test
    void whenAddLastThanAddToTail() {
        assertEquals("[one, two, three]", listDuo.toString());
        listDuo.addLast("four");
        assertEquals("[one, two, three, four]", listDuo.toString());
    }

    @Test
    void whenAddLastIntoEmptyListThanOneElementPresent() {
        ShppLinkedListDuo<Boolean> list = new ShppLinkedListDuo<>();
        list.addLast(true);
        assertEquals(1, list.size());
        assertEquals("[true]", list.toString());
    }

    @Test
    void whenAddAllToEmptyListThanGetListWithAllElements() {
        ShppLinkedListDuo<Integer> linkedListDuo = new ShppLinkedListDuo<>();
        List<Integer> integers = List.of(1, 2, 3);
        linkedListDuo.addAll(0, integers);
        assertEquals(3, linkedListDuo.size());
        assertEquals("[1, 2, 3]", linkedListDuo.toString());
    }

    @Test
    void whenAddAllToSpecificPositionThanExpectAddElements() {
        List<String> addList = List.of("two2", "three3");
        assertEquals("[one, two, three]", listDuo.toString());
        listDuo.addAll(1, addList);
        assertEquals("[one, two2, three3, two, three]", listDuo.toString());
        assertEquals(5, listDuo.size());
    }

    @Test
    void whenAddAllToTheEndThanExpectAddElementsToTail() {
        List<String> addList = List.of("four", "five");
        assertEquals("[one, two, three]", listDuo.toString());
        listDuo.addAll(2, addList);
        assertEquals("[one, two, four, five, three]", listDuo.toString());
    }

    @Test
    void whenAddAllToTheWrongPositionExpectError() {
        List<String> addList = List.of("four", "five");
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> listDuo.addAll(17, addList));
        assertEquals("wrong index 17 size: 3", exception.getMessage());
    }

    @Test
    void whenDeleteLastThanDeleteTailChain() {
        assertEquals("three", listDuo.deleteLast());
        assertEquals(2, listDuo.size());
        assertEquals("[one, two]", listDuo.toString());
    }

    @Test
    void whenDeleteLastIfListEmptyGetError() {
        ShppLinkedListDuo<Object> list = new ShppLinkedListDuo<>();
        Throwable except = assertThrows(NoSuchElementException.class, list::deleteLast);
        assertEquals("Empty linked list", except.getMessage());
    }

    @Test
    void whenRemoveUseIndexThanGetElementUnderItsIndex() {
        assertEquals("two", listDuo.remove(1));
        assertEquals("three", listDuo.remove(1));
        assertEquals("one", listDuo.remove(0));
        assertEquals(0, listDuo.size());
    }

    @Test
    void whenGetFirstThanFirstElement() {
        assertEquals("one", listDuo.getFirst());
        assertEquals(3, listDuo.size());
    }

    @Test
    void whenGetUseDataThanElementUnderIts() {
        assertEquals("two", listDuo.get(1));
        assertEquals("two", listDuo.get(1));
        assertEquals(3, listDuo.size());
    }

    @Test
    void whenAddOrRemoveThanSizeChange() {
        ShppLinkedListDuo<Integer> list = new ShppLinkedListDuo<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals("[1, 2, 3, 4]", list.toString());
        assertEquals(4, list.size());
        list.remove(0);
        assertEquals("[2, 3, 4]", list.toString());
        assertEquals(3, list.size());

        list.remove(2);
        assertEquals("[2, 3]", list.toString());
        assertEquals(2, list.size());

        list.add(4);
        list.add(5);
        assertEquals("[2, 3, 4, 5]", list.toString());
        assertEquals(4, list.size());

        list.remove(1);
        assertEquals("[2, 4, 5]", list.toString());
        assertEquals(3, list.size());
    }
}