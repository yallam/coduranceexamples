public class FrameScoreProvider {
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";

    public int getScoreProvider(String frame) {
        switch (FrameType.getFrameType(frame)) {
            case STRIKE_REGEX:
            case SPARE_REGEX:
                return 10;
            case TWO_MISS_REGEX:
                return 0;
            case TWO_DIGIT_REGEX:
                int firstDigit = Character.getNumericValue(frame.charAt(0));
                int secondDigit = Character.getNumericValue(frame.charAt(1));
                assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
                return firstDigit + secondDigit;
            case DIGIT_AND_MISS_REGEX:
                return Character.getNumericValue(frame.charAt(0));
            case MISS_AND_DIGIT_REGEX:
                return Character.getNumericValue(frame.charAt(1));
        }
        return 0;
    }
}
