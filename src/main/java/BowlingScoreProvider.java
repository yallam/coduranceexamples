import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingScoreProvider {


    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";
    private static final String DIGIT_MATCHER = "^[1-9]{1}$";


    public int getScore(String expression) {

        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        assert expression.length() - expression.replaceAll(BONUS_SEPARATOR, "").length() == 2 : INVALID_BONUS_EXPRESSION;
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));

        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(frame->new FrameValidation().getFrameValidation(frame));

        List<String> eachBallList = framesList.stream()
                .map(this::splitAndAssignStringToList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        int finalScore = 0;
        for (int i = 0; i < eachBallList.size(); i++) {
            //strike scenario
            if (eachBallList.get(i).equals("x")) {
                finalScore += 10
                        + getValueAfterNextIndex(eachBallList.get(i + 1), eachBallList)
                        + getValueAfterNextIndex(eachBallList.get(i + 2), eachBallList);
            }
            //spare scenario
            else if (eachBallList.get(i).equals("/")) {
                finalScore += 10 - Integer.parseInt(eachBallList.get(i - 1)) + getValueAfterNextIndex(eachBallList.get(i + 1), eachBallList);
            }
            //digit scenario
            else if (eachBallList.get(i).matches(DIGIT_MATCHER)) {
                finalScore += Integer.parseInt(eachBallList.get(i));
            }
            //miss scenario
            else if (eachBallList.get(i).equals("-")) {
                finalScore += 0;
            }
        }
        return finalScore;
    }

    private List<String> splitAndAssignStringToList(String frame) {
        String[] SplittedStringArray = frame.split("");
        return Arrays.asList(SplittedStringArray.clone());
    }

    private int getValueAfterNextIndex(String eachBall, List<String> eachBallList) {
        int valueAAfterNextIndex = 0;
        for (int i = 0; i < eachBallList.size(); i++) {
            if (eachBall.equals("x")) {
                valueAAfterNextIndex = 10;
            } else if (eachBall.equals("-")) {
                valueAAfterNextIndex = 0;
            } else if (eachBall.matches(DIGIT_MATCHER)) {
                valueAAfterNextIndex = Integer.parseInt(eachBall);
            } else if (eachBallList.get(i).equals("/")) {
                valueAAfterNextIndex = 10 - Integer.parseInt(eachBallList.get(i - 1));
            }

        }
        return valueAAfterNextIndex;
    }


}
