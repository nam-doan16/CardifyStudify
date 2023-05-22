package cs3500.pa02;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Objects;

/**
 * Represents a class that validates command-line arguments
 */
public class ArgumentValidator {
  /**
   * Determines if the given array of command-line arguments of type String has valid
   * properties (3 arguments: directory, order, path and filename; and any path/path and filename
   * exists and reachable)
   *
   * @param args - command-line arguments (String[])
   * @return true IF no arguments are thrown within the method
   * @throws IllegalArgumentException if exactly three command-line arguments are not given
   * @throws NoSuchFileException if the given paths/files do not exist or are unreachable
   */
  public static boolean isValid(String[] args) throws NoSuchFileException {
    // determines if there are only 3 command-line arguments (length of 3)
    if (Objects.isNull(args) || args.length != 3) {
      throw new IllegalArgumentException("Invalid Amount of Arguments");
    }

    // checks if the given ordering is parsable to their respective enumerated type
    parseOrder(args[1]);

    // checks if the given path to the directory with the md files is valid
    if (notValidDirec(new File(args[0]))) {
      throw new NoSuchFileException("Input Directory invalid: " + args[0]);
    }

    // removes the filename from the output path and filename
    // e.g. sampleinput/text.txt -> sampleinput/
    String directoryOnlyArg = args[2];
    if (args[2].lastIndexOf(File.separator) != -1) {
      directoryOnlyArg = args[2].substring(0, args[2].lastIndexOf(File.separator));
    }

    // checks if the given output path and filename is valid
    if (notValidDirec(new File(directoryOnlyArg))) {
      throw new NoSuchFileException("Output Directory invalid: " + args[2]);
    }

    return true;
  }

  /**
   * determines if the given file is valid
   *
   * @param file - instance of a file
   * @return whether file exists and if it's a file or directory
   */
  private static boolean notValidDirec(File file) {
    return !(file.exists() && file.isDirectory());
  }


  /**
   * returns the associated Ordering enumerated type with the given
   * order otherwise throws an IllegalArgumentException
   *
   * @param order - a string representing the order of files
   * @return associated Ordering enumerated type if exceptions not thrown
   * @throws IllegalArgumentException if there is no association with the given string order
   */
  // Warning suppressed b/c else statement doesn't return, rather it throws something
  @SuppressWarnings("UnusedReturnValue")
  public static Ordering parseOrder(String order) {
    if (order.equalsIgnoreCase("filename")) {
      return Ordering.FILENAME;
    } else if (order.equalsIgnoreCase("created")) {
      return Ordering.CREATED;
    } else if (order.equalsIgnoreCase("modified")) {
      return Ordering.MODIFIED;
    } else {
      throw new IllegalArgumentException("Bad ordering given: " + order);
    }
  }
}
