
import java.time.LocalTime;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {

    // construct two buses and compare the values of different arrival time
    // making sure that the compareto works

    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
        new int[] {1, 2}, new String[] {"01:00", "02:00"});

    Bus firstBus = new Bus(BusStop.getStop(1), route1);
    Bus secondBus = new Bus(BusStop.getStop(2), route1);

    return firstBus.compareTo(secondBus) == -1;


  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {

    BusStop.createDummyStops();
    BusRoute route1 = BusRoute.dummyRoute("ARoute", BusRoute.BusDirection.OUTGOING, new int[] {1},
        new String[] {"10:00"});
    BusRoute route2 = BusRoute.dummyRoute("BRoute", BusRoute.BusDirection.OUTGOING, new int[] {1},
        new String[] {"10:00"});

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    // it's going to compare by name of the route, so A is going to be higher

    return bus1.compareTo(bus2) == -1;


  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {

    BusStop.createDummyStops();
    BusRoute route1 = BusRoute.dummyRoute("ARoute", BusRoute.BusDirection.OUTGOING, new int[] {1},
        new String[] {"10:00"});
    BusRoute route2 = BusRoute.dummyRoute("ARoute", BusRoute.BusDirection.INCOMING, new int[] {1},
        new String[] {"10:00"});

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);

    // it's going to compare by name of the route, so A is going to be higher

    return bus1.compareTo(bus2) == -1;

  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }



  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {

    // Validate that the empty BST is considered valid
    return BusStopTree.isValidBST(null);


  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {



    // Create bus stops

    BusStop StopOne = BusStop.getStop(1);

    BusStop StopTwo = BusStop.getStop(2);

    BusStop StopThree = BusStop.getStop(3);

    BusStop StopFour = BusStop.getStop(4);

    BusStop StopFive = BusStop.getStop(5);

    BusStop StopSix = BusStop.getStop(6);



    // Create bus routes

    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2, 3, 4, 5, 6},
        new String[] {"09:00", "10:00", "11:00", "12:00", "10:30", "12:30"}, new BusRoute.Day[] {});



    // Create buses

    Bus bus1 = new Bus(StopOne, routeA);

    Bus bus2 = new Bus(StopTwo, routeA);

    Bus bus3 = new Bus(StopThree, routeA);

    Bus bus4 = new Bus(StopFour, routeA);

    Bus bus5 = new Bus(StopFive, routeA);

    Bus bus6 = new Bus(StopSix, routeA);



    // Create nodes for each bus

    Node<Bus> root = new Node<>(bus1);

    Node<Bus> node2 = new Node<>(bus2);

    Node<Bus> node3 = new Node<>(bus3);

    Node<Bus> node4 = new Node<>(bus4);

    Node<Bus> node5 = new Node<>(bus5);

    Node<Bus> node6 = new Node<>(bus6);



    // Construct an invalid binary search tree

    root.setLeft(node3);

    root.setRight(node6);

    node3.setLeft(node2);

    node3.setRight(node4);

    node6.setLeft(node5); // Invalid: left child greater than the root



    // Assuming you have a BusStopTree class with a method isValidBST

    // that checks if the given tree is a valid BST

    return !BusStopTree.isValidBST(root);

  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */



  // figure out how to do this
  // Go to TA hours and ask

  public static boolean testIsValidBSTValid() {
    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
        new int[] {1, 2}, new String[] {"01:00", "02:00"});

    Bus fifthBus = new Bus(BusStop.getStop(1), route1);
    Bus thirdBus = new Bus(BusStop.getStop(1), route1);
    Bus seventhBus = new Bus(BusStop.getStop(1), route1);
    Bus secondBus = new Bus(BusStop.getStop(1), route1);
    Bus fourthBus = new Bus(BusStop.getStop(1), route1);
    Bus firstBus = new Bus(BusStop.getStop(1), route1);
    Bus sixthBus = new Bus(BusStop.getStop(1), route1);

    // Building a valid BST
    Node<Bus> root = new Node<>(fifthBus);
    Node<Bus> node3 = new Node<>(thirdBus);
    Node<Bus> node7 = new Node<>(seventhBus);
    Node<Bus> node2 = new Node<>(secondBus);
    Node<Bus> node4 = new Node<>(fourthBus);
    Node<Bus> node1 = new Node<>(firstBus);
    Node<Bus> node6 = new Node<>(sixthBus);

    root.setLeft(node3);
    root.setRight(node7);
    node3.setLeft(node2);
    node3.setRight(node4);
    node2.setLeft(node1);
    node7.setRight(node6); // Fix: set node7 as the right child of node3

    // Testing isValidBST on the valid tree
    return BusStopTree.isValidBST(root);
  }



  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    // Create a BusRoute with dummy data
    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
        new int[] {1, 2}, new String[] {"01:00", "02:00"});

    // Create a BusStop with dummy data
    BusStop userStop = BusStop.getStop(1);

    // Create a Bus using the BusStop and BusRoute
    Bus firstBus = new Bus(userStop, route1);

    // Create a new instance of BusStopTree
    BusStopTree busStopTree = new BusStopTree(1); // what does the 0 in busstoptree do

    // Add the bus to the empty tree
    boolean result = busStopTree.addBus(firstBus);



    // The result should be true, as the tree was empty, and the bus should be added successfully
    return result && busStopTree.size() == 1;
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {

    // Create bus stops and routes

    BusStop firstStop = BusStop.getStop(1);

    BusStop secondStop = BusStop.getStop(2);

    BusStop thirdStop = BusStop.getStop(3);



    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2, 3}, new String[] {"05:00", "06:00", "07:00"}, new BusRoute.Day[] {});



    // Create a BST with initial bus stops

    BusStopTree busStopTree = new BusStopTree(3);

    busStopTree.addBus(new Bus(secondStop, routeA)); // Adding a bus to make it non-empty

    busStopTree.addBus(new Bus(firstStop, routeA)); // Adding another bus



    // Create a new bus to be added

    Bus newBus = new Bus(thirdStop, routeA);



    // Test adding the new bus

    boolean added = busStopTree.addBus(newBus);



    // Check if the bus was added successfully, BST is still valid, and the size has been
    // incremented

    return added && BusStopTree.isValidBST(busStopTree.getRoot()) && busStopTree.size() == 3;

  }



  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {

    // Create bus stops and routes

    BusStop stop1 = BusStop.getStop(1);

    BusStop stop2 = BusStop.getStop(2);



    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2}, new String[] {"09:00", "10:00"}, new BusRoute.Day[] {});



    // creating a bst 
    BusStopTree busStopTree = new BusStopTree(2);

    busStopTree.addBus(new Bus(stop1, routeA));



    // Attempt to add a duplicate bus

    Bus dBus = new Bus(stop1, routeA);

    boolean duplicate = busStopTree.addBus(dBus);



    // Check that the return value is false, and the BST is not modified

    return !duplicate && BusStopTree.isValidBST(busStopTree.getRoot()) && busStopTree.size() == 1;

  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {

    // Create bus stops and routes

    BusStop stop1 = BusStop.getStop(1);

    BusStop stop2 = BusStop.getStop(2);



    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2}, new String[] {"09:00", "10:00"}, new BusRoute.Day[] {});



    // Create a BST with initial bus stops

    BusStopTree busStopTree = new BusStopTree(2);

    Bus bus1 = new Bus(stop1, routeA);

    Bus bus2 = new Bus(stop2, routeA);

    busStopTree.addBus(bus1);



    // Test if contains correctly identifies the bus in the tree

    boolean containsBus1 = busStopTree.contains(bus1);

    boolean containsBus2 = busStopTree.contains(bus2);



    // Check if the test passes

    return containsBus1 && !containsBus2;

  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {

    BusStop stop1 = BusStop.getStop(1);

    BusStop stop2 = BusStop.getStop(2);



    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2}, new String[] {"09:00", "10:00"}, new BusRoute.Day[] {});



    // Create a BST with initial bus stops

    BusStopTree busStopTree = new BusStopTree(2);

    Bus bus1 = new Bus(stop1, routeA);

    Bus bus2 = new Bus(stop2, routeA);



    // Add buses to the BST

    busStopTree.addBus(bus1);

    busStopTree.addBus(bus2);



    // Specify a time after which the first bus should be found

    LocalTime searchTime = LocalTime.parse("09:30");



    // Test the getFirstNodeAfter method

    Node<Bus> resultNode = busStopTree.getFirstNodeAfter(searchTime, busStopTree.getRoot());



    // Check if the resultNode is the correct node (bus2) with the soonest arrival time

    return resultNode != null && resultNode.getValue().equals(bus2);
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"06:00", "07:00", "10:00", "11:00", "13:00"};
    String[] stopTimes3 = {"07:00", "08:00", "10:30", "11:00", "14:00"};
    String[] stopTimes4 = {"06:30", "07:30", "10:30", "11:30", "13:30"};

    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 4", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes4);

    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);

    BusStopTree testGetFirstNodeAfter = new BusStopTree(1);
    testGetFirstNodeAfter.addBus(bus2);
    testGetFirstNodeAfter.addBus(bus1);
    testGetFirstNodeAfter.addBus(bus3);
    testGetFirstNodeAfter.addBus(bus4);

    LocalTime testTime = LocalTime.parse("04:30");

    Bus expected = bus1;
    Bus actual = testGetFirstNodeAfter.getFirstNodeAfter(testTime, testGetFirstNodeAfter.getRoot())
        .getValue();

    if (!actual.equals(expected)) {
      return false;
    }

    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    // TODO: Default return value.
    BusStop stop1 = BusStop.getStop(1);

    BusStop stop2 = BusStop.getStop(2);

    BusStop stop3 = BusStop.getStop(3);



    BusRoute routeA = BusRoute.dummyRoute("RouteA", BusRoute.BusDirection.OUTGOING,

        new int[] {1, 2, 3}, new String[] {"09:00", "10:00", "11:00"}, new BusRoute.Day[] {});



    // Create a BST with initial bus stops

    BusStopTree busStopTree = new BusStopTree(3);

    Bus bus1 = new Bus(stop1, routeA);

    Bus bus2 = new Bus(stop2, routeA);

    Bus bus3 = new Bus(stop3, routeA);



    // Add buses to the BST

    busStopTree.addBus(bus1);

    busStopTree.addBus(bus2);

    busStopTree.addBus(bus3);



    // Specify a time after which the first bus should be found

    LocalTime searchTime = LocalTime.parse("09:30");



    // Test the getFirstNodeAfter method

    Node<Bus> resultNode = busStopTree.getFirstNodeAfter(searchTime, busStopTree.getRoot());



    // Check if the resultNode is the correct node (bus2) with the soonest arrival time in the right
    // subtree

    return resultNode != null && resultNode.getValue().equals(bus2);
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING,
        new int[] {1, 2}, new String[] {"01:00", "02:00"});

    Bus firstBus = new Bus(BusStop.getStop(1), route1);
    Bus secondBus = new Bus(BusStop.getStop(1), route1);
    Bus thirdBus = new Bus(BusStop.getStop(1), route1);
    Bus fourthBus = new Bus(BusStop.getStop(1), route1);
    Bus fifthBus = new Bus(BusStop.getStop(1), route1);
    Bus eighthBus = new Bus(BusStop.getStop(1), route1);



    BusStopTree busTree = new BusStopTree(1);
    busTree.addBus(firstBus);
    busTree.addBus(secondBus);
    busTree.addBus(thirdBus);
    busTree.addBus(fourthBus);
    busTree.addBus(fifthBus);
    busTree.addBus(eighthBus);


    LocalTime target = LocalTime.parse("01:00");


    Iterator<Bus> x = busTree.getNextBusesTo(target, BusStop.getStop(3)); // going to 3, none of
                                                                          // them go there

    while (x.hasNext()) {
      return false;
    }

    return true;

  }



  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {


    // creating stopIDs
    int[] stopIds = {1, 2};
    int[] stopIds1 = {1, 3};



    // creating a bunch of routes
    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});
    BusRoute route2 = BusRoute.dummyRoute("Route 2", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route3 = BusRoute.dummyRoute("Route 3", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route4 = BusRoute.dummyRoute("Route 4", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});
    BusRoute route5 = BusRoute.dummyRoute("Route 5", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route6 = BusRoute.dummyRoute("Route 6", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});


    Bus firstBus = new Bus(BusStop.getStop(1), route1);
    Bus secondBus = new Bus(BusStop.getStop(1), route2);
    Bus thirdBus = new Bus(BusStop.getStop(1), route3);
    Bus fourthBus = new Bus(BusStop.getStop(1), route4);
    Bus fifthBus = new Bus(BusStop.getStop(1), route5);
    Bus sixthBus = new Bus(BusStop.getStop(1), route6);


    BusStopTree busTree = new BusStopTree(1);
    busTree.addBus(firstBus);
    busTree.addBus(secondBus);
    busTree.addBus(thirdBus);
    busTree.addBus(fourthBus);
    busTree.addBus(fifthBus);
    busTree.addBus(sixthBus);



    LocalTime target = LocalTime.parse("01:00");

    // using forward iterator create filtered iterator

    BusForwardIterator x =
        new BusForwardIterator(busTree.getRoot(), BusStopTree.getFirstBusHelper(busTree.getRoot()));



    Iterator<Bus> z = busTree.getNextBusesTo(target, BusStop.getStop(1));

    BusFilteredIterator y = new BusFilteredIterator(x, BusStop.getStop(3));


    Bus[] expectedBuses = {firstBus, fourthBus, sixthBus};
    int i = 0;

    // check if the buses returned by the filtered iterator match the expected buses


    try {
      while (y.hasNext()) {
        Bus currentBus = y.next();
        if (currentBus.compareTo(expectedBuses[i++]) != 0) {
          System.out.println("Mismatch at index " + (i - 1));
          System.out.println("Current Bus: " + currentBus);
          System.out.println("Expected Bus: " + expectedBuses[i - 1] + " " + expectedBuses[i - 1]);
          System.out.println("Comparison result: " + currentBus.compareTo(expectedBuses[i - 1]));
          return false; // bus doesn't match the expected bus

        }
      }
    }


    catch (NoSuchElementException | IndexOutOfBoundsException e) {
      return false;

    }



    return i == expectedBuses.length;
  }

  // using forward iterator create filtered iterator
  /**
   * BusForwardIterator x = new BusForwardIterator(busTree.getRoot(),
   * BusStopTree.getFirstBusHelper(busTree.getRoot()));
   * 
   * 
   * 
   * Iterator<Bus> z = busTree.getNextBusesTo(target, BusStop.getStop(1));
   * 
   * BusFilteredIterator y = new BusFilteredIterator(x, BusStop.getStop(3));
   * 
   * 
   * 
   * 
   * Bus[] arr = new Bus[fourthBus, sixthBus]; int i = 0;
   * 
   * 
   * while (y.hasNext()) { Bus b = y.next(); b.compareTo(arr[i]);
   * 
   * //count the number of i's there should be two use the compareto method
   * 
   * } Bus b = y.next(); b = arr[i];
   **/



  /**
   * Tests the creation of a BusFilteredIterator where ALL of the buses go to the destination.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    int[] stopIds = {1, 2};
    int[] stopIds1 = {1, 3};

    BusRoute route1 = BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});
    BusRoute route2 = BusRoute.dummyRoute("Route 2", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route3 = BusRoute.dummyRoute("Route 3", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route4 = BusRoute.dummyRoute("Route 4", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});
    BusRoute route5 = BusRoute.dummyRoute("Route 5", BusRoute.BusDirection.OUTGOING, stopIds,
        new String[] {"06:00", "07:00"});
    BusRoute route6 = BusRoute.dummyRoute("Route 6", BusRoute.BusDirection.OUTGOING, stopIds1,
        new String[] {"06:00", "07:00"});

    Bus firstBus = new Bus(BusStop.getStop(1), route1);
    Bus secondBus = new Bus(BusStop.getStop(1), route2);
    Bus thirdBus = new Bus(BusStop.getStop(1), route3);
    Bus fourthBus = new Bus(BusStop.getStop(1), route4);
    Bus fifthBus = new Bus(BusStop.getStop(1), route5);
    Bus sixthBus = new Bus(BusStop.getStop(1), route6);

    BusStopTree busTree = new BusStopTree(1);
    busTree.addBus(firstBus);
    busTree.addBus(secondBus);
    busTree.addBus(thirdBus);
    busTree.addBus(fourthBus);
    busTree.addBus(fifthBus);
    busTree.addBus(sixthBus);

    LocalTime target = LocalTime.parse("01:00");

    // Using forward iterator create filtered iterator
    BusForwardIterator forwardIterator =
        new BusForwardIterator(busTree.getRoot(), BusStopTree.getFirstBusHelper(busTree.getRoot()));
    BusFilteredIterator filteredIterator =
        new BusFilteredIterator(forwardIterator, BusStop.getStop(1));

    Bus[] expectedBuses = {firstBus, secondBus, thirdBus, fourthBus, fifthBus, sixthBus};
    int i = 0;

    // Check if the buses returned by the filtered iterator match the expected buses
    try {
      while (filteredIterator.hasNext()) {
        Bus currentBus = filteredIterator.next();
        if (currentBus.compareTo(expectedBuses[i++]) != 0) {
          System.out.println(i);
          System.out.println(currentBus);
          System.out.println(expectedBuses[i]);
          System.out.println(currentBus.compareTo(expectedBuses[i]));
          return false; // Bus doesn't match the expected bus
        }
      }
    } catch (NoSuchElementException | IndexOutOfBoundsException e) {
      return false;
    }

    return i == expectedBuses.length;
  }



  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}