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
    private long size;
    public WorkingWithFile(InputStream imageStream, long size)
    {
        this.stream=imageStream;
        this.size=size;
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

            byte[] buffer = new byte[(int)size];
            stream.read(buffer);
            return buffer;
        }catch (IOException e){System.out.println(e.getMessage());}
        return null;
    }


}
