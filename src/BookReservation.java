import java.util.Date;

public class BookReservation {
    BookItem bookItem;
    Member member;
    Date checkoutDate;
    Date checkInDate;
    Fine fine;

    public BookReservation(BookItem item,Member member,Date checkOutDate){
        this.bookItem = item;
        this.member = member;
        this.checkoutDate = checkOutDate;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Fine getFine() {
        return fine;
    }

    public void setFine(Fine fine) {
        this.fine = fine;
    }
}