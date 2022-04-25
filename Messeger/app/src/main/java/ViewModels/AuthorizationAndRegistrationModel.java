package ViewModels;

import Handlers.ISwitchHandler;

public class AuthorizationAndRegistrationModel extends MyViewModel {
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
