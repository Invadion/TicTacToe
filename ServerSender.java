import java.net.*;
import java.util.Iterator;
import java.util.Map;
import java.io.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
  private MessageQueue queue;
  private PrintStream client;
  private MovesQueue movesQueue;
  private boolean kill=false;
  private OnlineTable online;
  private ClientScore score;
  public ServerSender(MessageQueue q, PrintStream c,MovesQueue mq,OnlineTable on,ClientScore sc) {
    queue = q;   
    client = c;
    movesQueue=mq;
    online =on;
    score=sc;
  }

  public void run() {
    while (!kill) {
      Message msg = queue.take();
      if(msg.getText().equals("quit")){
    	  client.println(msg);
    	  kill=true;
    	  break;
    	  
      }
      if(msg.getText().equals("who")){
    	 for(int i=0;i<online.getArray().size();i++){
    		 client.println(online.getArray().get(i));
    	 }
      }
      else if(msg.getText().equals("score")){
    	  Iterator<Map.Entry<String, Integer>> entries = score.getMap().entrySet().iterator();
    	  while (entries.hasNext()) {
    	      Map.Entry<String, Integer> entry = entries.next();
    	      client.println(entry.getKey() + " : " + entry.getValue());
    	  }
      }
      else if(msg.getText().equals("yes")){
    	  client.println("yes");
      }
      else {
    	  client.println("You have been challenged to a game of Tic-Tac-Toe by "+msg.getText());
      }
     
    }
  }
}
 