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
class FileDataWriterTest {
  FileDataWriter writer;
  FileDataWriter invalidWriter;

  /**
   * initialization method to be called before each test method
   */
  @BeforeEach
  public void setup() {
    MarkdownFile nam = new MarkdownFile(new File("sampleinput/folderinsidefolder/namdoan.md"));
    MarkdownFile test1 = new MarkdownFile(new File("otherTestFiles/test1.md"));
    try {
      writer = new FileDataWriter(new ArrayList<>(Arrays.asList(nam, test1)),
          new File("sampleoutput/testingOutput.md"));
      invalidWriter = new FileDataWriter(
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
    // testing with an invalid file
    assertThrows(NoSuchFileException.class, () -> invalidWriter.writeToFile());

    // testing with a valid file
    try {
      writer.writeToFile();
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }

    // checking if it wrote the right contents to the file
    StringBuilder temp = new StringBuilder();
    try {
      Scanner fileScan = new Scanner(new File("sampleoutput/testingOutput.md"));
      while (fileScan.hasNextLine()) {
        temp.append(fileScan.nextLine()).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    String expected = """
        # Nam Doan
        - How to spell "Nam Doan"
        - N-a-m D-o-a-n

        ## Nam's Favorite Foods
        - Nam really
        enjoys eating sushi!!

        # CS3500
        - It's crazy that were in OOD now!
        - HELLO!!!
        WORLD WOAHHHHHH

        """;
    assertEquals(temp.toString(), expected);
  }

  @Test
  public void testWriteQuestion() {
    MarkdownFile testMd = new MarkdownFile(new File("testWriteQuestion/test.md"));
    try {
      FileDataWriter writeQuestionTest = new FileDataWriter(new ArrayList<>(List.of(testMd)),
          new File("sampleoutput/writeQuestionTest.md"));
      writeQuestionTest.writeToFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // checking if it wrote the right contents to the file
    StringBuilder temp = new StringBuilder();
    try {
      Scanner fileScan = new Scanner(new File("sampleoutput/writeQuestionTest.sr"));
      while (fileScan.hasNextLine()) {
        temp.append(fileScan.nextLine()).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String expected = """
        [[What is your name?:::Test]]
        [[Hello:::
        World]]
        """;
    assertEquals(temp.toString(), expected);
  }
}