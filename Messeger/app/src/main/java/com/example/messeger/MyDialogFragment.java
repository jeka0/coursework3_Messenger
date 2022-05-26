package com.example.messeger;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    private String title;
    private String message;
    private String button1String;
    private String button2String;
    private DialogInterface.OnClickListener PositiveClick;
    public MyDialogFragment() {

    }
    public MyDialogFragment(String title, String message,String button1String,String button2String, DialogInterface.OnClickListener PositiveClick )
    {
        this.title=title;
        this.message=message;
        this.button1String = button1String;
        this.button2String = button2String;
        this.PositiveClick=PositiveClick;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            else
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }catch (NullPointerException e){System.out.println(e.getMessage());}
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(button1String, PositiveClick);
        builder.setNegativeButton(button2String,
                (DialogInterface dialog, int id) -> Toast.makeText(getActivity(), "Deletion canceled", Toast.LENGTH_LONG).show());
        builder.setCancelable(true);

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }catch (NullPointerException e){System.out.println(e.getMessage());}
    }
}
