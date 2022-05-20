package business;

import java.io.Serializable;

public class User implements Serializable {
    private String Name;
    private String Password, newPassword;
    public User(){}
    public User(String Name,String Password)
    {
        this.Name = Name;
        this.Password = Password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setName(String name) {Name = name;}
    public void setPassword(String password) {Password = password;}

    public String getNewPassword() {
        return newPassword;
    }

    public String getName() {return Name;}
    public String getPassword() {return Password;}
}
