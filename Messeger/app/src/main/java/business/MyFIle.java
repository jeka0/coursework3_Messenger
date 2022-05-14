package business;

import java.io.Serializable;

public class MyFIle implements Serializable {
    private String Name;
    private byte[] data;
    private String extension;
    private String path;
    public MyFIle() {}
    public MyFIle(byte[] data,String extension)
    {
        this.data=data;
        this.extension=extension;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public String getName() {
        return Name;
    }

    public String getExtension() {
        return extension;
    }

    public String getPath() {
        return path;
    }
}
