package sample.algo.ds;

import java.util.*;

public class Stack < E > {

    private List < E > list = new ArrayList < > ();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E item) {
        list.add(item);
    }

    public E pop() {
        if (list.isEmpty())
            return null;
        return list.remove(list.size() - 1);
    }


}