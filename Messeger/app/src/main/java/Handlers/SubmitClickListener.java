package Handlers;

import android.view.View;
import android.widget.EditText;

import com.example.messeger.MainActivity;
import com.example.messeger.R;

import Handlers.IHandlers.ISubmitClickListener;
import Net.IInternet;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.MainViewModel;
import business.Message;
import business.MyFIle;

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
        byte[] image = model.getImage();
        MyFIle file = model.getFile();
        if (text.equals("") && image ==null && file==null) return;
        Message message = new Message(model.getUser().getName(),chatName,text);
        if(image!=null){message.setImage(image);model.setImage(null);}
        if(file!=null){message.setFile(file);model.setFile(null);}
        new Thread(()-> IInternet.pushMessage(message)).start();
        editText.setText("");
    }
}
