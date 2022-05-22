package Handlers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.messeger.MainActivity;

import Data.Files;
import Handlers.IHandlers.IFileSelectionHandler;
import ViewModels.IViewModels.IMainViewModel;

public class FileSelectionHandler implements IFileSelectionHandler {
    private IMainViewModel mainViewModel;
    private MainActivity mainActivity;
    public FileSelectionHandler(MainActivity mainActivity,IMainViewModel mainViewModel)
    {
        this.mainActivity=mainActivity;
        this.mainViewModel=mainViewModel;
    }
    @Override
    public void onClick(View view) {
        try
        {
            if(Files.isStoragePermissionGranted(mainActivity)) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("file/*");
                mainActivity.startActivityForResult(photoPickerIntent, 10);
            }
        }catch(ActivityNotFoundException e){
            Toast.makeText(mainActivity,"Activity Not Found!!!!",Toast.LENGTH_SHORT).show();
        }
    }
}
