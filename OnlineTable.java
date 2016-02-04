import java.util.ArrayList;

public class OnlineTable {

	public ArrayList<String> online = new ArrayList<String>();
	
	public ArrayList<String> getArray(){
		return online;
	}
	public void addClient(String nickname){
		online.add(nickname);
	}
	public void removeClient(String nickname){
		online.remove(nickname);
	}
}
