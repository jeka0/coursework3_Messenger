package Handlers;

import android.view.View;
import android.widget.EditText;

import com.example.messeger.MainActivity;
import com.example.messeger.R;

import Net.ClientAccess;

public class SubmitClickListener implements View.OnClickListener{
    private MainActivity activity;
    private ClientAccess clientAccess;
    public SubmitClickListener(MainActivity activity, ClientAccess clientAccess)
    {
        this.activity = activity;
        this.clientAccess = clientAccess;
    }
    @Override
    public void onClick(View view) {
        EditText editText = activity.findViewById(R.id.messageField);
        String text = editText.getText().toString();
        if (text.equals("")) return;
        new Thread(()->clientAccess.pushMessage(text)).start();
        editText.setText("");
    }
}
