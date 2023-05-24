package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

/**
 * Represents a class that handles writing content to a file
 */
public class FileDataWriter {
  private final FileWriter writer;
  private final FileWriter srWriter;
  private final ArrayList<MarkdownFile> mdFiles;
  private final ArrayList<Question> questions;

  /**
   * Constructor for MdFileWriter
   *
   * @param mdFiles - list of markdown files
   * @param output - Output path and filename
   * @throws IOException - thrown if something goes wrong with the instantiation of writer
   */
  public FileDataWriter(ArrayList<MarkdownFile> mdFiles, File output) throws IOException {
    this(mdFiles, output, new ArrayList<>());
  }

  /**
   * Constructor for FileDataWriter that also takes in a list of questions
   *
   * @param mdFiles - list of markdown files
   * @param output - output path and filename
   * @param questions - list of questions
   * @throws IOException - thrown if something goes wrong with the instantiation of writer
   */
  public FileDataWriter(ArrayList<MarkdownFile> mdFiles, File output,
                        ArrayList<Question> questions) throws IOException {
    writer = new FileWriter(output);

    // removing .md from output path
    String outputSr = output.toString();
    if (outputSr.contains(".md")) {
      outputSr = outputSr.substring(0, outputSr.lastIndexOf(".")) + ".sr";
    }
    srWriter = new FileWriter(outputSr);
    this.mdFiles = mdFiles;
    this.questions = questions;

  }

  /**
   * Reads each file from the markdown file list and properly writes them to the output path
   */
  public void writeToFile() throws NoSuchFileException {
    FileDataReader reader = new FileDataReader(this.mdFiles, this.questions);
    try {
      writer.write(reader.getFilteredData());
      writer.close();
      this.writeQuestions();
    } catch (IOException e) {
      throw new NoSuchFileException("File not reachable");
    }
  }

  /**
   * Writes questions from this list of questions to an SR file
   *
   * @throws IOException if reading/writing to the SR file fails
   */
  public void writeQuestions() throws IOException {
    for (Question q : this.questions) {
      srWriter.write(q.toString());
    }
    srWriter.close();
  }
}
