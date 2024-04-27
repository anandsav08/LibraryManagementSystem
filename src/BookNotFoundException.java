public class BookNotFoundException extends Throwable {
    private String errorMsg;

    public BookNotFoundException(String s) {
        this.errorMsg = s;
    }
}
