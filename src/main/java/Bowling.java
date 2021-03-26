import java.util.Arrays;
import java.util.List;

public class Bowling {

    public static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";
    public static final String INVALID_FRAME_SIZE = "invalid frame size";
    public static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    public static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";


    public int getScore(String expression) {
        String frames = null;
        final Integer[] result = {0};
        String[] splitWithDoublePipe = expression.split("\\|\\|");
        assert expression.length() - expression.replaceAll("\\|\\|", "").length() == 2 : INVALID_BONUS_EXPRESSION;

        frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split("\\|"));

        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(frame -> frameValidationAndCalculateScore(frame, result));

        return result[0];
    }

    private void frameValidationAndCalculateScore(String frame, Integer[] result) {
        int frameScore;
        if (frame.matches(FrameValidations.STRIKE_REGEX.getRegex())) {
            frameScore = getScoreForAllBallsKnockedDown();
        } else if (frame.matches(FrameValidations.SPARE_REGEX.getRegex())) {
            frameScore = getScoreForAllBallsKnockedDown();
        } else if (frame.matches(FrameValidations.TWO_DIGIT_REGEX.getRegex())) {
            frameScore = scoreForTwoDigitNumber(frame);
        } else if (frame.matches(FrameValidations.TWO_MISS_REGEX.getRegex())) {
            frameScore = scoreForTwoMissesInAFrame();
        } else if (frame.matches(FrameValidations.DIGIT_AND_MISS_REGEX.getRegex())) {
           frameScore =  scoreForADigitAndAMiss(frame);
        } else if (frame.matches(FrameValidations.MISS_AND_DIGIT_REGEX.getRegex())) {
            frameScore = scoreForAMissAndADigit(frame);
        } else {
            throw new IllegalArgumentException(INVALID_FRAME_EXPRESSION + frame);
        }

        result[0] +=frameScore;

    }

    private int getScoreForAllBallsKnockedDown() {
        return 10;
    }

    private int scoreForTwoDigitNumber(String frame) {
        int firstDigit = Character.getNumericValue(frame.charAt(0));
        int secondDigit = Character.getNumericValue(frame.charAt(1));
        assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
        return firstDigit + secondDigit;
    }

    private int scoreForTwoMissesInAFrame() {
        return 0;
    }

    private int scoreForADigitAndAMiss(String frame) {
        int digit = Character.getNumericValue(frame.charAt(0));
       return digit;
    }

    private int scoreForAMissAndADigit(String frame) {
        int digit = Character.getNumericValue(frame.charAt(1));
       return digit;
    }

}
