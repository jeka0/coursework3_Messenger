package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.Authorization;
import com.example.messeger.MainActivity;
import com.example.messeger.R;
import com.example.messeger.Registration;

import Net.Internet;
import business.User;

public class SwitchActivity implements View.OnClickListener{
    private AppCompatActivity activity;
    private Internet internet;
    private interface Run { void run(User user);}
    public SwitchActivity(AppCompatActivity activity, Internet internet)
    {
        this.activity = activity;
        this.internet = internet;
    }
    @Override
    public void onClick(View view) {
        Class<? extends AppCompatActivity> activityClass;
        boolean flag = true;
        switch (view.getId()) {
            case R.id.buttonLogin:
                if(activity instanceof Authorization)activityClass = MainActivity.class;
                else {activityClass = Authorization.class;flag = false;}
                entrance(activityClass,flag,(User user)-> internet.checkUser(user));
                break;
            case R.id.buttonRegister:
                if(activity instanceof Authorization){activityClass = Registration.class;flag = false;}
                else activityClass = MainActivity.class;
                entrance(activityClass,flag,(User user)-> internet.UserRegistration(user));
                break;
            default:
                break;
        }

    }
    private void entrance(Class<? extends AppCompatActivity> activityClass, boolean flag, Run run)
    {
        Intent intent = new Intent(activity, activityClass);
        if(flag) {
            EditText editTextName = activity.findViewById(R.id.editTextName), editTextPassword = activity.findViewById(R.id.editTextPassword);
            User user = new User(editTextName.getText().toString(),editTextPassword.getText().toString());
            intent.putExtra("User",user);
            internet.setIntent(intent);
            new Thread(()->run.run(user)).start();
        }
        else activity.startActivity(intent);
    }

    public AppCompatActivity getActivity() {
        return activity;
    }
}
