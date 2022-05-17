package Handlers.IHandlers;

import android.widget.SearchView;

public interface ISuggestionListener extends SearchView.OnSuggestionListener{
    @Override
    boolean onSuggestionSelect(int i);
    @Override
    boolean onSuggestionClick(int i);
}
