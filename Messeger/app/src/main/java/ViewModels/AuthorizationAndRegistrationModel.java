package ViewModels;

import Handlers.IHandlers.ISwitchHandler;
import ViewModels.IViewModels.IAuthorizationAndRegistrationModel;

public class AuthorizationAndRegistrationModel extends MyViewModel implements IAuthorizationAndRegistrationModel {
    private static ISwitchHandler switchActivity;
    public AuthorizationAndRegistrationModel()
    {
        super();
    }

    public void setSwitchActivity(ISwitchHandler switchActivity) {
        AuthorizationAndRegistrationModel.switchActivity = switchActivity;
        super.getClientAccess().setAppActivity(switchActivity.getActivity());
    }
}
