package Handlers.IHandlers;

import android.widget.SearchView;

public interface ITextChangeHandler extends androidx.appcompat.widget.SearchView.OnQueryTextListener{
    @Override
    boolean onQueryTextSubmit(String s);
    @Override
    boolean onQueryTextChange(String s);
}
