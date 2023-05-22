package cs3500.pa02;

import java.util.Comparator;

/**
 * Represents a Comparator class representing the comparison of markdown files
 * based on their last modification time
 */
public class FileModifiedComparator implements Comparator<MarkdownFile> {
  /**
   * returns the difference between the 1st and 2nd given file's
   * last modification time
   *
   * @param o1 first file to be compared
   * @param o2 second file to be compared
   * @return difference between 1st and 2nd file's last modification time
   */
  @Override
  public int compare(MarkdownFile o1, MarkdownFile o2) {
    return o1.compareModifiedTime(o2);
  }
}
