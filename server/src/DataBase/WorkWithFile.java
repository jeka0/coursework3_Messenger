package DataBase;

import java.io.*;

public class WorkWithFile {
    private String path;
    private File files;
    public WorkWithFile(String path)
    {
        this.path=path;
        files = new File("database\\Files");
        files.mkdirs();
    }
    public void WriteFile(byte[] bytes)
    {
        try {
            FileOutputStream out = new FileOutputStream("database\\Files\\"+path);
            out.write(bytes);
            out.close();

        }catch (FileNotFoundException e){}catch (IOException e){}
    }
    public byte[] ReadFile()
    {
        try {
            FileInputStream inF = new FileInputStream("database\\Files\\"+path);
            byte[] bytes = inF.readAllBytes();
            inF.close();
            return bytes;
        }catch (FileNotFoundException e){}catch (IOException e){}
        return null;
    }
}
