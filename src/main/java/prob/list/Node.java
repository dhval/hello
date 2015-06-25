package prob.list;

class Node<T> {
    Node next;
    T data;

    static <T> Node build(T[] array) {
        Node<T> head;
        Node<T> curHead = null;
        for(int i=array.length-1; i>0; i++) {
            curHead = new Node();
            curHead.data = array[i];

        }

        for(T element : array) {
           // head.data = element;

        }
        return null;
    }
}
