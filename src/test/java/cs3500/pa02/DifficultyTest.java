package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DifficultyTest {
  @Test
  void testValues() {
    assertEquals(Difficulty.values()[0], Difficulty.NONE);
    assertEquals(Difficulty.values()[1], Difficulty.EASY);
    assertEquals(Difficulty.values()[2], Difficulty.HARD);
  }

  @Test
  void testValueOf() {
    assertEquals(Difficulty.valueOf("NONE"), Difficulty.NONE);
    assertEquals(Difficulty.valueOf("EASY"), Difficulty.EASY);
    assertEquals(Difficulty.valueOf("HARD"), Difficulty.HARD);
  }
}