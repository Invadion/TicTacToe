import java.io.*;
import java.net.*;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;
  private NoughtsCrossesModel model;
  ClientReceiver(BufferedReader server,NoughtsCrossesModel model) {
    this.server = server;
    this.model=model;
  }

  public void run() {
    // Print to the user whatever we get from the server:
    try {
      while (true) {
    	//System.out.println("VURTIM SE");
        String s = server.readLine();
        if(s.length() > 30){
        	System.out.println(s + "\nType yes to accept or type no to decline");
        }
        else if(s.equals("yes")){
        	System.out.println("Starting game\n");
        	NoughtsCrossesGUI gui = new NoughtsCrossesGUI(model);
        }
        else 
        System.out.println(s);

      }
    }
    catch (IOException e) {
      System.out.println("Disconnected");
      System.exit(1); // Give up.
    }
  
 }
}
