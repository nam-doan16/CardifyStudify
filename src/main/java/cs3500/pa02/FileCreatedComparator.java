package cs3500.pa02;

import java.util.Comparator;

/**
 * Represents a Comparator that compares markdown files based on their creation time
 */
public class FileCreatedComparator implements Comparator<MarkdownFile> {
  /**
   * returns the difference between o1's and o2's creation time
   *
   * @param o1 first file to be compared
   * @param o2 second file to be compared.
   * @return difference between o1 and o2's file creation time
   */
  @Override
  public int compare(MarkdownFile o1, MarkdownFile o2) {
    return o1.compareCreationDate(o2);
  }
}
