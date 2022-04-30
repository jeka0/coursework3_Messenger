package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.Authorization;
import com.example.messeger.ChatMenuActivity;
import com.example.messeger.R;
import com.example.messeger.Registration;
import com.google.android.material.snackbar.Snackbar;

import Handlers.IHandlers.ISwitchHandler;
import Net.IInternet;
import business.User;

public class SwitchActivity implements ISwitchHandler {
    private AppCompatActivity activity;
    private IInternet IInternet;
    private interface Run { void run(User user);}
    public SwitchActivity(AppCompatActivity activity, IInternet IInternet)
    {
        this.activity = activity;
        this.IInternet = IInternet;
    }
    @Override
    public void onClick(View view) {
        Class<? extends AppCompatActivity> activityClass;
        boolean flag = true;
        switch (view.getId()) {
            case R.id.buttonLogin:
                if(activity instanceof Authorization)activityClass = ChatMenuActivity.class;
                else {activityClass = Authorization.class;flag = false;}
                entrance(activityClass,flag,(User user)-> IInternet.checkUser(user),view);
                break;
            case R.id.buttonRegister:
                if(activity instanceof Authorization){activityClass = Registration.class;flag = false;}
                else activityClass = ChatMenuActivity.class;
                entrance(activityClass,flag,(User user)-> IInternet.UserRegistration(user),view);
                break;
            default:
                break;
        }
    }
    private void entrance(Class<? extends AppCompatActivity> activityClass, boolean flag, Run run, View view)
    {
        Intent intent = new Intent(activity, activityClass);
        if(flag) {
            if(IInternet.isConnected()) {
                EditText editTextName = activity.findViewById(R.id.editTextName), editTextPassword = activity.findViewById(R.id.editTextPassword);
                User user = new User(editTextName.getText().toString(), editTextPassword.getText().toString());
                intent.putExtra("User", user);
                IInternet.setIntent(intent);
                new Thread(() -> run.run(user)).start();
            }else Snackbar.make(view, "No connection to server", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else activity.startActivity(intent);
    }

    public AppCompatActivity getActivity() {
        return activity;
    }
}
