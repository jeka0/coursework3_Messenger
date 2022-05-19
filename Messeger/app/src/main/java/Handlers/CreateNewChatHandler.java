package Handlers;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.messeger.AddChatActivity;
import com.example.messeger.ChatMenuActivity;
import com.example.messeger.R;

import Handlers.IHandlers.ICreateNewChatHandler;
import ViewModels.AddChatModel;
import ViewModels.IViewModels.IAddChatModel;
import business.Chat;
import business.User;

public class CreateNewChatHandler implements ICreateNewChatHandler {
    private IAddChatModel addChatModel;
    private AddChatActivity activity;
    public CreateNewChatHandler(IAddChatModel addChatModel)
    {
        this.addChatModel=addChatModel;
        this.activity= addChatModel.getAddChatActivity();
    }

    @Override
    public void onClick(View view) {
        if(addChatModel!=null)
        {
            EditText chatName = activity.findViewById(R.id.editTextTextChatName);
            String name= chatName.getText().toString();
            if(!name.isEmpty())
            {
                Chat newChat = new Chat(name);
                for(User user : addChatModel.getSelectedUsers())newChat.addUser(user.getName());
                addChatModel.getClientAccess().setAppActivity(activity);
                Intent intent = new Intent(activity, ChatMenuActivity.class);
                intent.putExtra("User", addChatModel.getUser());
                addChatModel.getClientAccess().setIntent(intent);
                new Thread(()->addChatModel.getClientAccess().AddChat(newChat)).start();
            }else chatName.setError("Chat name must not be empty!!!");
        }
    }
}
