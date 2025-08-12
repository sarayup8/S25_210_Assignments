package assn04;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You can remove the comments and test out individual methods.
    You should add more examples and use this class to debug your code.
    */

//    // Test insertions & Traversals
    BST<Integer> bst = new NonEmptyBST<Integer>(50);
    bst = bst.insert(20);
    bst = bst.insert(70);
    bst = bst.insert(60);
    bst = bst.insert(30);
    bst = bst.insert(55);
    System.out.println("\nPrintInOrderTraversal:");
    bst.printInOrderTraversal();
    System.out.println("\nPrintPreOrderTraversal:");
    bst.printPreOrderTraversal();
    System.out.println("\nPrintPostOrderTraversal:");
    bst.printPostOrderTraversal();
//
//    // Test findMin
//    System.out.println("\nfindMin:" + bst.findMin());
//
//    // Test remove
//    bst = bst.remove(50);
//    System.out.println("\nAfter removing 50:");
//    bst.printInOrderTraversal();

//    // Test removeRange
//    int s = 25;
//    int e = 57;
//    bst = bst.removeRange(s, e);
//    System.out.println("\nAfter removeRange(" + s + ", " + e + "):");
//    bst.printInOrderTraversal();

//    Test replaceRange
    int s =25;
    int e = 57;
    int r = 65;
    bst = bst.replaceRange(s, e, r);
    System.out.println("\nAfter replaceRange(" + s + ", " + e + ", " + r + "):");
    bst.printInOrderTraversal();
  }

}
