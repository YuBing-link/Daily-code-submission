package moudle;

public class User {
    private Integer id;
    private String userName;

    private String password;

    public User( String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
