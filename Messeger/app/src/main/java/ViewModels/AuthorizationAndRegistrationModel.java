package ViewModels;

import Handlers.SwitchActivity;

public class AuthorizationAndRegistrationModel extends MyViewModel {
    private static SwitchActivity switchActivity;
    public AuthorizationAndRegistrationModel()
    {
        super();
    }

    public void setSwitchActivity(SwitchActivity switchActivity) {
        AuthorizationAndRegistrationModel.switchActivity = switchActivity;
        super.getClientAccess().setAppActivity(switchActivity.getActivity());
    }
}
