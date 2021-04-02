import java.util.Arrays;
import java.util.Optional;

public enum BallType {

    STRIKE("x"),
    SPARE("/"),
    MISS("-");
    private String ballType;

    BallType(String ballType) {
        this.ballType = ballType;
    }

    public String getBallType() {
        return ballType;
    }

}
