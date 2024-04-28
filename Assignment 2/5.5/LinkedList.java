// 5.5
public class LinkedList<T> {

  // Represents the first node in the LinkedList
  private Node<T> head;

  // Default constructor initializing an empty list
  public LinkedList() {
    head = null;
  }

  // Clears all nodes from the list (Time Complexity: O(1))
  public void clear() {
    head = null;
  }

  // Checks if the list contains a particular piece of data (Time Complexity:
  // O(n))
  public boolean contains(T data) {
    for (Node<T> current = head; current != null; current = current.next) {
      if (current.data.equals(data)) {
        return true;
      }
    }
    return false;
  }

  // Adds a new piece of data to the end of the list (Time Complexity: O(n))
  public void add(T data) {
    Node<T> n = new Node<>(data);
    if (head == null) {
      head = n;
    } else {
      Node<T> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = n;
    }
  }

  // Removes the first occurrence of a piece of data from the list (Time
  // Complexity: O(n))
  public void remove(T data) {
    if (head == null)
      return; // Empty list
    if (head.data.equals(data)) {
      head = head.next;
      return;
    }
    Node<T> pred = head;
    while (pred.next != null && !pred.next.data.equals(data)) {
      pred = pred.next;
    }
    if (pred.next != null) {
      pred.next = pred.next.next;
    }
  }

  // Prints all data in the list (Time Complexity: O(n))
  public void print() {
    for (Node<T> current = head; current != null; current = current.next) {
      System.out.printf("%s\t", current.data.toString());
    }
    System.out.printf("\n");
  }
}

public class Node<T> {

  // Holds the data for the node
  protected T data;

  // Points to the next node in the list
  protected Node<T> next;

  public Node(T data) {
    this.data = data;
    this.next = null;
  }
}
