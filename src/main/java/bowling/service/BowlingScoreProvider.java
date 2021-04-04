package bowling.service;

import bowling.model.Ball;
import bowling.model.BallType;
import bowling.model.Game;

import java.util.List;

public class BowlingScoreProvider {
    private GameEvaluator gameEvaluator = new GameEvaluator();

    public int getScore(String expression) {
        Game game = gameEvaluator.getGame(expression);
        List<Ball> eachBallList = game.getFrameBalls();
        List<Ball> eachBallInBonus = game.getBonusBalls();
        int finalScore = 0;

        for (int ballIndex = 0; ballIndex < eachBallList.size(); ballIndex++) {
            BallType ballType = eachBallList.get(ballIndex).getBallType();
            if (ballType == BallType.STRIKE) {
                finalScore += getStrikeFinalScore(eachBallList, eachBallInBonus, ballIndex);
            } else if (ballType == BallType.SPARE) {
                finalScore += getSpareFinalScore(eachBallList, eachBallInBonus, ballIndex);
            } else if (ballType == BallType.MISS || ballType == BallType.DIGIT) {
                finalScore += eachBallList.get(ballIndex).getBallValue();
            }
        }
        return finalScore;
    }

    private int getSpareFinalScore(List<Ball> eachBallList, List<Ball> eachBallInBonus, int ballIndex) {
        int spareScore;
        if (ballIndex == eachBallList.size() - 1) {
            spareScore = eachBallList.get(ballIndex).getBallValue() + eachBallInBonus.get(0).getBallValue();
        } else {
            spareScore = eachBallList.get(ballIndex).getBallValue() + eachBallList.get(ballIndex + 1).getBallValue();
        }
        return spareScore;
    }

    private int getStrikeFinalScore(List<Ball> eachBallList, List<Ball> eachBallInBonus, int ballIndex) {
        int strikeScore;
        if (ballIndex == eachBallList.size() - 1) {
            strikeScore = eachBallList.get(ballIndex).getBallValue()
                    + eachBallInBonus.get(0).getBallValue()
                    + eachBallInBonus.get(1).getBallValue();
        } else if (ballIndex == eachBallList.size() - 2 &&
                BallType.STRIKE == eachBallList.get(ballIndex + 1).getBallType()) {
            strikeScore = eachBallList.get(ballIndex).getBallValue()
                    + eachBallList.get(ballIndex + 1).getBallValue()
                    + eachBallInBonus.get(0).getBallValue();
        } else {
            strikeScore = eachBallList.get(ballIndex).getBallValue()
                    + eachBallList.get(ballIndex + 1).getBallValue()
                    + eachBallList.get(ballIndex + 2).getBallValue();
        }
        return strikeScore;
    }

}
