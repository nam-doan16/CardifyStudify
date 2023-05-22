package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for the Driver class, which is
 * what connects all the classes together
 */
class DriverTest {
  /**
   * Testing instantiation of Driver (jacoco wants it apparently)
   */
  @Test
  public void testInstantiation() {
    new Driver();
  }

  /**
   * Testing main
   */
  @Test
  public void testMain() {
    String[] validArgs = {"otherTestFiles/", "filename", "sampleoutput/mainTesting.md"};
    String[] badLengthArgs = {"hi"};
    String[] invalidArgs = {"LOL", "woah", "notreal"};
    String[] unreachableFile = {"", "filename", "noRWX.md"};

    // testing invalid args (should be illegal argument exception)
    assertThrows(IllegalArgumentException.class, () -> Driver.main(invalidArgs));

    // testing with an unreachable file
    assertThrows(IOException.class, () -> Driver.main(unreachableFile));

    // testing with args that are too short in length (list size wise)
    assertThrows(IllegalArgumentException.class, () -> Driver.main(badLengthArgs));

    try {
      Driver.main(validArgs);
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }

    // checking if test.main(validArgs) wrote the right contents to the file
    String temp = "";
    try {
      Scanner fileScan = new Scanner(new File("sampleoutput/mainTesting.md"));
      while (fileScan.hasNextLine()) {
        temp += fileScan.nextLine() + "\n";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String expected = "# CS3500\n- It's crazy that were in OOD now!\n- HELLO!!!\nWORLD WOAHHHHHH"
        + "\n\n";
    assertEquals(temp, expected);
  }
}