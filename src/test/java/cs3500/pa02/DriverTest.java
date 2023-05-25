package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    } catch (IOException e) {
      e.printStackTrace();
    }

    // checking if test.main(validArgs) wrote the right contents to the file
    StringBuilder temp = new StringBuilder();
    try {
      Scanner fileScan = new Scanner(new File("sampleoutput/mainTesting.md"));
      while (fileScan.hasNextLine()) {
        temp.append(fileScan.nextLine()).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String expected = """
        # CS3500
        - It's crazy that were in OOD now!
        - HELLO!!!
        WORLD WOAHHHHHH

        """;
    assertEquals(temp.toString(), expected);
  }

  /**
   * Testing Driver for the initial inputs for
   */
  @Test
  public void testStudySessionMain() {
    String input = "sampleinput/test.sr";
    InputStream inputPath = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputPath);
    // no args, bad input
    String[] emptyArgs = {};
    Exception e = assertThrows(NumberFormatException.class, () -> Driver.main(emptyArgs),
        "For input string: \"\"");
    assertEquals(e.getMessage(), "For input string: \"\"");
    System.setIn(System.in);


  }
}