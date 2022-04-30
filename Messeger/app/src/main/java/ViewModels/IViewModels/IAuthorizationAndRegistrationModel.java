package ViewModels.IViewModels;

import Handlers.IHandlers.ISwitchHandler;

public interface IAuthorizationAndRegistrationModel extends IMyViewModel {
    void setSwitchActivity(ISwitchHandler switchActivity);
}
