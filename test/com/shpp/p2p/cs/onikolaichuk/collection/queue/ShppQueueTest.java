package com.shpp.p2p.cs.onikolaichuk.collection.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShppQueueTest {
    private final ShppQueue<Integer> queue = new ShppQueue<>();

    @BeforeEach
    void initData() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
    }

    @Test
    void add() {
        assertTrue(queue.add(1));
        assertTrue(queue.add(2));
        assertTrue(queue.add(3));
    }

    @Test
    void remove() {
        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
    }

    @Test
    void whenRemoveIfEmptyQueueThanError() {
        ShppQueue<Boolean> queue1 = new ShppQueue<>();
        Throwable exception = assertThrows(NoSuchElementException.class, queue1::remove);
        assertEquals("list empty size: 0", exception.getMessage());
    }

    @Test
    void element() {
        assertEquals(1, queue.element());
        assertEquals(1, queue.element());
        assertEquals(1, queue.element());
    }

    @Test
    void poll() {
        assertEquals(1, queue.poll());
        queue.add(4);
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(4, queue.poll());
    }

    @Test
    void peek() {
        for (int i = 0; i < 10; i++) {
            assertEquals(1, queue.peek());
        }
    }

    @Test
    void testToString() {
        assertEquals("[1, 2, 3]", queue.toString());
        queue.add(4);
        assertEquals("[1, 2, 3, 4]", queue.toString());
    }
}