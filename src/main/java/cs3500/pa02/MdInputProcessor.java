package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a class that handles file/path processing and sorting
 */
public class MdInputProcessor {
  private final ArrayList<MarkdownFile> mdFiles;

  /**
   * Constructor for MdFileProcessor
   * Handles the process of searching the entire tree and storing valid md files, then sorts it
   *
   * @param startPath - Directory to where all the markdown files are
   * @param order - One of the Three Ordering enum types (FILENAME, CREATED, or MODIFIED)
   * @throws IOException - thrown if something goes wrong with walkFileTree
   */
  public MdInputProcessor(Path startPath, Ordering order)
      throws IOException {
    this.mdFiles = new ArrayList<>();
    Files.walkFileTree(startPath, new MdFileVisitor(this.mdFiles));
    this.sortMdFiles(order);
  }

  /**
   * Sorts mdFiles depending on the given order
   * Will always be one of the three Ordering enum types
   *
   * @param order - One of the three Ordering enum types (FILENAME, CREATED, or MODIFIED)
   */
  private void sortMdFiles(Ordering order) {
    Comparator<MarkdownFile> sorter;
    if (order == Ordering.FILENAME) {
      sorter = new FileNameComparator();
    } else if (order == Ordering.CREATED) {
      sorter = new FileCreatedComparator();
    } else {
      sorter = new FileModifiedComparator();
    }
    this.mdFiles.sort(sorter);
  }

  /**
   * returns the list of accumulated markdown files
   *
   * @return list of accumulated markdown files
   */
  public ArrayList<MarkdownFile> getMdFiles() {
    return this.mdFiles;
  }

}
