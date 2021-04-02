import java.util.Arrays;
import java.util.List;

public class ExpressionAndFrameValidation {
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    private static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String INVALID_BONUS_SIZE = "invalid bonus size";
    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";
    private static final String DIGIT_MATCHER = "^[1-9]{1}$";


    public void validateExpression(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        assert expression.length() - expression.replaceAll(BONUS_SEPARATOR, "").length() == 2 : INVALID_BONUS_SIZE;
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));
        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(this::validateFrame);
        List<String> eachBallList = new Game().getBalls(expression);
        String lastBallInFrameList = eachBallList.get(eachBallList.size() - 1);
        String bonus = splitWithDoublePipe.length == 1 ? null : splitWithDoublePipe[1];
        if (BallType.STRIKE.getBallType().equals(lastBallInFrameList)) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert bonus.split("").length == 2 : INVALID_BONUS_EXPRESSION;
        } else if (BallType.SPARE.getBallType().equals(lastBallInFrameList)) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert bonus.split("").length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (BallType.MISS.getBallType().equals(lastBallInFrameList)) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (lastBallInFrameList.matches(DIGIT_MATCHER)) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        }
    }

    private void validateFrame(String frame) {
        FrameType validFrameType = Arrays.stream(FrameType.values()).filter(frameType -> frame.matches(frameType.getValidRegex()))
                .findFirst().orElseThrow(() -> new AssertionError(INVALID_FRAME_EXPRESSION + frame));
        if (validFrameType == FrameType.TWO_DIGIT_REGEX) {
            int firstDigit = Character.getNumericValue(frame.charAt(0));
            int secondDigit = Character.getNumericValue(frame.charAt(1));
            assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
        }

    }

}
