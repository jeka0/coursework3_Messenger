package Handlers;

import Handlers.IHandlers.ICloseListener;

public class CloseListener implements ICloseListener {
    @Override
    public boolean onClose() {
        return false;
    }
}
