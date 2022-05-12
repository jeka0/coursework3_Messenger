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

    public byte[] ReadBytes()
    {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        }catch (RuntimeException e){}
        return null;
    }


}
