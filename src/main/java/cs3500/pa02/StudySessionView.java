package cs3500.pa02;

import java.util.Objects;

/**
 * Represents a viewing class for a Study Session, responsible for handling outputs to the user
 */
public class StudySessionView {
  private final ConsoleWriter writer;

  public StudySessionView(Appendable output) {
    this.writer = new ConsoleWriter(Objects.requireNonNull(output));
  }

  /**
   * Throws an IllegalArgumentException with the given string as a supplement for information
   *
   * @param str - Associated String with the Exception
   */
  public void throwIllegalException(String str) {
    throw new IllegalArgumentException(str);
  }

  /**
   * Prints the given string, formatted to properly take in an input on the same line
   *
   * @param str string prior to accepting input
   */
  public void printInputMessage(String str) {
    // formats "What is 1+1?" as "What is 1+1? " to give room for input
    writer.write(str + " ");
  }

  /**
   * Prints the given string with a new line generated after
   *
   * @param str string to be outputted to the user
   */
  public void printMessage(String str) {
    writer.write(str + "\n");
  }

  /**
   * Prints out the question and its choices, then takes in a user input
   *
   * @param q Question to be displayed
   * @param input Reader object to obtain user input
   * @return integer selection (inputted)
   */
  public int getQuestion(Question q, Reader input) {
    this.printMessage("Question: " + q.getQuestion());
    return this.getChoices(input, "Show Answer");
  }

  /**
   * Prints out the answer to the given question and its choices, then takes in a user input
   *
   * @param q Question and its values to be displayed
   * @param input - Reader object to obtain user input
   * @return integer selection (inputted)
   */
  public int getAnswer(Question q, Reader input) {
    this.printMessage("Answer: " + q.getAnswer());
    return this.getChoices(input, "Show Question");
  }

  /**
   * Prints out choices for the question and obtains a user input for it
   *
   * @param input - Reader object to obtain user input
   * @param thirdChoice - Either "Show Answer" or "Show Question"
   * @return integer of user input (1-4)
   */
  public int getChoices(Reader input, String thirdChoice) {
    this.printMessage("\n1. Mark Easy\n2. Mark Hard\n3. " + thirdChoice + "\n4. Exit");
    this.printInputMessage("Choice:");
    String userInput = input.read();
    if (!ArgumentValidator.isNumber(userInput)) {
      this.throwIllegalException("Invalid input!");
    }
    return Integer.parseInt(userInput);
  }

  /**
   * Prints out the given statistics to the user
   *
   * @param questionsAnswered - total questions answered
   * @param easyToHard - amount of questions that changed from easy to hard
   * @param hardToEasy - amount of questions that changed from hard to easy
   * @param totalEasy - total easy questions in the original question bank
   * @param totalHard - total hard question in the original question bank
   */
  public void summarizer(int questionsAnswered, int easyToHard, int hardToEasy,
                           int totalEasy, int totalHard) {
    this.printMessage("Congratulations!");
    this.printMessage("Total Questions Answered: " + questionsAnswered);
    this.printMessage("Total Questions Changed From Easy to Hard: " + easyToHard);
    this.printMessage("Total Questions Changed From Hard to Easy: " + hardToEasy);
    this.printMessage("Total Number of Easy Questions in the Question Bank: " + totalEasy);
    this.printMessage("Total Number of Hard Questions in the Question Bank: " + totalHard);
  }
}
