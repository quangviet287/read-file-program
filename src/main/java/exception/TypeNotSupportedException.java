package exception;

public class TypeNotSupportedException extends Exception {

    private String fileType;

    public TypeNotSupportedException(String fileType) {
        super("File not supported:" + fileType);
        this.fileType = fileType;
    }
}
