package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

/**
 * Represents a class that handles writing content to a file
 */
public class MdFileWriter {
  private final FileWriter writer;
  private final ArrayList<MarkdownFile> mdFiles;
  private final ArrayList<Question> questions;

  /**
   * Constructor for MdFileWriter
   *
   * @param mdFiles - list of markdown files
   * @param output - Output path and filename
   * @throws IOException - thrown if something goes wrong with the instantiation of writer
   */
  public MdFileWriter(ArrayList<MarkdownFile> mdFiles, File output) throws IOException {
    writer = new FileWriter(output);
    this.mdFiles = mdFiles;
    this.questions = new ArrayList<>();

  }

  /**
   * Reads each file from the markdown file list and properly writes them to the output path
   */
  public void writeToFile() throws NoSuchFileException {
    FileDataReader reader = new FileDataReader(this.mdFiles, this.questions);
    try {
      writer.write(reader.getFilteredData());
      writer.close();
    } catch (IOException e) {
      throw new NoSuchFileException("File not reachable");
    }
  }
}
