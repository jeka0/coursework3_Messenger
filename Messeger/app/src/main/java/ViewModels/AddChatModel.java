package ViewModels;

import com.example.messeger.AddChatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import ViewModels.IViewModels.IAddChatModel;
import business.User;

public class AddChatModel extends MyViewModel implements IAddChatModel {
    private User[] users = new User[0];
    private ArrayList<User> nowUsers = new ArrayList<>();
    private ArrayList<User> selectedUsers = new ArrayList<>();
    private AddChatActivity addChatActivity;
    public AddChatModel()
    {
        super();
        getClientAccess().setAddChatModel(this);
    }

    public AddChatActivity getAddChatActivity() {
        return addChatActivity;
    }

    public void setAddChatActivity(AddChatActivity addChatActivity) {
        this.addChatActivity = addChatActivity;
        System.out.println(selectedUsers.size());
        if(nowUsers.size()==0)new Thread(()->{getClientAccess().getUsers();}).start();
        else UpdateUsers(nowUsers.toArray(new User[0]));
    }
    public void UpdateUsers(User[] users)
    {
        if(addChatActivity!=null)addChatActivity.runOnUiThread(()->addChatActivity.UpdateUsers(Arrays.asList(users)));
    }
    public void setUsers(User[] users) {
        this.users = users;
        UpdateUsers(users);
    }

    public void setNowUsers(ArrayList<User> nowUsers) {
        this.nowUsers = nowUsers;
    }

    public User[] getUsers() {
        return users;
    }
    public void addSelectedUser(User user)
    {
        selectedUsers.add(user);
    }

    public ArrayList<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void removeSelectedUser(User user)
    {
        selectedUsers.remove(user);
    }

}
