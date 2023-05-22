package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for FileModifiedComparator, which represents
 * a comparator class that compares files based on their modification date
 */
class FileModifiedComparatorTest {
  final File arraysFile = new File("sampleinput/arrays.md");
  final File vectorsFile = new File("sampleinput/vectors.md");
  MarkdownFile mdArraysFile = new MarkdownFile(FileTime.fromMillis(0),
      FileTime.fromMillis(1000), arraysFile);
  MarkdownFile mdVectorsFile = new MarkdownFile(FileTime.fromMillis(0),
      FileTime.fromMillis(3000), vectorsFile);

  /**
   * Tests the implemented compare method of FileModifiedComparator
   */
  @Test
  public void testCompare() {
    FileModifiedComparator comparator = new FileModifiedComparator();
    // -1 because "arrays.md" was modified before than "vectors.md"
    assertEquals(comparator.compare(mdArraysFile, mdVectorsFile), -1);

    // 1 because "vectors.md" was modified later than "arrays.md"
    assertEquals(comparator.compare(mdVectorsFile, mdArraysFile), 1);

    // 0 because "arrays.md" is the same as "arrays.md"
    assertEquals(comparator.compare(mdArraysFile, mdArraysFile), 0);
  }
}