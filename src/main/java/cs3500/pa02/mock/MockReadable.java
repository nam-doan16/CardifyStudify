package cs3500.pa02.mock;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Represents a Mock class for Readable for testing
 */
public class MockReadable implements Readable {
  /**
   * mock read method
   *
   * @param cb the buffer to read characters into
   * @return N/A
   * @throws IOException always thrown for testing
   */
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Readable Mock throwing an error");
  }
}
