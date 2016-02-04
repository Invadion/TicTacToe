import javax.swing.JFrame;

public class NoughtsCrossesGUI 
{
		
		public NoughtsCrossesGUI(NoughtsCrossesModel model){
			
			NoughtsCrossesComponent comp = new NoughtsCrossesComponent(model);
			
			JFrame frame = new JFrame("Noughts and Crosses");
			frame.setSize(400, 400);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			frame.add(comp);
			
			frame.setVisible(true);
		}
		
}

