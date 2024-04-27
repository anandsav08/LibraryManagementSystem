public class BookItemNotAvailableException extends Throwable {
    private String msg;
    public BookItemNotAvailableException(String s) {
        this.msg = s;
    }
}
