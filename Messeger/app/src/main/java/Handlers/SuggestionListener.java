package Handlers;

import Handlers.IHandlers.ISuggestionListener;
import ViewModels.MessengerViewModel;

public class SuggestionListener implements ISuggestionListener {
    private MessengerViewModel messengerViewModel;
    public SuggestionListener(MessengerViewModel messengerViewModel)
    {
        this.messengerViewModel = messengerViewModel;
    }
    @Override
    public boolean onSuggestionSelect(int i) {
        return false;
    }

    @Override
    public boolean onSuggestionClick(int i) {
        //Удалить класс
        return false;
    }
}
