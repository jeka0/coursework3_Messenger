package ViewModels;

import ViewModels.IViewModels.IMainViewModel;
import business.Chat;
import business.Message;
import business.MyFIle;

public class MainViewModel extends MessengerViewModel implements IMainViewModel {
    private Chat chat;
    private byte[] image;
    private MyFIle file;
    public MainViewModel()
    {
        super();
    }
    public void setPosition(int position)
    {
        chat = getChats()[position];
        if(chat.getMessages().size()==0) new Thread(()->super.getClientAccess().UpdatePosts(chat.getName())).start();
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
        return chat.getMessages().toArray(new Message[0]);
    }
}
