package DataBase;

import business.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWork {
    private String path;
    private ObjectMapper mapper = new ObjectMapper();
    public JsonWork(String path){this.path = path;}
    public <T> void Write(T item)
    {
        try {
            FileWriter writer = new FileWriter(path);
            mapper.writeValue(writer,item);
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public <T> T Read(Class<T> type)
    {
        try {
            FileReader reader = new FileReader(path);
            return mapper.readValue(reader,type);
        }catch(IOException e){System.out.println(e.getMessage()); return null;}
    }
    public <T> T[] ReadArray(Class<T[]> type)
    {
        try {
            FileReader reader = new FileReader(path);
            return mapper.readValue(reader,type);
        }catch(IOException e){System.out.println(e.getMessage()); return null;}
    }
}
