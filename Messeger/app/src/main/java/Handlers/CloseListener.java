package Handlers;

import android.view.View;

import Handlers.IHandlers.ICloseListener;
import ViewModels.IViewModels.IChatMenuModel;
import ViewModels.MessengerViewModel;
import ViewModels.SearchViewModel;

public class CloseListener implements ICloseListener {
    private SearchViewModel searchViewModel;
    private MessengerViewModel messengerViewModel;
    private IChatMenuModel chatMenuModel;
    public CloseListener(IChatMenuModel chatMenuModel)
    {
        this.chatMenuModel=chatMenuModel;
        this.searchViewModel = chatMenuModel.getSearchViewModel();
        this.messengerViewModel= chatMenuModel.getMessengerModel();
    }
    @Override
    public void onViewDetachedFromWindow(View arg0) {
        this.messengerViewModel= chatMenuModel.getMessengerModel();
        messengerViewModel.UpdateChats();
    }

    @Override
    public void onViewAttachedToWindow(View arg0) {
        chatMenuModel.GoToSearch();
    }
}
