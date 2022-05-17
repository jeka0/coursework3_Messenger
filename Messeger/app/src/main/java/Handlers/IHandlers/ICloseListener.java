package Handlers.IHandlers;

import android.widget.SearchView;

public interface ICloseListener extends SearchView.OnCloseListener{
    @Override
    boolean onClose();
}
