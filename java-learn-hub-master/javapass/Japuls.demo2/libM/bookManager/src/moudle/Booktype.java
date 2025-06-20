package moudle;

public class Booktype {
    private Integer id;
    private String bookTypeName;

    private String bookTypeDesc;



    public Booktype( String bookTypeName, String bookTypeDesc) {

        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }


    public Integer getId() {
        return id;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public String getBookTypeDesc() {
        return bookTypeDesc;
    }
}
