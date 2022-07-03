package com.shpp.p2p.cs.onikolaichuk.collection.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShppMapTest {
    private final ShppMap<String, String> map = new ShppMap<>();

    @BeforeEach
    void initData() {
        map.put("one", "i'm one value");
        map.put("two", "i'm two value");
        map.put("three", "i'm three value");
    }

    @Test
    void whenPutSameKeyGetAnotherValue() {
        assertEquals("i'm one value", map.put("one", "new Value"));
        assertEquals("new Value", map.put("one", "new Value"));
        assertEquals(3, map.size());
    }

    @Test
    void get() {
        assertEquals("i'm one value", map.get("one"));
    }

    @Test
    void whenAddManySameKeysSizeNoGrow() {
        map.put("add me", "five");
        for (int i = 0; i < 1_000; i++) {
            assertEquals(4, map.size());
            assertEquals("five", map.put("add me", "five"));
        }
    }

    @Test
    void whenAddManyDifferentKeysSizeGrow() {
        for (int i = 0; i < 1_000; i++) {
            assertEquals(3 + i, map.size());
            assertNull(map.put("add me + i" + i, "five"));
        }
    }

    @Test
    void whenRemoveThanGetValueKey() {
        assertEquals("i'm one value", map.remove("one"));
        assertEquals("i'm two value", map.remove("two"));
        assertEquals("i'm three value", map.remove("three"));
    }

    @Test
    void whenTestToStringThanViewAllNods() {
        ShppMap<Integer, Boolean> map1 = new ShppMap<>();
        assertEquals("", map1.toString());

        map1.put(1, true);

        assertEquals("1 key= 1, value= true", map1.toString());
    }
}