package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - three command-line arguments in the order of:
   *             1. relative or absolute path to the directory with all the markdown files
   *             2. ordering flag (filename, created, modified)
   *             3. output path and filename
   * @throws IllegalArgumentException if not exactly three command-line arguments are given or
   *                                  if the given ordering flag is invalid
   * @throws NoSuchFileException      if the paths/paths and filename aren't valid and
   *                                  if they don't exist
   */
  public static void main(String[] args) throws NoSuchFileException {
    if (ArgumentValidator.isValid(args)) {
      try {
        MdFileWriter writer = new MdFileWriter(new MdInputProcessor(Path.of(args[0]),
            ArgumentValidator.parseOrder(args[1])).getMdFiles(), new File(args[2]));
        writer.writeToFile();
      } catch (IOException e) {
        // for Files.walkFileTree
        throw new NoSuchFileException("File not found");
      }
    }
  }
}