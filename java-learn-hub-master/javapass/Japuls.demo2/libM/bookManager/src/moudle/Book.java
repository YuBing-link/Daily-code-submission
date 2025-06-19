package moudle;

public class Book {

    private String bookName;

    private String author;

    private String sex;

    private float price;

    private String bookDesc;
    private Integer bookTypeId;


    public Book( String bookName, String author, String sex, float price, String bookDesc, Integer bookTypeId) {

        this.bookName = bookName;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.bookDesc = bookDesc;
        this.bookTypeId = bookTypeId;
    }



    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getSex() {
        return sex;
    }

    public float getPrice() {
        return price;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }
}
