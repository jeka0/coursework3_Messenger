package Handlers;

import android.content.Intent;
import android.view.View;

import com.example.messeger.MainActivity;

import Handlers.IHandlers.IImageSelectionHandler;
import ViewModels.IViewModels.IMainViewModel;

public class ImageSelectionHandler implements IImageSelectionHandler {
    private IMainViewModel mainViewModel;
    private MainActivity mainActivity;
    public ImageSelectionHandler(MainActivity mainActivity,IMainViewModel mainViewModel)
    {
        this.mainActivity=mainActivity;
        this.mainViewModel=mainViewModel;
    }
    @Override
    public void onClick(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        mainActivity.startActivityForResult(photoPickerIntent, 1);
    }

}
