package accounts.model;

public enum Direction {
    IN("IN"),
    OUT("OUT");
    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
