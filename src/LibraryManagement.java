import java.util.*;
import java.util.stream.Collectors;

/*
CLASSES
-------
1. Book, BookItem, Rack
2. SubjectCategory
3. Author
4. PersonInfo
5. Member
6. Constants
7. NotificationService
8. LibraryCard
9. librarian
10. Management System
11. Library
 */
class Constants{
    public static final int MAX_BOOK_CHECKOUT_LIMIT = 5;
    public static final int MAX_BOOK_CHECKOUT_TIME_LIMIT = 5;
    // others as required
}

// BOOKs related classes
enum BookStatus{
    CHECKED_OUT,
    AVAILABLE,
    RESERVED
}
class Book{
    private String ISBN;
    private String title;
    private String Author;
    private Date publicationDate;
    private SubjectCategory subjectCategory;
    private List<BookItem> bookItems;

    public Book(String ISBN, String title, String author, Date publicationDate, SubjectCategory subjectCategory) {
        this.ISBN = ISBN;
        this.title = title;
        Author = author;
        this.publicationDate = publicationDate;
        this.subjectCategory = subjectCategory;
        this.bookItems = new ArrayList<>();
    }

    public boolean addBook(Book book){
        return true;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public SubjectCategory getSubjectCategory() {
        return subjectCategory;
    }

    public void setSubjectCategory(SubjectCategory subjectCategory) {
        this.subjectCategory = subjectCategory;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

}
class Rack{
    int floor;
    int block;
    public Rack(int floor,int block){
        this.floor = floor;
        this.block = block;
    }
}

class BookItem{
    private int id;
    Book book;
    Rack rack;
    private BookStatus bookStatus;
    List<BookReservation> historyReservations;
    List<BookReservation> futureReservations;
    BookReservation currentReservation;

    public BookItem(Book book,Rack rack){
        this.book = book;
        this.rack = rack;
        bookStatus = BookStatus.AVAILABLE;
        historyReservations = new ArrayList<>();
        futureReservations = new ArrayList<>();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BookReservation> getHistoryReservations() {
        return historyReservations;
    }

    public void setHistoryReservations(List<BookReservation> historyReservations) {
        this.historyReservations = historyReservations;
    }

    public List<BookReservation> getFutureReservations() {
        return futureReservations;
    }

    public void setFutureReservations(List<BookReservation> futureReservations) {
        this.futureReservations = futureReservations;
    }

    public BookReservation getCurrentReservation() {
        return currentReservation;
    }

    public void setCurrentReservation(BookReservation currentReservation) {
        this.currentReservation = currentReservation;
    }

    public void addHistory(BookReservation reservation){
        this.historyReservations.add(reservation);
    }

    public void addFutureReservation(BookReservation reservation){
        this.futureReservations.add(reservation);
    }
}
enum SubjectCategory{
    FICTION,
    HORROR,
    FANTASY,
    ADVENTURE
    // ...
}

// Persons related classes
class Address{
    String street;
    String city;
    String State;
    String zipCode;
}
abstract class PersonInfo{
    String firstName;
    String lastName;
    Address address;
}

class Author{
    private PersonInfo personInfo;
    public Author(){

    }
}
class Librarian{
    PersonInfo personInfo;
    LibraryManagementSystem managementSystem;

    public Librarian(LibraryManagementSystem libraryManagementSystem){
        this.managementSystem = libraryManagementSystem;
    }

    public void setLibraryManagementSystem(LibraryManagementSystem system){
        this.managementSystem = system;
    }

    // methods
    // add books, remove books, update books, search books, checkout books, reserve books
    public LibraryCard registerMember(Member member) {
        return managementSystem.registerMember(member);
    }

    public boolean unregisterMember(Member member) {
        try{
            return managementSystem.unregisterMember(member);
        }catch (MemberNotFoundException e){
            System.out.println(e.toString());
        }
        return true;
    }

    public boolean addBook(Book book){
        return true;
    }

    public boolean removeBook(Book book){
        return true;
    }

    public List<Book> searchBooks(SearchParams params){
        return List.of();
    }

    public BookItem checkoutBook(Member member, Book book) throws BookNotFoundException, BookItemNotAvailableException {
        return managementSystem.checkOutBook(member,book);
    }
}

class SearchParams{
    private String title;
    private String author;
    private Date publicationDate;
    private SubjectCategory subjectCategory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public SubjectCategory getSubjectCategory() {
        return subjectCategory;
    }

    public void setSubjectCategory(SubjectCategory subjectCategory) {
        this.subjectCategory = subjectCategory;
    }
}
class Member{
    private PersonInfo personInfo;
    private LibraryCard libraryCard;
    private Librarian librarian;
    private LibraryManagementSystem managementSystem;
    private int ID;

    public Member(PersonInfo personInfo,int ID){
        this.personInfo = personInfo;
        this.ID = ID;
    }
    // methods
    // register, unregister, checkout books, reserve books, search Books

    public LibraryCard register(){
        LibraryCard libraryCard = librarian.registerMember(this);
        return libraryCard;
    }

    public boolean unregister(){
        return librarian.unregisterMember(this);
    }

    // search books
    public List<Book> searchBooks(SearchParams searchParams){
        return librarian.searchBooks(searchParams);
    }

    // Checkout Book
    public BookItem checkoutBook(Book book) throws BookNotFoundException, BookItemNotAvailableException {
        return librarian.checkoutBook(this,book);
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LibraryManagementSystem getManagementSystem() {
        return managementSystem;
    }

    public void setManagementSystem(LibraryManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }
}

enum SearchType{
    TITLE,
    AUTHOR,
    PUBLICATION_DATE,
    SUBJECT_CATEGORY
}
class LibraryCard{
    private long barCode;
    private Member member;
    public LibraryCard(long barCode,Member member){
        this.barCode = barCode;
        this.member = member;
    }
}
// Management System related classes
interface NotificationService{
    void notifyMember(Member member);
}

class EmailNotificationService implements NotificationService{
    @Override
    public void notifyMember(Member member){

    }
}

class SMSNotificationService implements NotificationService{
    @Override
    public void notifyMember(Member member){

    }
}

interface SearchEngine{
    List<Book> search(List<Book> books);
}

class SearchByTitle implements SearchEngine{
    private String searchTitle;
    public SearchByTitle(String searchTitle){
        this.searchTitle = searchTitle;
    }
    public List<Book> search(List<Book> books){
        return List.of();
    }
}

class SearchByAuthor implements SearchEngine{
    private Author searchAuthor;
    public SearchByAuthor(Author searchAuthor){
        this.searchAuthor = searchAuthor;
    }
    public List<Book> search(List<Book> books){
        return List.of();
    }
}
class LibraryManagementSystem {
    private Library library;
    private NotificationService notificationService;
    List<BookReservation> bookReservations;

    public LibraryManagementSystem(Library library){
        this.library = library;
        this.bookReservations = new ArrayList<>();
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public boolean notifyMember(Member member){
        try{
            this.notificationService.notifyMember(member);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public LibraryCard registerMember(Member member) {
        LibraryCard card = new LibraryCard(uniqueIdGenerator.getUniqueID(),member);
        library.getMembersCatalog().add(member);
        return card;
    }

    public boolean unregisterMember(Member member) throws MemberNotFoundException {
        int memberIdx = library.getMembersCatalog().indexOf(member);
        if(memberIdx == -1) throw new MemberNotFoundException("Member doesn't exist.");
        library.getMembersCatalog().remove(memberIdx);
        return true;
    }

    public List<Book> searchBooks(SearchParams params) {
        List<Book> books = library.getBooksCatalog();
        return books.stream()
                .filter(book -> params.getTitle() == null || book.getTitle().equals(params.getTitle()))
                .filter(book -> params.getAuthor() == null || book.getAuthor().equals(params.getAuthor()))
                .filter(book -> params.getPublicationDate() == null || book.getPublicationDate() == params.getPublicationDate())
                .filter(book -> params.getSubjectCategory() == null || book.getSubjectCategory() == params.getSubjectCategory())
                .collect(Collectors.toList());
    }

    public BookItem checkOutBook(Member member, Book book) throws BookNotFoundException, BookItemNotAvailableException {
        List<Book> books = library.getBooksCatalog();
        int index = books.indexOf(book);
        if(index == -1){
            throw new BookNotFoundException("Couldn't find book in the library catalog.");
        }
        Book searchedBook = library.getBooksCatalog().get(index);
        BookItem bookItem = searchedBook.getBookItems().stream()
                .filter(item -> item.getBookStatus() == BookStatus.AVAILABLE)
                .findFirst()
                .orElse(null);
        if(bookItem == null){
            throw new BookItemNotAvailableException("No book item is available at this moment.");
        }
        bookItem.setBookStatus(BookStatus.CHECKED_OUT);
        BookReservation bookReservation = new BookReservation(bookItem,member,new Date());
        bookReservations.add(bookReservation);
        bookItem.setCurrentReservation(bookReservation);
        return bookItem;
    }

    public BookReservation checkInBook(Member member, BookItem bookItem) {
        bookItem.setBookStatus(BookStatus.AVAILABLE);
        BookReservation bookReservation = findBookReservation(member,bookItem);
        bookReservation.setCheckInDate(new Date());
        Fine fine = new Fine(bookReservation);
        fine.calculateFine();
        bookReservation.setFine(fine);
        bookItem.addHistory(bookReservation);
        return bookReservation;
    }

    private BookReservation findBookReservation(Member member,BookItem bookItem){
        return bookReservations.stream()
                .filter(reservation -> {
                    return reservation.getBookItem().getId() == bookItem.getId() && reservation.getMember().getID() == member.getID();
                })
                .findFirst()
                .orElse(null);
    }
}

class Fine{
    private BookReservation reservation;
    private double fineAmount;

    public Fine(BookReservation reservation){
        this.reservation = reservation;
        this.fineAmount = 0.0;
    }

    public void calculateFine(){
        // implement fine calculation logic
        this.fineAmount = 0.0;
    }
}
class uniqueIdGenerator{
    public static int getUniqueID(){
        return new Random().nextInt(1000000000);
    }
}

// Exceptions
class MemberNotFoundException extends Throwable {
    String errorMsg;

    public MemberNotFoundException(String s) {
        this.errorMsg = s;
    }
}
class Library{
    private final String LIBRARY_NAME = "Commons Libarary";
    private final Address LIBRARY_ADDRESS = new Address();

    private List<Book> booksCatalog;
    private List<Member> membersCatalog;
    private Librarian librarian;
    private LibraryManagementSystem managementSystem;

    public Library(){
        booksCatalog = new ArrayList<>();
        membersCatalog = new ArrayList<>();
        managementSystem = new LibraryManagementSystem(this);
        librarian = new Librarian(managementSystem);
    }

    public void setLibrarian(Librarian librarian){
        this.librarian = librarian;
    }

    public Librarian getLibrarian(){return this.librarian;}

    public List<Book> getBooksCatalog() {
        return booksCatalog;
    }

    public void setBooksCatalog(List<Book> booksCatalog) {
        this.booksCatalog = booksCatalog;
    }

    public List<Member> getMembersCatalog() {
        return membersCatalog;
    }

    public void setMembersCatalog(List<Member> membersCatalog) {
        this.membersCatalog = membersCatalog;
    }

    public LibraryManagementSystem getManagementSystem() {
        return managementSystem;
    }

    public void setManagementSystem(LibraryManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }
}
public class LibraryManagement{
    public static void main(String[] args){

    }
}
