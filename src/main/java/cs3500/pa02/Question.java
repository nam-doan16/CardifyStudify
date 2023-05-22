package cs3500.pa02;

/**
 * Represents a singular question
 */
public class Question {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  /**
   * Constructor for Question
   *
   * @param question - represents a question with an answer
   * @param answer - represents the answer to the given question
   * @param difficulty - represents the difficulty of answering the given question
   */
  public Question(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * returns this question
   *
   * @return question
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * returns this answer to this question
   *
   * @return answer
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Determines if this difficulty is the same as the given
   *
   * @param other - other difficulty (EASY or HARD)
   * @return boolean - whether the given difficulty is the same as this or not
   */
  public boolean compareDifficulty(Difficulty other) {
    return this.difficulty == other;
  }

  /**
   * if the given difficulty is different than this difficulty, swap this difficulty to the given
   * and return true to note swap was successful, otherwise return false and do nothing
   *
   * @param other - other difficulty
   * @return true if there was a switch, false if not
   */
  public boolean switchDifficulty(Difficulty other) {
    if (this.compareDifficulty(other)) {
      return false;
    } else {
      this.difficulty = other;
      return true;
    }
  }



}
