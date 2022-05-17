package Handlers;

import android.view.View;

import Handlers.IHandlers.ICloseListener;
import ViewModels.IViewModels.IChatMenuModel;
import ViewModels.MessengerViewModel;

public class CloseListener implements ICloseListener {
    private MessengerViewModel messengerViewModel;
    private IChatMenuModel chatMenuModel;
    public CloseListener(IChatMenuModel chatMenuModel)
    {
        this.chatMenuModel=chatMenuModel;
        this.messengerViewModel = chatMenuModel.getMessengerModel();
    }
    @Override
    public void onViewDetachedFromWindow(View arg0) {
        messengerViewModel.searchOFF();
        messengerViewModel.UpdateChats();
    }

    @Override
    public void onViewAttachedToWindow(View arg0) {
        //messengerViewModel.ClearChats();
        messengerViewModel.searchON();
        new Thread(()->messengerViewModel.getClientAccess().UpdateSelectedChats()).start();
    }
}
