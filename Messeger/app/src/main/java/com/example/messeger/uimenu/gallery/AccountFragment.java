package com.example.messeger.uimenu.gallery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.messeger.AddChatActivity;
import com.example.messeger.Authorization;
import com.example.messeger.IShowError;
import com.example.messeger.MyDialogFragment;
import com.example.messeger.R;
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
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.setAccountFragment(this);
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userName = binding.UserName;
        userPassword = binding.NowPassword;
        userNewPassword = binding.NewPassword;
        userName.setText(accountViewModel.getUser().getName());
        settings = accountViewModel.getMenuActivity().getSharedPreferences(getString(R.string.pfName), Context.MODE_PRIVATE);
        editor = settings.edit();
        InitButton();
        return root;
    }
    private void InitButton()
    {
        Button SaveButton = binding.EditInformationButton, ExitButton = binding.ExitButton, DelButton = binding.DeleteButton;
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
        ExitButton.setOnClickListener((View view)-> {
            Intent intent = new Intent(accountViewModel.getMenuActivity(), Authorization.class);
            startActivity(intent);
            editor.putString(getString(R.string.pfCodeForID), getString(R.string.pfNoStringPresent));
            editor.putString(getString(R.string.pfCodeForPassword), getString(R.string.pfNoStringPresent));
            editor.commit();
            accountViewModel.getMenuActivity().finish();
        });
        DelButton.setOnClickListener((View view)->{
            FragmentManager manager = accountViewModel.getMenuActivity().getSupportFragmentManager();
            MyDialogFragment myDialogFragment = new MyDialogFragment("Delete user?","All user data will be deleted and cannot be recovered!!!",
                    "YES","NO",(DialogInterface dialog, int id)->{
                new Thread(()->accountViewModel.getClientAccess().DeleteUser()).start();
                ExitButton.callOnClick();
            });
            myDialogFragment.show(manager, "myDialog");
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