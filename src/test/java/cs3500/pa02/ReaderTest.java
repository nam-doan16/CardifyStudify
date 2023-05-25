package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReaderTest {
  private Readable readable;
  private Reader reader;
  private static final String testString = "Test String";
  private static final String emptyString = "";

  /**
   * Initialization method to be ran before every Test method
   */
  @BeforeEach
  public void setUp() {
    this.readable = new StringReader(testString);
    this.reader = new Reader(this.readable);
  }

  /**
   * Testing when Reader is successful
   */
  @Test
  public void testSuccess() {
    // testing that reading the readable contains "Test String"
    assertEquals(this.reader.read(), "Test String");

    // testing empty string to cover branches
    this.readable = new StringReader(emptyString);
    this.reader = new Reader(this.readable);
    assertEquals(this.reader.read(), "");
  }
}