package business;

public class User {
    public String Name;
    public String Password;
    public User(){}
    public User(String Name,String Password)
    {
        this.Name = Name;
        this.Password = Password;
    }
    public void setName(String name) {Name = name;}
    public void setPassword(String password) {Password = password;}
    public String getName() {return Name;}
    public String getPassword() {return Password;}
}
