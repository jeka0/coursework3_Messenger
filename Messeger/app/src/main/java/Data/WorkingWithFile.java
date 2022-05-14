package Data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class WorkingWithFile {
    private InputStream imageStream;
    public WorkingWithFile(InputStream imageStream)
    {
        this.imageStream=imageStream;
    }

    public byte[] ReadImageBytes()
    {
        try {

            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        }catch (RuntimeException e){System.out.println(e.getMessage());}
        return null;
    }
    public byte[] ReadBytes()
    {

        return null;
    }


}
