package Final;


import java.awt.Graphics;
import javax.swing.JPanel;

public class PacmanPanelLevel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PacmanField arr;
	Updated win;

	public PacmanPanelLevel(PacmanField array, Updated window) {
		arr = array;
		win = window;
	}
	
	public void paint(Graphics g)  {
		super.paint(g);	
		arr.drawField(g, this.getWidth(), this.getHeight());
	}
	

}

