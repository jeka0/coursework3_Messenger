package ViewModels;

import Net.ClientAccess;
import Net.Internet;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private static Internet internet;
    public MyViewModel()
    {
       if(internet ==null) internet = new ClientAccess("192.168.43.254");
    }

    public Internet getClientAccess() {
        return internet;
    }
}
