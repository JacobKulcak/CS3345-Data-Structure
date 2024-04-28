// 4.34
class TreeNode {
  int value;
  TreeNode left;
  TreeNode right;

  TreeNode(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

public class RandomBST {

  private static int getRandomValue(int lower, int upper) {
    return lower + (int) (Math.random() * (upper - lower + 1));
  }

  // This function recursively chooses a random number within a specified range to
  // be the root and then creates left and right subtrees
  // It still makes sure to return a valid BST
  // Time Complexity:
  // - Worst Case: O(n^2) -> tree could be skewed to the left or right
  // - Best Case: O(n) -> tree is balanced
  private static TreeNode buildRandomTree(int lower, int upper) {
    if (lower > upper) {
      return null;
    }

    int randomValue = getRandomValue(lower, upper);
    TreeNode rootNode = new TreeNode(randomValue);

    rootNode.left = buildRandomTree(lower, randomValue - 1);
    rootNode.right = buildRandomTree(randomValue + 1, upper);

    return rootNode;
  }

  public static TreeNode generateRandomBST(int n) {
    return buildRandomTree(1, n);
  }
}