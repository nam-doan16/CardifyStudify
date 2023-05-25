package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for Question
 */
class QuestionTest {
  private Question hardQuestion;
  private Question easyQuestion;

  /**
   * initialization method to be ran before each test method
   */
  @BeforeEach
  public void setUp() {
    this.hardQuestion = new Question("What is 1 + 1?", "2");
    this.easyQuestion = new Question("What is 4 + 4?", "8", Difficulty.EASY);
  }

  /**
   * Testing getQuestion method
   */
  @Test
  public void testGetQuestion() {
    assertEquals(this.hardQuestion.getQuestion(), "What is 1 + 1?");
    assertEquals(this.easyQuestion.getQuestion(), "What is 4 + 4?");
  }

  /**
   * Testing getAnswer method
   */
  @Test
  public void testGetAnswer() {
    assertEquals(this.hardQuestion.getAnswer(), "2");
    assertEquals(this.easyQuestion.getAnswer(), "8");
  }

  /**
   * Testing compareDifficulty method
   */
  @Test
  public void testCompareDifficulty() {
    // testing hard question
    assertFalse(this.hardQuestion.compareDifficulty(Difficulty.EASY));
    assertTrue(this.hardQuestion.compareDifficulty(Difficulty.HARD));

    // testing easy question
    assertTrue(this.easyQuestion.compareDifficulty(Difficulty.EASY));
    assertFalse(this.easyQuestion.compareDifficulty(Difficulty.HARD));
  }

  @Test
  public void switchDifficulty() {
    // testing initial difficulty
    assertTrue(this.hardQuestion.compareDifficulty(Difficulty.HARD));
    assertTrue(this.easyQuestion.compareDifficulty(Difficulty.EASY));

    // testing a false swap
    assertFalse(this.hardQuestion.switchDifficulty(Difficulty.HARD));
    assertFalse(this.easyQuestion.switchDifficulty(Difficulty.EASY));

    // checking false swap
    assertTrue(this.hardQuestion.compareDifficulty(Difficulty.HARD));
    assertTrue(this.easyQuestion.compareDifficulty(Difficulty.EASY));

    // testing a TRUE swap
    assertTrue(this.hardQuestion.switchDifficulty(Difficulty.EASY));
    assertTrue(this.easyQuestion.switchDifficulty(Difficulty.HARD));

    // checking TRUE swap
    assertFalse(this.hardQuestion.compareDifficulty(Difficulty.HARD));
    assertFalse(this.easyQuestion.compareDifficulty(Difficulty.EASY));
  }

  /**
   * Testing toString
   */
  @Test
  public void testToString() {
    String hard = "[[What is 1 + 1?:::2]]-HARD\n";
    String easy = "[[What is 4 + 4?:::8]]-EASY\n";
    assertEquals(this.hardQuestion.toString(), hard);
    assertEquals(this.easyQuestion.toString(), easy);
  }
}