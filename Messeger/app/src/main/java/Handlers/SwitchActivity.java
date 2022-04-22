package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.Authorization;
import com.example.messeger.MainActivity;
import com.example.messeger.R;
import com.example.messeger.Registration;

public class SwitchActivity implements View.OnClickListener{
    private AppCompatActivity activity;
    public SwitchActivity(AppCompatActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Class<? extends AppCompatActivity> activityClass;
        EditText editTextName,editTextPassword;
        String textName, textPassword;
        boolean flag = true;
        switch (view.getId()) {
            case R.id.buttonLogin:
                if(activity instanceof Authorization)activityClass = MainActivity.class;
                else {activityClass = Authorization.class;flag = false;}
                intent = new Intent(activity, activityClass);
                if(flag) {
                    editTextName = activity.findViewById(R.id.editTextName);
                    editTextPassword = activity.findViewById(R.id.editTextPassword);
                    textName = editTextName.getText().toString();
                    textPassword = editTextPassword.getText().toString();
                    intent.putExtra("Name", textName);
                    intent.putExtra("Password", textPassword);
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
                    textName = editTextName.getText().toString();
                    textPassword = editTextPassword.getText().toString();
                    intent.putExtra("Name", textName);
                    intent.putExtra("Password", textPassword);
                }
                activity.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
