package ViewModels.IViewModels;

import com.example.messeger.AddChatActivity;

import java.util.ArrayList;

import business.User;

public interface IAddChatModel extends IMyViewModel{
    void setAddChatActivity(AddChatActivity addChatActivity);
    void setUsers(User[] users);
    AddChatActivity getAddChatActivity();
    void UpdateUsers(User[] users);
    User[] getUsers();
    void setNowUsers(ArrayList<User> nowUsers);
    void addSelectedUser(User user);
    void removeSelectedUser(User user);
    ArrayList<User> getSelectedUsers();
}
