package com.shpp.p2p.cs.onikolaichuk.collection.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ShppStackTest {
    private final ShppStack<String> stack = new ShppStack<>();
    @BeforeEach
    void initData() {
        stack.push("one");
        stack.push("two");
        stack.push("three");
    }

    @Test
    void whenPushThanAddElement() {
        ShppStack<Integer> integers = new ShppStack<>();
        integers.push(1);
        assertEquals("[1]", integers.toString());

        integers.push(2);
        assertEquals("[1, 2]", integers.toString());
    }

    @Test
    void whenPopThanRemoveElementFromTopStack() {
        assertEquals("three", stack.pop());
        assertEquals("two", stack.pop());
        assertEquals("one", stack.pop());
    }

    @Test
    void whenPopInEmptyStackThanGetException() {
        ShppStack<Integer> integers = new ShppStack<>();
        Throwable exception = assertThrows(EmptyStackException.class, (integers::pop));
        assertNull(exception.getMessage());
    }

    @Test
    void whenPeekThanViewWithoutRemoveElement() {
        for (int i = 0; i < 10; i++) {
            assertEquals("three", stack.peek());
        }
    }

    @Test
    void whenEmptyThanTrue() {
        ShppStack<Integer> integers = new ShppStack<>();
        assertTrue(integers.empty());
    }
    @Test
    void whenNoEmptyThanFalse() {
        assertFalse(stack.empty());
    }

    @Test
    void whenSearchSpecificElementThanGetIndex() {
        assertEquals(0, stack.search("one"));
        assertEquals(1, stack.search("two"));
        assertEquals(2, stack.search("three"));
    }

    @Test
    void whenSearchElementNoPresentThanGetNegativeOne() {
        assertEquals(-1, stack.search("foo"));
    }

    @Test
    void whenSizeThanLengthInnerDataStructure() {
        assertEquals(3, stack.size());
        stack.push("four");
        assertEquals(4, stack.size());
        stack.pop();
        assertEquals(3, stack.size());
    }

    @Test
    void testToString() {
        assertEquals("[one, two, three]", stack.toString());
        stack.push("four");
        assertEquals("[one, two, three, four]", stack.toString());
    }
}