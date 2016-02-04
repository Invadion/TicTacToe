import java.net.*;
import java.io.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
  private String myClientsName;
  private BufferedReader myClient;
  private ClientTable clientTable;
  private MovesTable movesTable;
  private OnlineTable online;
  private String playerName;
  private MessageQueue recipientsQueue;
  private boolean kill=false;
  public ServerReceiver(String n, BufferedReader c, ClientTable t,MovesTable mt,OnlineTable on) {
    myClientsName = n;
    myClient = c;
    clientTable = t;
    movesTable=mt;
    online=on;
  }

  public void run() {
	String command = "";
    try {
    	
      while (!kill) {
         command = myClient.readLine();
         Message msg = new Message(myClientsName, command);
         
         if(command.equals("quit")){
        	 recipientsQueue = clientTable.getQueue(myClientsName);
       	     recipientsQueue.offer(msg);
       	     online.removeClient(myClientsName);
        	 kill=true;
        	 break;
         }
         
         else if(command.equals("who") || command.equals("score")){
        	 
        	  recipientsQueue = clientTable.getQueue(myClientsName);
        	  recipientsQueue.offer(msg);
        	  
         }
         
         else if(!command.equals("yes")){
        	 MessageQueue recipientsQueue = clientTable.getQueue(command);
        	 recipientsQueue.offer(msg);
         }
         else{
        	 MessageQueue recipientsQueue = clientTable.getQueue(myClientsName);
        	 recipientsQueue.offer(msg);
         }
         
//         if (recipientsQueue != null)
//                
//         else
//             	System.err.println("Message for unexistent client " 
//                                  + reci + ": " + text);
            }
//        	if(online.getArray().contains(command)){
//        		playerName = command;
//        		GameChallenge msg = new GameChallenge(myClientsName, command);
//        		ChallengeQueue challengedQueue = clientTable.getQueue(command);
//        	
//          
//        		if (challengedQueue != null){
//        	  
//        				challengedQueue.offer(msg);
//        		}
//          
//        		else
//        			System.err.println(command + " is busy.");
//        	}
//        	else if(command.length()==2){
//        		 MovesQueue moveQueue = movesTable.getQueue(playerName);
//        		 
//        		 if(moveQueue!=null){
//        			moveQueue.offer(command);
//        			System.out.println(command+ " " +playerName);
//        		}
//        	}
    	
      
    }
    catch (IOException e) {
      System.err.println(myClientsName + " disconnected"); 
      
      // No point in trying to close sockets. Just give up.
      // We end this thread (we don't do System.exit(1)).
    }
  }
}

