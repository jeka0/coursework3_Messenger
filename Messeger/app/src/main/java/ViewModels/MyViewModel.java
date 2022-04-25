package ViewModels;

import Net.ClientAccess;
import Net.IInternet;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private static IInternet IInternet;
    public MyViewModel()
    {
       if(IInternet ==null) IInternet = new ClientAccess("192.168.1.101");
    }

    public IInternet getClientAccess() {
        return IInternet;
    }
}
