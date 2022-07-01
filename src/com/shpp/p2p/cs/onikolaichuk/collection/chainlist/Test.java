package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> ipo = new LinkedList<>();
        ipo.add(1);
        ipo.add(null);
        ipo.add(2);
        ipo.add(null);
        ipo.add(3);
        ipo.add(null);
        System.out.println(ipo);
        System.out.println(ipo.contains(2));
        System.out.println(ipo.contains(null));

    }
}
