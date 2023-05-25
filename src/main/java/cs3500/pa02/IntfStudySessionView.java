package cs3500.pa02;

/**
 * Represents an Interface for a viewer/outputter for a Study Session
 */
public interface IntfStudySessionView {
  /**
   * Prints the given string, formatted for input
   *
   * @param str string to be printed
   */
  void printInputMessage(String str);

  /**
   * Prints the given string, with a empty line after it
   *
   * @param str string to be printed
   */
  void printMessage(String str);

  /**
   * Prints out the given question and a list of choices and takes the user's input
   *
   * @param q Question object for data
   * @param input - Reader object for user input
   * @return user integer input
   */
  int getQuestion(Question q, Reader input);

  /**
   * prints out the given question's answer and a list of choices and take the user's input
   *
   * @param q Question object for data
   * @param input - Reader object for user input
   * @return user integer input
   */
  int getAnswer(Question q, Reader input);

  /**
   * Prints out a list of choices to the user and takes their input
   *
   * @param input - Reader object for user input
   * @param thirdChoice - Show answer or show question
   * @return valid integer input
   */
  int getChoices(Reader input, String thirdChoice);

  /**
   * prints out the given statistics
   *
   * @param questionsAnswered - total questions answered
   * @param easyToHard - questions changed from easy to hard
   * @param hardToEasy - questions changed from hard to easy
   * @param totalEasy - total easy questions in the question bank
   * @param totalHard - total hard questions in the question bank
   */
  void summarizer(int questionsAnswered, int easyToHard, int hardToEasy,
                         int totalEasy, int totalHard);
}
