package Handlers.IHandlers;

import android.view.View;
import android.widget.SearchView;

public interface ICloseListener extends androidx.appcompat.widget.SearchView.OnAttachStateChangeListener{
    @Override
    void onViewDetachedFromWindow(View arg0);
    @Override
    void onViewAttachedToWindow(View arg0);
}
