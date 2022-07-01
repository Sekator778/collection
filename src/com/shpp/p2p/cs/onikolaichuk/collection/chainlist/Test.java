package com.shpp.p2p.cs.onikolaichuk.collection.chainlist;

import java.util.Arrays;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> ipo = new LinkedList<>();
        ipo.add(1);
        ipo.add(2);
        ipo.add(3);
        System.out.println(ipo);
        Object[] integers = {1, 2, 3};
        Object[] objects = ipo.toArray();

        System.out.println(Arrays.equals(objects, integers));

    }
}
