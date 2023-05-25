package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.mock.MockStudySessionView;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionControllerTest {
  private StudySessionController controller;
  private StudySessionController controllerInputOne;
  private StudySessionController controllerHighBound;
  private StudySessionController controllerLowBound;
  private StudySessionController exitInput;
  private IntfStudySessionView view;
  private Appendable strBuild;
  private StudySessionModel model;

  @BeforeEach
  public void setUpController() {
    this.strBuild = new StringBuilder();
    view = new MockStudySessionView();
    IntfStudySessionView highBoundaryView = new MockStudySessionView(5);
    IntfStudySessionView lowBoundaryView = new MockStudySessionView(0);
    IntfStudySessionView one = new MockStudySessionView(1);
    IntfStudySessionView four = new MockStudySessionView(4);
    try {
      this.model = new StudySessionModel(new File("sampleoutput/lol.sr"));

      // new StringReader 3 is to input "3" for the amount of questions to be asked
      // new instances has to be made every time
      this.controller =
          new StudySessionController(new StringReader("3"), strBuild, view, model);
      this.controllerHighBound =
          new StudySessionController(new StringReader("3"), strBuild, highBoundaryView, model);
      this.controllerLowBound =
          new StudySessionController(new StringReader("3"), strBuild, lowBoundaryView, model);
      this.controllerInputOne =
          new StudySessionController(new StringReader("3"), strBuild, one, model);
      this.exitInput =
          new StudySessionController(new StringReader("3"), strBuild, four, model);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testFirstConstructor() {
    StringReader test = new StringReader("sampleinput/test.sr");
    StringReader testInvalid = new StringReader("Ok");
    StringBuilder tempTest = new StringBuilder();
    try {
      new StudySessionController(test, tempTest);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Invalid Path and Filename
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new StudySessionController(testInvalid, tempTest),
        "Invalid Path and Filename: Ok");
    assertEquals(e.getMessage(), "Invalid Path and Filename: Ok");
  }

  @Test
  public void testRunStudySession() {
    try {
      this.controller.runStudySession();
      this.controllerInputOne.runStudySession();
      this.exitInput.runStudySession();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Exception highBound = assertThrows(IllegalArgumentException.class,
        () -> this.controllerHighBound.runStudySession(), "Invalid input choice: 5");
    Exception lowBound = assertThrows(IllegalArgumentException.class,
        () -> this.controllerLowBound.runStudySession(), "Invalid input choice: 0");
    assertEquals(highBound.getMessage(), "Invalid input choice: 5");
    assertEquals(lowBound.getMessage(), "Invalid input choice: 0");
  }

  @Test
  public void testInputNumQuestions() {
    // testing non-numeric numbers
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new StudySessionController(
            new StringReader("a"), strBuild, view, model).runStudySession(), "Invalid input: a");
    assertEquals(e.getMessage(), "Invalid input: a");

    // testing negatives
    e = assertThrows(IllegalArgumentException.class,
        () -> new StudySessionController(new StringReader("-1"), strBuild, view, model)
            .runStudySession(), "Invalid input: -1");
    assertEquals(e.getMessage(), "Invalid input: -1");
  }
}