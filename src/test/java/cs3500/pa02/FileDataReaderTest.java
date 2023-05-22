package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test clas for MdFileReader, which is a class that handles reading
 * markdown files
 */
class FileDataReaderTest {
  MarkdownFile file;
  MarkdownFile empty;
  MarkdownFile invalid;
  FileDataReader reader;
  FileDataReader emptyReader;
  FileDataReader invalidReader;

  /**
   * Initialization before each test method
   */
  @BeforeEach
  public void setup() {
    file = new MarkdownFile(new File("sampleinput/folderinsidefolder/namdoan.md"));
    empty = new MarkdownFile(new File("sampleinput/folderinsidefolder/empty.md"));

    // noRWX.md is a file with no read, write, execute permissions
    invalid = new MarkdownFile(new File("lololol"));
    reader = new FileDataReader(new ArrayList<>(List.of(file)), new ArrayList<>());
    emptyReader = new FileDataReader(new ArrayList<>(List.of(empty)), new ArrayList<>());
    invalidReader = new FileDataReader(new ArrayList<>(List.of(invalid)), new ArrayList<>());
  }

  /**
   * tests getFilteredData in MdFileReader
   */
  @Test
  public void testGetFilteredData() {
    // testing with a valid file
    try {
      assertEquals(reader.getFilteredData(), """
          # Nam Doan
          - How to spell "Nam Doan"
          - N-a-m D-o-a-n

          ## Nam's Favorite Foods
          - Nam really
          enjoys eating sushi!!

          """);
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }

    // testing with an invalid file
    assertThrows(IOException.class, () -> invalidReader.getFilteredData());
  }

  /**
   * Tests processString in MdFileReader
   */
  @Test
  public void testProcessString() {
    // represents each branch of the while loop
    // false and false
    String test1 = "- hello!";

    // false, true
    String test2 = "- cs3500]]";

    // true, false
    String test3 = "- [[hi";

    assertEquals(reader.processString(test1), "- hello!");
    assertEquals(reader.processString(test2), "- cs3500]]");
    assertEquals(reader.processString(test3), "- [[hi");

    // true, true
    String test4 = "- [[hello, world!]]";
    assertEquals(reader.processString(test4), "- hello, world!");

    // testing question syntax
    String questionSyntax = "1. [[What is Nam's last name?:::Doan]] hello";
    String questionSyntax1 = "2. [[Hello:::World]]";
    assertEquals(reader.processString(questionSyntax), "1.  hello");
    assertEquals(reader.processString(questionSyntax1), "2. ");

  }
}