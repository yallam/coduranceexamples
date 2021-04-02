import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private static final String BONUS_SEPARATOR = "\\|\\|";
    private static final String FRAME_SEPARATOR = "\\|";


    public List<String> getBalls(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        String frames = splitWithDoublePipe[0];
        List<String> framesList = Arrays.asList(frames.split(FRAME_SEPARATOR));

        return framesList.stream()
                .map(this::splitAndAssignStringToList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    public List<String> getBonusBalls(String expression) {
        String[] splitWithDoublePipe = expression.split(BONUS_SEPARATOR);
        String bonus = "";
        if (splitWithDoublePipe.length == 2) {
            bonus = splitWithDoublePipe[1];
        }
        return splitAndAssignStringToList(bonus);
    }

    private List<String> splitAndAssignStringToList(String frame) {
        String[] SplittedStringArray = frame.split("");
        return Arrays.asList(SplittedStringArray.clone());
    }

}
