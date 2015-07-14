package prob.ds;

import java.util.Iterable;

public class Queue < E > implements Iterable < E > {

    public Iterator < E > iterator() {
        return new QueueIterator < E > ();
    }

}