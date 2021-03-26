import java.util.Arrays;

public enum FrameType {
    STRIKE_REGEX("^X{1}$"),
    SPARE_REGEX("^[0-9]{1}\\/{1}$"),
    TWO_DIGIT_REGEX("^[0-9]{1}[0-9]{1}$"),
    TWO_MISS_REGEX("^[-]{1}[-]{1}$"),
    DIGIT_AND_MISS_REGEX("^[0-9]{1}[-]{1}$"),
    MISS_AND_DIGIT_REGEX("^[-]{1}[0-9]{1}$");
    private static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";
    private String validRegex;

    FrameType(String regx) {
        this.validRegex = regx;
    }

    public String getValidRegex() {
        return validRegex;
    }

    public static FrameType getFrameType(String frame) {
        return Arrays.stream(values()).filter(frameType -> frame.matches(frameType.getValidRegex()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FRAME_EXPRESSION + frame));
    }
}
