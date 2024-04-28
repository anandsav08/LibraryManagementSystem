import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class LibraryConstants{
    public static final int MEMBER_BOOK_CHECKOUT_LIMIT = 5;
    public static final int MEMBER_BOOK_CHECKOUT_DAYS_LIMIT = 10;
}
class Rack{
    private int floor;
    private int block;
}
enum SubjectCategory{
    HORROR,
    FANTASY,
    FICTION,
    ADVENTURE
}

enum BookItemStatus{
    CHECKED_OUT,
    AVAILABLE,
}
class BookItem{
    private Rack rack;
    private Book book;
    private BookItemStatus bookItemStatus;
    private List<BookReservation> reservationHistory;
    private List<BookReservation> futureReservations;
    private BookReservation currentReservation;

    public void setCurrentReservation(BookReservation bookReservation){
        this.currentReservation = bookReservation;
        this.bookItemStatus = bookItemStatus.CHECKED_OUT;
    }

    public void checkInBookItem(Date checkInDate){
        currentReservation.setCheckInDate(checkInDate);
        reservationHistory.add(currentReservation);
        currentReservation = null;
        bookItemStatus =BookItemStatus.AVAILABLE;
    }

    public BookReservation getCurrentReservation() {
        return this.currentReservation;
    }
    public void setBookItemStatus(BookItemStatus status){this.bookItemStatus = status;}
}
class Book{
    private String ISBN;
    private Author author;
    private String title;
    private SubjectCategory subjectCategory;
    private Date publicationDate;
    List<BookItem> bookItems;
}

class Address{
    private String apt;
    private String street;
    private String city;
    private String state;
    private int zipcode;
}
class PersonInfo{
    private String firstName;
    private String lastName;
    private Address address;
    private Date dob;
}

class Author{
    private PersonInfo personInfo;
}

enum MemberStatus{
    ACTIVE,
    INACTIVE
}

class SearchParams{
    String title;
    Author author;
    Date publicationDate;
    SubjectCategory category;
    SearchType searchType;

    public Author getAuthor(){return this.author;}
    public String getTitle(){return this.title;}
    public Date getPublicationDate(){return this.publicationDate;}
    public SubjectCategory getCategory(){return this.category;}
}
enum SearchType{
    AUTHOR,
    TITLE,
    CATEGORY,
    PUBLICATION_DATE
}
class Member{
    private PersonInfo personInfo;
    private MemberStatus memberStatus;
    private Date registrationDate;
    private LibraryManagementSystem managementSystem;
    private List<BookReservation> bookReservationsHistory;
    private List<BookReservation> currentCheckedoutBookReservations;
    private List<BookReservation> futureBookReservations;

    // methods
    // searchbook, checkout book, reserve book, register, unregister
    public List<Book> searchBook(SearchParams searchParams){
        SearchService searchService = null;
        switch (searchParams.searchType){
            case AUTHOR:
                searchService = new SearchByAuthor(searchParams.getAuthor());
                break;
            case TITLE:
                searchService = new SearchByTitle(searchParams.getTitle());
                break;
            case CATEGORY:
                searchService = new SearchByTitle(searchParams.getTitle());
                break;
            case PUBLICATION_DATE:
                searchService = new SearchByTitle(searchParams.getTitle());
                break;
        }
        return managementSystem.searchBook(searchService);
    }
    public boolean checkoutBookItem(BookItem bookItem, Duration numDays){
        BookReservation reservation = managementSystem.checkoutBookItem(this,bookItem,numDays);
        currentCheckedoutBookReservations.add(reservation);
        return true;
    }

    public void checkInBookItem(BookItem bookItem){
        managementSystem.checkInBookItem(bookItem);
    }
    public boolean reserveBookItem(BookItem bookItem, Date startDate, Duration numDays){
        if(numDays.toDays() > 10) return false;
        managementSystem.reserveBookItem(bookItem,startDate,numDays);
        return true;
    }
    public boolean registerMember(LibraryManagementSystem managementSystem){
        return managementSystem.registerMember(this);
    }
    public boolean unregisterMember(LibraryManagementSystem managementSystem){
        return managementSystem.unregisterMember(this);
    }
    public void setMemberStatus(MemberStatus status){this.memberStatus = status;}

    public boolean canCheckOutBook() {
        if(memberStatus == MemberStatus.INACTIVE) return false;
        if(currentCheckedoutBookReservations.size() == LibraryConstants.MEMBER_BOOK_CHECKOUT_LIMIT) return false;
        return true;
    }

    public void payDues(){

    }
}
class Account{

}

class Librarian{
    private PersonInfo personInfo;
    private Date dateOfJoining;
    private SalaryInfo salaryInfo;
    private Account account;
    private LibraryManagementSystem managementSystem;

    public void setLibraryManagementSystem(LibraryManagementSystem managementSystem){this.managementSystem = managementSystem;}
    public void addBook(Book book){}
    public void addBookItem(BookItem bookItem){}
}

class LibraryCard{
    private long barcode;
    Member member;

    public Member getMember(){return this.member;}
}

// Management classes:
class Worker{
    // cron job
    public void
}
class LibraryManagementSystem{
    private MemberCatalog memberCatalog;
    private BookCatalog bookCatalog;
    private List<Librarian> librarians;
    private SearchService searchService;
    private NotificationService notificationService;
    private ReservationService reservationService;
    private PaymentService paymentService;
    private Worker pollingLoop;

    public void addLibrarian(Librarian librarian){}

    public boolean registerMember(Member member) {
        memberCatalog.addMember(member);
        return true;
    }
    public boolean unregisterMember(Member member){
        memberCatalog.removeMember(member);
    }

    public List<Book> searchBook(SearchService searchService){
        return searchService.search(bookCatalog);
    }

    public BookReservation checkoutBookItem(Member member, BookItem bookItem,Duration duration) {
        return reservationService.checkoutBookItem(member,bookItem,duration);
    }

    public void checkInBookItem(BookItem bookItem) {
        reservationService.checkInBookItem(bookItem, new Date());
    }

    public void reserveBookItem(BookItem bookItem, Date startDate, Duration numDays) {

    }

    public void checkAndSendDueDateNotification(){
        List<BookReservation> dueDateOverReservations = reservationService.getReservationsWithBookDueDateOver();
        dueDateOverReservations.stream()
                .forEach(reservation -> {
                    notificationService.notify(reservation.getMember());
                });
    }
}
interface SearchService{
    List<Book> search(BookCatalog bookCatalog);
}

class SearchByTitle implements SearchService{
    private String title;
    public SearchByTitle(String title){this.title = title;}
    @Override
    public List<Book> search(BookCatalog bookCatalog){

    }
}

class SearchByAuthor implements SearchService{
    private Author author;

    public SearchByAuthor(Author author){this.author = author;}

    @Override
    public List<Book> search(BookCatalog bookCatalog){

    }
}

class SearchByCategory implements SearchService{
    private SubjectCategory subjectCategory;
    @Override
    public List<Book> search(BookCatalog bookCatalog){

    }
}

interface NotificationService{
    void notify(Member member);
}

class EmailNotificationService implements NotificationService{
    @Override
    public void notify(Member member){

    }
}

class SMSNotificationService implements NotificationService{
    @Override
    public void notify(Member member){

    }
}

class CardDetails{
    private String cardNumber;
    private Date issueDate;
    private Date expiryDate;
    private int pin;
}

interface PaymentService{
    void pay();
}

class NetPaymentService implements PaymentService{
    @Override
    public void pay(){

    }
}

enum CardType{
    DEBIT,
    CREDIT,
}

class Card{
    CardDetails cardDetails;
    CardType cardType;
}
class CardPaymentService implements PaymentService{
    private Card card;
    @Override
    public void pay(){

    }
}


class MemberCatalog{
    private List<Member> members;

    public boolean addMember(Member member){members.add(member); return true;}
    public void removeMember(Member member){member.setMemberStatus(MemberStatus.INACTIVE);}
}

class BookCatalog{
    private List<Book> books;

    public void addBook(Book book){books.add(book);}
    public void removeBook(Book book){}
}

class ReservationService{
    private List<BookReservation> currentBookReservations;

    public BookReservation checkoutBookItem(Member member, BookItem bookItem, Duration duration) {
        if(!member.canCheckOutBook() || duration.toDays() > 10) {
            // throw exception
        }
        BookReservation bookReservation = new BookReservation(bookItem,member,new Date(),duration);
        bookItem.setCurrentReservation(bookReservation);
        bookItem.setBookItemStatus(BookItemStatus.CHECKED_OUT);
        currentBookReservations.add(bookReservation);
        return bookReservation;
    }

    public void checkInBookItem(BookItem bookItem,Date checkInDate) {
        BookReservation reservation = bookItem.getCurrentReservation();
        reservation.setCheckInDate(checkInDate);
        reservation.calculateFine();
        bookItem.setBookItemStatus(BookItemStatus.AVAILABLE);
    }

    public List<BookReservation> getReservationsWithBookDueDateOver() {
        return List.of();
    }
}

public class Main {
    public static void main(String[] args){

    }
}
