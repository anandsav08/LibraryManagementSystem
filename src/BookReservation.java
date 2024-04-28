import java.time.Duration;
import java.util.Date;

public class BookReservation {
    BookItem bookItem;
    Member member;
    Date checkoutDate;
    Duration numCheckoutDays;
    Date checkInDate;
    Fine fine;

    public BookReservation(BookItem item,Member member,Date checkOutDate, Duration duration){
        this.bookItem = item;
        this.member = member;
        this.checkoutDate = checkOutDate;
        this.numCheckoutDays = duration;
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

    public void calculateFine() {

    }
}

class Fine{
    private double fineAmount;
    //I think its better to have an interface that calculates the Fines : 
    public double calculateFine(BookReservation bookReservation) {return 0.0};
    public double calculateFine(){return 0.0;}
}
