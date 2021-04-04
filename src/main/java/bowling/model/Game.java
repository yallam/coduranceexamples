package bowling.model;

import java.util.List;

public class Game {

    private List<Ball> frameBalls;
    private List<Ball> bonusBalls;
    public List<Ball> getFrameBalls() {
        return frameBalls;
    }

    public void setFrameBalls(List<Ball> frameBalls) {
        this.frameBalls = frameBalls;
    }

    public List<Ball> getBonusBalls() {
        return bonusBalls;
    }

    public void setBonusBalls(List<Ball> bonusBalls) {
        this.bonusBalls = bonusBalls;
    }

}
