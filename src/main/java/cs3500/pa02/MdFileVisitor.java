package cs3500.pa02;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a class that handles visiting markdown files and adding valid
 * markdown files to the given list
 */
public class MdFileVisitor implements FileVisitor<Path> {
  private final ArrayList<MarkdownFile> files;

  /**
   * Constructor for MdFileVisitor
   *
   * @param files - list of markdown files
   */
  public MdFileVisitor(ArrayList<MarkdownFile> files) {
    this.files = files;
  }

  /**
   * Verifies that the given directory should be visited and examined
   *
   * @param dir   path of the directory
   * @param attrs directory's attributes
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * Invoked for a file in a directory.
   * Adds a new instance of a MarkdownFile to the given list containing the
   * creation time, modified time, and a file instance of the path
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (file.getFileName().toString().endsWith(".md")) {
      files.add(new MarkdownFile(attrs.creationTime(), attrs.lastModifiedTime(), file.toFile()));
    }
    return CONTINUE;
  }

  /**
   * Continues search despite file not being able to be visited
   *
   * @param file path of the file
   * @param exc  the I/O exception from the file not being able to be visited
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    exc.printStackTrace();
    return CONTINUE;
  }

  /**
   * Continues to search other directories within the scope
   *
   * @param dir path of the directory
   * @param exc null if everything is fine, I/O exception if something happens within the search
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    if (!Objects.isNull(exc)) {
      exc.printStackTrace();
      System.err.println("Problem with searching through the directory: " + dir);
    }
    return CONTINUE;
  }
}
