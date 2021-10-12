package cinema;

public class CustomExceptionResponse {

    private final String error;

    public CustomExceptionResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

}
