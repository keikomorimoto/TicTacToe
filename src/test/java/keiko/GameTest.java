package test.java.keiko;

import static org.junit.jupiter.api.Assertions.*;

import main.java.keiko.Game;
import main.java.keiko.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(3);
    }

    @Test
    public void testValidMove() {
        Game game = new Game(3);
        try {
            boolean result;

            result = game.move(1, 0, 0);
            assertFalse(result); // no win

            result = game.move(1, 1, 1);
            assertFalse(result); // no win

            result = game.move(1, 2, 2);
            assertTrue(result); // Expecting a win
        } catch (InvalidMoveException e) {
            fail("Unexpected exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInvalidMove() {
        Game game = new Game(3);
        assertThrows(InvalidMoveException.class, () -> {
            game.move(1, 0, 0); // First move
            game.move(2, 0, 0); // Invalid move, should throw exception
        });
    }
}
