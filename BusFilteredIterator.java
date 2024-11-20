
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BusFilteredIterator implements Iterator<Bus> {

  private Iterator<Bus> baseIterator;
  private BusStop destination;
  private Bus next;

  /**
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination.
   *
   * @param iterator    the iterator we are filtering.
   * @param destination the desired destination.
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    this.baseIterator = iterator;
    this.destination = destination;
    // this needs to keep iterator
    this.advanceToNext();
  }

  /**
   * Private helper method that advances this iterator. It will iterate over `this.iterator` until
   * it reaches a Bus that stops at destination. Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    while (baseIterator.hasNext()) {
      Bus currentBus = baseIterator.next();
      if (currentBus.goesTo(destination)) {
        next = currentBus;
        return; // Return as soon as you find a matching bus
      }
    }
    next = null; // Set to null if no more matching buses
  }

  /**
   * Returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise.
   *
   * @return true if a call to next() will return another Bus; false otherwise.
   */
  @Override
  public boolean hasNext() {
    // returning if it's null so it can continue
    return next != null;
  }

  /**
   * Returns the `next` bus and advances the iterator until the next bus it will return.
   *
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * @throws NoSuchElementException if called when there is no next Bus.
   */
  @Override
  public Bus next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more buses available.");
    }

    // setting the currentBus
    Bus currentBus = next;
    advanceToNext();
    return currentBus;
  }
}
