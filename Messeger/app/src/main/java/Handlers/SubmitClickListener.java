package Handlers;

import android.view.View;
import android.widget.EditText;

import com.example.messeger.MainActivity;
import com.example.messeger.R;

import Handlers.IHandlers.ISubmitClickListener;
import Net.IInternet;

public class SubmitClickListener implements ISubmitClickListener {
    private MainActivity activity;
    private IInternet IInternet;
    public SubmitClickListener(MainActivity activity, IInternet IInternet)
    {
        this.activity = activity;
        this.IInternet = IInternet;
    }
    @Override
    public void onClick(View view) {
        EditText editText = activity.findViewById(R.id.messageField);
        String text = editText.getText().toString();
        if (text.equals("")) return;
        new Thread(()-> IInternet.pushMessage(text)).start();
        editText.setText("");
    }
}
