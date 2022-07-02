package com.shpp.p2p.cs.onikolaichuk.collection.set;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShppSetTest {

    @Test
    void contains() {
        ShppSet<String> set = new ShppSet<>();
        assertFalse(set.contains("one"));
        assertFalse(set.contains("two"));
        assertFalse(set.contains("three"));
        assertFalse(set.contains(null));

        assertTrue(set.add("one"));
        assertTrue(set.add("two"));
        assertTrue(set.add("three"));
        assertTrue(set.add(null));

        assertFalse(set.add(null));

        assertTrue(set.contains("one"));
        assertTrue(set.contains("two"));
        assertTrue(set.contains("three"));

        assertTrue(set.contains(null));
    }

    @Test
    void add() {
        ShppSet<Integer> set = new ShppSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));

        assertFalse(set.add(1));
        assertFalse(set.add(2));
        assertFalse(set.add(3));
    }
}