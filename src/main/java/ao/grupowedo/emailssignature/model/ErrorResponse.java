package ao.grupowedo.emailssignature.model;

public class ErrorResponse {
    private int     status;
    private String  message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int  getStatus() {
        return this.status;
    }

    public String   getMessage() {
        return this.message;
    }
}
