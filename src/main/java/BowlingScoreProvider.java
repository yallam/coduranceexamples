import java.util.Arrays;
import java.util.List;

public class BowlingScoreProvider {


    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";


    public int getScore(String expression) {

        final Integer[] result = {0};
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        assert expression.length() - expression.replaceAll(BONUS_SEPARATOR, "").length() == 2 : INVALID_BONUS_EXPRESSION;
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));

        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(frame -> frameValidationAndCalculateScore(frame, result));

        return result[0];
    }

    private void frameValidationAndCalculateScore(String frame, Integer[] result) {
        result[0] += new FrameScoreProvider().getScoreProvider(frame);

    }

}
