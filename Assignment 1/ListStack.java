import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class ListStack implements BKStack, Iterable<Double> {
    private Node top;
    private int size;
    private int modCount = 0;

    private class Node {
        double data;
        Node next;

        Node(double data) {
            this.data = data;
        }
    }

    @Override
    public void push(double d) {
        Node newNode = new Node(d);
        newNode.next = top;
        top = newNode;
        size++;
        modCount++;
    }

    @Override
    public double pop() {
        if (isEmpty())
            throw new EmptyStackException();
        double temp = top.data;
        top = top.next;
        size--;
        modCount--;
        return temp;
    }

    @Override
    public double peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int count() {
        return size;
    }

    @Override
    public Iterator<Double> iterator() {
        return new StockIterator();
    }

    private class StockIterator implements Iterator<Double> {
        Node current = top;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Double next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            double temp = current.data;
            current = current.next;
            return temp;
        }

    }

}
