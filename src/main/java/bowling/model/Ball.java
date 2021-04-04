package bowling.model;

public class Ball {
    private int ballValue;
    private BallType ballType;

    public int getBallValue() {
        return ballValue;
    }

    public void setBallValue(int ballValue) {
        this.ballValue = ballValue;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }
}
