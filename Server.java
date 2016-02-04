// Usage:
//        java Server
//
// There is no provision for ending the server gracefully.  It will
// end if (and only if) something exceptional happens.


import java.net.*;
import java.io.*;

public class Server {
	
	public static void main(String [] args) {

    // This will be shared by the server threads:
    ClientTable clientTable = new ClientTable();
    ClientScore score = new ClientScore();
    MovesTable movesTable = new MovesTable();
    OnlineTable online = new OnlineTable();

    // Open a server socket:
    ServerSocket serverSocket = null;
    if (args.length != 1) {
        System.err.println("Usage: java Server port");
        System.exit(1); // 
      }
    int port = Integer.parseInt(args[0]);

    // We must try because it may fail with a checked exception:
    try {
      serverSocket = new ServerSocket(port);
    } 
    catch (IOException e) {
      System.err.println("Couldn't listen on port " + port);
      System.exit(1); // Give up.
    }

    // Good. We succeeded. But we must try again for the same reason:
    try { 
      // We loop for ever, as servers usually do:

      while (true) {
        // Listen to the socket, accepting connections from new clients:
        Socket socket = serverSocket.accept();

        // This is so that we can use readLine():
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream toClient = new PrintStream(socket.getOutputStream());
        
        String clientName = fromClient.readLine();
        while(online.getArray().contains(clientName)){
        	clientName = clientName+"1";
        }
       
        System.out.println(clientName + " connected");

        // We add the client to the table:
        clientTable.add(clientName);
        online.addClient(clientName);
        score.add(clientName);
        movesTable.add(clientName);
        
        (new ServerReceiver(clientName, fromClient, clientTable,movesTable,online)).start();
        
        (new ServerSender(clientTable.getQueue(clientName), toClient,movesTable.getQueue(clientName),online,score)).start();
      }
    } 
    catch (IOException e) {
      // Lazy approach:
      System.err.println("IO error " + e.getMessage());
      // A more sophisticated approach could try to establish a new
      // connection. But this is beyond this simple exercise.
    }
  }
}
