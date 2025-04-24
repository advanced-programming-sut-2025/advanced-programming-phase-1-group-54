package model;

public record Result(int code, String message) {
    public Result(boolean success, String message) {
        this(success ? 0 : -1, message);
    }

    public boolean success() {
        return code >= 0;
    }
}
