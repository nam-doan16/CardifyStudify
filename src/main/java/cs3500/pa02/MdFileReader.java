package cs3500.pa02;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that handles reading markdown files
 */
public class MdFileReader {
  private final ArrayList<MarkdownFile> mdFiles;

  /**
   * Constructor for MdFileReader
   *
   * @param mdFiles - list of markdown files
   */
  MdFileReader(ArrayList<MarkdownFile> mdFiles) {
    this.mdFiles = mdFiles;
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
   *
   * @param str - Lines of content from a markdown file
   * @return Filtered string
   */
  public String processString(String str) {
    while (str.contains("[[") && str.substring(str.indexOf("[[")).contains("]]")) {
      str = str.substring(0, str.indexOf("[["))
          + str.substring(str.indexOf("[[") + 2, str.indexOf("]]"))
          + str.substring(str.indexOf("]]") + 2);
    }
    return str;
  }
}
