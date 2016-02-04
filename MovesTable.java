import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MovesTable {

  private ConcurrentMap<String,MovesQueue> movesTable  = new ConcurrentHashMap<String,MovesQueue>();

  public void add(String nickname) {
    movesTable.put(nickname, new MovesQueue());
  }

  // Returns null if the nickname is not in the table:
  public MovesQueue getQueue(String nickname) {
    return movesTable.get(nickname);
  }

}
