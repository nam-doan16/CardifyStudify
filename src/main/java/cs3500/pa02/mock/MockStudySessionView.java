package cs3500.pa02.mock;

import cs3500.pa02.IntfStudySessionView;
import cs3500.pa02.Question;
import cs3500.pa02.Reader;

/**
 * Represents a viewing class for a Study Session, responsible for handling outputs to the user
 */
public class MockStudySessionView implements IntfStudySessionView {
  private int inputChoice;

  /**
   * Constructor for MockStudySessionView, 3 represents the number choice to show answer/question
   */
  public MockStudySessionView() {
    this(3);
  }

  /**
   * Constructor for MockStudySessionView
   *
   * @param inputChoice - number (positive)
   */
  public MockStudySessionView(int inputChoice) {
    this.inputChoice = inputChoice;
  }

  /**
   * mock print method
   *
   * @param str string
   */
  public void printInputMessage(String str) {

  }

  /**
   * mock print method
   *
   * @param str string
   */
  public void printMessage(String str) {

  }

  /**
   * returns the input choice, or if it is 300 from calling getAnswer() (this happens in the
   * program when the user is swapping between question/answer), then a valid choice of hard/easy
   * is returned
   *
   * @param q Question to be displayed
   * @param input Reader object to obtain user input
   * @return integer selection (inputted)
   */
  public int getQuestion(Question q, Reader input) {
    if (inputChoice == 300) {
      this.inputChoice = 2;
    }
    return inputChoice;

  }

  /**
   * if input choice is 3, make it 300 because getQuestion will be called again in the study
   * session and it allows for each branch in jacoco to be visited since it ends after the 2nd
   * getQuestion call, otherwise just return inputChoice
   *
   * @param q Question and its values to be displayed
   * @param input - Reader object to obtain user input
   * @return integer selection (inputted)
   */
  public int getAnswer(Question q, Reader input) {
    if (inputChoice == 3) {
      inputChoice = 300;
      return inputChoice - 297;
    }
    return inputChoice;

  }

  /**
   * returns the number 2 to represent a valid choice of easy/hard for one of the four choices
   *
   * @param input - Reader object to obtain user input
   * @param thirdChoice - Either "Show Answer" or "Show Question"
   * @return integer of user input (1-4)
   */
  public int getChoices(Reader input, String thirdChoice) {

    return 2;
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
  }
}
