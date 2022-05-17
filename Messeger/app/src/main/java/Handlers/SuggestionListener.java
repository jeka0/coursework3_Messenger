package Handlers;

import Handlers.IHandlers.ISuggestionListener;

public class SuggestionListener implements ISuggestionListener {
    @Override
    public boolean onSuggestionSelect(int i) {
        return false;
    }

    @Override
    public boolean onSuggestionClick(int i) {
        return false;
    }
}
