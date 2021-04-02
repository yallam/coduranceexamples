import java.util.List;

public class BowlingScoreProvider {

    private static final String DIGIT_MATCHER = "^[1-9]{1}$";
    public static final int FRAME_MAX_POINTS = 10;

    public int getScore(String expression) {
        ExpressionAndFrameValidation expressionAndFrameValidation = new ExpressionAndFrameValidation();
        expressionAndFrameValidation.validateExpression(expression);
        Game game = new Game();
        List<String> eachBallList = game.getBalls(expression);
        List<String> eachBallInBonus = game.getBonusBalls(expression);
        int finalScore = 0;
        for (int ballIndex = 0; ballIndex < eachBallList.size(); ballIndex++) {
            if (BallType.STRIKE.getBallType().equals(eachBallList.get(ballIndex))) {//strike scenario
                finalScore += getStrikeFinalScore(eachBallList, eachBallInBonus, ballIndex);
            } else if (BallType.SPARE.getBallType().equals(eachBallList.get(ballIndex))) {//spare scenario
                finalScore += getSpareFinalScore(eachBallList, eachBallInBonus, ballIndex);
            } else if (eachBallList.get(ballIndex).matches(DIGIT_MATCHER)) {//digit scenario
                finalScore += Integer.parseInt(eachBallList.get(ballIndex));
            } else if (BallType.MISS.getBallType().equals(eachBallList.get(ballIndex))) {//miss scenario
                finalScore += 0;
            }
        }
        return finalScore;
    }

    private int getSpareFinalScore(List<String> eachBallList, List<String> eachBallInBonus, int ballIndex) {
        int nextBallIndex;
        List<String> ballsList;
        if (ballIndex == eachBallList.size() - 1) {

            nextBallIndex = 0;
            ballsList = eachBallInBonus;
        } else {

            nextBallIndex = ballIndex + 1;
            ballsList = eachBallList;
        }
        return FRAME_MAX_POINTS - Integer.parseInt(eachBallList.get(ballIndex - 1)) + getValueAfterNextIndex(nextBallIndex, ballsList);
    }

    private int getStrikeFinalScore(List<String> eachBallList, List<String> eachBallInBonus, int ballIndex) {
        int nextBallIndex;
        List<String> nextBallList;

        int secondNextBallIndex;
        List<String> secondNextBallList;

        if (ballIndex == eachBallList.size() - 1) {
            nextBallIndex = 0;
            nextBallList = eachBallInBonus;
            secondNextBallIndex = 1;
            secondNextBallList = eachBallInBonus;
        } else if (ballIndex == eachBallList.size() - 2 && BallType.STRIKE.getBallType().equalsIgnoreCase(eachBallList.get(ballIndex + 1))) {
            nextBallIndex = ballIndex + 1;
            nextBallList = eachBallList;
            secondNextBallIndex = 0;
            secondNextBallList = eachBallInBonus;
        } else {
            nextBallIndex = ballIndex + 1;
            nextBallList = eachBallList;
            secondNextBallIndex = ballIndex + 2;
            secondNextBallList = eachBallList;
        }
        return FRAME_MAX_POINTS + getValueAfterNextIndex(nextBallIndex, nextBallList)
                + getValueAfterNextIndex(secondNextBallIndex, secondNextBallList);
    }

    private int getValueAfterNextIndex(int ballIndex, List<String> ballsList) {
        String ball = ballsList.get(ballIndex);
        int valueAfterNextIndex = 0;
        if (BallType.STRIKE.getBallType().equals(ball)) {
            valueAfterNextIndex = FRAME_MAX_POINTS;
        } else if (BallType.MISS.getBallType().equals(ball)) {
            valueAfterNextIndex = 0;
        } else if (ball.matches(DIGIT_MATCHER)) {
            valueAfterNextIndex = Integer.parseInt(ball);
        } else if (BallType.SPARE.getBallType().equals(ball)) {
            valueAfterNextIndex = FRAME_MAX_POINTS - Integer.parseInt(ballsList.get(ballIndex - 1));
        }

        return valueAfterNextIndex;
    }

}
