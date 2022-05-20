package com.example.messeger.uimenu.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.messeger.IShowError;
import com.example.messeger.databinding.FragmentAccountBinding;

import ViewModels.AccountViewModel;
import ViewModels.IViewModels.IAccountViewModel;
import business.User;

public class AccountFragment extends Fragment implements IShowError {

    private FragmentAccountBinding binding;
    private TextView userName;
    private EditText userPassword;
    private EditText userNewPassword;
    private IAccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.setAccountFragment(this);
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userName = binding.UserName;
        userPassword = binding.NowPassword;
        userNewPassword = binding.NewPassword;
        userName.setText(accountViewModel.getUser().getName());
        InitButton();
        return root;
    }
    private void InitButton()
    {
        Button SaveButton = binding.EditInformationButton;
        SaveButton.setOnClickListener((View view)->{
            if(!userName.getText().toString().isEmpty()&&!userPassword.getText().toString().isEmpty()&&!userNewPassword.getText().toString().isEmpty())
            {
                User user = new User(userName.getText().toString(),userPassword.getText().toString());
                user.setNewPassword(userNewPassword.getText().toString());
                accountViewModel.getClientAccess().setAccountViewModel(accountViewModel);
                new Thread(()->accountViewModel.getClientAccess().UpdateUser(user)).start();
            }else
            {
                if(userPassword.getText().toString().isEmpty())userPassword.setError("The previous password must be entered!!!!");
                if(userNewPassword.getText().toString().isEmpty())userNewPassword.setError("Enter a new password for your account!!!");
            }
        });
    }
    public void Clear()
    {
        userPassword.setText("");
        userNewPassword.setText("");
    }
    public void setError()
    {
        userPassword.setError("Wrong password entered!!!");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}