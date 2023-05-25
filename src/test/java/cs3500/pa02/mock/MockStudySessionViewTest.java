package cs3500.pa02.mock;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockStudySessionViewTest {
  private MockStudySessionView mock;
  private MockStudySessionView mockTwo;

  @BeforeEach
  public void setUp() {
    mock = new MockStudySessionView();
    mockTwo = new MockStudySessionView(5);
  }

  /**
   * Tests mock methods that "supposedly" print, it does nothing.
   */
  @Test
  void printMessages() {
    mock.printInputMessage("LOL!");
    mock.printMessage("LOL!");
    mock.summarizer(0, 1, 2, 3, 4);
  }

  @Test
  void getQuestion() {
    // input choice is 3 and 5 respsectively
    assertEquals(mock.getQuestion(null, null), 3);
    assertEquals(mockTwo.getQuestion(null, null), 5);

    // transforming mock into 300
    assertEquals(mock.getAnswer(null, null), 3);

    // checking if mock is 2
    assertEquals(mock.getQuestion(null, null), 2);
  }

  @Test
  void getAnswer() {
    assertEquals(mock.getAnswer(null, null), 3);
    assertEquals(mockTwo.getAnswer(null, null), 5);

    // checking that mock changed into 300 after the first call
    assertEquals(mock.getQuestion(null, null), 2);
  }

  @Test
  void getChoices() {
    // 2 represents one of the two choices of easy or hard
    assertEquals(mock.getChoices(null, null), 2);
    assertEquals(mockTwo.getChoices(null, null), 2);
  }
}