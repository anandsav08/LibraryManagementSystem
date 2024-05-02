import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/*

Any library member should be able to search books by their title, author, subject category as well by the publication date.
Library members should be able to check-out and reserve any copy.
The system should be able to retrieve information like who took a particular book or what are the books checked-out by a specific library member.
The system should be able to collect fines for books returned after the due date.
Members should be able to reserve books that are not currently available.
The system should be able to send notifications whenever the reserved books become available, as well as when the book is not returned within the due date
*/
class LibraryConstants{
    //I think these constants should be part of member class
    public static final int MEMBER_BOOK_CHECKOUT_LIMIT = 5;
    public static final int MEMBER_BOOK_CHECKOUT_DAYS_LIMIT = 10;
}
//Rack is good
class Rack{
    private int floor;
    private int block;
}
//SubjectCategory is good.Think about String vs Enum ? Why you used Enum if String could have been used ?
enum SubjectCategory{
    HORROR,
    FANTASY,
    FICTION,
    ADVENTURE
}

enum BookItemStatus{
    //Can have more Statuses
    //Just write it out so you dont have to think in the interview.
    CHECKED_OUT,
    AVAILABLE,
    LOST,
}
//BookItem seems fine
class BookItem{
    private Rack rack;
    private Book book;
    private BookItemStatus bookItemStatus;
    private List<BookReservation> reservationHistory;
    private List<BookReservation> futureReservations;
    private BookReservation currentReservation;
    //I was expecting a validation method which verifies if this bookItem can be reserved
    public void setCurrentReservation(BookReservation bookReservation){
        this.currentReservation = bookReservation;
        this.bookItemStatus = bookItemStatus.CHECKED_OUT;
    }
    //When a Book is returned, the Fine might be calculated ? 
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
    //A book can have multiple Authors
    private Author author;
    private String title;
    //A book can have multiplt Categories
    //Use List<SubjectCatergory> 
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
    //Author's address wont be known. So, I think its better to use 
    //author as string
    private PersonInfo personInfo;
}

enum MemberStatus{
    ACTIVE,
    INACTIVE
}

//This if fine if you want to support search for multiple fields
//THIS IS RESPONSIBLE JUST FOR CREATING PARAMS NOT FOR SEARCH: 
class SearchParams implements ISearchParams{
    String title;
    Author author;
    Date publicationDate;
    SubjectCategory category;
    SearchType searchType;
    String fifthParam;
    
    List<Book> listOfBooks;    
}

interface ISearchParams{
    public String getAuthor();
    public Date getPublicationDate();
    public SubjectCategory getSubjectCategory();
    public String getTitle();
    public String getFifthParam();
}

interface ISearchService{
    List<Book> searchBooks(BookCatalog bookCatalog, ISearchParams searchParams);
}

class DefaultSearchService implements ISearchService{
    List<Book> searchBooks(BookCatalog bookCatalog, SearchParams searchParams){
        return new LinkedList<>();
    }
}

//You wont be requiring this enum.
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
    //This shouldn't be here
    //The Member Class is having an additional responsibility. 
    //One Class should have one resposnsibility. There should be only 1 reason for a class to change.
    //Make a decision if you want to support multiple fields.
    //Case 1: Multiple Fields -> There should be only 1 function that matches
    //Case 2: Only Single Fields -> You can use many functions.
    //The following is a bad design :
    public List<Book> searchBook(SearchParams searchParams){
        SearchService searchService = null;
        //Alternatively, switch(typeOf(searchParams))
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

abstract class Employee{
// All common Fields should go here:
    private PersonInfo personInfo;
    //This information is more suitable for all the employees
    //SO, I think you should create another class which keeps track of details of the employees
    private Date dateOfJoining;
    private SalaryInfo salaryInfo;
    private Account account;
}

class Librarian extends Employee{
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

class LibraryManagementSystem{
    private MemberCatalog memberCatalog;
    private BookCatalog bookCatalog;
    private List<Employee> librarians;
    private ISearchService searchService;
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

    public List<Book> searchBook(SearchParams searchParams){
        return ISearchService.search(bookCatalog, searchParams);
    }

    public BookReservation checkoutBookItem(Member member, BookItem bookItem,Duration duration) {
        return reservationService.checkoutBookItem(member,bookItem,duration);
    }

    public void checkInBookItem(BookItem bookItem) {
        reservationService.checkInBookItem(bookItem, new Date());
    }

    public void reserveBookItem(BookItem bookItem, Date startDate, Duration numDays) {

    }
    // A better option would be to have a function to sendNotification() inside a member class.
    //
    public void checkAndSendDueDateNotification(){
        List<BookReservation> dueDateOverReservations = reservationService.getReservationsWithBookDueDateOver();
        dueDateOverReservations.stream()
                .forEach(reservation -> {
                    notificationService.notify(reservation.getMember());
                });
    }
}
//This is a better implementation of search: 
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
    //You're sending notification to a member, How will you determine regarding which reservation it is associated with
    //How about void notify(BookReservation bookReservation) ? 
    void notify(Member member);
}

class EmailNotificationService implements NotificationService{
    @Override
    public void notify(Member member){
        //Just fetch the EmailDetails of the member and send the Email
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
    //WHy 2 details are required ? 
    //Try to associate the Fees related to either member or a bookReservation
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
