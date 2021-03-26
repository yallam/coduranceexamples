import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingScoreProviderTest {
    BowlingScoreProvider bowlingScoreProvider = new BowlingScoreProvider();

    @ParameterizedTest
    @ValueSource(strings = {"w", "ere2"})
    public void shouldThrowValidationErrorWhenBonusSeparatorIsNotGiven(String expression) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> bowlingScoreProvider.getScore(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid bonus expression"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"w||we2||233||@£@||@£23", "23||22||"})
    public void shouldThrowValidationErrorWhenMoreThanOneBonusSeparatorIsGiven(String expression) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> bowlingScoreProvider.getScore(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid bonus expression"));
    }

    @ParameterizedTest
    @CsvSource({
            "String split by pipeline and length 10, 23|22|32|23|23|23|23|23|23|21|| ,47"
    })
    public void shouldHave10FramesInGivenString(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }

    @ParameterizedTest

    @CsvSource({
            "String with frame size 4, w|we2|233|77||23 ,invalid frame size",
            "string with frame size 2, 23|22||,invalid frame size"
    })
    public void shouldThrowValidationErrorWhenFramesAreNot10(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> bowlingScoreProvider.getScore(expression),
                "Expected getScore() to throw, but it didn't"
        );

        assertEquals(expected, thrown.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "String with frames as X, X|X|X|X|X|X|X|X|X|X|| ,100",
            "string with spare in a frame,1/|2/|3/|4/|5/|6/|7/|8/|9/|1/||,100",
            "string with two misses,--|--|--|--|--|--|--|--|--|10||,1",
            "string with two digits,11|22|33|44|55|12|12|12|12|10||,43",
            "string with digit and miss,1-|1-|1-|2-|2-|2-|3-|3-|3-|1-||,19",
            "string with miss and digit, -1|-1|-1|-1|-2|-3|-4|-1|-1|-2||,17"
    })
    public void shouldGiveScoreWhenAddingAllTheFrames(String senario, String expression, int expected) {
        assertEquals(expected, bowlingScoreProvider.getScore(expression));
    }

    @ParameterizedTest

    @CsvSource({
            "String with invalid frame as X32, X|X|X32|X|X|X|X|X|X|X|| ,invalid frame expression frame:X32",
            "string with invalid frame as 3/21, 1/|2/|3/21|4/|5/|6/|7/|8/|9/|1/||,invalid frame expression frame:3/21"
    })
    public void shouldThrowValidationErrorWhenFrameFormatIsNotCorrect(String senario, String expression, String expected) {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> bowlingScoreProvider.getScore(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertEquals(expected, thrown.getMessage());
    }
    @ParameterizedTest

    @CsvSource({
            "String with invalid frame as 99, X|X|99|X|X|X|X|X|X|X|| ,invalid digits in given frame:99",
            "string with invalid frame as 88, 1/|2/|88|4/|5/|6/|7/|8/|9/|1/||,invalid digits in given frame:88"
    })
    public void shouldThrowValidationErrorWhenSumOfDigitsInFrameAreGreaterThan10(String senario, String expression, String expected) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> bowlingScoreProvider.getScore(expression),
                "Expected getScore() to throw, but it didn't"
        );
        assertEquals(expected, thrown.getMessage());
    }

}




