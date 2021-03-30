import java.util.Arrays;

public class FrameValidation {
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    private static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";

    public void getFrameValidation(String frame) {
        FrameType validFrameType = Arrays.stream(FrameType.values()).filter(frameType -> frame.matches(frameType.getValidRegex()))
                .findFirst().orElseThrow(() -> new AssertionError(INVALID_FRAME_EXPRESSION + frame));
        if (validFrameType == FrameType.TWO_DIGIT_REGEX) {
            int firstDigit = Character.getNumericValue(frame.charAt(0));
            int secondDigit = Character.getNumericValue(frame.charAt(1));
            assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
        }

    }
}
