package bowling.service;

import bowling.model.Ball;
import bowling.model.Game;
import bowling.service.GameEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEvaluatorTest {
    GameEvaluator gameEvaluator = new GameEvaluator();
    @Test
    public void shouldGiveScoreWhenAddingAllTheMissesInFrames() {
        String expression = "x|x|1/|3-|x|x|x|x|3-|1-||";
        Game game = gameEvaluator.getGame(expression);

        List<Ball> frameBalls = game.getFrameBalls();
        List<Ball> bonusBalls = game.getBonusBalls();
        assertEquals(14,frameBalls.size());
        assertEquals(10,frameBalls.get(0).getBallValue());
        assertEquals(10,frameBalls.get(1).getBallValue());
        assertEquals(1,frameBalls.get(2).getBallValue());
        assertEquals(9,frameBalls.get(3).getBallValue());
        assertEquals(3,frameBalls.get(4).getBallValue());
        assertEquals(0,frameBalls.get(5).getBallValue());
        assertEquals(10,frameBalls.get(6).getBallValue());
        assertEquals(10,frameBalls.get(7).getBallValue());

    }

}
