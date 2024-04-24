import server.*;

public class SVServer extends Server{
    SVState state;
    
    public SVServer() throws Exception{
         state = new SVState(this);
         this.setMaxNumberOfClients(2);
         start();
    }
    
    @Override
    public void receiveMessage(String message, SClient client){
         state.receiveMessage(message, client);   
    }
}