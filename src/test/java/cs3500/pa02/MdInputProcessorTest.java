package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for MdInputProcessor, which is a class that handles
 * inputting all the markdown files, sorting them, and distributing them to classes
 * that need them
 */
class MdInputProcessorTest {
  // making instances for all 3 orders to test the private method sortMDFiles
  MdInputProcessor filenameTest;
  MdInputProcessor modifiedTest;
  MdInputProcessor createdTest;

  /**
   * Initialization method to be run before each test method
   */
  @BeforeEach
  public void setup() {
    Path testPath = Path.of("sampleinput/");
    try {
      filenameTest = new MdInputProcessor(testPath, Ordering.FILENAME);
      modifiedTest = new MdInputProcessor(testPath, Ordering.MODIFIED);
      createdTest = new MdInputProcessor(testPath, Ordering.CREATED);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests getMdFiles in MdInputProcessor
   */
  @Test
  public void testGetMdFiles() {
    assertEquals(filenameTest.getMdFiles().size(), 4);
    assertEquals(modifiedTest.getMdFiles().size(), 4);
    assertEquals(createdTest.getMdFiles().size(), 4);

    File arrays = new File("sampleinput/arrays.md");
    File empty = new File("sampleinput/folderinsidefolder/empty.md");
    File nam = new File("sampleinput/folderinsidefolder/namdoan.md");
    assertEquals(filenameTest.getMdFiles().get(0).getFile(), arrays);
    assertEquals(filenameTest.getMdFiles().get(1).getFile(), empty);
    assertEquals(filenameTest.getMdFiles().get(2).getFile(), nam);

    File vectors = new File("sampleinput/vectors.md");
    assertEquals(filenameTest.getMdFiles().get(3).getFile(), vectors);
  }
}