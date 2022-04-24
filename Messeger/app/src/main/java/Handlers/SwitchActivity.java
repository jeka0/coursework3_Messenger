package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.Authorization;
import com.example.messeger.MainActivity;
import com.example.messeger.R;
import com.example.messeger.Registration;

import Net.ClientAccess;
import business.User;

public class SwitchActivity implements View.OnClickListener{
    private AppCompatActivity activity;
    private ClientAccess clientAccess;
    public SwitchActivity(AppCompatActivity activity, ClientAccess clientAccess)
    {
        this.activity = activity;
        this.clientAccess = clientAccess;
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        Class<? extends AppCompatActivity> activityClass;
        EditText editTextName,editTextPassword;
        User user;
        boolean flag = true;
        switch (view.getId()) {
            case R.id.buttonLogin:
                if(activity instanceof Authorization)activityClass = MainActivity.class;
                else {activityClass = Authorization.class;flag = false;}
                intent = new Intent(activity, activityClass);
                if(flag) {
                    editTextName = activity.findViewById(R.id.editTextName);
                    editTextPassword = activity.findViewById(R.id.editTextPassword);
                    user = new User(editTextName.getText().toString(),editTextPassword.getText().toString());
                    new Thread(()->clientAccess.checkUser(user)).start();
                    intent.putExtra("User",user);
                }
                activity.startActivity(intent);
                break;
            case R.id.buttonRegister:
                if(activity instanceof Authorization){activityClass = Registration.class;flag = false;}
                else activityClass = MainActivity.class;
                intent = new Intent(activity, activityClass);
                if(flag) {
                    editTextName = activity.findViewById(R.id.editTextName);
                    editTextPassword = activity.findViewById(R.id.editTextPassword);
                    user = new User(editTextName.getText().toString(),editTextPassword.getText().toString());
                    new Thread(()->clientAccess.UserRegistration(user)).start();
                    intent.putExtra("User",user);
                }
                activity.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
