package RequestHandler;

import business.Request;

import java.io.IOException;

public interface IRequestHandler {
    void handle(Request request)throws IOException;
    void answer(Request request) throws IOException;
}
