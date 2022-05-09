package Handlers;

import android.view.View;
import android.widget.EditText;

import com.example.messeger.MainActivity;
import com.example.messeger.R;

import Handlers.IHandlers.ISubmitClickListener;
import Net.IInternet;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.MainViewModel;

public class SubmitClickListener implements ISubmitClickListener {
    private MainActivity activity;
    private IMainViewModel model;
    private IInternet IInternet;
    private String chatName;
    public SubmitClickListener(MainActivity activity, IMainViewModel model)
    {
        this.chatName = model.getChatName();
        this.model = model;
        this.activity = activity;
        this.IInternet = model.getClientAccess();
    }
    @Override
    public void onClick(View view) {
        EditText editText = activity.findViewById(R.id.messageField);
        String text = editText.getText().toString();
        if (text.equals("")) return;
        new Thread(()-> IInternet.pushMessage(chatName,text)).start();
        editText.setText("");
    }
}
