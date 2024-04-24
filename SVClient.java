import client.*;

public class SVClient extends Client{
     SVView view;
     
     public SVClient(String ip) throws Exception{
         view = new SVView(this);
         this.connect(ip, 4444);
         sendMessage("neu");
     }
      
     @Override
     public void receiveMessage(String text){
         view.receiveMessage(text);        
     }

     public static void main(String[] args) {
        try {
            new SVClient("localhost");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
     }
}