package Handlers;

import android.content.Intent;
import android.view.View;

import com.example.messeger.ChatMenuActivity;
import com.example.messeger.MainActivity;

import Handlers.IHandlers.IChatClickHandler;
import ViewModels.IViewModels.IMessengerViewModel;

public class ChatClickHandler implements IChatClickHandler {
    private IMessengerViewModel viewModel;
    private ChatMenuActivity activity;
    public ChatClickHandler(IMessengerViewModel viewModel) {
        this.viewModel = viewModel;
        this.activity = viewModel.getMenuActivity();
    }
    public void onClick(View view, int position) {

        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("position",position);
        activity.startActivity(intent);
    }
}
