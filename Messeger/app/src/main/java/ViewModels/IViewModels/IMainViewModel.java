package ViewModels.IViewModels;

import business.Message;
import business.MyFIle;
import business.User;

public interface IMainViewModel extends IMessengerViewModel{
    Message[] getMessages();
    void setPosition(int position);
    void Update();
    String getChatName();
    byte[] getImage();
    void setImage(byte[] image);
    void setFile(MyFIle file);
    MyFIle getFile();
}
