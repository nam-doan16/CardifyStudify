package cs3500.pa02;

import java.io.File;
import java.nio.file.attribute.FileTime;

/**
 * Represents a Markdown file
 */
public class MarkdownFile {
  private final FileTime creationDate;
  private final FileTime lastModifiedTime;
  private final File file;

  /**
   * Constructor used for testing (no creation time worried about)
   *
   * @param file - File object of the markdown file
   */
  public MarkdownFile(File file) {
    this(FileTime.fromMillis(0), FileTime.fromMillis(0), file);
  }

  /**
   * main constructor for MarkdownFile
   *
   * @param creationDate - represents the file's creation time
   * @param lastModifiedTime - represents the file's last modified time
   * @param file - represents the file object of the markdown file
   */
  public MarkdownFile(FileTime creationDate, FileTime lastModifiedTime, File file) {
    this.creationDate = creationDate;
    this.lastModifiedTime = lastModifiedTime;
    this.file = file;
  }

  /**
   * getter method for MarkdownFile
   *
   * @return file object of the markdown file
   *
   */
  public File getFile() {
    return this.file;
  }

  /**
   * compares this markdown file's creation time with the given markdown file
   *
   * @param other - represents the other Markdown File to be compared
   * @return -1 if this md file comes before the given
   *         0 if they are the same
   *         1 if the other md file comes before this md file
   */
  public int compareCreationDate(MarkdownFile other) {
    return other.compareCreationDateHelp(this.creationDate);
  }

  /**
   * Helper method for compare creation date
   *
   * @param other - other Filetime
   * @return -1 if this md file comes before the given
   *         0 if they are the same
   *         1 if the other md file comes before this md file
   */
  private int compareCreationDateHelp(FileTime other) {
    return Long.compare(other.toMillis(), this.creationDate.toMillis());
  }

  /**
   * compares this markdown file's last modified time with the given markdown file's
   *
   * @param other - represents the other Markdown File to be compared
   * @return -1 if this md file comes before the given
   *         0 if they are the same
   *         1 if the other md file comes before this md file
   */
  public int compareModifiedTime(MarkdownFile other) {
    return other.compareModifiedTimeHelp(this.lastModifiedTime);
  }

  /**
   * Helper method for compare modified time
   *
   * @param other - other Filetime for last modified time
   * @return -1 if this md file comes before the given
   *         0 if they are the same
   *         1 if the other md file comes before this md file
   */
  private int compareModifiedTimeHelp(FileTime other) {
    return Long.compare(other.toMillis(), this.lastModifiedTime.toMillis());
  }

}
