
public enum FrameValidations {
     STRIKE_REGEX("^X{1}$"),
     SPARE_REGEX("^[0-9]{1}\\/{1}$"),
     TWO_DIGIT_REGEX("^[0-9]{1}[0-9]{1}$"),
     TWO_MISS_REGEX("^[-]{1}[-]{1}$"),
     DIGIT_AND_MISS_REGEX ("^[0-9]{1}[-]{1}$"),
     MISS_AND_DIGIT_REGEX("^[-]{1}[0-9]{1}$");

    private String regex;

    FrameValidations(String regx) {
        this.regex = regx;
    }

    public String getRegex() {
        return regex;
    }
}
