package business;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serializer {
    private ObjectMapper mapper = new ObjectMapper();
    public <T> String serialize(T item) throws IOException {
        return mapper.writeValueAsString(item);
    }

    public <T> T deserialize(String str, Class<T> type) throws IOException {
       return mapper.readValue(str,type);
    }

}
