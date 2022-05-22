package Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.example.messeger.MyDialogFragment;
import com.example.messeger.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Handlers.IHandlers.IChatClickHandler;
import Net.IInternet;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.IViewModels.ISearchViewModel;
import ViewModels.MessengerViewModel;
import business.Chat;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {
    private List<Chat> chatList = new ArrayList<>();
    private IChatClickHandler clickHandler;
    private IMessengerViewModel messengerViewModel;
    private ISearchViewModel searchViewModel;
    private IInternet internet;
    class ChatViewHolder extends RecyclerView.ViewHolder
    {
        private TextView chatName;
        private ImageButton deleteButton;
        public ChatViewHolder(View view)
        {
            super(view);
            this.chatName = view.findViewById(R.id.user_name);
            this.deleteButton = view.findViewById(R.id.imageButton);
        }
        public void bind(Chat chat,int position)
        {
            chatName.setText(chat.getName());
            if(messengerViewModel==null)deleteButton.setVisibility(View.GONE);
            else {
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setOnClickListener((View view)->{
                    if (internet.isConnected()) {
                        new Thread(()->internet.DeleteChatToUser(chat)).start();
                    } else
                        Snackbar.make(view, "No connection to server", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                });
            }
            if(clickHandler!=null)itemView.setOnClickListener((View view)-> {
                if (internet.isConnected()) {
                    clickHandler.onClick(view,position);
                } else
                    Snackbar.make(view, "No connection to server", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            });

        }

    }
    public ChatsAdapter(IChatClickHandler clickHandler, ISearchViewModel searchViewModel)
    {
        this.clickHandler = clickHandler;
        this.searchViewModel=searchViewModel;
        this.internet = searchViewModel.getClientAccess();
    }
    public ChatsAdapter(IChatClickHandler clickHandler, IMessengerViewModel messengerViewModel)
    {
        this.clickHandler = clickHandler;
        this.messengerViewModel=messengerViewModel;
        this.internet = messengerViewModel.getClientAccess();
    }
    public void setItems(Collection<Chat> chats)
    {
        chatList.addAll(chats);
        notifyDataSetChanged();
    }
    public void clearItems()
    {
        chatList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatsAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat,parent,false);
        return new ChatsAdapter.ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapter.ChatViewHolder holder, int position) {
        holder.bind(chatList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
