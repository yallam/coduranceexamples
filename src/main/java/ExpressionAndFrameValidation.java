import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionAndFrameValidation {
    private static final String INVALID_DIGITS_IN_FRAME = "invalid digits in given frame:";
    private static final String INVALID_FRAME_EXPRESSION = "invalid frame expression frame:";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String INVALID_BONUS_SIZE = "invalid bonus size";
    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";
    private static final String DIGIT_MATCHER = "^[1-9]{1}$";


    public void getExpressionValidation(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        assert expression.length() - expression.replaceAll(BONUS_SEPARATOR, "").length() == 2 : INVALID_BONUS_SIZE;
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));
        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(this::getFrameValidation);
        List<String> eachBallList = getEachBallList(expression);
        if (eachBallList.get(eachBallList.size() - 1).equals("x")) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert splitWithDoublePipe[1].split("").length == 2 : INVALID_BONUS_EXPRESSION;
        } else if (eachBallList.get(eachBallList.size() - 1).equals("/")) {
            assert splitWithDoublePipe.length == 2 : INVALID_BONUS_EXPRESSION;
            assert splitWithDoublePipe[1].split("").length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (eachBallList.get(eachBallList.size() - 1).equals("-")) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        } else if (eachBallList.get(eachBallList.size() - 1).matches(DIGIT_MATCHER)) {
            assert splitWithDoublePipe.length == 1 : INVALID_BONUS_EXPRESSION;
        }
    }

    public List<String> getEachBallList(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));
        assert framesList.size() == 10 : INVALID_FRAME_SIZE;
        framesList.forEach(this::getFrameValidation);
        List<String> eachBallList = framesList.stream()
                .map(this::splitAndAssignStringToList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return eachBallList;

    }

    public List<String> getEachBonusBallList(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        String bonus="";
        if(splitWithDoublePipe.length==2){
        bonus = splitWithDoublePipe[1];
        }
        return splitAndAssignStringToList(bonus);

    }

    private void getFrameValidation(String frame) {
        FrameType validFrameType = Arrays.stream(FrameType.values()).filter(frameType -> frame.matches(frameType.getValidRegex()))
                .findFirst().orElseThrow(() -> new AssertionError(INVALID_FRAME_EXPRESSION + frame));
        if (validFrameType == FrameType.TWO_DIGIT_REGEX) {
            int firstDigit = Character.getNumericValue(frame.charAt(0));
            int secondDigit = Character.getNumericValue(frame.charAt(1));
            assert firstDigit + secondDigit <= 10 : INVALID_DIGITS_IN_FRAME + frame;
        }

    }

    private List<String> splitAndAssignStringToList(String frame) {
        String[] SplittedStringArray = frame.split("");
        return Arrays.asList(SplittedStringArray.clone());
    }

}
