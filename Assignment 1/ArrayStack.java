import java.util.EmptyStackException;

public class ArrayStack implements BKStack {
    private static final int INITIAL_CAPACITY = 10;

    private double[] stack;
    private int top;

    public ArrayStack() {
        this.stack = new double[INITIAL_CAPACITY];
        this.top = -1;
    }

    @Override
    public void push(double d) {
        if (top == stack.length - 1) {
            resize();
        }
        stack[++top] = d;
    }

    @Override
    public double pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack[top--];
    }

    @Override
    public double peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int count() {
        return top + 1;
    }

    private void resize() {
        double[] newStack = new double[stack.length * 2];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }
}
