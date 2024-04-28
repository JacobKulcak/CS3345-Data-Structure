// 3.11
public class List {

  // Node class
  private class Node {
    int value;
    Node next;

    public Node(int v) {
      this.value = v;
      this.next = null;
    }
  }

  private Node header;

  public List() {
    this.header = new Node(-1);
  }

  // a.
  // this function returns the size of the linked list by traversing it and counting the number of nodes
  // Time Complexity: O(n) where n is the number of nodes in the list
  public int size() {
    int counter = 0;
    Node current = header.next;

    // count until last node reached
    while (current != null) {
      counter++;
      current = current.next;
    }
    return counter;
  }


  // b.
  // This function traverses through the list and prints out the values of each node
  // Time Complexity: O(n) where n is the number of nodes in the list
  public void print(){
    Node current = header.next;

    // print value of all nodes until last one reached
    while (current != null) {
      System.out.print(current.value + " -> ");
      current = current.next;
    }
    // once last node is reached, print "null"
    System.out.println("NULL");
  }

  // c. 
  // This function traverses through the list until it finds the node with the value that is passed in as a parameter
  // If it is found, it returns true - if not, false
  // Time omplexity: O(n) where n is the number of nodes in the list
  public boolean search(int key) {
    Node current = header.next;

    // check every node's value until last one reached
    while (current != null) {
      if (current.value == key){
        return true;
      }
      current = current.next;
    }
    return false;
  }

  // d.
  // This function inserts the value of 'key' into the beginning of the list, if it is not in the list already
  // Time Complexity: O(n) + O(n) = O(2n) -> O(n), where n is the number of nodes in the list
  public void insert(int key) {
   // only add value if it's not in List yet
    if (!search(key)) {
      Node newNode = new Node(key);
      newNode.next = header.next;
      header.next = newNode;
    }
  }

  // e.
  // This function removes the node containing the value of "key"
  // Time Complexity: O(n) where n is the number of nodes in the list.
  public void remove(int key){
    Node current = header;
    // find node with value to be removed
    // here, before moving to next node, check if next one is null
    while (current.next != null) {
      // traversing backwards is not possible, so check if next node's value is key
      if (current.next.value == key) {
        current.next = current.next.next;
        // then, set current node to point to node after node with key (skip node with key)
        return;
      }
      current = current.next;
    }
  } 
}