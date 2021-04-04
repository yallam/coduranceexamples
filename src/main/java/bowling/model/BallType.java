package bowling.model;

import java.util.Arrays;

public enum BallType {

    STRIKE("x"),
    SPARE("/"),
    MISS("-"),
    DIGIT("^[1-9]{1}$");
    private static final String INVALID_Ball_EXPRESSION = "invalid ball expression";
    private String ballModel;

    BallType(String ballModel) {
        this.ballModel = ballModel;
    }

    public String getBallModel() {
        return ballModel;
    }

    public static BallType getBallType(String ball) {

        return Arrays.stream(BallType.values()).filter(ballType -> ball.matches(ballType.getBallModel()))
                .findFirst().orElseThrow(() -> new AssertionError(INVALID_Ball_EXPRESSION));
    }
}
