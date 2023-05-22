package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.NoSuchFileException;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for ArgumentValidator, which is a class
 * responsible for validating given arguments
 */
class ArgumentValidatorTest {
  /**
   * Tests the instantiation of ArgumentValidator
   */
  @Test
  void testInitialization() {
    new ArgumentValidator();
  }

  /**
   * Tests the isValid method in ArgumentValidator
   */
  @Test
  void testIsValid() {
    String[] longList = new String[4];
    String[] nullList = null;
    String[] shortList = new String[2];
    String[] validList = {"sampleinput/", "filename", "sampleoutput/test.md"};

    // testing if isValid throws an exception for a list that is too long, too short, or null
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.isValid(longList));
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.isValid(shortList));
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.isValid(nullList));

    // testing with a valid list
    // tests parseOrder("filename")
    try {
      assertTrue(ArgumentValidator.isValid(validList));
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }

    String[] badStartDirectory = {"HELLOWRODLL", "modified", "sampleoutput/bad.md"};
    String[] badOrder = {"sampleinput/", "WOWORIWWOWO", "sampleoutput/bad2.md"};

    // test.txt was created to test the branch where the file existed, but not a directory
    String[] badOutputPath = {"sampleinput/", "created", "test.txt"};

    // testing with a list with a bad starting directory
    // tests parseOrder("modified")
    assertThrows(NoSuchFileException.class, () -> ArgumentValidator.isValid(badStartDirectory));

    // testing with a list with a bad ordering
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.isValid(badOrder));

    // testing with a list with a bad output path
    // tests parseOrder("created")
    assertThrows(NoSuchFileException.class, () -> ArgumentValidator.isValid(badOutputPath));
  }

  /**
   * Tests for parseOrder in ArgumentValidator
   */
  @Test
  void testParseOrder() {
    // testing for filename and testing to see that it not case-sensitive
    assertEquals(ArgumentValidator.parseOrder("filename"), Ordering.FILENAME);
    assertEquals(ArgumentValidator.parseOrder("FiLENAME"), Ordering.FILENAME);

    // testing created
    assertEquals(ArgumentValidator.parseOrder("created"), Ordering.CREATED);

    // testing modified
    assertEquals(ArgumentValidator.parseOrder("modified"), Ordering.MODIFIED);

    // testing if any other string is given other than the three associated ones
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.parseOrder("random"));
  }
}