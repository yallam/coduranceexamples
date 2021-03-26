import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PractiseExceptionTest {
    PractiseException practiseException = new PractiseException();
    @ParameterizedTest
    @CsvSource({
            "When Input greater than 50, 57,bindu",
            "When Input greater than 60, 67 ,bindu",
            "When Input greater than 90,100 ,bindu",
            "When Input less than 50, 46,vamsi",
            "When Input less than 20, 11 ,vamsi"})

    public void practiseExceptionWhenInputGreaterthan50AndInputIsLessThan50(String senario, int input, String expected) {
        assertEquals(expected, practiseException.practiseException(input));
    }

    @Test
    public void throwsExceptionWhenInputIsEqualTo0() {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> practiseException.practiseException(0),
                "Expected practiseException() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid input"));
    }

    @Test
    public void throwsExceptionWhenInputIsLessThan0() {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> practiseException.practiseException(-21),
                "Expected practiseException() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid input"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void parameterizedIntExceptionTest(int input) {
        AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> {practiseException.practiseException(input);},
                "Expected practiseException() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("invalid input"));
    }
}
