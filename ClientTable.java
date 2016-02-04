import java.util.concurrent.*;

public class ClientTable {

  private ConcurrentMap<String,MessageQueue> queueTable  = new ConcurrentHashMap<String,MessageQueue>();

  public void add(String nickname) {
    queueTable.put(nickname, new MessageQueue());
  }

  // Returns null if the nickname is not in the table:
  public MessageQueue getQueue(String nickname) {
    return queueTable.get(nickname);
  }

}
