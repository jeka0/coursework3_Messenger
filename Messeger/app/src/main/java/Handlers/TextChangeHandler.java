package Handlers;

import Handlers.IHandlers.ITextChangeHandler;

public class TextChangeHandler implements ITextChangeHandler {
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
