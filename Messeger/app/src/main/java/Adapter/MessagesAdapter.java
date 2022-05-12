package Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.example.messeger.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import business.Message;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>
{
    private List<Message> messageList = new ArrayList<>();
    class MessageViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName;
        private TextView messageTime;
        private TextView messageText;
        private ImageView image;
        public MessageViewHolder(View view)
        {
            super(view);
            this.userName = view.findViewById(R.id.text_user_name);
            this.messageTime = view.findViewById(R.id.text_time);
            this.messageText = view.findViewById(R.id.text_message);
            this.image=view.findViewById(R.id.imageView2);
        }
        public void bind(Message message)
        {
            userName.setText(message.getUserName());
            messageTime.setText(DateFormat.format("dd-MM-yyyy HH:mm:ss",message.getMessageTime()));
            messageText.setText(message.getTextMessage());
            byte[] bytes = message.getImage();
            if(bytes!=null)image.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        }

    }
    public void setItems(Collection<Message> messages)
    {
        messageList.addAll(messages);
        notifyDataSetChanged();
    }
    public void clearItems()
    {
        messageList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
