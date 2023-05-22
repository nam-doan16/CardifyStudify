package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a class testing for Ordering, which
 * represents the order of how files should be sorted in a list
 */
class OrderingTest {
  /**
   * Test for the enumerated types
   */
  @Test
  public void testEnumType() {
    assertEquals(Ordering.FILENAME.name(), "FILENAME");
    assertEquals(Ordering.CREATED.name(), "CREATED");
    assertEquals(Ordering.MODIFIED.name(), "MODIFIED");
  }

}