package Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.example.messeger.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Handlers.IHandlers.IChatClickHandler;
import business.Chat;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {
    private List<Chat> chatList = new ArrayList<>();
    private IChatClickHandler clickHandler;
    class ChatViewHolder extends RecyclerView.ViewHolder
    {
        private TextView chatName;
        public ChatViewHolder(View view)
        {
            super(view);
            this.chatName = view.findViewById(R.id.chat_name);
        }
        public void bind(Chat chat,int position)
        {
            chatName.setText(chat.getName());
            itemView.setOnClickListener((View view)-> clickHandler.onClick(view,position));
        }

    }
    public ChatsAdapter() {}
    public ChatsAdapter(IChatClickHandler clickHandler)
    {
        this.clickHandler = clickHandler;
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
