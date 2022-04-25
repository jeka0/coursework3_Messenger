package Handlers;

import android.view.View;
import android.widget.EditText;

import com.example.messeger.MainActivity;
import com.example.messeger.R;

import Net.Internet;

public class SubmitClickListener implements View.OnClickListener{
    private MainActivity activity;
    private Internet internet;
    public SubmitClickListener(MainActivity activity, Internet internet)
    {
        this.activity = activity;
        this.internet = internet;
    }
    @Override
    public void onClick(View view) {
        EditText editText = activity.findViewById(R.id.messageField);
        String text = editText.getText().toString();
        if (text.equals("")) return;
        new Thread(()-> internet.pushMessage(text)).start();
        editText.setText("");
    }
}
