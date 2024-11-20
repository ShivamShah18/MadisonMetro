//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: This file creates a binary search tree and methods within it
// Course: CS 300 Fall 2023
//
// Author: Shivam Shah
// Email: shah84@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Stack;

/**
 * <p>
 * The BusStopTree class represents a binary search tree of Bus objects that stop at a particular
 * BusStop (specified when constructing the tree). In other words, all Bus-es in the tree are known
 * to stop at the specified stop. We sort the Bus-es by a combination of their arrival time and
 * route names (see the Bus.compareTo() method).
 * </p>
 * 
 * <p>
 * The class provides methods to insert, search, and delete Bus objects from the tree, and iterate.
 * </p>
 */
public class BusStopTree {
  /**
   * The root node of the binary search tree.
   */
  private Node<Bus> root;

  /**
   * The bus stop this tree is organized around.
   */

  private BusStop userStop;

  /**
   * <p>
   * Construct an empty binary search tree.
   * </p>
   * 
   * @param stopId the particular stop we care about. We will filter out routes that do not go to
   *               the BusStop represented by this ID.
   */
  public BusStopTree(int stopId) {
    // Find BusStop.
    this.userStop = BusStop.getStop(stopId);
    if (this.userStop == null) {
      throw new IllegalArgumentException("cannot find stop " + stopId);
    }
    // Initialize an empty tree.
    this.root = null;
  }

  /**
   * <p>
   * Checks if the binary subtree rooted at the given node is a valid binary search tree.
   * </p>
   * 
   * <p>
   * In order to be a valid BST, the following must be true: For every internal (non-leaf) node X of
   * a binary tree, all the values in the node's left subtree are less than the value in X, and all
   * the values in the node's right subtree are greater than the value in X.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root node of the binary tree
   * @return true if the binary tree is a valid binary search tree, false otherwise
   */
  public static boolean isValidBST(Node<Bus> node) {
    // base case: empty tree is a valid tree
    if (node == null) {
      return true;
    }
    // establish initial bounds
    Node<Bus> low = getFirstBusHelper(node);
    Node<Bus> up = getLastBusHelper(node);

    return isValidBSTHelper(node, low, up);
  }

  /**
   * This is a helper method that uses bounds on recursive calls to itself to establish validity of
   * a BST
   * 
   * @param node  - local root to consider left and right subtrees of
   * @param lower - lowest bus possible given current range
   * @param upper - largest bus possible given range
   * @return true if all subtrees are valid, false otherwise
   */
  private static boolean isValidBSTHelper(Node<Bus> node, Node<Bus> low, Node<Bus> up) {
    if (node == null) {
      return true;
    }

    // this helper methods checks every edge case
    if ((node.getValue().compareTo(up.getValue()) == 0)
        && (node.getValue().compareTo(low.getValue()) == 0)) {
      return true;
    }

    if (node.getLeft() != null) {
      if (node.getLeft().getValue().compareTo(node.getValue()) == 0) {
        return false;
      }
    }

    //right keeps going
    if (node.getRight() != null) {
      if (node.getRight().getValue().compareTo(node.getValue()) == 0) {
        return false;
      }
    }

    if ((node.getValue().compareTo(up.getValue()) > 0)
        || (node.getValue().compareTo(low.getValue()) < 0)) {
      return false;
    }
    // Left

    // recursion
    if (!isValidBSTHelper(node.getLeft(), low, node)) {
      return false;
    }
    // Checking right
    if (!isValidBSTHelper(node.getRight(), node, up)) {
      return false;
    }

    return true;
  }

  /**
   * <p>
   * Adds a new Bus to this BusStopTree.
   * </p>
   * 
   * <p>
   * If the root is null, creates a new node with the given bus and sets it as the root. Otherwise,
   * calls the addBusHelper method to recursively add the bus to the tree. If the bus is already in
   * the tree, it will not be added again (return false).
   * </p>
   *
   * @param bus the Bus to be added to the tree
   * @return true if the bus was successfully added, false otherwise
   * @throws IllegalArgumentException if the bus does not go to this tree's stop
   */
  public boolean addBus(Bus bus) {
    if (!bus.goesTo(userStop)) {
      throw new IllegalArgumentException("attempt to add bus that does not go to stop");
    }

    // adding a bus
    // base case
    if (root == null) {
      root = new Node<Bus>(bus);
      return true;
    }

    return addBusHelper(root, bus);
  }

  /**
   * <p>
   * Adds a new bus to the binary search tree rooted at the given node. If the bus is already in the
   * tree, it will not be added again.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root node of the tree to add the bus to
   * @param bus  the bus to add to the tree
   * @return true if the bus was successfully added, false otherwise
   */
  public boolean addBusHelper(Node<Bus> node, Bus bus) {
    if (bus == null) {
      return true;
    }
    
    // Compare buses and decide the direction to traverse
    int comparison = bus.compareTo(node.getValue());

    // If the bus is already in the tree, do not add it again
    if (comparison == 0) {
      return false;
    } else if (comparison < 0) { // If bus is less than the current node, go left
      if (node.getLeft() == null) {
        // Add the bus as a new left child
        node.setLeft(new Node<>(bus));
        return true;
      } else {
        // Recursively add the bus to the left subtree
        return addBusHelper(node.getLeft(), bus);
      }
    } else { // If bus is greater than the current node, go right
      if (node.getRight() == null) {
        // Add the bus as a new right child
        node.setRight(new Node<>(bus));
        return true;
      } else {
        // Recursively add the bus to the right subtree
        return addBusHelper(node.getRight(), bus);
      }
    }



  }

  /**
   * <p>
   * Removes the given bus from the BusStopTree.
   * </p>
   * 
   * <p>
   * This method should handle the case where the tree is empty and should call removeBusHelper to
   * recursively remove the nodes from the tree.
   * </p>
   * 
   * @param bus the bus to be removed
   * @return true if the bus was successfully removed, false otherwise. Specifically, this method
   *         returns false if the tree is empty or the requested Bus is not in the tree.
   */
  public boolean removeBus(Bus bus) {
    // Remove from an empty tree
    if (root == null) {
      return false;
    }

    Node<Bus> newRoot = removeBusHelper(root, bus);

    // Removing the root node?
    if (bus.compareTo(root.getValue()) == 0) {
      root = newRoot;
      return true;
    } else {
      // The only non-success case where we newRoot can be null is when we are removing the last
      // node in the tree. We handle that case in the if-case above. Here a null value indicates
      // failure.
      return newRoot != null;
    }
  }

  /**
   * <p>
   * This method is <b>OPTIONAL</b>. You do not need to implement it for full credit.
   * <b>However,</b> you should absolutely implement it at some point! This is an important
   * functionality of BSTs, and we may ask you to do it in the future, hint, hint...
   * </p>
   * 
   * <p>
   * Removes a bus from the binary search subtree rooted at the given node and returns the new root
   * of the tree (which may or may not be the same as the old root). Note that the caller is
   * responsible for updating its references to the root. Also note that the "root" of a subtree
   * might be the left or right child of another node.
   * </p>
   * 
   * <p>
   * Hint: draw lots of pictures!
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * Note: this method is optional and you will not be graded on it. However, it is highly
   * encouraged you learn to do recursive remove.
   * 
   * @param node the current root node of the binary search subtree
   * @param bus  the bus to be removed from the binary search subtree
   * @return the root node of this binary search subtree after the bus has been removed, or null if
   *         the bus was not found in the subtree.
   */
  public Node<Bus> removeBusHelper(Node<Bus> node, Bus bus) {
    return null; // TODO (optional): default return value... fix me!
  }

  /**
   * <p>
   * Checks if the BusStopTree contains a given Bus object.
   * </p>
   * 
   * <p>
   * This method should handle the case where the tree is empty and then call containsHelper.
   * </p>
   * 
   * @param bus the Bus object to search for
   * @return true if the BusStopTree contains the Bus object, false otherwise
   */
  public boolean contains(Bus bus) {
    if (root == null) {
      return false;
    }
    // calls the containsHelper
    return containsHelper(root, bus);
  }

  /**
   * <p>
   * Checks if the given bus is contained in the subtree rooted at the given node using the
   * Bus.compareTo method to compare Bus-es.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root of the subtree to search in
   * @param bus  the bus to search for
   * @return true if the bus is found in the subtree, false otherwise
   */
  public boolean containsHelper(Node<Bus> node, Bus bus) {

    
    //base case 
    if (node == null) {

      return false; // The subtree is empty

    }



    int comparison = bus.compareTo(node.getValue());



    if (comparison < 0) {

      // The bus is smaller than the current node, search in the left subtree

      return containsHelper(node.getLeft(), bus);

    } else if (comparison > 0) {

      // The bus is larger than the current node, search in the right subtree

      return containsHelper(node.getRight(), bus);

    } else {

      // The buses are equal, the bus is found

      return true;

    }

  }



  /**
   * Returns an iterator over all buses arriving at this tree's stop at or after the given time.
   * 
   * @param time the time at or after which all Bus-es returned by the iterator arrive at the stop.
   * @return an Iterator that returns all Bus-es at or after the given time.
   */
  public Iterator<Bus> getNextBuses(LocalTime time) {
    return new BusForwardIterator(root, getFirstNodeAfter(time, root));
  }

  /**
   * <p>
   * Returns the node with the soonest arrival time at userStop that is at or after the given
   * `time`, in the subtree rooted at the given node. That is, within the subtree rooted at node,
   * find the first Bus at or after `time`.
   * </p>
   * 
   * <p>
   * Note: you will want to compare the **arrivalTimes** of the Bus-es here, not the whole Bus
   * objects. You can use `Bus.getArrivalTime()` to get a Bus-es arrival time at userStop. You can
   * LocalTime.compareTo() to compare two times.
   * </p>
   * 
   * <p>
   * Hint: consider the three places that the soonest Bus could be: (1) the left subtree, (2)
   * `node`, or (3) the right subtree. You will need to find the soonest Bus at or after `time` in
   * each option and then choose the best among them.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param time the desired time -- the returned Node will have an arrival time at or after this
   *             time.
   * @param node the root of the relevant subtree.
   * @return the node in the subtree that has the soonest arrival time at or after the desired time;
   *         or null if there is no such node.
   */
  protected Node<Bus> getFirstNodeAfter(LocalTime time, Node<Bus> node) {
    if (node == null || time == null) {
      return null;
    }

    // Compare the arrival time of the current node with the desired time
    int comparison = node.getValue().getArrivalTime().compareTo(time);

    // If the current node's arrival time is at or after the desired time,
    // then it could be a candidate for the soonest node
    if (comparison >= 0) {
      Node<Bus> leftResult = getFirstNodeAfter(time, node.getLeft()); // Check the left subtree
      Node<Bus> rightResult = getFirstNodeAfter(time, node.getRight()); // Check the right subtree

      // Determine the best candidate among the current node, left subtree, and right subtree
      Node<Bus> bestCandidate = getBestCandidate(node, leftResult, rightResult);

      return bestCandidate;
    } else {
      // If the current node's arrival time is before the desired time,
      // then check only the right subtree
      return getFirstNodeAfter(time, node.getRight());
    }
  }

  private Node<Bus> getBestCandidate(Node<Bus> current, Node<Bus> left, Node<Bus> right) {
    // If the current node is null, return the non-null result
    if (current == null) {
      return left != null ? left : right;
    }

    // If both left and right results are null, return the current node
    if (left == null && right == null) {
      return current;
    }

    // Find the candidate with the earliest arrival time among the current node, left subtree, and
    // right subtree
    Node<Bus> earliest = current;
    if (left != null
        && left.getValue().getArrivalTime().isBefore(earliest.getValue().getArrivalTime())) {
      earliest = left;
    }
    if (right != null
        && right.getValue().getArrivalTime().isBefore(earliest.getValue().getArrivalTime())) {
      earliest = right;
    }

    return earliest;
  }

  /**
   * Returns an iterator over all Bus-es at or after the given time that go to the specified
   * destination.
   * 
   * @param time        the time we want to start iterating from.
   * @param destination the destination we want all Bus-es to go to.
   * @return an iterator that that returns all Bus-es at or after the given time that go to the
   *         specified destination.
   */
  public Iterator<Bus> getNextBusesTo(LocalTime time, BusStop destination) {
    Iterator<Bus> unfiltered = getNextBuses(time);
    return new BusFilteredIterator(unfiltered, destination);
  }

  /**
   * Returns the next bus available at or after the given time.
   * 
   * @param time the time to compare with the bus schedules
   * @return the next bus available at or after the given time, or null if there are no buses
   *         available
   */
  public Bus getNextBus(LocalTime time) {
    Node<Bus> node = getFirstNodeAfter(time, root);
    if (node == null) {
      return null;
    } else {
      return node.getValue();
    }
  }

  /**
   * Returns the next bus to arrive at the given stop at or after the given time.
   * 
   * @param time the time after which the bus should arrive
   * @param stop the bus stop to check for the next bus
   * @return the next bus to arrive at the given stop at or after the given time, or null if there
   *         is no such bus
   */
  public Bus getNextBusTo(LocalTime time, BusStop stop) {
    Iterator<Bus> iterator = getNextBusesTo(time, stop);
    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      return null;
    }
  }

  /**
   * <p>
   * Returns the first bus in the bus stop tree. If the tree is empty, returns null. <br>
   * </p>
   * 
   * <p>
   * This method should handle the case of an empty tree and then call getFirstBusHelper to
   * recursively find the earliest Bus.
   * </p>
   *
   * @return the earliest bus in the bus stop tree, or null if the tree is empty.
   */
  public Bus getFirstBus() {
    if (root == null) {
      return null;
    }

    Node<Bus> node = getFirstBusHelper(root);

    if (node == null) {
      return null;
    }

    return node.getValue();
  }

  /**
   * <p>
   * Returns the first node in the tree.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   *
   * @param node the root node of the tree to search; should not be null.
   * @return the node containing the earliest Bus in the tree.
   */
  public static Node<Bus> getFirstBusHelper(Node<Bus> node) {

    if (node.getLeft() == null) {

      // If there is no left child, then this is the leftmost leaf.

      return node;

    } else {

      // Continue traversing the left subtree.

      return getFirstBusHelper(node.getLeft());

    }

  }

  /**
   * <p>
   * Returns the last bus in the bus stop tree. If the tree is empty, returns null.
   * </p>
   *
   * <p>
   * This method should handle the case of an empty tree and then call getLastBusHelper to
   * recursively find the latest Bus.
   * </p>
   *
   * @return the last bus in the bus stop tree, or null if the tree is empty.
   */
  public Bus getLastBus() {
    
    //base case 
    if (root == null) {
      return null;
    }
    
    //recursion 

    Node<Bus> node = getLastBusHelper(root);

    if (node == null) {
      return null;
    }

    //getting the value 
    return node.getValue();
  }

  /**
   * <p>
   * Returns the last node in the tree.
   * </p>
   *
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   *
   * @param node the root node of the tree to search; should not be null.
   * @return the node containing the latest Bus in the tree.
   */
  public static Node<Bus> getLastBusHelper(Node<Bus> node) {

    if (node.getRight() == null) {

      // If there is no right child, then this is the rightmost leaf.

      return node;

    } else {

      // Continue traversing the right subtree.

      return getLastBusHelper(node.getRight());

    }

  }

  /**
   * <p>
   * This static method finds the path from the given currentNode to one of its descendants destNode
   * and pushes all the nodes on the path to the given stack.
   * </p>
   * 
   * <p>
   * In a binary search tree, a "path" is a set of node that one must traverse to get from one node
   * to another.
   * </p>
   * 
   * <p>
   * You may assume that `destNode` is indeed a descendant of `currentNode` (or they are the same
   * node). You may assume that neither node is null.
   * </p>
   * 
   * <p>
   * The nodes are pushed in order from the currentNode to the destNode. That is, currentNode is
   * pushed to the stack, then it's child, then it's grandchild, etc. until we reach the destNode.
   * So at the end of this routine, destNode is the top element on the stack, followed by its
   * parent, followed by its grandparent, etc all the way to the currentNode.
   * </p>
   * 
   * <p>
   * This method should not modify the stack in any other way. Notably, it should not pop any
   * elements from the stack.
   * </p>
   * 
   * <p>
   * For example, if we are given the following tree:
   * </p>
   * 
   * <pre>
   *       A
   *     /   \
   *    B     C
   *   / \   / \
   *  D   E  F  G
   * </pre>
   * 
   * And the following stack:
   * 
   * <pre>
   * top -> Z Y X
   * </pre>
   * 
   * And we call pathToNode with the above stack, currentNode A, and destNode F, then the path from
   * A to F would A->C->F, and when we return from pathToNode, the stack will contain:
   * 
   * <pre>
   * top-> F C A Z Y X
   * </pre>
   * 
   * @param stack       the stack to push the path of nodes to.
   * @param currentNode the root of the subtree that contains node (and the first node in the path).
   * @param destNode    the desired destination node (and the last node in the path). This node is
   *                    guaranteed to be in the subtree rooted at root.
   */
  public static void pathToNode(Stack<Node<Bus>> stack, Node<Bus> currentNode, Node<Bus> destNode) {
    // Push the node (our current position on the path).
    stack.push(currentNode);

    // Descend left or right, if not done.
    if (currentNode == destNode) {
      return;
    } else if (destNode.getValue().compareTo(currentNode.getValue()) < 0) {
      pathToNode(stack, currentNode.getLeft(), destNode);
    } else {
      pathToNode(stack, currentNode.getRight(), destNode);
    }
  }

  /**
   * Returns the root node of the binary search tree.
   * 
   * @return the root node of the binary search tree
   */
  public Node<Bus> getRoot() {
    return root;
  }

  /**
   * A human readable string representation of the tree.
   * 
   * @return A human readable string representation of the tree useful for debugging.
   */
  @Override
  public String toString() {
    return toStringHelper(0, root);
  }

  /**
   * Helper used to implement toString. Prints the subtree rooted at the given node.
   * 
   * @param level the depth of node in the tree.
   * @param node  the root of the subtree we are printing.
   * @return a human readable string representing the subtree.
   */
  private String toStringHelper(int level, Node<Bus> node) {
    if (node == null) {
      return "| ".repeat(level) + "null";
    }

    return "| ".repeat(level) + node + "\n" + toStringHelper(level + 1, node.getLeft()) + "\n"
        + toStringHelper(level + 1, node.getRight());
  }

  /**
   * Returns the size of the BST.
   * 
   * @return the number of Bus-es in the tree.
   */
  public int size() {
    return sizeHelper(root);
  }

  /**
   * Returns the size of the subtree rooted at the given node.
   * 
   * @param node the root of the subtree
   * @return the number of Bus-es in the subtree
   */
  private int sizeHelper(Node<Bus> node) {
    if (node == null) {
      return 0;
    }

    return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
  }

  /**
   * Returns true if the BST is empty.
   * 
   * @return true if the size of the tree is 0; false otherwise.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Returns the height of the tree (counting nodes, not edges). The height of a tree with 1 node is
   * 1.
   * 
   * @returns the height of the tree.
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * <p>
   * Returns the height of the subtree rooted at the given node (counting nodes, not edges). The
   * height of a tree with 1 node is 1.
   * </p>
   * 
   * <p>
   * <b>THIS METHOD MUST BE IMPLEMENTED RECURSIVELY!</b>
   * </p>
   * 
   * @param node the root of the subtree (may be null)
   * @return the height of the subtree
   */
  private int heightHelper(Node<Bus> node) {
    // start at the root (root is 1 + max height of children)

    if (node == null) {
      return 0;
    }

    int x = 0;
    int y = 0;

    // recursively calling the method again

    if (node.getLeft() != null) {
      x = heightHelper(node.getLeft());
    }

    if (node.getRight() != null) {
      y = heightHelper(node.getRight());
    }

    // returning the values
    if (x > y) {
      return x += 1;
    } else {
      return y += 1;
    }


  }
}
