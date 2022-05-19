package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messeger.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Handlers.IHandlers.IChatClickHandler;
import ViewModels.IViewModels.IAddChatModel;
import business.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> userList = new ArrayList<>();
    private IChatClickHandler clickHandler;
    private IAddChatModel addChatModel;
    class UserViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName;
        private CheckBox checkBox;
        public UserViewHolder(View view)
        {
            super(view);
            this.userName = view.findViewById(R.id.user_name);
            this.checkBox = view.findViewById(R.id.checkBox);
        }
        public void bind(User user, int position)
        {
            userName.setText(user.getName());
            if(addChatModel.getSelectedUsers().contains(user))checkBox.setChecked(true);else checkBox.setChecked(false);
            checkBox.setOnClickListener((View view) ->{
                if(checkBox.isChecked())addChatModel.addSelectedUser(user);else addChatModel.removeSelectedUser(user);
                });
            if(clickHandler!=null)itemView.setOnClickListener((View view)-> clickHandler.onClick(view,position));
        }

    }
    public UserAdapter(IAddChatModel addChatModel) {this.addChatModel=addChatModel;}
    public UserAdapter(IChatClickHandler clickHandler)
    {
        this.clickHandler = clickHandler;
    }
    public void setItems(Collection<User> users)
    {
        userList.addAll(users);
        notifyDataSetChanged();
    }
    public void clearItems()
    {
        userList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user,parent,false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        holder.bind(userList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
