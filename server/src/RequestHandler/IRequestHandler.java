package RequestHandler;

import business.Chat;
import business.Request;

import java.io.IOException;
import java.util.ArrayList;

public interface IRequestHandler {
    void handle(Request request)throws IOException;
    void answer(Request request) throws IOException;
}
