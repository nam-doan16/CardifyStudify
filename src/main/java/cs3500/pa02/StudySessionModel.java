package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents the model for a StudySession, handling all the functionality
 */
public class StudySessionModel {
  private int questionsAnswered;
  private int easyToHard;
  private int hardToEasy;
  private final ArrayList<Question> questions;
  private final FileDataWriter writer;

  /**
   * Constructor for StudySessionModel
   *
   * @param file - Spaced repetition file path and filename
   * @throws IOException - thrown if file doesn't exist or unreachable
   */
  public StudySessionModel(File file) throws IOException {
    this.hardToEasy = 0;
    this.easyToHard = 0;
    this.questionsAnswered = 0;
    this.questions = new ArrayList<>();
    FileDataReader fileReader = new FileDataReader(null, this.questions);

    // updates this.questions
    fileReader.getFileContents(file);
    writer = new FileDataWriter(null, file, this.questions);
  }

  /**
   * Updates the spaced repetition file with updated difficulty changes
   *
   * @throws IOException - thrown if there is a problem with reading/writing to the file
   */
  public void updateFile() throws IOException {
    writer.writeQuestions();
  }

  /**
   * Getter method to retrieve the statistic of total amount of questions answered
   *
   * @return total amount of questions answered
   */
  public int getQuestionsAnswered() {
    return this.questionsAnswered;
  }

  /**
   * Getter method to retrieve the statistic of how many questions went from easy to hard
   *
   * @return amount of questions that changed from easy to hard
   */
  public int getEasyToHard() {
    return this.easyToHard;
  }

  /**
   * Getter method to retrieve the statistic of how many questions went from hard to easy
   *
   * @return amount of questions that changed from hard to easy
   */
  public int getHardToEasy() {
    return this.hardToEasy;
  }

  /**
   * computes and returns the total amount of easy questions in a list of questions
   *
   * @return amount of easy questions in a list of questions
   */
  public int getEasyAmount() {
    // if a question isn't hard, then it HAS to be easy

    return this.questions.size() - this.getHardAmount();
  }

  /**
   * computes and returns the total amount of hard questions in a list of questions
   *
   * @return amount of hard questions in a list of questions
   */
  public int getHardAmount() {
    int hardCount = 0;
    for (Question q : questions) {
      if (q.compareDifficulty(Difficulty.HARD)) {
        hardCount++;
      }
    }
    return hardCount;
  }

  /**
   * generates a random list of questions to be displayed to the user
   *
   * @param questions - amount of questions to be added
   * @param rand - Random object for randomness in question picking
   * @return randomized list of questions to be displayed and answered by the user
   */
  public ArrayList<Question> generateQuestionSet(int questions, Random rand) {
    ArrayList<Question> clonedQuestions = new ArrayList<>(this.questions);
    Collections.shuffle(clonedQuestions, rand);

    if (questions > this.questions.size()) {
      return clonedQuestions;
    }
    ArrayList<Question> temp = new ArrayList<>();
    for (int i = 0; i < questions; i++) {
      int randomIndex = rand.nextInt(clonedQuestions.size());
      temp.add(clonedQuestions.get(randomIndex));

      // prevents duplicate questions
      clonedQuestions.remove(randomIndex);
    }
    return temp;
  }

  /**
   * Updates the given question depending on the given choice and the question's current difficulty
   *
   * @param q - the question
   * @param choice either 1 or 2 (1 - easy, 2 - hard)
   */
  public void updateDifficultyChange(Question q, int choice) {
    // 1 == easy, 2 == hard
    if (choice == 1) {
      easyToHard = (q.switchDifficulty(Difficulty.EASY)) ? easyToHard + 1 : easyToHard;
    } else if (choice == 2) {
      hardToEasy = (q.switchDifficulty(Difficulty.HARD)) ? hardToEasy + 1 : hardToEasy;
    }
  }

  /**
   * Updates the state of total questions answered by 1
   */
  public void updateQuestionsAnswered() {
    this.questionsAnswered++;
  }
}
