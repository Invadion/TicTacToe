import java.util.Observable;

public class NoughtsCrossesModel extends Observable
{
	private NoughtsCrosses oxo;	
	private String currentTurn="";
	public NoughtsCrossesModel(NoughtsCrosses oxo)
	{
		super();
		this.oxo = oxo;
	}
	
/**
Get symbol at given location
@param i the row
@param j the column
@return the symbol at that location
*/
	public int get(int i, int j)
	{
		return oxo.get(i, j);
	}


/**
Is it cross's turn?
@return true if it is cross's turn, false for nought's turn
*/	
	public boolean isCrossTurn()
	{
		return oxo.isCrossTurn();
	}

/**
Let the player whose turn it is play at a particular location
@param i the row
@param j the column
*/
	public void turn(int i, int j)
	{
		oxo.turn(i, j);
		setChanged();
		notifyObservers();
		setCurrentTurn(i,j);
	}
	public void setCurrentTurn(int i,int j){
		currentTurn = ""+i+j;
	}
	
	public String getCurrentTurn(){
		return currentTurn;
	}
/**
Determine who (if anyone) has won
@return CROSS if cross has won, NOUGHT if nought has won, oetherwise BLANK
*/
	public int whoWon()
	{
		return oxo.whoWon();
	}
	
	
/**
Start a new game
*/
	public void newGame()
	{
		oxo.newGame();
		setChanged();
		notifyObservers();
	}
	public boolean ongoingGame(){
		boolean flag=true;
		int counter=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(oxo.get(i,j)!=0)
					counter++;
			}
		}
		if(counter==9)
			flag=false;
		return flag;
	}
}