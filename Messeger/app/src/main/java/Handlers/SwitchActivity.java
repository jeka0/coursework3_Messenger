package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.AddChatActivity;
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
    private EditText editTextName,editTextPassword,editTextRepPassword;
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
                editTextPassword = activity.findViewById(R.id.editTextPassword);
                entrance(activityClass,flag,(User user)-> IInternet.checkUser(user),view);
                break;
            case R.id.buttonRegister:
                if(activity instanceof Authorization){activityClass = Registration.class;flag = false;}
                else activityClass = ChatMenuActivity.class;
                editTextPassword = activity.findViewById(R.id.editTextPassword);
                if(activity instanceof Authorization) entrance(activityClass,flag,(User user)-> IInternet.UserRegistration(user),view);
                else
                {
                    editTextRepPassword = activity.findViewById(R.id.editTextPasswordRepeat);
                    if(editTextPassword.getText().toString().equals(editTextRepPassword.getText().toString()))
                    entrance(activityClass,flag,(User user)-> IInternet.UserRegistration(user),view);
                    else editTextRepPassword.setError("Пароли должны совпадать!!!");
                }
                break;
            case R.id.floatingActionButton2:
                if (IInternet.isConnected()) {
                    Intent intent = new Intent(activity, AddChatActivity.class);
                    activity.startActivity(intent);
                } else
                    Snackbar.make(view, "No connection to server", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            default:
                break;
        }
    }
    private void entrance(Class<? extends AppCompatActivity> activityClass, boolean flag, Run run, View view)
    {
        Intent intent = new Intent(activity, activityClass);
        if(flag) {
            editTextName = activity.findViewById(R.id.editTextName);
            if(!editTextName.getText().toString().isEmpty()&&!editTextPassword.getText().toString().isEmpty()) {
                if (IInternet.isConnected()) {
                    User user = new User(editTextName.getText().toString(), editTextPassword.getText().toString());
                    intent.putExtra("User", user);
                    IInternet.setAppActivity(activity);
                    IInternet.setIntent(intent);
                    new Thread(() -> run.run(user)).start();
                } else
                    Snackbar.make(view, "No connection to server", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }else
            {
                if(editTextName.getText().toString().isEmpty())editTextName.setError("Строка не должна быть пустой!!!");
                if(editTextPassword.getText().toString().isEmpty())editTextPassword.setError("Строка не должна быть пустой!!!");
            }
        }
        else {activity.startActivity(intent);activity.finish();}
    }

    public AppCompatActivity getActivity() {
        return activity;
    }
}
