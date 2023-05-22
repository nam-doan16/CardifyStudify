package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for MarkdownFile, which represents a markdown file
 */
class MarkdownFileTest {
  MarkdownFile test1;
  MarkdownFile test2;

  /**
   * initialization method to be ran before each test
   * also tests the instantiation of the object (constructors)
   */
  @BeforeEach
  public void setup() {
    test1 = new MarkdownFile(new File("sampleinput/arrays.md"));
    test2 = new MarkdownFile(FileTime.fromMillis(1000), FileTime.fromMillis(1000),
        new File("sampleinput/vectors.md"));
  }

  /**
   * Tests for getFile in MarkdownFile
   */
  @Test
  public void testGetFile() {
    assertEquals(test1.getFile(), new File("sampleinput/arrays.md"));
    assertEquals(test2.getFile(), new File("sampleinput/vectors.md"));
  }

  /**
   * Test for compareCreationDate and compareCreationDateHelp in MarkdownFile
   */
  @Test
  public void testCompareCreationDate() {
    // test1 has a creation date (in milliseconds) of 0
    // test2 has a creation date (in milliseconds) of 1000
    assertEquals(test1.compareCreationDate(test2), -1);
    assertEquals(test2.compareCreationDate(test1), 1);
    assertEquals(test1.compareCreationDate(test1), 0);
  }

}