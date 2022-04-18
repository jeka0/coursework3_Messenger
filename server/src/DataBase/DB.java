package DataBase;

import business.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DB {
    private DB db;
    private DB(){}
    public DB getInstance(){
        if(db==null)return new DB();
        else return db;
    }
    public int CheckUserPassword(User user)
    {
        JsonWork json = new JsonWork("database\\users.json");
        User[] users = json.ReadArray(User[].class);
        for(User nowUser:users)
            if(nowUser.getName().equals(user.getName()))
                if(nowUser.getPassword().equals(user.getPassword()))return 1;else return 0;
        return -1;
    }
}
