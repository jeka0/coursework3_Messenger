package Data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import com.example.messeger.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Files {
    private MainActivity activity;
    private String str="Messenger";
    private String path;
    public Files(String path, MainActivity activity)
    {
        this.path=path;
        this.activity=activity;
    }
    public Uri Create(byte[] data)
    {
        try {
            if (isStoragePermissionGranted()) {
                File dir= new File(Environment.getExternalStorageDirectory() + File.separator + str);;
                if (!dir.exists()) dir.mkdirs();
                File newFile = new File(dir.getPath() + File.separator + path);
                if (!newFile.exists()) newFile.createNewFile();
                FileOutputStream stream = new FileOutputStream(newFile);
                stream.write(data);
                stream.close();
                return Uri.fromFile(newFile);
            }
        }catch (IOException e){System.out.println(e.getMessage());}
        return null;
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

}
