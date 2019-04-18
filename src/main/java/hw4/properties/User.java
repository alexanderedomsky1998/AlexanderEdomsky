package hw4.properties;

public class User {
    private String name;
    private String password;

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getUserName()
    {
        return name;
    }

    public String getUserPassword()
    {
        return password;
    }
}
