import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ClientScore {

  private Map<String,Integer> score  = new HashMap<String,Integer>();

  public void add(String nickname) {
    score.put(nickname, 0);
  }

  // Returns null if the nickname is not in the table:
  public Integer getScore(String nickname) {
    return score.get(nickname);
  }
  public Map getMap(){
	  return score;
  }
}
