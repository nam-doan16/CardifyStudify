package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a controller for a Study Session, responsible for taking in majority of user input,
 * and interacting with the functionality and display of the study session
 */
public class StudySessionController {
  private final StudySessionModel model;
  private final StudySessionView view;
  private final Reader input;

  /**
   * Constructor for StudySessionController
   *
   * @param input - Readable object responsible for handling data inputs
   * @param output - Appendable object responsible for handling outputs to a user
   * @throws IOException if the model's filepath is invalid
   */
  public StudySessionController(Readable input, Appendable output) throws IOException {
    this.view = new StudySessionView(output);
    this.input = new Reader(input);
    this.model = new StudySessionModel(this.inputFilePath());

  }

  /**
   * Runs a Study Session instance
   *
   * @throws IOException thrown if there is a problem w/ reading/writing to the model's stored file
   */
  public void runStudySession() throws IOException {
    int numQuestions = this.inputNumQuestions();
    ArrayList<Question> generatedQuestions = model.generateQuestionSet(numQuestions, new Random());
    int inputChoice;
    int answerOrBack = 0;
    for (Question q : generatedQuestions) {
      // 1. Easy 2. Hard 3. Show Answer/Go Back 4. Exit
      inputChoice = view.getQuestion(q, input);
      if (inputChoice > 4 || inputChoice < 1) {
        view.throwIllegalException("Invalid input choice: " + inputChoice);
      }

      while (inputChoice == 3) {
        if (answerOrBack % 2 == 0) {
          inputChoice = view.getAnswer(q, input);
        } else {
          inputChoice = view.getQuestion(q, input);
        }
        answerOrBack++;
      }

      if (inputChoice == 1 || inputChoice == 2) {
        model.updateDifficultyChange(q, inputChoice);
      } else {
        break;
      }
      this.model.updateQuestionsAnswered();
    }
    view.summarizer(model.getQuestionsAnswered(), model.getEasyToHard(), model.getHardToEasy(),
        model.getEasyAmount(), model.getHardAmount());
    model.updateFile();
  }

  /**
   * Gets user input and returns a valid file path and filename of an SR file
   *
   * @return valid file object with the path and filename of an SR file
   */
  private File inputFilePath() {
    File tempFile;
    view.printInputMessage("Input Path and Filename of your SR file:");
    tempFile = new File(input.read());
    if (!tempFile.exists()) {
      view.throwIllegalException("Invalid Path and Filename: " + tempFile);
    }
    return tempFile;
  }

  /**
   * takes in a user input for a valid integer over 0 and returns it
   *
   * @return valid integer > 0
   */
  private int inputNumQuestions() {
    String numQuestionsStr;
    view.printInputMessage("Enter how many questions you want to answer:");
    numQuestionsStr = input.read();
    if (!ArgumentValidator.isNumber(numQuestionsStr)) {
      view.throwIllegalException("Invalid input!");
    }
    int numQuestions = Integer.parseInt(numQuestionsStr);
    if (numQuestions <= 0) {
      view.throwIllegalException("Invalid number: " + numQuestions);
    }
    return numQuestions;
  }
}
