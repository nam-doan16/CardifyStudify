package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.Test;

/**
 * Represents a class testing FileNameComparator, which is a comparator
 * class for comparing files based on their filenames
 */
class FileNameComparatorTest {
  final File arraysFile = new File("sampleinput/arrays.md");
  final File vectorsFile = new File("sampleinput/vectors.md");

  // used to cover the 2nd branch for javacoco testing (no File Separators)
  final File badFilePath = new File("arrays.md");
  MarkdownFile mdArraysFile = new MarkdownFile(arraysFile);
  MarkdownFile mdVectorsFile = new MarkdownFile(vectorsFile);
  MarkdownFile mdBadFilePath = new MarkdownFile(badFilePath);

  /**
   * Test for the implemented compare method in FileNameComparator
   */
  @Test
  public void testCompare() {
    FileNameComparator comparator = new FileNameComparator();
    // <0 because "arrays.md" comes before "vectors.md" (-21 is the diff)
    assertEquals(comparator.compare(mdArraysFile, mdVectorsFile), -21);
    assertEquals(comparator.compare(mdBadFilePath, mdVectorsFile), -21);

    // >0 because "vectors.md" comes after "arrays.md" (21 is the diff)
    assertEquals(comparator.compare(mdVectorsFile, mdArraysFile), 21);
    assertEquals(comparator.compare(mdVectorsFile, mdBadFilePath), 21);

    // 0 because "arrays.md" is the same as "arrays.md"
    assertEquals(comparator.compare(mdArraysFile, mdArraysFile), 0);
  }
}