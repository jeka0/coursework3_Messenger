package Data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class WorkingWithFile {
    private InputStream stream;
    private Uri uri;
    public WorkingWithFile(InputStream imageStream, Uri uri)
    {
        this.stream=imageStream;
        this.uri=uri;
    }

    public byte[] ReadImageBytes()
    {
        try {

            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        }catch (RuntimeException e){System.out.println(e.getMessage());}
        return null;
    }
    public byte[] ReadBytes()
    {
        try {
            byte[] buffer = new byte[1024*1024*2];
            int size = stream.read(buffer);
            byte[] bytes = new byte[size];
            for(int i=0;i<size;i++)bytes[i]=buffer[i];
            return bytes;
        }catch (IOException e){System.out.println(e.getMessage());}
        return null;
    }


}
