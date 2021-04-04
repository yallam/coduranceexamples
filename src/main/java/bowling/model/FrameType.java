package bowling.model;

import java.util.Arrays;

public enum FrameType {
    STRIKE_REGEX("^x{1}$"),
    SPARE_REGEX("^[0-9]{1}\\/{1}$"),
    TWO_DIGIT_REGEX("^[0-9]{1}[0-9]{1}$"),
    TWO_MISS_REGEX("^[-]{1}[-]{1}$"),
    DIGIT_AND_MISS_REGEX("^[0-9]{1}[-]{1}$"),
    MISS_AND_DIGIT_REGEX("^[-]{1}[0-9]{1}$");

    private String validRegex;

    FrameType(String regx) {
        this.validRegex = regx;
    }

    public String getValidRegex() {
        return validRegex;
    }

}
