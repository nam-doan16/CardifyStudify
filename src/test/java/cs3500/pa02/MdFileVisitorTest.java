package cs3500.pa02;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents a test class for MdFileVisitor, which handles visiting files in a given directory
 */
class MdFileVisitorTest {
  ArrayList<MarkdownFile> testList;
  MdFileVisitor tempFileVisit;
  Path validFilePath;
  Path validNotMd;
  Path validDirectoryPath;

  /**
   * initialization method to be run before each test method
   */
  @BeforeEach
  public void setup() {
    testList = new ArrayList<>();
    tempFileVisit = new MdFileVisitor(this.testList);
    validFilePath = Path.of("sampleinput/arrays.md");
    validNotMd = Path.of("test.txt");

    validDirectoryPath = Path.of("sampleinput/");
  }

  /**
   * tests preVisitDirectory in MdFileVisitor
   */
  @Test
  public void testPreVisitDirectory() {
    try {
      assertEquals(tempFileVisit.preVisitDirectory(validDirectoryPath,
              Files.readAttributes(validDirectoryPath, BasicFileAttributes.class)),
          FileVisitResult.CONTINUE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * tests visitFile in MdFileVisitor
   */
  @Test
  public void testVisitFile() {
    // Testing with a markdown file
    try {
      assertEquals(tempFileVisit.visitFile(validFilePath,
              Files.readAttributes(validFilePath, BasicFileAttributes.class)),
          FileVisitResult.CONTINUE);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(testList.size(), 1);
    assertEquals(testList.get(0).getFile(), new File("sampleinput/arrays.md"));

    // Testing with a non-markdown file
    try {
      assertEquals(tempFileVisit.visitFile(validNotMd,
              Files.readAttributes(validNotMd, BasicFileAttributes.class)),
          FileVisitResult.CONTINUE);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(testList.size(), 1);
    assertEquals(testList.get(0).getFile(), new File("sampleinput/arrays.md"));
  }

  /**
   * tests visitFileFailed in MdFileVisitor
   */
  @Test
  public void testVisitFileFailed() {
    assertEquals(tempFileVisit.visitFileFailed(validFilePath, new IOException("Test")),
        FileVisitResult.CONTINUE);
  }

  /**
   * tets postVisitDirectory in MdFileVisitor
   */
  @Test
  public void testPostVisitDirectory() {
    assertEquals(tempFileVisit.postVisitDirectory(validDirectoryPath, null),
        FileVisitResult.CONTINUE);
    assertEquals(tempFileVisit.postVisitDirectory(validDirectoryPath, new IOException("Test")),
        FileVisitResult.CONTINUE);
  }
}