package cs3500.pa02;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a reader class responsible for taking in user input
 */
public class Reader {
  private final Readable readable;

  /**
   * Constructor for Reader
   *
   * @param readable - Readable object
   */
  public Reader(Readable readable) {
    this.readable = Objects.requireNonNull(readable);
  }

  /**
   * Reads input from readable and returns the input
   *
   * @return input from readable
   */
  public String read() {
    Scanner scanner = new Scanner(this.readable);

    StringBuilder output = new StringBuilder();

    if (scanner.hasNextLine()) {
      output.append(scanner.nextLine());
    }
    return output.toString();
  }
}
