package ViewModels;

import ViewModels.IViewModels.IMainViewModel;
import business.Chat;
import business.Message;
import business.MyFIle;

public class MainViewModel extends MessengerViewModel implements IMainViewModel {
    private Chat chat;
    private byte[] image;
    private MyFIle file;
    private int position;
    public MainViewModel()
    {
        super();
    }
    public void setPosition(int position)
    {
        this.position=position;
        chat = getChats()[position];
        Update();
    }
    public void Update()
    {
        if(chat.getMessages().size()==0 || Refresh()){ new Thread(()->super.getClientAccess().UpdatePosts(chat.getName())).start();setRefresh(false);}
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFile(MyFIle file) {
        this.file = file;
    }

    public MyFIle getFile() {
        return file;
    }

    public String getChatName(){return chat.getName();}
    public Message[] getMessages() {
        return getChats()[position].getMessages().toArray(new Message[0]);
    }
}
