import java.util.List;

public class BowlingScoreProvider {


    private static final String INVALID_FRAME_SIZE = "invalid frame size";
    private static final String INVALID_BONUS_EXPRESSION = "invalid bonus expression";
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";
    private static final String DIGIT_MATCHER = "^[1-9]{1}$";


    public int getScore(String expression) {
        ExpressionAndFrameValidation expressionAndFrameValidation = new ExpressionAndFrameValidation();
        expressionAndFrameValidation.getExpressionValidation(expression);
        List<String> eachBallList = expressionAndFrameValidation.getEachBallList(expression);
        List<String> eachBallInBonus = expressionAndFrameValidation.getEachBonusBallList(expression);
        int finalScore = 0;
        for (int i = 0; i < eachBallList.size(); i++) {
            //strike scenario
            if (eachBallList.get(i).equals("x")) {
                if (i == eachBallList.size() - 1) {

                    finalScore += 10 + getValueAfterNextIndex(eachBallInBonus.get(0), 0, eachBallInBonus)
                            + getValueAfterNextIndex(eachBallInBonus.get(1), 1, eachBallInBonus);
                } else if (i == eachBallList.size() - 2 && eachBallList.get(i + 1).equals("x")) {
                    finalScore += 10 + getValueAfterNextIndex(eachBallList.get(i + 1), i + 1, eachBallList) + getValueAfterNextIndex(eachBallInBonus.get(0), 0, eachBallInBonus);
                } else {
                    finalScore += 10
                            + getValueAfterNextIndex(eachBallList.get(i + 1), i + 1, eachBallList)
                            + getValueAfterNextIndex(eachBallList.get(i + 2), i + 2, eachBallList);
                }
            }
            //spare scenario
            else if (eachBallList.get(i).equals("/")) {
                if (i == eachBallList.size() - 1) {
                    finalScore += 10 - Integer.parseInt(eachBallList.get(i - 1)) + getValueAfterNextIndex(eachBallInBonus.get(0), 0, eachBallInBonus);
                } else {
                    finalScore += 10 - Integer.parseInt(eachBallList.get(i - 1)) + getValueAfterNextIndex(eachBallList.get(i + 1), i + 1, eachBallList);
                }
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

    private int getValueAfterNextIndex(String eachBall, int index, List<String> eachBallList) {
        int valueAAfterNextIndex = 0;
        for (int i = 0; i < eachBallList.size(); i++) {
            if (eachBall.equals("x")) {
                valueAAfterNextIndex = 10;
            } else if (eachBall.equals("-")) {
                valueAAfterNextIndex = 0;
            } else if (eachBall.matches(DIGIT_MATCHER)) {
                valueAAfterNextIndex = Integer.parseInt(eachBall);
            } else if (eachBallList.get(i).equals("/")) {
                valueAAfterNextIndex = 10 - Integer.parseInt(eachBallList.get(index - 1));
            }

        }
        return valueAAfterNextIndex;
    }


}
