// 3.33
public class CircularQueue {
  private int[] data; // array
  private int front;
  private int rear;
  private int size; // to keep track of the size
  private final int cap; // maximum capacity of Queue

  public CircularQueue(int capacity) {
    this.cap = capacity;
    data = new int[cap];
    front = -1;
    rear = -1;
    size = 0;
  }

  // This function checks if the queue is empty
  // Time Complexity: O(1)
  public boolean isEmpty() {
    return size == 0;
  }

  // This function checks if the queue is full
  // Time Complexity: O(1)
  public boolean isFull() {
    return size == cap;
  }

  // This function enqueues element
  // Time Complexity: O(1)
  public void enqueue(int item) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
      // can't enqueue if it's full -> throw exception
    }

    if (isEmpty()) {
      front = 0;
    }

    rear = (rear + 1) % cap;
    data[rear] = item; // set item at rear
    size++;
  }

  // This function dequeues element
  // Time Complexity: O(1)
  public int dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
      // can't dequeue if it's empty -> throw exception
    }

    int item = data[front]; // get item
    front = (front + 1) % cap;
    size--;

    if (isEmpty()) {
      front = -1;
      rear = -1;
      // setting it to arbitrary numbers again if empty
    }

    return item;
  }

  // This function gets the front item without removing it (just looking at it)
  // Time Complexity: O(1)
  public int peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
      // if empty, nothing to look at -> throw exception
    }
    return data[front];
  }
}
