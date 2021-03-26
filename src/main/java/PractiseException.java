public class PractiseException {
    public String practiseException(int input) {
        assert input > 0 : "invalid input";
        String result = null;
        if (input > 50) {
            result = "bindu";
        } else if (input < 50) {
            result = "vamsi";
        }
        return result;
    }
}
