// 4.23
public class AVLTree {

  private class Node {
    int key, height;
    Node left, right;

    Node(int key) {
      this.key = key;
      height = 1;
    }
  }

  private Node root;

  // This function returns the height of a node
  // Time Complexity: O(1)
  private int height(Node node) {
    return node == null ? 0 : node.height;
  }

  // This function updates the height of a node
  // Time Complexity: O(1)
  private void updateHeight(Node node) {
    node.height = 1 + Math.max(height(node.left), height(node.right));
  }

  // This function returns the balance factor of a node
  // Time Complexity: O(1)
  private int getBalance(Node node) {
    return node == null ? 0 : height(node.left) - height(node.right);
  }

  // Right rotation
  private Node rightRotate(Node y) {
    Node x = y.left;
    Node T3 = x.right;

    // Perform rotation
    x.right = y;
    y.left = T3;

    // Update heights
    updateHeight(y);
    updateHeight(x);

    return x; // New root
  }

  // Left rotation
  private Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    // Perform rotation
    y.left = x;
    x.right = T2;

    // Update heights
    updateHeight(x);
    updateHeight(y);

    return y; // New root
  }

  // This function inserts a key into the tree
  // Time Complexity: O(log n)
  public void insert(int key) {
    if (root == null) {
      root = new Node(key);
      return;
    }

    Node parent = null, current = root;
    Stack<Node> path = new Stack<>();

    while (current != null) {
      parent = current;
      path.push(parent);

      if (key < current.key) {
        current = current.left;
      } else if (key > current.key) {
        current = current.right;
      } else { // Duplicate keys are not allowed
        return;
      }
    }

    if (key < parent.key) {
      parent.left = new Node(key);
    } else {
      parent.right = new Node(key);
    }

    // Rebalance the tree
    while (!path.isEmpty()) {
      current = path.pop();

      // Update height
      updateHeight(current);

      // Check for imbalance and perform rotations
      int balance = getBalance(current);

      // Left heavy
      if (balance > 1) {
        if (key < current.left.key) {
          current = rightRotate(current);
        } else {
          current.left = leftRotate(current.left);
          current = rightRotate(current);
        }
      }
      // Right heavy
      else if (balance < -1) {
        if (key > current.right.key) {
          current = leftRotate(current);
        } else {
          current.right = rightRotate(current.right);
          current = leftRotate(current);
        }
      }

      if (path.isEmpty()) {
        root = current; // update root if at the top
      } else if (path.peek().left == current) {
        path.peek().left = current; // update parent's left child reference
      } else {
        path.peek().right = current; // update parent's right child reference
      }
    }
  }
}
