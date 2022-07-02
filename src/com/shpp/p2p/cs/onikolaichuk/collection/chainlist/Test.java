package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> ipo = new LinkedList<>();

        List<Integer> list = List.of(1, 2, 3);
        ipo.addAll(1, list);
        System.out.println(ipo);
    }
}
