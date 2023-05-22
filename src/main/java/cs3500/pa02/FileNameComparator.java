package cs3500.pa02;

import java.io.File;
import java.util.Comparator;

/**
 * Represents a Comparator class that compares markdown Files based on their filenames
 */
public class FileNameComparator implements Comparator<MarkdownFile> {
  /**
   * Converts the two given file to a string representing their filename, then
   * returns the unicode difference of the first and second file.
   *
   * @param o1 first file to be compared
   * @param o2 second file to be compared
   * @return unicode difference of each file's filenames
   */
  @Override
  public int compare(MarkdownFile o1, MarkdownFile o2) {
    String f1 = o1.getFile().toString();
    String f2 = o2.getFile().toString();
    if (f1.lastIndexOf(File.separator) != -1) {
      f1 = f1.substring(f1.lastIndexOf(File.separator) + 1);
    }
    if (f2.lastIndexOf(File.separator) != -1) {
      f2 = f2.substring(f2.lastIndexOf(File.separator) + 1);
    }
    return f1.compareTo(f2);
  }
}
