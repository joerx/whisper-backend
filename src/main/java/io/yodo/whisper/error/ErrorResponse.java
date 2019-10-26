package io.yodo.whisper.error;

@SuppressWarnings("unused")
public class ErrorResponse {

    private String message;

    private String type;

    public ErrorResponse(Exception e) {
        this.message = e.getMessage();
        this.type = e.getClass().getName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
