package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionModelTest {
  private StudySessionModel model;
  private Question question;

  @BeforeEach
  public void setUp() {
    try {
      this.model = new StudySessionModel(new File("otherTestFiles/noChange.sr"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.question = new Question("Test1", "Test2", Difficulty.EASY);
  }

  @Test
  void updateFile() {
  }

  /**
   * Tests for both get and update questionsAnswered
   */
  @Test
  void getQuestionsAnswered() {
    // testing initial state of questionsAnswered
    assertEquals(this.model.getQuestionsAnswered(), 0);

    // updating
    this.model.updateQuestionsAnswered();

    // checking state after
    assertEquals(this.model.getQuestionsAnswered(), 1);

    // updating
    this.model.updateQuestionsAnswered();

    // checking state after
    assertEquals(this.model.getQuestionsAnswered(), 2);
  }

  /**
   * Testing updateDifficultyChange and getEasytoHard and vice versa
   */
  @Test
  public void testEasyHardStats() {
    // checking initial states of the stats
    assertEquals(this.model.getEasyToHard(), 0);
    assertEquals(this.model.getHardToEasy(), 0);

    // easy -> easy (no change)
    this.model.updateDifficultyChange(question, 1);
    assertEquals(this.model.getEasyToHard(), 0);
    assertEquals(this.model.getHardToEasy(), 0);

    // easy question -> hard question
    this.model.updateDifficultyChange(question, 2);

    // checking states of the stats
    assertEquals(this.model.getEasyToHard(), 0);
    assertEquals(this.model.getHardToEasy(), 1);

    // hard -> hard (no change) or even when a wrong number is given
    this.model.updateDifficultyChange(question, 2);
    this.model.updateDifficultyChange(question, 3);
    assertEquals(this.model.getEasyToHard(), 0);
    assertEquals(this.model.getHardToEasy(), 1);

    // hard question -> easy
    this.model.updateDifficultyChange(question, 1);

    // checking states of the stats
    assertEquals(this.model.getEasyToHard(), 1);
    assertEquals(this.model.getHardToEasy(), 1);
  }

  @Test
  public void testGetEasyAmount() {
    assertEquals(this.model.getEasyAmount(), 1);
  }

  @Test
  public void testGetHardAmount() {
    assertEquals(this.model.getHardAmount(), 2);
  }

  @Test
  public void testGenerateQuestionSet() {
    ArrayList<Question> tempQuestions = new ArrayList<>();
    FileDataReader reader = new FileDataReader(null, tempQuestions);
    try {
      reader.getFileContents(new File("otherTestFiles/noChange.sr"));
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }
    ArrayList<Question> randomQuestions = this.model.generateQuestionSet(3, new Random(5));
    assertEquals(randomQuestions.get(0).toString(), tempQuestions.get(2).toString());
    assertEquals(randomQuestions.get(1).toString(), tempQuestions.get(0).toString());
    assertEquals(randomQuestions.get(2).toString(), tempQuestions.get(1).toString());
    assertEquals(randomQuestions.size(), 3);

    // testing questions greater than size
    randomQuestions = this.model.generateQuestionSet(100, new Random(5));
    assertEquals(randomQuestions.get(0).toString(), tempQuestions.get(1).toString());
    assertEquals(randomQuestions.get(1).toString(), tempQuestions.get(0).toString());
    assertEquals(randomQuestions.get(2).toString(), tempQuestions.get(2).toString());
    assertEquals(randomQuestions.size(), 3);
  }


}