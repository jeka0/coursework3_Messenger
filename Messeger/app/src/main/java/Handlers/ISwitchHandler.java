package Handlers;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public interface ISwitchHandler extends View.OnClickListener{
    @Override
    void onClick(View view);
    AppCompatActivity getActivity();
}
