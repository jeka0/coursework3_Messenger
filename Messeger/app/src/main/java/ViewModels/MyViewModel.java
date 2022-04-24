package ViewModels;

import Net.ClientAccess;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private static ClientAccess clientAccess;
    public MyViewModel()
    {
       if(clientAccess==null) clientAccess = new ClientAccess("192.168.1.101");
    }

    public ClientAccess getClientAccess() {
        return clientAccess;
    }
}
