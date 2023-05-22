package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for MdFileWriter, which handles the writing functionality
 */
class MdFileWriterTest {
  MdFileWriter writer;
  MdFileWriter invalidWriter;

  /**
   * initialization method to be called before each test method
   */
  @BeforeEach
  public void setup() {
    MarkdownFile nam = new MarkdownFile(new File("sampleinput/folderinsidefolder/namdoan.md"));
    MarkdownFile test1 = new MarkdownFile(new File("otherTestFiles/test1.md"));
    try {
      writer = new MdFileWriter(new ArrayList<>(Arrays.asList(nam, test1)),
          new File("sampleoutput/testingOutput.md"));
      invalidWriter = new MdFileWriter(
          new ArrayList<>(List.of(new MarkdownFile(new File((""))))),
          new File("TESTING"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Represents tests for writeToFile()
   */
  @Test
  public void testWriteToFile() {
    // testing with an invalid file (noRWX.md has all their read, write, and execute permissions
    // removed
    assertThrows(NoSuchFileException.class, () -> invalidWriter.writeToFile());

    // testing with a valid file
    try {
      writer.writeToFile();
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }

    // checking if it wrote the right contents to the file
    String temp = "";
    try {
      Scanner fileScan = new Scanner(new File("sampleoutput/testingOutput.md"));
      while (fileScan.hasNextLine()) {
        temp += fileScan.nextLine() + "\n";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String expected = "# Nam Doan\n- How to spell \"Nam Doan\"\n- N-a-m D-o-a-n\n"
        + "\n## Nam's Favorite Foods\n- Nam really\nenjoys eating sushi!!\n\n"
        + "# CS3500\n- It's crazy that were in OOD now!\n- HELLO!!!\nWORLD WOAHHHHHH\n\n";
    assertEquals(temp, expected);
  }
}