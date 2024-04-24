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

    public static void main(String[] args) {
          try {
               new SVServer();
               new SVClient("localhost");
          } catch (Exception e) {
               System.err.println("Error: " + e);   
          }
     }
}