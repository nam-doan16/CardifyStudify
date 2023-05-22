package cs3500.pa02;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that handles reading markdown files
 */
public class FileDataReader {
  private final ArrayList<MarkdownFile> mdFiles;
  private final ArrayList<Question> questions;

  /**
   * Constructor for MdFileReader
   *
   * @param mdFiles - list of markdown files
   */
  FileDataReader(ArrayList<MarkdownFile> mdFiles) {
    this.mdFiles = mdFiles;
    this.questions = new ArrayList<>();
  }

  /**
   * returns a String containing filtered text from all markdown files
   *
   * @return filtered text from all markdown files in mdFiles
   */
  public String getFilteredData() throws NoSuchFileException {
    Scanner fileReader;
    String filteredData = "";
    for (int i = 0; i < this.mdFiles.size(); i++) {
      try {
        fileReader = new Scanner(this.mdFiles.get(i).getFile());
      } catch (FileNotFoundException e) {
        throw new NoSuchFileException("File not found: "
            + this.mdFiles.get(i).getFile().toString());
      }
      String tempString = "";
      while (fileReader.hasNextLine()) {
        tempString += fileReader.nextLine() + "\n";
      }
      filteredData += this.processString(tempString) + "\n";
    }
    return filteredData;
  }

  /**
   * Filters plaintext encased in double brackets (e.g. "[[hi]]" -> "hi")
   * If there is a question syntax in the plaintext within the double brackets,
   * add it to the questions list
   *
   * @param str - Lines of content from a markdown file
   * @return Filtered string
   */
  public String processString(String str) {
    while (str.contains("[[") && str.substring(str.indexOf("[[")).contains("]]")) {
      // Text inside the first instance of text encased in double brackets
      // "[[hello]]" -> "hello"
      String parsedText = str.substring(str.indexOf("[[") + 2, str.indexOf("]]"));

      // checking if the parsedText contains the syntax for a question
      if (parsedText.contains(":::")) {
        // parsing the question and answer out, then adding it to the questions list
        String question = parsedText.substring(0, parsedText.indexOf(":::"));
        String answer = parsedText.substring(parsedText.indexOf(":::") + 3);
        questions.add(new Question(question, answer));

        // removing instance of question
        parsedText = "";
      }

      // removing the brackets
      str = str.substring(0, str.indexOf("[["))
          + parsedText
          + str.substring(str.indexOf("]]") + 2);

    }
    return str;
  }
}
