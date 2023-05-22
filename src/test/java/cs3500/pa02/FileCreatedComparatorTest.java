package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for FileCreatedComparator, which is a comparator
 * class that compares files based on their creation dates
 */
class FileCreatedComparatorTest {
  final File arraysFile = new File("sampleinput/arrays.md");
  final File vectorsFile = new File("sampleinput/vectors.md");
  final File namFile = new File("sampleinput/folderinsidefolder/namdoan.md");
  MarkdownFile mdArraysFile = new MarkdownFile(FileTime.fromMillis(1000),
      FileTime.fromMillis(1000), arraysFile);
  MarkdownFile mdVectorsFile = new MarkdownFile(FileTime.fromMillis(3000),
      FileTime.fromMillis(1000), vectorsFile);
  MarkdownFile mdNamFile = new MarkdownFile(FileTime.fromMillis(5000),
      FileTime.fromMillis(1000), namFile);


  /**
   * Tests for the implemented compare method in FileCreatedComparator
   */
  @Test
  void testCompare() {
    // FileCreatedComparator's compare calls fileToTime for its conversion from a file to a long
    FileCreatedComparator comparator = new FileCreatedComparator();
    // -1 because "arrays.md" was created before "vectors.md"
    assertEquals(comparator.compare(mdArraysFile, mdVectorsFile), -1);

    // vectors.md was created before namdoan.md
    assertEquals(comparator.compare(mdVectorsFile, mdNamFile), -1);

    // 1 because "vectors.md" was created after "arrays.md"
    assertEquals(comparator.compare(mdVectorsFile, mdArraysFile), 1);

    // 1 because namdoan.md was created after arrays.md
    assertEquals(comparator.compare(mdNamFile, mdArraysFile), 1);

    // 0 because "arrays.md" is the same as "arrays.md"
    assertEquals(comparator.compare(mdArraysFile, mdArraysFile), 0);
  }
}