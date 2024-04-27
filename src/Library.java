//
//import java.util.*;
//
//class PersonInfo{
//    String firstName;
//    String lastName;
//    String email;
//
//    public PersonInfo(String firstName,String lastName,String email){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//}
//class Author{
//    private PersonInfo personInfo;
//    public Author(PersonInfo personInfo){
//        this.personInfo = personInfo;
//    }
//}
//
//enum SubjectCategory{
//    FICTION,
//    SCIFI,
//    HORROR
//    // add as per requirements
//}
//
//class Rack{
//    int floor;
//    int block;
//}
//
//class BookItem{
//    Rack rack;
//    Book book;
//    List<BookReservation> reservationsHistory;
//    List<BookReservation> upcomingReservations;
//    BookReservation currentReservation;
//}
//
//class Book{
//    long ISBN;
//    String title;
//    Author author;
//    SubjectCategory category;
//    Date publicationDate;
//    ArrayList<BookItem> bookItems;
//}
//
//class Member{
//    private PersonInfo personInfo;
//    private String id;  // or a unique id generator
//    private LibraryCard libraryCard;
//    List<BookItem> checkedOutBooks;
//    LibraryManagementSystem system;
//
//    public Member(String firstName,String lastName,String email,String id){
//        this.personInfo = new PersonInfo(firstName,lastName,email);
//        this.id = id;
//        this.checkedOutBooks = new ArrayList<>();
//    }
//
//    public List<Book> searchBook(SearchParams params){
//        return system.searchBook(params);
//    }
//
//    public boolean checkOutBook(Book book){
//        Optional<Book> checkedOutBook = system.checkOutBook(this,book);
//        if(!checkedOutBook.isPresent()) return false;
//        checkedOutBooks.add(checkedOutBook.get());
//        return true;
//    }
//
//    public boolean returnBook(Book book){
//        system.returnBook(this,book);
//        checkedOutBooks.remove(book);
//    }
//
//    public boolean reserveBook(Book book){
//        return system.reserveBook(this);
//    }
//    public void register(LibraryManagementSystem system){
//        LibraryCard card = system.registerMember(this);
//        if(card == null) return;
//        this.system = system;
//        this.libraryCard = card;
//    }
//
//    public boolean unregister(){
//        boolean res = system.unregisterMember(this);
//        this.system = null;
//        return res;
//    }
//
//    public boolean updateAccount(Member newMember){
//        return system.updateMemberAccount(this,newMember);
//    }
//
//    public String getId(){return this.id;}
//    @Override
//    public String toString() {
//        return "Member{\n" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", id='" + id + '\'' +
//                ", libraryCard=" + libraryCard +
//                ", checkedOutBooks=" + checkedOutBooks +
//                ", system=" + system +
//                "}\n";
//    }
//}
//
//class LibraryCard{
//    long barCode;
//    Member member;
//    public LibraryCard(long barCode,Member member){
//        this.barCode = barCode;
//        this.member = member;
//    }
//}
//
//class Librarian extends Person{
//    LibraryManagementSystem system;
//
//    public Librarian(String firstName,String lastName,String email){
//        super(firstName,lastName,email);
//    }
//
//    public void setSystem(LibraryManagementSystem system){
//        this.system = system;
//    }
//
//    public void addBook(Book book, ArrayList<Book> books){
//
//    }
//
//    public void removeBook(Book book, ArrayList<Book> books){
//
//    }
//
//    public void editBook(Book oldBook,Book newBook, ArrayList<Book> books){
//
//    }
//
//    public List<Member> getMembers(){
//        return system.getMembers();
//    }
//
//    public void addMember(Member member){
//        system.getMembers().add(member);
//    }
//
//    public void removeMember(Member member){
//
//    }
//
//    public Optional<Book> checkoutBook(Member member,Book book){
//        // Find the book in the library and if it has enough copies, then return an item of the book
//        return Optional.ofNullable(book);
//    }
//}
//
//// Should be singleton
//final class MemberCatalog{
//    private static MemberCatalog instance;
//    private HashMap<String,Member> catalog;
//    private MemberCatalog(){
//        this.catalog = new HashMap<>();
//    }
//
//    public static MemberCatalog getInstance(){
//        if(instance == null){
//            instance = new MemberCatalog();
//        }
//        return instance;
//    }
//
//    public boolean addMember(Member member){
//        if(instance.catalog.containsKey(member.getId())) return false;
//        instance.catalog.put(member.getId(),member);
//        return true;
//    }
//
//    public boolean removeMember(Member member){
//        if(!instance.catalog.containsKey(member.getId())) return false;
//        instance.catalog.remove(member.getId());
//        return true;
//    }
//
//    public boolean isMemberPresent(Member member){
//        return instance.catalog.containsKey(member.getId());
//    }
//
//    public HashMap<String,Member> getMembers(){
//        return instance.catalog;
//    }
//}
//
//final class LibraryConstants{
//    public static final int MAX_CHECKOUT_DAY_LIMIT = 5;
//    public static final int MAX_CHECKEDOUT_BOOK_LIMIT_PER_USER = 5;
//}
//
//class UniqueLibraryCardIDGenerator{
//    private static long id = 0;
//    public static long getUniqueId(){
//        id++;
//        return id;
//    }
//}
//
//class BookReservation{
//    BookItem bookItem;
//    Member member;
//    Date checkOut;
//    Date checkIn;
//    double fine;
//
//    public BookItem getBookItem() {
//        return bookItem;
//    }
//
//    public void setBookItem(BookItem bookItem) {
//        this.bookItem = bookItem;
//    }
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
//    public Date getCheckOut() {
//        return checkOut;
//    }
//
//    public void setCheckOut(Date checkOut) {
//        this.checkOut = checkOut;
//    }
//
//}
//class LibraryManagementSystem{
//    private Librarian librarian;
//    private ArrayList<Book> books;
//    private NotificationService notificationService;
//    private MemberCatalog memberCatalog;
//    List<BookReservation> bookReservations;
//
//    public LibraryManagementSystem(){
//        this.books = new ArrayList<>();
//        this.memberCatalog  = MemberCatalog.getInstance();
//        bookReservations = new ArrayList<>();
//    }
//    public void setLibrarian(Librarian librarian){ this.librarian = librarian;}
//    public void setNotificationService(NotificationService notificationService){ this.notificationService = notificationService;}
//    public void addBook(Book book){
//        librarian.addBook(book,books);
//    }
//
//    public void removeBook(Book book){
//        librarian.removeBook(book,books);
//    }
//
//    public void editBook(Book oldBook,Book newBook){
//        librarian.editBook(oldBook,newBook,books);
//    }
//
//    public Optional<Book> checkOutBook(Member member,Book book){
//        return librarian.checkoutBook(member,book);
//    }
//
//    public LibraryCard registerMember(Member member){
//        boolean res = memberCatalog.addMember(member);
//        if(!res) return null;
//        LibraryCard libCard = new LibraryCard(UniqueLibraryCardIDGenerator.getUniqueId(),member);
//        return libCard;
//    }
//
//    public boolean unregisterMember(Member member){
//        if(!memberCatalog.isMemberPresent(member)) return false;
//        memberCatalog.removeMember(member);
//        return true;
//    }
//
//    public void printMembers(){
//        for(Member member : memberCatalog.getMembers().values()){
//            System.out.println(member);
//        }
//    }
//
//    public List<Book> searchBook(SearchParams params) {
//        return List.of();
//    }
//}
//
//interface NotificationService{
//    void Notify(Member member);
//}
//
//class emailNotificationService implements NotificationService{
//
//    @Override
//    public void Notify(Member member) {
//
//    }
//}
//
//class SearchParams{
//    String title;
//    Author author;
//    SubjectCategory category;
//    Date publicationDate;
//
//    public SearchParams(){
//
//    }
//    public void setTitle(String title){
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public SubjectCategory getCategory() {
//        return category;
//    }
//
//    public void setCategory(SubjectCategory category) {
//        this.category = category;
//    }
//
//    public Date getPublicationDate() {
//        return publicationDate;
//    }
//
//    public void setPublicationDate(Date publicationDate) {
//        this.publicationDate = publicationDate;
//    }
//    //    class SearchParamBuilder{
////        SearchParams searchParams;
////        public SearchParamBuilder(){
////
////        }
////
////        public SearchParamBuilder withTitle(String title){
////
////        }
////        public SearchParams build(){
////            return
////        }
////    }
//}
//
//
//class SearchService{
//    public SearchService(){}
//    public List<Book> searchByTitle(String title){
//        return List.of(new Book());
//    }
//
//    public List<Book> searchByAuthor(Author author){
//        return List.of(new Book());
//    }
//
//    public List<Book> searchByCateogry(SubjectCategory category){
//        return List.of(new Book());
//    }
//
//    public List<Book> searchByPublicationDate(Date date){
//        return List.of(new Book());
//    }
//}
//
//
//public class Library {
//    public static void main(String[] args){
//        Librarian librarian = new Librarian("John","Doe","john.doe@gmai.com");
//        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
//        libraryManagementSystem.setLibrarian(librarian);
//        librarian.setSystem(libraryManagementSystem);
//        libraryManagementSystem.setNotificationService(new emailNotificationService());
//
//        // Add members
////        librarian.addMember(new Member("anand","kumar","anand.ku@gmail.com","1",new LibraryCard(1l),libraryManagementSystem));
////        librarian.addMember(new Member("Sam","smith","sam.sm@gmail.com","2",new LibraryCard(2l),libraryManagementSystem));
//
//        Member m1 = new Member("anand","kumar","anand.ku@gmail.com","1");
//        Member m2 = new Member("Sam","Smith","anand.ku@gmail.com","2");
//        m1.register(libraryManagementSystem);
//        m2.register(libraryManagementSystem);
//
//        libraryManagementSystem.printMembers();
//
//        // Add books
//    }
//
//
//}
