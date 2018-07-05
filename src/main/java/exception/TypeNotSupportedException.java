package exception;

public class TypeNotSupportedException extends Exception {

    private String errMessage;

    public TypeNotSupportedException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

}
