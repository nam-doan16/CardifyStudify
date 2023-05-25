package cs3500.pa02;

import java.io.File;
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
   * @param questions - list of questions
   */
  public FileDataReader(ArrayList<MarkdownFile> mdFiles, ArrayList<Question> questions) {
    this.mdFiles = mdFiles;
    this.questions = questions;
  }

  /**
   * returns a String containing filtered text from all markdown files
   *
   * @return filtered text from all markdown files in mdFiles
   * @throws NoSuchFileException thrown if file
   */
  public String getFilteredData() throws NoSuchFileException {

    StringBuilder filteredData = new StringBuilder();
    for (MarkdownFile mdFile : this.mdFiles) {
      String tempString = this.getFileContents(mdFile.getFile());
      filteredData.append(tempString);
    }
    return filteredData.toString();
  }

  /**
   * Retrieves the contents from the given file
   *
   * @param file a file to a valid path and filename
   * @return contents of the file
   * @throws NoSuchFileException if the file doesn't exist or unreachable
   */
  public String getFileContents(File file) throws NoSuchFileException {
    Scanner fileReader;
    try {
      fileReader = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new NoSuchFileException("File not found: "
          + file);
    }
    StringBuilder tempString = new StringBuilder();
    while (fileReader.hasNextLine()) {
      tempString.append(fileReader.nextLine()).append("\n");
    }
    fileReader.close();

    return this.processString(tempString + "\n");
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
        Question tempQuestion = new Question(question, answer);

        int closingBracketIndex = str.substring(str.indexOf("]]") + 2).length();
        if (closingBracketIndex > Difficulty.EASY.toString().length() + 1) {
          // + 2 to negate the double brackets, + 1 to negate the dash
          // + 7 to consider the length of HARD and EASY (+4 + 3)
          String possibleDifficulty = str.substring(str.indexOf("]]") + 3, str.indexOf("]]") + 7);
          if (str.charAt(str.indexOf("]]") + 2) == '-') {
            tempQuestion = new Question(question, answer, Difficulty.valueOf(possibleDifficulty));
          }
        }
        questions.add(tempQuestion);

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
