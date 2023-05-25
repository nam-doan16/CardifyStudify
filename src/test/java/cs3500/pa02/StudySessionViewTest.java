package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for StudySessionView
 */
class StudySessionViewTest {
  private Appendable appendable;
  private StudySessionView view;
  private static final Question question = new Question("testQuestion", "testAnswer");

  /**
   * initialization method to be ran before each test
   */
  @BeforeEach
  public void setUp() {
    this.appendable = new StringBuilder();
    this.view = new StudySessionView(this.appendable);
  }

  /**
   * tests printInputMessage
   */
  @Test
  public void testPrintInputMessage() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    view.printInputMessage("Test");

    // testing after state of appendable
    assertEquals(this.appendable.toString(), "Test ");
  }

  /**
   * tests printMessage
   */
  @Test
  public void testPrintMessage() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    view.printMessage("Test");

    // testing after state of appendable
    assertEquals(this.appendable.toString(), "Test\n");
  }

  /**
   * tests getQuestion
   */
  @Test
  public void testGetQuestion() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");


    // writing
    assertEquals(view.getQuestion(question,
        new Reader(new StringReader("4"))), 4);

    // testing after state of appendable
    assertEquals(this.appendable.toString(),
        """
            Question: testQuestion

            1. Mark Easy
            2. Mark Hard
            3. Show Answer
            4. Exit
            Choice:\s""");
  }

  /**
   * tests getAnswer
   */
  @Test
  public void testGetAnswer() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    assertEquals(view.getAnswer(question,
        new Reader(new StringReader("4"))), 4);

    // testing after state of appendable
    assertEquals(this.appendable.toString(),
        """
            Answer: testAnswer

            1. Mark Easy
            2. Mark Hard
            3. Show Question
            4. Exit
            Choice:\s""");
  }

  /**
   * tests getChoices
   */
  @Test
  public void testGetChoices() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    assertEquals(view.getChoices(new Reader(new StringReader("4")),
        "Show Question"), 4);

    // testing after state of appendable
    assertEquals(this.appendable.toString(),
        "\n1. Mark Easy\n2. Mark Hard\n3. Show Question\n4. Exit\nChoice: ");

    // testing non-integer
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> view.getChoices(new Reader(new StringReader("a")), "Show Question"),
        "Invalid input!");
    assertEquals(e.getMessage(), "Invalid input!");
  }

  /**
   * tests summarizer
   */
  @Test
  public void testSummarizer() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    view.summarizer(5, 2, 3, 3, 2);

    // testing after state of appendable
    assertEquals(this.appendable.toString(), """
        Congratulations!
        Total Questions Answered: 5
        Total Questions Changed From Easy to Hard: 2
        Total Questions Changed From Hard to Easy: 3
        Total Number of Easy Questions in the Question Bank: 3
        Total Number of Hard Questions in the Question Bank: 2
        """);
  }
}