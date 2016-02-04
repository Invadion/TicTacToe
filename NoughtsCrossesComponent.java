import javax.swing.JPanel;
import java.awt.BorderLayout;

public class NoughtsCrossesComponent extends JPanel
{
	public NoughtsCrossesComponent(NoughtsCrossesModel model)
	{
		super();
		
		BoardView board = new BoardView(model);
		//ButtonPanel controls = new ButtonPanel(model);
		
		model.addObserver(board);
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
		//add(controls, BorderLayout.SOUTH);
	}
	
}
