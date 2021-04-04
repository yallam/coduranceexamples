package bowling.service;

import bowling.model.Ball;
import bowling.model.BallType;
import bowling.model.FrameType;
import bowling.model.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameEvaluator {
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    private static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String INVALID_BONUS_SIZE = "invalid bonus size";
    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";
    private static final int FRAME_MAX_POINTS = 10;
    public static final int BALL_NO_POINTS = 0;

    public Game getGame(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        assert expression.length() - expression.replaceAll(BONUS_SEPARATOR, "").length() == 2 : INVALID_BONUS_SIZE;
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));
        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(this::validateFrame);

        Game game = new Game();

        game.setFrameBalls(framesList.stream()
                .map(this::getListOfBallsFromFrame)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        List<Ball> eachBallList = game.getFrameBalls();
        Ball lastBallInFrameList = eachBallList.get(eachBallList.size() - 1);
        String bonus = splitWithDoublePipe.length == 1 ? null : splitWithDoublePipe[1];
        validateBonus(splitWithDoublePipe, lastBallInFrameList, bonus);

        if (splitWithDoublePipe.length == 2) {
            bonus = splitWithDoublePipe[1];
            game.setBonusBalls(getListOfBallsFromFrame(bonus));
        }
        return game;
    }

    private void validateBonus(String[] splitWithDoublePipe, Ball lastBallInFrameList, String bonus) {
        if (BallType.STRIKE == lastBallInFrameList.getBallType()) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert bonus.split("").length == 2 : INVALID_BONUS_EXPRESSION;
        } else if (BallType.SPARE == lastBallInFrameList.getBallType()) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert bonus.split("").length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (BallType.MISS == lastBallInFrameList.getBallType()) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (BallType.DIGIT.getBallModel().equals(lastBallInFrameList.getBallType().getBallModel())) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        }
    }

    private List<Ball> getListOfBallsFromFrame(String frame) {
        List<Ball> frameBallsList = new ArrayList<>(Collections.emptyList());
        String[] splittedStringArray = frame.split("");
        for (int index = 0; index < splittedStringArray.length; index++) {
            Ball ball = getBallFromString(splittedStringArray, index);
            frameBallsList.add(ball);
        }
        return frameBallsList;
    }

    private Ball getBallFromString(String[] splittedStringArray, int index) {
        Ball ball = new Ball();
        String ballText = splittedStringArray[index];
        switch (BallType.getBallType(ballText)) {
            case STRIKE:
                ball.setBallType(BallType.STRIKE);
                ball.setBallValue(FRAME_MAX_POINTS);
                break;
            case SPARE:
                ball.setBallType(BallType.SPARE);
                ball.setBallValue(FRAME_MAX_POINTS - Integer.parseInt(splittedStringArray[0]));
                break;
            case MISS:
                ball.setBallType(BallType.MISS);
                ball.setBallValue(BALL_NO_POINTS);
                break;
            case DIGIT:
                ball.setBallType(BallType.DIGIT);
                ball.setBallValue(Integer.parseInt(splittedStringArray[index]));
                break;
        }
        return ball;
    }

    private void validateFrame(String frame) {
        FrameType validFrameType = Arrays.stream(FrameType.values()).filter(frameType -> frame.matches(frameType.getValidRegex()))
                .findFirst().orElseThrow(() -> new AssertionError(INVALID_FRAME_EXPRESSION + frame));
        if (validFrameType == FrameType.TWO_DIGIT_REGEX) {
            int firstDigit = Character.getNumericValue(frame.charAt(0));
            int secondDigit = Character.getNumericValue(frame.charAt(1));
            assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
        }

    }

}
