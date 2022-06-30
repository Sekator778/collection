package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> ipo = new ArrayList<>();
        ipo.add(1);
        ipo.add(2);
        ipo.add(3);
        System.out.println(ipo);
        ipo.set(3, 777);
        System.out.println(ipo);

    }
}
