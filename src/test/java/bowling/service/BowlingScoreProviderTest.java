package bowling.service;

import bowling.service.BowlingScoreProvider;
import bowling.service.GameEvaluator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingScoreProviderTest {
    BowlingScoreProvider bowlingScoreProvider = new BowlingScoreProvider();
    GameEvaluator gameEvaluator= new GameEvaluator();

    @ParameterizedTest
    @ValueSource(strings = {"w", "ere2"})
    public void shouldThrowValidationErrorWhenBonusSeparatorIsNotGiven(String expression) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid bonus size"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"w||we2||233||@£@||@£23", "23||22||"})
    public void shouldThrowValidationErrorWhenMoreThanOneBonusSeparatorIsGiven(String expression) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid bonus size"));
    }

    @ParameterizedTest

    @CsvSource({
            "String with frame size 4, w|we2|233|77||23 ,invalid frame size",
            "string with frame size 2, 23|22||,invalid frame size"
    })
    public void shouldThrowValidationErrorWhenFramesAreNot10(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () ->gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );

        assertEquals(expected, thrown.getMessage());
    }

    @ParameterizedTest

    @CsvSource({
            "String with invalid frame as X32, x|x|x32|x|x|x|x|x|x|x|| ,invalid frame expression frame:x32",
            "string with invalid frame as 3/21, 1/|2/|3/21|4/|5/|6/|7/|8/|9/|1/||,invalid frame expression frame:3/21"
    })
    public void shouldThrowValidationErrorWhenFrameFormatIsNotCorrect(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertEquals(expected, thrown.getMessage());
    }

    @ParameterizedTest

    @CsvSource({
            "String with invalid frame as 99, x|x|99|x|x|x|x|x|x|x|| ,invalid digits in given frame:99",
            "string with invalid frame as 88, 1/|2/|88|4/|5/|6/|7/|8/|9/|1/||,invalid digits in given frame:88"
    })
    public void shouldThrowValidationErrorWhenSumOfDigitsInFrameAreGreaterThan10(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertEquals(expected, thrown.getMessage());
    }
    @ParameterizedTest

    @CsvSource({
            "String with invalid bonus expression at last frame as digit, x|x|12|x|x|x|x|x|x|32||23 ,invalid bonus expression",
            "string with invalid bonus expression at last frame as miss, 1/|2/|21|4/|5/|6/|7/|8/|9/|1-||32,invalid bonus expression",
            "String with invalid bonus expression at last frame as spare, x|x|12|x|x|x|x|x|x|3/||23 ,invalid bonus expression",
            "string with invalid bonus expression at last frame as strike, 1/|2/|12|4/|5/|6/|7/|8/|9/|x||3,invalid bonus expression"
    })
    public void shouldThrowValidationErrorWhenValidBonusIsNotGiven(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> gameEvaluator.getGame(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertEquals(expected, thrown.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            "string with miss and digit, --|--|--|--|--|--|--|--|--|--||,0"

    })
    public void shouldGiveScoreWhenAddingAllTheMissesInFrames(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }

    @ParameterizedTest
    @CsvSource({

            "string with miss and digit, 1-|1-|2-|32|-3|--|--|--|--|--||,12"

    })
    public void shouldGiveScoreWhenAddingAllTheDigitsInFrames(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }

    @ParameterizedTest
    @CsvSource({
            "String with frames as X, x|x|1/|3-|x|x|x|x|3-|1-|| ,157",
            "string with spare in a frame,1/|1/|1/|1/|1/|1/|1/|1/|1/|11||,101",
            "string with spare in a frame,1/|1-|1/|-3|1/|1/|1/|1/|1/|11||,82",
            "string with two misses,--|--|--|--|--|--|--|--|--|1-||,1",
            "string with two digits,11|22|33|44|55|12|12|12|12|1-||,43",
            "string with digit and miss,1-|1-|1-|2-|2-|2-|3-|3-|3-|1-||,19",
            "string with miss and digit, 1-|1-|2-|32|-3|--|--|--|--|--||,12"

    })
    public void shouldGiveScoreWhenAddingBallsInFrames(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }
    @ParameterizedTest
    @CsvSource({
            "String with frames as X, x|x|1/|3-|x|x|x|x|3-|13|| ,160",
            "String with frames as X, x|x|1/|3-|x|x|x|x|3-|3-|| ,159",
            "String with frames as X, x|x|1/|3-|x|x|x|x|3-|x||xx ,186",
            "String with frames as X, x|x|1/|3-|x|x|x|x|3-|3/||x ,176",


    })
    public void shouldGiveTotalScoreWhenAddingBonusToFrames(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }

}




