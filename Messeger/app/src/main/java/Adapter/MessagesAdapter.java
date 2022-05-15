package Adapter;

import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.example.messeger.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import business.Message;
import business.MyFIle;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>
{
    private List<Message> messageList = new ArrayList<>();
    class MessageViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName;
        private TextView messageTime;
        private TextView messageText;
        private ImageView image;
        private FloatingActionButton fileButt;
        private TextView fileName;
        private TextView fileInfo;
        private ConstraintLayout file;
        public MessageViewHolder(View view)
        {
            super(view);
            this.userName = view.findViewById(R.id.text_user_name);
            this.messageTime = view.findViewById(R.id.text_time);
            this.messageText = view.findViewById(R.id.text_message);
            this.image=view.findViewById(R.id.imageView2);
            this.fileButt=view.findViewById(R.id.floatingActionButton);
            this.fileName=view.findViewById(R.id.text_file_name);
            this.fileInfo=view.findViewById(R.id.text_file_info);
            this.file =view.findViewById(R.id.constraintLayout2);

        }
        public void bind(Message message)
        {
            userName.setText(message.getUserName());
            messageTime.setText(DateFormat.format("HH:mm",message.getMessageTime()));
            messageText.setText(message.getTextMessage());
            byte[] bytesImage = message.getImage();
            MyFIle myFile = message.getFile();
            if(myFile!=null&& myFile.getData()!=null) {
                file.setVisibility(View.VISIBLE);
                fileName.setText(myFile.getName());
                int size = myFile.getData().length;
                DecimalFormat df = new DecimalFormat("###.##");
                String strSize="";
                if(size/(1024.0*1024.0)>=1) strSize = df.format(size/(1024.0*1024.0)) +" MB";else if(size/1024.0>=1)strSize = df.format(size/1024.0) +" KB";else strSize = size +" Bytes";
                fileInfo.setText(myFile.getExtension()+"/"+strSize);
            }else file.setVisibility(View.GONE);
            if(bytesImage!=null)image.setImageBitmap(BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length));else image.setImageBitmap(null);
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
