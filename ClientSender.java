import java.io.*;


// Repeatedly reads recipient's nickname and text from the user in two
// separate lines, sending them to the server (read by ServerReceiver
// thread).

public class ClientSender extends Thread {

  private String nickname;
  private PrintStream server;
  private NoughtsCrossesModel model;
  private boolean run = true;

  ClientSender(String nickname, PrintStream server,NoughtsCrossesModel model) {
    this.nickname = nickname;
    this.server = server;
    this.model=model;
  }

  public void run() {
    // So that we can use the method readLine:
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    try {
      // Tell the server what my nickname is:
      server.println(nickname);
      
      // Then loop forever sending messages to recipients via the server:
      while (run) {
    	  
        String command = user.readLine();
        if(command.equals("quit")){
        	server.println("quit");
        	run=false;
        	server.close();
        	break;
        }
        else if(command.equals("yes")){
        	System.out.println(nickname);
        	server.println("yes");
        	NoughtsCrossesGUI gui = new NoughtsCrossesGUI(model);
        }
	    else
	    	server.println(command);
        
      
      }
    }
    catch (IOException e) {
      System.err.println("Communication broke in ClientSender" 
                        + e.getMessage());
      System.exit(1);
    }
  }
}

